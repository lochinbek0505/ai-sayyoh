package uz.falconmobile.ai_obida.service;

import android.content.res.AssetManager;
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

    private static final String TAG = "TFImageClassifier";
    private static final String MODEL_PATH = "model_min.tflite";  // Model path
    private static final String LABEL_PATH = "labels_min.txt";    // Labels path

    private static final int INPUT_WIDTH = 224;  // Input image width
    private static final int INPUT_HEIGHT = 224; // Input image height
    private static final int PIXEL_SIZE = 3;     // Number of channels in the image (RGB)

    private Interpreter tflite;  // Interpreter to run the model
    private final List<String> labels; // List of labels

    private boolean isInterpreterClosed = false; // To track if the interpreter is closed

    // Constructor
    public ImageClassifier(AssetManager assetManager) throws IOException {
        tflite = new Interpreter(loadModelFile(assetManager, MODEL_PATH)); // Load model
        labels = loadLabelList(assetManager, LABEL_PATH); // Load labels
    }

    // Classify method that accepts a Bitmap and returns the classification result
    public synchronized ClassificationResult classify(Bitmap bitmap) {
        if (isInterpreterClosed) {
            throw new IllegalStateException("Interpreter is closed and cannot be used for inference.");
        }

        // Resize the image to the model input size
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, INPUT_WIDTH, INPUT_HEIGHT, true);

        // Convert the image to a ByteBuffer format
        ByteBuffer inputBuffer = convertBitmapToByteBuffer(resized);

        // Array to store the model's output
        float[][] output = new float[1][labels.size()];

        // Start measuring inference time
        long start = SystemClock.uptimeMillis();
        tflite.run(inputBuffer, output); // Run inference
        long end = SystemClock.uptimeMillis();

        // Get the label with the maximum confidence score
        int maxIndex = getMaxConfidenceIndex(output[0]);
        float confidence = output[0][maxIndex];
        String label = labels.get(maxIndex);

        Log.d(TAG, "Inference Time: " + (end - start) + "ms");  // Log the inference time
        return new ClassificationResult(label, confidence); // Return the result
    }

    // Convert a Bitmap to ByteBuffer format
    private ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap) {
        // Calculate buffer size (224 * 224 * 3 channels * 4 bytes per channel for float)
        ByteBuffer buffer = ByteBuffer.allocateDirect(INPUT_WIDTH * INPUT_HEIGHT * PIXEL_SIZE * 4);
        buffer.order(ByteOrder.nativeOrder()); // Set byte order

        // Get the pixel data from the bitmap
        int[] pixels = new int[INPUT_WIDTH * INPUT_HEIGHT];
        bitmap.getPixels(pixels, 0, INPUT_WIDTH, 0, 0, INPUT_WIDTH, INPUT_HEIGHT);

        // Normalize and write pixel values to the buffer
        for (int pixel : pixels) {
            float r = (((float) ((pixel >> 16) & 255)) - 127.5f) / 127.5f;
            float g = (((float) ((pixel >> 8) & 255)) - 127.5f) / 127.5f;
            float b = (((float) (pixel & 255)) - 127.5f) / 127.5f;

            buffer.putFloat(r);  // Add Red channel
            buffer.putFloat(g);  // Add Green channel
            buffer.putFloat(b);  // Add Blue channel
        }

        buffer.rewind();  // Rewind the buffer to read it later
        return buffer;  // Return the ByteBuffer
    }

    // Get the index of the label with the highest confidence score
    private int getMaxConfidenceIndex(float[] probs) {
        int maxIdx = 0;
        float max = probs[0];
        for (int i = 1; i < probs.length; i++) {
            if (probs[i] > max) {
                max = probs[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    // Load the model file from the assets folder
    private MappedByteBuffer loadModelFile(AssetManager assetManager, String modelPath) throws IOException {
        try (FileInputStream fis = new FileInputStream(assetManager.openFd(modelPath).getFileDescriptor())) {
            FileChannel channel = fis.getChannel();
            long startOffset = assetManager.openFd(modelPath).getStartOffset();
            long declaredLength = assetManager.openFd(modelPath).getDeclaredLength();
            return channel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
        }
    }

    // Load the label list from the assets folder
    private List<String> loadLabelList(AssetManager assetManager, String labelPath) throws IOException {
        List<String> labelList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open(labelPath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                labelList.add(line);
            }
        }
        return labelList;
    }

    // Close the TensorFlow Lite interpreter
    public synchronized void close() {
        if (tflite != null && !isInterpreterClosed) {
            tflite.close();
            isInterpreterClosed = true;
        }
    }

    // Inner class to store the classification result
    public static class ClassificationResult {
        public final String label;   // The label of the classification
        public final float confidence; // The confidence score

        public ClassificationResult(String label, float confidence) {
            this.label = label;
            this.confidence = confidence;
        }

        @Override
        public String toString() {
            return String.format("%s: %.2f", label, confidence);  // Format the result as a string
        }
    }
}
