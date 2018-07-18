package com.google.android.gms.internal;

public class zzbbw<T> {
    private static String READ_PERMISSION = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    private static final Object zzaqd = new Object();
    private static zzbcc zzfpy = null;
    private static int zzfpz = 0;
    private String zzbff;
    private T zzbfg;
    private T zzfqa = null;

    protected zzbbw(String str, T t) {
        this.zzbff = str;
        this.zzbfg = t;
    }

    public static zzbbw<Integer> zza(String str, Integer num) {
        return new zzbbz(str, num);
    }

    public static zzbbw<Long> zza(String str, Long l) {
        return new zzbby(str, l);
    }

    public static zzbbw<Boolean> zze(String str, boolean z) {
        return new zzbbx(str, Boolean.valueOf(z));
    }

    public static zzbbw<String> zzt(String str, String str2) {
        return new zzbcb(str, str2);
    }
}
