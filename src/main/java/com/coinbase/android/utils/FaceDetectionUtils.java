package com.coinbase.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.SparseArray;
import com.coinbase.android.ApplicationScope;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.FaceDetector.Builder;
import rx.Observable;
import rx.Subscriber;

@ApplicationScope
public class FaceDetectionUtils {
    private static final int[] ROTATIONS = new int[]{0, 90, ROTATION_180, ROTATION_270};
    private static final int ROTATION_0 = 0;
    private static final int ROTATION_180 = 180;
    private static final int ROTATION_270 = 270;
    private static final int ROTATION_90 = 90;

    public Observable<Boolean> hasFace(Context context, Bitmap bitmap) {
        return Observable.create(FaceDetectionUtils$$Lambda$1.lambdaFactory$(this, context, bitmap));
    }

    static /* synthetic */ void lambda$hasFace$0(FaceDetectionUtils this_, Context context, Bitmap bitmap, Subscriber subscriber) {
        int i = 0;
        if (context == null || bitmap == null) {
            subscriber.onNext(Boolean.valueOf(false));
            subscriber.onCompleted();
            return;
        }
        boolean result = false;
        FaceDetector detector = new Builder(context).setTrackingEnabled(false).setLandmarkType(1).setMode(0).build();
        if (detector.isOperational()) {
            int[] iArr = ROTATIONS;
            int length = iArr.length;
            while (i < length) {
                SparseArray<Face> faces = detector.detect(new Frame.Builder().setBitmap(this_.rotate(bitmap, iArr[i])).build());
                if (faces != null && faces.size() > 0) {
                    result = true;
                    break;
                }
                i++;
            }
            detector.release();
        }
        subscriber.onNext(Boolean.valueOf(result));
        subscriber.onCompleted();
    }

    private Bitmap rotate(Bitmap src, int rotation) {
        if (rotation == 0) {
            return src;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) rotation);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(src, src.getWidth(), src.getHeight(), false);
        return Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
    }
}
