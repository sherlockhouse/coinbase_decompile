package com.coinbase.android.gdpr;

import com.coinbase.v2.models.user.Data;
import rx.functions.Action1;

final /* synthetic */ class GdprInitializer$$Lambda$2 implements Action1 {
    private final GdprInitializer arg$1;

    private GdprInitializer$$Lambda$2(GdprInitializer gdprInitializer) {
        this.arg$1 = gdprInitializer;
    }

    public static Action1 lambdaFactory$(GdprInitializer gdprInitializer) {
        return new GdprInitializer$$Lambda$2(gdprInitializer);
    }

    public void call(Object obj) {
        GdprInitializer.lambda$onCreate$1(this.arg$1, (Data) obj);
    }
}
