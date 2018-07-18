package com.coinbase.android.paymentmethods;

final /* synthetic */ class AddPlaidAccountPresenter$$Lambda$2 implements Runnable {
    private final AddPlaidAccountPresenter arg$1;

    private AddPlaidAccountPresenter$$Lambda$2(AddPlaidAccountPresenter addPlaidAccountPresenter) {
        this.arg$1 = addPlaidAccountPresenter;
    }

    public static Runnable lambdaFactory$(AddPlaidAccountPresenter addPlaidAccountPresenter) {
        return new AddPlaidAccountPresenter$$Lambda$2(addPlaidAccountPresenter);
    }

    public void run() {
        AddPlaidAccountPresenter.lambda$onActivityResult$1(this.arg$1);
    }
}
