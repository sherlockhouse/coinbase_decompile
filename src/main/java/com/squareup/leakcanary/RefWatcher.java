package com.squareup.leakcanary;

public final class RefWatcher {
    public static final RefWatcher DISABLED = new RefWatcher();

    private RefWatcher() {
    }
}
