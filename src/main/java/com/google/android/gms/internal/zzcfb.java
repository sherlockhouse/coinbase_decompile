package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.stats.zza;

public final class zzcfb implements ServiceConnection, zzf, zzg {
    final /* synthetic */ zzceo zziwf;
    private volatile boolean zziwm;
    private volatile zzcbv zziwn;

    protected zzcfb(zzceo com_google_android_gms_internal_zzceo) {
        this.zziwf = com_google_android_gms_internal_zzceo;
    }

    public final void onConnected(Bundle bundle) {
        zzbp.zzfy("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                zzcbo com_google_android_gms_internal_zzcbo = (zzcbo) this.zziwn.zzajj();
                this.zziwn = null;
                this.zziwf.zzauk().zzg(new zzcfe(this, com_google_android_gms_internal_zzcbo));
            } catch (DeadObjectException e) {
                this.zziwn = null;
                this.zziwm = false;
            } catch (IllegalStateException e2) {
                this.zziwn = null;
                this.zziwm = false;
            }
        }
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        zzbp.zzfy("MeasurementServiceConnection.onConnectionFailed");
        zzcbw zzayw = this.zziwf.zziki.zzayw();
        if (zzayw != null) {
            zzayw.zzayf().zzj("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zziwm = false;
            this.zziwn = null;
        }
        this.zziwf.zzauk().zzg(new zzcfg(this));
    }

    public final void onConnectionSuspended(int i) {
        zzbp.zzfy("MeasurementServiceConnection.onConnectionSuspended");
        this.zziwf.zzaul().zzayi().log("Service connection suspended");
        this.zziwf.zzauk().zzg(new zzcff(this));
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzbp.zzfy("MeasurementServiceConnection.onServiceConnected");
        synchronized (this) {
            if (iBinder == null) {
                this.zziwm = false;
                this.zziwf.zzaul().zzayd().log("Service connected with null binder");
                return;
            }
            zzcbo com_google_android_gms_internal_zzcbo;
            try {
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                    if (iBinder == null) {
                        com_google_android_gms_internal_zzcbo = null;
                    } else {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                        com_google_android_gms_internal_zzcbo = queryLocalInterface instanceof zzcbo ? (zzcbo) queryLocalInterface : new zzcbq(iBinder);
                    }
                    try {
                        this.zziwf.zzaul().zzayj().log("Bound to IMeasurementService interface");
                    } catch (RemoteException e) {
                        this.zziwf.zzaul().zzayd().log("Service connect failed to get IMeasurementService");
                        if (com_google_android_gms_internal_zzcbo != null) {
                            this.zziwm = false;
                            try {
                                zza.zzaky();
                                this.zziwf.getContext().unbindService(this.zziwf.zzivy);
                            } catch (IllegalArgumentException e2) {
                            }
                        } else {
                            this.zziwf.zzauk().zzg(new zzcfc(this, com_google_android_gms_internal_zzcbo));
                        }
                    }
                    if (com_google_android_gms_internal_zzcbo != null) {
                        this.zziwm = false;
                        zza.zzaky();
                        this.zziwf.getContext().unbindService(this.zziwf.zzivy);
                    } else {
                        this.zziwf.zzauk().zzg(new zzcfc(this, com_google_android_gms_internal_zzcbo));
                    }
                }
                this.zziwf.zzaul().zzayd().zzj("Got binder with a wrong descriptor", interfaceDescriptor);
                com_google_android_gms_internal_zzcbo = null;
                if (com_google_android_gms_internal_zzcbo != null) {
                    this.zziwf.zzauk().zzg(new zzcfc(this, com_google_android_gms_internal_zzcbo));
                } else {
                    this.zziwm = false;
                    zza.zzaky();
                    this.zziwf.getContext().unbindService(this.zziwf.zzivy);
                }
            } catch (RemoteException e3) {
                com_google_android_gms_internal_zzcbo = null;
                this.zziwf.zzaul().zzayd().log("Service connect failed to get IMeasurementService");
                if (com_google_android_gms_internal_zzcbo != null) {
                    this.zziwf.zzauk().zzg(new zzcfc(this, com_google_android_gms_internal_zzcbo));
                } else {
                    this.zziwm = false;
                    zza.zzaky();
                    this.zziwf.getContext().unbindService(this.zziwf.zzivy);
                }
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzbp.zzfy("MeasurementServiceConnection.onServiceDisconnected");
        this.zziwf.zzaul().zzayi().log("Service disconnected");
        this.zziwf.zzauk().zzg(new zzcfd(this, componentName));
    }

    public final void zzazs() {
        this.zziwf.zzuj();
        Context context = this.zziwf.getContext();
        synchronized (this) {
            if (this.zziwm) {
                this.zziwf.zzaul().zzayj().log("Connection attempt already in progress");
            } else if (this.zziwn != null) {
                this.zziwf.zzaul().zzayj().log("Already awaiting connection attempt");
            } else {
                this.zziwn = new zzcbv(context, Looper.getMainLooper(), this, this);
                this.zziwf.zzaul().zzayj().log("Connecting to remote service");
                this.zziwm = true;
                this.zziwn.zzajf();
            }
        }
    }

    public final void zzk(Intent intent) {
        this.zziwf.zzuj();
        Context context = this.zziwf.getContext();
        zza zzaky = zza.zzaky();
        synchronized (this) {
            if (this.zziwm) {
                this.zziwf.zzaul().zzayj().log("Connection attempt already in progress");
                return;
            }
            this.zziwm = true;
            zzaky.zza(context, intent, this.zziwf.zzivy, 129);
        }
    }
}
