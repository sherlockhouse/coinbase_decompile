package com.coinbase.android.deposits;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class FiatTransactionsControllerModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final FiatTransactionsControllerModule module;

    public FiatTransactionsControllerModule_ProvidesControllerFactory(FiatTransactionsControllerModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(FiatTransactionsControllerModule module) {
        return proxyProvidesController(module);
    }

    public static FiatTransactionsControllerModule_ProvidesControllerFactory create(FiatTransactionsControllerModule module) {
        return new FiatTransactionsControllerModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(FiatTransactionsControllerModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
