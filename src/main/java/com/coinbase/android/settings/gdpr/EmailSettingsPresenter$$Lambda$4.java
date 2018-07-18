package com.coinbase.android.settings.gdpr;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class EmailSettingsPresenter$$Lambda$4 implements Action1 {
    private final EmailSettingsPresenter arg$1;

    private EmailSettingsPresenter$$Lambda$4(EmailSettingsPresenter emailSettingsPresenter) {
        this.arg$1 = emailSettingsPresenter;
    }

    public static Action1 lambdaFactory$(EmailSettingsPresenter emailSettingsPresenter) {
        return new EmailSettingsPresenter$$Lambda$4(emailSettingsPresenter);
    }

    public void call(Object obj) {
        EmailSettingsPresenter.lambda$onShow$3(this.arg$1, (Pair) obj);
    }
}
