package com.coinbase.android.idology;

final /* synthetic */ class IdologyAddressFormLayout$$Lambda$8 implements Runnable {
    private final IdologyAddressFormLayout arg$1;

    private IdologyAddressFormLayout$$Lambda$8(IdologyAddressFormLayout idologyAddressFormLayout) {
        this.arg$1 = idologyAddressFormLayout;
    }

    public static Runnable lambdaFactory$(IdologyAddressFormLayout idologyAddressFormLayout) {
        return new IdologyAddressFormLayout$$Lambda$8(idologyAddressFormLayout);
    }

    public void run() {
        this.arg$1.mPresenter.onFormUpdated();
    }
}
