package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zzc implements Callable<Boolean> {
    private /* synthetic */ SharedPreferences zzhbc;
    private /* synthetic */ String zzhbd;
    private /* synthetic */ Boolean zzhbe;

    zzc(SharedPreferences sharedPreferences, String str, Boolean bool) {
        this.zzhbc = sharedPreferences;
        this.zzhbd = str;
        this.zzhbe = bool;
    }

    public final /* synthetic */ Object call() throws Exception {
        return Boolean.valueOf(this.zzhbc.getBoolean(this.zzhbd, this.zzhbe.booleanValue()));
    }
}
