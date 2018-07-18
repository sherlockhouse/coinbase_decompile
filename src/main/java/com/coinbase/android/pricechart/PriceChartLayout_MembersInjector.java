package com.coinbase.android.pricechart;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class PriceChartLayout_MembersInjector implements MembersInjector<PriceChartLayout> {
    private final Provider<PriceChartPresenter> mPresenterProvider;

    public PriceChartLayout_MembersInjector(Provider<PriceChartPresenter> mPresenterProvider) {
        this.mPresenterProvider = mPresenterProvider;
    }

    public static MembersInjector<PriceChartLayout> create(Provider<PriceChartPresenter> mPresenterProvider) {
        return new PriceChartLayout_MembersInjector(mPresenterProvider);
    }

    public void injectMembers(PriceChartLayout instance) {
        injectMPresenter(instance, (PriceChartPresenter) this.mPresenterProvider.get());
    }

    public static void injectMPresenter(PriceChartLayout instance, PriceChartPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }
}
