package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public interface zzcd {
    ConnectionResult blockingConnect();

    void connect();

    void disconnect();

    void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    boolean isConnected();

    boolean zza(zzcv com_google_android_gms_common_api_internal_zzcv);

    void zzafp();

    void zzagi();

    <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(T t);

    <A extends zzb, T extends zzm<? extends Result, A>> T zze(T t);
}
