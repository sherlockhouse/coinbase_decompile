package com.coinbase.android.paymentmethods.card;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.billing.BillingAddressDeletedConnector;
import com.coinbase.android.ui.KeyboardListener;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.MystiqueListButtonConnector;
import com.coinbase.android.ui.MystiqueListButtonConnector.MystiqueListButtonEvent;
import com.coinbase.android.ui.MystiqueListSelectorConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.android.utils.BillingUtils;
import com.coinbase.android.utils.BindableString;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.billingAddress.BillingAddresses;
import com.coinbase.api.internal.models.billingAddress.Data;
import com.coinbase.api.internal.models.jumio.JumioProfiles;
import com.coinbase.api.internal.models.jumio.JumioProfiles.Status;
import com.coinbase.api.internal.models.paymentMethods.CardSetup;
import com.coinbase.api.internal.models.paymentMethods.Mapping;
import com.coinbase.api.internal.models.paymentMethods.verify.Verify;
import com.coinbase.v2.models.paymentMethods.Data.VerificationMethod;
import com.coinbase.v2.models.paymentMethods.PaymentMethod;
import com.worldpay.cse.WPCardData;
import com.worldpay.cse.WorldpayCSE;
import com.worldpay.cse.exception.WPCSEException;
import com.worldpay.cse.exception.WPCSEInvalidCardData;
import java.lang.annotation.Documented;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Qualifier;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class CardFormPresenter {
    private static final int CARD_BIN_LENGTH = 6;
    private static final int COMPLETE_EXPIRY_LENGTH = 5;
    private static final String FIRST_MONTH_LEADING_ZERO = "01";
    private static final int FOUR_DIGIT_CVV = 4;
    private static final int MONTH_LENGTH = 2;
    public static final String REQUIRES_BILLING_ADDRESS = "requires-billing-address";
    public static final String REQUIRES_CDV = "requires-cdv";
    public static final String REQUIRES_JUMIO = "requires-Jumio";
    private static final int THREE_DIGIT_CVV = 3;
    private final AllFieldsValidContainer mAllFieldsValidContainer;
    private Bundle mArgs;
    public BindableString mBilling = new BindableString("");
    private final BillingAddressDeletedConnector mBillingAddressDeletedConnector;
    List<Data> mBillingAddresses = new ArrayList();
    String mBillingId;
    public String mCardNumber = "";
    String mCardType;
    private final int mCheckFieldsErrorRes;
    private final Context mContext;
    private final CreditCardValidator mCreditCardValidator;
    public String mCvv = "";
    public String mExpiry = "";
    public String mFullName = "";
    boolean mIsWorldPay = false;
    private KeyboardListener mKeyboardListener;
    private String mLastExpiry = "";
    private final Logger mLogger = LoggerFactory.getLogger(CardFormPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MystiqueListButtonConnector mMystiqueListButtonConnector;
    private final MystiqueListSelectorConnector mMystiqueListSelectorConnector;
    com.coinbase.v2.models.paymentMethods.Data mPaymentMethod;
    private final int mPaymentProcessingErrorRes;
    private long mPollingStartTime = 0;
    private final SharedPreferences mPrefs;
    boolean mRequiresBillingAddress = false;
    boolean mRequiresCDV = false;
    boolean mRequiresJumio = false;
    private final CardFormRouter mRouter;
    private final CardFormScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final SuccessRouter mSuccessRouter;
    private final WorldPayPollingWrapper mWorldPayPollingWrapper;
    private final WorldPayValidator mWorldPayValidator;
    public String mZip = "";

    private class AllFieldsValidContainer {
        boolean mBillingAddressValid;
        boolean mCardNumberValid;
        boolean mCvvValid;
        boolean mExpiryValid;
        boolean mFullNameValid;
        boolean mZipValid;

        private AllFieldsValidContainer() {
        }

        private boolean allFieldsValid() {
            return this.mCardNumberValid && this.mExpiryValid && this.mCvvValid && this.mFullNameValid && (this.mBillingAddressValid || this.mZipValid);
        }
    }

    @Qualifier
    @Documented
    public @interface CheckFieldsErrorMessage {
    }

    @Qualifier
    @Documented
    public @interface PaymentProcessingErrorMessage {
    }

    @Inject
    CardFormPresenter(LoginManager loginManager, CardFormScreen screen, Application app, CardFormRouter router, SharedPreferences prefs, SnackBarWrapper snackBarWrapper, @CheckFieldsErrorMessage int checkFieldsErrorRes, @PaymentProcessingErrorMessage int paymentProcessingErrorRes, CreditCardValidator creditCardValidator, MystiqueListSelectorConnector mystiqueListSelectorConnector, MystiqueListButtonConnector mystiqueListButtonConnector, BillingAddressDeletedConnector billingAddressDeletedConnector, SuccessRouter successRouter, KeyboardListener keyboardListener, WorldPayValidator worldPayValidator, WorldPayPollingWrapper worldPayPollingWrapper, @MainScheduler Scheduler mainScheduler) {
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mContext = app;
        this.mRouter = router;
        this.mPrefs = prefs;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mCheckFieldsErrorRes = checkFieldsErrorRes;
        this.mPaymentProcessingErrorRes = paymentProcessingErrorRes;
        this.mCreditCardValidator = creditCardValidator;
        this.mMystiqueListSelectorConnector = mystiqueListSelectorConnector;
        this.mMystiqueListButtonConnector = mystiqueListButtonConnector;
        this.mBillingAddressDeletedConnector = billingAddressDeletedConnector;
        this.mSuccessRouter = successRouter;
        this.mKeyboardListener = keyboardListener;
        this.mAllFieldsValidContainer = new AllFieldsValidContainer();
        this.mWorldPayValidator = worldPayValidator;
        this.mWorldPayPollingWrapper = worldPayPollingWrapper;
        this.mMainScheduler = mainScheduler;
    }

    void onDestroy() {
        this.mSubscription.clear();
    }

    void onViewCreated(Bundle bundle) {
        this.mArgs = bundle;
        this.mScreen.hideContent();
        this.mRequiresJumio = bundle.getBoolean(REQUIRES_JUMIO, false);
        this.mCardType = bundle.getString(CardFormScreen.CARD_TYPE);
        if (this.mCardType == null) {
            this.mCardType = ApiConstants.DEBIT_CARD;
        }
        if (this.mRequiresJumio) {
            checkJumioProfiles();
            return;
        }
        this.mIsWorldPay = TextUtils.equals(this.mCardType, ApiConstants.WORLDPAY_CARD);
        this.mScreen.showWorldPayInfo();
        loadCardForm();
    }

    private void loadCardForm() {
        this.mScreen.showContent();
        this.mRequiresCDV = this.mArgs.getBoolean(REQUIRES_CDV, false);
        this.mRequiresBillingAddress = this.mArgs.getBoolean(REQUIRES_BILLING_ADDRESS, false);
        if (this.mRequiresBillingAddress) {
            this.mScreen.showRequiresBillingAddressView();
        } else {
            this.mScreen.showDoesntRequireBillingAddressView();
        }
        this.mScreen.setFullName(this.mPrefs.getString(Constants.KEY_ACCOUNT_FULL_NAME, null));
        if (!this.mLoginManager.getActiveUserCountryCode().equalsIgnoreCase("US")) {
            this.mScreen.showNonUsZipCode();
        }
        this.mScreen.setContinueButtonDisabled();
        onFormUpdated();
    }

    void checkJumioProfiles() {
        this.mScreen.showProgress();
        this.mLoginManager.getClient().getJumioProfiles(new CallbackWithRetrofit<JumioProfiles>() {
            public void onResponse(Call<JumioProfiles> call, Response<JumioProfiles> response, Retrofit retrofit) {
                CardFormPresenter.this.mScreen.hideProgress();
                if (!response.isSuccessful()) {
                    Utils.showMessage(CardFormPresenter.this.mContext, Utils.getErrorMessage(response, retrofit), 1);
                } else if (CardFormPresenter.this.mScreen.isShown()) {
                    Status verificationStatus = ((JumioProfiles) response.body()).getJumioProfileStatus();
                    if (verificationStatus == Status.COMPLETED) {
                        CardFormPresenter.this.loadCardForm();
                        return;
                    }
                    Bundle args = new Bundle();
                    args.putSerializable(PaymentMethodVerificationController.JUMIO_STATUS, verificationStatus);
                    CardFormPresenter.this.mRouter.routePaymentVerificationController(args);
                }
            }

            public void onFailure(Call<JumioProfiles> call, Throwable t) {
                Utils.showMessage(CardFormPresenter.this.mContext, t, 1);
            }
        });
    }

    void onResume() {
        this.mSubscription.add(this.mMystiqueListSelectorConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(CardFormPresenter$$Lambda$1.lambdaFactory$(this)));
        this.mSubscription.add(this.mMystiqueListButtonConnector.get().onBackpressureLatest().filter(CardFormPresenter$$Lambda$2.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(CardFormPresenter$$Lambda$3.lambdaFactory$(this)));
        this.mSubscription.add(this.mMystiqueListButtonConnector.get().onBackpressureLatest().filter(CardFormPresenter$$Lambda$4.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(CardFormPresenter$$Lambda$5.lambdaFactory$(this)));
        this.mSubscription.add(this.mBillingAddressDeletedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(CardFormPresenter$$Lambda$6.lambdaFactory$(this)));
        this.mSubscription.add(this.mKeyboardListener.isKeyboardVisible().observeOn(this.mMainScheduler).subscribe(CardFormPresenter$$Lambda$7.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$onResume$0(CardFormPresenter this_, Integer position) {
        Data billingAddress = (Data) this_.mBillingAddresses.get(position.intValue());
        this_.setBillingAddress(billingAddress.getId(), BillingUtils.getFullAddress(billingAddress));
        this_.mScreen.hideBillingAddressLayout();
    }

    static /* synthetic */ Boolean lambda$onResume$1(MystiqueListButtonEvent v) {
        return Boolean.valueOf(v == MystiqueListButtonEvent.ACTION);
    }

    static /* synthetic */ Boolean lambda$onResume$3(MystiqueListButtonEvent v) {
        return Boolean.valueOf(v == MystiqueListButtonEvent.CLOSE);
    }

    static /* synthetic */ void lambda$onResume$5(CardFormPresenter this_, Data billingAddress) {
        this_.mBillingAddresses.remove(billingAddress);
        this_.mScreen.notifyBillingAddressAdapterDataSetChanged();
        this_.setBillingAddress("", "");
    }

    static /* synthetic */ void lambda$onResume$6(CardFormPresenter this_, Boolean visible) {
        if (!visible.booleanValue()) {
            this_.mScreen.showWorldPayInfo();
        } else if (visible.booleanValue()) {
            this_.mScreen.hideWorldPayInfo();
        }
    }

    void onPause() {
        this.mSubscription.clear();
    }

    void onLastFieldEditorAction(int actionId) {
        if (actionId == 6) {
            submitForm();
        }
    }

    public void submitForm() {
        if (isValid()) {
            this.mScreen.hideKeyboard();
            if (this.mIsWorldPay) {
                this.mRouter.routeWorldpayCDVPermissionDialog();
                return;
            } else if (this.mRequiresCDV) {
                this.mRouter.routeCardCDVDialog();
                return;
            } else {
                paymentCardSetup();
                return;
            }
        }
        this.mSnackBarWrapper.show(this.mCheckFieldsErrorRes);
    }

    public void onBillingAddressClicked() {
        onFormUpdated();
        this.mScreen.showBillingAddressLayout();
        getBillingAddresses();
    }

    private void setBillingAddress(String billingId, String billingAddress) {
        this.mBilling.setValue(billingAddress);
        this.mBillingId = billingId;
        this.mAllFieldsValidContainer.mBillingAddressValid = !TextUtils.isEmpty(billingAddress);
        setContinueButtonEnabledIfAllFieldsValid();
    }

    void setFullName(String fullName) {
        this.mFullName = fullName;
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 66:
                if (resultCode == 66) {
                    onConfirmCDVPermission();
                    return;
                }
                return;
            case 103:
                if (resultCode == -1) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        setBillingAddress(extras.getString(Constants.BILLING_ID, ""), extras.getString(Constants.BILLING_STRING, ""));
                        return;
                    }
                    return;
                }
                return;
            case 104:
                this.mScreen.closeForm();
                return;
            default:
                return;
        }
    }

    void onConfirmCDVPermission() {
        if (this.mIsWorldPay) {
            encryptDataAndStartWorldPayProcessing();
        } else if (this.mRequiresCDV) {
            paymentCardSetup();
        }
    }

    void paymentCardSetup() {
        if (this.mScreen.isShown()) {
            this.mScreen.showAddingCardProgress();
            this.mSubscription.add(this.mLoginManager.getClient().paymentCardSetupRx().first().observeOn(this.mMainScheduler).subscribe(CardFormPresenter$$Lambda$8.lambdaFactory$(this), CardFormPresenter$$Lambda$9.lambdaFactory$(this)));
        }
    }

    static /* synthetic */ void lambda$paymentCardSetup$7(CardFormPresenter this_, Pair pair) {
        Response<CardSetup> response = pair.first;
        Retrofit retrofit = pair.second;
        if (response.isSuccessful()) {
            this_.processPaymentCard(((CardSetup) response.body()).getData());
            return;
        }
        this_.mScreen.hideProgress();
        this_.mSnackBarWrapper.showError(response, retrofit);
    }

    static /* synthetic */ void lambda$paymentCardSetup$8(CardFormPresenter this_, Throwable t) {
        this_.mScreen.hideProgress();
        this_.mSnackBarWrapper.showFailure(t);
    }

    void onFormUpdated() {
        if (this.mScreen.isShown()) {
            if (TextUtils.getTrimmedLength(this.mCardNumber) == 0) {
                this.mAllFieldsValidContainer.mCardNumberValid = false;
                this.mScreen.clearCardNumberError();
            } else if (isValidCard(this.mCardNumber.replace("\\s", ""))) {
                this.mAllFieldsValidContainer.mCardNumberValid = true;
                this.mScreen.showCardNumberValid();
            } else {
                this.mAllFieldsValidContainer.mCardNumberValid = false;
                this.mScreen.showCardNumberError();
            }
            if (TextUtils.getTrimmedLength(this.mFullName) == 0) {
                this.mAllFieldsValidContainer.mFullNameValid = false;
                this.mScreen.clearFullNameValid();
            } else if (isValidName(this.mFullName)) {
                this.mAllFieldsValidContainer.mFullNameValid = true;
                this.mScreen.showFullNameValid();
            } else {
                this.mAllFieldsValidContainer.mFullNameValid = false;
                this.mScreen.showFullNameError();
            }
            if (TextUtils.getTrimmedLength(this.mCvv) == 0) {
                this.mAllFieldsValidContainer.mCvvValid = false;
                this.mScreen.clearCvvError();
            } else if (isValidCvv(this.mCvv)) {
                this.mAllFieldsValidContainer.mCvvValid = true;
                this.mScreen.showCvvValid();
            } else {
                this.mAllFieldsValidContainer.mCvvValid = false;
                this.mScreen.showCvvError();
            }
            if (TextUtils.getTrimmedLength(this.mExpiry) == 0) {
                this.mAllFieldsValidContainer.mExpiryValid = false;
                this.mScreen.clearExpiryError();
            } else if (isValidCardExpiry(this.mExpiry)) {
                this.mAllFieldsValidContainer.mExpiryValid = true;
                this.mScreen.showExpiryValid();
            } else {
                String fixCardExpiry = fixCardExpiry(this.mExpiry);
                if (TextUtils.isEmpty(fixCardExpiry)) {
                    this.mAllFieldsValidContainer.mExpiryValid = false;
                    this.mScreen.showExpiryError();
                } else {
                    this.mScreen.setExpiry(fixCardExpiry);
                    this.mScreen.showExpiryValid();
                    this.mAllFieldsValidContainer.mExpiryValid = true;
                }
            }
            if (TextUtils.isEmpty(this.mZip)) {
                this.mAllFieldsValidContainer.mZipValid = false;
            } else {
                this.mAllFieldsValidContainer.mZipValid = true;
            }
            setContinueButtonEnabledIfAllFieldsValid();
        }
    }

    void onCardNumberTextUpdated(Editable editable) {
        this.mScreen.clearCardNumberError();
    }

    void onCvvTextUpdated(Editable editable) {
        this.mScreen.clearCvvError();
    }

    void onExpiryTextUpdated(Editable editable) {
        String expiry = editable.toString();
        try {
            if (isValidCardExpiry(expiry)) {
                this.mAllFieldsValidContainer.mExpiryValid = true;
                this.mScreen.showExpiryValid();
                setContinueButtonEnabledIfAllFieldsValid();
                return;
            }
            this.mAllFieldsValidContainer.mExpiryValid = false;
            this.mScreen.clearExpiryError();
            setContinueButtonEnabledIfAllFieldsValid();
            if (expiry.contains("/")) {
                this.mLastExpiry = expiry;
                return;
            }
            if (expiry.startsWith(this.mLastExpiry)) {
                try {
                    if (TextUtils.getTrimmedLength(expiry) > 2) {
                        this.mLastExpiry = expiry;
                        return;
                    }
                    int month = Integer.valueOf(expiry).intValue();
                    if (month > 0 && month <= 12) {
                        if (month > 1 && month < 10 && expiry.length() == 1) {
                            this.mScreen.setExpiry("0" + expiry + "/");
                        } else if (month != 1) {
                            this.mScreen.setExpiry(expiry + "/");
                        } else if (month == 1 && TextUtils.equals(FIRST_MONTH_LEADING_ZERO, expiry)) {
                            this.mScreen.setExpiry(expiry + "/");
                        }
                    }
                } catch (NumberFormatException e) {
                }
            }
            this.mLastExpiry = expiry;
        } finally {
            this.mLastExpiry = expiry;
        }
    }

    void onInvestNowClicked() {
        if (this.mSuccessRouter.shouldRouteSuccess()) {
            this.mSuccessRouter.routeSuccess();
            return;
        }
        this.mRouter.routeBuyActionToMainActivity();
        this.mScreen.popToRoot();
    }

    private void setContinueButtonEnabledIfAllFieldsValid() {
        if (this.mAllFieldsValidContainer.allFieldsValid()) {
            this.mScreen.setContinueButtonEnabled();
        } else {
            this.mScreen.setContinueButtonDisabled();
        }
    }

    private boolean isValidCard(String card) {
        if (this.mIsWorldPay) {
            return this.mWorldPayValidator.isValidCardNumber(card);
        }
        return this.mCreditCardValidator.isValid(this.mCardNumber.replace("\\s", ""));
    }

    private boolean isValidCvv(String cvv) {
        if (this.mIsWorldPay) {
            return this.mWorldPayValidator.isValidCvc(cvv);
        }
        int length = TextUtils.getTrimmedLength(cvv);
        return length == 4 || length == 3;
    }

    private boolean isValidName(String fullName) {
        if (this.mIsWorldPay) {
            return this.mWorldPayValidator.isValidName(fullName);
        }
        return !TextUtils.isEmpty(fullName);
    }

    private String[] parseMonthAndYear(String trimmed) {
        if (!trimmed.contains("/")) {
            return null;
        }
        String[] parts = trimmed.split("/");
        if (parts.length != 2) {
            return null;
        }
        if (TextUtils.isEmpty(parts[0]) || TextUtils.isEmpty(parts[1])) {
            return null;
        }
        return parts;
    }

    private boolean isValidMonth(int month) {
        return month > 0 && month <= 12;
    }

    boolean isValidCardExpiry(String expiry) {
        boolean z = true;
        if (TextUtils.isEmpty(expiry)) {
            return false;
        }
        if (this.mIsWorldPay) {
            if (!(this.mWorldPayValidator.isValidMonth(getExpireMonth()) && this.mWorldPayValidator.isValidYear(getExpireYear()))) {
                z = false;
            }
            return z;
        }
        String trimmed = expiry.trim();
        if (TextUtils.isEmpty(trimmed) || trimmed.length() != 5) {
            return false;
        }
        String[] parts = parseMonthAndYear(trimmed);
        if (parts == null) {
            return false;
        }
        try {
            int month = Integer.valueOf(parts[0]).intValue();
            int year = Integer.valueOf(parts[1]).intValue();
            if (!isValidMonth(month) || year < 0) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    String fixCardExpiry(String expiry) {
        if (TextUtils.isEmpty(expiry)) {
            return null;
        }
        String[] parts = parseMonthAndYear(expiry.trim());
        if (parts == null) {
            return null;
        }
        StringBuilder fixedExpiry = new StringBuilder("");
        try {
            int month = Integer.valueOf(parts[0]).intValue();
            int year = Integer.valueOf(parts[1]).intValue();
            if (isValidMonth(month)) {
                if (parts[0].length() == 1) {
                    fixedExpiry.append("0").append(month).append("/");
                } else {
                    fixedExpiry.append(parts[0]).append("/");
                }
            }
            if (year >= 0) {
                if (parts[1].length() == 1) {
                    fixedExpiry.append("0").append(year);
                } else {
                    fixedExpiry.append(parts[1]);
                }
            }
            return fixedExpiry.toString();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void processPaymentCard(com.coinbase.api.internal.models.paymentMethods.Data cardSetup) {
        HashMap<String, Object> params = new HashMap();
        for (Mapping mapping : cardSetup.getMapping()) {
            String name = mapping.getName();
            String id = mapping.getId();
            int i = -1;
            switch (id.hashCode()) {
                case -2143818164:
                    if (id.equals(ApiConstants.CUSTOMER_NAME)) {
                        i = 3;
                        break;
                    }
                    break;
                case -1993813776:
                    if (id.equals(ApiConstants.EXPIRATION_MONTH)) {
                        i = 4;
                        break;
                    }
                    break;
                case -1422950858:
                    if (id.equals(ApiConstants.ACTION)) {
                        i = 0;
                        break;
                    }
                    break;
                case -1230578114:
                    if (id.equals(ApiConstants.CARD_VERIFICATION_NUMBER)) {
                        i = 6;
                        break;
                    }
                    break;
                case -114479744:
                    if (id.equals(ApiConstants.ONE_TIME_TOKEN)) {
                        i = 1;
                        break;
                    }
                    break;
                case 578603864:
                    if (id.equals(ApiConstants.CARD_NUMBER)) {
                        i = 2;
                        break;
                    }
                    break;
                case 767314893:
                    if (id.equals(ApiConstants.EXPIRATION_YEAR)) {
                        i = 5;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                    String action = ApiConstants.ADD;
                    if (mapping.getValue() != null) {
                        action = mapping.getValue();
                    }
                    params.put(name, action);
                    break;
                case 1:
                    params.put(name, cardSetup.getOneTimeToken());
                    break;
                case 2:
                    params.put(name, this.mCardNumber);
                    break;
                case 3:
                    params.put(name, this.mFullName);
                    break;
                case 4:
                    String month = getExpireMonth();
                    if (month != null) {
                        params.put(name, month);
                        break;
                    }
                    this.mScreen.hideProgress();
                    this.mSnackBarWrapper.showGenericError();
                    return;
                case 5:
                    String year = getExpireYear();
                    if (year != null) {
                        params.put(name, year);
                        break;
                    }
                    this.mScreen.hideProgress();
                    this.mSnackBarWrapper.showGenericError();
                    return;
                case 6:
                    params.put(name, this.mCvv);
                    break;
                default:
                    break;
            }
        }
        String processUrl = cardSetup.getProcessUrl();
        if (processUrl.length() > 0 && processUrl.charAt(processUrl.length() - 1) == '/') {
            processUrl = processUrl.substring(0, processUrl.length() - 1);
        }
        finishProcessPaymentCard(processUrl, params, cardSetup);
    }

    void finishProcessPaymentCard(String processUrl, HashMap<String, Object> params, com.coinbase.api.internal.models.paymentMethods.Data cardSetup) {
        this.mSubscription.add(this.mLoginManager.getClient().processPaymentCardRx(processUrl, params).first().observeOn(this.mMainScheduler).subscribe(CardFormPresenter$$Lambda$10.lambdaFactory$(this, cardSetup), CardFormPresenter$$Lambda$11.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$finishProcessPaymentCard$9(CardFormPresenter this_, com.coinbase.api.internal.models.paymentMethods.Data cardSetup, Pair pair) {
        if (pair.first.isSuccessful()) {
            this_.completeSession(cardSetup);
            return;
        }
        this_.mScreen.hideProgress();
        this_.mSnackBarWrapper.show(this_.mPaymentProcessingErrorRes);
    }

    static /* synthetic */ void lambda$finishProcessPaymentCard$10(CardFormPresenter this_, Throwable t) {
        this_.mScreen.hideProgress();
        this_.mSnackBarWrapper.showFailure(t);
    }

    private void completeSession(com.coinbase.api.internal.models.paymentMethods.Data cardSetup) {
        String month = getExpireMonth();
        String year = getExpireYear();
        if (month == null || year == null) {
            this.mScreen.hideProgress();
            return;
        }
        HashMap<String, Object> params = new HashMap();
        params.put("type", this.mCardType);
        params.put(ApiConstants.ONE_TIME_TOKEN, cardSetup.getOneTimeToken());
        params.put(ApiConstants.EXPIRATION_MONTH, month);
        params.put(ApiConstants.EXPIRATION_YEAR, year);
        if (this.mBillingId == null || this.mBillingId.isEmpty()) {
            params.put(ApiConstants.POSTAL_CODE, this.mZip);
        } else {
            params.put(ApiConstants.BILLING_ADDRESS, this.mBillingId);
        }
        params.put("user_provided_bin", trimCardNumber(this.mCardNumber));
        finishCompleteSession(params);
    }

    void finishCompleteSession(HashMap<String, Object> params) {
        this.mSubscription.add(this.mLoginManager.getClient().verifyPaymentCardRx(params).first().observeOn(this.mMainScheduler).subscribe(CardFormPresenter$$Lambda$12.lambdaFactory$(this), CardFormPresenter$$Lambda$13.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$finishCompleteSession$11(CardFormPresenter this_, Pair pair) {
        Response<Verify> response = pair.first;
        Retrofit retrofit = pair.second;
        if (this_.mScreen.isShown()) {
            this_.mScreen.hideProgress();
            if (response.isSuccessful()) {
                this_.getPaymentMethod(((Verify) response.body()).getData().getId());
            } else {
                this_.mSnackBarWrapper.showError(response, retrofit);
            }
        }
    }

    static /* synthetic */ void lambda$finishCompleteSession$12(CardFormPresenter this_, Throwable t) {
        if (this_.mScreen.isShown()) {
            this_.mScreen.hideProgress();
            this_.mSnackBarWrapper.showFailure(t);
        }
    }

    private void getPaymentMethod(String id) {
        this.mSubscription.add(this.mLoginManager.getClient().getPaymentMethodRx(id).first().observeOn(this.mMainScheduler).subscribe(CardFormPresenter$$Lambda$14.lambdaFactory$(this), CardFormPresenter$$Lambda$15.lambdaFactory$()));
    }

    static /* synthetic */ void lambda$getPaymentMethod$13(CardFormPresenter this_, Pair pair) {
        Response<PaymentMethod> response = pair.first;
        if (response.isSuccessful()) {
            this_.mPaymentMethod = ((PaymentMethod) response.body()).getData();
            if (this_.mRequiresCDV) {
                this_.mRouter.routePlaidAccountLoginActivity(this_.mPaymentMethod);
                return;
            }
            this_.mScreen.switchToAllDoneView(String.format(this_.mContext.getString(R.string.all_set_card_details), new Object[]{this_.mPaymentMethod.getName()}));
            if (this_.mSuccessRouter.shouldRouteSuccess()) {
                this_.mScreen.setFinishButtonText(this_.mContext.getString(R.string.continue_button));
            } else {
                this_.mScreen.setFinishButtonText(this_.mContext.getString(R.string.btn_invest_now));
            }
        }
    }

    static /* synthetic */ void lambda$getPaymentMethod$14(Throwable t) {
    }

    private boolean isValid() {
        if (!this.mCreditCardValidator.isValid(this.mCardNumber) || this.mFullName.trim().isEmpty() || this.mScreen.getExpiry().trim().isEmpty() || this.mCvv.trim().isEmpty() || (this.mZip.trim().isEmpty() && this.mBilling.getValue().trim().isEmpty())) {
            return false;
        }
        return true;
    }

    String getExpireYear() {
        String str = null;
        String year = this.mScreen.getExpiry();
        int index = year.indexOf("/");
        if (index != -1) {
            try {
                str = new SimpleDateFormat("yyyy", Locale.getDefault()).format(new SimpleDateFormat("yy", Locale.getDefault()).parse(year.substring(index + 1, year.length())));
            } catch (Exception e) {
                this.mSnackBarWrapper.showGenericError();
            }
        }
        return str;
    }

    String getExpireMonth() {
        String month = this.mScreen.getExpiry();
        int index = month.indexOf("/");
        if (index == -1) {
            return null;
        }
        return month.substring(0, index);
    }

    String trimCardNumber(String cardNumber) {
        return cardNumber.substring(0, 6);
    }

    void onCardHelpClick() {
        this.mScreen.hideKeyboard();
        this.mScreen.showCardNumberHelpBottomSheet();
    }

    void onExpiryHelpClick() {
        this.mScreen.hideKeyboard();
        this.mScreen.showExpiryHelpBottomSheet();
    }

    void onCvvHelpClick() {
        this.mScreen.hideKeyboard();
        this.mScreen.showCvvHelpBottomSheet();
    }

    void getBillingAddresses() {
        this.mScreen.showBillingAddressProgressBar();
        this.mScreen.initializeBillingAddress(this.mBillingAddresses);
        this.mSubscription.add(this.mLoginManager.getClient().getBillingAddresses().observeOn(this.mMainScheduler).onBackpressureLatest().first().subscribe(CardFormPresenter$$Lambda$16.lambdaFactory$(this), CardFormPresenter$$Lambda$17.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$getBillingAddresses$15(CardFormPresenter this_, Pair pair) {
        Response<BillingAddresses> response = pair.first;
        this_.mScreen.hideBillingAddressProgressBar();
        if (response.isSuccessful()) {
            this_.mBillingAddresses.clear();
            this_.mBillingAddresses.addAll(((BillingAddresses) response.body()).getData());
            this_.mScreen.notifyBillingAddressAdapterDataSetChanged();
            return;
        }
        this_.mSnackBarWrapper.show((int) R.string.could_not_retrieve_data);
    }

    static /* synthetic */ void lambda$getBillingAddresses$16(CardFormPresenter this_, Throwable t) {
        this_.mScreen.hideBillingAddressProgressBar();
        this_.mSnackBarWrapper.showFailure(t);
    }

    private void gotoNewBillingAddress() {
        this.mScreen.hideBillingAddressLayout();
        this.mRouter.routeNewAddressActivity();
    }

    public void onBottomSheetCloseClicked() {
        this.mScreen.hideBottomSheet();
    }

    private void encryptDataAndStartWorldPayProcessing() {
        WorldpayCSE worldpayCSE = new WorldpayCSE();
        worldpayCSE.setPublicKey(WorldPayValidator.WORLDPAY_PUBLIC_KEY);
        WPCardData cardData = new WPCardData();
        cardData.setCardNumber(this.mCardNumber);
        cardData.setCvc(this.mCvv);
        cardData.setExpiryMonth(getExpireMonth());
        cardData.setExpiryYear(getExpireYear());
        cardData.setCardHolderName(this.mFullName);
        try {
            String encryptedBlob = worldpayCSE.encrypt(cardData);
            HashMap<String, Object> params = new HashMap();
            params.put("type", ApiConstants.WORLDPAY_CARD);
            params.put(ApiConstants.WORLDPAY_ENCRYPTED_DATA, encryptedBlob);
            params.put(ApiConstants.BILLING_ADDRESS, this.mBillingId);
            params.put("user_provided_bin", getBin(this.mCardNumber));
            params.put(ApiConstants.CARD_SOURCE, ApiConstants.CARD_MANUAL_ENTRY);
            params.put(ApiConstants.CARD_OMEGA, Boolean.valueOf(false));
            this.mScreen.showContinueProgress();
            this.mSubscription.add(this.mLoginManager.getClient().verifyPaymentCardRx(params).observeOn(this.mMainScheduler).subscribe(CardFormPresenter$$Lambda$18.lambdaFactory$(this), CardFormPresenter$$Lambda$19.lambdaFactory$(this)));
        } catch (WPCSEInvalidCardData e) {
            this.mLogger.error("Invalid card data thrown should never happen", e);
            if (this.mWorldPayValidator.isCardNumberError(e)) {
                this.mScreen.showCardNumberError();
            } else if (this.mWorldPayValidator.isCvcError(e)) {
                this.mScreen.showCvvError();
            } else if (this.mWorldPayValidator.isNameError(e)) {
                this.mScreen.showFullNameError();
            } else if (this.mWorldPayValidator.isExpiryDateError(e)) {
                this.mScreen.showExpiryError();
            }
        } catch (WPCSEException e2) {
            this.mLogger.error("World pay exception", e2);
            this.mSnackBarWrapper.showGenericError();
        }
    }

    static /* synthetic */ void lambda$encryptDataAndStartWorldPayProcessing$17(CardFormPresenter this_, Pair pair) {
        Response<Verify> response = pair.first;
        if (response.isSuccessful()) {
            this_.mPollingStartTime = System.currentTimeMillis();
            this_.pollWorldPayAddCard(((Verify) response.body()).getData());
            return;
        }
        this_.mScreen.hideContinueProgress();
        this_.mSnackBarWrapper.showError(response);
    }

    static /* synthetic */ void lambda$encryptDataAndStartWorldPayProcessing$18(CardFormPresenter this_, Throwable t) {
        this_.mSnackBarWrapper.showFailure(t);
        this_.mScreen.hideContinueProgress();
    }

    void pollWorldPayAddCard(com.coinbase.api.internal.models.paymentMethods.verify.Data createdPaymentMethod) {
        this.mSubscription.add(this.mLoginManager.getClient().getPaymentMethodVerifiedRx(createdPaymentMethod.getId()).first().observeOn(this.mMainScheduler).subscribe(CardFormPresenter$$Lambda$20.lambdaFactory$(this, createdPaymentMethod), CardFormPresenter$$Lambda$21.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$pollWorldPayAddCard$23(CardFormPresenter this_, com.coinbase.api.internal.models.paymentMethods.verify.Data createdPaymentMethod, Pair pair) {
        Response<PaymentMethod> response = pair.first;
        if (response.isSuccessful()) {
            this_.mPaymentMethod = ((PaymentMethod) response.body()).getData();
            if (this_.mPaymentMethod == null || this_.mPaymentMethod.getVerified() == null) {
                this_.mScreen.hideContinueProgress();
                this_.mSnackBarWrapper.showGenericError();
                return;
            } else if (this_.mPaymentMethod.getVerified().booleanValue()) {
                this_.mScreen.hideContinueProgress();
                this_.mScreen.switchToAllDoneView(String.format(this_.mContext.getString(R.string.all_set_card_details), new Object[]{createdPaymentMethod.getName()}));
                if (this_.mSuccessRouter.shouldRouteSuccess()) {
                    this_.mScreen.setFinishButtonText(this_.mContext.getString(R.string.continue_button));
                    return;
                } else {
                    this_.mScreen.setFinishButtonText(this_.mContext.getString(R.string.btn_invest_now));
                    return;
                }
            } else {
                VerificationMethod verificationMethod = this_.mPaymentMethod.getVerificationMethod();
                if (verificationMethod != null && verificationMethod == VerificationMethod.CDV) {
                    this_.mScreen.showContinueProgress();
                    this_.mSubscription.add(this_.mLoginManager.getClient().getPaymentMethodRx(createdPaymentMethod.getId()).first().observeOn(this_.mMainScheduler).subscribe(CardFormPresenter$$Lambda$24.lambdaFactory$(this_, response), CardFormPresenter$$Lambda$25.lambdaFactory$(this_)));
                    return;
                } else if (System.currentTimeMillis() - this_.mPollingStartTime < this_.mWorldPayPollingWrapper.getPollMaxTime()) {
                    this_.mSubscription.add(Observable.just(null).delay(this_.mWorldPayPollingWrapper.getPollDelay(), TimeUnit.SECONDS).subscribe(CardFormPresenter$$Lambda$22.lambdaFactory$(this_, createdPaymentMethod), CardFormPresenter$$Lambda$23.lambdaFactory$(this_, createdPaymentMethod)));
                    return;
                } else {
                    this_.mSnackBarWrapper.showGenericErrorTryAgain();
                    this_.mScreen.hideContinueProgress();
                    return;
                }
            }
        }
        this_.mScreen.hideContinueProgress();
        this_.mSnackBarWrapper.showError(response);
    }

    static /* synthetic */ void lambda$null$21(CardFormPresenter this_, Response response, Pair getPaymentMethodPair) {
        Response<PaymentMethod> getPaymentMethodResponse = getPaymentMethodPair.first;
        if (response.isSuccessful()) {
            this_.mRouter.routePlaidAccountLoginActivity(((PaymentMethod) getPaymentMethodResponse.body()).getData());
            return;
        }
        this_.mScreen.hideContinueProgress();
        this_.mSnackBarWrapper.showError(response);
    }

    static /* synthetic */ void lambda$null$22(CardFormPresenter this_, Throwable t) {
        this_.mScreen.hideContent();
        this_.mSnackBarWrapper.showFailure(t);
    }

    static /* synthetic */ void lambda$pollWorldPayAddCard$24(CardFormPresenter this_, Throwable t) {
        this_.mScreen.hideContinueProgress();
        this_.mSnackBarWrapper.showFailure(t);
    }

    private String getBin(String cardNumber) {
        return cardNumber.substring(0, 6);
    }
}
