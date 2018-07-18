package com.coinbase.android.settings.idology;

import com.coinbase.api.internal.models.idology.Data;
import rx.functions.Action1;

final /* synthetic */ class IdologySettingsPresenter$$Lambda$7 implements Action1 {
    private final IdologySettingsPresenter arg$1;

    private IdologySettingsPresenter$$Lambda$7(IdologySettingsPresenter idologySettingsPresenter) {
        this.arg$1 = idologySettingsPresenter;
    }

    public static Action1 lambdaFactory$(IdologySettingsPresenter idologySettingsPresenter) {
        return new IdologySettingsPresenter$$Lambda$7(idologySettingsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.processVerificationResult((Data) obj);
    }
}
