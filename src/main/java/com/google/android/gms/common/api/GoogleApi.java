package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasAccountOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.internal.zzbp;
import com.google.android.gms.common.api.internal.zzbr;
import com.google.android.gms.common.api.internal.zzbx;
import com.google.android.gms.common.api.internal.zzcw;
import com.google.android.gms.common.api.internal.zzcz;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.zzr;
import java.util.Collection;
import java.util.Collections;

public class GoogleApi<O extends ApiOptions> {
    private final Context mContext;
    private final int mId;
    private final Looper zzakg;
    private final Api<O> zzfdg;
    private final O zzfgr = null;
    private final zzh<O> zzfgs;
    private final GoogleApiClient zzfgt;
    private final zzcz zzfgu;
    protected final zzbp zzfgv;

    protected GoogleApi(Context context, Api<O> api, Looper looper) {
        com.google.android.gms.common.internal.zzbp.zzb((Object) context, (Object) "Null context is not permitted.");
        com.google.android.gms.common.internal.zzbp.zzb((Object) api, (Object) "Api must not be null.");
        com.google.android.gms.common.internal.zzbp.zzb((Object) looper, (Object) "Looper must not be null.");
        this.mContext = context.getApplicationContext();
        this.zzfdg = api;
        this.zzakg = looper;
        this.zzfgs = zzh.zzb(api);
        this.zzfgt = new zzbx(this);
        this.zzfgv = zzbp.zzca(this.mContext);
        this.mId = this.zzfgv.zzahq();
        this.zzfgu = new zzg();
    }

    private final <A extends zzb, T extends zzm<? extends Result, A>> T zza(int i, T t) {
        t.zzagg();
        this.zzfgv.zza(this, i, t);
        return t;
    }

    private final zzr zzafm() {
        Collection grantedScopes;
        zzr com_google_android_gms_common_internal_zzr = new zzr();
        Account account = this.zzfgr instanceof HasGoogleSignInAccountOptions ? ((HasGoogleSignInAccountOptions) this.zzfgr).getGoogleSignInAccount().getAccount() : this.zzfgr instanceof HasAccountOptions ? ((HasAccountOptions) this.zzfgr).getAccount() : null;
        com_google_android_gms_common_internal_zzr = com_google_android_gms_common_internal_zzr.zze(account);
        if (this.zzfgr instanceof HasGoogleSignInAccountOptions) {
            GoogleSignInAccount googleSignInAccount = ((HasGoogleSignInAccountOptions) this.zzfgr).getGoogleSignInAccount();
            if (googleSignInAccount != null) {
                grantedScopes = googleSignInAccount.getGrantedScopes();
                return com_google_android_gms_common_internal_zzr.zze(grantedScopes);
            }
        }
        grantedScopes = Collections.emptySet();
        return com_google_android_gms_common_internal_zzr.zze(grantedScopes);
    }

    public final Context getApplicationContext() {
        return this.mContext;
    }

    public final int getInstanceId() {
        return this.mId;
    }

    public final Looper getLooper() {
        return this.zzakg;
    }

    public zze zza(Looper looper, zzbr<O> com_google_android_gms_common_api_internal_zzbr_O) {
        return this.zzfdg.zzafd().zza(this.mContext, looper, zzafm().zzfz(this.mContext.getPackageName()).zzga(this.mContext.getClass().getName()).zzajz(), this.zzfgr, com_google_android_gms_common_api_internal_zzbr_O, com_google_android_gms_common_api_internal_zzbr_O);
    }

    public zzcw zza(Context context, Handler handler) {
        return new zzcw(context, handler, zzafm().zzajz());
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zza(T t) {
        return zza(0, (zzm) t);
    }

    public final Api<O> zzafj() {
        return this.zzfdg;
    }

    public final zzh<O> zzafk() {
        return this.zzfgs;
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zzb(T t) {
        return zza(1, (zzm) t);
    }
}
