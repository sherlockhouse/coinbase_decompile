package com.coinbase.android.settings.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologySettingsQuestionsPresenter$$Lambda$2 implements Action1 {
    private final IdologySettingsQuestionsPresenter arg$1;

    private IdologySettingsQuestionsPresenter$$Lambda$2(IdologySettingsQuestionsPresenter idologySettingsQuestionsPresenter) {
        this.arg$1 = idologySettingsQuestionsPresenter;
    }

    public static Action1 lambdaFactory$(IdologySettingsQuestionsPresenter idologySettingsQuestionsPresenter) {
        return new IdologySettingsQuestionsPresenter$$Lambda$2(idologySettingsQuestionsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.setContinueMenuEnabled((Boolean) obj);
    }
}
