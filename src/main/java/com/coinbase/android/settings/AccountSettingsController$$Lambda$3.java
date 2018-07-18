package com.coinbase.android.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AccountSettingsController$$Lambda$3 implements OnClickListener {
    private final NameItem arg$1;

    private AccountSettingsController$$Lambda$3(NameItem nameItem) {
        this.arg$1 = nameItem;
    }

    public static OnClickListener lambdaFactory$(NameItem nameItem) {
        return new AccountSettingsController$$Lambda$3(nameItem);
    }

    public void onClick(View view) {
        this.arg$1.onClick();
    }
}
