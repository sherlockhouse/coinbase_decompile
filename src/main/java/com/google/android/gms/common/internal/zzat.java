package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;

public abstract class zzat extends zzec implements zzas {
    public zzat() {
        attachInterface(this, "com.google.android.gms.common.internal.ICertData");
    }

    public static zzas zzak(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
        return queryLocalInterface instanceof zzas ? (zzas) queryLocalInterface : new zzau(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                IInterface zzaez = zzaez();
                parcel2.writeNoException();
                zzed.zza(parcel2, zzaez);
                return true;
            case 2:
                int zzafa = zzafa();
                parcel2.writeNoException();
                parcel2.writeInt(zzafa);
                return true;
            default:
                return false;
        }
    }
}
