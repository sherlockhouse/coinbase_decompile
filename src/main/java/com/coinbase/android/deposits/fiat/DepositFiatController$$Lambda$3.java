package com.coinbase.android.deposits.fiat;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.coinbase.android.phone.VerifyPhoneHandler.VerifyPhoneCaller;
import com.coinbase.android.quickstart.QuickstartItem;

final /* synthetic */ class DepositFiatController$$Lambda$3 implements OnItemClickListener {
    private final DepositFiatController arg$1;
    private final VerifyPhoneCaller arg$2;

    private DepositFiatController$$Lambda$3(DepositFiatController depositFiatController, VerifyPhoneCaller verifyPhoneCaller) {
        this.arg$1 = depositFiatController;
        this.arg$2 = verifyPhoneCaller;
    }

    public static OnItemClickListener lambdaFactory$(DepositFiatController depositFiatController, VerifyPhoneCaller verifyPhoneCaller) {
        return new DepositFiatController$$Lambda$3(depositFiatController, verifyPhoneCaller);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.arg$1.mDepositFiatPresenter.performActionForQuickstartItem((QuickstartItem) adapterView.getItemAtPosition(i), this.arg$1.getActivity(), this.arg$2);
    }
}
