package com.coinbase.android.phone;

import rx.functions.Func0;

final /* synthetic */ class PhoneNumberPresenter$$Lambda$1 implements Func0 {
    private final PhoneNumberPresenter arg$1;

    private PhoneNumberPresenter$$Lambda$1(PhoneNumberPresenter phoneNumberPresenter) {
        this.arg$1 = phoneNumberPresenter;
    }

    public static Func0 lambdaFactory$(PhoneNumberPresenter phoneNumberPresenter) {
        return new PhoneNumberPresenter$$Lambda$1(phoneNumberPresenter);
    }

    public Object call() {
        return new VerifyPhoneHandler(this.arg$1, this.arg$1.mPhoneNumbersUpdatedConnector);
    }
}
