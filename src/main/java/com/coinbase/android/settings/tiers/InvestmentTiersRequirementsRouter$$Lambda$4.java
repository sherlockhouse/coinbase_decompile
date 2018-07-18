package com.coinbase.android.settings.tiers;

import com.coinbase.api.internal.models.tiers.Requirement.Status;
import rx.functions.Func1;

final /* synthetic */ class InvestmentTiersRequirementsRouter$$Lambda$4 implements Func1 {
    private final InvestmentTiersRequirementsRouter arg$1;

    private InvestmentTiersRequirementsRouter$$Lambda$4(InvestmentTiersRequirementsRouter investmentTiersRequirementsRouter) {
        this.arg$1 = investmentTiersRequirementsRouter;
    }

    public static Func1 lambdaFactory$(InvestmentTiersRequirementsRouter investmentTiersRequirementsRouter) {
        return new InvestmentTiersRequirementsRouter$$Lambda$4(investmentTiersRequirementsRouter);
    }

    public Object call(Object obj) {
        return InvestmentTiersRequirementsRouter.lambda$initRequirementControllerMap$3(this.arg$1, (Status) obj);
    }
}
