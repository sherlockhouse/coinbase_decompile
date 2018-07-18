package com.coinbase.android.billing;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.Constants;
import com.coinbase.android.FragmentScope;
import com.coinbase.android.signin.StateListSelectorConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.BillingUtils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.billingAddress.BillingAddress;
import com.coinbase.api.internal.models.billingAddress.Data;
import java.lang.annotation.Documented;
import java.util.HashMap;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Qualifier;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@FragmentScope
public class AddBillingAddressPresenter {
    public String mAddressLine1 = "";
    public String mAddressLine2 = "";
    private final int mCheckFieldsErrorRes;
    public String mCity = "";
    public String mCountry = "";
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final AddBillingAddressScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    public String mState = "";
    private final StateListSelectorConnector mStateListSelectorConnector;
    private CompositeSubscription mSubscription;
    public String mZip = "";

    @Qualifier
    @Documented
    public @interface CheckFieldsErrorMessage {
    }

    @Inject
    AddBillingAddressPresenter(LoginManager loginManager, AddBillingAddressScreen screen, SnackBarWrapper snackBarWrapper, StateListSelectorConnector stateListSelectorConnector, @MainScheduler Scheduler mainScheduler, @CheckFieldsErrorMessage int checkFieldsErrorRes) {
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mStateListSelectorConnector = stateListSelectorConnector;
        this.mMainScheduler = mainScheduler;
        this.mCheckFieldsErrorRes = checkFieldsErrorRes;
    }

    public void onResume() {
        this.mSubscription = new CompositeSubscription();
        this.mSubscription.add(this.mStateListSelectorConnector.get().observeOn(this.mMainScheduler).filter(AddBillingAddressPresenter$$Lambda$1.lambdaFactory$()).subscribe(AddBillingAddressPresenter$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onResume$0(HashMap state) {
        return Boolean.valueOf(state != null);
    }

    public void onPause() {
        this.mSubscription.clear();
    }

    void onViewCreated() {
        if (!isUSCountry()) {
            this.mScreen.showNonUSPostalFields();
        }
        this.mScreen.setAddAddressButtonDisabled();
    }

    public void onContinueClick() {
        if (isValid()) {
            createBillingAddress();
        } else {
            this.mSnackBarWrapper.show(this.mCheckFieldsErrorRes);
        }
    }

    public void onFormUpdated() {
        if (isValid()) {
            this.mScreen.setAddAddressButtonEnabled();
        } else {
            this.mScreen.setAddAddressButtonDisabled();
        }
    }

    public void onStateClicked() {
        if (isUSCountry()) {
            this.mScreen.displayStateList();
        }
    }

    private boolean isUSCountry() {
        String countryCode = this.mLoginManager.getActiveUserCountryCode();
        this.mCountry = new Locale("", countryCode).getDisplayCountry();
        return countryCode.equals(Locale.US.getCountry());
    }

    private void createBillingAddress() {
        this.mScreen.showProgressDialog();
        this.mSubscription.add(this.mLoginManager.getClient().createBillingAddressRx(this.mAddressLine1, this.mAddressLine2, this.mCity, this.mState, this.mZip, this.mLoginManager.getActiveUserCountryCode()).observeOn(this.mMainScheduler).first().subscribe(AddBillingAddressPresenter$$Lambda$3.lambdaFactory$(this), AddBillingAddressPresenter$$Lambda$4.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$createBillingAddress$2(AddBillingAddressPresenter this_, Pair pair) {
        this_.mScreen.dismissProgressDialog();
        Response<BillingAddress> response = pair.first;
        Retrofit retrofit = pair.second;
        if (!response.isSuccessful()) {
            this_.mSnackBarWrapper.showError(response, retrofit);
        } else if (response.body() == null) {
            this_.mSnackBarWrapper.showGenericError();
        } else {
            Data billingAddress = ((BillingAddress) response.body()).getData();
            Intent intent = new Intent();
            intent.putExtra(Constants.BILLING_ID, billingAddress.getId());
            intent.putExtra(Constants.BILLING_STRING, BillingUtils.getFullAddress(billingAddress));
            this_.mScreen.setResult(-1, intent);
            this_.mScreen.finish();
        }
    }

    static /* synthetic */ void lambda$createBillingAddress$3(AddBillingAddressPresenter this_, Throwable t) {
        this_.mScreen.dismissProgressDialog();
        this_.mSnackBarWrapper.showFailure(t);
    }

    boolean isValid() {
        if (TextUtils.isEmpty(this.mAddressLine1) || TextUtils.isEmpty(this.mAddressLine1.trim()) || TextUtils.isEmpty(this.mCity) || TextUtils.isEmpty(this.mCity.trim()) || TextUtils.isEmpty(this.mState) || TextUtils.isEmpty(this.mState.trim()) || TextUtils.isEmpty(this.mZip) || TextUtils.isEmpty(this.mZip.trim()) || TextUtils.isEmpty(this.mCountry) || TextUtils.isEmpty(this.mCountry.trim())) {
            return false;
        }
        return true;
    }
}
