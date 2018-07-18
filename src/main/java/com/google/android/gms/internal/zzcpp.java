package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.Scope;

public final class zzcpp {
    public static final Api<zzcpt> API = new Api("SignIn.API", zzdwq, zzdwp);
    private static zzf<zzcqc> zzdwp = new zzf();
    public static final zza<zzcqc, zzcpt> zzdwq = new zzcpq();
    private static Scope zzecd = new Scope("profile");
    private static Scope zzece = new Scope("email");
    private static Api<Object> zzgdn = new Api("SignIn.INTERNAL_API", zzjnn, zzjnm);
    private static zzf<zzcqc> zzjnm = new zzf();
    private static zza<zzcqc, Object> zzjnn = new zzcpr();
}
