package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.zzbp;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public final class Tasks {
    public static <TResult> Task<TResult> call(Executor executor, Callable<TResult> callable) {
        zzbp.zzb((Object) executor, (Object) "Executor must not be null");
        zzbp.zzb((Object) callable, (Object) "Callback must not be null");
        Task com_google_android_gms_tasks_zzn = new zzn();
        executor.execute(new zzo(com_google_android_gms_tasks_zzn, callable));
        return com_google_android_gms_tasks_zzn;
    }

    public static <TResult> Task<TResult> forException(Exception exception) {
        Task com_google_android_gms_tasks_zzn = new zzn();
        com_google_android_gms_tasks_zzn.setException(exception);
        return com_google_android_gms_tasks_zzn;
    }

    public static <TResult> Task<TResult> forResult(TResult tResult) {
        Task com_google_android_gms_tasks_zzn = new zzn();
        com_google_android_gms_tasks_zzn.setResult(tResult);
        return com_google_android_gms_tasks_zzn;
    }
}
