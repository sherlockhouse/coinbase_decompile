package com.coinbase.android.task;

import retrofit2.Response;
import rx.functions.Action1;

final /* synthetic */ class SyncAccountsTask$$Lambda$3 implements Action1 {
    private final SyncAccountsTask arg$1;

    private SyncAccountsTask$$Lambda$3(SyncAccountsTask syncAccountsTask) {
        this.arg$1 = syncAccountsTask;
    }

    public static Action1 lambdaFactory$(SyncAccountsTask syncAccountsTask) {
        return new SyncAccountsTask$$Lambda$3(syncAccountsTask);
    }

    public void call(Object obj) {
        SyncAccountsTask.lambda$syncAccounts$2(this.arg$1, (Response) obj);
    }
}
