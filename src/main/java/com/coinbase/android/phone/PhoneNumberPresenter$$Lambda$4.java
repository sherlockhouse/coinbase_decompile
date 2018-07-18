package com.coinbase.android.phone;

import rx.functions.Action1;

final /* synthetic */ class PhoneNumberPresenter$$Lambda$4 implements Action1 {
    private final PhoneNumberPresenter arg$1;

    private PhoneNumberPresenter$$Lambda$4(PhoneNumberPresenter phoneNumberPresenter) {
        this.arg$1 = phoneNumberPresenter;
    }

    public static Action1 lambdaFactory$(PhoneNumberPresenter phoneNumberPresenter) {
        return new PhoneNumberPresenter$$Lambda$4(phoneNumberPresenter);
    }

    public void call(Object obj) {
        PhoneNumberPresenter.lambda$updatePhoneNumbers$3(this.arg$1, (Throwable) obj);
    }
}
