package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.zzy;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.common.internal.zzq;

public final class zzcqc extends zzaa<zzcqa> implements zzcps {
    private final zzq zzfkj;
    private Integer zzftu;
    private final boolean zzjnv;
    private final Bundle zzjnw;

    private zzcqc(Context context, Looper looper, boolean z, zzq com_google_android_gms_common_internal_zzq, Bundle bundle, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, com_google_android_gms_common_internal_zzq, connectionCallbacks, onConnectionFailedListener);
        this.zzjnv = true;
        this.zzfkj = com_google_android_gms_common_internal_zzq;
        this.zzjnw = bundle;
        this.zzftu = com_google_android_gms_common_internal_zzq.zzajy();
    }

    public zzcqc(Context context, Looper looper, boolean z, zzq com_google_android_gms_common_internal_zzq, zzcpt com_google_android_gms_internal_zzcpt, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, true, com_google_android_gms_common_internal_zzq, zza(com_google_android_gms_common_internal_zzq), connectionCallbacks, onConnectionFailedListener);
    }

    public static Bundle zza(zzq com_google_android_gms_common_internal_zzq) {
        zzcpt zzajx = com_google_android_gms_common_internal_zzq.zzajx();
        Integer zzajy = com_google_android_gms_common_internal_zzq.zzajy();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", com_google_android_gms_common_internal_zzq.getAccount());
        if (zzajy != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", zzajy.intValue());
        }
        if (zzajx != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzajx.zzbbw());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzajx.isIdTokenRequested());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzajx.getServerClientId());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zzajx.zzbbx());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", zzajx.zzbby());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", zzajx.zzbbz());
            if (zzajx.zzbca() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", zzajx.zzbca().longValue());
            }
            if (zzajx.zzbcb() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", zzajx.zzbcb().longValue());
            }
        }
        return bundle;
    }

    public final void connect() {
        zza(new zzm(this));
    }

    public final void zza(zzam com_google_android_gms_common_internal_zzam, boolean z) {
        try {
            ((zzcqa) zzajj()).zza(com_google_android_gms_common_internal_zzam, this.zzftu.intValue(), z);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public final void zza(zzcpy com_google_android_gms_internal_zzcpy) {
        zzbp.zzb((Object) com_google_android_gms_internal_zzcpy, (Object) "Expecting a valid ISignInCallbacks");
        try {
            Account zzajp = this.zzfkj.zzajp();
            GoogleSignInAccount googleSignInAccount = null;
            if ("<<default account>>".equals(zzajp.name)) {
                googleSignInAccount = zzy.zzbl(getContext()).zzaas();
            }
            ((zzcqa) zzajj()).zza(new zzcqd(new zzbq(zzajp, this.zzftu.intValue(), googleSignInAccount)), com_google_android_gms_internal_zzcpy);
        } catch (Throwable e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                com_google_android_gms_internal_zzcpy.zzb(new zzcqf(8));
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    public final boolean zzaac() {
        return this.zzjnv;
    }

    public final void zzbbv() {
        try {
            ((zzcqa) zzajj()).zzec(this.zzftu.intValue());
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        return queryLocalInterface instanceof zzcqa ? (zzcqa) queryLocalInterface : new zzcqb(iBinder);
    }

    protected final String zzhc() {
        return "com.google.android.gms.signin.service.START";
    }

    protected final String zzhd() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    protected final Bundle zzzu() {
        if (!getContext().getPackageName().equals(this.zzfkj.zzaju())) {
            this.zzjnw.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzfkj.zzaju());
        }
        return this.zzjnw;
    }
}
