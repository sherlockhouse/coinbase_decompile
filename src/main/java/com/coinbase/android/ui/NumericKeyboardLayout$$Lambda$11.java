package com.coinbase.android.ui;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class NumericKeyboardLayout$$Lambda$11 implements OnClickListener {
    private final NumericKeyboardLayout arg$1;

    private NumericKeyboardLayout$$Lambda$11(NumericKeyboardLayout numericKeyboardLayout) {
        this.arg$1 = numericKeyboardLayout;
    }

    public static OnClickListener lambdaFactory$(NumericKeyboardLayout numericKeyboardLayout) {
        return new NumericKeyboardLayout$$Lambda$11(numericKeyboardLayout);
    }

    public void onClick(View view) {
        this.arg$1.onLeftKeyboardClicked(view);
    }
}
