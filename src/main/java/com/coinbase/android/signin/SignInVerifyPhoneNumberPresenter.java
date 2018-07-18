package com.coinbase.android.signin;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.phoneNumber.PhoneNumber;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class SignInVerifyPhoneNumberPresenter {
    private static final int EXPECTED_INPUT_SIZE = 7;
    public static final String MASKED_PHONE_NUMBER = "masked_phone_number";
    public static final String PHONE_ID = "phone_id";
    public static final String PHONE_IDS = "phone_ids";
    public static final String PHONE_NUMBER = "phone_number";
    private final AuthRouter mAuthRouter;
    private final Context mContext;
    private final Logger mLogger = LoggerFactory.getLogger(SignInVerifyPhoneNumberPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private String mMaskedPhoneNumber;
    private final MixpanelTracking mMixpanelTracking;
    private String mPhoneNumber;
    private final LinkedList<String> mPhoneNumberIds = new LinkedList();
    private final SignInVerifyPhoneNumberScreen mSignInVerifyPhoneNumberScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public SignInVerifyPhoneNumberPresenter(Application app, SignInVerifyPhoneNumberScreen signInVerifyPhoneNumberScreen, AuthRouter authRouter, LoginManager loginManager, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, @MainScheduler Scheduler scheduler) {
        this.mContext = app;
        this.mSignInVerifyPhoneNumberScreen = signInVerifyPhoneNumberScreen;
        this.mAuthRouter = authRouter;
        this.mLoginManager = loginManager;
        this.mMixpanelTracking = mixpanelTracking;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMainScheduler = scheduler;
    }

    public void onCreate(Bundle args) {
        String phoneId = args.getString("phone_id");
        ArrayList<String> phoneIds = args.getStringArrayList(PHONE_IDS);
        if (!TextUtils.isEmpty(phoneId)) {
            this.mPhoneNumberIds.add(phoneId);
        } else if (phoneIds == null || phoneIds.isEmpty()) {
            this.mSnackBarWrapper.showGenericError();
            return;
        } else {
            this.mPhoneNumberIds.addAll(phoneIds);
        }
        this.mPhoneNumber = args.getString("phone_number");
        this.mMaskedPhoneNumber = args.getString(MASKED_PHONE_NUMBER);
        if (!TextUtils.isEmpty(this.mPhoneNumber)) {
            this.mSignInVerifyPhoneNumberScreen.setVerificationText(HtmlCompat.fromHtml(this.mContext.getString(R.string.sign_in_verify_phone_number_text, new Object[]{this.mPhoneNumber})));
        } else if (TextUtils.isEmpty(this.mMaskedPhoneNumber)) {
            this.mSignInVerifyPhoneNumberScreen.setVerificationText(HtmlCompat.fromHtml(this.mContext.getString(R.string.sign_in_verify_phone_number_default_text)));
        } else {
            this.mSignInVerifyPhoneNumberScreen.setVerificationText(HtmlCompat.fromHtml(this.mContext.getString(R.string.sign_in_verify_phone_number_masked_text, new Object[]{this.mMaskedPhoneNumber.replaceAll("\\D+", "")})));
        }
    }

    public void onShow() {
        if (this.mAuthRouter.startedRouting()) {
            this.mSubscription.add(this.mAuthRouter.routeToNext());
            this.mSignInVerifyPhoneNumberScreen.showProgress();
            this.mSignInVerifyPhoneNumberScreen.hideVerifyPhoneNumberView();
            return;
        }
        this.mSignInVerifyPhoneNumberScreen.hideProgress();
        this.mSignInVerifyPhoneNumberScreen.showVerifyPhoneNumberView();
    }

    public void onHide() {
        this.mSubscription.clear();
    }

    void onResendTokenClicked() {
        if (this.mPhoneNumberIds.isEmpty()) {
            this.mSnackBarWrapper.showGenericError();
            this.mLogger.error("Null phone id when resending token, should never happen");
            return;
        }
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_RESEND_CODE, new String[0]);
        List<Observable<Pair<Response<Void>, Retrofit>>> resendObservables = new LinkedList();
        Iterator it = this.mPhoneNumberIds.iterator();
        while (it.hasNext()) {
            resendObservables.add(this.mLoginManager.getClient().resendTwoFactorTokenRx((String) it.next()));
        }
        this.mSignInVerifyPhoneNumberScreen.showProgressDialog();
        this.mSubscription.add(Observable.combineLatest(resendObservables, SignInVerifyPhoneNumberPresenter$$Lambda$1.lambdaFactory$(this)).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SignInVerifyPhoneNumberPresenter$$Lambda$2.lambdaFactory$(this), SignInVerifyPhoneNumberPresenter$$Lambda$3.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$onResendTokenClicked$1(SignInVerifyPhoneNumberPresenter this_, Pair pair) {
        Response<Void> response = pair.first;
        Retrofit retrofit = pair.second;
        this_.mSignInVerifyPhoneNumberScreen.hideProgressDialog();
        if (response.isSuccessful()) {
            this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_SIGN_IN_PHONE_NUMBER_TWO_FACTOR_RESENT, new String[0]);
            this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_RESEND_CODE_SUCCESS, new String[0]);
            this_.mSnackBarWrapper.show((int) R.string.sign_in_two_factor_code_resent);
            return;
        }
        this_.mSnackBarWrapper.showError(response, retrofit);
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_RESEND_CODE_FAILURE, new String[0]);
    }

    static /* synthetic */ void lambda$onResendTokenClicked$2(SignInVerifyPhoneNumberPresenter this_, Throwable t) {
        this_.mSignInVerifyPhoneNumberScreen.hideProgressDialog();
        this_.mSnackBarWrapper.showFailure(t);
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_RESEND_CODE_FAILURE, new String[0]);
    }

    void onFormUpdated() {
        String trimmedInput = this.mSignInVerifyPhoneNumberScreen.getInput().trim();
        if (!TextUtils.isEmpty(trimmedInput) && trimmedInput.length() >= 7) {
            if (this.mPhoneNumberIds.isEmpty()) {
                this.mSnackBarWrapper.showGenericError();
                this.mLogger.error("Null phone id when verifying phone number, should never happen");
                return;
            }
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_CONFIRM, new String[0]);
            verifyPhoneNumber(trimmedInput);
        }
    }

    private void verifyPhoneNumber(String token) {
        if (TextUtils.isEmpty(token)) {
            this.mSnackBarWrapper.show((int) R.string.token_empty_message);
        } else if (this.mPhoneNumberIds.isEmpty()) {
            this.mSnackBarWrapper.showGenericError();
        } else {
            this.mSignInVerifyPhoneNumberScreen.showProgressDialog();
            HashMap<String, Object> params = new HashMap();
            params.put("token", token);
            List<Observable<Pair<Response<PhoneNumber>, Retrofit>>> phoneNumberIdObservables = new LinkedList();
            Iterator it = this.mPhoneNumberIds.iterator();
            while (it.hasNext()) {
                phoneNumberIdObservables.add(this.mLoginManager.getClient().verifyPhoneNumberRx(params, (String) it.next()));
            }
            this.mSubscription.add(Observable.combineLatest(phoneNumberIdObservables, SignInVerifyPhoneNumberPresenter$$Lambda$4.lambdaFactory$(this)).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SignInVerifyPhoneNumberPresenter$$Lambda$5.lambdaFactory$(this), SignInVerifyPhoneNumberPresenter$$Lambda$6.lambdaFactory$(this)));
        }
    }

    static /* synthetic */ void lambda$verifyPhoneNumber$4(SignInVerifyPhoneNumberPresenter this_, Pair pair) {
        this_.mSignInVerifyPhoneNumberScreen.hideProgressDialog();
        if (pair == null) {
            this_.mSnackBarWrapper.showGenericError();
            return;
        }
        Response<PhoneNumber> response = pair.first;
        Retrofit retrofit = pair.second;
        if (response.isSuccessful()) {
            this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_CONFIRM_SUCCESS, new String[0]);
            this_.mSignInVerifyPhoneNumberScreen.showProgress();
            this_.mSignInVerifyPhoneNumberScreen.hideVerifyPhoneNumberView();
            this_.mSubscription.add(this_.mAuthRouter.routeToNext());
            return;
        }
        this_.mSnackBarWrapper.showError(response, retrofit);
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_CONFIRM_FAILURE, new String[0]);
    }

    static /* synthetic */ void lambda$verifyPhoneNumber$5(SignInVerifyPhoneNumberPresenter this_, Throwable t) {
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_CONFIRM_FAILURE, new String[0]);
        this_.mSignInVerifyPhoneNumberScreen.hideProgressDialog();
        this_.mSnackBarWrapper.showFailure(t);
    }

    private Pair<Response<PhoneNumber>, Retrofit> combineVerifyResults(Object... args) {
        Pair<Response<PhoneNumber>, Retrofit> chosenResponse = null;
        for (Pair<Response<PhoneNumber>, Retrofit> o : args) {
            if (o instanceof Pair) {
                Pair pair = o;
                if ((pair.first instanceof Response) && (pair.second instanceof Retrofit)) {
                    Response response = (Response) pair.first;
                    if ((response.body() == null || (response.body() instanceof PhoneNumber)) && (chosenResponse == null || response.isSuccessful())) {
                        chosenResponse = o;
                    }
                }
            }
        }
        return chosenResponse;
    }

    private Pair<Response<Void>, Retrofit> combineResendResults(Object... args) {
        Pair<Response<Void>, Retrofit> chosenResponse = null;
        for (Pair<Response<Void>, Retrofit> o : args) {
            if (o instanceof Pair) {
                Pair pair = o;
                if ((pair.first instanceof Response) && (pair.second instanceof Retrofit)) {
                    Response response = (Response) pair.first;
                    if (chosenResponse == null || response.isSuccessful()) {
                        chosenResponse = o;
                    }
                }
            }
        }
        return chosenResponse;
    }
}
