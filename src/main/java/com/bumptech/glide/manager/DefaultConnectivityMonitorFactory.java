package com.bumptech.glide.manager;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import com.bumptech.glide.manager.ConnectivityMonitor.ConnectivityListener;

public class DefaultConnectivityMonitorFactory implements ConnectivityMonitorFactory {
    public ConnectivityMonitor build(Context context, ConnectivityListener listener) {
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") == 0 ? new DefaultConnectivityMonitor(context, listener) : new NullConnectivityMonitor();
    }
}
