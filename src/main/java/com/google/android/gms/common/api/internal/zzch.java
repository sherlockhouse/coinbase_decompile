package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public final class zzch extends Fragment implements zzcg {
    private static WeakHashMap<Activity, WeakReference<zzch>> zzfop = new WeakHashMap();
    private int zzbyz = 0;
    private Map<String, LifecycleCallback> zzfoq = new ArrayMap();
    private Bundle zzfor;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static zzch zzo(Activity activity) {
        WeakReference weakReference = (WeakReference) zzfop.get(activity);
        if (weakReference != null) {
            zzch com_google_android_gms_common_api_internal_zzch = (zzch) weakReference.get();
        }
        try {
            com_google_android_gms_common_api_internal_zzch = (zzch) activity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
            if (com_google_android_gms_common_api_internal_zzch == null || com_google_android_gms_common_api_internal_zzch.isRemoving()) {
                com_google_android_gms_common_api_internal_zzch = new zzch();
                activity.getFragmentManager().beginTransaction().add(com_google_android_gms_common_api_internal_zzch, "LifecycleFragmentImpl").commitAllowingStateLoss();
            }
            zzfop.put(activity, new WeakReference(com_google_android_gms_common_api_internal_zzch));
            return com_google_android_gms_common_api_internal_zzch;
        } catch (Throwable e) {
            throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", e);
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
            new Handler(Looper.getMainLooper()).post(new zzci(this, lifecycleCallback, str));
        }
    }

    public final Activity zzaij() {
        return getActivity();
    }
}
