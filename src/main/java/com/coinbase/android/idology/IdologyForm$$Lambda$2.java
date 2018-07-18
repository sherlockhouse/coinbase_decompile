package com.coinbase.android.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologyForm$$Lambda$2 implements Action1 {
    private final IdologyForm arg$1;

    private IdologyForm$$Lambda$2(IdologyForm idologyForm) {
        this.arg$1 = idologyForm;
    }

    public static Action1 lambdaFactory$(IdologyForm idologyForm) {
        return new IdologyForm$$Lambda$2(idologyForm);
    }

    public void call(Object obj) {
        IdologyForm.lambda$submitForm$1(this.arg$1, (Throwable) obj);
    }
}
