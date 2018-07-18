package com.coinbase.android.idology;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class IdologyNameFormLayout$$Lambda$3 implements OnEditorActionListener {
    private final IdologyNameFormLayout arg$1;

    private IdologyNameFormLayout$$Lambda$3(IdologyNameFormLayout idologyNameFormLayout) {
        this.arg$1 = idologyNameFormLayout;
    }

    public static OnEditorActionListener lambdaFactory$(IdologyNameFormLayout idologyNameFormLayout) {
        return new IdologyNameFormLayout$$Lambda$3(idologyNameFormLayout);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return IdologyNameFormLayout.lambda$setListeners$2(this.arg$1, textView, i, keyEvent);
    }
}
