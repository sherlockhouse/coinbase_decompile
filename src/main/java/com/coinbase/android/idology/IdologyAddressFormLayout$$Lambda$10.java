package com.coinbase.android.idology;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class IdologyAddressFormLayout$$Lambda$10 implements OnEditorActionListener {
    private final IdologyAddressFormLayout arg$1;

    private IdologyAddressFormLayout$$Lambda$10(IdologyAddressFormLayout idologyAddressFormLayout) {
        this.arg$1 = idologyAddressFormLayout;
    }

    public static OnEditorActionListener lambdaFactory$(IdologyAddressFormLayout idologyAddressFormLayout) {
        return new IdologyAddressFormLayout$$Lambda$10(idologyAddressFormLayout);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return IdologyAddressFormLayout.lambda$setListeners$10(this.arg$1, textView, i, keyEvent);
    }
}
