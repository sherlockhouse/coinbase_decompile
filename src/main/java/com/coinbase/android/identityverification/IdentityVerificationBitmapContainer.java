package com.coinbase.android.identityverification;

import android.graphics.Bitmap;

public abstract class IdentityVerificationBitmapContainer {

    public static abstract class Builder {
        public abstract IdentityVerificationBitmapContainer build();

        public abstract Builder setBackBitmap(Bitmap bitmap);

        public abstract Builder setFaceMatchBitmap(Bitmap bitmap);

        public abstract Builder setFrontBitmap(Bitmap bitmap);
    }

    public abstract Bitmap getBackBitmap();

    public abstract Bitmap getFaceMatchBitmap();

    public abstract Bitmap getFrontBitmap();

    public static Builder builder() {
        return new Builder();
    }
}
