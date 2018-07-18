package com.coinbase.android.ui;

import android.widget.TextView;
import com.coinbase.android.ui.AutoResizeTextView.OnTextResizeListener;

final /* synthetic */ class AutoResizeTextViewAdjustor$$Lambda$1 implements OnTextResizeListener {
    private final AutoResizeTextViewAdjustor arg$1;

    private AutoResizeTextViewAdjustor$$Lambda$1(AutoResizeTextViewAdjustor autoResizeTextViewAdjustor) {
        this.arg$1 = autoResizeTextViewAdjustor;
    }

    public static OnTextResizeListener lambdaFactory$(AutoResizeTextViewAdjustor autoResizeTextViewAdjustor) {
        return new AutoResizeTextViewAdjustor$$Lambda$1(autoResizeTextViewAdjustor);
    }

    public void onTextResize(TextView textView, float f, float f2) {
        AutoResizeTextViewAdjustor.lambda$addView$0(this.arg$1, textView, f, f2);
    }
}
