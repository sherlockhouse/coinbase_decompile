package com.google.android.gms.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.utils.CryptoUri;
import com.coinbase.api.internal.ApiConstants;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class zzccw {
    private static volatile zzccw zzisr;
    private final Context mContext;
    private final zzd zzasc = zzh.zzalc();
    private final boolean zzdoe;
    private final zzcax zziss = new zzcax(this);
    private final zzcch zzist;
    private final zzcbw zzisu;
    private final zzccr zzisv;
    private final zzcfl zzisw;
    private final zzccq zzisx;
    private final AppMeasurement zzisy;
    private final FirebaseAnalytics zzisz;
    private final zzcfw zzita;
    private final zzcbu zzitb;
    private final zzcay zzitc;
    private final zzcbs zzitd;
    private final zzcca zzite;
    private final zzcek zzitf;
    private final zzceo zzitg;
    private final zzcbe zzith;
    private final zzcdw zziti;
    private final zzcbr zzitj;
    private final zzccf zzitk;
    private final zzcfr zzitl;
    private final zzcau zzitm;
    private final zzcan zzitn;
    private boolean zzito;
    private Boolean zzitp;
    private long zzitq;
    private FileLock zzitr;
    private FileChannel zzits;
    private List<Long> zzitt;
    private List<Runnable> zzitu;
    private int zzitv;
    private int zzitw;
    private long zzitx = -1;
    private long zzity;
    private boolean zzitz;
    private boolean zziua;
    private boolean zziub;
    private final long zziuc = this.zzasc.currentTimeMillis();

    class zza implements zzcba {
        List<zzcgh> zzaoc;
        private /* synthetic */ zzccw zziud;
        zzcgk zziue;
        List<Long> zziuf;
        private long zziug;

        private zza(zzccw com_google_android_gms_internal_zzccw) {
            this.zziud = com_google_android_gms_internal_zzccw;
        }

        private static long zza(zzcgh com_google_android_gms_internal_zzcgh) {
            return ((com_google_android_gms_internal_zzcgh.zziyy.longValue() / 1000) / 60) / 60;
        }

        public final boolean zza(long j, zzcgh com_google_android_gms_internal_zzcgh) {
            zzbp.zzu(com_google_android_gms_internal_zzcgh);
            if (this.zzaoc == null) {
                this.zzaoc = new ArrayList();
            }
            if (this.zziuf == null) {
                this.zziuf = new ArrayList();
            }
            if (this.zzaoc.size() > 0 && zza((zzcgh) this.zzaoc.get(0)) != zza(com_google_android_gms_internal_zzcgh)) {
                return false;
            }
            long zzhi = this.zziug + ((long) com_google_android_gms_internal_zzcgh.zzhi());
            if (zzhi >= ((long) zzcax.zzawr())) {
                return false;
            }
            this.zziug = zzhi;
            this.zzaoc.add(com_google_android_gms_internal_zzcgh);
            this.zziuf.add(Long.valueOf(j));
            return this.zzaoc.size() < zzcax.zzaws();
        }

        public final void zzb(zzcgk com_google_android_gms_internal_zzcgk) {
            zzbp.zzu(com_google_android_gms_internal_zzcgk);
            this.zziue = com_google_android_gms_internal_zzcgk;
        }
    }

    private zzccw(zzcdv com_google_android_gms_internal_zzcdv) {
        zzcby zzayh;
        zzbp.zzu(com_google_android_gms_internal_zzcdv);
        this.mContext = com_google_android_gms_internal_zzcdv.mContext;
        zzcdu com_google_android_gms_internal_zzcch = new zzcch(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzist = com_google_android_gms_internal_zzcch;
        com_google_android_gms_internal_zzcch = new zzcbw(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzisu = com_google_android_gms_internal_zzcch;
        zzaul().zzayh().zzj("App measurement is starting up, version", Long.valueOf(zzcax.zzauv()));
        zzcax.zzawk();
        zzaul().zzayh().log("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        com_google_android_gms_internal_zzcch = new zzcfw(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzita = com_google_android_gms_internal_zzcch;
        com_google_android_gms_internal_zzcch = new zzcbu(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzitb = com_google_android_gms_internal_zzcch;
        com_google_android_gms_internal_zzcch = new zzcbe(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzith = com_google_android_gms_internal_zzcch;
        com_google_android_gms_internal_zzcch = new zzcbr(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzitj = com_google_android_gms_internal_zzcch;
        zzcax.zzawk();
        String appId = com_google_android_gms_internal_zzcch.getAppId();
        if (zzauh().zzke(appId)) {
            zzayh = zzaul().zzayh();
            appId = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
        } else {
            zzayh = zzaul().zzayh();
            String str = "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ";
            appId = String.valueOf(appId);
            appId = appId.length() != 0 ? str.concat(appId) : new String(str);
        }
        zzayh.log(appId);
        zzaul().zzayi().log("Debug-level message logging enabled");
        com_google_android_gms_internal_zzcch = new zzcay(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzitc = com_google_android_gms_internal_zzcch;
        com_google_android_gms_internal_zzcch = new zzcbs(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzitd = com_google_android_gms_internal_zzcch;
        com_google_android_gms_internal_zzcch = new zzcau(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzitm = com_google_android_gms_internal_zzcch;
        this.zzitn = new zzcan(this);
        com_google_android_gms_internal_zzcch = new zzcca(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzite = com_google_android_gms_internal_zzcch;
        com_google_android_gms_internal_zzcch = new zzcek(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzitf = com_google_android_gms_internal_zzcch;
        com_google_android_gms_internal_zzcch = new zzceo(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzitg = com_google_android_gms_internal_zzcch;
        com_google_android_gms_internal_zzcch = new zzcdw(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zziti = com_google_android_gms_internal_zzcch;
        com_google_android_gms_internal_zzcch = new zzcfr(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzitl = com_google_android_gms_internal_zzcch;
        this.zzitk = new zzccf(this);
        this.zzisy = new AppMeasurement(this);
        this.zzisz = new FirebaseAnalytics(this);
        com_google_android_gms_internal_zzcch = new zzcfl(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzisw = com_google_android_gms_internal_zzcch;
        com_google_android_gms_internal_zzcch = new zzccq(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzisx = com_google_android_gms_internal_zzcch;
        com_google_android_gms_internal_zzcch = new zzccr(this);
        com_google_android_gms_internal_zzcch.initialize();
        this.zzisv = com_google_android_gms_internal_zzcch;
        if (this.zzitv != this.zzitw) {
            zzaul().zzayd().zze("Not all components initialized", Integer.valueOf(this.zzitv), Integer.valueOf(this.zzitw));
        }
        this.zzdoe = true;
        zzcax.zzawk();
        if (this.mContext.getApplicationContext() instanceof Application) {
            zzcdt zzatz = zzatz();
            if (zzatz.getContext().getApplicationContext() instanceof Application) {
                Application application = (Application) zzatz.getContext().getApplicationContext();
                if (zzatz.zziut == null) {
                    zzatz.zziut = new zzcej(zzatz);
                }
                application.unregisterActivityLifecycleCallbacks(zzatz.zziut);
                application.registerActivityLifecycleCallbacks(zzatz.zziut);
                zzatz.zzaul().zzayj().log("Registered activity lifecycle callback");
            }
        } else {
            zzaul().zzayf().log("Application context is not an Application");
        }
        this.zzisv.zzg(new zzccx(this));
    }

    private final int zza(FileChannel fileChannel) {
        int i = 0;
        zzauk().zzuj();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzaul().zzayd().log("Bad chanel to read from");
        } else {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            try {
                fileChannel.position(0);
                int read = fileChannel.read(allocate);
                if (read == 4) {
                    allocate.flip();
                    i = allocate.getInt();
                } else if (read != -1) {
                    zzaul().zzayf().zzj("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
            } catch (IOException e) {
                zzaul().zzayd().zzj("Failed to read from channel", e);
            }
        }
        return i;
    }

    private final void zza(zzcbf com_google_android_gms_internal_zzcbf, zzcas com_google_android_gms_internal_zzcas) {
        zzauk().zzuj();
        zzwk();
        zzbp.zzu(com_google_android_gms_internal_zzcbf);
        zzbp.zzu(com_google_android_gms_internal_zzcas);
        zzbp.zzgg(com_google_android_gms_internal_zzcbf.mAppId);
        zzbp.zzbh(com_google_android_gms_internal_zzcbf.mAppId.equals(com_google_android_gms_internal_zzcas.packageName));
        zzcgk com_google_android_gms_internal_zzcgk = new zzcgk();
        com_google_android_gms_internal_zzcgk.zzize = Integer.valueOf(1);
        com_google_android_gms_internal_zzcgk.zzizm = "android";
        com_google_android_gms_internal_zzcgk.zzci = com_google_android_gms_internal_zzcas.packageName;
        com_google_android_gms_internal_zzcgk.zzilv = com_google_android_gms_internal_zzcas.zzilv;
        com_google_android_gms_internal_zzcgk.zzhtt = com_google_android_gms_internal_zzcas.zzhtt;
        com_google_android_gms_internal_zzcgk.zzizz = com_google_android_gms_internal_zzcas.zzimb == -2147483648L ? null : Integer.valueOf((int) com_google_android_gms_internal_zzcas.zzimb);
        com_google_android_gms_internal_zzcgk.zzizq = Long.valueOf(com_google_android_gms_internal_zzcas.zzilw);
        com_google_android_gms_internal_zzcgk.zzilu = com_google_android_gms_internal_zzcas.zzilu;
        com_google_android_gms_internal_zzcgk.zzizv = com_google_android_gms_internal_zzcas.zzilx == 0 ? null : Long.valueOf(com_google_android_gms_internal_zzcas.zzilx);
        Pair zzjh = zzaum().zzjh(com_google_android_gms_internal_zzcas.packageName);
        if (zzjh != null && !TextUtils.isEmpty((CharSequence) zzjh.first)) {
            com_google_android_gms_internal_zzcgk.zzizs = (String) zzjh.first;
            com_google_android_gms_internal_zzcgk.zzizt = (Boolean) zzjh.second;
        } else if (!zzaub().zzdm(this.mContext)) {
            String string = Secure.getString(this.mContext.getContentResolver(), "android_id");
            if (string == null) {
                zzaul().zzayf().zzj("null secure ID. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcgk.zzci));
                string = "null";
            } else if (string.isEmpty()) {
                zzaul().zzayf().zzj("empty secure ID. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcgk.zzci));
            }
            com_google_android_gms_internal_zzcgk.zzjac = string;
        }
        zzaub().zzwk();
        com_google_android_gms_internal_zzcgk.zzizn = Build.MODEL;
        zzaub().zzwk();
        com_google_android_gms_internal_zzcgk.zzcw = VERSION.RELEASE;
        com_google_android_gms_internal_zzcgk.zzizp = Integer.valueOf((int) zzaub().zzaxw());
        com_google_android_gms_internal_zzcgk.zzizo = zzaub().zzaxx();
        com_google_android_gms_internal_zzcgk.zzizr = null;
        com_google_android_gms_internal_zzcgk.zzizh = null;
        com_google_android_gms_internal_zzcgk.zzizi = null;
        com_google_android_gms_internal_zzcgk.zzizj = null;
        com_google_android_gms_internal_zzcgk.zzjae = Long.valueOf(com_google_android_gms_internal_zzcas.zzimd);
        if (isEnabled() && zzcax.zzaxh()) {
            zzaua();
            com_google_android_gms_internal_zzcgk.zzjaf = null;
        }
        zzcar zziw = zzauf().zziw(com_google_android_gms_internal_zzcas.packageName);
        if (zziw == null) {
            zziw = new zzcar(this, com_google_android_gms_internal_zzcas.packageName);
            zziw.zzim(zzaua().zzaya());
            zziw.zzip(com_google_android_gms_internal_zzcas.zzimc);
            zziw.zzin(com_google_android_gms_internal_zzcas.zzilu);
            zziw.zzio(zzaum().zzji(com_google_android_gms_internal_zzcas.packageName));
            zziw.zzaq(0);
            zziw.zzal(0);
            zziw.zzam(0);
            zziw.setAppVersion(com_google_android_gms_internal_zzcas.zzhtt);
            zziw.zzan(com_google_android_gms_internal_zzcas.zzimb);
            zziw.zziq(com_google_android_gms_internal_zzcas.zzilv);
            zziw.zzao(com_google_android_gms_internal_zzcas.zzilw);
            zziw.zzap(com_google_android_gms_internal_zzcas.zzilx);
            zziw.setMeasurementEnabled(com_google_android_gms_internal_zzcas.zzilz);
            zziw.zzaz(com_google_android_gms_internal_zzcas.zzimd);
            zzauf().zza(zziw);
        }
        com_google_android_gms_internal_zzcgk.zzizu = zziw.getAppInstanceId();
        com_google_android_gms_internal_zzcgk.zzimc = zziw.zzauq();
        List zziv = zzauf().zziv(com_google_android_gms_internal_zzcas.packageName);
        com_google_android_gms_internal_zzcgk.zzizg = new zzcgm[zziv.size()];
        for (int i = 0; i < zziv.size(); i++) {
            zzcgm com_google_android_gms_internal_zzcgm = new zzcgm();
            com_google_android_gms_internal_zzcgk.zzizg[i] = com_google_android_gms_internal_zzcgm;
            com_google_android_gms_internal_zzcgm.name = ((zzcfv) zziv.get(i)).mName;
            com_google_android_gms_internal_zzcgm.zzjaj = Long.valueOf(((zzcfv) zziv.get(i)).zzixd);
            zzauh().zza(com_google_android_gms_internal_zzcgm, ((zzcfv) zziv.get(i)).mValue);
        }
        try {
            boolean z;
            long zza = zzauf().zza(com_google_android_gms_internal_zzcgk);
            zzcay zzauf = zzauf();
            if (com_google_android_gms_internal_zzcbf.zzink != null) {
                Iterator it = com_google_android_gms_internal_zzcbf.zzink.iterator();
                while (it.hasNext()) {
                    if ("_r".equals((String) it.next())) {
                        z = true;
                        break;
                    }
                }
                z = zzaui().zzap(com_google_android_gms_internal_zzcbf.mAppId, com_google_android_gms_internal_zzcbf.mName);
                zzcaz zza2 = zzauf().zza(zzazf(), com_google_android_gms_internal_zzcbf.mAppId, false, false, false, false, false);
                if (z && zza2.zzinc < ((long) this.zziss.zzis(com_google_android_gms_internal_zzcbf.mAppId))) {
                    z = true;
                    if (zzauf.zza(com_google_android_gms_internal_zzcbf, zza, z)) {
                        this.zzity = 0;
                    }
                }
            }
            z = false;
            if (zzauf.zza(com_google_android_gms_internal_zzcbf, zza, z)) {
                this.zzity = 0;
            }
        } catch (IOException e) {
            zzaul().zzayd().zze("Data loss. Failed to insert raw event metadata. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcgk.zzci), e);
        }
    }

    private static void zza(zzcdt com_google_android_gms_internal_zzcdt) {
        if (com_google_android_gms_internal_zzcdt == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static void zza(zzcdu com_google_android_gms_internal_zzcdu) {
        if (com_google_android_gms_internal_zzcdu == null) {
            throw new IllegalStateException("Component not created");
        } else if (!com_google_android_gms_internal_zzcdu.isInitialized()) {
            throw new IllegalStateException("Component not initialized");
        }
    }

    private final boolean zza(int i, FileChannel fileChannel) {
        zzauk().zzuj();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzaul().zzayd().log("Bad chanel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() == 4) {
                return true;
            }
            zzaul().zzayd().zzj("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            return true;
        } catch (IOException e) {
            zzaul().zzayd().zzj("Failed to write to channel", e);
            return false;
        }
    }

    private final zzcgg[] zza(String str, zzcgm[] com_google_android_gms_internal_zzcgmArr, zzcgh[] com_google_android_gms_internal_zzcghArr) {
        zzbp.zzgg(str);
        return zzaty().zza(str, com_google_android_gms_internal_zzcghArr, com_google_android_gms_internal_zzcgmArr);
    }

    static void zzatu() {
        zzcax.zzawk();
        throw new IllegalStateException("Unexpected call on client side");
    }

    private final zzccf zzazb() {
        if (this.zzitk != null) {
            return this.zzitk;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzcfr zzazc() {
        zza(this.zzitl);
        return this.zzitl;
    }

    private final boolean zzazd() {
        zzauk().zzuj();
        try {
            this.zzits = new RandomAccessFile(new File(this.mContext.getFilesDir(), zzcax.zzawi()), "rw").getChannel();
            this.zzitr = this.zzits.tryLock();
            if (this.zzitr != null) {
                zzaul().zzayj().log("Storage concurrent access okay");
                return true;
            }
            zzaul().zzayd().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            zzaul().zzayd().zzj("Failed to acquire storage lock", e);
        } catch (IOException e2) {
            zzaul().zzayd().zzj("Failed to access storage lock file", e2);
        }
    }

    private final long zzazf() {
        long currentTimeMillis = this.zzasc.currentTimeMillis();
        zzcdt zzaum = zzaum();
        zzaum.zzwk();
        zzaum.zzuj();
        long j = zzaum.zziqt.get();
        if (j == 0) {
            j = (long) (zzaum.zzauh().zzazy().nextInt(86400000) + 1);
            zzaum.zziqt.set(j);
        }
        return ((((j + currentTimeMillis) / 1000) / 60) / 60) / 24;
    }

    private final boolean zzazh() {
        zzauk().zzuj();
        zzwk();
        return zzauf().zzaxn() || !TextUtils.isEmpty(zzauf().zzaxi());
    }

    private final void zzazi() {
        zzauk().zzuj();
        zzwk();
        if (zzazl()) {
            long abs;
            if (this.zzity > 0) {
                abs = 3600000 - Math.abs(this.zzasc.elapsedRealtime() - this.zzity);
                if (abs > 0) {
                    zzaul().zzayj().zzj("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                    zzazb().unregister();
                    zzazc().cancel();
                    return;
                }
                this.zzity = 0;
            }
            if (zzayv() && zzazh()) {
                long currentTimeMillis = this.zzasc.currentTimeMillis();
                long zzaxd = zzcax.zzaxd();
                Object obj = (zzauf().zzaxo() || zzauf().zzaxj()) ? 1 : null;
                if (obj != null) {
                    CharSequence zzaxg = this.zziss.zzaxg();
                    abs = (TextUtils.isEmpty(zzaxg) || ".none.".equals(zzaxg)) ? zzcax.zzawy() : zzcax.zzawz();
                } else {
                    abs = zzcax.zzawx();
                }
                long j = zzaum().zziqp.get();
                long j2 = zzaum().zziqq.get();
                long max = Math.max(zzauf().zzaxl(), zzauf().zzaxm());
                if (max == 0) {
                    abs = 0;
                } else {
                    max = currentTimeMillis - Math.abs(max - currentTimeMillis);
                    j2 = currentTimeMillis - Math.abs(j2 - currentTimeMillis);
                    j = Math.max(currentTimeMillis - Math.abs(j - currentTimeMillis), j2);
                    currentTimeMillis = max + zzaxd;
                    if (obj != null && j > 0) {
                        currentTimeMillis = Math.min(max, j) + abs;
                    }
                    if (!zzauh().zzf(j, abs)) {
                        currentTimeMillis = j + abs;
                    }
                    if (j2 == 0 || j2 < max) {
                        abs = currentTimeMillis;
                    } else {
                        for (int i = 0; i < zzcax.zzaxf(); i++) {
                            currentTimeMillis += ((long) (1 << i)) * zzcax.zzaxe();
                            if (currentTimeMillis > j2) {
                                abs = currentTimeMillis;
                                break;
                            }
                        }
                        abs = 0;
                    }
                }
                if (abs == 0) {
                    zzaul().zzayj().log("Next upload time is 0");
                    zzazb().unregister();
                    zzazc().cancel();
                    return;
                } else if (zzaza().zzyx()) {
                    currentTimeMillis = zzaum().zziqr.get();
                    long zzaww = zzcax.zzaww();
                    if (!zzauh().zzf(currentTimeMillis, zzaww)) {
                        abs = Math.max(abs, currentTimeMillis + zzaww);
                    }
                    zzazb().unregister();
                    abs -= this.zzasc.currentTimeMillis();
                    if (abs <= 0) {
                        abs = zzcax.zzaxa();
                        zzaum().zziqp.set(this.zzasc.currentTimeMillis());
                    }
                    zzaul().zzayj().zzj("Upload scheduled in approximately ms", Long.valueOf(abs));
                    zzazc().zzs(abs);
                    return;
                } else {
                    zzaul().zzayj().log("No network");
                    zzazb().zzyu();
                    zzazc().cancel();
                    return;
                }
            }
            zzaul().zzayj().log("Nothing to upload or uploading impossible");
            zzazb().unregister();
            zzazc().cancel();
        }
    }

    private final boolean zzazl() {
        zzauk().zzuj();
        zzwk();
        return this.zzito;
    }

    private final void zzazm() {
        zzauk().zzuj();
        if (this.zzitz || this.zziua || this.zziub) {
            zzaul().zzayj().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzitz), Boolean.valueOf(this.zziua), Boolean.valueOf(this.zziub));
            return;
        }
        zzaul().zzayj().log("Stopping uploading service(s)");
        if (this.zzitu != null) {
            for (Runnable run : this.zzitu) {
                run.run();
            }
            this.zzitu.clear();
        }
    }

    private final void zzb(zzcar com_google_android_gms_internal_zzcar) {
        zzauk().zzuj();
        if (TextUtils.isEmpty(com_google_android_gms_internal_zzcar.getGmpAppId())) {
            zzb(com_google_android_gms_internal_zzcar.getAppId(), 204, null, null, null);
            return;
        }
        String gmpAppId = com_google_android_gms_internal_zzcar.getGmpAppId();
        String appInstanceId = com_google_android_gms_internal_zzcar.getAppInstanceId();
        Builder builder = new Builder();
        Builder encodedAuthority = builder.scheme((String) zzcbm.zziof.get()).encodedAuthority((String) zzcbm.zziog.get());
        String str = "config/app/";
        String valueOf = String.valueOf(gmpAppId);
        encodedAuthority.path(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).appendQueryParameter("app_instance_id", appInstanceId).appendQueryParameter(ApiConstants.PLATFORM, "android").appendQueryParameter("gmp_version", "11400");
        String uri = builder.build().toString();
        try {
            Map map;
            URL url = new URL(uri);
            zzaul().zzayj().zzj("Fetching remote configuration", com_google_android_gms_internal_zzcar.getAppId());
            zzcge zzjn = zzaui().zzjn(com_google_android_gms_internal_zzcar.getAppId());
            CharSequence zzjo = zzaui().zzjo(com_google_android_gms_internal_zzcar.getAppId());
            if (zzjn == null || TextUtils.isEmpty(zzjo)) {
                map = null;
            } else {
                Map arrayMap = new ArrayMap();
                arrayMap.put("If-Modified-Since", zzjo);
                map = arrayMap;
            }
            this.zzitz = true;
            zzcdt zzaza = zzaza();
            appInstanceId = com_google_android_gms_internal_zzcar.getAppId();
            zzccc com_google_android_gms_internal_zzcda = new zzcda(this);
            zzaza.zzuj();
            zzaza.zzwk();
            zzbp.zzu(url);
            zzbp.zzu(com_google_android_gms_internal_zzcda);
            zzaza.zzauk().zzh(new zzcce(zzaza, appInstanceId, url, null, map, com_google_android_gms_internal_zzcda));
        } catch (MalformedURLException e) {
            zzaul().zzayd().zze("Failed to parse config URL. Not fetching. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcar.getAppId()), uri);
        }
    }

    private final void zzc(zzcbk com_google_android_gms_internal_zzcbk, zzcas com_google_android_gms_internal_zzcas) {
        zzbp.zzu(com_google_android_gms_internal_zzcas);
        zzbp.zzgg(com_google_android_gms_internal_zzcas.packageName);
        long nanoTime = System.nanoTime();
        zzauk().zzuj();
        zzwk();
        String str = com_google_android_gms_internal_zzcas.packageName;
        zzauh();
        if (!zzcfw.zzd(com_google_android_gms_internal_zzcbk, com_google_android_gms_internal_zzcas)) {
            return;
        }
        if (!com_google_android_gms_internal_zzcas.zzilz) {
            zzf(com_google_android_gms_internal_zzcas);
        } else if (zzaui().zzao(str, com_google_android_gms_internal_zzcbk.name)) {
            zzaul().zzayf().zze("Dropping blacklisted event. appId", zzcbw.zzjf(str), zzaug().zzjc(com_google_android_gms_internal_zzcbk.name));
            Object obj = (zzauh().zzkg(str) || zzauh().zzkh(str)) ? 1 : null;
            if (obj == null && !"_err".equals(com_google_android_gms_internal_zzcbk.name)) {
                zzauh().zza(str, 11, "_ev", com_google_android_gms_internal_zzcbk.name, 0);
            }
            if (obj != null) {
                zzcar zziw = zzauf().zziw(str);
                if (zziw != null) {
                    if (Math.abs(this.zzasc.currentTimeMillis() - Math.max(zziw.zzava(), zziw.zzauz())) > zzcax.zzawo()) {
                        zzaul().zzayi().log("Fetching config for blacklisted app");
                        zzb(zziw);
                    }
                }
            }
        } else {
            Bundle zzaxz;
            if (zzaul().zzad(2)) {
                zzaul().zzayj().zzj("Logging event", zzaug().zzb(com_google_android_gms_internal_zzcbk));
            }
            zzauf().beginTransaction();
            zzcdt zzauf;
            try {
                zzaxz = com_google_android_gms_internal_zzcbk.zzinr.zzaxz();
                zzf(com_google_android_gms_internal_zzcas);
                if ("_iap".equals(com_google_android_gms_internal_zzcbk.name) || "ecommerce_purchase".equals(com_google_android_gms_internal_zzcbk.name)) {
                    long round;
                    Object string = zzaxz.getString("currency");
                    if ("ecommerce_purchase".equals(com_google_android_gms_internal_zzcbk.name)) {
                        double d = zzaxz.getDouble(CryptoUri.VALUE) * 1000000.0d;
                        if (d == 0.0d) {
                            d = ((double) zzaxz.getLong(CryptoUri.VALUE)) * 1000000.0d;
                        }
                        if (d > 9.223372036854776E18d || d < -9.223372036854776E18d) {
                            zzaul().zzayf().zze("Data lost. Currency value is too big. appId", zzcbw.zzjf(str), Double.valueOf(d));
                            zzauf().setTransactionSuccessful();
                            zzauf().endTransaction();
                            return;
                        }
                        round = Math.round(d);
                    } else {
                        round = zzaxz.getLong(CryptoUri.VALUE);
                    }
                    if (!TextUtils.isEmpty(string)) {
                        String toUpperCase = string.toUpperCase(Locale.US);
                        if (toUpperCase.matches("[A-Z]{3}")) {
                            String valueOf = String.valueOf("_ltv_");
                            toUpperCase = String.valueOf(toUpperCase);
                            String concat = toUpperCase.length() != 0 ? valueOf.concat(toUpperCase) : new String(valueOf);
                            zzcfv zzah = zzauf().zzah(str, concat);
                            if (zzah == null || !(zzah.mValue instanceof Long)) {
                                zzauf = zzauf();
                                int zzb = this.zziss.zzb(str, zzcbm.zzipf) - 1;
                                zzbp.zzgg(str);
                                zzauf.zzuj();
                                zzauf.zzwk();
                                zzauf.getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(zzb)});
                                zzah = new zzcfv(str, com_google_android_gms_internal_zzcbk.zzimg, concat, this.zzasc.currentTimeMillis(), Long.valueOf(round));
                            } else {
                                zzah = new zzcfv(str, com_google_android_gms_internal_zzcbk.zzimg, concat, this.zzasc.currentTimeMillis(), Long.valueOf(round + ((Long) zzah.mValue).longValue()));
                            }
                            if (!zzauf().zza(zzah)) {
                                zzaul().zzayd().zzd("Too many unique user properties are set. Ignoring user property. appId", zzcbw.zzjf(str), zzaug().zzje(zzah.mName), zzah.mValue);
                                zzauh().zza(str, 9, null, null, 0);
                            }
                        }
                    }
                }
            } catch (SQLiteException e) {
                zzauf.zzaul().zzayd().zze("Error pruning currencies. appId", zzcbw.zzjf(str), e);
            } catch (Throwable th) {
                zzauf().endTransaction();
            }
            boolean zzju = zzcfw.zzju(com_google_android_gms_internal_zzcbk.name);
            boolean equals = "_err".equals(com_google_android_gms_internal_zzcbk.name);
            zzcaz zza = zzauf().zza(zzazf(), str, true, zzju, false, equals, false);
            long zzavw = zza.zzimz - zzcax.zzavw();
            if (zzavw > 0) {
                if (zzavw % 1000 == 1) {
                    zzaul().zzayd().zze("Data loss. Too many events logged. appId, count", zzcbw.zzjf(str), Long.valueOf(zza.zzimz));
                }
                zzauf().setTransactionSuccessful();
                zzauf().endTransaction();
                return;
            }
            zzcbg com_google_android_gms_internal_zzcbg;
            if (zzju) {
                zzavw = zza.zzimy - zzcax.zzavx();
                if (zzavw > 0) {
                    if (zzavw % 1000 == 1) {
                        zzaul().zzayd().zze("Data loss. Too many public events logged. appId, count", zzcbw.zzjf(str), Long.valueOf(zza.zzimy));
                    }
                    zzauh().zza(str, 16, "_ev", com_google_android_gms_internal_zzcbk.name, 0);
                    zzauf().setTransactionSuccessful();
                    zzauf().endTransaction();
                    return;
                }
            }
            if (equals) {
                zzavw = zza.zzinb - ((long) Math.max(0, Math.min(1000000, this.zziss.zzb(com_google_android_gms_internal_zzcas.packageName, zzcbm.zziom))));
                if (zzavw > 0) {
                    if (zzavw == 1) {
                        zzaul().zzayd().zze("Too many error events logged. appId, count", zzcbw.zzjf(str), Long.valueOf(zza.zzinb));
                    }
                    zzauf().setTransactionSuccessful();
                    zzauf().endTransaction();
                    return;
                }
            }
            zzauh().zza(zzaxz, "_o", com_google_android_gms_internal_zzcbk.zzimg);
            if (zzauh().zzke(str)) {
                zzauh().zza(zzaxz, "_dbg", Long.valueOf(1));
                zzauh().zza(zzaxz, "_r", Long.valueOf(1));
            }
            zzavw = zzauf().zzix(str);
            if (zzavw > 0) {
                zzaul().zzayf().zze("Data lost. Too many events stored on disk, deleted. appId", zzcbw.zzjf(str), Long.valueOf(zzavw));
            }
            zzcbf com_google_android_gms_internal_zzcbf = new zzcbf(this, com_google_android_gms_internal_zzcbk.zzimg, str, com_google_android_gms_internal_zzcbk.name, com_google_android_gms_internal_zzcbk.zzins, 0, zzaxz);
            zzcbg zzaf = zzauf().zzaf(str, com_google_android_gms_internal_zzcbf.mName);
            if (zzaf == null) {
                long zzja = zzauf().zzja(str);
                zzcax.zzavv();
                if (zzja >= 500) {
                    zzaul().zzayd().zzd("Too many event names used, ignoring event. appId, name, supported count", zzcbw.zzjf(str), zzaug().zzjc(com_google_android_gms_internal_zzcbf.mName), Integer.valueOf(zzcax.zzavv()));
                    zzauh().zza(str, 8, null, null, 0);
                    zzauf().endTransaction();
                    return;
                }
                com_google_android_gms_internal_zzcbg = new zzcbg(str, com_google_android_gms_internal_zzcbf.mName, 0, 0, com_google_android_gms_internal_zzcbf.zzfdc);
            } else {
                com_google_android_gms_internal_zzcbf = com_google_android_gms_internal_zzcbf.zza(this, zzaf.zzinn);
                com_google_android_gms_internal_zzcbg = zzaf.zzbb(com_google_android_gms_internal_zzcbf.zzfdc);
            }
            zzauf().zza(com_google_android_gms_internal_zzcbg);
            zza(com_google_android_gms_internal_zzcbf, com_google_android_gms_internal_zzcas);
            zzauf().setTransactionSuccessful();
            if (zzaul().zzad(2)) {
                zzaul().zzayj().zzj("Event recorded", zzaug().zza(com_google_android_gms_internal_zzcbf));
            }
            zzauf().endTransaction();
            zzazi();
            zzaul().zzayj().zzj("Background event processing time, ms", Long.valueOf(((System.nanoTime() - nanoTime) + 500000) / 1000000));
        }
    }

    public static zzccw zzdn(Context context) {
        zzbp.zzu(context);
        zzbp.zzu(context.getApplicationContext());
        if (zzisr == null) {
            synchronized (zzccw.class) {
                if (zzisr == null) {
                    zzisr = new zzccw(new zzcdv(context));
                }
            }
        }
        return zzisr;
    }

    private final void zzf(zzcas com_google_android_gms_internal_zzcas) {
        Object obj = 1;
        zzauk().zzuj();
        zzwk();
        zzbp.zzu(com_google_android_gms_internal_zzcas);
        zzbp.zzgg(com_google_android_gms_internal_zzcas.packageName);
        zzcar zziw = zzauf().zziw(com_google_android_gms_internal_zzcas.packageName);
        String zzji = zzaum().zzji(com_google_android_gms_internal_zzcas.packageName);
        Object obj2 = null;
        if (zziw == null) {
            zzcar com_google_android_gms_internal_zzcar = new zzcar(this, com_google_android_gms_internal_zzcas.packageName);
            com_google_android_gms_internal_zzcar.zzim(zzaua().zzaya());
            com_google_android_gms_internal_zzcar.zzio(zzji);
            zziw = com_google_android_gms_internal_zzcar;
            obj2 = 1;
        } else if (!zzji.equals(zziw.zzaup())) {
            zziw.zzio(zzji);
            zziw.zzim(zzaua().zzaya());
            int i = 1;
        }
        if (!(TextUtils.isEmpty(com_google_android_gms_internal_zzcas.zzilu) || com_google_android_gms_internal_zzcas.zzilu.equals(zziw.getGmpAppId()))) {
            zziw.zzin(com_google_android_gms_internal_zzcas.zzilu);
            obj2 = 1;
        }
        if (!(TextUtils.isEmpty(com_google_android_gms_internal_zzcas.zzimc) || com_google_android_gms_internal_zzcas.zzimc.equals(zziw.zzauq()))) {
            zziw.zzip(com_google_android_gms_internal_zzcas.zzimc);
            obj2 = 1;
        }
        if (!(com_google_android_gms_internal_zzcas.zzilw == 0 || com_google_android_gms_internal_zzcas.zzilw == zziw.zzauv())) {
            zziw.zzao(com_google_android_gms_internal_zzcas.zzilw);
            obj2 = 1;
        }
        if (!(TextUtils.isEmpty(com_google_android_gms_internal_zzcas.zzhtt) || com_google_android_gms_internal_zzcas.zzhtt.equals(zziw.zzuo()))) {
            zziw.setAppVersion(com_google_android_gms_internal_zzcas.zzhtt);
            obj2 = 1;
        }
        if (com_google_android_gms_internal_zzcas.zzimb != zziw.zzaut()) {
            zziw.zzan(com_google_android_gms_internal_zzcas.zzimb);
            obj2 = 1;
        }
        if (!(com_google_android_gms_internal_zzcas.zzilv == null || com_google_android_gms_internal_zzcas.zzilv.equals(zziw.zzauu()))) {
            zziw.zziq(com_google_android_gms_internal_zzcas.zzilv);
            obj2 = 1;
        }
        if (com_google_android_gms_internal_zzcas.zzilx != zziw.zzauw()) {
            zziw.zzap(com_google_android_gms_internal_zzcas.zzilx);
            obj2 = 1;
        }
        if (com_google_android_gms_internal_zzcas.zzilz != zziw.zzaux()) {
            zziw.setMeasurementEnabled(com_google_android_gms_internal_zzcas.zzilz);
            obj2 = 1;
        }
        if (!(TextUtils.isEmpty(com_google_android_gms_internal_zzcas.zzily) || com_google_android_gms_internal_zzcas.zzily.equals(zziw.zzavi()))) {
            zziw.zzir(com_google_android_gms_internal_zzcas.zzily);
            obj2 = 1;
        }
        if (com_google_android_gms_internal_zzcas.zzimd != zziw.zzavk()) {
            zziw.zzaz(com_google_android_gms_internal_zzcas.zzimd);
        } else {
            obj = obj2;
        }
        if (obj != null) {
            zzauf().zza(zziw);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzg(String str, long j) {
        int i;
        zzcgi com_google_android_gms_internal_zzcgi;
        int i2;
        int length;
        long zzaus;
        Throwable th;
        zzauf().beginTransaction();
        zzccw com_google_android_gms_internal_zzccw = this;
        zza com_google_android_gms_internal_zzccw_zza = new zza();
        zzcdt zzauf = zzauf();
        String str2 = null;
        long j2 = this.zzitx;
        zzbp.zzu(com_google_android_gms_internal_zzccw_zza);
        zzauf.zzuj();
        zzauf.zzwk();
        Cursor cursor = null;
        Object obj;
        boolean z;
        zzcgk com_google_android_gms_internal_zzcgk;
        int i3;
        boolean zzap;
        Object obj2;
        Object obj3;
        zzcgi[] com_google_android_gms_internal_zzcgiArr;
        int length2;
        int i4;
        zzcgi[] com_google_android_gms_internal_zzcgiArr2;
        zzcgi com_google_android_gms_internal_zzcgi2;
        zzcgh com_google_android_gms_internal_zzcgh;
        Object obj4;
        zzcgi com_google_android_gms_internal_zzcgi3;
        zzcgi[] com_google_android_gms_internal_zzcgiArr3;
        int i5;
        zzcgi com_google_android_gms_internal_zzcgi4;
        int i6;
        boolean z2;
        int i7;
        boolean z3;
        String str3;
        zzcar zziw;
        long zzaur;
        zzcge zzjn;
        zzcdt zzauf2;
        boolean z4;
        try {
            String[] strArr;
            String str4;
            String str5;
            Cursor cursor2;
            String str6;
            SQLiteDatabase writableDatabase = zzauf.getWritableDatabase();
            if (TextUtils.isEmpty(null)) {
                strArr = j2 != -1 ? new String[]{String.valueOf(j2), String.valueOf(j)} : new String[]{String.valueOf(j)};
                str4 = j2 != -1 ? "rowid <= ? and " : "";
                cursor = writableDatabase.rawQuery(new StringBuilder(String.valueOf(str4).length() + 148).append("select app_id, metadata_fingerprint from raw_events where ").append(str4).append("app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;").toString(), strArr);
                if (cursor.moveToFirst()) {
                    str2 = cursor.getString(0);
                    str4 = cursor.getString(1);
                    cursor.close();
                    str5 = str4;
                    cursor2 = cursor;
                    str6 = str2;
                } else {
                    if (cursor != null) {
                        cursor.close();
                    }
                    obj = (com_google_android_gms_internal_zzccw_zza.zzaoc != null || com_google_android_gms_internal_zzccw_zza.zzaoc.isEmpty()) ? 1 : null;
                    if (obj != null) {
                        z = false;
                        com_google_android_gms_internal_zzcgk = com_google_android_gms_internal_zzccw_zza.zziue;
                        com_google_android_gms_internal_zzcgk.zzizf = new zzcgh[com_google_android_gms_internal_zzccw_zza.zzaoc.size()];
                        i3 = 0;
                        i = 0;
                        while (i < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                            if (zzaui().zzao(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name)) {
                                zzap = zzaui().zzap(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name);
                                if (!zzap) {
                                    zzauh();
                                }
                                obj2 = null;
                                obj3 = null;
                                if (((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx == null) {
                                    ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = new zzcgi[0];
                                }
                                com_google_android_gms_internal_zzcgiArr = ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx;
                                length2 = com_google_android_gms_internal_zzcgiArr.length;
                                i4 = 0;
                                while (i4 < length2) {
                                    com_google_android_gms_internal_zzcgi = com_google_android_gms_internal_zzcgiArr[i4];
                                    if (!"_c".equals(com_google_android_gms_internal_zzcgi.name)) {
                                        com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                        obj2 = 1;
                                        obj = obj3;
                                    } else if ("_r".equals(com_google_android_gms_internal_zzcgi.name)) {
                                        obj = obj3;
                                    } else {
                                        com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                        obj = 1;
                                    }
                                    i4++;
                                    obj3 = obj;
                                }
                                if (obj2 == null && zzap) {
                                    zzaul().zzayj().zzj("Marking event as conversion", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                    com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                                    com_google_android_gms_internal_zzcgi2 = new zzcgi();
                                    com_google_android_gms_internal_zzcgi2.name = "_c";
                                    com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                                    com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                                    ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                                }
                                if (obj3 == null) {
                                    zzaul().zzayj().zzj("Marking event as real-time", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                    com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                                    com_google_android_gms_internal_zzcgi2 = new zzcgi();
                                    com_google_android_gms_internal_zzcgi2.name = "_r";
                                    com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                                    com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                                    ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                                }
                                if (zzauf().zza(zzazf(), com_google_android_gms_internal_zzccw_zza.zziue.zzci, false, false, false, false, true).zzinc <= ((long) this.zziss.zzis(com_google_android_gms_internal_zzccw_zza.zziue.zzci))) {
                                    com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                    i2 = 0;
                                    while (i2 < com_google_android_gms_internal_zzcgh.zziyx.length) {
                                        if ("_r".equals(com_google_android_gms_internal_zzcgh.zziyx[i2].name)) {
                                            i2++;
                                        } else {
                                            obj3 = new zzcgi[(com_google_android_gms_internal_zzcgh.zziyx.length - 1)];
                                            if (i2 > 0) {
                                                System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, 0, obj3, 0, i2);
                                            }
                                            if (i2 < obj3.length) {
                                                System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, i2 + 1, obj3, i2, obj3.length - i2);
                                            }
                                            com_google_android_gms_internal_zzcgh.zziyx = obj3;
                                        }
                                    }
                                } else {
                                    z = true;
                                }
                                if (zzcfw.zzju(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name) && zzap && zzauf().zza(zzazf(), com_google_android_gms_internal_zzccw_zza.zziue.zzci, false, false, true, false, false).zzina > ((long) this.zziss.zzb(com_google_android_gms_internal_zzccw_zza.zziue.zzci, zzcbm.zzioo))) {
                                    zzaul().zzayf().zzj("Too many conversions. Not logging as conversion. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                    com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                    obj4 = null;
                                    com_google_android_gms_internal_zzcgi3 = null;
                                    com_google_android_gms_internal_zzcgiArr3 = com_google_android_gms_internal_zzcgh.zziyx;
                                    length = com_google_android_gms_internal_zzcgiArr3.length;
                                    i5 = 0;
                                    while (i5 < length) {
                                        com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgiArr3[i5];
                                        if (!"_c".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                            obj3 = obj4;
                                        } else if ("_err".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                            com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi3;
                                            obj3 = obj4;
                                        } else {
                                            com_google_android_gms_internal_zzcgi4 = com_google_android_gms_internal_zzcgi3;
                                            i6 = 1;
                                            com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi4;
                                        }
                                        i5++;
                                        obj4 = obj3;
                                        com_google_android_gms_internal_zzcgi3 = com_google_android_gms_internal_zzcgi2;
                                    }
                                    if (obj4 == null && com_google_android_gms_internal_zzcgi3 != null) {
                                        com_google_android_gms_internal_zzcgiArr3 = new zzcgi[(com_google_android_gms_internal_zzcgh.zziyx.length - 1)];
                                        i4 = 0;
                                        zzcgi[] com_google_android_gms_internal_zzcgiArr4 = com_google_android_gms_internal_zzcgh.zziyx;
                                        int length3 = com_google_android_gms_internal_zzcgiArr4.length;
                                        i5 = 0;
                                        while (i5 < length3) {
                                            zzcgi com_google_android_gms_internal_zzcgi5 = com_google_android_gms_internal_zzcgiArr4[i5];
                                            if (com_google_android_gms_internal_zzcgi5 != com_google_android_gms_internal_zzcgi3) {
                                                i2 = i4 + 1;
                                                com_google_android_gms_internal_zzcgiArr3[i4] = com_google_android_gms_internal_zzcgi5;
                                            } else {
                                                i2 = i4;
                                            }
                                            i5++;
                                            i4 = i2;
                                        }
                                        com_google_android_gms_internal_zzcgh.zziyx = com_google_android_gms_internal_zzcgiArr3;
                                        z2 = z;
                                        i2 = i3 + 1;
                                        com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                        i7 = i2;
                                        z3 = z2;
                                    } else if (com_google_android_gms_internal_zzcgi3 == null) {
                                        com_google_android_gms_internal_zzcgi3.name = "_err";
                                        com_google_android_gms_internal_zzcgi3.zzizb = Long.valueOf(10);
                                        z2 = z;
                                        i2 = i3 + 1;
                                        com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                        i7 = i2;
                                        z3 = z2;
                                    } else {
                                        zzaul().zzayd().zzj("Did not find conversion parameter. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                    }
                                }
                                z2 = z;
                                i2 = i3 + 1;
                                com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                i7 = i2;
                                z3 = z2;
                            } else {
                                zzaul().zzayf().zze("Dropping blacklisted raw event. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci), zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                obj = (zzauh().zzkg(com_google_android_gms_internal_zzccw_zza.zziue.zzci) || zzauh().zzkh(com_google_android_gms_internal_zzccw_zza.zziue.zzci)) ? 1 : null;
                                if (obj == null || "_err".equals(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name)) {
                                    i7 = i3;
                                    z3 = z;
                                } else {
                                    zzauh().zza(com_google_android_gms_internal_zzccw_zza.zziue.zzci, 11, "_ev", ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name, 0);
                                    i7 = i3;
                                    z3 = z;
                                }
                            }
                            i++;
                            i3 = i7;
                            z = z3;
                        }
                        if (i3 < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                            com_google_android_gms_internal_zzcgk.zzizf = (zzcgh[]) Arrays.copyOf(com_google_android_gms_internal_zzcgk.zzizf, i3);
                        }
                        com_google_android_gms_internal_zzcgk.zzizy = zza(com_google_android_gms_internal_zzccw_zza.zziue.zzci, com_google_android_gms_internal_zzccw_zza.zziue.zzizg, com_google_android_gms_internal_zzcgk.zzizf);
                        com_google_android_gms_internal_zzcgk.zzizi = Long.valueOf(Long.MAX_VALUE);
                        com_google_android_gms_internal_zzcgk.zzizj = Long.valueOf(Long.MIN_VALUE);
                        for (zzcgh com_google_android_gms_internal_zzcgh2 : com_google_android_gms_internal_zzcgk.zzizf) {
                            if (com_google_android_gms_internal_zzcgh2.zziyy.longValue() < com_google_android_gms_internal_zzcgk.zzizi.longValue()) {
                                com_google_android_gms_internal_zzcgk.zzizi = com_google_android_gms_internal_zzcgh2.zziyy;
                            }
                            if (com_google_android_gms_internal_zzcgh2.zziyy.longValue() <= com_google_android_gms_internal_zzcgk.zzizj.longValue()) {
                                com_google_android_gms_internal_zzcgk.zzizj = com_google_android_gms_internal_zzcgh2.zziyy;
                            }
                        }
                        str3 = com_google_android_gms_internal_zzccw_zza.zziue.zzci;
                        zziw = zzauf().zziw(str3);
                        if (zziw != null) {
                            zzaul().zzayd().zzj("Bundling raw events w/o app info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                        } else if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                            zzaus = zziw.zzaus();
                            com_google_android_gms_internal_zzcgk.zzizl = zzaus == 0 ? Long.valueOf(zzaus) : null;
                            zzaur = zziw.zzaur();
                            if (zzaur != 0) {
                                zzaus = zzaur;
                            }
                            com_google_android_gms_internal_zzcgk.zzizk = zzaus == 0 ? Long.valueOf(zzaus) : null;
                            zziw.zzavb();
                            com_google_android_gms_internal_zzcgk.zzizw = Integer.valueOf((int) zziw.zzauy());
                            zziw.zzal(com_google_android_gms_internal_zzcgk.zzizi.longValue());
                            zziw.zzam(com_google_android_gms_internal_zzcgk.zzizj.longValue());
                            com_google_android_gms_internal_zzcgk.zzily = zziw.zzavj();
                            zzauf().zza(zziw);
                        }
                        if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                            zzcax.zzawk();
                            zzjn = zzaui().zzjn(com_google_android_gms_internal_zzccw_zza.zziue.zzci);
                            if (zzjn == null && zzjn.zziym != null) {
                                com_google_android_gms_internal_zzcgk.zzjad = zzjn.zziym;
                            } else if (TextUtils.isEmpty(com_google_android_gms_internal_zzccw_zza.zziue.zzilu)) {
                                zzaul().zzayf().zzj("Did not find measurement config or missing version info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                            } else {
                                com_google_android_gms_internal_zzcgk.zzjad = Long.valueOf(-1);
                            }
                            zzauf().zza(com_google_android_gms_internal_zzcgk, z);
                        }
                        zzauf().zzae(com_google_android_gms_internal_zzccw_zza.zziuf);
                        zzauf2 = zzauf();
                        try {
                            zzauf2.getWritableDatabase().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                        } catch (SQLiteException e) {
                            zzauf2.zzaul().zzayd().zze("Failed to remove unused event metadata. appId", zzcbw.zzjf(str3), e);
                        }
                        zzauf().setTransactionSuccessful();
                        z4 = com_google_android_gms_internal_zzcgk.zzizf.length <= 0;
                        zzauf().endTransaction();
                        return z4;
                    }
                    zzauf().setTransactionSuccessful();
                    zzauf().endTransaction();
                    return false;
                }
            }
            strArr = j2 != -1 ? new String[]{null, String.valueOf(j2)} : new String[]{null};
            str4 = j2 != -1 ? " and rowid <= ?" : "";
            cursor = writableDatabase.rawQuery(new StringBuilder(String.valueOf(str4).length() + 84).append("select metadata_fingerprint from raw_events where app_id = ?").append(str4).append(" order by rowid limit 1;").toString(), strArr);
            if (cursor.moveToFirst()) {
                str4 = cursor.getString(0);
                cursor.close();
                str5 = str4;
                cursor2 = cursor;
                str6 = null;
            } else {
                if (cursor != null) {
                    cursor.close();
                }
                if (com_google_android_gms_internal_zzccw_zza.zzaoc != null) {
                }
                if (obj != null) {
                    zzauf().setTransactionSuccessful();
                    zzauf().endTransaction();
                    return false;
                }
                z = false;
                com_google_android_gms_internal_zzcgk = com_google_android_gms_internal_zzccw_zza.zziue;
                com_google_android_gms_internal_zzcgk.zzizf = new zzcgh[com_google_android_gms_internal_zzccw_zza.zzaoc.size()];
                i3 = 0;
                i = 0;
                while (i < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                    if (zzaui().zzao(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name)) {
                        zzap = zzaui().zzap(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name);
                        if (zzap) {
                            zzauh();
                        }
                        obj2 = null;
                        obj3 = null;
                        if (((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx == null) {
                            ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = new zzcgi[0];
                        }
                        com_google_android_gms_internal_zzcgiArr = ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx;
                        length2 = com_google_android_gms_internal_zzcgiArr.length;
                        i4 = 0;
                        while (i4 < length2) {
                            com_google_android_gms_internal_zzcgi = com_google_android_gms_internal_zzcgiArr[i4];
                            if (!"_c".equals(com_google_android_gms_internal_zzcgi.name)) {
                                com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                obj2 = 1;
                                obj = obj3;
                            } else if ("_r".equals(com_google_android_gms_internal_zzcgi.name)) {
                                obj = obj3;
                            } else {
                                com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                obj = 1;
                            }
                            i4++;
                            obj3 = obj;
                        }
                        zzaul().zzayj().zzj("Marking event as conversion", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                        com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                        com_google_android_gms_internal_zzcgi2 = new zzcgi();
                        com_google_android_gms_internal_zzcgi2.name = "_c";
                        com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                        com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                        ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                        if (obj3 == null) {
                            zzaul().zzayj().zzj("Marking event as real-time", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                            com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                            com_google_android_gms_internal_zzcgi2 = new zzcgi();
                            com_google_android_gms_internal_zzcgi2.name = "_r";
                            com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                            com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                            ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                        }
                        if (zzauf().zza(zzazf(), com_google_android_gms_internal_zzccw_zza.zziue.zzci, false, false, false, false, true).zzinc <= ((long) this.zziss.zzis(com_google_android_gms_internal_zzccw_zza.zziue.zzci))) {
                            z = true;
                        } else {
                            com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                            i2 = 0;
                            while (i2 < com_google_android_gms_internal_zzcgh.zziyx.length) {
                                if ("_r".equals(com_google_android_gms_internal_zzcgh.zziyx[i2].name)) {
                                    i2++;
                                } else {
                                    obj3 = new zzcgi[(com_google_android_gms_internal_zzcgh.zziyx.length - 1)];
                                    if (i2 > 0) {
                                        System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, 0, obj3, 0, i2);
                                    }
                                    if (i2 < obj3.length) {
                                        System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, i2 + 1, obj3, i2, obj3.length - i2);
                                    }
                                    com_google_android_gms_internal_zzcgh.zziyx = obj3;
                                }
                            }
                        }
                        zzaul().zzayf().zzj("Too many conversions. Not logging as conversion. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                        com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                        obj4 = null;
                        com_google_android_gms_internal_zzcgi3 = null;
                        com_google_android_gms_internal_zzcgiArr3 = com_google_android_gms_internal_zzcgh.zziyx;
                        length = com_google_android_gms_internal_zzcgiArr3.length;
                        i5 = 0;
                        while (i5 < length) {
                            com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgiArr3[i5];
                            if (!"_c".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                obj3 = obj4;
                            } else if ("_err".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi3;
                                obj3 = obj4;
                            } else {
                                com_google_android_gms_internal_zzcgi4 = com_google_android_gms_internal_zzcgi3;
                                i6 = 1;
                                com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi4;
                            }
                            i5++;
                            obj4 = obj3;
                            com_google_android_gms_internal_zzcgi3 = com_google_android_gms_internal_zzcgi2;
                        }
                        if (obj4 == null) {
                        }
                        if (com_google_android_gms_internal_zzcgi3 == null) {
                            zzaul().zzayd().zzj("Did not find conversion parameter. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                            z2 = z;
                            i2 = i3 + 1;
                            com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                            i7 = i2;
                            z3 = z2;
                        } else {
                            com_google_android_gms_internal_zzcgi3.name = "_err";
                            com_google_android_gms_internal_zzcgi3.zzizb = Long.valueOf(10);
                            z2 = z;
                            i2 = i3 + 1;
                            com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                            i7 = i2;
                            z3 = z2;
                        }
                    } else {
                        zzaul().zzayf().zze("Dropping blacklisted raw event. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci), zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                        if (!zzauh().zzkg(com_google_android_gms_internal_zzccw_zza.zziue.zzci)) {
                        }
                        if (obj == null) {
                        }
                        i7 = i3;
                        z3 = z;
                    }
                    i++;
                    i3 = i7;
                    z = z3;
                }
                if (i3 < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                    com_google_android_gms_internal_zzcgk.zzizf = (zzcgh[]) Arrays.copyOf(com_google_android_gms_internal_zzcgk.zzizf, i3);
                }
                com_google_android_gms_internal_zzcgk.zzizy = zza(com_google_android_gms_internal_zzccw_zza.zziue.zzci, com_google_android_gms_internal_zzccw_zza.zziue.zzizg, com_google_android_gms_internal_zzcgk.zzizf);
                com_google_android_gms_internal_zzcgk.zzizi = Long.valueOf(Long.MAX_VALUE);
                com_google_android_gms_internal_zzcgk.zzizj = Long.valueOf(Long.MIN_VALUE);
                for (zzcgh com_google_android_gms_internal_zzcgh22 : com_google_android_gms_internal_zzcgk.zzizf) {
                    if (com_google_android_gms_internal_zzcgh22.zziyy.longValue() < com_google_android_gms_internal_zzcgk.zzizi.longValue()) {
                        com_google_android_gms_internal_zzcgk.zzizi = com_google_android_gms_internal_zzcgh22.zziyy;
                    }
                    if (com_google_android_gms_internal_zzcgh22.zziyy.longValue() <= com_google_android_gms_internal_zzcgk.zzizj.longValue()) {
                        com_google_android_gms_internal_zzcgk.zzizj = com_google_android_gms_internal_zzcgh22.zziyy;
                    }
                }
                str3 = com_google_android_gms_internal_zzccw_zza.zziue.zzci;
                zziw = zzauf().zziw(str3);
                if (zziw != null) {
                    zzaul().zzayd().zzj("Bundling raw events w/o app info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                } else if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                    zzaus = zziw.zzaus();
                    if (zzaus == 0) {
                    }
                    com_google_android_gms_internal_zzcgk.zzizl = zzaus == 0 ? Long.valueOf(zzaus) : null;
                    zzaur = zziw.zzaur();
                    if (zzaur != 0) {
                        zzaus = zzaur;
                    }
                    if (zzaus == 0) {
                    }
                    com_google_android_gms_internal_zzcgk.zzizk = zzaus == 0 ? Long.valueOf(zzaus) : null;
                    zziw.zzavb();
                    com_google_android_gms_internal_zzcgk.zzizw = Integer.valueOf((int) zziw.zzauy());
                    zziw.zzal(com_google_android_gms_internal_zzcgk.zzizi.longValue());
                    zziw.zzam(com_google_android_gms_internal_zzcgk.zzizj.longValue());
                    com_google_android_gms_internal_zzcgk.zzily = zziw.zzavj();
                    zzauf().zza(zziw);
                }
                if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                    zzcax.zzawk();
                    zzjn = zzaui().zzjn(com_google_android_gms_internal_zzccw_zza.zziue.zzci);
                    if (zzjn == null) {
                    }
                    if (TextUtils.isEmpty(com_google_android_gms_internal_zzccw_zza.zziue.zzilu)) {
                        zzaul().zzayf().zzj("Did not find measurement config or missing version info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                    } else {
                        com_google_android_gms_internal_zzcgk.zzjad = Long.valueOf(-1);
                    }
                    zzauf().zza(com_google_android_gms_internal_zzcgk, z);
                }
                zzauf().zzae(com_google_android_gms_internal_zzccw_zza.zziuf);
                zzauf2 = zzauf();
                zzauf2.getWritableDatabase().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                zzauf().setTransactionSuccessful();
                if (com_google_android_gms_internal_zzcgk.zzizf.length <= 0) {
                }
                zzauf().endTransaction();
                return z4;
            }
            try {
                cursor2 = writableDatabase.query("raw_events_metadata", new String[]{"metadata"}, "app_id = ? and metadata_fingerprint = ?", new String[]{str6, str5}, null, null, "rowid", "2");
                if (cursor2.moveToFirst()) {
                    byte[] blob = cursor2.getBlob(0);
                    zzeye zzm = zzeye.zzm(blob, 0, blob.length);
                    zzeyn com_google_android_gms_internal_zzcgk2 = new zzcgk();
                    try {
                        com_google_android_gms_internal_zzcgk2.zza(zzm);
                        if (cursor2.moveToNext()) {
                            zzauf.zzaul().zzayf().zzj("Get multiple raw event metadata records, expected one. appId", zzcbw.zzjf(str6));
                        }
                        cursor2.close();
                        com_google_android_gms_internal_zzccw_zza.zzb(com_google_android_gms_internal_zzcgk2);
                        if (j2 != -1) {
                            str4 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
                            strArr = new String[]{str6, str5, String.valueOf(j2)};
                        } else {
                            str4 = "app_id = ? and metadata_fingerprint = ?";
                            strArr = new String[]{str6, str5};
                        }
                        cursor = writableDatabase.query("raw_events", new String[]{"rowid", "name", "timestamp", "data"}, str4, strArr, null, null, "rowid", null);
                        if (cursor.moveToFirst()) {
                            do {
                                zzaur = cursor.getLong(0);
                                byte[] blob2 = cursor.getBlob(3);
                                zzeye zzm2 = zzeye.zzm(blob2, 0, blob2.length);
                                zzeyn com_google_android_gms_internal_zzcgh3 = new zzcgh();
                                try {
                                    com_google_android_gms_internal_zzcgh3.zza(zzm2);
                                    com_google_android_gms_internal_zzcgh3.name = cursor.getString(1);
                                    com_google_android_gms_internal_zzcgh3.zziyy = Long.valueOf(cursor.getLong(2));
                                    if (!com_google_android_gms_internal_zzccw_zza.zza(zzaur, com_google_android_gms_internal_zzcgh3)) {
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (com_google_android_gms_internal_zzccw_zza.zzaoc != null) {
                                        }
                                        if (obj != null) {
                                            z = false;
                                            com_google_android_gms_internal_zzcgk = com_google_android_gms_internal_zzccw_zza.zziue;
                                            com_google_android_gms_internal_zzcgk.zzizf = new zzcgh[com_google_android_gms_internal_zzccw_zza.zzaoc.size()];
                                            i3 = 0;
                                            i = 0;
                                            while (i < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                                                if (zzaui().zzao(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name)) {
                                                    zzaul().zzayf().zze("Dropping blacklisted raw event. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci), zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                                    if (zzauh().zzkg(com_google_android_gms_internal_zzccw_zza.zziue.zzci)) {
                                                    }
                                                    if (obj == null) {
                                                    }
                                                    i7 = i3;
                                                    z3 = z;
                                                } else {
                                                    zzap = zzaui().zzap(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name);
                                                    if (zzap) {
                                                        zzauh();
                                                    }
                                                    obj2 = null;
                                                    obj3 = null;
                                                    if (((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx == null) {
                                                        ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = new zzcgi[0];
                                                    }
                                                    com_google_android_gms_internal_zzcgiArr = ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx;
                                                    length2 = com_google_android_gms_internal_zzcgiArr.length;
                                                    i4 = 0;
                                                    while (i4 < length2) {
                                                        com_google_android_gms_internal_zzcgi = com_google_android_gms_internal_zzcgiArr[i4];
                                                        if (!"_c".equals(com_google_android_gms_internal_zzcgi.name)) {
                                                            com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                                            obj2 = 1;
                                                            obj = obj3;
                                                        } else if ("_r".equals(com_google_android_gms_internal_zzcgi.name)) {
                                                            com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                                            obj = 1;
                                                        } else {
                                                            obj = obj3;
                                                        }
                                                        i4++;
                                                        obj3 = obj;
                                                    }
                                                    zzaul().zzayj().zzj("Marking event as conversion", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                                    com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                                                    com_google_android_gms_internal_zzcgi2 = new zzcgi();
                                                    com_google_android_gms_internal_zzcgi2.name = "_c";
                                                    com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                                                    com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                                                    ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                                                    if (obj3 == null) {
                                                        zzaul().zzayj().zzj("Marking event as real-time", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                                        com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                                                        com_google_android_gms_internal_zzcgi2 = new zzcgi();
                                                        com_google_android_gms_internal_zzcgi2.name = "_r";
                                                        com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                                                        com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                                                        ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                                                    }
                                                    if (zzauf().zza(zzazf(), com_google_android_gms_internal_zzccw_zza.zziue.zzci, false, false, false, false, true).zzinc <= ((long) this.zziss.zzis(com_google_android_gms_internal_zzccw_zza.zziue.zzci))) {
                                                        com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                                        i2 = 0;
                                                        while (i2 < com_google_android_gms_internal_zzcgh.zziyx.length) {
                                                            if ("_r".equals(com_google_android_gms_internal_zzcgh.zziyx[i2].name)) {
                                                                obj3 = new zzcgi[(com_google_android_gms_internal_zzcgh.zziyx.length - 1)];
                                                                if (i2 > 0) {
                                                                    System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, 0, obj3, 0, i2);
                                                                }
                                                                if (i2 < obj3.length) {
                                                                    System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, i2 + 1, obj3, i2, obj3.length - i2);
                                                                }
                                                                com_google_android_gms_internal_zzcgh.zziyx = obj3;
                                                            } else {
                                                                i2++;
                                                            }
                                                        }
                                                    } else {
                                                        z = true;
                                                    }
                                                    zzaul().zzayf().zzj("Too many conversions. Not logging as conversion. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                                    com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                                    obj4 = null;
                                                    com_google_android_gms_internal_zzcgi3 = null;
                                                    com_google_android_gms_internal_zzcgiArr3 = com_google_android_gms_internal_zzcgh.zziyx;
                                                    length = com_google_android_gms_internal_zzcgiArr3.length;
                                                    i5 = 0;
                                                    while (i5 < length) {
                                                        com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgiArr3[i5];
                                                        if (!"_c".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                                            obj3 = obj4;
                                                        } else if ("_err".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                                            com_google_android_gms_internal_zzcgi4 = com_google_android_gms_internal_zzcgi3;
                                                            i6 = 1;
                                                            com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi4;
                                                        } else {
                                                            com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi3;
                                                            obj3 = obj4;
                                                        }
                                                        i5++;
                                                        obj4 = obj3;
                                                        com_google_android_gms_internal_zzcgi3 = com_google_android_gms_internal_zzcgi2;
                                                    }
                                                    if (obj4 == null) {
                                                    }
                                                    if (com_google_android_gms_internal_zzcgi3 == null) {
                                                        com_google_android_gms_internal_zzcgi3.name = "_err";
                                                        com_google_android_gms_internal_zzcgi3.zzizb = Long.valueOf(10);
                                                        z2 = z;
                                                        i2 = i3 + 1;
                                                        com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                                        i7 = i2;
                                                        z3 = z2;
                                                    } else {
                                                        zzaul().zzayd().zzj("Did not find conversion parameter. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                                        z2 = z;
                                                        i2 = i3 + 1;
                                                        com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                                        i7 = i2;
                                                        z3 = z2;
                                                    }
                                                }
                                                i++;
                                                i3 = i7;
                                                z = z3;
                                            }
                                            if (i3 < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                                                com_google_android_gms_internal_zzcgk.zzizf = (zzcgh[]) Arrays.copyOf(com_google_android_gms_internal_zzcgk.zzizf, i3);
                                            }
                                            com_google_android_gms_internal_zzcgk.zzizy = zza(com_google_android_gms_internal_zzccw_zza.zziue.zzci, com_google_android_gms_internal_zzccw_zza.zziue.zzizg, com_google_android_gms_internal_zzcgk.zzizf);
                                            com_google_android_gms_internal_zzcgk.zzizi = Long.valueOf(Long.MAX_VALUE);
                                            com_google_android_gms_internal_zzcgk.zzizj = Long.valueOf(Long.MIN_VALUE);
                                            for (zzcgh com_google_android_gms_internal_zzcgh222 : com_google_android_gms_internal_zzcgk.zzizf) {
                                                if (com_google_android_gms_internal_zzcgh222.zziyy.longValue() < com_google_android_gms_internal_zzcgk.zzizi.longValue()) {
                                                    com_google_android_gms_internal_zzcgk.zzizi = com_google_android_gms_internal_zzcgh222.zziyy;
                                                }
                                                if (com_google_android_gms_internal_zzcgh222.zziyy.longValue() <= com_google_android_gms_internal_zzcgk.zzizj.longValue()) {
                                                    com_google_android_gms_internal_zzcgk.zzizj = com_google_android_gms_internal_zzcgh222.zziyy;
                                                }
                                            }
                                            str3 = com_google_android_gms_internal_zzccw_zza.zziue.zzci;
                                            zziw = zzauf().zziw(str3);
                                            if (zziw != null) {
                                                zzaul().zzayd().zzj("Bundling raw events w/o app info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                            } else if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                                                zzaus = zziw.zzaus();
                                                if (zzaus == 0) {
                                                }
                                                com_google_android_gms_internal_zzcgk.zzizl = zzaus == 0 ? Long.valueOf(zzaus) : null;
                                                zzaur = zziw.zzaur();
                                                if (zzaur != 0) {
                                                    zzaus = zzaur;
                                                }
                                                if (zzaus == 0) {
                                                }
                                                com_google_android_gms_internal_zzcgk.zzizk = zzaus == 0 ? Long.valueOf(zzaus) : null;
                                                zziw.zzavb();
                                                com_google_android_gms_internal_zzcgk.zzizw = Integer.valueOf((int) zziw.zzauy());
                                                zziw.zzal(com_google_android_gms_internal_zzcgk.zzizi.longValue());
                                                zziw.zzam(com_google_android_gms_internal_zzcgk.zzizj.longValue());
                                                com_google_android_gms_internal_zzcgk.zzily = zziw.zzavj();
                                                zzauf().zza(zziw);
                                            }
                                            if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                                                zzcax.zzawk();
                                                zzjn = zzaui().zzjn(com_google_android_gms_internal_zzccw_zza.zziue.zzci);
                                                if (zzjn == null) {
                                                }
                                                if (TextUtils.isEmpty(com_google_android_gms_internal_zzccw_zza.zziue.zzilu)) {
                                                    com_google_android_gms_internal_zzcgk.zzjad = Long.valueOf(-1);
                                                } else {
                                                    zzaul().zzayf().zzj("Did not find measurement config or missing version info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                                }
                                                zzauf().zza(com_google_android_gms_internal_zzcgk, z);
                                            }
                                            zzauf().zzae(com_google_android_gms_internal_zzccw_zza.zziuf);
                                            zzauf2 = zzauf();
                                            zzauf2.getWritableDatabase().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                                            zzauf().setTransactionSuccessful();
                                            if (com_google_android_gms_internal_zzcgk.zzizf.length <= 0) {
                                            }
                                            zzauf().endTransaction();
                                            return z4;
                                        }
                                        zzauf().setTransactionSuccessful();
                                        zzauf().endTransaction();
                                        return false;
                                    }
                                } catch (IOException e2) {
                                    try {
                                        zzauf.zzaul().zzayd().zze("Data loss. Failed to merge raw event. appId", zzcbw.zzjf(str6), e2);
                                    } catch (SQLiteException e3) {
                                        obj = e3;
                                        str2 = str6;
                                    }
                                }
                            } while (cursor.moveToNext());
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (com_google_android_gms_internal_zzccw_zza.zzaoc != null) {
                            }
                            if (obj != null) {
                                zzauf().setTransactionSuccessful();
                                zzauf().endTransaction();
                                return false;
                            }
                            z = false;
                            com_google_android_gms_internal_zzcgk = com_google_android_gms_internal_zzccw_zza.zziue;
                            com_google_android_gms_internal_zzcgk.zzizf = new zzcgh[com_google_android_gms_internal_zzccw_zza.zzaoc.size()];
                            i3 = 0;
                            i = 0;
                            while (i < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                                if (zzaui().zzao(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name)) {
                                    zzap = zzaui().zzap(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name);
                                    if (zzap) {
                                        zzauh();
                                    }
                                    obj2 = null;
                                    obj3 = null;
                                    if (((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx == null) {
                                        ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = new zzcgi[0];
                                    }
                                    com_google_android_gms_internal_zzcgiArr = ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx;
                                    length2 = com_google_android_gms_internal_zzcgiArr.length;
                                    i4 = 0;
                                    while (i4 < length2) {
                                        com_google_android_gms_internal_zzcgi = com_google_android_gms_internal_zzcgiArr[i4];
                                        if (!"_c".equals(com_google_android_gms_internal_zzcgi.name)) {
                                            com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                            obj2 = 1;
                                            obj = obj3;
                                        } else if ("_r".equals(com_google_android_gms_internal_zzcgi.name)) {
                                            obj = obj3;
                                        } else {
                                            com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                            obj = 1;
                                        }
                                        i4++;
                                        obj3 = obj;
                                    }
                                    zzaul().zzayj().zzj("Marking event as conversion", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                    com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                                    com_google_android_gms_internal_zzcgi2 = new zzcgi();
                                    com_google_android_gms_internal_zzcgi2.name = "_c";
                                    com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                                    com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                                    ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                                    if (obj3 == null) {
                                        zzaul().zzayj().zzj("Marking event as real-time", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                        com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                                        com_google_android_gms_internal_zzcgi2 = new zzcgi();
                                        com_google_android_gms_internal_zzcgi2.name = "_r";
                                        com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                                        com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                                        ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                                    }
                                    if (zzauf().zza(zzazf(), com_google_android_gms_internal_zzccw_zza.zziue.zzci, false, false, false, false, true).zzinc <= ((long) this.zziss.zzis(com_google_android_gms_internal_zzccw_zza.zziue.zzci))) {
                                        z = true;
                                    } else {
                                        com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                        i2 = 0;
                                        while (i2 < com_google_android_gms_internal_zzcgh.zziyx.length) {
                                            if ("_r".equals(com_google_android_gms_internal_zzcgh.zziyx[i2].name)) {
                                                i2++;
                                            } else {
                                                obj3 = new zzcgi[(com_google_android_gms_internal_zzcgh.zziyx.length - 1)];
                                                if (i2 > 0) {
                                                    System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, 0, obj3, 0, i2);
                                                }
                                                if (i2 < obj3.length) {
                                                    System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, i2 + 1, obj3, i2, obj3.length - i2);
                                                }
                                                com_google_android_gms_internal_zzcgh.zziyx = obj3;
                                            }
                                        }
                                    }
                                    zzaul().zzayf().zzj("Too many conversions. Not logging as conversion. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                    com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                    obj4 = null;
                                    com_google_android_gms_internal_zzcgi3 = null;
                                    com_google_android_gms_internal_zzcgiArr3 = com_google_android_gms_internal_zzcgh.zziyx;
                                    length = com_google_android_gms_internal_zzcgiArr3.length;
                                    i5 = 0;
                                    while (i5 < length) {
                                        com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgiArr3[i5];
                                        if (!"_c".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                            obj3 = obj4;
                                        } else if ("_err".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                            com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi3;
                                            obj3 = obj4;
                                        } else {
                                            com_google_android_gms_internal_zzcgi4 = com_google_android_gms_internal_zzcgi3;
                                            i6 = 1;
                                            com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi4;
                                        }
                                        i5++;
                                        obj4 = obj3;
                                        com_google_android_gms_internal_zzcgi3 = com_google_android_gms_internal_zzcgi2;
                                    }
                                    if (obj4 == null) {
                                    }
                                    if (com_google_android_gms_internal_zzcgi3 == null) {
                                        zzaul().zzayd().zzj("Did not find conversion parameter. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                        z2 = z;
                                        i2 = i3 + 1;
                                        com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                        i7 = i2;
                                        z3 = z2;
                                    } else {
                                        com_google_android_gms_internal_zzcgi3.name = "_err";
                                        com_google_android_gms_internal_zzcgi3.zzizb = Long.valueOf(10);
                                        z2 = z;
                                        i2 = i3 + 1;
                                        com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                        i7 = i2;
                                        z3 = z2;
                                    }
                                } else {
                                    zzaul().zzayf().zze("Dropping blacklisted raw event. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci), zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                    if (zzauh().zzkg(com_google_android_gms_internal_zzccw_zza.zziue.zzci)) {
                                    }
                                    if (obj == null) {
                                    }
                                    i7 = i3;
                                    z3 = z;
                                }
                                i++;
                                i3 = i7;
                                z = z3;
                            }
                            if (i3 < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                                com_google_android_gms_internal_zzcgk.zzizf = (zzcgh[]) Arrays.copyOf(com_google_android_gms_internal_zzcgk.zzizf, i3);
                            }
                            com_google_android_gms_internal_zzcgk.zzizy = zza(com_google_android_gms_internal_zzccw_zza.zziue.zzci, com_google_android_gms_internal_zzccw_zza.zziue.zzizg, com_google_android_gms_internal_zzcgk.zzizf);
                            com_google_android_gms_internal_zzcgk.zzizi = Long.valueOf(Long.MAX_VALUE);
                            com_google_android_gms_internal_zzcgk.zzizj = Long.valueOf(Long.MIN_VALUE);
                            for (zzcgh com_google_android_gms_internal_zzcgh2222 : com_google_android_gms_internal_zzcgk.zzizf) {
                                if (com_google_android_gms_internal_zzcgh2222.zziyy.longValue() < com_google_android_gms_internal_zzcgk.zzizi.longValue()) {
                                    com_google_android_gms_internal_zzcgk.zzizi = com_google_android_gms_internal_zzcgh2222.zziyy;
                                }
                                if (com_google_android_gms_internal_zzcgh2222.zziyy.longValue() <= com_google_android_gms_internal_zzcgk.zzizj.longValue()) {
                                    com_google_android_gms_internal_zzcgk.zzizj = com_google_android_gms_internal_zzcgh2222.zziyy;
                                }
                            }
                            str3 = com_google_android_gms_internal_zzccw_zza.zziue.zzci;
                            zziw = zzauf().zziw(str3);
                            if (zziw != null) {
                                zzaul().zzayd().zzj("Bundling raw events w/o app info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                            } else if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                                zzaus = zziw.zzaus();
                                if (zzaus == 0) {
                                }
                                com_google_android_gms_internal_zzcgk.zzizl = zzaus == 0 ? Long.valueOf(zzaus) : null;
                                zzaur = zziw.zzaur();
                                if (zzaur != 0) {
                                    zzaus = zzaur;
                                }
                                if (zzaus == 0) {
                                }
                                com_google_android_gms_internal_zzcgk.zzizk = zzaus == 0 ? Long.valueOf(zzaus) : null;
                                zziw.zzavb();
                                com_google_android_gms_internal_zzcgk.zzizw = Integer.valueOf((int) zziw.zzauy());
                                zziw.zzal(com_google_android_gms_internal_zzcgk.zzizi.longValue());
                                zziw.zzam(com_google_android_gms_internal_zzcgk.zzizj.longValue());
                                com_google_android_gms_internal_zzcgk.zzily = zziw.zzavj();
                                zzauf().zza(zziw);
                            }
                            if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                                zzcax.zzawk();
                                zzjn = zzaui().zzjn(com_google_android_gms_internal_zzccw_zza.zziue.zzci);
                                if (zzjn == null) {
                                }
                                if (TextUtils.isEmpty(com_google_android_gms_internal_zzccw_zza.zziue.zzilu)) {
                                    zzaul().zzayf().zzj("Did not find measurement config or missing version info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                } else {
                                    com_google_android_gms_internal_zzcgk.zzjad = Long.valueOf(-1);
                                }
                                zzauf().zza(com_google_android_gms_internal_zzcgk, z);
                            }
                            zzauf().zzae(com_google_android_gms_internal_zzccw_zza.zziuf);
                            zzauf2 = zzauf();
                            zzauf2.getWritableDatabase().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                            zzauf().setTransactionSuccessful();
                            if (com_google_android_gms_internal_zzcgk.zzizf.length <= 0) {
                            }
                            zzauf().endTransaction();
                            return z4;
                        }
                        zzauf.zzaul().zzayf().zzj("Raw event data disappeared while in transaction. appId", zzcbw.zzjf(str6));
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (com_google_android_gms_internal_zzccw_zza.zzaoc != null) {
                        }
                        if (obj != null) {
                            zzauf().setTransactionSuccessful();
                            zzauf().endTransaction();
                            return false;
                        }
                        z = false;
                        com_google_android_gms_internal_zzcgk = com_google_android_gms_internal_zzccw_zza.zziue;
                        com_google_android_gms_internal_zzcgk.zzizf = new zzcgh[com_google_android_gms_internal_zzccw_zza.zzaoc.size()];
                        i3 = 0;
                        i = 0;
                        while (i < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                            if (zzaui().zzao(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name)) {
                                zzap = zzaui().zzap(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name);
                                if (zzap) {
                                    zzauh();
                                }
                                obj2 = null;
                                obj3 = null;
                                if (((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx == null) {
                                    ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = new zzcgi[0];
                                }
                                com_google_android_gms_internal_zzcgiArr = ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx;
                                length2 = com_google_android_gms_internal_zzcgiArr.length;
                                i4 = 0;
                                while (i4 < length2) {
                                    com_google_android_gms_internal_zzcgi = com_google_android_gms_internal_zzcgiArr[i4];
                                    if (!"_c".equals(com_google_android_gms_internal_zzcgi.name)) {
                                        com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                        obj2 = 1;
                                        obj = obj3;
                                    } else if ("_r".equals(com_google_android_gms_internal_zzcgi.name)) {
                                        obj = obj3;
                                    } else {
                                        com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                        obj = 1;
                                    }
                                    i4++;
                                    obj3 = obj;
                                }
                                zzaul().zzayj().zzj("Marking event as conversion", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                                com_google_android_gms_internal_zzcgi2 = new zzcgi();
                                com_google_android_gms_internal_zzcgi2.name = "_c";
                                com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                                com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                                ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                                if (obj3 == null) {
                                    zzaul().zzayj().zzj("Marking event as real-time", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                    com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                                    com_google_android_gms_internal_zzcgi2 = new zzcgi();
                                    com_google_android_gms_internal_zzcgi2.name = "_r";
                                    com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                                    com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                                    ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                                }
                                if (zzauf().zza(zzazf(), com_google_android_gms_internal_zzccw_zza.zziue.zzci, false, false, false, false, true).zzinc <= ((long) this.zziss.zzis(com_google_android_gms_internal_zzccw_zza.zziue.zzci))) {
                                    z = true;
                                } else {
                                    com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                    i2 = 0;
                                    while (i2 < com_google_android_gms_internal_zzcgh.zziyx.length) {
                                        if ("_r".equals(com_google_android_gms_internal_zzcgh.zziyx[i2].name)) {
                                            i2++;
                                        } else {
                                            obj3 = new zzcgi[(com_google_android_gms_internal_zzcgh.zziyx.length - 1)];
                                            if (i2 > 0) {
                                                System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, 0, obj3, 0, i2);
                                            }
                                            if (i2 < obj3.length) {
                                                System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, i2 + 1, obj3, i2, obj3.length - i2);
                                            }
                                            com_google_android_gms_internal_zzcgh.zziyx = obj3;
                                        }
                                    }
                                }
                                zzaul().zzayf().zzj("Too many conversions. Not logging as conversion. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                obj4 = null;
                                com_google_android_gms_internal_zzcgi3 = null;
                                com_google_android_gms_internal_zzcgiArr3 = com_google_android_gms_internal_zzcgh.zziyx;
                                length = com_google_android_gms_internal_zzcgiArr3.length;
                                i5 = 0;
                                while (i5 < length) {
                                    com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgiArr3[i5];
                                    if (!"_c".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                        obj3 = obj4;
                                    } else if ("_err".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                        com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi3;
                                        obj3 = obj4;
                                    } else {
                                        com_google_android_gms_internal_zzcgi4 = com_google_android_gms_internal_zzcgi3;
                                        i6 = 1;
                                        com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi4;
                                    }
                                    i5++;
                                    obj4 = obj3;
                                    com_google_android_gms_internal_zzcgi3 = com_google_android_gms_internal_zzcgi2;
                                }
                                if (obj4 == null) {
                                }
                                if (com_google_android_gms_internal_zzcgi3 == null) {
                                    zzaul().zzayd().zzj("Did not find conversion parameter. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                    z2 = z;
                                    i2 = i3 + 1;
                                    com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                    i7 = i2;
                                    z3 = z2;
                                } else {
                                    com_google_android_gms_internal_zzcgi3.name = "_err";
                                    com_google_android_gms_internal_zzcgi3.zzizb = Long.valueOf(10);
                                    z2 = z;
                                    i2 = i3 + 1;
                                    com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                    i7 = i2;
                                    z3 = z2;
                                }
                            } else {
                                zzaul().zzayf().zze("Dropping blacklisted raw event. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci), zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                if (zzauh().zzkg(com_google_android_gms_internal_zzccw_zza.zziue.zzci)) {
                                }
                                if (obj == null) {
                                }
                                i7 = i3;
                                z3 = z;
                            }
                            i++;
                            i3 = i7;
                            z = z3;
                        }
                        if (i3 < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                            com_google_android_gms_internal_zzcgk.zzizf = (zzcgh[]) Arrays.copyOf(com_google_android_gms_internal_zzcgk.zzizf, i3);
                        }
                        com_google_android_gms_internal_zzcgk.zzizy = zza(com_google_android_gms_internal_zzccw_zza.zziue.zzci, com_google_android_gms_internal_zzccw_zza.zziue.zzizg, com_google_android_gms_internal_zzcgk.zzizf);
                        com_google_android_gms_internal_zzcgk.zzizi = Long.valueOf(Long.MAX_VALUE);
                        com_google_android_gms_internal_zzcgk.zzizj = Long.valueOf(Long.MIN_VALUE);
                        for (zzcgh com_google_android_gms_internal_zzcgh22222 : com_google_android_gms_internal_zzcgk.zzizf) {
                            if (com_google_android_gms_internal_zzcgh22222.zziyy.longValue() < com_google_android_gms_internal_zzcgk.zzizi.longValue()) {
                                com_google_android_gms_internal_zzcgk.zzizi = com_google_android_gms_internal_zzcgh22222.zziyy;
                            }
                            if (com_google_android_gms_internal_zzcgh22222.zziyy.longValue() <= com_google_android_gms_internal_zzcgk.zzizj.longValue()) {
                                com_google_android_gms_internal_zzcgk.zzizj = com_google_android_gms_internal_zzcgh22222.zziyy;
                            }
                        }
                        str3 = com_google_android_gms_internal_zzccw_zza.zziue.zzci;
                        zziw = zzauf().zziw(str3);
                        if (zziw != null) {
                            zzaul().zzayd().zzj("Bundling raw events w/o app info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                        } else if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                            zzaus = zziw.zzaus();
                            if (zzaus == 0) {
                            }
                            com_google_android_gms_internal_zzcgk.zzizl = zzaus == 0 ? Long.valueOf(zzaus) : null;
                            zzaur = zziw.zzaur();
                            if (zzaur != 0) {
                                zzaus = zzaur;
                            }
                            if (zzaus == 0) {
                            }
                            com_google_android_gms_internal_zzcgk.zzizk = zzaus == 0 ? Long.valueOf(zzaus) : null;
                            zziw.zzavb();
                            com_google_android_gms_internal_zzcgk.zzizw = Integer.valueOf((int) zziw.zzauy());
                            zziw.zzal(com_google_android_gms_internal_zzcgk.zzizi.longValue());
                            zziw.zzam(com_google_android_gms_internal_zzcgk.zzizj.longValue());
                            com_google_android_gms_internal_zzcgk.zzily = zziw.zzavj();
                            zzauf().zza(zziw);
                        }
                        if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                            zzcax.zzawk();
                            zzjn = zzaui().zzjn(com_google_android_gms_internal_zzccw_zza.zziue.zzci);
                            if (zzjn == null) {
                            }
                            if (TextUtils.isEmpty(com_google_android_gms_internal_zzccw_zza.zziue.zzilu)) {
                                zzaul().zzayf().zzj("Did not find measurement config or missing version info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                            } else {
                                com_google_android_gms_internal_zzcgk.zzjad = Long.valueOf(-1);
                            }
                            zzauf().zza(com_google_android_gms_internal_zzcgk, z);
                        }
                        zzauf().zzae(com_google_android_gms_internal_zzccw_zza.zziuf);
                        zzauf2 = zzauf();
                        zzauf2.getWritableDatabase().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                        zzauf().setTransactionSuccessful();
                        if (com_google_android_gms_internal_zzcgk.zzizf.length <= 0) {
                        }
                        zzauf().endTransaction();
                        return z4;
                    } catch (IOException e22) {
                        zzauf.zzaul().zzayd().zze("Data loss. Failed to merge raw event metadata. appId", zzcbw.zzjf(str6), e22);
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                    } catch (Throwable th2) {
                        zzauf().endTransaction();
                    }
                } else {
                    zzauf.zzaul().zzayd().zzj("Raw event metadata record is missing. appId", zzcbw.zzjf(str6));
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (com_google_android_gms_internal_zzccw_zza.zzaoc != null) {
                    }
                    if (obj != null) {
                        z = false;
                        com_google_android_gms_internal_zzcgk = com_google_android_gms_internal_zzccw_zza.zziue;
                        com_google_android_gms_internal_zzcgk.zzizf = new zzcgh[com_google_android_gms_internal_zzccw_zza.zzaoc.size()];
                        i3 = 0;
                        i = 0;
                        while (i < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                            if (zzaui().zzao(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name)) {
                                zzaul().zzayf().zze("Dropping blacklisted raw event. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci), zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                if (zzauh().zzkg(com_google_android_gms_internal_zzccw_zza.zziue.zzci)) {
                                }
                                if (obj == null) {
                                }
                                i7 = i3;
                                z3 = z;
                            } else {
                                zzap = zzaui().zzap(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name);
                                if (zzap) {
                                    zzauh();
                                }
                                obj2 = null;
                                obj3 = null;
                                if (((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx == null) {
                                    ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = new zzcgi[0];
                                }
                                com_google_android_gms_internal_zzcgiArr = ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx;
                                length2 = com_google_android_gms_internal_zzcgiArr.length;
                                i4 = 0;
                                while (i4 < length2) {
                                    com_google_android_gms_internal_zzcgi = com_google_android_gms_internal_zzcgiArr[i4];
                                    if (!"_c".equals(com_google_android_gms_internal_zzcgi.name)) {
                                        com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                        obj2 = 1;
                                        obj = obj3;
                                    } else if ("_r".equals(com_google_android_gms_internal_zzcgi.name)) {
                                        com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                        obj = 1;
                                    } else {
                                        obj = obj3;
                                    }
                                    i4++;
                                    obj3 = obj;
                                }
                                zzaul().zzayj().zzj("Marking event as conversion", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                                com_google_android_gms_internal_zzcgi2 = new zzcgi();
                                com_google_android_gms_internal_zzcgi2.name = "_c";
                                com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                                com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                                ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                                if (obj3 == null) {
                                    zzaul().zzayj().zzj("Marking event as real-time", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                    com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                                    com_google_android_gms_internal_zzcgi2 = new zzcgi();
                                    com_google_android_gms_internal_zzcgi2.name = "_r";
                                    com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                                    com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                                    ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                                }
                                if (zzauf().zza(zzazf(), com_google_android_gms_internal_zzccw_zza.zziue.zzci, false, false, false, false, true).zzinc <= ((long) this.zziss.zzis(com_google_android_gms_internal_zzccw_zza.zziue.zzci))) {
                                    com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                    i2 = 0;
                                    while (i2 < com_google_android_gms_internal_zzcgh.zziyx.length) {
                                        if ("_r".equals(com_google_android_gms_internal_zzcgh.zziyx[i2].name)) {
                                            obj3 = new zzcgi[(com_google_android_gms_internal_zzcgh.zziyx.length - 1)];
                                            if (i2 > 0) {
                                                System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, 0, obj3, 0, i2);
                                            }
                                            if (i2 < obj3.length) {
                                                System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, i2 + 1, obj3, i2, obj3.length - i2);
                                            }
                                            com_google_android_gms_internal_zzcgh.zziyx = obj3;
                                        } else {
                                            i2++;
                                        }
                                    }
                                } else {
                                    z = true;
                                }
                                zzaul().zzayf().zzj("Too many conversions. Not logging as conversion. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                obj4 = null;
                                com_google_android_gms_internal_zzcgi3 = null;
                                com_google_android_gms_internal_zzcgiArr3 = com_google_android_gms_internal_zzcgh.zziyx;
                                length = com_google_android_gms_internal_zzcgiArr3.length;
                                i5 = 0;
                                while (i5 < length) {
                                    com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgiArr3[i5];
                                    if (!"_c".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                        obj3 = obj4;
                                    } else if ("_err".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                        com_google_android_gms_internal_zzcgi4 = com_google_android_gms_internal_zzcgi3;
                                        i6 = 1;
                                        com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi4;
                                    } else {
                                        com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi3;
                                        obj3 = obj4;
                                    }
                                    i5++;
                                    obj4 = obj3;
                                    com_google_android_gms_internal_zzcgi3 = com_google_android_gms_internal_zzcgi2;
                                }
                                if (obj4 == null) {
                                }
                                if (com_google_android_gms_internal_zzcgi3 == null) {
                                    com_google_android_gms_internal_zzcgi3.name = "_err";
                                    com_google_android_gms_internal_zzcgi3.zzizb = Long.valueOf(10);
                                    z2 = z;
                                    i2 = i3 + 1;
                                    com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                    i7 = i2;
                                    z3 = z2;
                                } else {
                                    zzaul().zzayd().zzj("Did not find conversion parameter. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                    z2 = z;
                                    i2 = i3 + 1;
                                    com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                    i7 = i2;
                                    z3 = z2;
                                }
                            }
                            i++;
                            i3 = i7;
                            z = z3;
                        }
                        if (i3 < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                            com_google_android_gms_internal_zzcgk.zzizf = (zzcgh[]) Arrays.copyOf(com_google_android_gms_internal_zzcgk.zzizf, i3);
                        }
                        com_google_android_gms_internal_zzcgk.zzizy = zza(com_google_android_gms_internal_zzccw_zza.zziue.zzci, com_google_android_gms_internal_zzccw_zza.zziue.zzizg, com_google_android_gms_internal_zzcgk.zzizf);
                        com_google_android_gms_internal_zzcgk.zzizi = Long.valueOf(Long.MAX_VALUE);
                        com_google_android_gms_internal_zzcgk.zzizj = Long.valueOf(Long.MIN_VALUE);
                        for (zzcgh com_google_android_gms_internal_zzcgh222222 : com_google_android_gms_internal_zzcgk.zzizf) {
                            if (com_google_android_gms_internal_zzcgh222222.zziyy.longValue() < com_google_android_gms_internal_zzcgk.zzizi.longValue()) {
                                com_google_android_gms_internal_zzcgk.zzizi = com_google_android_gms_internal_zzcgh222222.zziyy;
                            }
                            if (com_google_android_gms_internal_zzcgh222222.zziyy.longValue() <= com_google_android_gms_internal_zzcgk.zzizj.longValue()) {
                                com_google_android_gms_internal_zzcgk.zzizj = com_google_android_gms_internal_zzcgh222222.zziyy;
                            }
                        }
                        str3 = com_google_android_gms_internal_zzccw_zza.zziue.zzci;
                        zziw = zzauf().zziw(str3);
                        if (zziw != null) {
                            zzaul().zzayd().zzj("Bundling raw events w/o app info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                        } else if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                            zzaus = zziw.zzaus();
                            if (zzaus == 0) {
                            }
                            com_google_android_gms_internal_zzcgk.zzizl = zzaus == 0 ? Long.valueOf(zzaus) : null;
                            zzaur = zziw.zzaur();
                            if (zzaur != 0) {
                                zzaus = zzaur;
                            }
                            if (zzaus == 0) {
                            }
                            com_google_android_gms_internal_zzcgk.zzizk = zzaus == 0 ? Long.valueOf(zzaus) : null;
                            zziw.zzavb();
                            com_google_android_gms_internal_zzcgk.zzizw = Integer.valueOf((int) zziw.zzauy());
                            zziw.zzal(com_google_android_gms_internal_zzcgk.zzizi.longValue());
                            zziw.zzam(com_google_android_gms_internal_zzcgk.zzizj.longValue());
                            com_google_android_gms_internal_zzcgk.zzily = zziw.zzavj();
                            zzauf().zza(zziw);
                        }
                        if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                            zzcax.zzawk();
                            zzjn = zzaui().zzjn(com_google_android_gms_internal_zzccw_zza.zziue.zzci);
                            if (zzjn == null) {
                            }
                            if (TextUtils.isEmpty(com_google_android_gms_internal_zzccw_zza.zziue.zzilu)) {
                                com_google_android_gms_internal_zzcgk.zzjad = Long.valueOf(-1);
                            } else {
                                zzaul().zzayf().zzj("Did not find measurement config or missing version info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                            }
                            zzauf().zza(com_google_android_gms_internal_zzcgk, z);
                        }
                        zzauf().zzae(com_google_android_gms_internal_zzccw_zza.zziuf);
                        zzauf2 = zzauf();
                        zzauf2.getWritableDatabase().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                        zzauf().setTransactionSuccessful();
                        if (com_google_android_gms_internal_zzcgk.zzizf.length <= 0) {
                        }
                        zzauf().endTransaction();
                        return z4;
                    }
                    zzauf().setTransactionSuccessful();
                    zzauf().endTransaction();
                    return false;
                }
            } catch (SQLiteException e4) {
                obj = e4;
                cursor = cursor2;
                str2 = str6;
                try {
                    zzauf.zzaul().zzayd().zze("Data loss. Error selecting raw event. appId", zzcbw.zzjf(str2), obj);
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (com_google_android_gms_internal_zzccw_zza.zzaoc != null) {
                    }
                    if (obj != null) {
                        zzauf().setTransactionSuccessful();
                        zzauf().endTransaction();
                        return false;
                    }
                    z = false;
                    com_google_android_gms_internal_zzcgk = com_google_android_gms_internal_zzccw_zza.zziue;
                    com_google_android_gms_internal_zzcgk.zzizf = new zzcgh[com_google_android_gms_internal_zzccw_zza.zzaoc.size()];
                    i3 = 0;
                    i = 0;
                    while (i < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                        if (zzaui().zzao(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name)) {
                            zzap = zzaui().zzap(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name);
                            if (zzap) {
                                zzauh();
                            }
                            obj2 = null;
                            obj3 = null;
                            if (((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx == null) {
                                ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = new zzcgi[0];
                            }
                            com_google_android_gms_internal_zzcgiArr = ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx;
                            length2 = com_google_android_gms_internal_zzcgiArr.length;
                            i4 = 0;
                            while (i4 < length2) {
                                com_google_android_gms_internal_zzcgi = com_google_android_gms_internal_zzcgiArr[i4];
                                if (!"_c".equals(com_google_android_gms_internal_zzcgi.name)) {
                                    com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                    obj2 = 1;
                                    obj = obj3;
                                } else if ("_r".equals(com_google_android_gms_internal_zzcgi.name)) {
                                    obj = obj3;
                                } else {
                                    com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                    obj = 1;
                                }
                                i4++;
                                obj3 = obj;
                            }
                            zzaul().zzayj().zzj("Marking event as conversion", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                            com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                            com_google_android_gms_internal_zzcgi2 = new zzcgi();
                            com_google_android_gms_internal_zzcgi2.name = "_c";
                            com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                            com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                            ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                            if (obj3 == null) {
                                zzaul().zzayj().zzj("Marking event as real-time", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                                com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                                com_google_android_gms_internal_zzcgi2 = new zzcgi();
                                com_google_android_gms_internal_zzcgi2.name = "_r";
                                com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                                com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                                ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                            }
                            if (zzauf().zza(zzazf(), com_google_android_gms_internal_zzccw_zza.zziue.zzci, false, false, false, false, true).zzinc <= ((long) this.zziss.zzis(com_google_android_gms_internal_zzccw_zza.zziue.zzci))) {
                                z = true;
                            } else {
                                com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                i2 = 0;
                                while (i2 < com_google_android_gms_internal_zzcgh.zziyx.length) {
                                    if ("_r".equals(com_google_android_gms_internal_zzcgh.zziyx[i2].name)) {
                                        i2++;
                                    } else {
                                        obj3 = new zzcgi[(com_google_android_gms_internal_zzcgh.zziyx.length - 1)];
                                        if (i2 > 0) {
                                            System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, 0, obj3, 0, i2);
                                        }
                                        if (i2 < obj3.length) {
                                            System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, i2 + 1, obj3, i2, obj3.length - i2);
                                        }
                                        com_google_android_gms_internal_zzcgh.zziyx = obj3;
                                    }
                                }
                            }
                            zzaul().zzayf().zzj("Too many conversions. Not logging as conversion. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                            com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                            obj4 = null;
                            com_google_android_gms_internal_zzcgi3 = null;
                            com_google_android_gms_internal_zzcgiArr3 = com_google_android_gms_internal_zzcgh.zziyx;
                            length = com_google_android_gms_internal_zzcgiArr3.length;
                            i5 = 0;
                            while (i5 < length) {
                                com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgiArr3[i5];
                                if (!"_c".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                    obj3 = obj4;
                                } else if ("_err".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                    com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi3;
                                    obj3 = obj4;
                                } else {
                                    com_google_android_gms_internal_zzcgi4 = com_google_android_gms_internal_zzcgi3;
                                    i6 = 1;
                                    com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi4;
                                }
                                i5++;
                                obj4 = obj3;
                                com_google_android_gms_internal_zzcgi3 = com_google_android_gms_internal_zzcgi2;
                            }
                            if (obj4 == null) {
                            }
                            if (com_google_android_gms_internal_zzcgi3 == null) {
                                zzaul().zzayd().zzj("Did not find conversion parameter. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                                z2 = z;
                                i2 = i3 + 1;
                                com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                i7 = i2;
                                z3 = z2;
                            } else {
                                com_google_android_gms_internal_zzcgi3.name = "_err";
                                com_google_android_gms_internal_zzcgi3.zzizb = Long.valueOf(10);
                                z2 = z;
                                i2 = i3 + 1;
                                com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                                i7 = i2;
                                z3 = z2;
                            }
                        } else {
                            zzaul().zzayf().zze("Dropping blacklisted raw event. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci), zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                            if (zzauh().zzkg(com_google_android_gms_internal_zzccw_zza.zziue.zzci)) {
                            }
                            if (obj == null) {
                            }
                            i7 = i3;
                            z3 = z;
                        }
                        i++;
                        i3 = i7;
                        z = z3;
                    }
                    if (i3 < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                        com_google_android_gms_internal_zzcgk.zzizf = (zzcgh[]) Arrays.copyOf(com_google_android_gms_internal_zzcgk.zzizf, i3);
                    }
                    com_google_android_gms_internal_zzcgk.zzizy = zza(com_google_android_gms_internal_zzccw_zza.zziue.zzci, com_google_android_gms_internal_zzccw_zza.zziue.zzizg, com_google_android_gms_internal_zzcgk.zzizf);
                    com_google_android_gms_internal_zzcgk.zzizi = Long.valueOf(Long.MAX_VALUE);
                    com_google_android_gms_internal_zzcgk.zzizj = Long.valueOf(Long.MIN_VALUE);
                    for (zzcgh com_google_android_gms_internal_zzcgh2222222 : com_google_android_gms_internal_zzcgk.zzizf) {
                        if (com_google_android_gms_internal_zzcgh2222222.zziyy.longValue() < com_google_android_gms_internal_zzcgk.zzizi.longValue()) {
                            com_google_android_gms_internal_zzcgk.zzizi = com_google_android_gms_internal_zzcgh2222222.zziyy;
                        }
                        if (com_google_android_gms_internal_zzcgh2222222.zziyy.longValue() <= com_google_android_gms_internal_zzcgk.zzizj.longValue()) {
                            com_google_android_gms_internal_zzcgk.zzizj = com_google_android_gms_internal_zzcgh2222222.zziyy;
                        }
                    }
                    str3 = com_google_android_gms_internal_zzccw_zza.zziue.zzci;
                    zziw = zzauf().zziw(str3);
                    if (zziw != null) {
                        zzaul().zzayd().zzj("Bundling raw events w/o app info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                    } else if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                        zzaus = zziw.zzaus();
                        if (zzaus == 0) {
                        }
                        com_google_android_gms_internal_zzcgk.zzizl = zzaus == 0 ? Long.valueOf(zzaus) : null;
                        zzaur = zziw.zzaur();
                        if (zzaur != 0) {
                            zzaus = zzaur;
                        }
                        if (zzaus == 0) {
                        }
                        com_google_android_gms_internal_zzcgk.zzizk = zzaus == 0 ? Long.valueOf(zzaus) : null;
                        zziw.zzavb();
                        com_google_android_gms_internal_zzcgk.zzizw = Integer.valueOf((int) zziw.zzauy());
                        zziw.zzal(com_google_android_gms_internal_zzcgk.zzizi.longValue());
                        zziw.zzam(com_google_android_gms_internal_zzcgk.zzizj.longValue());
                        com_google_android_gms_internal_zzcgk.zzily = zziw.zzavj();
                        zzauf().zza(zziw);
                    }
                    if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                        zzcax.zzawk();
                        zzjn = zzaui().zzjn(com_google_android_gms_internal_zzccw_zza.zziue.zzci);
                        if (zzjn == null) {
                        }
                        if (TextUtils.isEmpty(com_google_android_gms_internal_zzccw_zza.zziue.zzilu)) {
                            zzaul().zzayf().zzj("Did not find measurement config or missing version info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                        } else {
                            com_google_android_gms_internal_zzcgk.zzjad = Long.valueOf(-1);
                        }
                        zzauf().zza(com_google_android_gms_internal_zzcgk, z);
                    }
                    zzauf().zzae(com_google_android_gms_internal_zzccw_zza.zziuf);
                    zzauf2 = zzauf();
                    zzauf2.getWritableDatabase().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                    zzauf().setTransactionSuccessful();
                    if (com_google_android_gms_internal_zzcgk.zzizf.length <= 0) {
                    }
                    zzauf().endTransaction();
                    return z4;
                } catch (Throwable th3) {
                    th = th3;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                cursor = cursor2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e5) {
            obj = e5;
            zzauf.zzaul().zzayd().zze("Data loss. Error selecting raw event. appId", zzcbw.zzjf(str2), obj);
            if (cursor != null) {
                cursor.close();
            }
            if (com_google_android_gms_internal_zzccw_zza.zzaoc != null) {
            }
            if (obj != null) {
                z = false;
                com_google_android_gms_internal_zzcgk = com_google_android_gms_internal_zzccw_zza.zziue;
                com_google_android_gms_internal_zzcgk.zzizf = new zzcgh[com_google_android_gms_internal_zzccw_zza.zzaoc.size()];
                i3 = 0;
                i = 0;
                while (i < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                    if (zzaui().zzao(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name)) {
                        zzaul().zzayf().zze("Dropping blacklisted raw event. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci), zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                        if (zzauh().zzkg(com_google_android_gms_internal_zzccw_zza.zziue.zzci)) {
                        }
                        if (obj == null) {
                        }
                        i7 = i3;
                        z3 = z;
                    } else {
                        zzap = zzaui().zzap(com_google_android_gms_internal_zzccw_zza.zziue.zzci, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name);
                        if (zzap) {
                            zzauh();
                        }
                        obj2 = null;
                        obj3 = null;
                        if (((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx == null) {
                            ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = new zzcgi[0];
                        }
                        com_google_android_gms_internal_zzcgiArr = ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx;
                        length2 = com_google_android_gms_internal_zzcgiArr.length;
                        i4 = 0;
                        while (i4 < length2) {
                            com_google_android_gms_internal_zzcgi = com_google_android_gms_internal_zzcgiArr[i4];
                            if (!"_c".equals(com_google_android_gms_internal_zzcgi.name)) {
                                com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                obj2 = 1;
                                obj = obj3;
                            } else if ("_r".equals(com_google_android_gms_internal_zzcgi.name)) {
                                com_google_android_gms_internal_zzcgi.zzizb = Long.valueOf(1);
                                obj = 1;
                            } else {
                                obj = obj3;
                            }
                            i4++;
                            obj3 = obj;
                        }
                        zzaul().zzayj().zzj("Marking event as conversion", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                        com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                        com_google_android_gms_internal_zzcgi2 = new zzcgi();
                        com_google_android_gms_internal_zzcgi2.name = "_c";
                        com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                        com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                        ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                        if (obj3 == null) {
                            zzaul().zzayj().zzj("Marking event as real-time", zzaug().zzjc(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).name));
                            com_google_android_gms_internal_zzcgiArr2 = (zzcgi[]) Arrays.copyOf(((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx, ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx.length + 1);
                            com_google_android_gms_internal_zzcgi2 = new zzcgi();
                            com_google_android_gms_internal_zzcgi2.name = "_r";
                            com_google_android_gms_internal_zzcgi2.zzizb = Long.valueOf(1);
                            com_google_android_gms_internal_zzcgiArr2[com_google_android_gms_internal_zzcgiArr2.length - 1] = com_google_android_gms_internal_zzcgi2;
                            ((zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i)).zziyx = com_google_android_gms_internal_zzcgiArr2;
                        }
                        if (zzauf().zza(zzazf(), com_google_android_gms_internal_zzccw_zza.zziue.zzci, false, false, false, false, true).zzinc <= ((long) this.zziss.zzis(com_google_android_gms_internal_zzccw_zza.zziue.zzci))) {
                            com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                            i2 = 0;
                            while (i2 < com_google_android_gms_internal_zzcgh.zziyx.length) {
                                if ("_r".equals(com_google_android_gms_internal_zzcgh.zziyx[i2].name)) {
                                    obj3 = new zzcgi[(com_google_android_gms_internal_zzcgh.zziyx.length - 1)];
                                    if (i2 > 0) {
                                        System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, 0, obj3, 0, i2);
                                    }
                                    if (i2 < obj3.length) {
                                        System.arraycopy(com_google_android_gms_internal_zzcgh.zziyx, i2 + 1, obj3, i2, obj3.length - i2);
                                    }
                                    com_google_android_gms_internal_zzcgh.zziyx = obj3;
                                } else {
                                    i2++;
                                }
                            }
                        } else {
                            z = true;
                        }
                        zzaul().zzayf().zzj("Too many conversions. Not logging as conversion. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                        com_google_android_gms_internal_zzcgh = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                        obj4 = null;
                        com_google_android_gms_internal_zzcgi3 = null;
                        com_google_android_gms_internal_zzcgiArr3 = com_google_android_gms_internal_zzcgh.zziyx;
                        length = com_google_android_gms_internal_zzcgiArr3.length;
                        i5 = 0;
                        while (i5 < length) {
                            com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgiArr3[i5];
                            if (!"_c".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                obj3 = obj4;
                            } else if ("_err".equals(com_google_android_gms_internal_zzcgi2.name)) {
                                com_google_android_gms_internal_zzcgi4 = com_google_android_gms_internal_zzcgi3;
                                i6 = 1;
                                com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi4;
                            } else {
                                com_google_android_gms_internal_zzcgi2 = com_google_android_gms_internal_zzcgi3;
                                obj3 = obj4;
                            }
                            i5++;
                            obj4 = obj3;
                            com_google_android_gms_internal_zzcgi3 = com_google_android_gms_internal_zzcgi2;
                        }
                        if (obj4 == null) {
                        }
                        if (com_google_android_gms_internal_zzcgi3 == null) {
                            com_google_android_gms_internal_zzcgi3.name = "_err";
                            com_google_android_gms_internal_zzcgi3.zzizb = Long.valueOf(10);
                            z2 = z;
                            i2 = i3 + 1;
                            com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                            i7 = i2;
                            z3 = z2;
                        } else {
                            zzaul().zzayd().zzj("Did not find conversion parameter. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                            z2 = z;
                            i2 = i3 + 1;
                            com_google_android_gms_internal_zzcgk.zzizf[i3] = (zzcgh) com_google_android_gms_internal_zzccw_zza.zzaoc.get(i);
                            i7 = i2;
                            z3 = z2;
                        }
                    }
                    i++;
                    i3 = i7;
                    z = z3;
                }
                if (i3 < com_google_android_gms_internal_zzccw_zza.zzaoc.size()) {
                    com_google_android_gms_internal_zzcgk.zzizf = (zzcgh[]) Arrays.copyOf(com_google_android_gms_internal_zzcgk.zzizf, i3);
                }
                com_google_android_gms_internal_zzcgk.zzizy = zza(com_google_android_gms_internal_zzccw_zza.zziue.zzci, com_google_android_gms_internal_zzccw_zza.zziue.zzizg, com_google_android_gms_internal_zzcgk.zzizf);
                com_google_android_gms_internal_zzcgk.zzizi = Long.valueOf(Long.MAX_VALUE);
                com_google_android_gms_internal_zzcgk.zzizj = Long.valueOf(Long.MIN_VALUE);
                for (zzcgh com_google_android_gms_internal_zzcgh22222222 : com_google_android_gms_internal_zzcgk.zzizf) {
                    if (com_google_android_gms_internal_zzcgh22222222.zziyy.longValue() < com_google_android_gms_internal_zzcgk.zzizi.longValue()) {
                        com_google_android_gms_internal_zzcgk.zzizi = com_google_android_gms_internal_zzcgh22222222.zziyy;
                    }
                    if (com_google_android_gms_internal_zzcgh22222222.zziyy.longValue() <= com_google_android_gms_internal_zzcgk.zzizj.longValue()) {
                        com_google_android_gms_internal_zzcgk.zzizj = com_google_android_gms_internal_zzcgh22222222.zziyy;
                    }
                }
                str3 = com_google_android_gms_internal_zzccw_zza.zziue.zzci;
                zziw = zzauf().zziw(str3);
                if (zziw != null) {
                    zzaul().zzayd().zzj("Bundling raw events w/o app info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                } else if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                    zzaus = zziw.zzaus();
                    if (zzaus == 0) {
                    }
                    com_google_android_gms_internal_zzcgk.zzizl = zzaus == 0 ? Long.valueOf(zzaus) : null;
                    zzaur = zziw.zzaur();
                    if (zzaur != 0) {
                        zzaus = zzaur;
                    }
                    if (zzaus == 0) {
                    }
                    com_google_android_gms_internal_zzcgk.zzizk = zzaus == 0 ? Long.valueOf(zzaus) : null;
                    zziw.zzavb();
                    com_google_android_gms_internal_zzcgk.zzizw = Integer.valueOf((int) zziw.zzauy());
                    zziw.zzal(com_google_android_gms_internal_zzcgk.zzizi.longValue());
                    zziw.zzam(com_google_android_gms_internal_zzcgk.zzizj.longValue());
                    com_google_android_gms_internal_zzcgk.zzily = zziw.zzavj();
                    zzauf().zza(zziw);
                }
                if (com_google_android_gms_internal_zzcgk.zzizf.length > 0) {
                    zzcax.zzawk();
                    zzjn = zzaui().zzjn(com_google_android_gms_internal_zzccw_zza.zziue.zzci);
                    if (zzjn == null) {
                    }
                    if (TextUtils.isEmpty(com_google_android_gms_internal_zzccw_zza.zziue.zzilu)) {
                        com_google_android_gms_internal_zzcgk.zzjad = Long.valueOf(-1);
                    } else {
                        zzaul().zzayf().zzj("Did not find measurement config or missing version info. appId", zzcbw.zzjf(com_google_android_gms_internal_zzccw_zza.zziue.zzci));
                    }
                    zzauf().zza(com_google_android_gms_internal_zzcgk, z);
                }
                zzauf().zzae(com_google_android_gms_internal_zzccw_zza.zziuf);
                zzauf2 = zzauf();
                zzauf2.getWritableDatabase().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                zzauf().setTransactionSuccessful();
                if (com_google_android_gms_internal_zzcgk.zzizf.length <= 0) {
                }
                zzauf().endTransaction();
                return z4;
            }
            zzauf().setTransactionSuccessful();
            zzauf().endTransaction();
            return false;
        }
    }

    private final zzcas zzjr(String str) {
        zzcar zziw = zzauf().zziw(str);
        if (zziw == null || TextUtils.isEmpty(zziw.zzuo())) {
            zzaul().zzayi().zzj("No app data available; dropping", str);
            return null;
        }
        try {
            String str2 = zzbed.zzcr(this.mContext).getPackageInfo(str, 0).versionName;
            if (!(zziw.zzuo() == null || zziw.zzuo().equals(str2))) {
                zzaul().zzayf().zzj("App version does not match; dropping. appId", zzcbw.zzjf(str));
                return null;
            }
        } catch (NameNotFoundException e) {
        }
        return new zzcas(str, zziw.getGmpAppId(), zziw.zzuo(), zziw.zzaut(), zziw.zzauu(), zziw.zzauv(), zziw.zzauw(), null, zziw.zzaux(), false, zziw.zzauq(), zziw.zzavk(), 0, 0);
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final boolean isEnabled() {
        boolean z = false;
        zzauk().zzuj();
        zzwk();
        if (this.zziss.zzawl()) {
            return false;
        }
        Boolean zzit = this.zziss.zzit("firebase_analytics_collection_enabled");
        if (zzit != null) {
            z = zzit.booleanValue();
        } else if (!zzcax.zzaif()) {
            z = true;
        }
        return zzaum().zzbn(z);
    }

    protected final void start() {
        zzauk().zzuj();
        zzauf().zzaxk();
        if (zzaum().zziqp.get() == 0) {
            zzaum().zziqp.set(this.zzasc.currentTimeMillis());
        }
        if (Long.valueOf(zzaum().zziqu.get()).longValue() == 0) {
            zzaul().zzayj().zzj("Persisting first open", Long.valueOf(this.zziuc));
            zzaum().zziqu.set(this.zziuc);
        }
        if (zzayv()) {
            zzcax.zzawk();
            if (!TextUtils.isEmpty(zzaua().getGmpAppId())) {
                String zzaym = zzaum().zzaym();
                if (zzaym == null) {
                    zzaum().zzjj(zzaua().getGmpAppId());
                } else if (!zzaym.equals(zzaua().getGmpAppId())) {
                    zzaul().zzayh().log("Rechecking which service to use due to a GMP App Id change");
                    zzaum().zzayp();
                    this.zzitg.disconnect();
                    this.zzitg.zzxh();
                    zzaum().zzjj(zzaua().getGmpAppId());
                    zzaum().zziqu.set(this.zziuc);
                    zzaum().zziqv.zzjl(null);
                }
            }
            zzatz().zzjk(zzaum().zziqv.zzayr());
            zzcax.zzawk();
            if (!TextUtils.isEmpty(zzaua().getGmpAppId())) {
                zzcdt zzatz = zzatz();
                zzatz.zzuj();
                zzatz.zzatv();
                zzatz.zzwk();
                if (zzatz.zziki.zzayv()) {
                    zzatz.zzauc().zzazq();
                    String zzayq = zzatz.zzaum().zzayq();
                    if (!TextUtils.isEmpty(zzayq)) {
                        zzatz.zzaub().zzwk();
                        if (!zzayq.equals(VERSION.RELEASE)) {
                            Bundle bundle = new Bundle();
                            bundle.putString("_po", zzayq);
                            zzatz.zzc("auto", "_ou", bundle);
                        }
                    }
                }
                zzauc().zza(new AtomicReference());
            }
        } else if (isEnabled()) {
            if (!zzauh().zzdt("android.permission.INTERNET")) {
                zzaul().zzayd().log("App is missing INTERNET permission");
            }
            if (!zzauh().zzdt("android.permission.ACCESS_NETWORK_STATE")) {
                zzaul().zzayd().log("App is missing ACCESS_NETWORK_STATE permission");
            }
            zzcax.zzawk();
            if (!zzbed.zzcr(this.mContext).zzalq()) {
                if (!zzccn.zzj(this.mContext, false)) {
                    zzaul().zzayd().log("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzcfh.zzk(this.mContext, false)) {
                    zzaul().zzayd().log("AppMeasurementService not registered/enabled");
                }
            }
            zzaul().zzayd().log("Uploading is not possible. App measurement disabled");
        }
        zzazi();
    }

    protected final void zza(int i, Throwable th, byte[] bArr) {
        zzauk().zzuj();
        zzwk();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zziua = false;
                zzazm();
            }
        }
        List<Long> list = this.zzitt;
        this.zzitt = null;
        if ((i == Callback.DEFAULT_DRAG_ANIMATION_DURATION || i == 204) && th == null) {
            try {
                zzaum().zziqp.set(this.zzasc.currentTimeMillis());
                zzaum().zziqq.set(0);
                zzazi();
                zzaul().zzayj().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzauf().beginTransaction();
                zzcdt zzauf;
                try {
                    for (Long l : list) {
                        zzauf = zzauf();
                        long longValue = l.longValue();
                        zzauf.zzuj();
                        zzauf.zzwk();
                        if (zzauf.getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                            throw new SQLiteException("Deleted fewer rows from queue than expected");
                        }
                    }
                    zzauf().setTransactionSuccessful();
                    zzauf().endTransaction();
                    if (zzaza().zzyx() && zzazh()) {
                        zzazg();
                    } else {
                        this.zzitx = -1;
                        zzazi();
                    }
                    this.zzity = 0;
                } catch (SQLiteException e) {
                    zzauf.zzaul().zzayd().zzj("Failed to delete a bundle in a queue table", e);
                    throw e;
                } catch (Throwable th3) {
                    zzauf().endTransaction();
                }
            } catch (SQLiteException e2) {
                zzaul().zzayd().zzj("Database error while trying to delete uploaded bundles", e2);
                this.zzity = this.zzasc.elapsedRealtime();
                zzaul().zzayj().zzj("Disable upload, time", Long.valueOf(this.zzity));
            }
        } else {
            zzaul().zzayj().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            zzaum().zziqq.set(this.zzasc.currentTimeMillis());
            boolean z = i == 503 || i == 429;
            if (z) {
                zzaum().zziqr.set(this.zzasc.currentTimeMillis());
            }
            zzazi();
        }
        this.zziua = false;
        zzazm();
    }

    public final byte[] zza(zzcbk com_google_android_gms_internal_zzcbk, String str) {
        zzwk();
        zzauk().zzuj();
        zzatu();
        zzbp.zzu(com_google_android_gms_internal_zzcbk);
        zzbp.zzgg(str);
        zzeyn com_google_android_gms_internal_zzcgj = new zzcgj();
        zzauf().beginTransaction();
        try {
            zzcar zziw = zzauf().zziw(str);
            byte[] bArr;
            if (zziw == null) {
                zzaul().zzayi().zzj("Log and bundle not available. package_name", str);
                bArr = new byte[0];
                return bArr;
            } else if (zziw.zzaux()) {
                long j;
                zzcgk com_google_android_gms_internal_zzcgk = new zzcgk();
                com_google_android_gms_internal_zzcgj.zzizc = new zzcgk[]{com_google_android_gms_internal_zzcgk};
                com_google_android_gms_internal_zzcgk.zzize = Integer.valueOf(1);
                com_google_android_gms_internal_zzcgk.zzizm = "android";
                com_google_android_gms_internal_zzcgk.zzci = zziw.getAppId();
                com_google_android_gms_internal_zzcgk.zzilv = zziw.zzauu();
                com_google_android_gms_internal_zzcgk.zzhtt = zziw.zzuo();
                long zzaut = zziw.zzaut();
                com_google_android_gms_internal_zzcgk.zzizz = zzaut == -2147483648L ? null : Integer.valueOf((int) zzaut);
                com_google_android_gms_internal_zzcgk.zzizq = Long.valueOf(zziw.zzauv());
                com_google_android_gms_internal_zzcgk.zzilu = zziw.getGmpAppId();
                com_google_android_gms_internal_zzcgk.zzizv = Long.valueOf(zziw.zzauw());
                if (isEnabled() && zzcax.zzaxh() && this.zziss.zziu(com_google_android_gms_internal_zzcgk.zzci)) {
                    zzaua();
                    com_google_android_gms_internal_zzcgk.zzjaf = null;
                }
                Pair zzjh = zzaum().zzjh(zziw.getAppId());
                if (!(zzjh == null || TextUtils.isEmpty((CharSequence) zzjh.first))) {
                    com_google_android_gms_internal_zzcgk.zzizs = (String) zzjh.first;
                    com_google_android_gms_internal_zzcgk.zzizt = (Boolean) zzjh.second;
                }
                zzaub().zzwk();
                com_google_android_gms_internal_zzcgk.zzizn = Build.MODEL;
                zzaub().zzwk();
                com_google_android_gms_internal_zzcgk.zzcw = VERSION.RELEASE;
                com_google_android_gms_internal_zzcgk.zzizp = Integer.valueOf((int) zzaub().zzaxw());
                com_google_android_gms_internal_zzcgk.zzizo = zzaub().zzaxx();
                com_google_android_gms_internal_zzcgk.zzizu = zziw.getAppInstanceId();
                com_google_android_gms_internal_zzcgk.zzimc = zziw.zzauq();
                List zziv = zzauf().zziv(zziw.getAppId());
                com_google_android_gms_internal_zzcgk.zzizg = new zzcgm[zziv.size()];
                for (int i = 0; i < zziv.size(); i++) {
                    zzcgm com_google_android_gms_internal_zzcgm = new zzcgm();
                    com_google_android_gms_internal_zzcgk.zzizg[i] = com_google_android_gms_internal_zzcgm;
                    com_google_android_gms_internal_zzcgm.name = ((zzcfv) zziv.get(i)).mName;
                    com_google_android_gms_internal_zzcgm.zzjaj = Long.valueOf(((zzcfv) zziv.get(i)).zzixd);
                    zzauh().zza(com_google_android_gms_internal_zzcgm, ((zzcfv) zziv.get(i)).mValue);
                }
                Bundle zzaxz = com_google_android_gms_internal_zzcbk.zzinr.zzaxz();
                if ("_iap".equals(com_google_android_gms_internal_zzcbk.name)) {
                    zzaxz.putLong("_c", 1);
                    zzaul().zzayi().log("Marking in-app purchase as real-time");
                    zzaxz.putLong("_r", 1);
                }
                zzaxz.putString("_o", com_google_android_gms_internal_zzcbk.zzimg);
                if (zzauh().zzke(com_google_android_gms_internal_zzcgk.zzci)) {
                    zzauh().zza(zzaxz, "_dbg", Long.valueOf(1));
                    zzauh().zza(zzaxz, "_r", Long.valueOf(1));
                }
                zzcbg zzaf = zzauf().zzaf(str, com_google_android_gms_internal_zzcbk.name);
                if (zzaf == null) {
                    zzauf().zza(new zzcbg(str, com_google_android_gms_internal_zzcbk.name, 1, 0, com_google_android_gms_internal_zzcbk.zzins));
                    j = 0;
                } else {
                    j = zzaf.zzinn;
                    zzauf().zza(zzaf.zzbb(com_google_android_gms_internal_zzcbk.zzins).zzaxy());
                }
                zzcbf com_google_android_gms_internal_zzcbf = new zzcbf(this, com_google_android_gms_internal_zzcbk.zzimg, str, com_google_android_gms_internal_zzcbk.name, com_google_android_gms_internal_zzcbk.zzins, j, zzaxz);
                zzcgh com_google_android_gms_internal_zzcgh = new zzcgh();
                com_google_android_gms_internal_zzcgk.zzizf = new zzcgh[]{com_google_android_gms_internal_zzcgh};
                com_google_android_gms_internal_zzcgh.zziyy = Long.valueOf(com_google_android_gms_internal_zzcbf.zzfdc);
                com_google_android_gms_internal_zzcgh.name = com_google_android_gms_internal_zzcbf.mName;
                com_google_android_gms_internal_zzcgh.zziyz = Long.valueOf(com_google_android_gms_internal_zzcbf.zzinj);
                com_google_android_gms_internal_zzcgh.zziyx = new zzcgi[com_google_android_gms_internal_zzcbf.zzink.size()];
                Iterator it = com_google_android_gms_internal_zzcbf.zzink.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String str2 = (String) it.next();
                    zzcgi com_google_android_gms_internal_zzcgi = new zzcgi();
                    int i3 = i2 + 1;
                    com_google_android_gms_internal_zzcgh.zziyx[i2] = com_google_android_gms_internal_zzcgi;
                    com_google_android_gms_internal_zzcgi.name = str2;
                    zzauh().zza(com_google_android_gms_internal_zzcgi, com_google_android_gms_internal_zzcbf.zzink.get(str2));
                    i2 = i3;
                }
                com_google_android_gms_internal_zzcgk.zzizy = zza(zziw.getAppId(), com_google_android_gms_internal_zzcgk.zzizg, com_google_android_gms_internal_zzcgk.zzizf);
                com_google_android_gms_internal_zzcgk.zzizi = com_google_android_gms_internal_zzcgh.zziyy;
                com_google_android_gms_internal_zzcgk.zzizj = com_google_android_gms_internal_zzcgh.zziyy;
                zzaut = zziw.zzaus();
                com_google_android_gms_internal_zzcgk.zzizl = zzaut != 0 ? Long.valueOf(zzaut) : null;
                long zzaur = zziw.zzaur();
                if (zzaur != 0) {
                    zzaut = zzaur;
                }
                com_google_android_gms_internal_zzcgk.zzizk = zzaut != 0 ? Long.valueOf(zzaut) : null;
                zziw.zzavb();
                com_google_android_gms_internal_zzcgk.zzizw = Integer.valueOf((int) zziw.zzauy());
                com_google_android_gms_internal_zzcgk.zzizr = Long.valueOf(zzcax.zzauv());
                com_google_android_gms_internal_zzcgk.zzizh = Long.valueOf(this.zzasc.currentTimeMillis());
                com_google_android_gms_internal_zzcgk.zzizx = Boolean.TRUE;
                zziw.zzal(com_google_android_gms_internal_zzcgk.zzizi.longValue());
                zziw.zzam(com_google_android_gms_internal_zzcgk.zzizj.longValue());
                zzauf().zza(zziw);
                zzauf().setTransactionSuccessful();
                zzauf().endTransaction();
                try {
                    bArr = new byte[com_google_android_gms_internal_zzcgj.zzhi()];
                    zzeyf zzn = zzeyf.zzn(bArr, 0, bArr.length);
                    com_google_android_gms_internal_zzcgj.zza(zzn);
                    zzn.zzctn();
                    return zzauh().zzp(bArr);
                } catch (IOException e) {
                    zzaul().zzayd().zze("Data loss. Failed to bundle and serialize. appId", zzcbw.zzjf(str), e);
                    return null;
                }
            } else {
                zzaul().zzayi().zzj("Log and bundle disabled. package_name", str);
                bArr = new byte[0];
                zzauf().endTransaction();
                return bArr;
            }
        } finally {
            zzauf().endTransaction();
        }
    }

    public final zzcan zzatx() {
        zza(this.zzitn);
        return this.zzitn;
    }

    public final zzcau zzaty() {
        zza(this.zzitm);
        return this.zzitm;
    }

    public final zzcdw zzatz() {
        zza(this.zziti);
        return this.zziti;
    }

    public final zzcbr zzaua() {
        zza(this.zzitj);
        return this.zzitj;
    }

    public final zzcbe zzaub() {
        zza(this.zzith);
        return this.zzith;
    }

    public final zzceo zzauc() {
        zza(this.zzitg);
        return this.zzitg;
    }

    public final zzcek zzaud() {
        zza(this.zzitf);
        return this.zzitf;
    }

    public final zzcbs zzaue() {
        zza(this.zzitd);
        return this.zzitd;
    }

    public final zzcay zzauf() {
        zza(this.zzitc);
        return this.zzitc;
    }

    public final zzcbu zzaug() {
        zza(this.zzitb);
        return this.zzitb;
    }

    public final zzcfw zzauh() {
        zza(this.zzita);
        return this.zzita;
    }

    public final zzccq zzaui() {
        zza(this.zzisx);
        return this.zzisx;
    }

    public final zzcfl zzauj() {
        zza(this.zzisw);
        return this.zzisw;
    }

    public final zzccr zzauk() {
        zza(this.zzisv);
        return this.zzisv;
    }

    public final zzcbw zzaul() {
        zza(this.zzisu);
        return this.zzisu;
    }

    public final zzcch zzaum() {
        zza(this.zzist);
        return this.zzist;
    }

    public final zzcax zzaun() {
        return this.zziss;
    }

    protected final boolean zzayv() {
        boolean z = false;
        zzwk();
        zzauk().zzuj();
        if (this.zzitp == null || this.zzitq == 0 || !(this.zzitp == null || this.zzitp.booleanValue() || Math.abs(this.zzasc.elapsedRealtime() - this.zzitq) <= 1000)) {
            this.zzitq = this.zzasc.elapsedRealtime();
            zzcax.zzawk();
            if (zzauh().zzdt("android.permission.INTERNET") && zzauh().zzdt("android.permission.ACCESS_NETWORK_STATE") && (zzbed.zzcr(this.mContext).zzalq() || (zzccn.zzj(this.mContext, false) && zzcfh.zzk(this.mContext, false)))) {
                z = true;
            }
            this.zzitp = Boolean.valueOf(z);
            if (this.zzitp.booleanValue()) {
                this.zzitp = Boolean.valueOf(zzauh().zzkb(zzaua().getGmpAppId()));
            }
        }
        return this.zzitp.booleanValue();
    }

    public final zzcbw zzayw() {
        return (this.zzisu == null || !this.zzisu.isInitialized()) ? null : this.zzisu;
    }

    final zzccr zzayx() {
        return this.zzisv;
    }

    public final AppMeasurement zzayy() {
        return this.zzisy;
    }

    public final FirebaseAnalytics zzayz() {
        return this.zzisz;
    }

    public final zzcca zzaza() {
        zza(this.zzite);
        return this.zzite;
    }

    final long zzaze() {
        Long valueOf = Long.valueOf(zzaum().zziqu.get());
        return valueOf.longValue() == 0 ? this.zziuc : Math.min(this.zziuc, valueOf.longValue());
    }

    public final void zzazg() {
        boolean z = true;
        zzauk().zzuj();
        zzwk();
        this.zziub = true;
        String zzaxi;
        String zzawu;
        try {
            zzcax.zzawk();
            Boolean zzayo = zzaum().zzayo();
            if (zzayo == null) {
                zzaul().zzayf().log("Upload data called on the client side before use of service was decided");
                this.zziub = false;
                zzazm();
            } else if (zzayo.booleanValue()) {
                zzaul().zzayd().log("Upload called in the client side when service should be used");
                this.zziub = false;
                zzazm();
            } else if (this.zzity > 0) {
                zzazi();
                this.zziub = false;
                zzazm();
            } else {
                zzauk().zzuj();
                if (this.zzitt != null) {
                    zzaul().zzayj().log("Uploading requested multiple times");
                    this.zziub = false;
                    zzazm();
                } else if (zzaza().zzyx()) {
                    long currentTimeMillis = this.zzasc.currentTimeMillis();
                    zzg(null, currentTimeMillis - zzcax.zzawv());
                    long j = zzaum().zziqp.get();
                    if (j != 0) {
                        zzaul().zzayi().zzj("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - j)));
                    }
                    zzaxi = zzauf().zzaxi();
                    Object zzba;
                    if (TextUtils.isEmpty(zzaxi)) {
                        this.zzitx = -1;
                        zzba = zzauf().zzba(currentTimeMillis - zzcax.zzawv());
                        if (!TextUtils.isEmpty(zzba)) {
                            zzcar zziw = zzauf().zziw(zzba);
                            if (zziw != null) {
                                zzb(zziw);
                            }
                        }
                    } else {
                        if (this.zzitx == -1) {
                            this.zzitx = zzauf().zzaxp();
                        }
                        List<Pair> zzl = zzauf().zzl(zzaxi, this.zziss.zzb(zzaxi, zzcbm.zzioh), Math.max(0, this.zziss.zzb(zzaxi, zzcbm.zzioi)));
                        if (!zzl.isEmpty()) {
                            zzcgk com_google_android_gms_internal_zzcgk;
                            Object obj;
                            int i;
                            List subList;
                            for (Pair pair : zzl) {
                                com_google_android_gms_internal_zzcgk = (zzcgk) pair.first;
                                if (!TextUtils.isEmpty(com_google_android_gms_internal_zzcgk.zzizs)) {
                                    obj = com_google_android_gms_internal_zzcgk.zzizs;
                                    break;
                                }
                            }
                            obj = null;
                            if (obj != null) {
                                for (i = 0; i < zzl.size(); i++) {
                                    com_google_android_gms_internal_zzcgk = (zzcgk) ((Pair) zzl.get(i)).first;
                                    if (!TextUtils.isEmpty(com_google_android_gms_internal_zzcgk.zzizs) && !com_google_android_gms_internal_zzcgk.zzizs.equals(obj)) {
                                        subList = zzl.subList(0, i);
                                        break;
                                    }
                                }
                            }
                            subList = zzl;
                            zzcgj com_google_android_gms_internal_zzcgj = new zzcgj();
                            com_google_android_gms_internal_zzcgj.zzizc = new zzcgk[subList.size()];
                            Collection arrayList = new ArrayList(subList.size());
                            boolean z2 = zzcax.zzaxh() && this.zziss.zziu(zzaxi);
                            for (i = 0; i < com_google_android_gms_internal_zzcgj.zzizc.length; i++) {
                                com_google_android_gms_internal_zzcgj.zzizc[i] = (zzcgk) ((Pair) subList.get(i)).first;
                                arrayList.add((Long) ((Pair) subList.get(i)).second);
                                com_google_android_gms_internal_zzcgj.zzizc[i].zzizr = Long.valueOf(zzcax.zzauv());
                                com_google_android_gms_internal_zzcgj.zzizc[i].zzizh = Long.valueOf(currentTimeMillis);
                                com_google_android_gms_internal_zzcgj.zzizc[i].zzizx = Boolean.valueOf(zzcax.zzawk());
                                if (!z2) {
                                    com_google_android_gms_internal_zzcgj.zzizc[i].zzjaf = null;
                                }
                            }
                            Object zza = zzaul().zzad(2) ? zzaug().zza(com_google_android_gms_internal_zzcgj) : null;
                            Object zzb = zzauh().zzb(com_google_android_gms_internal_zzcgj);
                            zzawu = zzcax.zzawu();
                            URL url = new URL(zzawu);
                            if (arrayList.isEmpty()) {
                                z = false;
                            }
                            zzbp.zzbh(z);
                            if (this.zzitt != null) {
                                zzaul().zzayd().log("Set uploading progress before finishing the previous upload");
                            } else {
                                this.zzitt = new ArrayList(arrayList);
                            }
                            zzaum().zziqq.set(currentTimeMillis);
                            zzba = "?";
                            if (com_google_android_gms_internal_zzcgj.zzizc.length > 0) {
                                zzba = com_google_android_gms_internal_zzcgj.zzizc[0].zzci;
                            }
                            zzaul().zzayj().zzd("Uploading data. app, uncompressed size, data", zzba, Integer.valueOf(zzb.length), zza);
                            this.zziua = true;
                            zzcdt zzaza = zzaza();
                            zzccc com_google_android_gms_internal_zzccz = new zzccz(this);
                            zzaza.zzuj();
                            zzaza.zzwk();
                            zzbp.zzu(url);
                            zzbp.zzu(zzb);
                            zzbp.zzu(com_google_android_gms_internal_zzccz);
                            zzaza.zzauk().zzh(new zzcce(zzaza, zzaxi, url, zzb, null, com_google_android_gms_internal_zzccz));
                        }
                    }
                    this.zziub = false;
                    zzazm();
                } else {
                    zzaul().zzayj().log("Network not connected, ignoring upload request");
                    zzazi();
                    this.zziub = false;
                    zzazm();
                }
            }
        } catch (MalformedURLException e) {
            zzaul().zzayd().zze("Failed to parse upload URL. Not uploading. appId", zzcbw.zzjf(zzaxi), zzawu);
        } catch (Throwable th) {
            this.zziub = false;
            zzazm();
        }
    }

    final void zzazj() {
        this.zzitw++;
    }

    final void zzazk() {
        zzauk().zzuj();
        zzwk();
        if (!this.zzito) {
            zzaul().zzayh().log("This instance being marked as an uploader");
            zzauk().zzuj();
            zzwk();
            if (zzazl() && zzazd()) {
                int zza = zza(this.zzits);
                int zzayb = zzaua().zzayb();
                zzauk().zzuj();
                if (zza > zzayb) {
                    zzaul().zzayd().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza), Integer.valueOf(zzayb));
                } else if (zza < zzayb) {
                    if (zza(zzayb, this.zzits)) {
                        zzaul().zzayj().zze("Storage version upgraded. Previous, current version", Integer.valueOf(zza), Integer.valueOf(zzayb));
                    } else {
                        zzaul().zzayd().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza), Integer.valueOf(zzayb));
                    }
                }
            }
            this.zzito = true;
            zzazi();
        }
    }

    final void zzb(zzcav com_google_android_gms_internal_zzcav, zzcas com_google_android_gms_internal_zzcas) {
        boolean z = true;
        zzbp.zzu(com_google_android_gms_internal_zzcav);
        zzbp.zzgg(com_google_android_gms_internal_zzcav.packageName);
        zzbp.zzu(com_google_android_gms_internal_zzcav.zzimg);
        zzbp.zzu(com_google_android_gms_internal_zzcav.zzimh);
        zzbp.zzgg(com_google_android_gms_internal_zzcav.zzimh.name);
        zzauk().zzuj();
        zzwk();
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzcas.zzilu)) {
            if (com_google_android_gms_internal_zzcas.zzilz) {
                zzcav com_google_android_gms_internal_zzcav2 = new zzcav(com_google_android_gms_internal_zzcav);
                com_google_android_gms_internal_zzcav2.zzimj = false;
                zzauf().beginTransaction();
                try {
                    zzcav zzai = zzauf().zzai(com_google_android_gms_internal_zzcav2.packageName, com_google_android_gms_internal_zzcav2.zzimh.name);
                    if (!(zzai == null || zzai.zzimg.equals(com_google_android_gms_internal_zzcav2.zzimg))) {
                        zzaul().zzayf().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", zzaug().zzje(com_google_android_gms_internal_zzcav2.zzimh.name), com_google_android_gms_internal_zzcav2.zzimg, zzai.zzimg);
                    }
                    if (zzai != null && zzai.zzimj) {
                        com_google_android_gms_internal_zzcav2.zzimg = zzai.zzimg;
                        com_google_android_gms_internal_zzcav2.zzimi = zzai.zzimi;
                        com_google_android_gms_internal_zzcav2.zzimm = zzai.zzimm;
                        com_google_android_gms_internal_zzcav2.zzimk = zzai.zzimk;
                        com_google_android_gms_internal_zzcav2.zzimn = zzai.zzimn;
                        com_google_android_gms_internal_zzcav2.zzimj = zzai.zzimj;
                        com_google_android_gms_internal_zzcav2.zzimh = new zzcft(com_google_android_gms_internal_zzcav2.zzimh.name, zzai.zzimh.zziwz, com_google_android_gms_internal_zzcav2.zzimh.getValue(), zzai.zzimh.zzimg);
                        z = false;
                    } else if (TextUtils.isEmpty(com_google_android_gms_internal_zzcav2.zzimk)) {
                        com_google_android_gms_internal_zzcav2.zzimh = new zzcft(com_google_android_gms_internal_zzcav2.zzimh.name, com_google_android_gms_internal_zzcav2.zzimi, com_google_android_gms_internal_zzcav2.zzimh.getValue(), com_google_android_gms_internal_zzcav2.zzimh.zzimg);
                        com_google_android_gms_internal_zzcav2.zzimj = true;
                    } else {
                        z = false;
                    }
                    if (com_google_android_gms_internal_zzcav2.zzimj) {
                        zzcft com_google_android_gms_internal_zzcft = com_google_android_gms_internal_zzcav2.zzimh;
                        zzcfv com_google_android_gms_internal_zzcfv = new zzcfv(com_google_android_gms_internal_zzcav2.packageName, com_google_android_gms_internal_zzcav2.zzimg, com_google_android_gms_internal_zzcft.name, com_google_android_gms_internal_zzcft.zziwz, com_google_android_gms_internal_zzcft.getValue());
                        if (zzauf().zza(com_google_android_gms_internal_zzcfv)) {
                            zzaul().zzayi().zzd("User property updated immediately", com_google_android_gms_internal_zzcav2.packageName, zzaug().zzje(com_google_android_gms_internal_zzcfv.mName), com_google_android_gms_internal_zzcfv.mValue);
                        } else {
                            zzaul().zzayd().zzd("(2)Too many active user properties, ignoring", zzcbw.zzjf(com_google_android_gms_internal_zzcav2.packageName), zzaug().zzje(com_google_android_gms_internal_zzcfv.mName), com_google_android_gms_internal_zzcfv.mValue);
                        }
                        if (z && com_google_android_gms_internal_zzcav2.zzimn != null) {
                            zzc(new zzcbk(com_google_android_gms_internal_zzcav2.zzimn, com_google_android_gms_internal_zzcav2.zzimi), com_google_android_gms_internal_zzcas);
                        }
                    }
                    if (zzauf().zza(com_google_android_gms_internal_zzcav2)) {
                        zzaul().zzayi().zzd("Conditional property added", com_google_android_gms_internal_zzcav2.packageName, zzaug().zzje(com_google_android_gms_internal_zzcav2.zzimh.name), com_google_android_gms_internal_zzcav2.zzimh.getValue());
                    } else {
                        zzaul().zzayd().zzd("Too many conditional properties, ignoring", zzcbw.zzjf(com_google_android_gms_internal_zzcav2.packageName), zzaug().zzje(com_google_android_gms_internal_zzcav2.zzimh.name), com_google_android_gms_internal_zzcav2.zzimh.getValue());
                    }
                    zzauf().setTransactionSuccessful();
                } finally {
                    zzauf().endTransaction();
                }
            } else {
                zzf(com_google_android_gms_internal_zzcas);
            }
        }
    }

    final void zzb(zzcbk com_google_android_gms_internal_zzcbk, zzcas com_google_android_gms_internal_zzcas) {
        zzbp.zzu(com_google_android_gms_internal_zzcas);
        zzbp.zzgg(com_google_android_gms_internal_zzcas.packageName);
        zzauk().zzuj();
        zzwk();
        String str = com_google_android_gms_internal_zzcas.packageName;
        long j = com_google_android_gms_internal_zzcbk.zzins;
        zzauh();
        if (!zzcfw.zzd(com_google_android_gms_internal_zzcbk, com_google_android_gms_internal_zzcas)) {
            return;
        }
        if (com_google_android_gms_internal_zzcas.zzilz) {
            zzauf().beginTransaction();
            try {
                List emptyList;
                Object obj;
                zzcdt zzauf = zzauf();
                zzbp.zzgg(str);
                zzauf.zzuj();
                zzauf.zzwk();
                if (j < 0) {
                    zzauf.zzaul().zzayf().zze("Invalid time querying timed out conditional properties", zzcbw.zzjf(str), Long.valueOf(j));
                    emptyList = Collections.emptyList();
                } else {
                    emptyList = zzauf.zzc("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
                }
                for (zzcav com_google_android_gms_internal_zzcav : r2) {
                    if (com_google_android_gms_internal_zzcav != null) {
                        zzaul().zzayi().zzd("User property timed out", com_google_android_gms_internal_zzcav.packageName, zzaug().zzje(com_google_android_gms_internal_zzcav.zzimh.name), com_google_android_gms_internal_zzcav.zzimh.getValue());
                        if (com_google_android_gms_internal_zzcav.zziml != null) {
                            zzc(new zzcbk(com_google_android_gms_internal_zzcav.zziml, j), com_google_android_gms_internal_zzcas);
                        }
                        zzauf().zzaj(str, com_google_android_gms_internal_zzcav.zzimh.name);
                    }
                }
                zzauf = zzauf();
                zzbp.zzgg(str);
                zzauf.zzuj();
                zzauf.zzwk();
                if (j < 0) {
                    zzauf.zzaul().zzayf().zze("Invalid time querying expired conditional properties", zzcbw.zzjf(str), Long.valueOf(j));
                    emptyList = Collections.emptyList();
                } else {
                    emptyList = zzauf.zzc("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
                }
                List arrayList = new ArrayList(r2.size());
                for (zzcav com_google_android_gms_internal_zzcav2 : r2) {
                    if (com_google_android_gms_internal_zzcav2 != null) {
                        zzaul().zzayi().zzd("User property expired", com_google_android_gms_internal_zzcav2.packageName, zzaug().zzje(com_google_android_gms_internal_zzcav2.zzimh.name), com_google_android_gms_internal_zzcav2.zzimh.getValue());
                        zzauf().zzag(str, com_google_android_gms_internal_zzcav2.zzimh.name);
                        if (com_google_android_gms_internal_zzcav2.zzimp != null) {
                            arrayList.add(com_google_android_gms_internal_zzcav2.zzimp);
                        }
                        zzauf().zzaj(str, com_google_android_gms_internal_zzcav2.zzimh.name);
                    }
                }
                ArrayList arrayList2 = (ArrayList) arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    obj = arrayList2.get(i);
                    i++;
                    zzc(new zzcbk((zzcbk) obj, j), com_google_android_gms_internal_zzcas);
                }
                zzauf = zzauf();
                String str2 = com_google_android_gms_internal_zzcbk.name;
                zzbp.zzgg(str);
                zzbp.zzgg(str2);
                zzauf.zzuj();
                zzauf.zzwk();
                if (j < 0) {
                    zzauf.zzaul().zzayf().zzd("Invalid time querying triggered conditional properties", zzcbw.zzjf(str), zzauf.zzaug().zzjc(str2), Long.valueOf(j));
                    emptyList = Collections.emptyList();
                } else {
                    emptyList = zzauf.zzc("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
                }
                List arrayList3 = new ArrayList(r2.size());
                for (zzcav com_google_android_gms_internal_zzcav3 : r2) {
                    if (com_google_android_gms_internal_zzcav3 != null) {
                        zzcft com_google_android_gms_internal_zzcft = com_google_android_gms_internal_zzcav3.zzimh;
                        zzcfv com_google_android_gms_internal_zzcfv = new zzcfv(com_google_android_gms_internal_zzcav3.packageName, com_google_android_gms_internal_zzcav3.zzimg, com_google_android_gms_internal_zzcft.name, j, com_google_android_gms_internal_zzcft.getValue());
                        if (zzauf().zza(com_google_android_gms_internal_zzcfv)) {
                            zzaul().zzayi().zzd("User property triggered", com_google_android_gms_internal_zzcav3.packageName, zzaug().zzje(com_google_android_gms_internal_zzcfv.mName), com_google_android_gms_internal_zzcfv.mValue);
                        } else {
                            zzaul().zzayd().zzd("Too many active user properties, ignoring", zzcbw.zzjf(com_google_android_gms_internal_zzcav3.packageName), zzaug().zzje(com_google_android_gms_internal_zzcfv.mName), com_google_android_gms_internal_zzcfv.mValue);
                        }
                        if (com_google_android_gms_internal_zzcav3.zzimn != null) {
                            arrayList3.add(com_google_android_gms_internal_zzcav3.zzimn);
                        }
                        com_google_android_gms_internal_zzcav3.zzimh = new zzcft(com_google_android_gms_internal_zzcfv);
                        com_google_android_gms_internal_zzcav3.zzimj = true;
                        zzauf().zza(com_google_android_gms_internal_zzcav3);
                    }
                }
                zzc(com_google_android_gms_internal_zzcbk, com_google_android_gms_internal_zzcas);
                arrayList2 = (ArrayList) arrayList3;
                int size2 = arrayList2.size();
                i = 0;
                while (i < size2) {
                    obj = arrayList2.get(i);
                    i++;
                    zzc(new zzcbk((zzcbk) obj, j), com_google_android_gms_internal_zzcas);
                }
                zzauf().setTransactionSuccessful();
            } finally {
                zzauf().endTransaction();
            }
        } else {
            zzf(com_google_android_gms_internal_zzcas);
        }
    }

    final void zzb(zzcbk com_google_android_gms_internal_zzcbk, String str) {
        zzcar zziw = zzauf().zziw(str);
        if (zziw == null || TextUtils.isEmpty(zziw.zzuo())) {
            zzaul().zzayi().zzj("No app data available; dropping event", str);
            return;
        }
        try {
            String str2 = zzbed.zzcr(this.mContext).getPackageInfo(str, 0).versionName;
            if (!(zziw.zzuo() == null || zziw.zzuo().equals(str2))) {
                zzaul().zzayf().zzj("App version does not match; dropping event. appId", zzcbw.zzjf(str));
                return;
            }
        } catch (NameNotFoundException e) {
            if (!"_ui".equals(com_google_android_gms_internal_zzcbk.name)) {
                zzaul().zzayf().zzj("Could not find package. appId", zzcbw.zzjf(str));
            }
        }
        zzcbk com_google_android_gms_internal_zzcbk2 = com_google_android_gms_internal_zzcbk;
        zzb(com_google_android_gms_internal_zzcbk2, new zzcas(str, zziw.getGmpAppId(), zziw.zzuo(), zziw.zzaut(), zziw.zzauu(), zziw.zzauv(), zziw.zzauw(), null, zziw.zzaux(), false, zziw.zzauq(), zziw.zzavk(), 0, 0));
    }

    final void zzb(zzcdu com_google_android_gms_internal_zzcdu) {
        this.zzitv++;
    }

    final void zzb(zzcft com_google_android_gms_internal_zzcft, zzcas com_google_android_gms_internal_zzcas) {
        int i = 0;
        zzauk().zzuj();
        zzwk();
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzcas.zzilu)) {
            if (com_google_android_gms_internal_zzcas.zzilz) {
                int zzjy = zzauh().zzjy(com_google_android_gms_internal_zzcft.name);
                String zza;
                if (zzjy != 0) {
                    zzauh();
                    zza = zzcfw.zza(com_google_android_gms_internal_zzcft.name, zzcax.zzavo(), true);
                    if (com_google_android_gms_internal_zzcft.name != null) {
                        i = com_google_android_gms_internal_zzcft.name.length();
                    }
                    zzauh().zza(com_google_android_gms_internal_zzcas.packageName, zzjy, "_ev", zza, i);
                    return;
                }
                zzjy = zzauh().zzl(com_google_android_gms_internal_zzcft.name, com_google_android_gms_internal_zzcft.getValue());
                if (zzjy != 0) {
                    zzauh();
                    zza = zzcfw.zza(com_google_android_gms_internal_zzcft.name, zzcax.zzavo(), true);
                    Object value = com_google_android_gms_internal_zzcft.getValue();
                    if (value != null && ((value instanceof String) || (value instanceof CharSequence))) {
                        i = String.valueOf(value).length();
                    }
                    zzauh().zza(com_google_android_gms_internal_zzcas.packageName, zzjy, "_ev", zza, i);
                    return;
                }
                Object zzm = zzauh().zzm(com_google_android_gms_internal_zzcft.name, com_google_android_gms_internal_zzcft.getValue());
                if (zzm != null) {
                    zzcfv com_google_android_gms_internal_zzcfv = new zzcfv(com_google_android_gms_internal_zzcas.packageName, com_google_android_gms_internal_zzcft.zzimg, com_google_android_gms_internal_zzcft.name, com_google_android_gms_internal_zzcft.zziwz, zzm);
                    zzaul().zzayi().zze("Setting user property", zzaug().zzje(com_google_android_gms_internal_zzcfv.mName), zzm);
                    zzauf().beginTransaction();
                    try {
                        zzf(com_google_android_gms_internal_zzcas);
                        boolean zza2 = zzauf().zza(com_google_android_gms_internal_zzcfv);
                        zzauf().setTransactionSuccessful();
                        if (zza2) {
                            zzaul().zzayi().zze("User property set", zzaug().zzje(com_google_android_gms_internal_zzcfv.mName), com_google_android_gms_internal_zzcfv.mValue);
                        } else {
                            zzaul().zzayd().zze("Too many unique user properties are set. Ignoring user property", zzaug().zzje(com_google_android_gms_internal_zzcfv.mName), com_google_android_gms_internal_zzcfv.mValue);
                            zzauh().zza(com_google_android_gms_internal_zzcas.packageName, 9, null, null, 0);
                        }
                        zzauf().endTransaction();
                        return;
                    } catch (Throwable th) {
                        zzauf().endTransaction();
                    }
                } else {
                    return;
                }
            }
            zzf(com_google_android_gms_internal_zzcas);
        }
    }

    final void zzb(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        boolean z = true;
        zzauk().zzuj();
        zzwk();
        zzbp.zzgg(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzitz = false;
                zzazm();
            }
        }
        zzaul().zzayj().zzj("onConfigFetched. Response size", Integer.valueOf(bArr.length));
        zzauf().beginTransaction();
        zzcar zziw = zzauf().zziw(str);
        boolean z2 = (i == Callback.DEFAULT_DRAG_ANIMATION_DURATION || i == 204 || i == 304) && th == null;
        if (zziw == null) {
            zzaul().zzayf().zzj("App does not exist in onConfigFetched. appId", zzcbw.zzjf(str));
        } else if (z2 || i == 404) {
            List list = map != null ? (List) map.get("Last-Modified") : null;
            String str2 = (list == null || list.size() <= 0) ? null : (String) list.get(0);
            if (i == 404 || i == 304) {
                if (zzaui().zzjn(str) == null && !zzaui().zzb(str, null, null)) {
                    zzauf().endTransaction();
                    this.zzitz = false;
                    zzazm();
                    return;
                }
            } else if (!zzaui().zzb(str, bArr, str2)) {
                zzauf().endTransaction();
                this.zzitz = false;
                zzazm();
                return;
            }
            zziw.zzar(this.zzasc.currentTimeMillis());
            zzauf().zza(zziw);
            if (i == 404) {
                zzaul().zzayg().zzj("Config not found. Using empty config. appId", str);
            } else {
                zzaul().zzayj().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
            }
            if (zzaza().zzyx() && zzazh()) {
                zzazg();
            } else {
                zzazi();
            }
        } else {
            zziw.zzas(this.zzasc.currentTimeMillis());
            zzauf().zza(zziw);
            zzaul().zzayj().zze("Fetching config failed. code, error", Integer.valueOf(i), th);
            zzaui().zzjp(str);
            zzaum().zziqq.set(this.zzasc.currentTimeMillis());
            if (!(i == 503 || i == 429)) {
                z = false;
            }
            if (z) {
                zzaum().zziqr.set(this.zzasc.currentTimeMillis());
            }
            zzazi();
        }
        zzauf().setTransactionSuccessful();
        zzauf().endTransaction();
        this.zzitz = false;
        zzazm();
    }

    public final void zzbo(boolean z) {
        zzazi();
    }

    final void zzc(zzcav com_google_android_gms_internal_zzcav, zzcas com_google_android_gms_internal_zzcas) {
        zzbp.zzu(com_google_android_gms_internal_zzcav);
        zzbp.zzgg(com_google_android_gms_internal_zzcav.packageName);
        zzbp.zzu(com_google_android_gms_internal_zzcav.zzimh);
        zzbp.zzgg(com_google_android_gms_internal_zzcav.zzimh.name);
        zzauk().zzuj();
        zzwk();
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzcas.zzilu)) {
            if (com_google_android_gms_internal_zzcas.zzilz) {
                zzauf().beginTransaction();
                try {
                    zzf(com_google_android_gms_internal_zzcas);
                    zzcav zzai = zzauf().zzai(com_google_android_gms_internal_zzcav.packageName, com_google_android_gms_internal_zzcav.zzimh.name);
                    if (zzai != null) {
                        zzaul().zzayi().zze("Removing conditional user property", com_google_android_gms_internal_zzcav.packageName, zzaug().zzje(com_google_android_gms_internal_zzcav.zzimh.name));
                        zzauf().zzaj(com_google_android_gms_internal_zzcav.packageName, com_google_android_gms_internal_zzcav.zzimh.name);
                        if (zzai.zzimj) {
                            zzauf().zzag(com_google_android_gms_internal_zzcav.packageName, com_google_android_gms_internal_zzcav.zzimh.name);
                        }
                        if (com_google_android_gms_internal_zzcav.zzimp != null) {
                            Bundle bundle = null;
                            if (com_google_android_gms_internal_zzcav.zzimp.zzinr != null) {
                                bundle = com_google_android_gms_internal_zzcav.zzimp.zzinr.zzaxz();
                            }
                            zzc(zzauh().zza(com_google_android_gms_internal_zzcav.zzimp.name, bundle, zzai.zzimg, com_google_android_gms_internal_zzcav.zzimp.zzins, true, false), com_google_android_gms_internal_zzcas);
                        }
                    } else {
                        zzaul().zzayf().zze("Conditional user property doesn't exist", zzcbw.zzjf(com_google_android_gms_internal_zzcav.packageName), zzaug().zzje(com_google_android_gms_internal_zzcav.zzimh.name));
                    }
                    zzauf().setTransactionSuccessful();
                } finally {
                    zzauf().endTransaction();
                }
            } else {
                zzf(com_google_android_gms_internal_zzcas);
            }
        }
    }

    final void zzc(zzcft com_google_android_gms_internal_zzcft, zzcas com_google_android_gms_internal_zzcas) {
        zzauk().zzuj();
        zzwk();
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzcas.zzilu)) {
            if (com_google_android_gms_internal_zzcas.zzilz) {
                zzaul().zzayi().zzj("Removing user property", zzaug().zzje(com_google_android_gms_internal_zzcft.name));
                zzauf().beginTransaction();
                try {
                    zzf(com_google_android_gms_internal_zzcas);
                    zzauf().zzag(com_google_android_gms_internal_zzcas.packageName, com_google_android_gms_internal_zzcft.name);
                    zzauf().setTransactionSuccessful();
                    zzaul().zzayi().zzj("User property removed", zzaug().zzje(com_google_android_gms_internal_zzcft.name));
                } finally {
                    zzauf().endTransaction();
                }
            } else {
                zzf(com_google_android_gms_internal_zzcas);
            }
        }
    }

    final void zzd(zzcas com_google_android_gms_internal_zzcas) {
        zzauk().zzuj();
        zzwk();
        zzbp.zzgg(com_google_android_gms_internal_zzcas.packageName);
        zzf(com_google_android_gms_internal_zzcas);
    }

    final void zzd(zzcav com_google_android_gms_internal_zzcav) {
        zzcas zzjr = zzjr(com_google_android_gms_internal_zzcav.packageName);
        if (zzjr != null) {
            zzb(com_google_android_gms_internal_zzcav, zzjr);
        }
    }

    public final void zze(zzcas com_google_android_gms_internal_zzcas) {
        zzauk().zzuj();
        zzwk();
        zzbp.zzu(com_google_android_gms_internal_zzcas);
        zzbp.zzgg(com_google_android_gms_internal_zzcas.packageName);
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzcas.zzilu)) {
            zzcar zziw = zzauf().zziw(com_google_android_gms_internal_zzcas.packageName);
            if (!(zziw == null || !TextUtils.isEmpty(zziw.getGmpAppId()) || TextUtils.isEmpty(com_google_android_gms_internal_zzcas.zzilu))) {
                zziw.zzar(0);
                zzauf().zza(zziw);
                zzaui().zzjq(com_google_android_gms_internal_zzcas.packageName);
            }
            if (com_google_android_gms_internal_zzcas.zzilz) {
                int i;
                Bundle bundle;
                long j = com_google_android_gms_internal_zzcas.zzime;
                if (j == 0) {
                    j = this.zzasc.currentTimeMillis();
                }
                int i2 = com_google_android_gms_internal_zzcas.zzimf;
                if (i2 == 0 || i2 == 1) {
                    i = i2;
                } else {
                    zzaul().zzayf().zze("Incorrect app type, assuming installed app. appId, appType", zzcbw.zzjf(com_google_android_gms_internal_zzcas.packageName), Integer.valueOf(i2));
                    i = 0;
                }
                zzauf().beginTransaction();
                zzcdt zzauf;
                String appId;
                try {
                    zziw = zzauf().zziw(com_google_android_gms_internal_zzcas.packageName);
                    if (!(zziw == null || zziw.getGmpAppId() == null || zziw.getGmpAppId().equals(com_google_android_gms_internal_zzcas.zzilu))) {
                        zzaul().zzayf().zzj("New GMP App Id passed in. Removing cached database data. appId", zzcbw.zzjf(zziw.getAppId()));
                        zzauf = zzauf();
                        appId = zziw.getAppId();
                        zzauf.zzwk();
                        zzauf.zzuj();
                        zzbp.zzgg(appId);
                        SQLiteDatabase writableDatabase = zzauf.getWritableDatabase();
                        String[] strArr = new String[]{appId};
                        i2 = writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + ((((((((writableDatabase.delete("events", "app_id=?", strArr) + 0) + writableDatabase.delete("user_attributes", "app_id=?", strArr)) + writableDatabase.delete("conditional_properties", "app_id=?", strArr)) + writableDatabase.delete("apps", "app_id=?", strArr)) + writableDatabase.delete("raw_events", "app_id=?", strArr)) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr)) + writableDatabase.delete("event_filters", "app_id=?", strArr)) + writableDatabase.delete("property_filters", "app_id=?", strArr));
                        if (i2 > 0) {
                            zzauf.zzaul().zzayj().zze("Deleted application data. app, records", appId, Integer.valueOf(i2));
                        }
                        zziw = null;
                    }
                } catch (SQLiteException e) {
                    zzauf.zzaul().zzayd().zze("Error deleting application data. appId, error", zzcbw.zzjf(appId), e);
                } catch (Throwable th) {
                    zzauf().endTransaction();
                }
                if (zziw != null) {
                    if (!(zziw.zzuo() == null || zziw.zzuo().equals(com_google_android_gms_internal_zzcas.zzhtt))) {
                        bundle = new Bundle();
                        bundle.putString("_pv", zziw.zzuo());
                        zzb(new zzcbk("_au", new zzcbh(bundle), "auto", j), com_google_android_gms_internal_zzcas);
                    }
                }
                zzf(com_google_android_gms_internal_zzcas);
                zzcbg com_google_android_gms_internal_zzcbg = null;
                if (i == 0) {
                    com_google_android_gms_internal_zzcbg = zzauf().zzaf(com_google_android_gms_internal_zzcas.packageName, "_f");
                } else if (i == 1) {
                    com_google_android_gms_internal_zzcbg = zzauf().zzaf(com_google_android_gms_internal_zzcas.packageName, "_v");
                }
                if (com_google_android_gms_internal_zzcbg == null) {
                    long j2 = (1 + (j / 3600000)) * 3600000;
                    if (i == 0) {
                        zzb(new zzcft("_fot", j, Long.valueOf(j2), "auto"), com_google_android_gms_internal_zzcas);
                        zzauk().zzuj();
                        zzwk();
                        Bundle bundle2 = new Bundle();
                        bundle2.putLong("_c", 1);
                        bundle2.putLong("_r", 1);
                        bundle2.putLong("_uwa", 0);
                        bundle2.putLong("_pfo", 0);
                        bundle2.putLong("_sys", 0);
                        bundle2.putLong("_sysu", 0);
                        if (this.mContext.getPackageManager() == null) {
                            zzaul().zzayd().zzj("PackageManager is null, first open report might be inaccurate. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcas.packageName));
                        } else {
                            ApplicationInfo applicationInfo;
                            PackageInfo packageInfo = null;
                            try {
                                packageInfo = zzbed.zzcr(this.mContext).getPackageInfo(com_google_android_gms_internal_zzcas.packageName, 0);
                            } catch (NameNotFoundException e2) {
                                zzaul().zzayd().zze("Package info is null, first open report might be inaccurate. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcas.packageName), e2);
                            }
                            if (packageInfo != null) {
                                if (packageInfo.firstInstallTime != 0) {
                                    Object obj = null;
                                    if (packageInfo.firstInstallTime != packageInfo.lastUpdateTime) {
                                        bundle2.putLong("_uwa", 1);
                                    } else {
                                        obj = 1;
                                    }
                                    zzb(new zzcft("_fi", j, Long.valueOf(obj != null ? 1 : 0), "auto"), com_google_android_gms_internal_zzcas);
                                }
                            }
                            try {
                                applicationInfo = zzbed.zzcr(this.mContext).getApplicationInfo(com_google_android_gms_internal_zzcas.packageName, 0);
                            } catch (NameNotFoundException e22) {
                                zzaul().zzayd().zze("Application info is null, first open report might be inaccurate. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcas.packageName), e22);
                                applicationInfo = null;
                            }
                            if (applicationInfo != null) {
                                if ((applicationInfo.flags & 1) != 0) {
                                    bundle2.putLong("_sys", 1);
                                }
                                if ((applicationInfo.flags & 128) != 0) {
                                    bundle2.putLong("_sysu", 1);
                                }
                            }
                        }
                        zzcdt zzauf2 = zzauf();
                        String str = com_google_android_gms_internal_zzcas.packageName;
                        zzbp.zzgg(str);
                        zzauf2.zzuj();
                        zzauf2.zzwk();
                        j2 = zzauf2.zzam(str, "first_open_count");
                        if (j2 >= 0) {
                            bundle2.putLong("_pfo", j2);
                        }
                        zzb(new zzcbk("_f", new zzcbh(bundle2), "auto", j), com_google_android_gms_internal_zzcas);
                    } else if (i == 1) {
                        zzb(new zzcft("_fvt", j, Long.valueOf(j2), "auto"), com_google_android_gms_internal_zzcas);
                        zzauk().zzuj();
                        zzwk();
                        bundle = new Bundle();
                        bundle.putLong("_c", 1);
                        bundle.putLong("_r", 1);
                        zzb(new zzcbk("_v", new zzcbh(bundle), "auto", j), com_google_android_gms_internal_zzcas);
                    }
                    bundle = new Bundle();
                    bundle.putLong("_et", 1);
                    zzb(new zzcbk("_e", new zzcbh(bundle), "auto", j), com_google_android_gms_internal_zzcas);
                } else if (com_google_android_gms_internal_zzcas.zzima) {
                    zzb(new zzcbk("_cd", new zzcbh(new Bundle()), "auto", j), com_google_android_gms_internal_zzcas);
                }
                zzauf().setTransactionSuccessful();
                zzauf().endTransaction();
                return;
            }
            zzf(com_google_android_gms_internal_zzcas);
        }
    }

    final void zze(zzcav com_google_android_gms_internal_zzcav) {
        zzcas zzjr = zzjr(com_google_android_gms_internal_zzcav.packageName);
        if (zzjr != null) {
            zzc(com_google_android_gms_internal_zzcav, zzjr);
        }
    }

    final void zzi(Runnable runnable) {
        zzauk().zzuj();
        if (this.zzitu == null) {
            this.zzitu = new ArrayList();
        }
        this.zzitu.add(runnable);
    }

    public final String zzjs(String str) {
        Object e;
        try {
            return (String) zzauk().zzd(new zzccy(this, str)).get(30000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e2) {
            e = e2;
        } catch (InterruptedException e3) {
            e = e3;
        } catch (ExecutionException e4) {
            e = e4;
        }
        zzaul().zzayd().zze("Failed to get app instance id. appId", zzcbw.zzjf(str), e);
        return null;
    }

    public final zzd zzvx() {
        return this.zzasc;
    }

    final void zzwk() {
        if (!this.zzdoe) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }
}
