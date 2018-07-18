package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzex extends zzeb implements zzev {
    zzex(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    }

    public final String getId() throws RemoteException {
        Parcel zza = zza(1, zzax());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final boolean zzb(boolean z) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, true);
        zzax = zza(2, zzax);
        boolean zza = zzed.zza(zzax);
        zzax.recycle();
        return zza;
    }
}
