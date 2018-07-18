package com.coinbase.android.settings.gdpr;

import com.coinbase.android.settings.SettingsPreferenceItem;
import rx.functions.Func1;

final /* synthetic */ class EmailSettingsPresenter$$Lambda$3 implements Func1 {
    private final EmailSettingsPresenter arg$1;

    private EmailSettingsPresenter$$Lambda$3(EmailSettingsPresenter emailSettingsPresenter) {
        this.arg$1 = emailSettingsPresenter;
    }

    public static Func1 lambdaFactory$(EmailSettingsPresenter emailSettingsPresenter) {
        return new EmailSettingsPresenter$$Lambda$3(emailSettingsPresenter);
    }

    public Object call(Object obj) {
        return EmailSettingsPresenter.lambda$onShow$2(this.arg$1, (SettingsPreferenceItem) obj);
    }
}
