package com.coinbase.android.idology;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class IdologySourceOfFundsFormLayout$$Lambda$2 implements OnClickListener {
    private final IdologySourceOfFundsFormLayout arg$1;

    private IdologySourceOfFundsFormLayout$$Lambda$2(IdologySourceOfFundsFormLayout idologySourceOfFundsFormLayout) {
        this.arg$1 = idologySourceOfFundsFormLayout;
    }

    public static OnClickListener lambdaFactory$(IdologySourceOfFundsFormLayout idologySourceOfFundsFormLayout) {
        return new IdologySourceOfFundsFormLayout$$Lambda$2(idologySourceOfFundsFormLayout);
    }

    public void onClick(View view) {
        IdologySourceOfFundsFormLayout.lambda$setListeners$1(this.arg$1, view);
    }
}
