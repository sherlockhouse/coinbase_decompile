package com.coinbase.android.signin.state;

import android.app.Application;
import android.content.Context;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.signin.AuthRouter;
import com.coinbase.android.signin.StateDisclosureFinishedConnector;
import com.coinbase.android.signin.StateListSelectorConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.CoinbaseInternal;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
/* compiled from: StateSelectorPresenter.kt */
public final class StateSelectorPresenter {
    public static final Companion Companion = new Companion();
    private static final String NEW_YORK = NEW_YORK;
    private final AuthRouter mAuthRouter;
    private final Context mContext;
    private final Logger mLogger;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final StateRegistrationRouter mRouter;
    private final StateSelectorScreen mScreen;
    private HashMap<String, String> mSelectedState;
    private final SnackBarWrapper mSnackBarWrapper;
    private final StateDisclosureFinishedConnector mStateDisclosureFinishedConnector;
    private final StateListSelectorConnector mStateListSelectorConnector;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    /* compiled from: StateSelectorPresenter.kt */
    public static final class Companion {
        private Companion() {
        }

        private final String getNEW_YORK() {
            return StateSelectorPresenter.NEW_YORK;
        }
    }

    @Inject
    public StateSelectorPresenter(Application application, LoginManager mLoginManager, StateSelectorScreen mScreen, StateRegistrationRouter mRouter, StateListSelectorConnector mStateListSelectorConnector, SnackBarWrapper mSnackBarWrapper, StateDisclosureFinishedConnector mStateDisclosureFinishedConnector, MixpanelTracking mMixpanelTracking, AuthRouter mAuthRouter, @MainScheduler Scheduler mMainScheduler) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        Intrinsics.checkParameterIsNotNull(mLoginManager, "mLoginManager");
        Intrinsics.checkParameterIsNotNull(mScreen, "mScreen");
        Intrinsics.checkParameterIsNotNull(mRouter, "mRouter");
        Intrinsics.checkParameterIsNotNull(mStateListSelectorConnector, "mStateListSelectorConnector");
        Intrinsics.checkParameterIsNotNull(mSnackBarWrapper, "mSnackBarWrapper");
        Intrinsics.checkParameterIsNotNull(mStateDisclosureFinishedConnector, "mStateDisclosureFinishedConnector");
        Intrinsics.checkParameterIsNotNull(mMixpanelTracking, "mMixpanelTracking");
        Intrinsics.checkParameterIsNotNull(mAuthRouter, "mAuthRouter");
        Intrinsics.checkParameterIsNotNull(mMainScheduler, "mMainScheduler");
        this.mLoginManager = mLoginManager;
        this.mScreen = mScreen;
        this.mRouter = mRouter;
        this.mStateListSelectorConnector = mStateListSelectorConnector;
        this.mSnackBarWrapper = mSnackBarWrapper;
        this.mStateDisclosureFinishedConnector = mStateDisclosureFinishedConnector;
        this.mMixpanelTracking = mMixpanelTracking;
        this.mAuthRouter = mAuthRouter;
        this.mMainScheduler = mMainScheduler;
        Logger logger = LoggerFactory.getLogger(StateSelectorPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(logger, "LoggerFactory.getLogger(…torPresenter::class.java)");
        this.mLogger = logger;
        this.mContext = application;
    }

    public final void onShow$coinbase_android_productionRelease() {
        if (this.mAuthRouter.startedRouting()) {
            this.mScreen.showProgressBar();
            this.mSubscription.add(this.mAuthRouter.routeToNext());
            return;
        }
        this.mSubscription.add(this.mStateListSelectorConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).filter(StateSelectorPresenter$onShow$1.INSTANCE).subscribe((Action1) new StateSelectorPresenter$onShow$2(this), (Action1) new StateSelectorPresenter$onShow$3(this)));
    }

    public final void onHide$coinbase_android_productionRelease() {
        this.mSubscription.clear();
    }

    public final void onStateSelectorClicked$coinbase_android_productionRelease() {
        this.mScreen.showStateSelector();
    }

    public final void onContinueButtonClicked$coinbase_android_productionRelease() {
        if (this.mSelectedState != null) {
            HashMap hashMap = this.mSelectedState;
            if (hashMap == null) {
                Intrinsics.throwNpe();
            }
            if (hashMap.get("name") != null) {
                this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_SELECT_STATE_TAPPED_CONTINUE, new String[0]);
                String access$getNEW_YORK$p = Companion.getNEW_YORK();
                hashMap = this.mSelectedState;
                if (hashMap == null) {
                    Intrinsics.throwNpe();
                }
                if (StringsKt.equals(access$getNEW_YORK$p, (String) hashMap.get("name"), true)) {
                    this.mRouter.routeToIdology();
                    return;
                } else {
                    createAddress();
                    return;
                }
            }
        }
        this.mSnackBarWrapper.show((int) R.string.please_select_state);
    }

    public final void onConfirmCancelButtonClicked$coinbase_android_productionRelease() {
        this.mLoginManager.signout();
        this.mSelectedState = (HashMap) null;
        this.mRouter.cancelStateRegistration();
    }

    private final void createAddress() {
        if (this.mSelectedState != null) {
            HashMap hashMap = this.mSelectedState;
            if (hashMap == null) {
                Intrinsics.throwNpe();
            }
            if (hashMap.get(Constants.ABBREVIATION) != null) {
                this.mScreen.showProgressBar();
                CoinbaseInternal client = this.mLoginManager.getClient();
                HashMap hashMap2 = this.mSelectedState;
                if (hashMap2 == null) {
                    Intrinsics.throwNpe();
                }
                this.mSubscription.add(client.createAddressRx(null, null, null, (String) hashMap2.get(Constants.ABBREVIATION), null).onBackpressureLatest().first().observeOn(this.mMainScheduler).subscribe((Action1) new StateSelectorPresenter$createAddress$1(this), (Action1) new StateSelectorPresenter$createAddress$2(this)));
                return;
            }
        }
        this.mSnackBarWrapper.showGenericError();
    }
}
