package com.crashlytics.android.answers;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class BackgroundManager {
    final AtomicReference<ScheduledFuture<?>> backgroundFutureRef = new AtomicReference();
    private final ScheduledExecutorService executorService;
    private volatile boolean flushOnBackground = true;
    boolean inBackground = true;
    private final List<Listener> listeners = new ArrayList();

    public interface Listener {
        void onBackground();
    }

    public BackgroundManager(ScheduledExecutorService executorService) {
        this.executorService = executorService;
    }

    public void setFlushOnBackground(boolean flushOnBackground) {
        this.flushOnBackground = flushOnBackground;
    }

    private void notifyBackground() {
        for (Listener listener : this.listeners) {
            listener.onBackground();
        }
    }

    public void registerListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void onActivityResumed() {
        this.inBackground = false;
        ScheduledFuture backgroundFuture = (ScheduledFuture) this.backgroundFutureRef.getAndSet(null);
        if (backgroundFuture != null) {
            backgroundFuture.cancel(false);
        }
    }

    public void onActivityPaused() {
        if (this.flushOnBackground && !this.inBackground) {
            this.inBackground = true;
            try {
                this.backgroundFutureRef.compareAndSet(null, this.executorService.schedule(new Runnable() {
                    public void run() {
                        BackgroundManager.this.backgroundFutureRef.set(null);
                        BackgroundManager.this.notifyBackground();
                    }
                }, 5000, TimeUnit.MILLISECONDS));
            } catch (RejectedExecutionException e) {
                Fabric.getLogger().d("Answers", "Failed to schedule background detector", e);
            }
        }
    }
}
