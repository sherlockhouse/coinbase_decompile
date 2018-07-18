package com.coinbase.android.paymentmethods.linkedaccounts;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LinkedAccountItemLayout$$Lambda$2 implements OnClickListener {
    private final LinkedAccountItemLayout arg$1;

    private LinkedAccountItemLayout$$Lambda$2(LinkedAccountItemLayout linkedAccountItemLayout) {
        this.arg$1 = linkedAccountItemLayout;
    }

    public static OnClickListener lambdaFactory$(LinkedAccountItemLayout linkedAccountItemLayout) {
        return new LinkedAccountItemLayout$$Lambda$2(linkedAccountItemLayout);
    }

    public void onClick(View view) {
        this.arg$1.mConnector.getLinkedAccountClickedSubject().onNext(null);
    }
}
