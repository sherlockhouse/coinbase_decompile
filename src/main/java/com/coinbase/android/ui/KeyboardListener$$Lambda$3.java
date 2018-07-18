package com.coinbase.android.ui;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import rx.Subscriber;

final /* synthetic */ class KeyboardListener$$Lambda$3 implements OnGlobalLayoutListener {
    private final KeyboardListener arg$1;
    private final View arg$2;
    private final Subscriber arg$3;

    private KeyboardListener$$Lambda$3(KeyboardListener keyboardListener, View view, Subscriber subscriber) {
        this.arg$1 = keyboardListener;
        this.arg$2 = view;
        this.arg$3 = subscriber;
    }

    public static OnGlobalLayoutListener lambdaFactory$(KeyboardListener keyboardListener, View view, Subscriber subscriber) {
        return new KeyboardListener$$Lambda$3(keyboardListener, view, subscriber);
    }

    public void onGlobalLayout() {
        KeyboardListener.lambda$null$1(this.arg$1, this.arg$2, this.arg$3);
    }
}
