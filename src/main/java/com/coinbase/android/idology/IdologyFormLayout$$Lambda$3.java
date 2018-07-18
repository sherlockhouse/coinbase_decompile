package com.coinbase.android.idology;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class IdologyFormLayout$$Lambda$3 implements OnClickListener {
    private final IdologyFormLayout arg$1;

    private IdologyFormLayout$$Lambda$3(IdologyFormLayout idologyFormLayout) {
        this.arg$1 = idologyFormLayout;
    }

    public static OnClickListener lambdaFactory$(IdologyFormLayout idologyFormLayout) {
        return new IdologyFormLayout$$Lambda$3(idologyFormLayout);
    }

    public void onClick(View view) {
        IdologyFormLayout.lambda$setListeners$2(this.arg$1, view);
    }
}
