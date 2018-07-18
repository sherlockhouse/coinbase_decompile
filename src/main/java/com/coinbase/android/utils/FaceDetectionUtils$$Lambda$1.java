package com.coinbase.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

final /* synthetic */ class FaceDetectionUtils$$Lambda$1 implements OnSubscribe {
    private final FaceDetectionUtils arg$1;
    private final Context arg$2;
    private final Bitmap arg$3;

    private FaceDetectionUtils$$Lambda$1(FaceDetectionUtils faceDetectionUtils, Context context, Bitmap bitmap) {
        this.arg$1 = faceDetectionUtils;
        this.arg$2 = context;
        this.arg$3 = bitmap;
    }

    public static OnSubscribe lambdaFactory$(FaceDetectionUtils faceDetectionUtils, Context context, Bitmap bitmap) {
        return new FaceDetectionUtils$$Lambda$1(faceDetectionUtils, context, bitmap);
    }

    public void call(Object obj) {
        FaceDetectionUtils.lambda$hasFace$0(this.arg$1, this.arg$2, this.arg$3, (Subscriber) obj);
    }
}
