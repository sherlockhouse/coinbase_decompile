package com.coinbase.android.phone;

import rx.functions.Action1;

final /* synthetic */ class PhoneNumberPresenter$$Lambda$2 implements Action1 {
    private final PhoneNumberPresenter arg$1;

    private PhoneNumberPresenter$$Lambda$2(PhoneNumberPresenter phoneNumberPresenter) {
        this.arg$1 = phoneNumberPresenter;
    }

    public static Action1 lambdaFactory$(PhoneNumberPresenter phoneNumberPresenter) {
        return new PhoneNumberPresenter$$Lambda$2(phoneNumberPresenter);
    }

    public void call(Object obj) {
        this.arg$1.onPhoneNumbersUpdated(obj);
    }
}
