package com.google.android.gms.vision.face.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzdck;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzf extends zzeb implements zze {
    zzf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
    }

    public final void zzbin() throws RemoteException {
        zzb(3, zzax());
    }

    public final FaceParcel[] zzc(IObjectWrapper iObjectWrapper, zzdck com_google_android_gms_internal_zzdck) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (IInterface) iObjectWrapper);
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzdck);
        Parcel zza = zza(1, zzax);
        FaceParcel[] faceParcelArr = (FaceParcel[]) zza.createTypedArray(FaceParcel.CREATOR);
        zza.recycle();
        return faceParcelArr;
    }
}
