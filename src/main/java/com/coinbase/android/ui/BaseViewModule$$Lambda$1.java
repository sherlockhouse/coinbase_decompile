package com.coinbase.android.ui;

import rx.functions.Func0;

final /* synthetic */ class BaseViewModule$$Lambda$1 implements Func0 {
    private final BaseViewModule arg$1;

    private BaseViewModule$$Lambda$1(BaseViewModule baseViewModule) {
        this.arg$1 = baseViewModule;
    }

    public static Func0 lambdaFactory$(BaseViewModule baseViewModule) {
        return new BaseViewModule$$Lambda$1(baseViewModule);
    }

    public Object call() {
        return this.arg$1.mBaseViewProvider.getBlockingOverlayView();
    }
}
