package com.coinbase.android.transfers;

import rx.Observable.OnSubscribe;
import rx.Subscriber;

final /* synthetic */ class TransferSendPresenter$GetGoogleSuggestionsTask$$Lambda$1 implements OnSubscribe {
    private final GetGoogleSuggestionsTask arg$1;

    private TransferSendPresenter$GetGoogleSuggestionsTask$$Lambda$1(GetGoogleSuggestionsTask getGoogleSuggestionsTask) {
        this.arg$1 = getGoogleSuggestionsTask;
    }

    public static OnSubscribe lambdaFactory$(GetGoogleSuggestionsTask getGoogleSuggestionsTask) {
        return new TransferSendPresenter$GetGoogleSuggestionsTask$$Lambda$1(getGoogleSuggestionsTask);
    }

    public void call(Object obj) {
        GetGoogleSuggestionsTask.lambda$call$0(this.arg$1, (Subscriber) obj);
    }
}
