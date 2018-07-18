package org.joda.time.base;

public abstract class BaseLocal extends AbstractPartial {
    protected abstract long getLocalMillis();

    protected BaseLocal() {
    }
}
