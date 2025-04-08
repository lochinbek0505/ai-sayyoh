package uz.falconmobile.ai_obida.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.YuvImage;
import androidx.camera.core.ImageProxy;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class YuvToRgbConverter {

    public YuvToRgbConverter(Context context) {}

    public Bitmap yuvToRgb(ImageProxy image) {
        // Tasvirni YUV_420_888 formatidan RGB ga o'zgartirish
        int width = image.getWidth();
        int height = image.getHeight();

        // YUV_420_888 formatida, har bir plane uchun buffer olish
        ByteBuffer yBuffer = image.getPlanes()[0].getBuffer(); // Y plane
        ByteBuffer uBuffer = null;
        ByteBuffer vBuffer = null;

        if (image.getPlanes().length >= 2) {
            uBuffer = image.getPlanes()[1].getBuffer(); // U plane
        }
        if (image.getPlanes().length >= 3) {
            vBuffer = image.getPlanes()[2].getBuffer(); // V plane
        }

        int ySize = yBuffer.remaining();

        byte[] nv21;
        if (uBuffer != null && vBuffer != null) {
            int uSize = uBuffer.remaining();
            int vSize = vBuffer.remaining();
            nv21 = new byte[ySize + uSize + vSize];

            yBuffer.get(nv21, 0, ySize);
            uBuffer.get(nv21, ySize, uSize);
            vBuffer.get(nv21, ySize + uSize, vSize);
        } else {
            // Agar U yoki V planes mavjud bo'lmasa, faqat Y plane bilan ishlash
            nv21 = new byte[ySize];
            yBuffer.get(nv21, 0, ySize);
        }

        // YUV tasvirni JPEG formatiga o'zgartirish
        YuvImage yuvImage = new YuvImage(nv21, ImageFormat.NV21, width, height, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new android.graphics.Rect(0, 0, width, height), 100, out);
        byte[] imageBytes = out.toByteArray();

        // JPEG tasvirdan Bitmap yaratish
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length)
                .copy(Bitmap.Config.ARGB_8888, true);
    }
}
