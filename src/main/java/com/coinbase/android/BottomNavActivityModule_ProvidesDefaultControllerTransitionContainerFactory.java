package com.coinbase.android;

import com.coinbase.android.ui.ControllerTransitionContainer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class BottomNavActivityModule_ProvidesDefaultControllerTransitionContainerFactory implements Factory<ControllerTransitionContainer> {
    private final BottomNavActivityModule module;

    public BottomNavActivityModule_ProvidesDefaultControllerTransitionContainerFactory(BottomNavActivityModule module) {
        this.module = module;
    }

    public ControllerTransitionContainer get() {
        return provideInstance(this.module);
    }

    public static ControllerTransitionContainer provideInstance(BottomNavActivityModule module) {
        return proxyProvidesDefaultControllerTransitionContainer(module);
    }

    public static BottomNavActivityModule_ProvidesDefaultControllerTransitionContainerFactory create(BottomNavActivityModule module) {
        return new BottomNavActivityModule_ProvidesDefaultControllerTransitionContainerFactory(module);
    }

    public static ControllerTransitionContainer proxyProvidesDefaultControllerTransitionContainer(BottomNavActivityModule instance) {
        return (ControllerTransitionContainer) Preconditions.checkNotNull(instance.providesDefaultControllerTransitionContainer(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
