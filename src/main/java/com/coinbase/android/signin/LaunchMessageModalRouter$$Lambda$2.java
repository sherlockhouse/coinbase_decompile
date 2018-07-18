package com.coinbase.android.signin;

import com.coinbase.api.internal.models.config.Message;
import rx.functions.Action0;

final /* synthetic */ class LaunchMessageModalRouter$$Lambda$2 implements Action0 {
    private final LaunchMessageModalRouter arg$1;
    private final Message arg$2;
    private final boolean arg$3;

    private LaunchMessageModalRouter$$Lambda$2(LaunchMessageModalRouter launchMessageModalRouter, Message message, boolean z) {
        this.arg$1 = launchMessageModalRouter;
        this.arg$2 = message;
        this.arg$3 = z;
    }

    public static Action0 lambdaFactory$(LaunchMessageModalRouter launchMessageModalRouter, Message message, boolean z) {
        return new LaunchMessageModalRouter$$Lambda$2(launchMessageModalRouter, message, z);
    }

    public void call() {
        LaunchMessageModalRouter.lambda$null$0(this.arg$1, this.arg$2, this.arg$3);
    }
}
