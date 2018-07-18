package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;

public abstract class zzaw extends zzec implements zzav {
    public zzaw() {
        attachInterface(this, "com.google.android.gms.common.internal.IGmsCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zza(parcel.readInt(), parcel.readStrongBinder(), (Bundle) zzed.zza(parcel, Bundle.CREATOR));
                break;
            case 2:
                zza(parcel.readInt(), (Bundle) zzed.zza(parcel, Bundle.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
