package com.coinbase.android.settings.tiers;

import android.os.Bundle;
import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class InvestmentTiersRequirementsPresenter$$Lambda$1 implements Action1 {
    private final InvestmentTiersRequirementsPresenter arg$1;
    private final Bundle arg$2;

    private InvestmentTiersRequirementsPresenter$$Lambda$1(InvestmentTiersRequirementsPresenter investmentTiersRequirementsPresenter, Bundle bundle) {
        this.arg$1 = investmentTiersRequirementsPresenter;
        this.arg$2 = bundle;
    }

    public static Action1 lambdaFactory$(InvestmentTiersRequirementsPresenter investmentTiersRequirementsPresenter, Bundle bundle) {
        return new InvestmentTiersRequirementsPresenter$$Lambda$1(investmentTiersRequirementsPresenter, bundle);
    }

    public void call(Object obj) {
        InvestmentTiersRequirementsPresenter.lambda$onShow$0(this.arg$1, this.arg$2, (Pair) obj);
    }
}
