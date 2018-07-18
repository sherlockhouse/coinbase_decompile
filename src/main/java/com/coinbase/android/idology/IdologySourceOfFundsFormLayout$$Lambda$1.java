package com.coinbase.android.idology;

final /* synthetic */ class IdologySourceOfFundsFormLayout$$Lambda$1 implements Runnable {
    private final IdologySourceOfFundsFormLayout arg$1;

    private IdologySourceOfFundsFormLayout$$Lambda$1(IdologySourceOfFundsFormLayout idologySourceOfFundsFormLayout) {
        this.arg$1 = idologySourceOfFundsFormLayout;
    }

    public static Runnable lambdaFactory$(IdologySourceOfFundsFormLayout idologySourceOfFundsFormLayout) {
        return new IdologySourceOfFundsFormLayout$$Lambda$1(idologySourceOfFundsFormLayout);
    }

    public void run() {
        this.arg$1.mPresenter.onFormUpdated();
    }
}
