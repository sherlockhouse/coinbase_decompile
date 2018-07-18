package com.coinbase.android.transfers;

import com.coinbase.android.wbl.AvailableBalance;
import rx.functions.Action1;

final /* synthetic */ class SendPresenter$$Lambda$1 implements Action1 {
    private final SendPresenter arg$1;

    private SendPresenter$$Lambda$1(SendPresenter sendPresenter) {
        this.arg$1 = sendPresenter;
    }

    public static Action1 lambdaFactory$(SendPresenter sendPresenter) {
        return new SendPresenter$$Lambda$1(sendPresenter);
    }

    public void call(Object obj) {
        SendPresenter.lambda$onViewCreated$0(this.arg$1, (AvailableBalance) obj);
    }
}
