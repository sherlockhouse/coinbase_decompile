package com.coinbase.android.settings.gdpr;

import rx.functions.Action1;

final /* synthetic */ class EmailSettingsPresenter$$Lambda$2 implements Action1 {
    private final EmailSettingsPresenter arg$1;

    private EmailSettingsPresenter$$Lambda$2(EmailSettingsPresenter emailSettingsPresenter) {
        this.arg$1 = emailSettingsPresenter;
    }

    public static Action1 lambdaFactory$(EmailSettingsPresenter emailSettingsPresenter) {
        return new EmailSettingsPresenter$$Lambda$2(emailSettingsPresenter);
    }

    public void call(Object obj) {
        EmailSettingsPresenter.lambda$onShow$1(this.arg$1, (Throwable) obj);
    }
}
