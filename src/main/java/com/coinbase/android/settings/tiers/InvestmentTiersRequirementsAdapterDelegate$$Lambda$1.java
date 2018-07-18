package com.coinbase.android.settings.tiers;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.api.internal.models.tiers.Requirement;

final /* synthetic */ class InvestmentTiersRequirementsAdapterDelegate$$Lambda$1 implements OnClickListener {
    private final InvestmentTiersRequirementsAdapterDelegate arg$1;
    private final Requirement arg$2;

    private InvestmentTiersRequirementsAdapterDelegate$$Lambda$1(InvestmentTiersRequirementsAdapterDelegate investmentTiersRequirementsAdapterDelegate, Requirement requirement) {
        this.arg$1 = investmentTiersRequirementsAdapterDelegate;
        this.arg$2 = requirement;
    }

    public static OnClickListener lambdaFactory$(InvestmentTiersRequirementsAdapterDelegate investmentTiersRequirementsAdapterDelegate, Requirement requirement) {
        return new InvestmentTiersRequirementsAdapterDelegate$$Lambda$1(investmentTiersRequirementsAdapterDelegate, requirement);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onRequirementClicked(this.arg$2);
    }
}
