package com.coinbase.android.ui;

import android.view.View;
import android.view.View.OnLongClickListener;

final /* synthetic */ class NumericKeyboardLayout$$Lambda$13 implements OnLongClickListener {
    private final NumericKeyboardLayout arg$1;

    private NumericKeyboardLayout$$Lambda$13(NumericKeyboardLayout numericKeyboardLayout) {
        this.arg$1 = numericKeyboardLayout;
    }

    public static OnLongClickListener lambdaFactory$(NumericKeyboardLayout numericKeyboardLayout) {
        return new NumericKeyboardLayout$$Lambda$13(numericKeyboardLayout);
    }

    public boolean onLongClick(View view) {
        return this.arg$1.onRightKeyboardLongClicked(view);
    }
}
