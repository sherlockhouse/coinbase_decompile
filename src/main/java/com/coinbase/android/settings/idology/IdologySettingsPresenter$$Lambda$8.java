package com.coinbase.android.settings.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologySettingsPresenter$$Lambda$8 implements Action1 {
    private final IdologySettingsPresenter arg$1;

    private IdologySettingsPresenter$$Lambda$8(IdologySettingsPresenter idologySettingsPresenter) {
        this.arg$1 = idologySettingsPresenter;
    }

    public static Action1 lambdaFactory$(IdologySettingsPresenter idologySettingsPresenter) {
        return new IdologySettingsPresenter$$Lambda$8(idologySettingsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't subscribe to IdologyVerificationConnector, shouldn't happen", (Throwable) obj);
    }
}
