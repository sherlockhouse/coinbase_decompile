package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.zzm;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzbb extends zzeb implements zzaz {
    zzbb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    public final boolean zza(zzm com_google_android_gms_common_zzm, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (Parcelable) com_google_android_gms_common_zzm);
        zzed.zza(zzax, (IInterface) iObjectWrapper);
        zzax = zza(5, zzax);
        boolean zza = zzed.zza(zzax);
        zzax.recycle();
        return zza;
    }
}
