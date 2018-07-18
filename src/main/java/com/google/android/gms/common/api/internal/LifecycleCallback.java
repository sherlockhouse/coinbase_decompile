package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Keep;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class LifecycleCallback {
    protected final zzcg zzfoo;

    protected LifecycleCallback(zzcg com_google_android_gms_common_api_internal_zzcg) {
        this.zzfoo = com_google_android_gms_common_api_internal_zzcg;
    }

    @Keep
    private static zzcg getChimeraLifecycleFragmentImpl(zzcf com_google_android_gms_common_api_internal_zzcf) {
        throw new IllegalStateException("Method not available in SDK.");
    }

    protected static zzcg zzb(zzcf com_google_android_gms_common_api_internal_zzcf) {
        if (com_google_android_gms_common_api_internal_zzcf.zzaig()) {
            return zzdb.zza(com_google_android_gms_common_api_internal_zzcf.zzaii());
        }
        if (com_google_android_gms_common_api_internal_zzcf.isAndroid()) {
            return zzch.zzo(com_google_android_gms_common_api_internal_zzcf.zzaih());
        }
        throw new IllegalArgumentException("Can't get fragment for unexpected activity.");
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public final Activity getActivity() {
        return this.zzfoo.zzaij();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onCreate(Bundle bundle) {
    }

    public void onDestroy() {
    }

    public void onResume() {
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onStart() {
    }

    public void onStop() {
    }
}
