package com.google.android.gms.common.internal;

public final class zzak {
    private static int zzfvi = 15;
    private static final String zzfvj = null;
    private final String zzfvk;
    private final String zzfvl;

    public zzak(String str) {
        this(str, null);
    }

    private zzak(String str, String str2) {
        zzbp.zzb((Object) str, (Object) "log tag cannot be null");
        zzbp.zzb(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, Integer.valueOf(23));
        this.zzfvk = str;
        this.zzfvl = null;
    }
}
