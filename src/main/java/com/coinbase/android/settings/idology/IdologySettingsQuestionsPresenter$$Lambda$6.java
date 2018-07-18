package com.coinbase.android.settings.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologySettingsQuestionsPresenter$$Lambda$6 implements Action1 {
    private final IdologySettingsQuestionsPresenter arg$1;

    private IdologySettingsQuestionsPresenter$$Lambda$6(IdologySettingsQuestionsPresenter idologySettingsQuestionsPresenter) {
        this.arg$1 = idologySettingsQuestionsPresenter;
    }

    public static Action1 lambdaFactory$(IdologySettingsQuestionsPresenter idologySettingsQuestionsPresenter) {
        return new IdologySettingsQuestionsPresenter$$Lambda$6(idologySettingsQuestionsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't subscribe to IdologyRetryConnector, shouldn't happen", (Throwable) obj);
    }
}
