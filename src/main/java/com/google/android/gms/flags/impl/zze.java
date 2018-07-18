package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zze implements Callable<Integer> {
    private /* synthetic */ SharedPreferences zzhbc;
    private /* synthetic */ String zzhbd;
    private /* synthetic */ Integer zzhbf;

    zze(SharedPreferences sharedPreferences, String str, Integer num) {
        this.zzhbc = sharedPreferences;
        this.zzhbd = str;
        this.zzhbf = num;
    }

    public final /* synthetic */ Object call() throws Exception {
        return Integer.valueOf(this.zzhbc.getInt(this.zzhbd, this.zzhbf.intValue()));
    }
}
