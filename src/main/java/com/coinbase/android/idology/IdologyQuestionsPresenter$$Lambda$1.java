package com.coinbase.android.idology;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class IdologyQuestionsPresenter$$Lambda$1 implements Action1 {
    private final IdologyQuestionsPresenter arg$1;

    private IdologyQuestionsPresenter$$Lambda$1(IdologyQuestionsPresenter idologyQuestionsPresenter) {
        this.arg$1 = idologyQuestionsPresenter;
    }

    public static Action1 lambdaFactory$(IdologyQuestionsPresenter idologyQuestionsPresenter) {
        return new IdologyQuestionsPresenter$$Lambda$1(idologyQuestionsPresenter);
    }

    public void call(Object obj) {
        IdologyQuestionsPresenter.lambda$submitAnswers$0(this.arg$1, (Pair) obj);
    }
}
