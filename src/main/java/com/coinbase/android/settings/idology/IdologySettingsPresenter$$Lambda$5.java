package com.coinbase.android.settings.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologySettingsPresenter$$Lambda$5 implements Action1 {
    private final IdologySettingsPresenter arg$1;

    private IdologySettingsPresenter$$Lambda$5(IdologySettingsPresenter idologySettingsPresenter) {
        this.arg$1 = idologySettingsPresenter;
    }

    public static Action1 lambdaFactory$(IdologySettingsPresenter idologySettingsPresenter) {
        return new IdologySettingsPresenter$$Lambda$5(idologySettingsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.setContinueMenuEnabled(((Boolean) obj).booleanValue());
    }
}
