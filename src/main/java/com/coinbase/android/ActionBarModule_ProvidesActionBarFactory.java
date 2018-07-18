package com.coinbase.android;

import android.support.v7.app.ActionBar;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ActionBarModule_ProvidesActionBarFactory implements Factory<ActionBar> {
    private final ActionBarModule module;

    public ActionBarModule_ProvidesActionBarFactory(ActionBarModule module) {
        this.module = module;
    }

    public ActionBar get() {
        return provideInstance(this.module);
    }

    public static ActionBar provideInstance(ActionBarModule module) {
        return proxyProvidesActionBar(module);
    }

    public static ActionBarModule_ProvidesActionBarFactory create(ActionBarModule module) {
        return new ActionBarModule_ProvidesActionBarFactory(module);
    }

    public static ActionBar proxyProvidesActionBar(ActionBarModule instance) {
        return (ActionBar) Preconditions.checkNotNull(instance.providesActionBar(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
