package com.coinbase.android.deposits.fiat;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.coinbase.android.phone.VerifyPhoneHandler.VerifyPhoneCaller;
import com.coinbase.android.quickstart.QuickstartItem;

final /* synthetic */ class WithdrawFiatController$$Lambda$3 implements OnItemClickListener {
    private final WithdrawFiatController arg$1;
    private final VerifyPhoneCaller arg$2;

    private WithdrawFiatController$$Lambda$3(WithdrawFiatController withdrawFiatController, VerifyPhoneCaller verifyPhoneCaller) {
        this.arg$1 = withdrawFiatController;
        this.arg$2 = verifyPhoneCaller;
    }

    public static OnItemClickListener lambdaFactory$(WithdrawFiatController withdrawFiatController, VerifyPhoneCaller verifyPhoneCaller) {
        return new WithdrawFiatController$$Lambda$3(withdrawFiatController, verifyPhoneCaller);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.arg$1.mWithdrawFiatPresenter.performActionForQuickstartItem((QuickstartItem) adapterView.getItemAtPosition(i), this.arg$1.getActivity(), this.arg$2);
    }
}
