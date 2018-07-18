package com.coinbase.android.widgets;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class WidgetChooseAccountActivity_MembersInjector implements MembersInjector<WidgetChooseAccountActivity> {
    private final Provider<LoginManager> loginManagerProvider;

    public WidgetChooseAccountActivity_MembersInjector(Provider<LoginManager> loginManagerProvider) {
        this.loginManagerProvider = loginManagerProvider;
    }

    public static MembersInjector<WidgetChooseAccountActivity> create(Provider<LoginManager> loginManagerProvider) {
        return new WidgetChooseAccountActivity_MembersInjector(loginManagerProvider);
    }

    public void injectMembers(WidgetChooseAccountActivity instance) {
        injectLoginManager(instance, (LoginManager) this.loginManagerProvider.get());
    }

    public static void injectLoginManager(WidgetChooseAccountActivity instance, LoginManager loginManager) {
        instance.loginManager = loginManager;
    }
}
