package com.google.android.gms.internal;

public final class zzeyj implements Cloneable {
    private static final zzeyk zzotn = new zzeyk();
    private int mSize;
    private boolean zzoto;
    private int[] zzotp;
    private zzeyk[] zzotq;

    zzeyj() {
        this(10);
    }

    private zzeyj(int i) {
        this.zzoto = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzotp = new int[idealIntArraySize];
        this.zzotq = new zzeyk[idealIntArraySize];
        this.mSize = 0;
    }

    private static int idealIntArraySize(int i) {
        int i2 = i << 2;
        for (int i3 = 4; i3 < 32; i3++) {
            if (i2 <= (1 << i3) - 12) {
                i2 = (1 << i3) - 12;
                break;
            }
        }
        return i2 / 4;
    }

    private final int zzlg(int i) {
        int i2 = 0;
        int i3 = this.mSize - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.zzotp[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return i2 ^ -1;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzeyj com_google_android_gms_internal_zzeyj = new zzeyj(i);
        System.arraycopy(this.zzotp, 0, com_google_android_gms_internal_zzeyj.zzotp, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzotq[i2] != null) {
                com_google_android_gms_internal_zzeyj.zzotq[i2] = (zzeyk) this.zzotq[i2].clone();
            }
        }
        com_google_android_gms_internal_zzeyj.mSize = i;
        return com_google_android_gms_internal_zzeyj;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzeyj)) {
            return false;
        }
        zzeyj com_google_android_gms_internal_zzeyj = (zzeyj) obj;
        if (this.mSize != com_google_android_gms_internal_zzeyj.mSize) {
            return false;
        }
        int i;
        boolean z;
        int[] iArr = this.zzotp;
        int[] iArr2 = com_google_android_gms_internal_zzeyj.zzotp;
        int i2 = this.mSize;
        for (i = 0; i < i2; i++) {
            if (iArr[i] != iArr2[i]) {
                z = false;
                break;
            }
        }
        z = true;
        if (z) {
            zzeyk[] com_google_android_gms_internal_zzeykArr = this.zzotq;
            zzeyk[] com_google_android_gms_internal_zzeykArr2 = com_google_android_gms_internal_zzeyj.zzotq;
            i2 = this.mSize;
            for (i = 0; i < i2; i++) {
                if (!com_google_android_gms_internal_zzeykArr[i].equals(com_google_android_gms_internal_zzeykArr2[i])) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzotp[i2]) * 31) + this.zzotq[i2].hashCode();
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    final int size() {
        return this.mSize;
    }

    final void zza(int i, zzeyk com_google_android_gms_internal_zzeyk) {
        int zzlg = zzlg(i);
        if (zzlg >= 0) {
            this.zzotq[zzlg] = com_google_android_gms_internal_zzeyk;
            return;
        }
        zzlg ^= -1;
        if (zzlg >= this.mSize || this.zzotq[zzlg] != zzotn) {
            if (this.mSize >= this.zzotp.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                Object obj = new int[idealIntArraySize];
                Object obj2 = new zzeyk[idealIntArraySize];
                System.arraycopy(this.zzotp, 0, obj, 0, this.zzotp.length);
                System.arraycopy(this.zzotq, 0, obj2, 0, this.zzotq.length);
                this.zzotp = obj;
                this.zzotq = obj2;
            }
            if (this.mSize - zzlg != 0) {
                System.arraycopy(this.zzotp, zzlg, this.zzotp, zzlg + 1, this.mSize - zzlg);
                System.arraycopy(this.zzotq, zzlg, this.zzotq, zzlg + 1, this.mSize - zzlg);
            }
            this.zzotp[zzlg] = i;
            this.zzotq[zzlg] = com_google_android_gms_internal_zzeyk;
            this.mSize++;
            return;
        }
        this.zzotp[zzlg] = i;
        this.zzotq[zzlg] = com_google_android_gms_internal_zzeyk;
    }

    final zzeyk zzle(int i) {
        int zzlg = zzlg(i);
        return (zzlg < 0 || this.zzotq[zzlg] == zzotn) ? null : this.zzotq[zzlg];
    }

    final zzeyk zzlf(int i) {
        return this.zzotq[i];
    }
}
