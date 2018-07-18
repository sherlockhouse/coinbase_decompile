package com.coinbase.android.ui;

import rx.functions.Action1;

final /* synthetic */ class OptionalViewHider$$Lambda$1 implements Action1 {
    private final OptionalViewHider arg$1;

    private OptionalViewHider$$Lambda$1(OptionalViewHider optionalViewHider) {
        this.arg$1 = optionalViewHider;
    }

    public static Action1 lambdaFactory$(OptionalViewHider optionalViewHider) {
        return new OptionalViewHider$$Lambda$1(optionalViewHider);
    }

    public void call(Object obj) {
        this.arg$1.updateViewsVisibility(((Boolean) obj).booleanValue());
    }
}
