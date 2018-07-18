package com.google.firebase.iid;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import java.io.IOException;

public class FirebaseInstanceIdService extends zzb {
    private static Object zznfp = new Object();
    private static boolean zznfq = false;
    private boolean zznfr = false;

    static class zza extends BroadcastReceiver {
        private static BroadcastReceiver receiver;
        private int zznfs;

        private zza(int i) {
            this.zznfs = i;
        }

        static synchronized void zzl(Context context, int i) {
            synchronized (zza.class) {
                if (receiver == null) {
                    receiver = new zza(i);
                    context.getApplicationContext().registerReceiver(receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                }
            }
        }

        public void onReceive(Context context, Intent intent) {
            synchronized (zza.class) {
                if (receiver != this) {
                } else if (FirebaseInstanceIdService.zzen(context)) {
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        Log.d("FirebaseInstanceId", "connectivity changed. starting background sync.");
                    }
                    context.getApplicationContext().unregisterReceiver(this);
                    receiver = null;
                    zzq.zzcge().zze(context, FirebaseInstanceIdService.zzhk(this.zznfs));
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void zza(Context context, FirebaseInstanceId firebaseInstanceId) {
        synchronized (zznfp) {
            if (zznfq) {
            }
        }
    }

    private final void zza(Intent intent, String str) {
        int i = 28800;
        boolean zzen = zzen(this);
        int intExtra = intent == null ? 10 : intent.getIntExtra("next_retry_delay_in_seconds", 0);
        if (intExtra < 10 && !zzen) {
            i = 30;
        } else if (intExtra < 10) {
            i = 10;
        } else if (intExtra <= 28800) {
            i = intExtra;
        }
        Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(str).length() + 47).append("background sync failed: ").append(str).append(", retry in ").append(i).append("s").toString());
        synchronized (zznfp) {
            ((AlarmManager) getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + ((long) (i * 1000)), zzq.zza(this, 0, zzhk(i << 1), 134217728));
            zznfq = true;
        }
        if (!zzen) {
            if (this.zznfr) {
                Log.d("FirebaseInstanceId", "device not connected. Connectivity change received registered");
            }
            zza.zzl(this, i);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(Intent intent, boolean z, boolean z2) {
        synchronized (zznfp) {
            zznfq = false;
        }
        if (zzl.zzdf(this) != null) {
            FirebaseInstanceId instance = FirebaseInstanceId.getInstance();
            zzs zzcfx = instance.zzcfx();
            if (zzcfx == null || zzcfx.zzrg(zzj.zzhtt)) {
                try {
                    String zzcfy = instance.zzcfy();
                    if (zzcfy != null) {
                        if (this.zznfr) {
                            Log.d("FirebaseInstanceId", "get master token succeeded");
                        }
                        zza((Context) this, instance);
                        if (z2 || zzcfx == null || !(zzcfx == null || zzcfy.equals(zzcfx.zzkoo))) {
                            onTokenRefresh();
                            return;
                        }
                        return;
                    }
                    zza(intent, "returned token is null");
                    return;
                } catch (IOException e) {
                    zza(intent, e.getMessage());
                    return;
                } catch (Throwable e2) {
                    Log.e("FirebaseInstanceId", "Unable to get master token", e2);
                    return;
                }
            }
            zzk zzcfz = FirebaseInstanceId.zzcfz();
            for (String zzcgc = zzcfz.zzcgc(); zzcgc != null; zzcgc = zzcfz.zzcgc()) {
                String[] split = zzcgc.split("!");
                if (split.length == 2) {
                    String str = split[0];
                    String str2 = split[1];
                    int i = -1;
                    switch (str.hashCode()) {
                        case 83:
                            try {
                                if (str.equals("S")) {
                                    i = 0;
                                }
                            } catch (IOException e3) {
                                zza(intent, e3.getMessage());
                                return;
                            }
                        case 85:
                            if (str.equals("U")) {
                                i = 1;
                            }
                            switch (i) {
                                case 0:
                                    FirebaseInstanceId.getInstance().zzqx(str2);
                                    if (!this.zznfr) {
                                        Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                                        break;
                                    }
                                    break;
                                case 1:
                                    FirebaseInstanceId.getInstance().zzqy(str2);
                                    if (!this.zznfr) {
                                        Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                                        break;
                                    }
                                    break;
                                default:
                                    continue;
                            }
                    }
                    switch (i) {
                        case 0:
                            FirebaseInstanceId.getInstance().zzqx(str2);
                            if (!this.zznfr) {
                                break;
                            }
                            Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                            break;
                        case 1:
                            FirebaseInstanceId.getInstance().zzqy(str2);
                            if (!this.zznfr) {
                                break;
                            }
                            Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                            break;
                        default:
                            continue;
                    }
                }
                zzcfz.zzra(zzcgc);
            }
            Log.d("FirebaseInstanceId", "topic sync succeeded");
        }
    }

    static void zzem(Context context) {
        if (zzl.zzdf(context) != null) {
            synchronized (zznfp) {
                if (!zznfq) {
                    zzq.zzcge().zze(context, zzhk(0));
                    zznfq = true;
                }
            }
        }
    }

    private static boolean zzen(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private static Intent zzhk(int i) {
        Intent intent = new Intent("ACTION_TOKEN_REFRESH_RETRY");
        intent.putExtra("next_retry_delay_in_seconds", i);
        return intent;
    }

    private static String zzp(Intent intent) {
        String stringExtra = intent.getStringExtra("subtype");
        return stringExtra == null ? "" : stringExtra;
    }

    private final zzj zzqz(String str) {
        if (str == null) {
            return zzj.zza(this, null);
        }
        Bundle bundle = new Bundle();
        bundle.putString("subtype", str);
        return zzj.zza(this, bundle);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleIntent(Intent intent) {
        boolean z;
        String action = intent.getAction();
        if (action == null) {
            action = "";
        }
        switch (action.hashCode()) {
            case -1737547627:
                if (action.equals("ACTION_TOKEN_REFRESH_RETRY")) {
                    z = false;
                    break;
                }
            default:
                z = true;
                break;
        }
        switch (z) {
            case false:
                zza(intent, false, false);
                return;
            default:
                action = zzp(intent);
                zzj zzqz = zzqz(action);
                String stringExtra = intent.getStringExtra("CMD");
                if (this.zznfr) {
                    String valueOf = String.valueOf(intent.getExtras());
                    Log.d("FirebaseInstanceId", new StringBuilder(((String.valueOf(action).length() + 18) + String.valueOf(stringExtra).length()) + String.valueOf(valueOf).length()).append("Service command ").append(action).append(" ").append(stringExtra).append(" ").append(valueOf).toString());
                }
                if (intent.getStringExtra("unregistered") != null) {
                    zzr zzcga = zzj.zzcga();
                    if (action == null) {
                        action = "";
                    }
                    zzcga.zzhu(action);
                    zzj.zzcgb().zzi(intent);
                    return;
                } else if ("gcm.googleapis.com/refresh".equals(intent.getStringExtra("from"))) {
                    zzj.zzcga().zzhu(action);
                    zza(intent, false, true);
                    return;
                } else if ("RST".equals(stringExtra)) {
                    zzqz.zzasr();
                    zza(intent, true, true);
                    return;
                } else if ("RST_FULL".equals(stringExtra)) {
                    if (!zzj.zzcga().isEmpty()) {
                        zzqz.zzasr();
                        zzj.zzcga().zzasv();
                        zza(intent, true, true);
                        return;
                    }
                    return;
                } else if ("SYNC".equals(stringExtra)) {
                    zzj.zzcga().zzhu(action);
                    zza(intent, false, true);
                    return;
                } else if ("PING".equals(stringExtra)) {
                    Bundle extras = intent.getExtras();
                    String zzdf = zzl.zzdf(this);
                    if (zzdf == null) {
                        Log.w("FirebaseInstanceId", "Unable to respond to ping due to missing target package");
                        return;
                    }
                    Intent intent2 = new Intent("com.google.android.gcm.intent.SEND");
                    intent2.setPackage(zzdf);
                    intent2.putExtras(extras);
                    zzl.zzd(this, intent2);
                    intent2.putExtra("google.to", "google.com/iid");
                    intent2.putExtra("google.message_id", zzl.zzasu());
                    sendOrderedBroadcast(intent2, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
                    return;
                } else {
                    return;
                }
        }
    }

    public void onTokenRefresh() {
    }

    protected final Intent zzn(Intent intent) {
        return (Intent) zzq.zzcge().zzngd.poll();
    }

    public final boolean zzo(Intent intent) {
        this.zznfr = Log.isLoggable("FirebaseInstanceId", 3);
        if (intent.getStringExtra("error") == null && intent.getStringExtra("registration_id") == null) {
            return false;
        }
        String zzp = zzp(intent);
        if (this.zznfr) {
            String str = "FirebaseInstanceId";
            String str2 = "Register result in service ";
            String valueOf = String.valueOf(zzp);
            Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        zzqz(zzp);
        zzj.zzcgb().zzi(intent);
        return true;
    }
}
