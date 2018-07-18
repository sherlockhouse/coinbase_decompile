package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzdci extends zzeb implements zzdch {
    zzdci(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
    }

    public final zzdcf zza(IObjectWrapper iObjectWrapper, zzdcc com_google_android_gms_internal_zzdcc) throws RemoteException {
        zzdcf com_google_android_gms_internal_zzdcf;
        Parcel zzax = zzax();
        zzed.zza(zzax, (IInterface) iObjectWrapper);
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzdcc);
        Parcel zza = zza(1, zzax);
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            com_google_android_gms_internal_zzdcf = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
            com_google_android_gms_internal_zzdcf = queryLocalInterface instanceof zzdcf ? (zzdcf) queryLocalInterface : new zzdcg(readStrongBinder);
        }
        zza.recycle();
        return com_google_android_gms_internal_zzdcf;
    }
}
