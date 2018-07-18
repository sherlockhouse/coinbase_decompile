package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zzg implements Callable<Long> {
    private /* synthetic */ SharedPreferences zzhbc;
    private /* synthetic */ String zzhbd;
    private /* synthetic */ Long zzhbg;

    zzg(SharedPreferences sharedPreferences, String str, Long l) {
        this.zzhbc = sharedPreferences;
        this.zzhbd = str;
        this.zzhbg = l;
    }

    public final /* synthetic */ Object call() throws Exception {
        return Long.valueOf(this.zzhbc.getLong(this.zzhbd, this.zzhbg.longValue()));
    }
}
