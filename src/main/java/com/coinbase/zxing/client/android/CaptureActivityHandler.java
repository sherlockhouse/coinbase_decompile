package com.coinbase.zxing.client.android;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.coinbase.zxing.client.android.camera.CameraManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.Collection;

public final class CaptureActivityHandler extends Handler {
    private static final String TAG = CaptureActivityHandler.class.getSimpleName();
    private final CaptureActivity activity;
    private final CameraManager cameraManager;
    private final DecodeThread decodeThread;
    private State state = State.SUCCESS;

    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    CaptureActivityHandler(CaptureActivity activity, Collection<BarcodeFormat> decodeFormats, String characterSet, CameraManager cameraManager) {
        this.activity = activity;
        this.decodeThread = new DecodeThread(activity, decodeFormats, characterSet, new ViewfinderResultPointCallback(activity.getViewfinderView()));
        this.decodeThread.start();
        this.cameraManager = cameraManager;
        cameraManager.startPreview();
        restartPreviewAndDecode();
    }

    public void handleMessage(Message message) {
        if (message.what == R.id.restart_preview) {
            Log.d(TAG, "Got restart preview message");
            restartPreviewAndDecode();
        } else if (message.what == R.id.decode_succeeded) {
            Bitmap barcode;
            Log.d(TAG, "Got decode succeeded message");
            this.state = State.SUCCESS;
            Bundle bundle = message.getData();
            if (bundle == null) {
                barcode = null;
            } else {
                barcode = (Bitmap) bundle.getParcelable(DecodeThread.BARCODE_BITMAP);
            }
            this.activity.handleDecode((Result) message.obj, barcode);
        } else if (message.what == R.id.decode_failed) {
            this.state = State.PREVIEW;
            this.cameraManager.requestPreviewFrame(this.decodeThread.getHandler(), R.id.decode);
        } else if (message.what == R.id.return_scan_result) {
            Log.d(TAG, "Got return scan result message");
            this.activity.setResult(-1, (Intent) message.obj);
            this.activity.finish();
        } else if (message.what == R.id.launch_product_query) {
            Log.d(TAG, "Got product query message");
            String url = message.obj;
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(524288);
            intent.setData(Uri.parse(url));
            ResolveInfo resolveInfo = this.activity.getPackageManager().resolveActivity(intent, 65536);
            String browserPackageName = null;
            if (resolveInfo.activityInfo != null) {
                browserPackageName = resolveInfo.activityInfo.packageName;
                Log.d(TAG, "Using browser in package " + browserPackageName);
            }
            if ("com.android.browser".equals(browserPackageName)) {
                intent.setPackage(browserPackageName);
                intent.addFlags(268435456);
                intent.putExtra("com.android.browser.application_id", browserPackageName);
            }
            try {
                this.activity.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Log.w(TAG, "Can't find anything to handle VIEW of URI " + url);
            }
        }
    }

    public void quitSynchronously() {
        this.state = State.DONE;
        this.cameraManager.stopPreview();
        Message.obtain(this.decodeThread.getHandler(), R.id.quit).sendToTarget();
        try {
            this.decodeThread.join(500);
        } catch (InterruptedException e) {
        }
        removeMessages(R.id.decode_succeeded);
        removeMessages(R.id.decode_failed);
    }

    private void restartPreviewAndDecode() {
        if (this.state == State.SUCCESS) {
            this.state = State.PREVIEW;
            this.cameraManager.requestPreviewFrame(this.decodeThread.getHandler(), R.id.decode);
            this.activity.drawViewfinder();
        }
    }
}
