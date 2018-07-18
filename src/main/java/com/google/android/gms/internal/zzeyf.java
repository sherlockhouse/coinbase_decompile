package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzeyf {
    private final ByteBuffer zzotk;

    private zzeyf(ByteBuffer byteBuffer) {
        this.zzotk = byteBuffer;
        this.zzotk.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzeyf(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    private static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int length = charSequence.length();
        int i3 = 0;
        int i4 = i + i2;
        while (i3 < length && i3 + i < i4) {
            char charAt = charSequence.charAt(i3);
            if (charAt >= '') {
                break;
            }
            bArr[i + i3] = (byte) charAt;
            i3++;
        }
        if (i3 == length) {
            return i + length;
        }
        int i5 = i + i3;
        while (i3 < length) {
            int i6;
            char charAt2 = charSequence.charAt(i3);
            if (charAt2 < '' && i5 < i4) {
                i6 = i5 + 1;
                bArr[i5] = (byte) charAt2;
            } else if (charAt2 < 'ࠀ' && i5 <= i4 - 2) {
                r6 = i5 + 1;
                bArr[i5] = (byte) ((charAt2 >>> 6) | 960);
                i6 = r6 + 1;
                bArr[r6] = (byte) ((charAt2 & 63) | 128);
            } else if ((charAt2 < '?' || '?' < charAt2) && i5 <= i4 - 3) {
                i6 = i5 + 1;
                bArr[i5] = (byte) ((charAt2 >>> 12) | 480);
                i5 = i6 + 1;
                bArr[i6] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i6 = i5 + 1;
                bArr[i5] = (byte) ((charAt2 & 63) | 128);
            } else if (i5 <= i4 - 4) {
                if (i3 + 1 != charSequence.length()) {
                    i3++;
                    charAt = charSequence.charAt(i3);
                    if (Character.isSurrogatePair(charAt2, charAt)) {
                        int toCodePoint = Character.toCodePoint(charAt2, charAt);
                        i6 = i5 + 1;
                        bArr[i5] = (byte) ((toCodePoint >>> 18) | 240);
                        i5 = i6 + 1;
                        bArr[i6] = (byte) (((toCodePoint >>> 12) & 63) | 128);
                        r6 = i5 + 1;
                        bArr[i5] = (byte) (((toCodePoint >>> 6) & 63) | 128);
                        i6 = r6 + 1;
                        bArr[r6] = (byte) ((toCodePoint & 63) | 128);
                    }
                }
                throw new IllegalArgumentException("Unpaired surrogate at index " + (i3 - 1));
            } else {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i5);
            }
            i3++;
            i5 = i6;
        }
        return i5;
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
            } catch (Throwable e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            zzb(charSequence, byteBuffer);
        }
    }

    public static int zzaa(int i, int i2) {
        return zzkb(i) + zzkc(i2);
    }

    public static int zzb(int i, zzeyn com_google_android_gms_internal_zzeyn) {
        int zzkb = zzkb(i);
        int zzhi = com_google_android_gms_internal_zzeyn.zzhi();
        return zzkb + (zzhi + zzld(zzhi));
    }

    private static void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < '') {
                byteBuffer.put((byte) charAt);
            } else if (charAt < 'ࠀ') {
                byteBuffer.put((byte) ((charAt >>> 6) | 960));
                byteBuffer.put((byte) ((charAt & 63) | 128));
            } else if (charAt < '?' || '?' < charAt) {
                byteBuffer.put((byte) ((charAt >>> 12) | 480));
                byteBuffer.put((byte) (((charAt >>> 6) & 63) | 128));
                byteBuffer.put((byte) ((charAt & 63) | 128));
            } else {
                if (i + 1 != charSequence.length()) {
                    i++;
                    char charAt2 = charSequence.charAt(i);
                    if (Character.isSurrogatePair(charAt, charAt2)) {
                        int toCodePoint = Character.toCodePoint(charAt, charAt2);
                        byteBuffer.put((byte) ((toCodePoint >>> 18) | 240));
                        byteBuffer.put((byte) (((toCodePoint >>> 12) & 63) | 128));
                        byteBuffer.put((byte) (((toCodePoint >>> 6) & 63) | 128));
                        byteBuffer.put((byte) ((toCodePoint & 63) | 128));
                    }
                }
                throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
            }
            i++;
        }
    }

    public static zzeyf zzbf(byte[] bArr) {
        return zzn(bArr, 0, bArr.length);
    }

    public static int zzc(int i, long j) {
        return zzkb(i) + zzdg(j);
    }

    private static int zzc(CharSequence charSequence) {
        int i = 0;
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < '') {
            i2++;
        }
        int i3 = length;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt < 'ࠀ') {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 'ࠀ') {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if ('?' <= charAt2 && charAt2 <= '?') {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                throw new IllegalArgumentException("Unpaired surrogate at index " + i2);
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i2 = i3 + i;
                if (i2 < length) {
                    return i2;
                }
                throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + 4294967296L));
            }
        }
        i2 = i3;
        if (i2 < length) {
            return i2;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + 4294967296L));
    }

    private final void zzdf(long j) throws IOException {
        while ((-128 & j) != 0) {
            zzlb((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzlb((int) j);
    }

    public static int zzdg(long j) {
        return (-128 & j) == 0 ? 1 : (-16384 & j) == 0 ? 2 : (-2097152 & j) == 0 ? 3 : (-268435456 & j) == 0 ? 4 : (-34359738368L & j) == 0 ? 5 : (-4398046511104L & j) == 0 ? 6 : (-562949953421312L & j) == 0 ? 7 : (-72057594037927936L & j) == 0 ? 8 : (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    private final void zzdh(long j) throws IOException {
        if (this.zzotk.remaining() < 8) {
            throw new zzeyg(this.zzotk.position(), this.zzotk.limit());
        }
        this.zzotk.putLong(j);
    }

    public static int zzkb(int i) {
        return zzld(i << 3);
    }

    public static int zzkc(int i) {
        return i >= 0 ? zzld(i) : 10;
    }

    private final void zzlb(int i) throws IOException {
        byte b = (byte) i;
        if (this.zzotk.hasRemaining()) {
            this.zzotk.put(b);
            return;
        }
        throw new zzeyg(this.zzotk.position(), this.zzotk.limit());
    }

    public static int zzld(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzn(int i, String str) {
        return zzkb(i) + zztk(str);
    }

    public static zzeyf zzn(byte[] bArr, int i, int i2) {
        return new zzeyf(bArr, 0, i2);
    }

    public static int zztk(String str) {
        int zzc = zzc(str);
        return zzc + zzld(zzc);
    }

    public final void zza(int i, double d) throws IOException {
        zzw(i, 1);
        zzdh(Double.doubleToLongBits(d));
    }

    public final void zza(int i, long j) throws IOException {
        zzw(i, 0);
        zzdf(j);
    }

    public final void zza(int i, zzeyn com_google_android_gms_internal_zzeyn) throws IOException {
        zzw(i, 2);
        zzb(com_google_android_gms_internal_zzeyn);
    }

    public final void zzb(zzeyn com_google_android_gms_internal_zzeyn) throws IOException {
        zzlc(com_google_android_gms_internal_zzeyn.zzcwg());
        com_google_android_gms_internal_zzeyn.zza(this);
    }

    public final void zzbh(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.zzotk.remaining() >= length) {
            this.zzotk.put(bArr, 0, length);
            return;
        }
        throw new zzeyg(this.zzotk.position(), this.zzotk.limit());
    }

    public final void zzc(int i, float f) throws IOException {
        zzw(i, 5);
        int floatToIntBits = Float.floatToIntBits(f);
        if (this.zzotk.remaining() < 4) {
            throw new zzeyg(this.zzotk.position(), this.zzotk.limit());
        }
        this.zzotk.putInt(floatToIntBits);
    }

    public final void zzctn() {
        if (this.zzotk.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.zzotk.remaining())}));
        }
    }

    public final void zzf(int i, long j) throws IOException {
        zzw(i, 0);
        zzdf(j);
    }

    public final void zzl(int i, boolean z) throws IOException {
        int i2 = 0;
        zzw(i, 0);
        if (z) {
            i2 = 1;
        }
        byte b = (byte) i2;
        if (this.zzotk.hasRemaining()) {
            this.zzotk.put(b);
            return;
        }
        throw new zzeyg(this.zzotk.position(), this.zzotk.limit());
    }

    public final void zzlc(int i) throws IOException {
        while ((i & -128) != 0) {
            zzlb((i & 127) | 128);
            i >>>= 7;
        }
        zzlb(i);
    }

    public final void zzm(int i, String str) throws IOException {
        zzw(i, 2);
        try {
            int zzld = zzld(str.length());
            if (zzld == zzld(str.length() * 3)) {
                int position = this.zzotk.position();
                if (this.zzotk.remaining() < zzld) {
                    throw new zzeyg(zzld + position, this.zzotk.limit());
                }
                this.zzotk.position(position + zzld);
                zza((CharSequence) str, this.zzotk);
                int position2 = this.zzotk.position();
                this.zzotk.position(position);
                zzlc((position2 - position) - zzld);
                this.zzotk.position(position2);
                return;
            }
            zzlc(zzc(str));
            zza((CharSequence) str, this.zzotk);
        } catch (Throwable e) {
            zzeyg com_google_android_gms_internal_zzeyg = new zzeyg(this.zzotk.position(), this.zzotk.limit());
            com_google_android_gms_internal_zzeyg.initCause(e);
            throw com_google_android_gms_internal_zzeyg;
        }
    }

    public final void zzw(int i, int i2) throws IOException {
        zzlc((i << 3) | i2);
    }

    public final void zzx(int i, int i2) throws IOException {
        zzw(i, 0);
        if (i2 >= 0) {
            zzlc(i2);
        } else {
            zzdf((long) i2);
        }
    }
}
