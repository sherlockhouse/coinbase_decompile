package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzan extends GoogleApiClient {
    private final String zzflg;

    public zzan(String str) {
        this.zzflg = str;
    }

    public ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException(this.zzflg);
    }

    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        throw new UnsupportedOperationException(this.zzflg);
    }

    public void connect() {
        throw new UnsupportedOperationException(this.zzflg);
    }

    public void disconnect() {
        throw new UnsupportedOperationException(this.zzflg);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        throw new UnsupportedOperationException(this.zzflg);
    }

    public boolean isConnected() {
        throw new UnsupportedOperationException(this.zzflg);
    }

    public void reconnect() {
        throw new UnsupportedOperationException(this.zzflg);
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        throw new UnsupportedOperationException(this.zzflg);
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        throw new UnsupportedOperationException(this.zzflg);
    }
}
