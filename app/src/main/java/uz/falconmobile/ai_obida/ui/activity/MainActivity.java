package uz.falconmobile.ai_obida.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.*;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uz.falconmobile.ai_obida.R;
import uz.falconmobile.ai_obida.service.ImageClassifier;
import uz.falconmobile.ai_obida.service.SharedPreferenceHelper;
import uz.falconmobile.ai_obida.service.YuvToRgbConverter;

public class MainActivity extends AppCompatActivity {

    private PreviewView previewView;
    private MaterialButton button;
    private String key = "";
    private ImageClassifier classifier;
    private ExecutorService cameraExecutor;
    private YuvToRgbConverter yuvToRgbConverter;
    private boolean isDestroyed = false;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previewView = findViewById(R.id.previewView);
        button = findViewById(R.id.btn_capture);
        cameraExecutor = Executors.newSingleThreadExecutor();
        yuvToRgbConverter = new YuvToRgbConverter(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove classifier.close() here
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });

        try {
            classifier = new ImageClassifier(getAssets());
        } catch (Exception e) {
            Toast.makeText(this, "Modelni yuklashda xatolik: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }

        requestPermissionAndStartCamera();
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

                ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                        .setTargetResolution(new Size(224, 224))
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build();

                imageAnalysis.setAnalyzer(cameraExecutor, image -> {
                    Bitmap bitmap = yuvToRgbConverter.yuvToRgb(image);
                    if (bitmap != null) {
                        ImageClassifier.ClassificationResult result = classifier.classify(bitmap);
                        runOnUiThread(() -> {
                            key = result.label.toString();
                            SharedPreferenceHelper.saveData(MainActivity.this, key);
                            button.setText(result.label.toString());
                        });
                    }
                    image.close();
                });

                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis);

            } catch (Exception e) {
                Log.e(TAG, "Kamerani ishga tushirishda xatolik: " + e.getMessage());
            }
        }, ContextCompat.getMainExecutor(this));
    }

    @Override
    protected void onDestroy() {
        // Close the classifier here, when it's no longer needed
        isDestroyed = true;
        if (classifier != null) classifier.close();
        if (cameraExecutor != null) cameraExecutor.shutdown();
        super.onDestroy();
    }
}
