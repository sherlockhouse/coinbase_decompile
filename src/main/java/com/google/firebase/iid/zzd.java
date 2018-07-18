package com.google.firebase.iid;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Intent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class zzd {
    final Intent intent;
    private final PendingResult zznew;
    private boolean zznex = false;
    private final ScheduledFuture<?> zzney;

    zzd(Intent intent, PendingResult pendingResult, ScheduledExecutorService scheduledExecutorService) {
        this.intent = intent;
        this.zznew = pendingResult;
        this.zzney = scheduledExecutorService.schedule(new zze(this, intent), 9500, TimeUnit.MILLISECONDS);
    }

    final synchronized void finish() {
        if (!this.zznex) {
            this.zznew.finish();
            this.zzney.cancel(false);
            this.zznex = true;
        }
    }
}
