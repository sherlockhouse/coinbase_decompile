package com.coinbase.zxing.client.android;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPointCallback;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

final class DecodeThread extends Thread {
    public static final String BARCODE_BITMAP = "barcode_bitmap";
    private final CaptureActivity activity;
    private Handler handler;
    private final CountDownLatch handlerInitLatch = new CountDownLatch(1);
    private final Map<DecodeHintType, Object> hints = new EnumMap(DecodeHintType.class);

    DecodeThread(CaptureActivity activity, Collection<BarcodeFormat> decodeFormats, String characterSet, ResultPointCallback resultPointCallback) {
        this.activity = activity;
        if (decodeFormats == null || decodeFormats.isEmpty()) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
            decodeFormats = EnumSet.noneOf(BarcodeFormat.class);
            if (prefs.getBoolean(PreferencesActivity.KEY_DECODE_1D, false)) {
                decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
            }
            if (prefs.getBoolean(PreferencesActivity.KEY_DECODE_QR, false)) {
                decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            }
            if (prefs.getBoolean(PreferencesActivity.KEY_DECODE_DATA_MATRIX, false)) {
                decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
            }
        }
        this.hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
        if (characterSet != null) {
            this.hints.put(DecodeHintType.CHARACTER_SET, characterSet);
        }
        this.hints.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, resultPointCallback);
    }

    Handler getHandler() {
        try {
            this.handlerInitLatch.await();
        } catch (InterruptedException e) {
        }
        return this.handler;
    }

    public void run() {
        Looper.prepare();
        this.handler = new DecodeHandler(this.activity, this.hints);
        this.handlerInitLatch.countDown();
        Looper.loop();
    }
}
