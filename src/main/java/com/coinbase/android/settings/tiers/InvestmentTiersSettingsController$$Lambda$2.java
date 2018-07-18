package com.coinbase.android.settings.tiers;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class InvestmentTiersSettingsController$$Lambda$2 implements OnClickListener {
    private final InvestmentTiersSettingsController arg$1;

    private InvestmentTiersSettingsController$$Lambda$2(InvestmentTiersSettingsController investmentTiersSettingsController) {
        this.arg$1 = investmentTiersSettingsController;
    }

    public static OnClickListener lambdaFactory$(InvestmentTiersSettingsController investmentTiersSettingsController) {
        return new InvestmentTiersSettingsController$$Lambda$2(investmentTiersSettingsController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onIncreaseLimitsClicked();
    }
}
