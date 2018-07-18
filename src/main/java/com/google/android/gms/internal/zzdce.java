package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;
import com.google.android.gms.vision.barcode.Barcode;
import java.nio.ByteBuffer;

public final class zzdce extends zzdcj<zzdcf> {
    private final zzdcc zzkik;

    public zzdce(Context context, zzdcc com_google_android_gms_internal_zzdcc) {
        super(context, "BarcodeNativeHandle");
        this.zzkik = com_google_android_gms_internal_zzdcc;
        zzbip();
    }

    protected final /* synthetic */ Object zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, zzc {
        zzdch com_google_android_gms_internal_zzdch;
        IBinder zzgv = dynamiteModule.zzgv("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator");
        if (zzgv == null) {
            com_google_android_gms_internal_zzdch = null;
        } else {
            IInterface queryLocalInterface = zzgv.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
            com_google_android_gms_internal_zzdch = queryLocalInterface instanceof zzdch ? (zzdch) queryLocalInterface : new zzdci(zzgv);
        }
        return com_google_android_gms_internal_zzdch == null ? null : com_google_android_gms_internal_zzdch.zza(zzn.zzw(context), this.zzkik);
    }

    public final Barcode[] zza(Bitmap bitmap, zzdck com_google_android_gms_internal_zzdck) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return ((zzdcf) zzbip()).zzb(zzn.zzw(bitmap), com_google_android_gms_internal_zzdck);
        } catch (Throwable e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }

    public final Barcode[] zza(ByteBuffer byteBuffer, zzdck com_google_android_gms_internal_zzdck) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return ((zzdcf) zzbip()).zza(zzn.zzw(byteBuffer), com_google_android_gms_internal_zzdck);
        } catch (Throwable e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }

    protected final void zzbim() throws RemoteException {
        if (isOperational()) {
            ((zzdcf) zzbip()).zzbin();
        }
    }
}
