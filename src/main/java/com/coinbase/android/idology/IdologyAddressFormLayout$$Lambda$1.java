package com.coinbase.android.idology;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final /* synthetic */ class IdologyAddressFormLayout$$Lambda$1 implements OnFocusChangeListener {
    private final IdologyAddressFormLayout arg$1;

    private IdologyAddressFormLayout$$Lambda$1(IdologyAddressFormLayout idologyAddressFormLayout) {
        this.arg$1 = idologyAddressFormLayout;
    }

    public static OnFocusChangeListener lambdaFactory$(IdologyAddressFormLayout idologyAddressFormLayout) {
        return new IdologyAddressFormLayout$$Lambda$1(idologyAddressFormLayout);
    }

    public void onFocusChange(View view, boolean z) {
        IdologyAddressFormLayout.lambda$init$1(this.arg$1, view, z);
    }
}
