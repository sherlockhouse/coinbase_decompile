package com.coinbase.zxing.client.android;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import java.util.Map;

final class DecodeHandler extends Handler {
    private static final String TAG = DecodeHandler.class.getSimpleName();
    private final CaptureActivity activity;
    private final MultiFormatReader multiFormatReader = new MultiFormatReader();
    private boolean running = true;

    DecodeHandler(CaptureActivity activity, Map<DecodeHintType, Object> hints) {
        this.multiFormatReader.setHints(hints);
        this.activity = activity;
    }

    public void handleMessage(Message message) {
        if (!this.running) {
            return;
        }
        if (message.what == R.id.decode) {
            decode((byte[]) message.obj, message.arg1, message.arg2);
        } else if (message.what == R.id.quit) {
            this.running = false;
            Looper.myLooper().quit();
        }
    }

    private void decode(byte[] data, int width, int height) {
        long start = System.currentTimeMillis();
        Result rawResult = null;
        if (this.activity.getResources().getConfiguration().orientation == 1) {
            byte[] rotatedData = new byte[data.length];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    rotatedData[(((x * height) + height) - y) - 1] = data[(y * width) + x];
                }
            }
            int tmp = width;
            width = height;
            height = tmp;
            data = rotatedData;
        }
        PlanarYUVLuminanceSource source = this.activity.getCameraManager().buildLuminanceSource(data, width, height);
        if (source != null) {
            try {
                rawResult = this.multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(source)));
            } catch (ReaderException e) {
            } finally {
                this.multiFormatReader.reset();
            }
        }
        Handler handler = this.activity.getHandler();
        if (rawResult != null) {
            Log.d(TAG, "Found barcode in " + (System.currentTimeMillis() - start) + " ms");
            if (handler != null) {
                Message message = Message.obtain(handler, R.id.decode_succeeded, rawResult);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DecodeThread.BARCODE_BITMAP, toBitmap(source, source.renderCroppedGreyscaleBitmap()));
                message.setData(bundle);
                message.sendToTarget();
            }
        } else if (handler != null) {
            Message.obtain(handler, R.id.decode_failed).sendToTarget();
        }
    }

    private static Bitmap toBitmap(LuminanceSource source, int[] pixels) {
        int width = source.getWidth();
        int height = source.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
