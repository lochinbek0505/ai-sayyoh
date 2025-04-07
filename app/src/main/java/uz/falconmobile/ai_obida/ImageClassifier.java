package uz.falconmobile.ai_obida;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;

import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class ImageClassifier {

    private static final String TAG = "ImageClassifier";
    private static final String MODEL_PATH = "model_unquant.tflite";
    private static final String LABEL_PATH = "labels3.txt";

    private static final int DIM_BATCH_SIZE = 1;
    private static final int DIM_PIXEL_SIZE = 3;
    static final int DIM_IMG_SIZE_X = 224;
    static final int DIM_IMG_SIZE_Y = 224;

    private Interpreter tflite;
    private List<String> labelList;
    private ByteBuffer imgData;
    private float[][] labelProbArray;

    public ImageClassifier(Activity activity) throws IOException {
        Interpreter.Options options = new Interpreter.Options();
        options.setNumThreads(4); // ko‘proq performance uchun
        tflite = new Interpreter(loadModelFile(activity), options);

        labelList = loadLabelList(activity);

        imgData = ByteBuffer.allocateDirect(4 * DIM_BATCH_SIZE * DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y * DIM_PIXEL_SIZE);
        imgData.order(ByteOrder.nativeOrder());

        labelProbArray = new float[1][labelList.size()];
    }

    public String classifyFrame(Bitmap bitmap) {
        if (tflite == null) return "Classifier not initialized.";

        bitmap = Bitmap.createScaledBitmap(bitmap, DIM_IMG_SIZE_X, DIM_IMG_SIZE_Y, true);
        convertBitmapToByteBuffer(bitmap);

        long startTime = SystemClock.uptimeMillis();
        tflite.run(imgData, labelProbArray);
        long endTime = SystemClock.uptimeMillis();

        String result = getTopResult();
        Log.d(TAG, "Inference time: " + (endTime - startTime) + "ms, Result: " + result);
        return result;
    }

    private String getTopResult() {
        int maxIndex = 0;
        float maxProb = labelProbArray[0][0];

        for (int i = 1; i < labelList.size(); i++) {
            if (labelProbArray[0][i] > maxProb) {
                maxProb = labelProbArray[0][i];
                maxIndex = i;
            }
        }
        return String.format("%s (%.2f)", labelList.get(maxIndex), maxProb);
    }

    private void convertBitmapToByteBuffer(Bitmap bitmap) {
        if (imgData == null) return;
        imgData.rewind();

        int[] intValues = new int[DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y];
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        int pixelIndex = 0;
        for (int i = 0; i < DIM_IMG_SIZE_X; ++i) {
            for (int j = 0; j < DIM_IMG_SIZE_Y; ++j) {
                final int val = intValues[pixelIndex++];
                imgData.putFloat(((val >> 16) & 0xFF) / 255.0f); // R
                imgData.putFloat(((val >> 8) & 0xFF) / 255.0f);  // G
                imgData.putFloat((val & 0xFF) / 255.0f);         // B
            }
        }
    }

    private List<String> loadLabelList(Activity activity) throws IOException {
        List<String> labels = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(activity.getAssets().open(LABEL_PATH)));
        String line;
        while ((line = reader.readLine()) != null) {
            labels.add(line);
        }
        reader.close();
        return labels;
    }

    private MappedByteBuffer loadModelFile(Activity activity) throws IOException {
        FileInputStream inputStream = new FileInputStream(activity.getAssets().openFd(MODEL_PATH).getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = activity.getAssets().openFd(MODEL_PATH).getStartOffset();
        long declaredLength = activity.getAssets().openFd(MODEL_PATH).getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public void close() {
        if (tflite != null) {
            tflite.close();
            tflite = null;
        }
    }
}
