package com.coinbase.android.settings.tiers;

import android.os.Bundle;
import com.coinbase.android.paymentmethods.PaymentMethodsController;
import rx.functions.Func1;

final /* synthetic */ class InvestmentTiersRequirementsRouter$$Lambda$5 implements Func1 {
    private final InvestmentTiersRequirementsRouter arg$1;

    private InvestmentTiersRequirementsRouter$$Lambda$5(InvestmentTiersRequirementsRouter investmentTiersRequirementsRouter) {
        this.arg$1 = investmentTiersRequirementsRouter;
    }

    public static Func1 lambdaFactory$(InvestmentTiersRequirementsRouter investmentTiersRequirementsRouter) {
        return new InvestmentTiersRequirementsRouter$$Lambda$5(investmentTiersRequirementsRouter);
    }

    public Object call(Object obj) {
        return new PaymentMethodsController(this.arg$1.mController.appendModalArgsWithRoot(new Bundle()));
    }
}
