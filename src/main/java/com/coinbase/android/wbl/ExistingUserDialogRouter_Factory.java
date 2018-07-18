package com.coinbase.android.wbl;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ExistingUserDialogRouter_Factory implements Factory<ExistingUserDialogRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public ExistingUserDialogRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public ExistingUserDialogRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static ExistingUserDialogRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new ExistingUserDialogRouter((ActionBarController) controllerProvider.get());
    }

    public static ExistingUserDialogRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new ExistingUserDialogRouter_Factory(controllerProvider);
    }

    public static ExistingUserDialogRouter newExistingUserDialogRouter(ActionBarController controller) {
        return new ExistingUserDialogRouter(controller);
    }
}
