package com.google.android.gms.vision.face.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzh extends zzeb implements zzg {
    zzh(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
    }

    public final zze zza(IObjectWrapper iObjectWrapper, zzc com_google_android_gms_vision_face_internal_client_zzc) throws RemoteException {
        zze com_google_android_gms_vision_face_internal_client_zze;
        Parcel zzax = zzax();
        zzed.zza(zzax, (IInterface) iObjectWrapper);
        zzed.zza(zzax, (Parcelable) com_google_android_gms_vision_face_internal_client_zzc);
        Parcel zza = zza(1, zzax);
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            com_google_android_gms_vision_face_internal_client_zze = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
            com_google_android_gms_vision_face_internal_client_zze = queryLocalInterface instanceof zze ? (zze) queryLocalInterface : new zzf(readStrongBinder);
        }
        zza.recycle();
        return com_google_android_gms_vision_face_internal_client_zze;
    }
}
