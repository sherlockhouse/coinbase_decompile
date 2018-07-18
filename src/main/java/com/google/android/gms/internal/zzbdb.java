package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzbdb extends zzec implements zzbda {
    public zzbdb() {
        attachInterface(this, "com.google.android.gms.common.internal.service.ICommonCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zzcg(parcel.readInt());
        parcel2.writeNoException();
        return true;
    }
}
