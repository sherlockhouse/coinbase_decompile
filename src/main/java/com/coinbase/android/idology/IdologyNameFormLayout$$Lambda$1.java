package com.coinbase.android.idology;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class IdologyNameFormLayout$$Lambda$1 implements OnClickListener {
    private final IdologyNameFormLayout arg$1;

    private IdologyNameFormLayout$$Lambda$1(IdologyNameFormLayout idologyNameFormLayout) {
        this.arg$1 = idologyNameFormLayout;
    }

    public static OnClickListener lambdaFactory$(IdologyNameFormLayout idologyNameFormLayout) {
        return new IdologyNameFormLayout$$Lambda$1(idologyNameFormLayout);
    }

    public void onClick(View view) {
        IdologyNameFormLayout.lambda$setListeners$0(this.arg$1, view);
    }
}
