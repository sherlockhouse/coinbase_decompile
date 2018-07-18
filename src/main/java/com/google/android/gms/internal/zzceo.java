package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.stats.zza;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.zze;
import com.google.android.gms.measurement.AppMeasurement.zzb;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class zzceo extends zzcdu {
    private final zzcfb zzivy;
    private zzcbo zzivz;
    private Boolean zziwa;
    private final zzcbc zziwb;
    private final zzcfq zziwc;
    private final List<Runnable> zziwd = new ArrayList();
    private final zzcbc zziwe;

    protected zzceo(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
        this.zziwc = new zzcfq(com_google_android_gms_internal_zzccw.zzvx());
        this.zzivy = new zzcfb(this);
        this.zziwb = new zzcep(this, com_google_android_gms_internal_zzccw);
        this.zziwe = new zzcet(this, com_google_android_gms_internal_zzccw);
    }

    private final void onServiceDisconnected(ComponentName componentName) {
        zzuj();
        if (this.zzivz != null) {
            this.zzivz = null;
            zzaul().zzayj().zzj("Disconnected from device MeasurementService", componentName);
            zzuj();
            zzxh();
        }
    }

    private final void zzazr() {
        zzuj();
        zzaul().zzayj().zzj("Processing queued up service tasks", Integer.valueOf(this.zziwd.size()));
        for (Runnable run : this.zziwd) {
            try {
                run.run();
            } catch (Throwable th) {
                zzaul().zzayd().zzj("Task exception while flushing queue", th);
            }
        }
        this.zziwd.clear();
        this.zziwe.cancel();
    }

    private final zzcas zzbr(boolean z) {
        zzcax.zzawk();
        return zzaua().zzjb(z ? zzaul().zzayk() : null);
    }

    private final void zzj(Runnable runnable) throws IllegalStateException {
        zzuj();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.zziwd.size()) >= zzcax.zzawp()) {
            zzaul().zzayd().log("Discarding data. Max runnable queue size reached");
        } else {
            this.zziwd.add(runnable);
            this.zziwe.zzs(60000);
            zzxh();
        }
    }

    private final void zzww() {
        zzuj();
        this.zziwc.start();
        this.zziwb.zzs(zzcax.zzawh());
    }

    private final void zzwx() {
        zzuj();
        if (isConnected()) {
            zzaul().zzayj().log("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    public final void disconnect() {
        zzuj();
        zzwk();
        try {
            zza.zzaky();
            getContext().unbindService(this.zzivy);
        } catch (IllegalStateException e) {
        } catch (IllegalArgumentException e2) {
        }
        this.zzivz = null;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final boolean isConnected() {
        zzuj();
        zzwk();
        return this.zzivz != null;
    }

    protected final void zza(zzcbo com_google_android_gms_internal_zzcbo) {
        zzuj();
        zzbp.zzu(com_google_android_gms_internal_zzcbo);
        this.zzivz = com_google_android_gms_internal_zzcbo;
        zzww();
        zzazr();
    }

    final void zza(zzcbo com_google_android_gms_internal_zzcbo, zzbck com_google_android_gms_internal_zzbck, zzcas com_google_android_gms_internal_zzcas) {
        zzuj();
        zzatv();
        zzwk();
        zzcax.zzawk();
        List arrayList = new ArrayList();
        zzcax.zzawt();
        int i = 100;
        for (int i2 = 0; i2 < 1001 && r5 == 100; i2++) {
            Object zzdw = zzaue().zzdw(100);
            if (zzdw != null) {
                arrayList.addAll(zzdw);
                i = zzdw.size();
            } else {
                i = 0;
            }
            if (com_google_android_gms_internal_zzbck != null && r5 < 100) {
                arrayList.add(com_google_android_gms_internal_zzbck);
            }
            ArrayList arrayList2 = (ArrayList) arrayList;
            int size = arrayList2.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList2.get(i3);
                i3++;
                zzbck com_google_android_gms_internal_zzbck2 = (zzbck) obj;
                if (com_google_android_gms_internal_zzbck2 instanceof zzcbk) {
                    try {
                        com_google_android_gms_internal_zzcbo.zza((zzcbk) com_google_android_gms_internal_zzbck2, com_google_android_gms_internal_zzcas);
                    } catch (RemoteException e) {
                        zzaul().zzayd().zzj("Failed to send event to the service", e);
                    }
                } else if (com_google_android_gms_internal_zzbck2 instanceof zzcft) {
                    try {
                        com_google_android_gms_internal_zzcbo.zza((zzcft) com_google_android_gms_internal_zzbck2, com_google_android_gms_internal_zzcas);
                    } catch (RemoteException e2) {
                        zzaul().zzayd().zzj("Failed to send attribute to the service", e2);
                    }
                } else if (com_google_android_gms_internal_zzbck2 instanceof zzcav) {
                    try {
                        com_google_android_gms_internal_zzcbo.zza((zzcav) com_google_android_gms_internal_zzbck2, com_google_android_gms_internal_zzcas);
                    } catch (RemoteException e22) {
                        zzaul().zzayd().zzj("Failed to send conditional property to the service", e22);
                    }
                } else {
                    zzaul().zzayd().log("Discarding data. Unrecognized parcel type.");
                }
            }
        }
    }

    protected final void zza(zzb com_google_android_gms_measurement_AppMeasurement_zzb) {
        zzuj();
        zzwk();
        zzj(new zzces(this, com_google_android_gms_measurement_AppMeasurement_zzb));
    }

    public final void zza(AtomicReference<String> atomicReference) {
        zzuj();
        zzwk();
        zzj(new zzceq(this, atomicReference, zzbr(false)));
    }

    protected final void zza(AtomicReference<List<zzcav>> atomicReference, String str, String str2, String str3) {
        zzuj();
        zzwk();
        zzj(new zzcex(this, atomicReference, str, str2, str3, zzbr(false)));
    }

    protected final void zza(AtomicReference<List<zzcft>> atomicReference, String str, String str2, String str3, boolean z) {
        zzuj();
        zzwk();
        zzj(new zzcey(this, atomicReference, str, str2, str3, z, zzbr(false)));
    }

    protected final void zza(AtomicReference<List<zzcft>> atomicReference, boolean z) {
        zzuj();
        zzwk();
        zzj(new zzcfa(this, atomicReference, zzbr(false), z));
    }

    public final /* bridge */ /* synthetic */ void zzatu() {
        super.zzatu();
    }

    public final /* bridge */ /* synthetic */ void zzatv() {
        super.zzatv();
    }

    public final /* bridge */ /* synthetic */ void zzatw() {
        super.zzatw();
    }

    public final /* bridge */ /* synthetic */ zzcan zzatx() {
        return super.zzatx();
    }

    public final /* bridge */ /* synthetic */ zzcau zzaty() {
        return super.zzaty();
    }

    public final /* bridge */ /* synthetic */ zzcdw zzatz() {
        return super.zzatz();
    }

    public final /* bridge */ /* synthetic */ zzcbr zzaua() {
        return super.zzaua();
    }

    public final /* bridge */ /* synthetic */ zzcbe zzaub() {
        return super.zzaub();
    }

    public final /* bridge */ /* synthetic */ zzceo zzauc() {
        return super.zzauc();
    }

    public final /* bridge */ /* synthetic */ zzcek zzaud() {
        return super.zzaud();
    }

    public final /* bridge */ /* synthetic */ zzcbs zzaue() {
        return super.zzaue();
    }

    public final /* bridge */ /* synthetic */ zzcay zzauf() {
        return super.zzauf();
    }

    public final /* bridge */ /* synthetic */ zzcbu zzaug() {
        return super.zzaug();
    }

    public final /* bridge */ /* synthetic */ zzcfw zzauh() {
        return super.zzauh();
    }

    public final /* bridge */ /* synthetic */ zzccq zzaui() {
        return super.zzaui();
    }

    public final /* bridge */ /* synthetic */ zzcfl zzauj() {
        return super.zzauj();
    }

    public final /* bridge */ /* synthetic */ zzccr zzauk() {
        return super.zzauk();
    }

    public final /* bridge */ /* synthetic */ zzcbw zzaul() {
        return super.zzaul();
    }

    public final /* bridge */ /* synthetic */ zzcch zzaum() {
        return super.zzaum();
    }

    public final /* bridge */ /* synthetic */ zzcax zzaun() {
        return super.zzaun();
    }

    protected final void zzazp() {
        zzuj();
        zzwk();
        zzj(new zzceu(this, zzbr(true)));
    }

    protected final void zzazq() {
        zzuj();
        zzwk();
        zzj(new zzcer(this, zzbr(true)));
    }

    protected final void zzb(zzcft com_google_android_gms_internal_zzcft) {
        zzuj();
        zzwk();
        zzcax.zzawk();
        zzj(new zzcez(this, zzaue().zza(com_google_android_gms_internal_zzcft), com_google_android_gms_internal_zzcft, zzbr(true)));
    }

    protected final void zzc(zzcbk com_google_android_gms_internal_zzcbk, String str) {
        zzbp.zzu(com_google_android_gms_internal_zzcbk);
        zzuj();
        zzwk();
        zzcax.zzawk();
        zzj(new zzcev(this, true, zzaue().zza(com_google_android_gms_internal_zzcbk), com_google_android_gms_internal_zzcbk, zzbr(true), str));
    }

    protected final void zzf(zzcav com_google_android_gms_internal_zzcav) {
        zzbp.zzu(com_google_android_gms_internal_zzcav);
        zzuj();
        zzwk();
        zzcax.zzawk();
        zzj(new zzcew(this, true, zzaue().zzc(com_google_android_gms_internal_zzcav), new zzcav(com_google_android_gms_internal_zzcav), zzbr(true), com_google_android_gms_internal_zzcav));
    }

    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    protected final void zzuk() {
    }

    public final /* bridge */ /* synthetic */ zzd zzvx() {
        return super.zzvx();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void zzxh() {
        Object obj = 1;
        zzuj();
        zzwk();
        if (!isConnected()) {
            if (this.zziwa == null) {
                this.zziwa = zzaum().zzayo();
                if (this.zziwa == null) {
                    zzaul().zzayj().log("State of service unknown");
                    zzuj();
                    zzwk();
                    zzcax.zzawk();
                    zzaul().zzayj().log("Checking service availability");
                    boolean z;
                    switch (zze.zzaex().isGooglePlayServicesAvailable(getContext())) {
                        case 0:
                            zzaul().zzayj().log("Service available");
                            z = true;
                            break;
                        case 1:
                            zzaul().zzayj().log("Service missing");
                            break;
                        case 2:
                            zzaul().zzayi().log("Service container out of date");
                            z = true;
                            break;
                        case 3:
                            zzaul().zzayf().log("Service disabled");
                            break;
                        case 9:
                            zzaul().zzayf().log("Service invalid");
                            break;
                        case 18:
                            zzaul().zzayf().log("Service updating");
                            z = true;
                            break;
                    }
                }
            }
            if (this.zziwa.booleanValue()) {
                zzaul().zzayj().log("Using measurement service");
                this.zzivy.zzazs();
                return;
            }
            zzcax.zzawk();
            List queryIntentServices = getContext().getPackageManager().queryIntentServices(new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
            if (queryIntentServices == null || queryIntentServices.size() <= 0) {
                obj = null;
            }
            if (obj != null) {
                zzaul().zzayj().log("Using local app measurement service");
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                Context context = getContext();
                zzcax.zzawk();
                intent.setComponent(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
                this.zzivy.zzk(intent);
                return;
            }
            zzaul().zzayd().log("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
        }
    }
}
