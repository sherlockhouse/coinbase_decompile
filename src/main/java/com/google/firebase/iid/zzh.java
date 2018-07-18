package com.google.firebase.iid;

import android.content.BroadcastReceiver.PendingResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.stats.zza;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public final class zzh implements ServiceConnection {
    private final Context zzahz;
    private final Intent zznfd;
    private final ScheduledExecutorService zznfe;
    private final Queue<zzd> zznff;
    private zzf zznfg;
    private boolean zznfh;

    public zzh(Context context, String str) {
        this(context, str, new ScheduledThreadPoolExecutor(0));
    }

    private zzh(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
        this.zznff = new LinkedList();
        this.zznfh = false;
        this.zzahz = context.getApplicationContext();
        this.zznfd = new Intent(str).setPackage(this.zzahz.getPackageName());
        this.zznfe = scheduledExecutorService;
    }

    private final synchronized void zzcfv() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "flush queue called");
        }
        while (!this.zznff.isEmpty()) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "found intent to be delivered");
            }
            if (this.zznfg == null || !this.zznfg.isBinderAlive()) {
                if (Log.isLoggable("EnhancedIntentService", 3)) {
                    Log.d("EnhancedIntentService", "binder is dead. start connection? " + (!this.zznfh));
                }
                if (!this.zznfh) {
                    this.zznfh = true;
                    try {
                        if (!zza.zzaky().zza(this.zzahz, this.zznfd, this, 65)) {
                            Log.e("EnhancedIntentService", "binding to the service failed");
                            while (!this.zznff.isEmpty()) {
                                ((zzd) this.zznff.poll()).finish();
                            }
                        }
                    } catch (Throwable e) {
                        Log.e("EnhancedIntentService", "Exception while binding the service", e);
                    }
                }
            } else {
                if (Log.isLoggable("EnhancedIntentService", 3)) {
                    Log.d("EnhancedIntentService", "binder is alive, sending the intent.");
                }
                this.zznfg.zza((zzd) this.zznff.poll());
            }
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this) {
            this.zznfh = false;
            this.zznfg = (zzf) iBinder;
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                String valueOf = String.valueOf(componentName);
                Log.d("EnhancedIntentService", new StringBuilder(String.valueOf(valueOf).length() + 20).append("onServiceConnected: ").append(valueOf).toString());
            }
            zzcfv();
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            String valueOf = String.valueOf(componentName);
            Log.d("EnhancedIntentService", new StringBuilder(String.valueOf(valueOf).length() + 23).append("onServiceDisconnected: ").append(valueOf).toString());
        }
        zzcfv();
    }

    public final synchronized void zza(Intent intent, PendingResult pendingResult) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
        }
        this.zznff.add(new zzd(intent, pendingResult, this.zznfe));
        zzcfv();
    }
}
