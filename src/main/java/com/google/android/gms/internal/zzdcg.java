package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.barcode.Barcode;

public final class zzdcg extends zzeb implements zzdcf {
    zzdcg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
    }

    public final Barcode[] zza(IObjectWrapper iObjectWrapper, zzdck com_google_android_gms_internal_zzdck) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (IInterface) iObjectWrapper);
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzdck);
        Parcel zza = zza(1, zzax);
        Barcode[] barcodeArr = (Barcode[]) zza.createTypedArray(Barcode.CREATOR);
        zza.recycle();
        return barcodeArr;
    }

    public final Barcode[] zzb(IObjectWrapper iObjectWrapper, zzdck com_google_android_gms_internal_zzdck) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (IInterface) iObjectWrapper);
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzdck);
        Parcel zza = zza(2, zzax);
        Barcode[] barcodeArr = (Barcode[]) zza.createTypedArray(Barcode.CREATOR);
        zza.recycle();
        return barcodeArr;
    }

    public final void zzbin() throws RemoteException {
        zzb(3, zzax());
    }
}
