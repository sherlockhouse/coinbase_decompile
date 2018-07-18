package com.coinbase.android.wbl;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AvailableBalanceAppBarLayout$$Lambda$1 implements OnClickListener {
    private final AvailableBalanceAppBarPresenter arg$1;

    private AvailableBalanceAppBarLayout$$Lambda$1(AvailableBalanceAppBarPresenter availableBalanceAppBarPresenter) {
        this.arg$1 = availableBalanceAppBarPresenter;
    }

    public static OnClickListener lambdaFactory$(AvailableBalanceAppBarPresenter availableBalanceAppBarPresenter) {
        return new AvailableBalanceAppBarLayout$$Lambda$1(availableBalanceAppBarPresenter);
    }

    public void onClick(View view) {
        this.arg$1.onAvailableBalanceClicked();
    }
}
