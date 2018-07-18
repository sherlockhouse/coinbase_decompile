package com.coinbase.android.widgets;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class WidgetChooseCurrencyActivity_MembersInjector implements MembersInjector<WidgetChooseCurrencyActivity> {
    private final Provider<LoginManager> mLoginManagerProvider;

    public WidgetChooseCurrencyActivity_MembersInjector(Provider<LoginManager> mLoginManagerProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
    }

    public static MembersInjector<WidgetChooseCurrencyActivity> create(Provider<LoginManager> mLoginManagerProvider) {
        return new WidgetChooseCurrencyActivity_MembersInjector(mLoginManagerProvider);
    }

    public void injectMembers(WidgetChooseCurrencyActivity instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
    }

    public static void injectMLoginManager(WidgetChooseCurrencyActivity instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }
}
