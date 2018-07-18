package com.coinbase.android.buysell;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class QuickBuyListAdapter$$Lambda$1 implements OnClickListener {
    private final QuickBuyListAdapter arg$1;
    private final String arg$2;

    private QuickBuyListAdapter$$Lambda$1(QuickBuyListAdapter quickBuyListAdapter, String str) {
        this.arg$1 = quickBuyListAdapter;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(QuickBuyListAdapter quickBuyListAdapter, String str) {
        return new QuickBuyListAdapter$$Lambda$1(quickBuyListAdapter, str);
    }

    public void onClick(View view) {
        this.arg$1.mQuickBuyConnector.get().onNext(this.arg$2);
    }
}
