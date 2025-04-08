package uz.falconmobile.ai_obida.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.*;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uz.falconmobile.ai_obida.service.ImageClassifier;
import uz.falconmobile.ai_obida.R;
import uz.falconmobile.ai_obida.service.YuvToRgbConverter;

public class MainActivity extends AppCompatActivity {

    private PreviewView previewView;
    private ImageView imageView;
    private TextView textResult;
    private Button btnCapture;

    private ImageCapture imageCapture;
    private ImageClassifier classifier;
    private ExecutorService cameraExecutor;
    private YuvToRgbConverter yuvToRgbConverter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(uz.falconmobile.ai_obida.R.layout.activity_main);

        previewView = findViewById(uz.falconmobile.ai_obida.R.id.previewView);
        imageView = findViewById(uz.falconmobile.ai_obida.R.id.image_view);
        textResult = findViewById(uz.falconmobile.ai_obida.R.id.text_result);
        btnCapture = findViewById(R.id.btn_capture);

        cameraExecutor = Executors.newSingleThreadExecutor();
        yuvToRgbConverter = new YuvToRgbConverter(this);

        try {
            classifier = new ImageClassifier(getAssets());
        } catch (Exception e) {
            Toast.makeText(this, "Modelni yuklashda xatolik: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }

        requestPermissionAndStartCamera();

        btnCapture.setOnClickListener(v -> captureImage());
    }

    private void requestPermissionAndStartCamera() {
        registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                startCamera();
            } else {
                Toast.makeText(this, "Kamera ruxsati kerak", Toast.LENGTH_SHORT).show();
            }
        }).launch(android.Manifest.permission.CAMERA);
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                imageCapture = new ImageCapture.Builder()
                        .setTargetResolution(new Size(224, 224))
                        .build();

                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);

            } catch (Exception e) {
                Log.e(TAG, "Kamerani ishga tushirishda xatolik: " + e.getMessage());
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void captureImage() {
        if (imageCapture == null) return;

        imageCapture.takePicture(ContextCompat.getMainExecutor(this), new ImageCapture.OnImageCapturedCallback() {
            @Override
            public void onCaptureSuccess(@NonNull ImageProxy image) {
                Bitmap bitmap = yuvToRgbConverter.yuvToRgb(image);
                image.close();

                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    ImageClassifier.ClassificationResult result = classifier.classify(bitmap);
                    textResult.setText(result.toString());
                    Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                Toast.makeText(MainActivity.this, "Rasm olishda xatolik", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Rasm olishda xatolik: " + exception.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (classifier != null) classifier.close();
        if (cameraExecutor != null) cameraExecutor.shutdown();
        super.onDestroy();
    }
}
