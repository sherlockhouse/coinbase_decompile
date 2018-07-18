package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzbd extends zzeb implements zzbc {
    zzbd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
    }

    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, zzbu com_google_android_gms_common_internal_zzbu) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (IInterface) iObjectWrapper);
        zzed.zza(zzax, (Parcelable) com_google_android_gms_common_internal_zzbu);
        zzax = zza(2, zzax);
        IObjectWrapper zzao = zza.zzao(zzax.readStrongBinder());
        zzax.recycle();
        return zzao;
    }
}
