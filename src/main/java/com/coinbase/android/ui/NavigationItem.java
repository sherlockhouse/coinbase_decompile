package com.coinbase.android.ui;

import rx.functions.Func0;

public abstract class NavigationItem {
    private boolean mHasNotification = false;

    abstract Runnable getClearHasNotificationFunc();

    abstract Func0<Boolean> getHasNotificationFunc();

    public abstract int getIconNotification();

    public abstract int getTitle();

    public boolean hasNotification() {
        return ((Boolean) getHasNotificationFunc().call()).booleanValue();
    }

    public void clearHasNotification() {
        getClearHasNotificationFunc().run();
    }
}
