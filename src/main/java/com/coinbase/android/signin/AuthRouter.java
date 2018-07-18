package com.coinbase.android.signin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.signin.AuthManager.ViewType;
import com.coinbase.android.signin.state.StateIdologyNameFormController;
import com.coinbase.android.signin.state.StateLockedController;
import com.coinbase.android.signin.state.StateSelectorController;
import com.coinbase.android.signin.state.StateSuspendedController;
import com.coinbase.android.signin.state.UpfrontKycIdentityProcessingController;
import com.coinbase.android.signin.state.UpfrontKycIdentityProcessingPresenter;
import com.coinbase.android.task.GetUserTask.AdminFlags;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.ActivityPermissionCheckUtils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.v2.models.user.User;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;

@ControllerScope
public class AuthRouter {
    public static final String ROUTED_BY_SCHEME = "routed_by_scheme";
    private static final String STARTED_ROUTING = "started_routing";
    private final AppCompatActivity mAppCompatActivity;
    private final AuthCompletionFactory mAuthCompletionFactory;
    private final AuthManager mAuthManager;
    private final ActionBarController mController;
    private final Logger mLogger = LoggerFactory.getLogger(AuthRouter.class);
    private final Scheduler mMainScheduler;
    private final ActivityPermissionCheckUtils mPermissionCheckUtils;

    @Inject
    public AuthRouter(ActionBarController controller, AuthManager authManager, ActivityPermissionCheckUtils permissionCheckUtils, AppCompatActivity appCompatActivity, AuthCompletionFactory authCompletionFactory, @MainScheduler Scheduler mainScheduler) {
        this.mController = controller;
        this.mAuthManager = authManager;
        this.mAppCompatActivity = appCompatActivity;
        this.mAuthCompletionFactory = authCompletionFactory;
        this.mPermissionCheckUtils = permissionCheckUtils;
        this.mMainScheduler = mainScheduler;
    }

    public Subscription routeToNext() {
        return routeToNextObservable().subscribe(AuthRouter$$Lambda$1.lambdaFactory$(), AuthRouter$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ void lambda$routeToNext$0(Void b) {
    }

    static /* synthetic */ void lambda$routeToNext$1(Throwable t) {
    }

    public Observable<Void> routeToNextObservable() {
        this.mController.getArgs().putBoolean(STARTED_ROUTING, true);
        return this.mAuthManager.getAuthTypeForUser().onBackpressureLatest().observeOn(this.mMainScheduler).doOnError(AuthRouter$$Lambda$3.lambdaFactory$(this)).flatMap(AuthRouter$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$routeToNextObservable$2(AuthRouter this_, Throwable t) {
        this_.mLogger.error("Error routing to next controller", t);
        this_.mAppCompatActivity.finish();
    }

    public Observable<Void> routeToNext(User user) {
        return routeToNext(this.mAuthManager.getAuthTypeForUser(user));
    }

    public Observable<Void> routeToNext(Pair<ViewType, String> pair) {
        this.mController.getArgs().putBoolean(STARTED_ROUTING, true);
        ActionBarController actionBarController = getNextController(pair.first, TextUtils.equals((CharSequence) pair.second, "STATE_UNSUPPORTED"));
        if (actionBarController == null) {
            return routeToSignedInView();
        }
        pushOrReplaceModalController(actionBarController);
        return Observable.just(null);
    }

    public boolean startedRouting() {
        return this.mController.getArgs().containsKey(STARTED_ROUTING);
    }

    final boolean loadAuthFromBundle(Bundle bundle) throws SecurityException {
        if (bundle == null) {
            return false;
        }
        ActionBarController nextController = getNextController(ViewType.from(bundle.getInt(RegistrationControllerActivity.NEXT_FLOW)), bundle.getBoolean("STATE_UNSUPPORTED"), this.mAuthManager.isSignUp());
        if (nextController == null) {
            return false;
        }
        if (bundle.getBoolean(ROUTED_BY_SCHEME) || this.mPermissionCheckUtils.checkCallingPackage((AppCompatActivity) this.mController.getActivity())) {
            clearBundle(bundle);
            this.mController.pushModalController(nextController);
            return true;
        }
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_PERMISSION_CHECK_FAILED, new String[0]);
        throw new SecurityException("Invalid calling package [" + this.mController.getActivity().getCallingPackage() + "]");
    }

    ActionBarController getNextController(ViewType nextFlow, boolean stateUnsupported) {
        return getNextController(nextFlow, stateUnsupported, this.mAuthManager.isSignUp());
    }

    private ActionBarController getNextController(ViewType nextFlow, boolean stateUnsupported, boolean isSignUp) {
        Bundle modalArgs = this.mController.appendModalArgs(new Bundle());
        if (stateUnsupported) {
            if (isSignUp) {
                return new StateSuspendedController(modalArgs);
            }
            return new StateLockedController(modalArgs);
        } else if (nextFlow == null) {
            return null;
        } else {
            switch (nextFlow) {
                case EMAIL_VERIFICATION:
                    return new EmailVerifyController(modalArgs);
                case TWO_FACTOR:
                    return new TwoFactorController(modalArgs);
                case DEVICE_VERIFICATION:
                    return new DeviceVerifyController(modalArgs);
                case STATE_REGISTRATION:
                    return new StateSelectorController(modalArgs);
                case PHONE_VERIFICATION:
                    return new SignInPhoneNumberController(modalArgs);
                case IDENTITY_VERIFICATION:
                    return new StateIdologyNameFormController(modalArgs);
                case TERMS_OF_SERVICE:
                    return new AcceptTermsController(modalArgs);
                case IDV_REQUIRED:
                    modalArgs.putBoolean(UpfrontKycIdentityProcessingPresenter.IS_INTERSTITIAL, true);
                    return new UpfrontKycIdentityProcessingController(modalArgs);
                case IDV_WITH_FACE_MATCH_REQUIRED:
                    modalArgs.putBoolean(AdminFlags.REQUIRE_JUMIO_FACE_MATCH.toString(), true);
                    modalArgs.putBoolean(UpfrontKycIdentityProcessingPresenter.IS_INTERSTITIAL, true);
                    return new UpfrontKycIdentityProcessingController(modalArgs);
                case NONE:
                    return null;
                default:
                    return new StateSelectorController(modalArgs);
            }
        }
    }

    private void pushOrReplaceModalController(ActionBarController actionBarController) {
        if (this.mController.getRouter().getBackstackSize() > 1) {
            this.mController.replaceModalController(actionBarController);
        } else {
            this.mController.pushModalController(actionBarController);
        }
    }

    private void clearBundle(Bundle bundle) {
        bundle.remove(RegistrationControllerActivity.NEXT_FLOW);
        bundle.remove("STATE_UNSUPPORTED");
    }

    private Observable<Void> routeToSignedInView() {
        return this.mAuthCompletionFactory.get().complete();
    }
}
