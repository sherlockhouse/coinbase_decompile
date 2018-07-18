package com.coinbase.android.dashboard;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class DashboardTabPeriodLayout_MembersInjector implements MembersInjector<DashboardTabPeriodLayout> {
    private final Provider<DashboardTabPeriodPresenter> mPresenterProvider;

    public DashboardTabPeriodLayout_MembersInjector(Provider<DashboardTabPeriodPresenter> mPresenterProvider) {
        this.mPresenterProvider = mPresenterProvider;
    }

    public static MembersInjector<DashboardTabPeriodLayout> create(Provider<DashboardTabPeriodPresenter> mPresenterProvider) {
        return new DashboardTabPeriodLayout_MembersInjector(mPresenterProvider);
    }

    public void injectMembers(DashboardTabPeriodLayout instance) {
        injectMPresenter(instance, (DashboardTabPeriodPresenter) this.mPresenterProvider.get());
    }

    public static void injectMPresenter(DashboardTabPeriodLayout instance, DashboardTabPeriodPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }
}
