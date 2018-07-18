package com.coinbase.android.settings.gdpr;

import com.coinbase.android.R;
import rx.functions.Action1;

final /* synthetic */ class EmailSettingsPresenter$$Lambda$5 implements Action1 {
    private final EmailSettingsPresenter arg$1;

    private EmailSettingsPresenter$$Lambda$5(EmailSettingsPresenter emailSettingsPresenter) {
        this.arg$1 = emailSettingsPresenter;
    }

    public static Action1 lambdaFactory$(EmailSettingsPresenter emailSettingsPresenter) {
        return new EmailSettingsPresenter$$Lambda$5(emailSettingsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mSnackBarWrapper.show(this.arg$1.mContext.getString(R.string.error_occurred_try_again));
    }
}
