package com.coinbase.android.ui;

import android.view.View;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

final /* synthetic */ class KeyboardListener$$Lambda$2 implements OnSubscribe {
    private final KeyboardListener arg$1;
    private final View arg$2;

    private KeyboardListener$$Lambda$2(KeyboardListener keyboardListener, View view) {
        this.arg$1 = keyboardListener;
        this.arg$2 = view;
    }

    public static OnSubscribe lambdaFactory$(KeyboardListener keyboardListener, View view) {
        return new KeyboardListener$$Lambda$2(keyboardListener, view);
    }

    public void call(Object obj) {
        KeyboardListener.lambda$isKeyboardVisible$2(this.arg$1, this.arg$2, (Subscriber) obj);
    }
}
