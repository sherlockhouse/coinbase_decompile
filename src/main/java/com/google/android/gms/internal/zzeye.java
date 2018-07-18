package com.google.android.gms.internal;

import java.io.IOException;

public final class zzeye {
    private final byte[] buffer;
    private int zzond;
    private int zzone = 64;
    private int zzonf = 67108864;
    private int zzonj;
    private int zzonl;
    private int zzonm = Integer.MAX_VALUE;
    private int zzono;
    private int zzoti;
    private int zzotj;

    private zzeye(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzoti = i;
        this.zzono = i + i2;
        this.zzotj = i;
    }

    private final void zzctk() {
        this.zzono += this.zzonj;
        int i = this.zzono;
        if (i > this.zzonm) {
            this.zzonj = i - this.zzonm;
            this.zzono -= this.zzonj;
            return;
        }
        this.zzonj = 0;
    }

    private final byte zzctl() throws IOException {
        if (this.zzotj == this.zzono) {
            throw zzeym.zzcwc();
        }
        byte[] bArr = this.buffer;
        int i = this.zzotj;
        this.zzotj = i + 1;
        return bArr[i];
    }

    private final void zzjp(int i) throws IOException {
        if (i < 0) {
            throw zzeym.zzcwd();
        } else if (this.zzotj + i > this.zzonm) {
            zzjp(this.zzonm - this.zzotj);
            throw zzeym.zzcwc();
        } else if (i <= this.zzono - this.zzotj) {
            this.zzotj += i;
        } else {
            throw zzeym.zzcwc();
        }
    }

    public static zzeye zzm(byte[] bArr, int i, int i2) {
        return new zzeye(bArr, 0, i2);
    }

    public final int getPosition() {
        return this.zzotj - this.zzoti;
    }

    public final String readString() throws IOException {
        int zzctc = zzctc();
        if (zzctc < 0) {
            throw zzeym.zzcwd();
        } else if (zzctc > this.zzono - this.zzotj) {
            throw zzeym.zzcwc();
        } else {
            String str = new String(this.buffer, this.zzotj, zzctc, zzeyl.UTF_8);
            this.zzotj = zzctc + this.zzotj;
            return str;
        }
    }

    public final void zza(zzeyn com_google_android_gms_internal_zzeyn) throws IOException {
        int zzctc = zzctc();
        if (this.zzond >= this.zzone) {
            throw zzeym.zzcwf();
        }
        zzctc = zzjn(zzctc);
        this.zzond++;
        com_google_android_gms_internal_zzeyn.zza(this);
        zzjk(0);
        this.zzond--;
        zzjo(zzctc);
    }

    public final byte[] zzai(int i, int i2) {
        if (i2 == 0) {
            return zzeyq.zzoue;
        }
        Object obj = new byte[i2];
        System.arraycopy(this.buffer, this.zzoti + i, obj, 0, i2);
        return obj;
    }

    final void zzaj(int i, int i2) {
        if (i > this.zzotj - this.zzoti) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.zzotj - this.zzoti));
        } else if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        } else {
            this.zzotj = this.zzoti + i;
            this.zzonl = i2;
        }
    }

    public final int zzcsn() throws IOException {
        if (this.zzotj == this.zzono) {
            this.zzonl = 0;
            return 0;
        }
        this.zzonl = zzctc();
        if (this.zzonl != 0) {
            return this.zzonl;
        }
        throw new zzeym("Protocol message contained an invalid tag (zero).");
    }

    public final long zzcsp() throws IOException {
        return zzcth();
    }

    public final int zzcsq() throws IOException {
        return zzctc();
    }

    public final boolean zzcst() throws IOException {
        return zzctc() != 0;
    }

    public final int zzctc() throws IOException {
        byte zzctl = zzctl();
        if (zzctl >= (byte) 0) {
            return zzctl;
        }
        int i = zzctl & 127;
        byte zzctl2 = zzctl();
        if (zzctl2 >= (byte) 0) {
            return i | (zzctl2 << 7);
        }
        i |= (zzctl2 & 127) << 7;
        zzctl2 = zzctl();
        if (zzctl2 >= (byte) 0) {
            return i | (zzctl2 << 14);
        }
        i |= (zzctl2 & 127) << 14;
        zzctl2 = zzctl();
        if (zzctl2 >= (byte) 0) {
            return i | (zzctl2 << 21);
        }
        i |= (zzctl2 & 127) << 21;
        zzctl2 = zzctl();
        i |= zzctl2 << 28;
        if (zzctl2 >= (byte) 0) {
            return i;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            if (zzctl() >= (byte) 0) {
                return i;
            }
        }
        throw zzeym.zzcwe();
    }

    public final int zzcte() {
        if (this.zzonm == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzonm - this.zzotj;
    }

    public final long zzcth() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzctl = zzctl();
            j |= ((long) (zzctl & 127)) << i;
            if ((zzctl & 128) == 0) {
                return j;
            }
        }
        throw zzeym.zzcwe();
    }

    public final int zzcti() throws IOException {
        return (((zzctl() & 255) | ((zzctl() & 255) << 8)) | ((zzctl() & 255) << 16)) | ((zzctl() & 255) << 24);
    }

    public final long zzctj() throws IOException {
        byte zzctl = zzctl();
        byte zzctl2 = zzctl();
        return ((((((((((long) zzctl2) & 255) << 8) | (((long) zzctl) & 255)) | ((((long) zzctl()) & 255) << 16)) | ((((long) zzctl()) & 255) << 24)) | ((((long) zzctl()) & 255) << 32)) | ((((long) zzctl()) & 255) << 40)) | ((((long) zzctl()) & 255) << 48)) | ((((long) zzctl()) & 255) << 56);
    }

    public final void zzjk(int i) throws zzeym {
        if (this.zzonl != i) {
            throw new zzeym("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzjl(int i) throws IOException {
        switch (i & 7) {
            case 0:
                zzctc();
                return true;
            case 1:
                zzctj();
                return true;
            case 2:
                zzjp(zzctc());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzcti();
                return true;
            default:
                throw new zzeym("Protocol message tag had invalid wire type.");
        }
        int zzcsn;
        do {
            zzcsn = zzcsn();
            if (zzcsn != 0) {
            }
            zzjk(((i >>> 3) << 3) | 4);
            return true;
        } while (zzjl(zzcsn));
        zzjk(((i >>> 3) << 3) | 4);
        return true;
    }

    public final int zzjn(int i) throws zzeym {
        if (i < 0) {
            throw zzeym.zzcwd();
        }
        int i2 = this.zzotj + i;
        int i3 = this.zzonm;
        if (i2 > i3) {
            throw zzeym.zzcwc();
        }
        this.zzonm = i2;
        zzctk();
        return i3;
    }

    public final void zzjo(int i) {
        this.zzonm = i;
        zzctk();
    }

    public final void zzla(int i) {
        zzaj(i, this.zzonl);
    }
}
