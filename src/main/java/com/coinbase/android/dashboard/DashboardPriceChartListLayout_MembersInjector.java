package com.coinbase.android.dashboard;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class DashboardPriceChartListLayout_MembersInjector implements MembersInjector<DashboardPriceChartListLayout> {
    private final Provider<DashboardPriceChartListPresenter> mPresenterProvider;

    public DashboardPriceChartListLayout_MembersInjector(Provider<DashboardPriceChartListPresenter> mPresenterProvider) {
        this.mPresenterProvider = mPresenterProvider;
    }

    public static MembersInjector<DashboardPriceChartListLayout> create(Provider<DashboardPriceChartListPresenter> mPresenterProvider) {
        return new DashboardPriceChartListLayout_MembersInjector(mPresenterProvider);
    }

    public void injectMembers(DashboardPriceChartListLayout instance) {
        injectMPresenter(instance, (DashboardPriceChartListPresenter) this.mPresenterProvider.get());
    }

    public static void injectMPresenter(DashboardPriceChartListLayout instance, DashboardPriceChartListPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }
}
