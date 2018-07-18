package com.coinbase.android.settings.gdpr;

import com.coinbase.android.ui.SuccessRouter;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class GdprRequestSentPresenter_Factory implements Factory<GdprRequestSentPresenter> {
    private final Provider<SuccessRouter> successRouterProvider;

    public GdprRequestSentPresenter_Factory(Provider<SuccessRouter> successRouterProvider) {
        this.successRouterProvider = successRouterProvider;
    }

    public GdprRequestSentPresenter get() {
        return provideInstance(this.successRouterProvider);
    }

    public static GdprRequestSentPresenter provideInstance(Provider<SuccessRouter> successRouterProvider) {
        return new GdprRequestSentPresenter((SuccessRouter) successRouterProvider.get());
    }

    public static GdprRequestSentPresenter_Factory create(Provider<SuccessRouter> successRouterProvider) {
        return new GdprRequestSentPresenter_Factory(successRouterProvider);
    }

    public static GdprRequestSentPresenter newGdprRequestSentPresenter(SuccessRouter successRouter) {
        return new GdprRequestSentPresenter(successRouter);
    }
}
