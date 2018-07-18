package com.bumptech.glide.request;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RequestFutureTarget<R> implements FutureTarget<R>, Runnable {
    private static final Waiter DEFAULT_WAITER = new Waiter();
    private final boolean assertBackgroundThread;
    private final int height;
    private boolean isCancelled;
    private boolean loadFailed;
    private final Handler mainHandler;
    private Request request;
    private R resource;
    private boolean resultReceived;
    private final Waiter waiter;
    private final int width;

    static class Waiter {
        Waiter() {
        }

        public void waitForTimeout(Object toWaitOn, long timeoutMillis) throws InterruptedException {
            toWaitOn.wait(timeoutMillis);
        }

        public void notifyAll(Object toNotify) {
            toNotify.notifyAll();
        }
    }

    public RequestFutureTarget(Handler mainHandler, int width, int height) {
        this(mainHandler, width, height, true, DEFAULT_WAITER);
    }

    RequestFutureTarget(Handler mainHandler, int width, int height, boolean assertBackgroundThread, Waiter waiter) {
        this.mainHandler = mainHandler;
        this.width = width;
        this.height = height;
        this.assertBackgroundThread = assertBackgroundThread;
        this.waiter = waiter;
    }

    public synchronized boolean cancel(boolean mayInterruptIfRunning) {
        boolean z = true;
        synchronized (this) {
            if (isDone()) {
                z = false;
            } else {
                this.isCancelled = true;
                this.waiter.notifyAll(this);
                if (mayInterruptIfRunning) {
                    clearOnMainThread();
                }
            }
        }
        return z;
    }

    public synchronized boolean isCancelled() {
        return this.isCancelled;
    }

    public synchronized boolean isDone() {
        boolean z;
        z = this.isCancelled || this.resultReceived;
        return z;
    }

    public R get() throws InterruptedException, ExecutionException {
        try {
            return doGet(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    public R get(long time, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return doGet(Long.valueOf(timeUnit.toMillis(time)));
    }

    public void getSize(SizeReadyCallback cb) {
        cb.onSizeReady(this.width, this.height);
    }

    public void removeCallback(SizeReadyCallback cb) {
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return this.request;
    }

    public void onLoadCleared(Drawable placeholder) {
    }

    public void onLoadStarted(Drawable placeholder) {
    }

    public synchronized void onLoadFailed(Drawable errorDrawable) {
        this.loadFailed = true;
        this.waiter.notifyAll(this);
    }

    public synchronized void onResourceReady(R resource, Transition<? super R> transition) {
        this.resultReceived = true;
        this.resource = resource;
        this.waiter.notifyAll(this);
    }

    private synchronized R doGet(Long timeoutMillis) throws ExecutionException, InterruptedException, TimeoutException {
        R r;
        if (this.assertBackgroundThread && !isDone()) {
            Util.assertBackgroundThread();
        }
        if (this.isCancelled) {
            throw new CancellationException();
        } else if (this.loadFailed) {
            throw new ExecutionException(new IllegalStateException("Load failed"));
        } else if (this.resultReceived) {
            r = this.resource;
        } else {
            if (timeoutMillis == null) {
                this.waiter.waitForTimeout(this, 0);
            } else if (timeoutMillis.longValue() > 0) {
                this.waiter.waitForTimeout(this, timeoutMillis.longValue());
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            } else if (this.loadFailed) {
                throw new ExecutionException(new IllegalStateException("Load failed"));
            } else if (this.isCancelled) {
                throw new CancellationException();
            } else if (this.resultReceived) {
                r = this.resource;
            } else {
                throw new TimeoutException();
            }
        }
        return r;
    }

    public void run() {
        if (this.request != null) {
            this.request.clear();
            this.request = null;
        }
    }

    private void clearOnMainThread() {
        this.mainHandler.post(this);
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onDestroy() {
    }
}
