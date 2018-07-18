package com.google.android.gms.tasks;

public class TaskCompletionSource<TResult> {
    private final zzn<TResult> zzkgh = new zzn();

    public Task<TResult> getTask() {
        return this.zzkgh;
    }

    public void setException(Exception exception) {
        this.zzkgh.setException(exception);
    }

    public void setResult(TResult tResult) {
        this.zzkgh.setResult(tResult);
    }

    public boolean trySetException(Exception exception) {
        return this.zzkgh.trySetException(exception);
    }

    public boolean trySetResult(TResult tResult) {
        return this.zzkgh.trySetResult(tResult);
    }
}
