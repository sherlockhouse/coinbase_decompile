package com.coinbase.android.ui;

import rx.Observable.OnSubscribe;
import rx.Subscriber;

final /* synthetic */ class KeyboardListener$$Lambda$1 implements OnSubscribe {
    private static final KeyboardListener$$Lambda$1 instance = new KeyboardListener$$Lambda$1();

    private KeyboardListener$$Lambda$1() {
    }

    public static OnSubscribe lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        KeyboardListener.lambda$isKeyboardVisible$0((Subscriber) obj);
    }
}
