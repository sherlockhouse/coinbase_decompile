package com.coinbase.android.settings.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologySettingsQuestionsPresenter$$Lambda$8 implements Action1 {
    private final IdologySettingsQuestionsPresenter arg$1;

    private IdologySettingsQuestionsPresenter$$Lambda$8(IdologySettingsQuestionsPresenter idologySettingsQuestionsPresenter) {
        this.arg$1 = idologySettingsQuestionsPresenter;
    }

    public static Action1 lambdaFactory$(IdologySettingsQuestionsPresenter idologySettingsQuestionsPresenter) {
        return new IdologySettingsQuestionsPresenter$$Lambda$8(idologySettingsQuestionsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't subscribe to IdologyVerificationConnector, shouldn't happen", (Throwable) obj);
    }
}
