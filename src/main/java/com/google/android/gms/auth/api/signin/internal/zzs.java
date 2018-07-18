package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;

public abstract class zzs extends zzec implements zzr {
    public zzs() {
        attachInterface(this, "com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 101:
                zza((GoogleSignInAccount) zzed.zza(parcel, GoogleSignInAccount.CREATOR), (Status) zzed.zza(parcel, Status.CREATOR));
                break;
            case 102:
                zzi((Status) zzed.zza(parcel, Status.CREATOR));
                break;
            case 103:
                zzj((Status) zzed.zza(parcel, Status.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
