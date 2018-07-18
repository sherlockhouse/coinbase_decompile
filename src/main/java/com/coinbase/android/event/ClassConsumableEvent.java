package com.coinbase.android.event;

import java.util.HashSet;
import java.util.Set;

public class ClassConsumableEvent<T> {
    private final Set<Object> mConsumableSet;
    private final T mResult;

    public ClassConsumableEvent(T result) {
        this.mConsumableSet = new HashSet();
        this.mResult = result;
    }

    public ClassConsumableEvent() {
        this.mConsumableSet = new HashSet();
        this.mResult = null;
    }

    public boolean consumeIfNotConsumed(Object clazz) {
        boolean consumed;
        synchronized (this) {
            consumed = this.mConsumableSet.contains(clazz);
            if (!consumed) {
                this.mConsumableSet.add(clazz);
            }
        }
        return consumed;
    }

    public T get() {
        return this.mResult;
    }
}
