package com.coinbase.android.buysell;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.coinbase.android.quickstart.QuickstartItem;

final /* synthetic */ class BuyController$$Lambda$4 implements OnItemClickListener {
    private final BuyController arg$1;

    private BuyController$$Lambda$4(BuyController buyController) {
        this.arg$1 = buyController;
    }

    public static OnItemClickListener lambdaFactory$(BuyController buyController) {
        return new BuyController$$Lambda$4(buyController);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.arg$1.mPresenter.performActionForQuickstartItem((QuickstartItem) adapterView.getItemAtPosition(i), this.arg$1.getActivity(), this.arg$1.mVerifyPhoneCaller);
    }
}
