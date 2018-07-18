package com.coinbase.android.transfers;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DelayedTransactionsControllerModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final DelayedTransactionsControllerModule module;

    public DelayedTransactionsControllerModule_ProvidesControllerFactory(DelayedTransactionsControllerModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(DelayedTransactionsControllerModule module) {
        return proxyProvidesController(module);
    }

    public static DelayedTransactionsControllerModule_ProvidesControllerFactory create(DelayedTransactionsControllerModule module) {
        return new DelayedTransactionsControllerModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(DelayedTransactionsControllerModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
