package com.coinbase.android.settings.tiers;

import rx.functions.Action1;

final /* synthetic */ class InvestmentTiersSettingsPresenter$$Lambda$2 implements Action1 {
    private final InvestmentTiersSettingsPresenter arg$1;

    private InvestmentTiersSettingsPresenter$$Lambda$2(InvestmentTiersSettingsPresenter investmentTiersSettingsPresenter) {
        this.arg$1 = investmentTiersSettingsPresenter;
    }

    public static Action1 lambdaFactory$(InvestmentTiersSettingsPresenter investmentTiersSettingsPresenter) {
        return new InvestmentTiersSettingsPresenter$$Lambda$2(investmentTiersSettingsPresenter);
    }

    public void call(Object obj) {
        InvestmentTiersSettingsPresenter.lambda$onShow$1(this.arg$1, (Throwable) obj);
    }
}
