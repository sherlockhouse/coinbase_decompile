package com.coinbase.android.settings.idology;

import com.coinbase.api.internal.models.idology.Data;
import rx.functions.Action1;

final /* synthetic */ class IdologySettingsQuestionsPresenter$$Lambda$7 implements Action1 {
    private final IdologySettingsQuestionsPresenter arg$1;

    private IdologySettingsQuestionsPresenter$$Lambda$7(IdologySettingsQuestionsPresenter idologySettingsQuestionsPresenter) {
        this.arg$1 = idologySettingsQuestionsPresenter;
    }

    public static Action1 lambdaFactory$(IdologySettingsQuestionsPresenter idologySettingsQuestionsPresenter) {
        return new IdologySettingsQuestionsPresenter$$Lambda$7(idologySettingsQuestionsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.processVerificationResult((Data) obj);
    }
}
