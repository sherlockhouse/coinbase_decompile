package com.coinbase.android.utils;

import dagger.internal.Factory;

public final class ActivityPermissionCheckUtils_Factory implements Factory<ActivityPermissionCheckUtils> {
    private static final ActivityPermissionCheckUtils_Factory INSTANCE = new ActivityPermissionCheckUtils_Factory();

    public ActivityPermissionCheckUtils get() {
        return provideInstance();
    }

    public static ActivityPermissionCheckUtils provideInstance() {
        return new ActivityPermissionCheckUtils();
    }

    public static ActivityPermissionCheckUtils_Factory create() {
        return INSTANCE;
    }

    public static ActivityPermissionCheckUtils newActivityPermissionCheckUtils() {
        return new ActivityPermissionCheckUtils();
    }
}
