package com.coinbase.android;

import android.support.v7.widget.Toolbar;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ActionBarModule_ProvidesToolbarFactory implements Factory<Toolbar> {
    private final ActionBarModule module;

    public ActionBarModule_ProvidesToolbarFactory(ActionBarModule module) {
        this.module = module;
    }

    public Toolbar get() {
        return provideInstance(this.module);
    }

    public static Toolbar provideInstance(ActionBarModule module) {
        return proxyProvidesToolbar(module);
    }

    public static ActionBarModule_ProvidesToolbarFactory create(ActionBarModule module) {
        return new ActionBarModule_ProvidesToolbarFactory(module);
    }

    public static Toolbar proxyProvidesToolbar(ActionBarModule instance) {
        return (Toolbar) Preconditions.checkNotNull(instance.providesToolbar(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
