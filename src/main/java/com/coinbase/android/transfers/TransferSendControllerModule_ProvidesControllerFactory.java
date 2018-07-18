package com.coinbase.android.transfers;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class TransferSendControllerModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final TransferSendControllerModule module;

    public TransferSendControllerModule_ProvidesControllerFactory(TransferSendControllerModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(TransferSendControllerModule module) {
        return proxyProvidesController(module);
    }

    public static TransferSendControllerModule_ProvidesControllerFactory create(TransferSendControllerModule module) {
        return new TransferSendControllerModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(TransferSendControllerModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
