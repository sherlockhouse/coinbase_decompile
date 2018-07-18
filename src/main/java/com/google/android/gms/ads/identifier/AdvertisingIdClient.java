package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzew;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AdvertisingIdClient {
    private final Context mContext;
    private com.google.android.gms.common.zza zzall;
    private zzev zzalm;
    private boolean zzaln;
    private Object zzalo;
    private zza zzalp;
    private long zzalq;

    public static final class Info {
        private final String zzalw;
        private final boolean zzalx;

        public Info(String str, boolean z) {
            this.zzalw = str;
            this.zzalx = z;
        }

        public final String getId() {
            return this.zzalw;
        }

        public final boolean isLimitAdTrackingEnabled() {
            return this.zzalx;
        }

        public final String toString() {
            String str = this.zzalw;
            return new StringBuilder(String.valueOf(str).length() + 7).append("{").append(str).append("}").append(this.zzalx).toString();
        }
    }

    static class zza extends Thread {
        private WeakReference<AdvertisingIdClient> zzals;
        private long zzalt;
        CountDownLatch zzalu = new CountDownLatch(1);
        boolean zzalv = false;

        public zza(AdvertisingIdClient advertisingIdClient, long j) {
            this.zzals = new WeakReference(advertisingIdClient);
            this.zzalt = j;
            start();
        }

        private final void disconnect() {
            AdvertisingIdClient advertisingIdClient = (AdvertisingIdClient) this.zzals.get();
            if (advertisingIdClient != null) {
                advertisingIdClient.finish();
                this.zzalv = true;
            }
        }

        public final void run() {
            try {
                if (!this.zzalu.await(this.zzalt, TimeUnit.MILLISECONDS)) {
                    disconnect();
                }
            } catch (InterruptedException e) {
                disconnect();
            }
        }
    }

    public AdvertisingIdClient(Context context) {
        this(context, 30000, false);
    }

    public AdvertisingIdClient(Context context, long j, boolean z) {
        this.zzalo = new Object();
        zzbp.zzu(context);
        if (z) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            this.mContext = context;
        } else {
            this.mContext = context;
        }
        this.zzaln = false;
        this.zzalq = j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Info getAdvertisingIdInfo(Context context) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zzd com_google_android_gms_ads_identifier_zzd = new zzd(context);
        boolean z = com_google_android_gms_ads_identifier_zzd.getBoolean("gads:ad_id_app_context:enabled", false);
        float f = com_google_android_gms_ads_identifier_zzd.getFloat("gads:ad_id_app_context:ping_ratio", 0.0f);
        boolean z2 = com_google_android_gms_ads_identifier_zzd.getBoolean("gads:ad_id_use_shared_preference:enabled", false);
        String string = com_google_android_gms_ads_identifier_zzd.getString("gads:ad_id_use_shared_preference:experiment_id", "");
        if (z2) {
            Info info = zzb.zzd(context).getInfo();
        }
        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1, z);
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            advertisingIdClient.start(false);
            info = advertisingIdClient.getInfo();
            advertisingIdClient.zza(info, z, f, SystemClock.elapsedRealtime() - elapsedRealtime, string, null);
            advertisingIdClient.finish();
            return info;
        } catch (Throwable th) {
            advertisingIdClient.finish();
        }
    }

    public static void setShouldSkipGmsCoreVersionCheck(boolean z) {
    }

    private final void start(boolean z) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zzbp.zzgh("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.zzaln) {
                finish();
            }
            this.zzall = zzc(this.mContext);
            this.zzalm = zza(this.mContext, this.zzall);
            this.zzaln = true;
            if (z) {
                zzbh();
            }
        }
    }

    private static zzev zza(Context context, com.google.android.gms.common.zza com_google_android_gms_common_zza) throws IOException {
        try {
            return zzew.zzc(com_google_android_gms_common_zza.zza(10000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            throw new IOException("Interrupted exception");
        } catch (Throwable th) {
            IOException iOException = new IOException(th);
        }
    }

    private final boolean zza(Info info, boolean z, float f, long j, String str, Throwable th) {
        if (Math.random() > ((double) f)) {
            return false;
        }
        Map hashMap = new HashMap();
        hashMap.put("app_context", z ? "1" : "0");
        if (info != null) {
            hashMap.put("limit_ad_tracking", info.isLimitAdTrackingEnabled() ? "1" : "0");
        }
        if (!(info == null || info.getId() == null)) {
            hashMap.put("ad_id_size", Integer.toString(info.getId().length()));
        }
        if (th != null) {
            hashMap.put("error", th.getClass().getName());
        }
        if (!(str == null || str.isEmpty())) {
            hashMap.put("experiment_id", str);
        }
        hashMap.put("tag", "AdvertisingIdClient");
        hashMap.put("time_spent", Long.toString(j));
        new zza(this, hashMap).start();
        return true;
    }

    private final void zzbh() {
        synchronized (this.zzalo) {
            if (this.zzalp != null) {
                this.zzalp.zzalu.countDown();
                try {
                    this.zzalp.join();
                } catch (InterruptedException e) {
                }
            }
            if (this.zzalq > 0) {
                this.zzalp = new zza(this, this.zzalq);
            }
        }
    }

    private static com.google.android.gms.common.zza zzc(Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            switch (zze.zzaex().isGooglePlayServicesAvailable(context)) {
                case 0:
                case 2:
                    Object com_google_android_gms_common_zza = new com.google.android.gms.common.zza();
                    Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                    intent.setPackage("com.google.android.gms");
                    try {
                        if (com.google.android.gms.common.stats.zza.zzaky().zza(context, intent, com_google_android_gms_common_zza, 1)) {
                            return com_google_android_gms_common_zza;
                        }
                        throw new IOException("Connection failure");
                    } catch (Throwable th) {
                        IOException iOException = new IOException(th);
                    }
                default:
                    throw new IOException("Google Play services not available");
            }
        } catch (NameNotFoundException e) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
    }

    protected void finalize() throws Throwable {
        finish();
        super.finalize();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void finish() {
        zzbp.zzgh("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.mContext == null || this.zzall == null) {
            } else {
                try {
                    if (this.zzaln) {
                        com.google.android.gms.common.stats.zza.zzaky();
                        this.mContext.unbindService(this.zzall);
                    }
                } catch (Throwable th) {
                    Log.i("AdvertisingIdClient", "AdvertisingIdClient unbindService failed.", th);
                }
                this.zzaln = false;
                this.zzalm = null;
                this.zzall = null;
            }
        }
    }

    public Info getInfo() throws IOException {
        Info info;
        zzbp.zzgh("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (!this.zzaln) {
                synchronized (this.zzalo) {
                    if (this.zzalp == null || !this.zzalp.zzalv) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
                try {
                    start(false);
                    if (!this.zzaln) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.");
                    }
                } catch (Throwable e) {
                    Log.i("AdvertisingIdClient", "GMS remote exception ", e);
                    throw new IOException("Remote exception");
                } catch (Throwable e2) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.", e2);
                }
            }
            zzbp.zzu(this.zzall);
            zzbp.zzu(this.zzalm);
            info = new Info(this.zzalm.getId(), this.zzalm.zzb(true));
        }
        zzbh();
        return info;
    }

    public void start() throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        start(true);
    }
}
