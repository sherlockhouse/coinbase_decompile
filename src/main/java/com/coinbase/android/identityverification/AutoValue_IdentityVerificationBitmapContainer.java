package com.coinbase.android.identityverification;

import android.graphics.Bitmap;

final class AutoValue_IdentityVerificationBitmapContainer extends IdentityVerificationBitmapContainer {
    private final Bitmap backBitmap;
    private final Bitmap faceMatchBitmap;
    private final Bitmap frontBitmap;

    static final class Builder extends com.coinbase.android.identityverification.IdentityVerificationBitmapContainer.Builder {
        private Bitmap backBitmap;
        private Bitmap faceMatchBitmap;
        private Bitmap frontBitmap;

        Builder() {
        }

        Builder(IdentityVerificationBitmapContainer source) {
            this.frontBitmap = source.getFrontBitmap();
            this.backBitmap = source.getBackBitmap();
            this.faceMatchBitmap = source.getFaceMatchBitmap();
        }

        public com.coinbase.android.identityverification.IdentityVerificationBitmapContainer.Builder setFrontBitmap(Bitmap frontBitmap) {
            this.frontBitmap = frontBitmap;
            return this;
        }

        public com.coinbase.android.identityverification.IdentityVerificationBitmapContainer.Builder setBackBitmap(Bitmap backBitmap) {
            this.backBitmap = backBitmap;
            return this;
        }

        public com.coinbase.android.identityverification.IdentityVerificationBitmapContainer.Builder setFaceMatchBitmap(Bitmap faceMatchBitmap) {
            this.faceMatchBitmap = faceMatchBitmap;
            return this;
        }

        public IdentityVerificationBitmapContainer build() {
            return new AutoValue_IdentityVerificationBitmapContainer(this.frontBitmap, this.backBitmap, this.faceMatchBitmap);
        }
    }

    private AutoValue_IdentityVerificationBitmapContainer(Bitmap frontBitmap, Bitmap backBitmap, Bitmap faceMatchBitmap) {
        this.frontBitmap = frontBitmap;
        this.backBitmap = backBitmap;
        this.faceMatchBitmap = faceMatchBitmap;
    }

    public Bitmap getFrontBitmap() {
        return this.frontBitmap;
    }

    public Bitmap getBackBitmap() {
        return this.backBitmap;
    }

    public Bitmap getFaceMatchBitmap() {
        return this.faceMatchBitmap;
    }

    public String toString() {
        return "IdentityVerificationBitmapContainer{frontBitmap=" + this.frontBitmap + ", backBitmap=" + this.backBitmap + ", faceMatchBitmap=" + this.faceMatchBitmap + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IdentityVerificationBitmapContainer)) {
            return false;
        }
        IdentityVerificationBitmapContainer that = (IdentityVerificationBitmapContainer) o;
        if (this.frontBitmap != null) {
            if (this.frontBitmap.equals(that.getFrontBitmap())) {
            }
            return false;
        }
        if (this.backBitmap != null) {
            if (this.backBitmap.equals(that.getBackBitmap())) {
            }
            return false;
        }
        if (this.faceMatchBitmap == null) {
            if (that.getFaceMatchBitmap() == null) {
                return true;
            }
        } else if (this.faceMatchBitmap.equals(that.getFaceMatchBitmap())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.frontBitmap == null ? 0 : this.frontBitmap.hashCode())) * 1000003) ^ (this.backBitmap == null ? 0 : this.backBitmap.hashCode())) * 1000003;
        if (this.faceMatchBitmap != null) {
            i = this.faceMatchBitmap.hashCode();
        }
        return h ^ i;
    }
}
