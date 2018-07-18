package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzi extends zzg {
    private static final WeakReference<byte[]> zzffr = new WeakReference(null);
    private WeakReference<byte[]> zzffq = zzffr;

    zzi(byte[] bArr) {
        super(bArr);
    }

    final byte[] getBytes() {
        byte[] bArr;
        synchronized (this) {
            bArr = (byte[]) this.zzffq.get();
            if (bArr == null) {
                bArr = zzafb();
                this.zzffq = new WeakReference(bArr);
            }
        }
        return bArr;
    }

    protected abstract byte[] zzafb();
}
