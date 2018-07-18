package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

final class zzay implements zzax {
    private final IBinder zzajx;

    zzay(IBinder iBinder) {
        this.zzajx = iBinder;
    }

    public final IBinder asBinder() {
        return this.zzajx;
    }

    public final void zza(zzav com_google_android_gms_common_internal_zzav, zzy com_google_android_gms_common_internal_zzy) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
            obtain.writeStrongBinder(com_google_android_gms_common_internal_zzav.asBinder());
            obtain.writeInt(1);
            com_google_android_gms_common_internal_zzy.writeToParcel(obtain, 0);
            this.zzajx.transact(46, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
