package com.coinbase.android.task;

import rx.functions.Action1;

final /* synthetic */ class SyncAccountsTask$$Lambda$4 implements Action1 {
    private final SyncAccountsTask arg$1;

    private SyncAccountsTask$$Lambda$4(SyncAccountsTask syncAccountsTask) {
        this.arg$1 = syncAccountsTask;
    }

    public static Action1 lambdaFactory$(SyncAccountsTask syncAccountsTask) {
        return new SyncAccountsTask$$Lambda$4(syncAccountsTask);
    }

    public void call(Object obj) {
        this.arg$1.handleException();
    }
}
