package com.coinbase.android.paymentmethods.card;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final /* synthetic */ class CardFormController$$Lambda$1 implements OnFocusChangeListener {
    private final CardFormController arg$1;

    private CardFormController$$Lambda$1(CardFormController cardFormController) {
        this.arg$1 = cardFormController;
    }

    public static OnFocusChangeListener lambdaFactory$(CardFormController cardFormController) {
        return new CardFormController$$Lambda$1(cardFormController);
    }

    public void onFocusChange(View view, boolean z) {
        CardFormController.lambda$new$0(this.arg$1, view, z);
    }
}
