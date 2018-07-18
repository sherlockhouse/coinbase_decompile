package com.coinbase.android.buysell;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.coinbase.android.quickstart.QuickstartItem;

final /* synthetic */ class SellController$$Lambda$7 implements OnItemClickListener {
    private final SellController arg$1;

    private SellController$$Lambda$7(SellController sellController) {
        this.arg$1 = sellController;
    }

    public static OnItemClickListener lambdaFactory$(SellController sellController) {
        return new SellController$$Lambda$7(sellController);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.arg$1.mPresenter.performActionForQuickstartItem((QuickstartItem) adapterView.getItemAtPosition(i), this.arg$1.getActivity(), this.arg$1.mVerifyPhoneCaller);
    }
}
