package com.coinbase.android.idology;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class IdologyFormLayout$$Lambda$7 implements OnEditorActionListener {
    private final IdologyFormLayout arg$1;

    private IdologyFormLayout$$Lambda$7(IdologyFormLayout idologyFormLayout) {
        this.arg$1 = idologyFormLayout;
    }

    public static OnEditorActionListener lambdaFactory$(IdologyFormLayout idologyFormLayout) {
        return new IdologyFormLayout$$Lambda$7(idologyFormLayout);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return IdologyFormLayout.lambda$setListeners$6(this.arg$1, textView, i, keyEvent);
    }
}
