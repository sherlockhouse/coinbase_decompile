package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.internal.zzak;

public final class zzbde {
    private final String mTag;
    private final int zzdqr;
    private final String zzfvl;
    private final zzak zzfwj;

    private zzbde(String str, String str2) {
        this.zzfvl = str2;
        this.mTag = str;
        this.zzfwj = new zzak(str);
        this.zzdqr = getLogLevel();
    }

    public zzbde(String str, String... strArr) {
        this(str, zzb(strArr));
    }

    private final String format(String str, Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(str, objArr);
        }
        return this.zzfvl.concat(str);
    }

    private final int getLogLevel() {
        int i = 2;
        while (7 >= i && !Log.isLoggable(this.mTag, i)) {
            i++;
        }
        return i;
    }

    private final boolean zzad(int i) {
        return this.zzdqr <= i;
    }

    private static String zzb(String... strArr) {
        if (strArr.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (String str : strArr) {
            if (stringBuilder.length() > 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append(str);
        }
        stringBuilder.append(']').append(' ');
        return stringBuilder.toString();
    }

    public final void zzb(String str, Object... objArr) {
        if (zzad(3)) {
            Log.d(this.mTag, format(str, objArr));
        }
    }
}
