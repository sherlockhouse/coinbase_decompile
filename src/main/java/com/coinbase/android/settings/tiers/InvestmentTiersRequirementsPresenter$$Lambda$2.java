package com.coinbase.android.settings.tiers;

import rx.functions.Action1;

final /* synthetic */ class InvestmentTiersRequirementsPresenter$$Lambda$2 implements Action1 {
    private final InvestmentTiersRequirementsPresenter arg$1;

    private InvestmentTiersRequirementsPresenter$$Lambda$2(InvestmentTiersRequirementsPresenter investmentTiersRequirementsPresenter) {
        this.arg$1 = investmentTiersRequirementsPresenter;
    }

    public static Action1 lambdaFactory$(InvestmentTiersRequirementsPresenter investmentTiersRequirementsPresenter) {
        return new InvestmentTiersRequirementsPresenter$$Lambda$2(investmentTiersRequirementsPresenter);
    }

    public void call(Object obj) {
        InvestmentTiersRequirementsPresenter.lambda$onShow$1(this.arg$1, (Throwable) obj);
    }
}
