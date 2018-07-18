package com.coinbase.android.signin.state;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.signin.SignInRouter;
import javax.inject.Inject;

@ControllerScope
public class UpfrontKycIdentityFailedPresenter {
    static String HEADER = "header";
    static String HINT = "hint";
    private final UpfrontKycIdentityFailedRouter mRouter;
    private final UpfrontKycIdentityFailedScreen mScreen;
    private final SignInRouter mSignInRouter;

    @Inject
    public UpfrontKycIdentityFailedPresenter(UpfrontKycIdentityFailedScreen screen, SignInRouter signInRouter, UpfrontKycIdentityFailedRouter router) {
        this.mScreen = screen;
        this.mSignInRouter = signInRouter;
        this.mRouter = router;
    }

    void onViewCreated(Bundle args) {
        this.mScreen.setHeader(args.getString(HEADER));
        this.mScreen.setHint(args.getString(HINT));
    }

    boolean onBackPressed() {
        this.mSignInRouter.cancel(UpfrontKycIdentityFailedPresenter$$Lambda$1.lambdaFactory$());
        return true;
    }

    static /* synthetic */ void lambda$onBackPressed$0() {
    }

    void onTryAgainClicked() {
        this.mRouter.routeToDocumentSelector();
    }
}
