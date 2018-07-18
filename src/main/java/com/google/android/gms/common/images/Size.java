package com.google.android.gms.common.images;

public final class Size {
    private final int zzakq;
    private final int zzakr;

    public Size(int i, int i2) {
        this.zzakq = i;
        this.zzakr = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        return this.zzakq == size.zzakq && this.zzakr == size.zzakr;
    }

    public final int getHeight() {
        return this.zzakr;
    }

    public final int getWidth() {
        return this.zzakq;
    }

    public final int hashCode() {
        return this.zzakr ^ ((this.zzakq << 16) | (this.zzakq >>> 16));
    }

    public final String toString() {
        int i = this.zzakq;
        return i + "x" + this.zzakr;
    }
}
