package com.coinbase.android.ui;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class NumericKeyboardLayout$$Lambda$12 implements OnClickListener {
    private final NumericKeyboardLayout arg$1;

    private NumericKeyboardLayout$$Lambda$12(NumericKeyboardLayout numericKeyboardLayout) {
        this.arg$1 = numericKeyboardLayout;
    }

    public static OnClickListener lambdaFactory$(NumericKeyboardLayout numericKeyboardLayout) {
        return new NumericKeyboardLayout$$Lambda$12(numericKeyboardLayout);
    }

    public void onClick(View view) {
        this.arg$1.onRightKeyboardClicked(view);
    }
}
