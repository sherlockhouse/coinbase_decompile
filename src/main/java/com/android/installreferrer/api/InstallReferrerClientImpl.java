package com.android.installreferrer.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.installreferrer.commons.InstallReferrerCommons;
import com.google.android.finsky.externalreferrer.IGetInstallReferrerService;
import com.google.android.finsky.externalreferrer.IGetInstallReferrerService.Stub;
import java.util.List;

class InstallReferrerClientImpl extends InstallReferrerClient {
    private final Context mApplicationContext;
    private int mClientState = 0;
    private IGetInstallReferrerService mService;
    private ServiceConnection mServiceConnection;

    private final class InstallReferrerServiceConnection implements ServiceConnection {
        private final InstallReferrerStateListener mListener;

        private InstallReferrerServiceConnection(InstallReferrerStateListener listener) {
            if (listener == null) {
                throw new RuntimeException("Please specify a listener to know when setup is done.");
            }
            this.mListener = listener;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            InstallReferrerCommons.logVerbose("InstallReferrerClient", "Install Referrer service connected.");
            InstallReferrerClientImpl.this.mService = Stub.asInterface(iBinder);
            InstallReferrerClientImpl.this.mClientState = 2;
            this.mListener.onInstallReferrerSetupFinished(0);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            InstallReferrerCommons.logWarn("InstallReferrerClient", "Install Referrer service disconnected.");
            InstallReferrerClientImpl.this.mService = null;
            InstallReferrerClientImpl.this.mClientState = 0;
            this.mListener.onInstallReferrerServiceDisconnected();
        }
    }

    public InstallReferrerClientImpl(Context context) {
        this.mApplicationContext = context.getApplicationContext();
    }

    public boolean isReady() {
        return (this.mClientState != 2 || this.mService == null || this.mServiceConnection == null) ? false : true;
    }

    public void startConnection(InstallReferrerStateListener listener) {
        if (isReady()) {
            InstallReferrerCommons.logVerbose("InstallReferrerClient", "Service connection is valid. No need to re-initialize.");
            listener.onInstallReferrerSetupFinished(0);
        } else if (this.mClientState == 1) {
            InstallReferrerCommons.logWarn("InstallReferrerClient", "Client is already in the process of connecting to the service.");
            listener.onInstallReferrerSetupFinished(3);
        } else if (this.mClientState == 3) {
            InstallReferrerCommons.logWarn("InstallReferrerClient", "Client was already closed and can't be reused. Please create another instance.");
            listener.onInstallReferrerSetupFinished(3);
        } else {
            InstallReferrerCommons.logVerbose("InstallReferrerClient", "Starting install referrer service setup.");
            this.mServiceConnection = new InstallReferrerServiceConnection(listener);
            Intent serviceIntent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
            serviceIntent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
            List<ResolveInfo> intentServices = this.mApplicationContext.getPackageManager().queryIntentServices(serviceIntent, 0);
            if (!(intentServices == null || intentServices.isEmpty())) {
                ResolveInfo resolveInfo = (ResolveInfo) intentServices.get(0);
                if (resolveInfo.serviceInfo != null) {
                    String packageName = resolveInfo.serviceInfo.packageName;
                    String className = resolveInfo.serviceInfo.name;
                    if ("com.android.vending".equals(packageName) && className != null && isPlayStoreCompatible()) {
                        if (this.mApplicationContext.bindService(new Intent(serviceIntent), this.mServiceConnection, 1)) {
                            InstallReferrerCommons.logVerbose("InstallReferrerClient", "Service was bonded successfully.");
                            return;
                        }
                        InstallReferrerCommons.logWarn("InstallReferrerClient", "Connection to service is blocked.");
                        this.mClientState = 0;
                        listener.onInstallReferrerSetupFinished(1);
                        return;
                    }
                    InstallReferrerCommons.logWarn("InstallReferrerClient", "Play Store missing or incompatible. Version 8.3.73 or later required.");
                    this.mClientState = 0;
                    listener.onInstallReferrerSetupFinished(2);
                    return;
                }
            }
            this.mClientState = 0;
            InstallReferrerCommons.logVerbose("InstallReferrerClient", "Install Referrer service unavailable on device.");
            listener.onInstallReferrerSetupFinished(2);
        }
    }

    public void endConnection() {
        this.mClientState = 3;
        if (this.mServiceConnection != null) {
            InstallReferrerCommons.logVerbose("InstallReferrerClient", "Unbinding from service.");
            this.mApplicationContext.unbindService(this.mServiceConnection);
            this.mServiceConnection = null;
        }
        this.mService = null;
    }

    public ReferrerDetails getInstallReferrer() throws RemoteException {
        if (isReady()) {
            Bundle paramsBundle = new Bundle();
            paramsBundle.putString("package_name", this.mApplicationContext.getPackageName());
            try {
                return new ReferrerDetails(this.mService.getInstallReferrer(paramsBundle));
            } catch (RemoteException e) {
                InstallReferrerCommons.logWarn("InstallReferrerClient", "RemoteException getting install referrer information");
                this.mClientState = 0;
                throw e;
            }
        }
        throw new IllegalStateException("Service not connected. Please start a connection before using the service.");
    }

    private boolean isPlayStoreCompatible() {
        try {
            if (this.mApplicationContext.getPackageManager().getPackageInfo("com.android.vending", 128).versionCode >= 80837300) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
