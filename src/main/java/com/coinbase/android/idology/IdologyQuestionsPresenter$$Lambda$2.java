package com.coinbase.android.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologyQuestionsPresenter$$Lambda$2 implements Action1 {
    private final IdologyQuestionsPresenter arg$1;

    private IdologyQuestionsPresenter$$Lambda$2(IdologyQuestionsPresenter idologyQuestionsPresenter) {
        this.arg$1 = idologyQuestionsPresenter;
    }

    public static Action1 lambdaFactory$(IdologyQuestionsPresenter idologyQuestionsPresenter) {
        return new IdologyQuestionsPresenter$$Lambda$2(idologyQuestionsPresenter);
    }

    public void call(Object obj) {
        IdologyQuestionsPresenter.lambda$submitAnswers$1(this.arg$1, (Throwable) obj);
    }
}
