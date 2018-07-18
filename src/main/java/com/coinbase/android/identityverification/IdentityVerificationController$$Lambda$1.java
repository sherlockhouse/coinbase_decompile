package com.coinbase.android.identityverification;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class IdentityVerificationController$$Lambda$1 implements Action1 {
    private final IdentityVerificationController arg$1;

    private IdentityVerificationController$$Lambda$1(IdentityVerificationController identityVerificationController) {
        this.arg$1 = identityVerificationController;
    }

    public static Action1 lambdaFactory$(IdentityVerificationController identityVerificationController) {
        return new IdentityVerificationController$$Lambda$1(identityVerificationController);
    }

    public void call(Object obj) {
        IdentityVerificationController.lambda$getJumioProfiles$0(this.arg$1, (Pair) obj);
    }
}
