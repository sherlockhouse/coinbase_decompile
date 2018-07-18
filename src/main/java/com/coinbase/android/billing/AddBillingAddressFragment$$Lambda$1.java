package com.coinbase.android.billing;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class AddBillingAddressFragment$$Lambda$1 implements OnEditorActionListener {
    private final AddBillingAddressFragment arg$1;

    private AddBillingAddressFragment$$Lambda$1(AddBillingAddressFragment addBillingAddressFragment) {
        this.arg$1 = addBillingAddressFragment;
    }

    public static OnEditorActionListener lambdaFactory$(AddBillingAddressFragment addBillingAddressFragment) {
        return new AddBillingAddressFragment$$Lambda$1(addBillingAddressFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return AddBillingAddressFragment.lambda$onViewCreated$0(this.arg$1, textView, i, keyEvent);
    }
}
