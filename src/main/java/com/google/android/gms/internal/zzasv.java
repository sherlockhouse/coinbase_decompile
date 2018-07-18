package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

public final class zzasv extends zzeb implements zzasu {
    zzasv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
    }

    public final void zza(zzass com_google_android_gms_internal_zzass, CredentialRequest credentialRequest) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (IInterface) com_google_android_gms_internal_zzass);
        zzed.zza(zzax, (Parcelable) credentialRequest);
        zzb(1, zzax);
    }

    public final void zza(zzass com_google_android_gms_internal_zzass, zzasw com_google_android_gms_internal_zzasw) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (IInterface) com_google_android_gms_internal_zzass);
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzasw);
        zzb(2, zzax);
    }
}
