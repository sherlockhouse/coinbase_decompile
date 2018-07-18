package com.coinbase.android.quickstart;

import android.util.Pair;
import com.coinbase.android.phone.VerifyPhoneHandler.VerifyPhoneCaller;
import rx.functions.Action1;

final /* synthetic */ class QuickstartManager$$Lambda$4 implements Action1 {
    private final QuickstartManager arg$1;
    private final VerifyPhoneCaller arg$2;

    private QuickstartManager$$Lambda$4(QuickstartManager quickstartManager, VerifyPhoneCaller verifyPhoneCaller) {
        this.arg$1 = quickstartManager;
        this.arg$2 = verifyPhoneCaller;
    }

    public static Action1 lambdaFactory$(QuickstartManager quickstartManager, VerifyPhoneCaller verifyPhoneCaller) {
        return new QuickstartManager$$Lambda$4(quickstartManager, verifyPhoneCaller);
    }

    public void call(Object obj) {
        QuickstartManager.lambda$getPhoneNumbersAndShowVerify$3(this.arg$1, this.arg$2, (Pair) obj);
    }
}
