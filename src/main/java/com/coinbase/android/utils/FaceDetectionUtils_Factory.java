package com.coinbase.android.utils;

import dagger.internal.Factory;

public final class FaceDetectionUtils_Factory implements Factory<FaceDetectionUtils> {
    private static final FaceDetectionUtils_Factory INSTANCE = new FaceDetectionUtils_Factory();

    public FaceDetectionUtils get() {
        return provideInstance();
    }

    public static FaceDetectionUtils provideInstance() {
        return new FaceDetectionUtils();
    }

    public static FaceDetectionUtils_Factory create() {
        return INSTANCE;
    }

    public static FaceDetectionUtils newFaceDetectionUtils() {
        return new FaceDetectionUtils();
    }
}
