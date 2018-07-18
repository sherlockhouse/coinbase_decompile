package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.util.LinkedList;
import java.util.Queue;

public final class zzq {
    private static zzq zznga;
    private final SimpleArrayMap<String, String> zzngb = new SimpleArrayMap();
    private Boolean zzngc = null;
    final Queue<Intent> zzngd = new LinkedList();
    private Queue<Intent> zznge = new LinkedList();

    private zzq() {
    }

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        return zza(context, 0, "com.google.firebase.INSTANCE_ID_EVENT", intent, 134217728);
    }

    private static PendingIntent zza(Context context, int i, String str, Intent intent, int i2) {
        Intent intent2 = new Intent(context, FirebaseInstanceIdInternalReceiver.class);
        intent2.setAction(str);
        intent2.putExtra("wrapped_intent", intent);
        return PendingIntent.getBroadcast(context, i, intent2, i2);
    }

    public static PendingIntent zzb(Context context, int i, Intent intent, int i2) {
        return zza(context, i, "com.google.firebase.MESSAGING_EVENT", intent, 1073741824);
    }

    public static synchronized zzq zzcge() {
        zzq com_google_firebase_iid_zzq;
        synchronized (zzq.class) {
            if (zznga == null) {
                zznga = new zzq();
            }
            com_google_firebase_iid_zzq = zznga;
        }
        return com_google_firebase_iid_zzq;
    }

    private final int zzf(Context context, Intent intent) {
        String str;
        ComponentName startWakefulService;
        synchronized (this.zzngb) {
            str = (String) this.zzngb.get(intent.getAction());
        }
        if (str == null) {
            ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
            if (resolveService == null || resolveService.serviceInfo == null) {
                Log.e("FirebaseInstanceId", "Failed to resolve target intent service, skipping classname enforcement");
                if (this.zzngc == null) {
                    this.zzngc = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
                }
                if (this.zzngc.booleanValue()) {
                    startWakefulService = WakefulBroadcastReceiver.startWakefulService(context, intent);
                } else {
                    startWakefulService = context.startService(intent);
                    Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
                }
                if (startWakefulService == null) {
                    return -1;
                }
                Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
                return 404;
            }
            ServiceInfo serviceInfo = resolveService.serviceInfo;
            if (!context.getPackageName().equals(serviceInfo.packageName) || serviceInfo.name == null) {
                String str2 = serviceInfo.packageName;
                str = serviceInfo.name;
                Log.e("FirebaseInstanceId", new StringBuilder((String.valueOf(str2).length() + 94) + String.valueOf(str).length()).append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ").append(str2).append("/").append(str).toString());
                if (this.zzngc == null) {
                    if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
                    }
                    this.zzngc = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
                }
                if (this.zzngc.booleanValue()) {
                    startWakefulService = context.startService(intent);
                    Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
                } else {
                    startWakefulService = WakefulBroadcastReceiver.startWakefulService(context, intent);
                }
                if (startWakefulService == null) {
                    return -1;
                }
                Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
                return 404;
            }
            str = serviceInfo.name;
            if (str.startsWith(".")) {
                String valueOf = String.valueOf(context.getPackageName());
                str = String.valueOf(str);
                str = str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
            }
            synchronized (this.zzngb) {
                this.zzngb.put(intent.getAction(), str);
            }
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            str2 = "FirebaseInstanceId";
            String str3 = "Restricting intent to a specific service: ";
            valueOf = String.valueOf(str);
            Log.d(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        intent.setClassName(context.getPackageName(), str);
        try {
            if (this.zzngc == null) {
                if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
                }
                this.zzngc = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
            }
            if (this.zzngc.booleanValue()) {
                startWakefulService = WakefulBroadcastReceiver.startWakefulService(context, intent);
            } else {
                startWakefulService = context.startService(intent);
                Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
            }
            if (startWakefulService == null) {
                return -1;
            }
            Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
            return 404;
        } catch (Throwable e) {
            Log.e("FirebaseInstanceId", "Error while delivering the message to the serviceIntent", e);
            return 401;
        } catch (IllegalStateException e2) {
            str = String.valueOf(e2);
            Log.e("FirebaseInstanceId", new StringBuilder(String.valueOf(str).length() + 45).append("Failed to start service while in background: ").append(str).toString());
            return 402;
        }
    }

    public final int zza(Context context, String str, Intent intent) {
        Object obj = -1;
        switch (str.hashCode()) {
            case -842411455:
                if (str.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
                    obj = null;
                    break;
                }
                break;
            case 41532704:
                if (str.equals("com.google.firebase.MESSAGING_EVENT")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                this.zzngd.offer(intent);
                break;
            case 1:
                this.zznge.offer(intent);
                break;
            default:
                String str2 = "FirebaseInstanceId";
                String str3 = "Unknown service action: ";
                String valueOf = String.valueOf(str);
                Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                return 500;
        }
        Intent intent2 = new Intent(str);
        intent2.setPackage(context.getPackageName());
        return zzf(context, intent2);
    }

    public final Intent zzcgf() {
        return (Intent) this.zznge.poll();
    }

    public final void zze(Context context, Intent intent) {
        zza(context, "com.google.firebase.INSTANCE_ID_EVENT", intent);
    }
}
