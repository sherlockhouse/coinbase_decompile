package com.coinbase.android.idology;

import com.coinbase.android.ui.BackgroundDimmer;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class IdologySourceOfFundsFormLayout_MembersInjector implements MembersInjector<IdologySourceOfFundsFormLayout> {
    private final Provider<BackgroundDimmer> mBackgroundDimmerProvider;
    private final Provider<IdologySourceOfFundsFormPresenter> mPresenterProvider;

    public IdologySourceOfFundsFormLayout_MembersInjector(Provider<IdologySourceOfFundsFormPresenter> mPresenterProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider) {
        this.mPresenterProvider = mPresenterProvider;
        this.mBackgroundDimmerProvider = mBackgroundDimmerProvider;
    }

    public static MembersInjector<IdologySourceOfFundsFormLayout> create(Provider<IdologySourceOfFundsFormPresenter> mPresenterProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider) {
        return new IdologySourceOfFundsFormLayout_MembersInjector(mPresenterProvider, mBackgroundDimmerProvider);
    }

    public void injectMembers(IdologySourceOfFundsFormLayout instance) {
        injectMPresenter(instance, (IdologySourceOfFundsFormPresenter) this.mPresenterProvider.get());
        injectMBackgroundDimmer(instance, (BackgroundDimmer) this.mBackgroundDimmerProvider.get());
    }

    public static void injectMPresenter(IdologySourceOfFundsFormLayout instance, IdologySourceOfFundsFormPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMBackgroundDimmer(IdologySourceOfFundsFormLayout instance, BackgroundDimmer mBackgroundDimmer) {
        instance.mBackgroundDimmer = mBackgroundDimmer;
    }
}
