package com.google.android.gms.vision.barcode;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.internal.zzdcc;
import com.google.android.gms.internal.zzdce;
import com.google.android.gms.internal.zzdck;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;

public final class BarcodeDetector extends Detector<Barcode> {
    private final zzdce zzkij;

    public static class Builder {
        private Context mContext;
        private zzdcc zzkik = new zzdcc();

        public Builder(Context context) {
            this.mContext = context;
        }

        public BarcodeDetector build() {
            return new BarcodeDetector(new zzdce(this.mContext, this.zzkik));
        }

        public Builder setBarcodeFormats(int i) {
            this.zzkik.zzkil = i;
            return this;
        }
    }

    private BarcodeDetector() {
        throw new IllegalStateException("Default constructor called");
    }

    private BarcodeDetector(zzdce com_google_android_gms_internal_zzdce) {
        this.zzkij = com_google_android_gms_internal_zzdce;
    }

    public final SparseArray<Barcode> detect(Frame frame) {
        if (frame == null) {
            throw new IllegalArgumentException("No frame supplied.");
        }
        Barcode[] zza;
        zzdck zzc = zzdck.zzc(frame);
        if (frame.getBitmap() != null) {
            zza = this.zzkij.zza(frame.getBitmap(), zzc);
            if (zza == null) {
                throw new IllegalArgumentException("Internal barcode detector error; check logcat output.");
            }
        }
        zza = this.zzkij.zza(frame.getGrayscaleImageData(), zzc);
        SparseArray<Barcode> sparseArray = new SparseArray(zza.length);
        for (Barcode barcode : zza) {
            sparseArray.append(barcode.rawValue.hashCode(), barcode);
        }
        return sparseArray;
    }

    public final boolean isOperational() {
        return this.zzkij.isOperational();
    }

    public final void release() {
        super.release();
        this.zzkij.zzbio();
    }
}
