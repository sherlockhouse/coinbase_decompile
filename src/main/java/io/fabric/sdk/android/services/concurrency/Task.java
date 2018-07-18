package io.fabric.sdk.android.services.concurrency;

public interface Task {
    boolean isFinished();

    void setError(Throwable th);

    void setFinished(boolean z);
}
