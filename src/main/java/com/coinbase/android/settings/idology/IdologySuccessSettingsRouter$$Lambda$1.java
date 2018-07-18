package com.coinbase.android.settings.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologySuccessSettingsRouter$$Lambda$1 implements Action1 {
    private final IdologySuccessSettingsRouter arg$1;

    private IdologySuccessSettingsRouter$$Lambda$1(IdologySuccessSettingsRouter idologySuccessSettingsRouter) {
        this.arg$1 = idologySuccessSettingsRouter;
    }

    public static Action1 lambdaFactory$(IdologySuccessSettingsRouter idologySuccessSettingsRouter) {
        return new IdologySuccessSettingsRouter$$Lambda$1(idologySuccessSettingsRouter);
    }

    public void call(Object obj) {
        this.arg$1.mController.pushModalController(new IdologySuccessController(this.arg$1.mIdologyArgsBuilder.getIdologyArgs(null)));
    }
}
