package com.coinbase.android.signin.state;

import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.settings.idology.IdologyArgsBuilder;
import com.coinbase.android.signin.AuthManager;
import com.coinbase.android.signin.AuthManager.ViewType;
import com.coinbase.android.signin.AuthRouter;
import com.coinbase.android.splittesting.SplitTestConstants;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.idology.Data;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.inject.Inject;

@ControllerScope
public class UpfrontKycIdologyFailureRouter implements IdologyFailureRouter {
    private final AuthManager mAuthManager;
    private final AuthRouter mAuthRouter;
    private final ActionBarController mController;
    private final IdologyArgsBuilder mIdologyArgsBuilder;
    private final LoginManager mLoginManager;
    private final SplitTesting mSplitTesting;

    @Inject
    public UpfrontKycIdologyFailureRouter(ActionBarController controller, IdologyArgsBuilder idologyArgsBuilder, LoginManager loginManager, AuthManager authManager, SplitTesting splitTesting, AuthRouter authRouter) {
        this.mController = controller;
        this.mIdologyArgsBuilder = idologyArgsBuilder;
        this.mLoginManager = loginManager;
        this.mAuthManager = authManager;
        this.mSplitTesting = splitTesting;
        this.mAuthRouter = authRouter;
    }

    public void routeToFailure(Data idologyData) {
        if (!TextUtils.isEmpty(idologyData.getFallbackToRestriction()) && (!Locale.US.getCountry().equals(this.mLoginManager.getActiveUserCountryCode()) || this.mSplitTesting.isInGroup(SplitTestConstants.US_MOBILE_FALLBACK_TO_IDV, SplitTestConstants.US_MOBILE_FALLBACK_GROUP))) {
            Set<String> restrictions = new HashSet();
            restrictions.add(idologyData.getFallbackToRestriction());
            Pair nextAuth = this.mAuthManager.getAuthTypeForRestrictions(restrictions);
            if (nextAuth.first != ViewType.NONE) {
                this.mAuthRouter.routeToNext(nextAuth);
                return;
            }
        }
        this.mController.pushModalController(new StateIdologyFailureController(this.mIdologyArgsBuilder.getIdologyArgs(idologyData)));
    }
}
