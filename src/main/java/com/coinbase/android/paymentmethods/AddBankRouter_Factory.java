package com.coinbase.android.paymentmethods;

import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.SuccessRouter;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AddBankRouter_Factory implements Factory<AddBankRouter> {
    private final Provider<AppCompatActivity> activityProvider;
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<AddBankPickerScreen> screenProvider;
    private final Provider<SuccessRouter> successRouterProvider;

    public AddBankRouter_Factory(Provider<ActionBarController> controllerProvider, Provider<AppCompatActivity> activityProvider, Provider<AddBankPickerScreen> screenProvider, Provider<SuccessRouter> successRouterProvider) {
        this.controllerProvider = controllerProvider;
        this.activityProvider = activityProvider;
        this.screenProvider = screenProvider;
        this.successRouterProvider = successRouterProvider;
    }

    public AddBankRouter get() {
        return provideInstance(this.controllerProvider, this.activityProvider, this.screenProvider, this.successRouterProvider);
    }

    public static AddBankRouter provideInstance(Provider<ActionBarController> controllerProvider, Provider<AppCompatActivity> activityProvider, Provider<AddBankPickerScreen> screenProvider, Provider<SuccessRouter> successRouterProvider) {
        return new AddBankRouter((ActionBarController) controllerProvider.get(), (AppCompatActivity) activityProvider.get(), (AddBankPickerScreen) screenProvider.get(), (SuccessRouter) successRouterProvider.get());
    }

    public static AddBankRouter_Factory create(Provider<ActionBarController> controllerProvider, Provider<AppCompatActivity> activityProvider, Provider<AddBankPickerScreen> screenProvider, Provider<SuccessRouter> successRouterProvider) {
        return new AddBankRouter_Factory(controllerProvider, activityProvider, screenProvider, successRouterProvider);
    }

    public static AddBankRouter newAddBankRouter(ActionBarController controller, AppCompatActivity activity, AddBankPickerScreen screen, SuccessRouter successRouter) {
        return new AddBankRouter(controller, activity, screen, successRouter);
    }
}
