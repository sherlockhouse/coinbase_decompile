package com.firebase.jobdispatcher;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

final class GooglePlayJobCallback implements JobCallback {
    private final IBinder remote;

    public GooglePlayJobCallback(IBinder binder) {
        this.remote = binder;
    }

    public void jobFinished(int status) {
        Parcel request = Parcel.obtain();
        Parcel response = Parcel.obtain();
        try {
            request.writeInterfaceToken("com.google.android.gms.gcm.INetworkTaskCallback");
            request.writeInt(status);
            this.remote.transact(2, request, response, 0);
            response.readException();
            request.recycle();
            response.recycle();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            request.recycle();
            response.recycle();
        }
    }
}
