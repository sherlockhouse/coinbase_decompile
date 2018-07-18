package com.coinbase.android.identityverification;

import rx.functions.Action1;

final /* synthetic */ class IdentityVerificationController$$Lambda$2 implements Action1 {
    private final IdentityVerificationController arg$1;

    private IdentityVerificationController$$Lambda$2(IdentityVerificationController identityVerificationController) {
        this.arg$1 = identityVerificationController;
    }

    public static Action1 lambdaFactory$(IdentityVerificationController identityVerificationController) {
        return new IdentityVerificationController$$Lambda$2(identityVerificationController);
    }

    public void call(Object obj) {
        IdentityVerificationController.lambda$getJumioProfiles$1(this.arg$1, (Throwable) obj);
    }
}
