package com.coinbase.android.transfers;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SendRouter_Factory implements Factory<SendRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public SendRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public SendRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static SendRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new SendRouter((ActionBarController) controllerProvider.get());
    }

    public static SendRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new SendRouter_Factory(controllerProvider);
    }

    public static SendRouter newSendRouter(ActionBarController controller) {
        return new SendRouter(controller);
    }
}
