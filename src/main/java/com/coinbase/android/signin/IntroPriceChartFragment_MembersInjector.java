package com.coinbase.android.signin;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class IntroPriceChartFragment_MembersInjector implements MembersInjector<IntroPriceChartFragment> {
    private final Provider<IntroPriceChartPresenter> mPresenterProvider;

    public IntroPriceChartFragment_MembersInjector(Provider<IntroPriceChartPresenter> mPresenterProvider) {
        this.mPresenterProvider = mPresenterProvider;
    }

    public static MembersInjector<IntroPriceChartFragment> create(Provider<IntroPriceChartPresenter> mPresenterProvider) {
        return new IntroPriceChartFragment_MembersInjector(mPresenterProvider);
    }

    public void injectMembers(IntroPriceChartFragment instance) {
        injectMPresenter(instance, (IntroPriceChartPresenter) this.mPresenterProvider.get());
    }

    public static void injectMPresenter(IntroPriceChartFragment instance, IntroPriceChartPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }
}
