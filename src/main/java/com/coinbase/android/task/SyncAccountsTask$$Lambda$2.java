package com.coinbase.android.task;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class SyncAccountsTask$$Lambda$2 implements Func1 {
    private final SyncAccountsTask arg$1;

    private SyncAccountsTask$$Lambda$2(SyncAccountsTask syncAccountsTask) {
        this.arg$1 = syncAccountsTask;
    }

    public static Func1 lambdaFactory$(SyncAccountsTask syncAccountsTask) {
        return new SyncAccountsTask$$Lambda$2(syncAccountsTask);
    }

    public Object call(Object obj) {
        return SyncAccountsTask.lambda$syncAccounts$1(this.arg$1, (Pair) obj);
    }
}
