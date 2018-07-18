package com.coinbase.android.idology;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class IdologyAddressFormLayout$$Lambda$3 implements OnClickListener {
    private final IdologyAddressFormLayout arg$1;

    private IdologyAddressFormLayout$$Lambda$3(IdologyAddressFormLayout idologyAddressFormLayout) {
        this.arg$1 = idologyAddressFormLayout;
    }

    public static OnClickListener lambdaFactory$(IdologyAddressFormLayout idologyAddressFormLayout) {
        return new IdologyAddressFormLayout$$Lambda$3(idologyAddressFormLayout);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onEnterManuallyClicked();
    }
}
