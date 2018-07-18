package com.coinbase.android.phone;

import android.os.Bundle;
import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class PhoneNumberPresenter$$Lambda$3 implements Action1 {
    private final PhoneNumberPresenter arg$1;
    private final Bundle arg$2;

    private PhoneNumberPresenter$$Lambda$3(PhoneNumberPresenter phoneNumberPresenter, Bundle bundle) {
        this.arg$1 = phoneNumberPresenter;
        this.arg$2 = bundle;
    }

    public static Action1 lambdaFactory$(PhoneNumberPresenter phoneNumberPresenter, Bundle bundle) {
        return new PhoneNumberPresenter$$Lambda$3(phoneNumberPresenter, bundle);
    }

    public void call(Object obj) {
        PhoneNumberPresenter.lambda$updatePhoneNumbers$2(this.arg$1, this.arg$2, (Pair) obj);
    }
}
