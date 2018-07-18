package com.coinbase.android.settings.tiers;

import android.os.Bundle;
import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class InvestmentTiersSettingsPresenter$$Lambda$1 implements Action1 {
    private final InvestmentTiersSettingsPresenter arg$1;
    private final Bundle arg$2;

    private InvestmentTiersSettingsPresenter$$Lambda$1(InvestmentTiersSettingsPresenter investmentTiersSettingsPresenter, Bundle bundle) {
        this.arg$1 = investmentTiersSettingsPresenter;
        this.arg$2 = bundle;
    }

    public static Action1 lambdaFactory$(InvestmentTiersSettingsPresenter investmentTiersSettingsPresenter, Bundle bundle) {
        return new InvestmentTiersSettingsPresenter$$Lambda$1(investmentTiersSettingsPresenter, bundle);
    }

    public void call(Object obj) {
        InvestmentTiersSettingsPresenter.lambda$onShow$0(this.arg$1, this.arg$2, (Pair) obj);
    }
}
