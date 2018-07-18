package com.coinbase.android.billing;

import com.coinbase.android.Constants;
import java.util.HashMap;
import rx.functions.Action1;

final /* synthetic */ class AddBillingAddressPresenter$$Lambda$2 implements Action1 {
    private final AddBillingAddressPresenter arg$1;

    private AddBillingAddressPresenter$$Lambda$2(AddBillingAddressPresenter addBillingAddressPresenter) {
        this.arg$1 = addBillingAddressPresenter;
    }

    public static Action1 lambdaFactory$(AddBillingAddressPresenter addBillingAddressPresenter) {
        return new AddBillingAddressPresenter$$Lambda$2(addBillingAddressPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mScreen.setStateChosen((String) ((HashMap) obj).get(Constants.ABBREVIATION));
    }
}
