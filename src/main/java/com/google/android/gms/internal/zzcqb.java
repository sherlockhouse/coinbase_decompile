package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzam;

public final class zzcqb extends zzeb implements zzcqa {
    zzcqb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
    }

    public final void zza(zzam com_google_android_gms_common_internal_zzam, int i, boolean z) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (IInterface) com_google_android_gms_common_internal_zzam);
        zzax.writeInt(i);
        zzed.zza(zzax, z);
        zzb(9, zzax);
    }

    public final void zza(zzcqd com_google_android_gms_internal_zzcqd, zzcpy com_google_android_gms_internal_zzcpy) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcqd);
        zzed.zza(zzax, (IInterface) com_google_android_gms_internal_zzcpy);
        zzb(12, zzax);
    }

    public final void zzec(int i) throws RemoteException {
        Parcel zzax = zzax();
        zzax.writeInt(i);
        zzb(7, zzax);
    }
}
