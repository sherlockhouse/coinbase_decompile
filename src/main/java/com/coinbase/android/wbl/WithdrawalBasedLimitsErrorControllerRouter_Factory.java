package com.coinbase.android.wbl;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class WithdrawalBasedLimitsErrorControllerRouter_Factory implements Factory<WithdrawalBasedLimitsErrorControllerRouter> {
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<LimitExceededTrackingContext> trackingContextProvider;

    public WithdrawalBasedLimitsErrorControllerRouter_Factory(Provider<ActionBarController> controllerProvider, Provider<LimitExceededTrackingContext> trackingContextProvider) {
        this.controllerProvider = controllerProvider;
        this.trackingContextProvider = trackingContextProvider;
    }

    public WithdrawalBasedLimitsErrorControllerRouter get() {
        return provideInstance(this.controllerProvider, this.trackingContextProvider);
    }

    public static WithdrawalBasedLimitsErrorControllerRouter provideInstance(Provider<ActionBarController> controllerProvider, Provider<LimitExceededTrackingContext> trackingContextProvider) {
        return new WithdrawalBasedLimitsErrorControllerRouter((ActionBarController) controllerProvider.get(), (LimitExceededTrackingContext) trackingContextProvider.get());
    }

    public static WithdrawalBasedLimitsErrorControllerRouter_Factory create(Provider<ActionBarController> controllerProvider, Provider<LimitExceededTrackingContext> trackingContextProvider) {
        return new WithdrawalBasedLimitsErrorControllerRouter_Factory(controllerProvider, trackingContextProvider);
    }

    public static WithdrawalBasedLimitsErrorControllerRouter newWithdrawalBasedLimitsErrorControllerRouter(ActionBarController controller, LimitExceededTrackingContext trackingContext) {
        return new WithdrawalBasedLimitsErrorControllerRouter(controller, trackingContext);
    }
}
