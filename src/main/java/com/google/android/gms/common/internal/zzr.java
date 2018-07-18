package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzcpt;
import java.util.Collection;

public final class zzr {
    private Account zzduz;
    private String zzdxc;
    private int zzfhd = 0;
    private String zzfhf;
    private zzcpt zzftt = zzcpt.zzjno;
    private ArraySet<Scope> zzftv;

    public final zzq zzajz() {
        return new zzq(this.zzduz, this.zzftv, null, 0, null, this.zzdxc, this.zzfhf, this.zzftt);
    }

    public final zzr zze(Account account) {
        this.zzduz = account;
        return this;
    }

    public final zzr zze(Collection<Scope> collection) {
        if (this.zzftv == null) {
            this.zzftv = new ArraySet();
        }
        this.zzftv.addAll(collection);
        return this;
    }

    public final zzr zzfz(String str) {
        this.zzdxc = str;
        return this;
    }

    public final zzr zzga(String str) {
        this.zzfhf = str;
        return this;
    }
}
