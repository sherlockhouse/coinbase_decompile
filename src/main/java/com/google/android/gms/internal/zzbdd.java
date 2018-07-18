package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzbdd extends zzeb implements zzbdc {
    zzbdd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.ICommonService");
    }

    public final void zza(zzbda com_google_android_gms_internal_zzbda) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (IInterface) com_google_android_gms_internal_zzbda);
        zzc(1, zzax);
    }
}
