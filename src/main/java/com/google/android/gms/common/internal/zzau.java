package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzeb;

public final class zzau extends zzeb implements zzas {
    zzau(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ICertData");
    }

    public final IObjectWrapper zzaez() throws RemoteException {
        Parcel zza = zza(1, zzax());
        IObjectWrapper zzao = zza.zzao(zza.readStrongBinder());
        zza.recycle();
        return zzao;
    }

    public final int zzafa() throws RemoteException {
        Parcel zza = zza(2, zzax());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }
}
