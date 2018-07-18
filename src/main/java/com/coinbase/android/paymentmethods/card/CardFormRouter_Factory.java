package com.coinbase.android.paymentmethods.card;

import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.SuccessRouter;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CardFormRouter_Factory implements Factory<CardFormRouter> {
    private final Provider<AppCompatActivity> activityProvider;
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<CardFormScreen> screenProvider;
    private final Provider<SuccessRouter> successRouterProvider;

    public CardFormRouter_Factory(Provider<CardFormScreen> screenProvider, Provider<ActionBarController> controllerProvider, Provider<AppCompatActivity> activityProvider, Provider<SuccessRouter> successRouterProvider) {
        this.screenProvider = screenProvider;
        this.controllerProvider = controllerProvider;
        this.activityProvider = activityProvider;
        this.successRouterProvider = successRouterProvider;
    }

    public CardFormRouter get() {
        return provideInstance(this.screenProvider, this.controllerProvider, this.activityProvider, this.successRouterProvider);
    }

    public static CardFormRouter provideInstance(Provider<CardFormScreen> screenProvider, Provider<ActionBarController> controllerProvider, Provider<AppCompatActivity> activityProvider, Provider<SuccessRouter> successRouterProvider) {
        return new CardFormRouter((CardFormScreen) screenProvider.get(), (ActionBarController) controllerProvider.get(), (AppCompatActivity) activityProvider.get(), (SuccessRouter) successRouterProvider.get());
    }

    public static CardFormRouter_Factory create(Provider<CardFormScreen> screenProvider, Provider<ActionBarController> controllerProvider, Provider<AppCompatActivity> activityProvider, Provider<SuccessRouter> successRouterProvider) {
        return new CardFormRouter_Factory(screenProvider, controllerProvider, activityProvider, successRouterProvider);
    }

    public static CardFormRouter newCardFormRouter(Object screen, ActionBarController controller, AppCompatActivity activity, SuccessRouter successRouter) {
        return new CardFormRouter((CardFormScreen) screen, controller, activity, successRouter);
    }
}
