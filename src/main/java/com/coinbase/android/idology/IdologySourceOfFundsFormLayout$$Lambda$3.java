package com.coinbase.android.idology;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class IdologySourceOfFundsFormLayout$$Lambda$3 implements OnClickListener {
    private final IdologySourceOfFundsFormLayout arg$1;

    private IdologySourceOfFundsFormLayout$$Lambda$3(IdologySourceOfFundsFormLayout idologySourceOfFundsFormLayout) {
        this.arg$1 = idologySourceOfFundsFormLayout;
    }

    public static OnClickListener lambdaFactory$(IdologySourceOfFundsFormLayout idologySourceOfFundsFormLayout) {
        return new IdologySourceOfFundsFormLayout$$Lambda$3(idologySourceOfFundsFormLayout);
    }

    public void onClick(View view) {
        IdologySourceOfFundsFormLayout.lambda$setListeners$2(this.arg$1, view);
    }
}
