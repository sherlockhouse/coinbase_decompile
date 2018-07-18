package com.coinbase.android.accounts;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class EnableSendReceiveLayout$$Lambda$1 implements OnClickListener {
    private final EnableSendReceiveLayout arg$1;

    private EnableSendReceiveLayout$$Lambda$1(EnableSendReceiveLayout enableSendReceiveLayout) {
        this.arg$1 = enableSendReceiveLayout;
    }

    public static OnClickListener lambdaFactory$(EnableSendReceiveLayout enableSendReceiveLayout) {
        return new EnableSendReceiveLayout$$Lambda$1(enableSendReceiveLayout);
    }

    public void onClick(View view) {
        EnableSendReceiveLayout.lambda$init$0(this.arg$1, view);
    }
}
