package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzq;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class Api<O extends ApiOptions> {
    private final String mName;
    private final zza<?, O> zzfgf;
    private final zzh<?, O> zzfgg = null;
    private final zzf<?> zzfgh;
    private final zzi<?> zzfgi;

    public interface ApiOptions {

        public interface HasOptions extends ApiOptions {
        }

        public interface NotRequiredOptions extends ApiOptions {
        }

        public interface Optional extends HasOptions, NotRequiredOptions {
        }

        public interface HasAccountOptions extends HasOptions, NotRequiredOptions {
            Account getAccount();
        }

        public interface HasGoogleSignInAccountOptions extends HasOptions {
            GoogleSignInAccount getGoogleSignInAccount();
        }
    }

    public interface zzb {
    }

    public interface zze extends zzb {
        void disconnect();

        void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

        boolean isConnected();

        boolean isConnecting();

        void zza(zzam com_google_android_gms_common_internal_zzam, Set<Scope> set);

        void zza(zzj com_google_android_gms_common_internal_zzj);

        boolean zzaac();

        boolean zzaal();

        Intent zzaam();

        boolean zzaff();

        IBinder zzafg();
    }

    public static abstract class zzd<T extends zzb, O> {
        public int getPriority() {
            return Integer.MAX_VALUE;
        }

        public List<Scope> zzn(O o) {
            return Collections.emptyList();
        }
    }

    public static abstract class zza<T extends zze, O> extends zzd<T, O> {
        public abstract T zza(Context context, Looper looper, zzq com_google_android_gms_common_internal_zzq, O o, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener);
    }

    public static class zzc<C extends zzb> {
    }

    public static final class zzf<C extends zze> extends zzc<C> {
    }

    public interface zzg<T extends IInterface> extends zzb {
    }

    public static abstract class zzh<T extends zzg, O> extends zzd<T, O> {
    }

    public static final class zzi<C extends zzg> extends zzc<C> {
    }

    public <C extends zze> Api(String str, zza<C, O> com_google_android_gms_common_api_Api_zza_C__O, zzf<C> com_google_android_gms_common_api_Api_zzf_C) {
        zzbp.zzb((Object) com_google_android_gms_common_api_Api_zza_C__O, (Object) "Cannot construct an Api with a null ClientBuilder");
        zzbp.zzb((Object) com_google_android_gms_common_api_Api_zzf_C, (Object) "Cannot construct an Api with a null ClientKey");
        this.mName = str;
        this.zzfgf = com_google_android_gms_common_api_Api_zza_C__O;
        this.zzfgh = com_google_android_gms_common_api_Api_zzf_C;
        this.zzfgi = null;
    }

    public final String getName() {
        return this.mName;
    }

    public final zzd<?, O> zzafc() {
        return this.zzfgf;
    }

    public final zza<?, O> zzafd() {
        zzbp.zza(this.zzfgf != null, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.zzfgf;
    }

    public final zzc<?> zzafe() {
        if (this.zzfgh != null) {
            return this.zzfgh;
        }
        throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
    }
}
