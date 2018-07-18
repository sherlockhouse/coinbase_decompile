package com.coinbase.android.paymentmethods;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.Constants;
import com.coinbase.android.FragmentScope;
import com.coinbase.android.R;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.paymentmethods.DeletePaymentMethodTask.DeletePaymentMethodListener;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.v2.models.paymentMethods.Data;
import com.coinbase.v2.models.paymentMethods.Data.CDVStatus;
import com.coinbase.v2.models.paymentMethods.Data.Type;
import com.coinbase.v2.models.paymentMethods.PaymentMethod;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@FragmentScope
public class IAVLoginPresenter {
    public static final String PAYMENT_METHOD = "payment_method";
    public String mAccountNumber;
    private final List<AccountTypeSpinnerItem> mAccountSpinnerItems = new LinkedList();
    public String mAccountType;
    private final BankAccountsUpdatedConnector mBankAccountsUpdatedConnector;
    public String mBankAmount1;
    public String mBankAmount2;
    public String mCardAmount1;
    public String mCardAmount2;
    private final Context mContext;
    public String mCustomerName;
    private boolean mIsInVerificationMode;
    private final LoginManager mLoginManager;
    private final MixpanelTracking mMixpanelTracking;
    private boolean mParentSuccessRouter;
    Data mPaymentMethod;
    private final IAVLoginRouter mRouter;
    public String mRoutingNumber;
    private final Scheduler mScheduler;
    IAVLoginScreen mScreen;
    private final SharedPreferences mSharedPrefs;
    private final SnackBarWrapper mSnackBarWrapper;
    IAVStep mStep;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    class AccountTypeSpinnerItem {
        String key;
        String value;

        private AccountTypeSpinnerItem(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return this.value;
        }

        public String getKey() {
            return this.key;
        }
    }

    enum IAVStep {
        NAME,
        TYPE,
        DETAILS,
        CDV_VERIFICATION
    }

    @Inject
    IAVLoginPresenter(IAVLoginScreen screen, Application app, IAVLoginRouter router, LoginManager loginManager, @MainScheduler Scheduler scheduler, SnackBarWrapper snackBarWrapper, MixpanelTracking mixpanelTracking, SharedPreferences sharedPrefs, BankAccountsUpdatedConnector bankAccountsUpdatedConnector) {
        this.mScreen = screen;
        this.mContext = app;
        this.mRouter = router;
        this.mLoginManager = loginManager;
        this.mScheduler = scheduler;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMixpanelTracking = mixpanelTracking;
        this.mSharedPrefs = sharedPrefs;
        this.mBankAccountsUpdatedConnector = bankAccountsUpdatedConnector;
        this.mAccountSpinnerItems.add(new AccountTypeSpinnerItem("checking", this.mContext.getString(R.string.account_type_checking)));
        this.mAccountSpinnerItems.add(new AccountTypeSpinnerItem("savings", this.mContext.getString(R.string.account_type_savings)));
        this.mAccountSpinnerItems.add(new AccountTypeSpinnerItem("business_checking", this.mContext.getString(R.string.account_type_business_checking)));
    }

    void onCreate(Bundle arguments) {
        Gson gson = new Gson();
        if (arguments == null || !arguments.containsKey("payment_method")) {
            this.mStep = IAVStep.NAME;
        } else {
            this.mPaymentMethod = (Data) gson.fromJson(arguments.getString("payment_method"), Data.class);
            this.mStep = IAVStep.CDV_VERIFICATION;
            this.mIsInVerificationMode = true;
        }
        if (arguments != null) {
            this.mParentSuccessRouter = arguments.getBoolean(Constants.PARENT_SUCCESS_ROUTER, false);
        }
        this.mCustomerName = this.mSharedPrefs.getString(Constants.KEY_ACCOUNT_FULL_NAME, null);
    }

    void onPause() {
        this.mSubscription.clear();
    }

    void onActivityCreated() {
        this.mScreen.initializeAccountTypeAdapter(this.mAccountSpinnerItems);
        this.mScreen.showContinueButtonDisabled();
        onFormUpdated();
        if (this.mPaymentMethod != null) {
            Type type = this.mPaymentMethod.getType();
            if (type == Type.CREDIT_CARD || type == Type.DEBIT_CARD || type == Type.WORLDPAY_CARD) {
                this.mScreen.showCdvVerificationAmountsForm();
                this.mMixpanelTracking.trackEvent(MixpanelTracking.VERIFY_CARD_VIEWED, new String[0]);
            } else {
                this.mScreen.showBankCdvVerificationAmountsForm();
                this.mMixpanelTracking.trackEvent(MixpanelTracking.VERIFY_BANK_ACCOUNT_VIEWED, new String[0]);
            }
            this.mScreen.hideIAVForm();
            if (!((this.mPaymentMethod.getCdvStatus() != null && this.mPaymentMethod.getCdvStatus() == CDVStatus.IN_PROGRESS) || type == Type.CREDIT_CARD || type == Type.DEBIT_CARD || type == Type.WORLDPAY_CARD)) {
                startAchVerification();
            }
        }
        if (this.mParentSuccessRouter) {
            this.mScreen.setInvestNowButtonText(this.mContext.getString(R.string.continue_button));
        } else {
            this.mScreen.setInvestNowButtonText(this.mContext.getString(R.string.btn_invest_now));
        }
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_M_ENTER_NAME_SCREEN, new String[0]);
    }

    void onStart() {
    }

    void onStop() {
        this.mSubscription.clear();
    }

    void onFormUpdated() {
        switch (this.mStep) {
            case DETAILS:
                if (isValidRoutingNumber() && isValidAccountNumber()) {
                    this.mScreen.showContinueButtonEnabled();
                    return;
                } else {
                    this.mScreen.showContinueButtonDisabled();
                    return;
                }
            case TYPE:
                return;
            case CDV_VERIFICATION:
                if (areValidAmountsEntered()) {
                    this.mScreen.showContinueButtonEnabled();
                    return;
                } else {
                    this.mScreen.showContinueButtonDisabled();
                    return;
                }
            default:
                if (isValidName()) {
                    this.mScreen.showContinueButtonEnabled();
                    return;
                } else {
                    this.mScreen.showContinueButtonDisabled();
                    return;
                }
        }
    }

    void onIAVNameFormSubmit() {
        this.mStep = IAVStep.TYPE;
        if (TextUtils.isEmpty(this.mCustomerName)) {
            this.mSnackBarWrapper.show((int) R.string.customer_name_required);
        } else {
            this.mScreen.switchToIAVTypeForm();
        }
    }

    void onIAVTypeFormSubmit() {
        this.mStep = IAVStep.DETAILS;
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_M_ENTER_CREDENTIALS_SCREEN, new String[0]);
        this.mAccountType = ((AccountTypeSpinnerItem) this.mAccountSpinnerItems.get(this.mScreen.getSelectedAccountTypePosition())).getKey();
        this.mScreen.switchToAccountDetailsForm();
        this.mScreen.showContinueButtonDisabled();
    }

    void onIAVDetailsFormSubmit() {
        if (!isValidRoutingNumber()) {
            this.mSnackBarWrapper.show((int) R.string.invalid_routing_number);
        } else if (isValidAccountNumber()) {
            this.mScreen.hideKeyboard();
            createAchAccountManually();
        } else {
            this.mSnackBarWrapper.show((int) R.string.invalid_account_number);
        }
    }

    void onChooseCdvClicked() {
        startAchVerification();
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_M_CDV_INITIATED, new String[0]);
    }

    void onCdvVerificationAmountSubmit() {
        if (areValidAmountsEntered()) {
            this.mScreen.hideKeyboard();
            Type type = this.mPaymentMethod.getType();
            if (type == Type.CREDIT_CARD || type == Type.DEBIT_CARD || type == Type.WORLDPAY_CARD) {
                completeCardCDVVerification(this.mCardAmount1, this.mCardAmount2);
                this.mMixpanelTracking.trackEvent(MixpanelTracking.VERIFY_CARD_TAPPED_SUBMIT, new String[0]);
                return;
            }
            completeBankCDVVerification(this.mBankAmount1, this.mBankAmount2);
            this.mMixpanelTracking.trackEvent(MixpanelTracking.VERIFY_BANK_ACCOUNT_TAPPED_SUBMIT, new String[0]);
            return;
        }
        this.mSnackBarWrapper.show((int) R.string.invalid_amounts);
    }

    void onCdvAmountSentContinueClicked() {
        this.mScreen.finishActivity();
    }

    void onPlaidAllDoneSubmit() {
        if (this.mScreen.getActivity() != null) {
            if (this.mParentSuccessRouter) {
                this.mScreen.getActivity().setResult(Constants.ACTIVITY_RESULT_PARENT_SUCCESS_ROUTER);
                this.mScreen.finishActivity();
                return;
            }
            this.mRouter.routeToBuyView();
            this.mScreen.finishActivity();
        }
    }

    void onRoutingDetailsHelpClicked() {
        this.mScreen.showRoutingDetails();
    }

    boolean onBackPressed() {
        if (this.mScreen.showingRoutingDetailsHint()) {
            this.mScreen.hideRoutingDetails();
            return true;
        } else if (this.mIsInVerificationMode || this.mScreen.showingPlaidAllDoneView()) {
            return false;
        } else {
            this.mScreen.showConfirmPlaidCancelScreen();
            return true;
        }
    }

    void onConfirmPlaidCancel() {
        if (this.mPaymentMethod == null || this.mPaymentMethod.getId() == null || !(this.mPaymentMethod.getCdvStatus() == null || this.mPaymentMethod.getCdvStatus() == CDVStatus.READY)) {
            this.mScreen.finishActivity();
        } else {
            new DeletePaymentMethodTask(this.mScreen.getActivity(), this.mPaymentMethod.getId(), new DeletePaymentMethodListener() {
                public void onFinally() {
                    IAVLoginPresenter.this.mSnackBarWrapper.show((int) R.string.payment_method_deleted);
                    IAVLoginPresenter.this.mScreen.finishActivity();
                }
            }).deletePaymentMethod();
        }
    }

    private void startAchVerification() {
        this.mScreen.showProgressVerifying();
        HashMap<String, Object> params = new HashMap();
        this.mStep = IAVStep.CDV_VERIFICATION;
        params.put(ApiConstants.VERIFICATION_METHOD, ApiConstants.CDV);
        this.mSubscription.add(this.mLoginManager.getClient().startAchVerificationRx(this.mPaymentMethod.getId(), params).first().observeOn(this.mScheduler).subscribe(IAVLoginPresenter$$Lambda$1.lambdaFactory$(this), IAVLoginPresenter$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$startAchVerification$0(IAVLoginPresenter this_, Pair pair) {
        Response<PaymentMethod> response = pair.first;
        Retrofit retrofit = pair.second;
        this_.mScreen.hideProgressDialog();
        if (response.isSuccessful()) {
            this_.mPaymentMethod = ((PaymentMethod) response.body()).getData();
            this_.mScreen.showCdvVerificationForm();
            this_.mBankAccountsUpdatedConnector.get().onNext(new ClassConsumableEvent());
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_M_CDV_SCREEN, new String[0]);
            return;
        }
        this_.mSnackBarWrapper.showError(response, retrofit);
    }

    static /* synthetic */ void lambda$startAchVerification$1(IAVLoginPresenter this_, Throwable t) {
        this_.mSnackBarWrapper.showFailure(t);
        this_.mScreen.hideProgressDialog();
    }

    private void createAchAccountManually() {
        this.mScreen.showCreateAchAccountProgress();
        HashMap<String, Object> params = new HashMap();
        params.put("type", ApiConstants.ACH_BANK_ACCOUNT);
        params.put(ApiConstants.CUSTOMER_NAME, this.mCustomerName);
        params.put(ApiConstants.ACCOUNT_NUMBER, this.mAccountNumber);
        params.put(ApiConstants.ROUTING_NUMBER, this.mRoutingNumber);
        if (this.mAccountType != null) {
            params.put("account_type", this.mAccountType.toLowerCase());
        }
        this.mSubscription.add(this.mLoginManager.getClient().createBankManuallyRx(params).first().observeOn(this.mScheduler).subscribe(IAVLoginPresenter$$Lambda$3.lambdaFactory$(this), IAVLoginPresenter$$Lambda$4.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$createAchAccountManually$2(IAVLoginPresenter this_, Pair pair) {
        Response<PaymentMethod> response = pair.first;
        Retrofit retrofit = pair.second;
        this_.mScreen.hideProgressDialog();
        this_.mScreen.showContinueButtonDisabled();
        if (response.isSuccessful()) {
            this_.mSnackBarWrapper.show((int) R.string.account_created);
            this_.mPaymentMethod = ((PaymentMethod) response.body()).getData();
            this_.startAchVerification();
            return;
        }
        this_.mSnackBarWrapper.showError(response, retrofit);
    }

    static /* synthetic */ void lambda$createAchAccountManually$3(IAVLoginPresenter this_, Throwable t) {
        this_.mScreen.showContinueButtonDisabled();
        this_.mScreen.hideProgressDialog();
        this_.mSnackBarWrapper.showFailure(t);
    }

    private void completeCardCDVVerification(String amount1, String amount2) {
        this.mScreen.showProgressVerifying();
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.AMOUNT_1, amount1);
        params.put(ApiConstants.AMOUNT_2, amount2);
        params.put(ApiConstants.VERIFICATION_METHOD, ApiConstants.CDV);
        this.mSubscription.add(this.mLoginManager.getClient().completeCardCDVRx(this.mPaymentMethod.getId(), params).first().observeOn(this.mScheduler).subscribe(IAVLoginPresenter$$Lambda$5.lambdaFactory$(this), IAVLoginPresenter$$Lambda$6.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$completeCardCDVVerification$4(IAVLoginPresenter this_, Pair pair) {
        Response<Void> response = pair.first;
        Retrofit retrofit = pair.second;
        this_.mScreen.hideProgressDialog();
        if (response.isSuccessful()) {
            this_.mScreen.switchToAllDoneView(String.format(this_.mContext.getString(R.string.all_set_card_details), new Object[]{this_.mPaymentMethod.getName()}));
            this_.mBankAccountsUpdatedConnector.get().onNext(new ClassConsumableEvent());
            return;
        }
        this_.mSnackBarWrapper.showError(response, retrofit);
    }

    static /* synthetic */ void lambda$completeCardCDVVerification$5(IAVLoginPresenter this_, Throwable t) {
        this_.mScreen.hideProgressDialog();
        this_.mSnackBarWrapper.showFailure(t);
    }

    private void completeBankCDVVerification(String amount1, String amount2) {
        this.mScreen.showProgressVerifying();
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.AMOUNT_1, amount1);
        params.put(ApiConstants.AMOUNT_2, amount2);
        this.mSubscription.add(this.mLoginManager.getClient().completeCDVVerificationRx(this.mPaymentMethod.getId(), params).first().observeOn(this.mScheduler).subscribe(IAVLoginPresenter$$Lambda$7.lambdaFactory$(this), IAVLoginPresenter$$Lambda$8.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$completeBankCDVVerification$6(IAVLoginPresenter this_, Pair pair) {
        Response<PaymentMethod> response = pair.first;
        Retrofit retrofit = pair.second;
        this_.mScreen.hideProgressDialog();
        if (response.isSuccessful()) {
            this_.mPaymentMethod = ((PaymentMethod) response.body()).getData();
            this_.mScreen.switchToAllDoneView(String.format(this_.mContext.getString(R.string.all_set_details), new Object[]{this_.mPaymentMethod.getName()}));
            this_.mBankAccountsUpdatedConnector.get().onNext(new ClassConsumableEvent());
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_M_SUCCESSFUL, new String[0]);
            return;
        }
        this_.mSnackBarWrapper.showError(response, retrofit);
    }

    static /* synthetic */ void lambda$completeBankCDVVerification$7(IAVLoginPresenter this_, Throwable t) {
        this_.mScreen.hideProgressDialog();
        this_.mSnackBarWrapper.showFailure(t);
    }

    private boolean isValidName() {
        return (this.mCustomerName == null || TextUtils.isEmpty(this.mCustomerName.trim())) ? false : true;
    }

    private boolean isValidRoutingNumber() {
        return !TextUtils.isEmpty(this.mRoutingNumber) && this.mRoutingNumber.length() == 9;
    }

    private boolean isValidAccountNumber() {
        return !TextUtils.isEmpty(this.mAccountNumber) && this.mAccountNumber.length() >= 8;
    }

    private boolean areValidAmountsEntered() {
        Type type = this.mPaymentMethod.getType();
        if (type == Type.CREDIT_CARD || type == Type.DEBIT_CARD || type == Type.WORLDPAY_CARD) {
            if (TextUtils.isEmpty(this.mCardAmount1) || TextUtils.isEmpty(this.mCardAmount2)) {
                return false;
            }
            return true;
        } else if (TextUtils.isEmpty(this.mBankAmount1) || TextUtils.isEmpty(this.mBankAmount2)) {
            return false;
        } else {
            return true;
        }
    }
}
