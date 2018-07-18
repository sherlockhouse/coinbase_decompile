package com.coinbase.android.idology;

import android.util.Pair;
import com.coinbase.api.internal.models.idology.Data;
import rx.functions.Action1;

final /* synthetic */ class IdologyForm$$Lambda$1 implements Action1 {
    private final IdologyForm arg$1;
    private final Data arg$2;

    private IdologyForm$$Lambda$1(IdologyForm idologyForm, Data data) {
        this.arg$1 = idologyForm;
        this.arg$2 = data;
    }

    public static Action1 lambdaFactory$(IdologyForm idologyForm, Data data) {
        return new IdologyForm$$Lambda$1(idologyForm, data);
    }

    public void call(Object obj) {
        IdologyForm.lambda$submitForm$0(this.arg$1, this.arg$2, (Pair) obj);
    }
}
