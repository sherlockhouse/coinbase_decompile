package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzbvn extends zzeb implements zzbvl {
    zzbvn(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.flags.IFlagProvider");
    }

    public final boolean getBooleanFlagValue(String str, boolean z, int i) throws RemoteException {
        Parcel zzax = zzax();
        zzax.writeString(str);
        zzed.zza(zzax, z);
        zzax.writeInt(i);
        zzax = zza(2, zzax);
        boolean zza = zzed.zza(zzax);
        zzax.recycle();
        return zza;
    }

    public final int getIntFlagValue(String str, int i, int i2) throws RemoteException {
        Parcel zzax = zzax();
        zzax.writeString(str);
        zzax.writeInt(i);
        zzax.writeInt(i2);
        zzax = zza(3, zzax);
        int readInt = zzax.readInt();
        zzax.recycle();
        return readInt;
    }

    public final long getLongFlagValue(String str, long j, int i) throws RemoteException {
        Parcel zzax = zzax();
        zzax.writeString(str);
        zzax.writeLong(j);
        zzax.writeInt(i);
        zzax = zza(4, zzax);
        long readLong = zzax.readLong();
        zzax.recycle();
        return readLong;
    }

    public final String getStringFlagValue(String str, String str2, int i) throws RemoteException {
        Parcel zzax = zzax();
        zzax.writeString(str);
        zzax.writeString(str2);
        zzax.writeInt(i);
        zzax = zza(5, zzax);
        String readString = zzax.readString();
        zzax.recycle();
        return readString;
    }

    public final void init(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (IInterface) iObjectWrapper);
        zzb(1, zzax);
    }
}
