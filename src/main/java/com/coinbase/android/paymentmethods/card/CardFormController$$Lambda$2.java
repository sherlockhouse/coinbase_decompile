package com.coinbase.android.paymentmethods.card;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CardFormController$$Lambda$2 implements OnClickListener {
    private final CardFormController arg$1;

    private CardFormController$$Lambda$2(CardFormController cardFormController) {
        this.arg$1 = cardFormController;
    }

    public static OnClickListener lambdaFactory$(CardFormController cardFormController) {
        return new CardFormController$$Lambda$2(cardFormController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onCardHelpClick();
    }
}
