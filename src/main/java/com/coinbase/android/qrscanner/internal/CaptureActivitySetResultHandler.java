package com.coinbase.android.qrscanner.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.coinbase.zxing.client.android.BeepManager;
import com.coinbase.zxing.client.android.Intents.Scan;
import com.google.android.gms.vision.barcode.Barcode;

class CaptureActivitySetResultHandler extends Handler {
    static int DETECTION_RESULT = 1;
    private static final String TAG = CaptureActivitySetResultHandler.class.getSimpleName();
    private final BeepManager mBeepManager;
    private volatile CaptureActivityScreen mScreen;

    CaptureActivitySetResultHandler(Looper looper, CaptureActivityScreen screen, BeepManager beepManager) {
        super(looper);
        this.mScreen = screen;
        this.mBeepManager = beepManager;
    }

    public void handleMessage(Message message) {
        if (this.mScreen != null) {
            if (message.what == DETECTION_RESULT) {
                this.mBeepManager.playBeepSoundAndVibrate();
                Barcode obj = (Barcode) message.obj;
                Intent intent = new Intent(((Activity) this.mScreen).getIntent().getAction());
                intent.putExtra(Scan.RESULT, obj.rawValue);
                this.mScreen.setResult(-1, intent);
                this.mScreen.finish();
                return;
            }
            Log.w(TAG, "Had error getting barcode");
            this.mScreen.displayFrameworkBugMessageAndExit();
        }
    }

    void onDestroy() {
        this.mScreen = null;
    }
}
