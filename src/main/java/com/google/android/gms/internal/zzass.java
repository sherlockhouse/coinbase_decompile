package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

public interface zzass extends IInterface {
    void zza(Status status, Credential credential) throws RemoteException;

    void zza(Status status, String str) throws RemoteException;

    void zze(Status status) throws RemoteException;
}
