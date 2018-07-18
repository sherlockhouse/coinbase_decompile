package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzca;
import com.google.android.gms.common.util.zzd;
import com.google.firebase.iid.FirebaseInstanceId;
import java.math.BigInteger;
import java.util.Locale;

public final class zzcbr extends zzcdu {
    private String mAppId;
    private String zzcye;
    private String zzdmb;
    private String zzdmc;
    private String zzilf;
    private long zzilj;
    private int zzipi;
    private long zzipj;
    private int zzipk;

    zzcbr(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
    }

    private final String zzauq() {
        zzuj();
        try {
            return FirebaseInstanceId.getInstance().getId();
        } catch (IllegalStateException e) {
            zzaul().zzayf().log("Failed to retrieve Firebase Instance Id");
            return null;
        }
    }

    final String getAppId() {
        zzwk();
        return this.mAppId;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    final String getGmpAppId() {
        zzwk();
        return this.zzcye;
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

    final String zzaya() {
        zzauh().zzazy().nextBytes(new byte[16]);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, r0)});
    }

    final int zzayb() {
        zzwk();
        return this.zzipi;
    }

    final zzcas zzjb(String str) {
        zzuj();
        String appId = getAppId();
        String gmpAppId = getGmpAppId();
        zzwk();
        String str2 = this.zzdmc;
        long zzayb = (long) zzayb();
        zzwk();
        String str3 = this.zzilf;
        long zzauv = zzcax.zzauv();
        zzwk();
        zzuj();
        if (this.zzipj == 0) {
            this.zzipj = this.zziki.zzauh().zzah(getContext(), getContext().getPackageName());
        }
        long j = this.zzipj;
        boolean isEnabled = this.zziki.isEnabled();
        boolean z = !zzaum().zzirh;
        String zzauq = zzauq();
        zzwk();
        long zzaze = this.zziki.zzaze();
        zzwk();
        return new zzcas(appId, gmpAppId, str2, zzayb, str3, zzauv, j, str, isEnabled, z, zzauq, 0, zzaze, this.zzipk);
    }

    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    protected final void zzuk() {
        int i = 1;
        String str = "unknown";
        String str2 = "Unknown";
        int i2 = Integer.MIN_VALUE;
        String str3 = "Unknown";
        String packageName = getContext().getPackageName();
        PackageManager packageManager = getContext().getPackageManager();
        if (packageManager == null) {
            zzaul().zzayd().zzj("PackageManager is null, app identity information might be inaccurate. appId", zzcbw.zzjf(packageName));
        } else {
            try {
                str = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException e) {
                zzaul().zzayd().zzj("Error retrieving app installer package name. appId", zzcbw.zzjf(packageName));
            }
            if (str == null) {
                str = "manual_install";
            } else if ("com.android.vending".equals(str)) {
                str = "";
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
                if (packageInfo != null) {
                    CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                    if (!TextUtils.isEmpty(applicationLabel)) {
                        str3 = applicationLabel.toString();
                    }
                    str2 = packageInfo.versionName;
                    i2 = packageInfo.versionCode;
                }
            } catch (NameNotFoundException e2) {
                zzaul().zzayd().zze("Error retrieving package info. appId, appName", zzcbw.zzjf(packageName), str3);
            }
        }
        this.mAppId = packageName;
        this.zzilf = str;
        this.zzdmc = str2;
        this.zzipi = i2;
        this.zzdmb = str3;
        this.zzipj = 0;
        zzcax.zzawk();
        Status zzcb = zzca.zzcb(getContext());
        int i3 = (zzcb == null || !zzcb.isSuccess()) ? 0 : 1;
        if (i3 == 0) {
            if (zzcb == null) {
                zzaul().zzayd().log("GoogleService failed to initialize (no status)");
            } else {
                zzaul().zzayd().zze("GoogleService failed to initialize, status", Integer.valueOf(zzcb.getStatusCode()), zzcb.getStatusMessage());
            }
        }
        if (i3 != 0) {
            Boolean zzit = zzaun().zzit("firebase_analytics_collection_enabled");
            if (zzaun().zzawl()) {
                zzaul().zzayh().log("Collection disabled with firebase_analytics_collection_deactivated=1");
                i3 = 0;
            } else if (zzit != null && !zzit.booleanValue()) {
                zzaul().zzayh().log("Collection disabled with firebase_analytics_collection_enabled=0");
                i3 = 0;
            } else if (zzit == null && zzcax.zzaif()) {
                zzaul().zzayh().log("Collection disabled with google_app_measurement_enable=0");
                i3 = 0;
            } else {
                zzaul().zzayj().log("Collection enabled");
                i3 = 1;
            }
        } else {
            i3 = 0;
        }
        this.zzcye = "";
        this.zzilj = 0;
        zzcax.zzawk();
        try {
            String zzaie = zzca.zzaie();
            if (TextUtils.isEmpty(zzaie)) {
                zzaie = "";
            }
            this.zzcye = zzaie;
            if (i3 != 0) {
                zzaul().zzayj().zze("App package, google app id", this.mAppId, this.zzcye);
            }
        } catch (IllegalStateException e3) {
            zzaul().zzayd().zze("getGoogleAppId or isMeasurementEnabled failed with exception. appId", zzcbw.zzjf(packageName), e3);
        }
        if (VERSION.SDK_INT >= 16) {
            if (!zzbeb.zzcp(getContext())) {
                i = 0;
            }
            this.zzipk = i;
            return;
        }
        this.zzipk = 0;
    }

    public final /* bridge */ /* synthetic */ zzd zzvx() {
        return super.zzvx();
    }
}
