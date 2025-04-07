package uz.falconmobile.ai_obida;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.*;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 1001;

    private PreviewView previewView;
    private TextView textResult;
    private Button btnCapture;
    private ImageView imageView;
    private ImageClassifier imageClassifier;
    private ExecutorService cameraExecutor;
    private ImageCapture imageCapture; // ImageCapture instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previewView = findViewById(R.id.previewView);
        textResult = findViewById(R.id.text_result);
        btnCapture = findViewById(R.id.btn_capture);
        imageView = findViewById(R.id.image_view);

        cameraExecutor = Executors.newSingleThreadExecutor();

        try {
            imageClassifier = new ImageClassifier(this);
        } catch (Exception e) {
            Toast.makeText(this, "Modelni yuklab bo‘lmadi", Toast.LENGTH_LONG).show();
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        } else {
            startCamera();
        }

        btnCapture.setOnClickListener(v -> captureImage());
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                // Create an ImageCapture instance
                imageCapture = new ImageCapture.Builder()
                        .setTargetResolution(new Size(224, 224))  // O'lchamni to'g'ri sozlash
                        .build();

                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(
                        this, cameraSelector, preview, imageCapture);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void captureImage() {
        if (imageCapture != null) {
            // Rasmni olish uchun
            imageCapture.takePicture(
                    ContextCompat.getMainExecutor(this), new ImageCapture.OnImageCapturedCallback() {
                        @Override
                        public void onCaptureSuccess(@NonNull ImageProxy image) {
                            super.onCaptureSuccess(image);
                            Bitmap bitmap = toBitmap(image);
                            if (bitmap != null) {
                                // Rasmni ko'rsatish
                                imageView.setImageBitmap(bitmap);

                                // Tasniflash
                                String result = imageClassifier.classifyFrame(bitmap);
                                Log.e("tttttt",result);
                                Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
                                textResult.setText(result);
                            }
                            image.close();  // Tasvirni ishlatib bo'lgach, uni yopish
                        }

                        @Override
                        public void onError(@NonNull ImageCaptureException exception) {
                            super.onError(exception);
                            Toast.makeText(MainActivity.this, "Rasm olishda xatolik yuz berdi", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private Bitmap toBitmap(ImageProxy image) {
        // YUV tasvirini RGB ga aylantirish
        YuvToRgbConverter converter = new YuvToRgbConverter(this);
        return converter.yuvToRgb(image);  // Yangi yuvToRgb methodini chaqirish
    }

    @Override
    protected void onDestroy() {
        imageClassifier.close();
        cameraExecutor.shutdown();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            startCamera();
        }
    }
}
