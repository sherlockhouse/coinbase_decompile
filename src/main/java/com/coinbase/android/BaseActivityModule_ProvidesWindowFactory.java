package com.coinbase.android;

import android.view.Window;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class BaseActivityModule_ProvidesWindowFactory implements Factory<Window> {
    private final BaseActivityModule module;

    public BaseActivityModule_ProvidesWindowFactory(BaseActivityModule module) {
        this.module = module;
    }

    public Window get() {
        return provideInstance(this.module);
    }

    public static Window provideInstance(BaseActivityModule module) {
        return proxyProvidesWindow(module);
    }

    public static BaseActivityModule_ProvidesWindowFactory create(BaseActivityModule module) {
        return new BaseActivityModule_ProvidesWindowFactory(module);
    }

    public static Window proxyProvidesWindow(BaseActivityModule instance) {
        return (Window) Preconditions.checkNotNull(instance.providesWindow(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
