package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public final class zzdb extends Fragment implements zzcg {
    private static WeakHashMap<FragmentActivity, WeakReference<zzdb>> zzfop = new WeakHashMap();
    private int zzbyz = 0;
    private Map<String, LifecycleCallback> zzfoq = new ArrayMap();
    private Bundle zzfor;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static zzdb zza(FragmentActivity fragmentActivity) {
        WeakReference weakReference = (WeakReference) zzfop.get(fragmentActivity);
        if (weakReference != null) {
            zzdb com_google_android_gms_common_api_internal_zzdb = (zzdb) weakReference.get();
        }
        try {
            com_google_android_gms_common_api_internal_zzdb = (zzdb) fragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
            if (com_google_android_gms_common_api_internal_zzdb == null || com_google_android_gms_common_api_internal_zzdb.isRemoving()) {
                com_google_android_gms_common_api_internal_zzdb = new zzdb();
                fragmentActivity.getSupportFragmentManager().beginTransaction().add(com_google_android_gms_common_api_internal_zzdb, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
            }
            zzfop.put(fragmentActivity, new WeakReference(com_google_android_gms_common_api_internal_zzdb));
            return com_google_android_gms_common_api_internal_zzdb;
        } catch (Throwable e) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e);
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (LifecycleCallback dump : this.zzfoq.values()) {
            dump.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (LifecycleCallback onActivityResult : this.zzfoq.values()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbyz = 1;
        this.zzfor = bundle;
        for (Entry entry : this.zzfoq.entrySet()) {
            ((LifecycleCallback) entry.getValue()).onCreate(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzbyz = 5;
        for (LifecycleCallback onDestroy : this.zzfoq.values()) {
            onDestroy.onDestroy();
        }
    }

    public final void onResume() {
        super.onResume();
        this.zzbyz = 3;
        for (LifecycleCallback onResume : this.zzfoq.values()) {
            onResume.onResume();
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Entry entry : this.zzfoq.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((LifecycleCallback) entry.getValue()).onSaveInstanceState(bundle2);
                bundle.putBundle((String) entry.getKey(), bundle2);
            }
        }
    }

    public final void onStart() {
        super.onStart();
        this.zzbyz = 2;
        for (LifecycleCallback onStart : this.zzfoq.values()) {
            onStart.onStart();
        }
    }

    public final void onStop() {
        super.onStop();
        this.zzbyz = 4;
        for (LifecycleCallback onStop : this.zzfoq.values()) {
            onStop.onStop();
        }
    }

    public final <T extends LifecycleCallback> T zza(String str, Class<T> cls) {
        return (LifecycleCallback) cls.cast(this.zzfoq.get(str));
    }

    public final void zza(String str, LifecycleCallback lifecycleCallback) {
        if (this.zzfoq.containsKey(str)) {
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 59).append("LifecycleCallback with tag ").append(str).append(" already added to this fragment.").toString());
        }
        this.zzfoq.put(str, lifecycleCallback);
        if (this.zzbyz > 0) {
            new Handler(Looper.getMainLooper()).post(new zzdc(this, lifecycleCallback, str));
        }
    }

    public final /* synthetic */ Activity zzaij() {
        return getActivity();
    }
}
