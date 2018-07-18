package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

public abstract class Task<TResult> {
    public Task<TResult> addOnCompleteListener(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    public abstract Exception getException();

    public abstract boolean isSuccessful();
}
