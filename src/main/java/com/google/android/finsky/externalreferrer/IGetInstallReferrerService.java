package com.google.android.finsky.externalreferrer;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGetInstallReferrerService extends IInterface {

    public static abstract class Stub extends Binder implements IGetInstallReferrerService {

        private static class Proxy implements IGetInstallReferrerService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public Bundle getInstallReferrer(Bundle paramaters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
                    if (paramaters != null) {
                        _data.writeInt(1);
                        paramaters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
        }

        public static IGetInstallReferrerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
            if (iin == null || !(iin instanceof IGetInstallReferrerService)) {
                return new Proxy(obj);
            }
            return (IGetInstallReferrerService) iin;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    Bundle _arg0;
                    data.enforceInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
                    if (data.readInt() != 0) {
                        _arg0 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    Bundle _result = getInstallReferrer(_arg0);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    Bundle getInstallReferrer(Bundle bundle) throws RemoteException;
}
