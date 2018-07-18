package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zzi implements Callable<String> {
    private /* synthetic */ SharedPreferences zzhbc;
    private /* synthetic */ String zzhbd;
    private /* synthetic */ String zzhbh;

    zzi(SharedPreferences sharedPreferences, String str, String str2) {
        this.zzhbc = sharedPreferences;
        this.zzhbd = str;
        this.zzhbh = str2;
    }

    public final /* synthetic */ Object call() throws Exception {
        return this.zzhbc.getString(this.zzhbd, this.zzhbh);
    }
}
