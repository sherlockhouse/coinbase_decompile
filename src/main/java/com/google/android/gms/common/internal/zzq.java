package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzcpt;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzq {
    private final Account zzduz;
    private final String zzdxc;
    private final Set<Scope> zzfhb;
    private final int zzfhd;
    private final View zzfhe;
    private final String zzfhf;
    private final Set<Scope> zzftr;
    private final Map<Api<?>, zzs> zzfts;
    private final zzcpt zzftt;
    private Integer zzftu;

    public zzq(Account account, Set<Scope> set, Map<Api<?>, zzs> map, int i, View view, String str, String str2, zzcpt com_google_android_gms_internal_zzcpt) {
        Map map2;
        this.zzduz = account;
        this.zzfhb = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        if (map == null) {
            map2 = Collections.EMPTY_MAP;
        }
        this.zzfts = map2;
        this.zzfhe = view;
        this.zzfhd = i;
        this.zzdxc = str;
        this.zzfhf = str2;
        this.zzftt = com_google_android_gms_internal_zzcpt;
        Set hashSet = new HashSet(this.zzfhb);
        for (zzs com_google_android_gms_common_internal_zzs : this.zzfts.values()) {
            hashSet.addAll(com_google_android_gms_common_internal_zzs.zzecm);
        }
        this.zzftr = Collections.unmodifiableSet(hashSet);
    }

    public final Account getAccount() {
        return this.zzduz;
    }

    @Deprecated
    public final String getAccountName() {
        return this.zzduz != null ? this.zzduz.name : null;
    }

    public final Account zzajp() {
        return this.zzduz != null ? this.zzduz : new Account("<<default account>>", "com.google");
    }

    public final Set<Scope> zzajr() {
        return this.zzfhb;
    }

    public final Set<Scope> zzajs() {
        return this.zzftr;
    }

    public final Map<Api<?>, zzs> zzajt() {
        return this.zzfts;
    }

    public final String zzaju() {
        return this.zzdxc;
    }

    public final String zzajv() {
        return this.zzfhf;
    }

    public final zzcpt zzajx() {
        return this.zzftt;
    }

    public final Integer zzajy() {
        return this.zzftu;
    }

    public final Set<Scope> zzc(Api<?> api) {
        zzs com_google_android_gms_common_internal_zzs = (zzs) this.zzfts.get(api);
        if (com_google_android_gms_common_internal_zzs == null || com_google_android_gms_common_internal_zzs.zzecm.isEmpty()) {
            return this.zzfhb;
        }
        Set<Scope> hashSet = new HashSet(this.zzfhb);
        hashSet.addAll(com_google_android_gms_common_internal_zzs.zzecm);
        return hashSet;
    }

    public final void zzc(Integer num) {
        this.zzftu = num;
    }
}
