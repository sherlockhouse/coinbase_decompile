package com.coinbase.android.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologyQuestionsListAdapter$$Lambda$4 implements Action1 {
    private final IdologyQuestionsListAdapter arg$1;

    private IdologyQuestionsListAdapter$$Lambda$4(IdologyQuestionsListAdapter idologyQuestionsListAdapter) {
        this.arg$1 = idologyQuestionsListAdapter;
    }

    public static Action1 lambdaFactory$(IdologyQuestionsListAdapter idologyQuestionsListAdapter) {
        return new IdologyQuestionsListAdapter$$Lambda$4(idologyQuestionsListAdapter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't subscribe to IdologyAnswerSelectedConnector, shouldn't happen", (Throwable) obj);
    }
}
