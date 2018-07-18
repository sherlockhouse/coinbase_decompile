package com.coinbase.android.task;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class GetUserTask$$Lambda$1 implements Action1 {
    private final GetUserTask arg$1;

    private GetUserTask$$Lambda$1(GetUserTask getUserTask) {
        this.arg$1 = getUserTask;
    }

    public static Action1 lambdaFactory$(GetUserTask getUserTask) {
        return new GetUserTask$$Lambda$1(getUserTask);
    }

    public void call(Object obj) {
        GetUserTask.lambda$getUser$0(this.arg$1, (Pair) obj);
    }
}
