package com.google.android.gms.auth.api;

import android.os.Bundle;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zzc;
import com.google.android.gms.auth.api.signin.internal.zzd;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.internal.zzarj;
import com.google.android.gms.internal.zzark;
import com.google.android.gms.internal.zzarl;
import com.google.android.gms.internal.zzash;
import com.google.android.gms.internal.zzasp;
import com.google.android.gms.internal.zzato;

public final class Auth {
    public static final Api<AuthCredentialsOptions> CREDENTIALS_API = new Api("Auth.CREDENTIALS_API", zzdyf, zzdyc);
    public static final CredentialsApi CredentialsApi = new zzash();
    public static final Api<GoogleSignInOptions> GOOGLE_SIGN_IN_API = new Api("Auth.GOOGLE_SIGN_IN_API", zzdyh, zzdye);
    public static final GoogleSignInApi GoogleSignInApi = new zzc();
    public static final Api<zzf> PROXY_API = zzd.API;
    public static final ProxyApi ProxyApi = new zzato();
    public static final zzf<zzasp> zzdyc = new zzf();
    private static zzf<zzarl> zzdyd = new zzf();
    public static final zzf<zzd> zzdye = new zzf();
    private static final zza<zzasp, AuthCredentialsOptions> zzdyf = new zza();
    private static final zza<zzarl, Object> zzdyg = new zzb();
    private static final zza<zzd, GoogleSignInOptions> zzdyh = new zzc();
    private static Api<Object> zzdyi = new Api("Auth.ACCOUNT_STATUS_API", zzdyg, zzdyd);
    private static zzarj zzdyj = new zzark();

    public static final class AuthCredentialsOptions implements Optional {
        private static AuthCredentialsOptions zzdyk = new AuthCredentialsOptions(new Builder());
        private final String zzdyl = null;
        private final PasswordSpecification zzdym;

        public static class Builder {
            private PasswordSpecification zzdym = PasswordSpecification.zzeax;
        }

        private AuthCredentialsOptions(Builder builder) {
            this.zzdym = builder.zzdym;
        }

        public final Bundle zzzu() {
            Bundle bundle = new Bundle();
            bundle.putString("consumer_package", null);
            bundle.putParcelable("password_specification", this.zzdym);
            return bundle;
        }
    }
}
