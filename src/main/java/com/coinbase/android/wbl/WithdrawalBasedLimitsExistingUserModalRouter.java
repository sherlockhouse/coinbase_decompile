package com.coinbase.android.wbl;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.modalAlerts.ModalRouter;
import com.coinbase.android.modalAlerts.ModalRouterAggregator;
import com.coinbase.android.signin.AuthManager.ViewType;
import com.coinbase.android.splittesting.FlavorSplitTestConstants;
import com.coinbase.android.splittesting.SplitTestConstants;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Observable;
import rx.functions.Action0;

@ControllerScope
public class WithdrawalBasedLimitsExistingUserModalRouter implements ModalRouter {
    public static final String VIEWED_WBL_EXISTING_USER_MESSAGE = "viewed_wbl_existing_user_message";
    private final ActionBarController mController;
    private final Gson mGson = new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create();
    private final LoginManager mLoginManager;
    private final SharedPreferences mSharedPrefs;
    private final SplitTesting mSplitTesting;

    @Inject
    public WithdrawalBasedLimitsExistingUserModalRouter(SharedPreferences sharedPreferences, LoginManager loginManager, ActionBarController actionBarController, SplitTesting splitTesting) {
        this.mSharedPrefs = sharedPreferences;
        this.mLoginManager = loginManager;
        this.mController = actionBarController;
        this.mSplitTesting = splitTesting;
    }

    public Observable<Action0> route() {
        if (!this.mSplitTesting.isInGroup(SplitTestConstants.WBL_EXISTING_USER_SPLIT_TEST, FlavorSplitTestConstants.WBL_ENABLED) || this.mSharedPrefs.contains(VIEWED_WBL_EXISTING_USER_MESSAGE)) {
            return Observable.just(null);
        }
        if (this.mSharedPrefs.getInt(ModalRouterAggregator.INITIATING_AUTH_SCREEN, ViewType.LOGIN.ordinal()) != ViewType.LOGIN.ordinal()) {
            return Observable.just(null);
        }
        return this.mLoginManager.getClient().getTiersRx().map(WithdrawalBasedLimitsExistingUserModalRouter$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ Action0 lambda$route$1(WithdrawalBasedLimitsExistingUserModalRouter this_, Pair pair) {
        if (!((Response) pair.first).isSuccessful() || ((Response) pair.first).body() == null) {
            return null;
        }
        return WithdrawalBasedLimitsExistingUserModalRouter$$Lambda$2.lambdaFactory$(this_, pair);
    }

    static /* synthetic */ void lambda$null$0(WithdrawalBasedLimitsExistingUserModalRouter this_, Pair pair) {
        this_.mSharedPrefs.edit().putBoolean(VIEWED_WBL_EXISTING_USER_MESSAGE, true).apply();
        Bundle bundle = new Bundle();
        bundle.putString(ExistingUserDialogPresenter.ACCOUNT_LEVELS, this_.mGson.toJson(((Response) pair.first).body()));
        this_.mController.pushDialogModalController(new ExistingUserDialogController(this_.mController.appendModalArgs(bundle)));
    }
}
