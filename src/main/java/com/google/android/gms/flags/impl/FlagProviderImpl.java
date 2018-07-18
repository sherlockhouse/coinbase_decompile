package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzbvm;

@DynamiteApi
public class FlagProviderImpl extends zzbvm {
    private boolean zzaqf = false;
    private SharedPreferences zzbfl;

    public boolean getBooleanFlagValue(String str, boolean z, int i) {
        return !this.zzaqf ? z : zzb.zza(this.zzbfl, str, Boolean.valueOf(z)).booleanValue();
    }

    public int getIntFlagValue(String str, int i, int i2) {
        return !this.zzaqf ? i : zzd.zza(this.zzbfl, str, Integer.valueOf(i)).intValue();
    }

    public long getLongFlagValue(String str, long j, int i) {
        return !this.zzaqf ? j : zzf.zza(this.zzbfl, str, Long.valueOf(j)).longValue();
    }

    public String getStringFlagValue(String str, String str2, int i) {
        return !this.zzaqf ? str2 : zzh.zza(this.zzbfl, str, str2);
    }

    public void init(IObjectWrapper iObjectWrapper) {
        Context context = (Context) zzn.zzx(iObjectWrapper);
        if (!this.zzaqf) {
            try {
                this.zzbfl = zzj.zzcy(context.createPackageContext("com.google.android.gms", 0));
                this.zzaqf = true;
            } catch (NameNotFoundException e) {
            } catch (Exception e2) {
                String str = "FlagProviderImpl";
                String str2 = "Could not retrieve sdk flags, continuing with defaults: ";
                String valueOf = String.valueOf(e2.getMessage());
                Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
        }
    }
}
