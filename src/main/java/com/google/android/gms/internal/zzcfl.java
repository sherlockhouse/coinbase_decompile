package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.util.zzd;

public final class zzcfl extends zzcdu {
    private Handler mHandler;
    private long zziwt = zzvx().elapsedRealtime();
    private final zzcbc zziwu = new zzcfm(this, this.zziki);
    private final zzcbc zziwv = new zzcfn(this, this.zziki);

    zzcfl(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
    }

    private final void zzazt() {
        synchronized (this) {
            if (this.mHandler == null) {
                this.mHandler = new Handler(Looper.getMainLooper());
            }
        }
    }

    private final void zzazu() {
        zzuj();
        zzbs(false);
        zzatx().zzaj(zzvx().elapsedRealtime());
    }

    private final void zzbd(long j) {
        zzuj();
        zzazt();
        this.zziwu.cancel();
        this.zziwv.cancel();
        zzaul().zzayj().zzj("Activity resumed, time", Long.valueOf(j));
        this.zziwt = j;
        if (zzvx().currentTimeMillis() - zzaum().zzird.get() > zzaum().zzirf.get()) {
            zzaum().zzire.set(true);
            zzaum().zzirg.set(0);
        }
        if (zzaum().zzire.get()) {
            this.zziwu.zzs(Math.max(0, zzaum().zzirc.get() - zzaum().zzirg.get()));
        } else {
            this.zziwv.zzs(Math.max(0, 3600000 - zzaum().zzirg.get()));
        }
    }

    private final void zzbe(long j) {
        zzuj();
        zzazt();
        this.zziwu.cancel();
        this.zziwv.cancel();
        zzaul().zzayj().zzj("Activity paused, time", Long.valueOf(j));
        if (this.zziwt != 0) {
            zzaum().zzirg.set(zzaum().zzirg.get() + (j - this.zziwt));
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
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

    public final boolean zzbs(boolean z) {
        zzuj();
        zzwk();
        long elapsedRealtime = zzvx().elapsedRealtime();
        zzaum().zzirf.set(zzvx().currentTimeMillis());
        long j = elapsedRealtime - this.zziwt;
        if (z || j >= 1000) {
            zzaum().zzirg.set(j);
            zzaul().zzayj().zzj("Recording user engagement, ms", Long.valueOf(j));
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j);
            zzcek.zza(zzaud().zzazn(), bundle);
            zzatz().zzc("auto", "_e", bundle);
            this.zziwt = elapsedRealtime;
            this.zziwv.cancel();
            this.zziwv.zzs(Math.max(0, 3600000 - zzaum().zzirg.get()));
            return true;
        }
        zzaul().zzayj().zzj("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j));
        return false;
    }

    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    protected final void zzuk() {
    }

    public final /* bridge */ /* synthetic */ zzd zzvx() {
        return super.zzvx();
    }
}
