package com.google.android.gms.auth.api.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzu extends zzeb implements zzt {
    zzu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.signin.internal.ISignInService");
    }

    public final void zzc(zzr com_google_android_gms_auth_api_signin_internal_zzr, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (IInterface) com_google_android_gms_auth_api_signin_internal_zzr);
        zzed.zza(zzax, (Parcelable) googleSignInOptions);
        zzb(103, zzax);
    }
}
