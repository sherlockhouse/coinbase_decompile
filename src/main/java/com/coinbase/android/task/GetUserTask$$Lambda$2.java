package com.coinbase.android.task;

import rx.functions.Action1;

final /* synthetic */ class GetUserTask$$Lambda$2 implements Action1 {
    private final GetUserTask arg$1;

    private GetUserTask$$Lambda$2(GetUserTask getUserTask) {
        this.arg$1 = getUserTask;
    }

    public static Action1 lambdaFactory$(GetUserTask getUserTask) {
        return new GetUserTask$$Lambda$2(getUserTask);
    }

    public void call(Object obj) {
        GetUserTask.lambda$getUser$1(this.arg$1, (Throwable) obj);
    }
}
