package com.coinbase.android.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologyUtils$$Lambda$1 implements Action1 {
    private final IdologyUtils arg$1;

    private IdologyUtils$$Lambda$1(IdologyUtils idologyUtils) {
        this.arg$1 = idologyUtils;
    }

    public static Action1 lambdaFactory$(IdologyUtils idologyUtils) {
        return new IdologyUtils$$Lambda$1(idologyUtils);
    }

    public void call(Object obj) {
        this.arg$1.updateIdologyEnabledFlag(((Boolean) obj).booleanValue());
    }
}
