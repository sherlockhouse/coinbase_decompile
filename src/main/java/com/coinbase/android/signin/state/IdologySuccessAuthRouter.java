package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.settings.idology.IdologySuccessRouter;
import com.coinbase.android.signin.AuthRouter;
import javax.inject.Inject;
import rx.Subscription;

@ControllerScope
public class IdologySuccessAuthRouter implements IdologySuccessRouter {
    private final AuthRouter mAuthRouter;

    @Inject
    public IdologySuccessAuthRouter(AuthRouter authRouter) {
        this.mAuthRouter = authRouter;
    }

    public Subscription routeToSuccessPage() {
        return this.mAuthRouter.routeToNext();
    }
}
