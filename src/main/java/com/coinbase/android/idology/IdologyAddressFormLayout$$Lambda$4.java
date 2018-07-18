package com.coinbase.android.idology;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class IdologyAddressFormLayout$$Lambda$4 implements OnEditorActionListener {
    private final IdologyAddressFormLayout arg$1;

    private IdologyAddressFormLayout$$Lambda$4(IdologyAddressFormLayout idologyAddressFormLayout) {
        this.arg$1 = idologyAddressFormLayout;
    }

    public static OnEditorActionListener lambdaFactory$(IdologyAddressFormLayout idologyAddressFormLayout) {
        return new IdologyAddressFormLayout$$Lambda$4(idologyAddressFormLayout);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return IdologyAddressFormLayout.lambda$init$4(this.arg$1, textView, i, keyEvent);
    }
}
