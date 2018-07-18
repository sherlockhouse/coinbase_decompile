package com.android.installreferrer.api;

import android.content.Context;
import android.os.RemoteException;

public abstract class InstallReferrerClient {

    public static final class Builder {
        private final Context mContext;

        private Builder(Context context) {
            this.mContext = context;
        }

        public InstallReferrerClient build() {
            if (this.mContext != null) {
                return new InstallReferrerClientImpl(this.mContext);
            }
            throw new IllegalArgumentException("Please provide a valid Context.");
        }
    }

    public abstract void endConnection();

    public abstract ReferrerDetails getInstallReferrer() throws RemoteException;

    public abstract void startConnection(InstallReferrerStateListener installReferrerStateListener);

    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }
}
