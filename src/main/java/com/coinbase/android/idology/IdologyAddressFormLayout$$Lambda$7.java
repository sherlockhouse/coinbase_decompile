package com.coinbase.android.idology;

final /* synthetic */ class IdologyAddressFormLayout$$Lambda$7 implements Runnable {
    private final IdologyAddressFormLayout arg$1;

    private IdologyAddressFormLayout$$Lambda$7(IdologyAddressFormLayout idologyAddressFormLayout) {
        this.arg$1 = idologyAddressFormLayout;
    }

    public static Runnable lambdaFactory$(IdologyAddressFormLayout idologyAddressFormLayout) {
        return new IdologyAddressFormLayout$$Lambda$7(idologyAddressFormLayout);
    }

    public void run() {
        this.arg$1.mPresenter.onFormUpdated();
    }
}
