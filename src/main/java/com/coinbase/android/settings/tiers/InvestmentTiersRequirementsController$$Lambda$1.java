package com.coinbase.android.settings.tiers;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class InvestmentTiersRequirementsController$$Lambda$1 implements OnClickListener {
    private final InvestmentTiersRequirementsController arg$1;

    private InvestmentTiersRequirementsController$$Lambda$1(InvestmentTiersRequirementsController investmentTiersRequirementsController) {
        this.arg$1 = investmentTiersRequirementsController;
    }

    public static OnClickListener lambdaFactory$(InvestmentTiersRequirementsController investmentTiersRequirementsController) {
        return new InvestmentTiersRequirementsController$$Lambda$1(investmentTiersRequirementsController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onContinueClicked();
    }
}
