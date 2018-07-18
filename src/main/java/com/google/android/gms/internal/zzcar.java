package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbp;

final class zzcar {
    private final String mAppId;
    private String zzcye;
    private String zzdmc;
    private String zzgan;
    private final zzccw zziki;
    private String zzikz;
    private String zzila;
    private long zzilb;
    private long zzilc;
    private long zzild;
    private long zzile;
    private String zzilf;
    private long zzilg;
    private long zzilh;
    private boolean zzili;
    private long zzilj;
    private long zzilk;
    private long zzill;
    private long zzilm;
    private long zziln;
    private long zzilo;
    private long zzilp;
    private String zzilq;
    private boolean zzilr;
    private long zzils;
    private long zzilt;

    zzcar(zzccw com_google_android_gms_internal_zzccw, String str) {
        zzbp.zzu(com_google_android_gms_internal_zzccw);
        zzbp.zzgg(str);
        this.zziki = com_google_android_gms_internal_zzccw;
        this.mAppId = str;
        this.zziki.zzauk().zzuj();
    }

    public final String getAppId() {
        this.zziki.zzauk().zzuj();
        return this.mAppId;
    }

    public final String getAppInstanceId() {
        this.zziki.zzauk().zzuj();
        return this.zzgan;
    }

    public final String getGmpAppId() {
        this.zziki.zzauk().zzuj();
        return this.zzcye;
    }

    public final void setAppVersion(String str) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (!zzcfw.zzas(this.zzdmc, str) ? 1 : 0) | this.zzilr;
        this.zzdmc = str;
    }

    public final void setMeasurementEnabled(boolean z) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzili != z ? 1 : 0) | this.zzilr;
        this.zzili = z;
    }

    public final void zzal(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzilc != j ? 1 : 0) | this.zzilr;
        this.zzilc = j;
    }

    public final void zzam(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzild != j ? 1 : 0) | this.zzilr;
        this.zzild = j;
    }

    public final void zzan(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzile != j ? 1 : 0) | this.zzilr;
        this.zzile = j;
    }

    public final void zzao(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzilg != j ? 1 : 0) | this.zzilr;
        this.zzilg = j;
    }

    public final void zzap(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzilh != j ? 1 : 0) | this.zzilr;
        this.zzilh = j;
    }

    public final void zzaq(long j) {
        int i = 1;
        zzbp.zzbh(j >= 0);
        this.zziki.zzauk().zzuj();
        boolean z = this.zzilr;
        if (this.zzilb == j) {
            i = 0;
        }
        this.zzilr = z | i;
        this.zzilb = j;
    }

    public final void zzar(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzils != j ? 1 : 0) | this.zzilr;
        this.zzils = j;
    }

    public final void zzas(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzilt != j ? 1 : 0) | this.zzilr;
        this.zzilt = j;
    }

    public final void zzat(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzilk != j ? 1 : 0) | this.zzilr;
        this.zzilk = j;
    }

    public final void zzau(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzill != j ? 1 : 0) | this.zzilr;
        this.zzill = j;
    }

    public final void zzauo() {
        this.zziki.zzauk().zzuj();
        this.zzilr = false;
    }

    public final String zzaup() {
        this.zziki.zzauk().zzuj();
        return this.zzikz;
    }

    public final String zzauq() {
        this.zziki.zzauk().zzuj();
        return this.zzila;
    }

    public final long zzaur() {
        this.zziki.zzauk().zzuj();
        return this.zzilc;
    }

    public final long zzaus() {
        this.zziki.zzauk().zzuj();
        return this.zzild;
    }

    public final long zzaut() {
        this.zziki.zzauk().zzuj();
        return this.zzile;
    }

    public final String zzauu() {
        this.zziki.zzauk().zzuj();
        return this.zzilf;
    }

    public final long zzauv() {
        this.zziki.zzauk().zzuj();
        return this.zzilg;
    }

    public final long zzauw() {
        this.zziki.zzauk().zzuj();
        return this.zzilh;
    }

    public final boolean zzaux() {
        this.zziki.zzauk().zzuj();
        return this.zzili;
    }

    public final long zzauy() {
        this.zziki.zzauk().zzuj();
        return this.zzilb;
    }

    public final long zzauz() {
        this.zziki.zzauk().zzuj();
        return this.zzils;
    }

    public final void zzav(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzilm != j ? 1 : 0) | this.zzilr;
        this.zzilm = j;
    }

    public final long zzava() {
        this.zziki.zzauk().zzuj();
        return this.zzilt;
    }

    public final void zzavb() {
        this.zziki.zzauk().zzuj();
        long j = this.zzilb + 1;
        if (j > 2147483647L) {
            this.zziki.zzaul().zzayf().zzj("Bundle index overflow. appId", zzcbw.zzjf(this.mAppId));
            j = 0;
        }
        this.zzilr = true;
        this.zzilb = j;
    }

    public final long zzavc() {
        this.zziki.zzauk().zzuj();
        return this.zzilk;
    }

    public final long zzavd() {
        this.zziki.zzauk().zzuj();
        return this.zzill;
    }

    public final long zzave() {
        this.zziki.zzauk().zzuj();
        return this.zzilm;
    }

    public final long zzavf() {
        this.zziki.zzauk().zzuj();
        return this.zziln;
    }

    public final long zzavg() {
        this.zziki.zzauk().zzuj();
        return this.zzilp;
    }

    public final long zzavh() {
        this.zziki.zzauk().zzuj();
        return this.zzilo;
    }

    public final String zzavi() {
        this.zziki.zzauk().zzuj();
        return this.zzilq;
    }

    public final String zzavj() {
        this.zziki.zzauk().zzuj();
        String str = this.zzilq;
        zzir(null);
        return str;
    }

    public final long zzavk() {
        this.zziki.zzauk().zzuj();
        return this.zzilj;
    }

    public final void zzaw(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zziln != j ? 1 : 0) | this.zzilr;
        this.zziln = j;
    }

    public final void zzax(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzilp != j ? 1 : 0) | this.zzilr;
        this.zzilp = j;
    }

    public final void zzay(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzilo != j ? 1 : 0) | this.zzilr;
        this.zzilo = j;
    }

    public final void zzaz(long j) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (this.zzilj != j ? 1 : 0) | this.zzilr;
        this.zzilj = j;
    }

    public final void zzim(String str) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (!zzcfw.zzas(this.zzgan, str) ? 1 : 0) | this.zzilr;
        this.zzgan = str;
    }

    public final void zzin(String str) {
        this.zziki.zzauk().zzuj();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzilr = (!zzcfw.zzas(this.zzcye, str) ? 1 : 0) | this.zzilr;
        this.zzcye = str;
    }

    public final void zzio(String str) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (!zzcfw.zzas(this.zzikz, str) ? 1 : 0) | this.zzilr;
        this.zzikz = str;
    }

    public final void zzip(String str) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (!zzcfw.zzas(this.zzila, str) ? 1 : 0) | this.zzilr;
        this.zzila = str;
    }

    public final void zziq(String str) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (!zzcfw.zzas(this.zzilf, str) ? 1 : 0) | this.zzilr;
        this.zzilf = str;
    }

    public final void zzir(String str) {
        this.zziki.zzauk().zzuj();
        this.zzilr = (!zzcfw.zzas(this.zzilq, str) ? 1 : 0) | this.zzilr;
        this.zzilq = str;
    }

    public final String zzuo() {
        this.zziki.zzauk().zzuj();
        return this.zzdmc;
    }
}
