package com.coinbase.android.idology;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class IdologyAddressFormLayout$$Lambda$2 implements OnClickListener {
    private final IdologyAddressFormLayout arg$1;

    private IdologyAddressFormLayout$$Lambda$2(IdologyAddressFormLayout idologyAddressFormLayout) {
        this.arg$1 = idologyAddressFormLayout;
    }

    public static OnClickListener lambdaFactory$(IdologyAddressFormLayout idologyAddressFormLayout) {
        return new IdologyAddressFormLayout$$Lambda$2(idologyAddressFormLayout);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onAutoCompleteStarted();
    }
}
