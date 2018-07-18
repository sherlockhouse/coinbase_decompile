package com.coinbase.android.signin;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.phoneNumber.Data;
import com.coinbase.api.internal.models.phoneNumber.PhoneNumber;
import com.coinbase.api.internal.models.phoneNumber.PhoneNumbers;
import com.coinbase.v2.models.errors.ErrorBody;
import com.coinbase.v2.models.errors.Errors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.inject.Inject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class SignInPhoneNumberPresenter {
    private static final String ROUTED_TO_VERIFY = "routed_to_verify";
    private Bundle mArgs;
    private final AuthRouter mAuthRouter;
    private final Context mContext;
    private final Logger mLogger = LoggerFactory.getLogger(SignInPhoneNumberPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final SignInPhoneNumberRouter mRouter;
    private final SignInPhoneNumberScreen mScreen;
    private final SignInRouter mSignInRouter;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    class CountryCode {
        String alpha2;
        String code;
        String name;

        CountryCode(String code, String name, String alpha2) {
            this.code = code;
            this.name = name;
            this.alpha2 = alpha2;
        }

        public String toString() {
            return this.name;
        }

        public String getAlpha2() {
            return this.alpha2;
        }

        public String getCode() {
            return this.code;
        }
    }

    @Inject
    public SignInPhoneNumberPresenter(SignInPhoneNumberScreen screen, SignInPhoneNumberRouter router, SignInRouter signInRouter, AuthRouter authRouter, LoginManager loginManager, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, Application app, @MainScheduler Scheduler scheduler) {
        this.mScreen = screen;
        this.mRouter = router;
        this.mSignInRouter = signInRouter;
        this.mAuthRouter = authRouter;
        this.mLoginManager = loginManager;
        this.mMixpanelTracking = mixpanelTracking;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mContext = app;
        this.mMainScheduler = scheduler;
    }

    void onCreate() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_VIEWED, new String[0]);
        try {
            Pair<CountryCode[], Integer> countryCodesAndSelectedIndex = getCountryCodes();
            this.mScreen.populateCountryAdapter((CountryCode[]) countryCodesAndSelectedIndex.first);
            this.mScreen.setSelectedCountry(((Integer) countryCodesAndSelectedIndex.second).intValue());
        } catch (JSONException e) {
            this.mLogger.error("Exception reading country code", e);
            this.mSnackBarWrapper.showGenericError();
        }
    }

    protected void onUserConfirm(String input, String countryAlpha2) {
        if (TextUtils.isEmpty(input) || TextUtils.isEmpty(countryAlpha2)) {
            this.mSnackBarWrapper.show((int) R.string.invalid_phone);
            return;
        }
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_CONTINUE, new String[0]);
        addPhoneNumber(input, countryAlpha2);
    }

    protected void onSkipClicked() {
        this.mScreen.showProgressBar();
        this.mScreen.hideAddPhoneNumber();
        getPhoneNumbersAndRouteToVerificationScreenIfNecessary();
    }

    void onShow(Bundle args) {
        this.mArgs = args;
        if (this.mAuthRouter.startedRouting()) {
            this.mScreen.showProgressBar();
            this.mScreen.hideAddPhoneNumber();
            this.mSubscription.add(this.mAuthRouter.routeToNext());
        } else if (args.containsKey(ROUTED_TO_VERIFY)) {
            this.mScreen.hideProgressBar();
            this.mScreen.showAddPhoneNumber();
            this.mScreen.showSkipPhoneNumber();
        } else {
            this.mScreen.showProgressBar();
            this.mScreen.hideAddPhoneNumber();
            this.mScreen.hideSkipPhoneNumber();
            getPhoneNumbersAndRouteToVerificationScreenIfNecessary();
        }
    }

    void onHide() {
        this.mSubscription.clear();
    }

    boolean onBackPressed() {
        this.mSignInRouter.cancel(SignInPhoneNumberPresenter$$Lambda$1.lambdaFactory$(this));
        return true;
    }

    private void addPhoneNumber(String phone, String countryCode) {
        HashMap<String, Object> params = new HashMap();
        params.put("number", phone);
        params.put("country", countryCode);
        this.mScreen.showProgressDialog();
        this.mSubscription.add(this.mLoginManager.getClient().createPhoneNumberRx(null, params).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SignInPhoneNumberPresenter$$Lambda$2.lambdaFactory$(this, phone), SignInPhoneNumberPresenter$$Lambda$3.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$addPhoneNumber$1(SignInPhoneNumberPresenter this_, String phone, Pair pair) {
        Response<PhoneNumber> response = pair.first;
        Retrofit retrofit = pair.second;
        this_.mScreen.hideProgressDialog();
        if (!this_.mScreen.isShown()) {
            return;
        }
        if (response.isSuccessful()) {
            Log.d("Coinbase", "Phone number added, now waiting for SMS");
            Data number = ((PhoneNumber) response.body()).getData();
            this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_CONTINUE_SUCCESS, new String[0]);
            this_.mRouter.showVerifyPhoneDialogController(number.getId(), phone, null);
            this_.mArgs.putBoolean(ROUTED_TO_VERIFY, true);
            return;
        }
        Errors errors = Utils.getErrors(response, retrofit);
        if (errors != null) {
            Iterator it = errors.getErrors().iterator();
            if (it.hasNext()) {
                ErrorBody errorBody = (ErrorBody) it.next();
                if (errorBody.getId().equalsIgnoreCase(ApiConstants.TWO_FACTOR_REQUIRED_ERROR)) {
                    this_.mRouter.showVerifyPhoneDialogController(null, phone, null);
                    this_.mArgs.putBoolean(ROUTED_TO_VERIFY, true);
                    this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_CONTINUE_SUCCESS, new String[0]);
                    return;
                } else if (!TextUtils.isEmpty(errorBody.getMessage())) {
                    this_.mSnackBarWrapper.show(errorBody.getMessage());
                    return;
                } else {
                    return;
                }
            }
            this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_CONTINUE_FAILURE, new String[0]);
        }
    }

    static /* synthetic */ void lambda$addPhoneNumber$2(SignInPhoneNumberPresenter this_, Throwable t) {
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_CONTINUE_FAILURE, new String[0]);
        this_.mScreen.hideProgressDialog();
        this_.mSnackBarWrapper.showFailure(t);
    }

    private void getPhoneNumbersAndRouteToVerificationScreenIfNecessary() {
        this.mSubscription.add(this.mLoginManager.getClient().getPhoneNumbersRx().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SignInPhoneNumberPresenter$$Lambda$4.lambdaFactory$(this), SignInPhoneNumberPresenter$$Lambda$5.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$getPhoneNumbersAndRouteToVerificationScreenIfNecessary$3(SignInPhoneNumberPresenter this_, Pair pair) {
        this_.mScreen.hideProgressBar();
        Response<PhoneNumbers> response = pair.first;
        if (!response.isSuccessful() || ((PhoneNumbers) response.body()).getData() == null || ((PhoneNumbers) response.body()).getData().isEmpty()) {
            this_.mScreen.showAddPhoneNumber();
            return;
        }
        List<Data> phoneNumbers = ((PhoneNumbers) response.body()).getData();
        ArrayList<String> unverifiedPhoneNumbers = new ArrayList();
        for (Data number : phoneNumbers) {
            if (!(number.getVerified() == null || number.getVerified().booleanValue() || number.getId() == null || number.getNumber() == null)) {
                if (phoneNumbers.size() == 1) {
                    this_.mRouter.showVerifyPhoneDialogController(number.getId(), null, number.getNumber());
                    this_.mArgs.putBoolean(ROUTED_TO_VERIFY, true);
                    return;
                }
                unverifiedPhoneNumbers.add(number.getId());
            }
        }
        if (unverifiedPhoneNumbers.isEmpty()) {
            this_.mScreen.showProgressBar();
            this_.mScreen.hideAddPhoneNumber();
            this_.mSubscription.add(this_.mAuthRouter.routeToNext());
            return;
        }
        this_.mRouter.showVerifyPhoneDialogControllerMultipleNumbers(unverifiedPhoneNumbers);
        this_.mArgs.putBoolean(ROUTED_TO_VERIFY, true);
    }

    static /* synthetic */ void lambda$getPhoneNumbersAndRouteToVerificationScreenIfNecessary$4(SignInPhoneNumberPresenter this_, Throwable t) {
        this_.mScreen.hideProgressBar();
        this_.mScreen.showAddPhoneNumber();
    }

    private Pair<CountryCode[], Integer> getCountryCodes() throws JSONException {
        JSONArray array = new JSONArray(new JSONTokener(new Scanner(this.mContext.getResources().openRawResource(R.raw.countries)).useDelimiter("\\A").next()));
        CountryCode[] result = new CountryCode[array.length()];
        String userCountry = this.mLoginManager.getActiveUserCountryCode();
        int selectedIndex = 0;
        for (int i = 0; i < array.length(); i++) {
            JSONArray country = array.getJSONArray(i);
            result[i] = new CountryCode(country.getString(1), country.getString(0), country.getString(2));
            if (userCountry.equalsIgnoreCase(result[i].getAlpha2())) {
                selectedIndex = i;
            }
        }
        return new Pair(result, Integer.valueOf(selectedIndex));
    }
}
