package com.coinbase.android.gdpr;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class GdprPrivacyPolicyPresenter$$Lambda$1 implements Action1 {
    private final GdprPrivacyPolicyPresenter arg$1;

    private GdprPrivacyPolicyPresenter$$Lambda$1(GdprPrivacyPolicyPresenter gdprPrivacyPolicyPresenter) {
        this.arg$1 = gdprPrivacyPolicyPresenter;
    }

    public static Action1 lambdaFactory$(GdprPrivacyPolicyPresenter gdprPrivacyPolicyPresenter) {
        return new GdprPrivacyPolicyPresenter$$Lambda$1(gdprPrivacyPolicyPresenter);
    }

    public void call(Object obj) {
        GdprPrivacyPolicyPresenter.lambda$onAcknowledgeClicked$0(this.arg$1, (Pair) obj);
    }
}
