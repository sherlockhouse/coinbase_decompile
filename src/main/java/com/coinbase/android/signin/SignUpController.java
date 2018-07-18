package com.coinbase.android.signin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Pair;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import com.coinbase.android.Analytics;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ActivitySignupBinding;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.gdpr.OnboardingRouter;
import com.coinbase.android.gdpr.OnboardingRouter.OnboardingStep;
import com.coinbase.android.gdpr.OnboardingUpdatedConnector;
import com.coinbase.android.signin.AuthManager.ViewType;
import com.coinbase.android.signin.EmailVerifiedConnector.Type;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.MapToMainLooperFunc;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.v2.models.user.Data;
import com.coinbase.v2.models.user.OnboardingItem;
import com.coinbase.v2.models.user.User;
import com.google.android.gms.auth.api.credentials.Credential;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class SignUpController extends ActionBarController implements ValidationListener {
    static final String EMAIL = "SIGN_UP_EMAIL";
    static final String FIRST_NAME = "FIRST_NAME";
    static final String LAST_NAME = "LAST_NAME";
    static final String PASSWORD = "SIGN_UP_PASSWORD";
    @Inject
    protected Analytics mAnalytics;
    @Inject
    protected AuthManager mAuthManager;
    @Inject
    protected AuthRouter mAuthRouter;
    private ActivitySignupBinding mBinding;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    @Inject
    protected CredentialsApiRxWrapper mCredentialsApiRxWrapper;
    @Email(message = "Please enter a valid email", order = 4)
    @Required(message = "Email is a required field", order = 3)
    EditText mEmailInput;
    @Inject
    protected EmailVerifiedConnector mEmailVerifiedConnector;
    @Required(message = "Please enter your first name", order = 1)
    EditText mFirstNameInput;
    @Required(message = "Please enter your last name", order = 2)
    EditText mLastNameInput;
    private final Logger mLogger = LoggerFactory.getLogger(SignUpController.class);
    @Inject
    protected LoginManager mLoginManager;
    @MainScheduler
    @Inject
    Scheduler mMainScheduler;
    @Inject
    OnboardingRouter mOnboardingRouter;
    @Inject
    OnboardingUpdatedConnector mOnboardingUpdatedConnector;
    @Password(message = "Password is a required field", order = 5)
    EditText mPasswordInput;
    @Inject
    SignInRouter mSignInRouter;
    Validator mValidator;

    private void initializeSaripaarBindings() {
        this.mEmailInput = this.mBinding.etSignupEmailField;
        this.mFirstNameInput = this.mBinding.etSignupFirstNameField;
        this.mLastNameInput = this.mBinding.etSignupLastNameField;
        this.mPasswordInput = this.mBinding.etSignupPasswordField;
    }

    public SignUpController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ActivitySignupBinding) DataBindingUtil.inflate(inflater, R.layout.activity_signup, container, false);
        initializeSaripaarBindings();
        onAttachToolbar(null);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addSignUpControllerSubcomponent(new SignUpControllerSubcomponentModule(this)).inject(this);
        this.mCompositeSubscription.add(this.mEmailVerifiedConnector.get().observeOn(this.mMainScheduler).filter(SignUpController$$Lambda$1.lambdaFactory$()).map(SignUpController$$Lambda$2.lambdaFactory$()).subscribe(SignUpController$$Lambda$3.lambdaFactory$(this), SignUpController$$Lambda$4.lambdaFactory$(this)));
        this.mValidator = new Validator(this);
        this.mValidator.setValidationListener(this);
        this.mBinding.btnSignupSubmit.setOnClickListener(SignUpController$$Lambda$5.lambdaFactory$(this));
        this.mPasswordInput.setTypeface(Typeface.DEFAULT);
        this.mPasswordInput.setTransformationMethod(new PasswordTransformationMethod());
        this.mBinding.checkboxCertifyAgreeAcknowledge.setOnCheckedChangeListener(SignUpController$$Lambda$6.lambdaFactory$(this));
        this.mBinding.tvCertifyAgreeAcknowledge.setText(Html.fromHtml(getResources().getString(R.string.sign_up_certify_agree_acknowledge)));
        this.mBinding.tvCertifyAgreeAcknowledge.setMovementMethod(LinkMovementMethod.getInstance());
        this.mPasswordInput.setOnKeyListener(SignUpController$$Lambda$7.lambdaFactory$(this));
        this.mEmailInput.setOnClickListener(SignUpController$$Lambda$8.lambdaFactory$(this));
        this.mAuthManager.setAuthScreen(ViewType.SIGN_UP.ordinal());
        try {
            this.mAuthRouter.loadAuthFromBundle(getArgs());
            showSignUpForm();
        } catch (SecurityException e) {
            Log.e("Coinbase", "Exception", e);
            getActivity().finish();
        }
        return this.mBinding.getRoot();
    }

    static /* synthetic */ Boolean lambda$onCreateView$0(ClassConsumableEvent event) {
        return Boolean.valueOf(!event.consumeIfNotConsumed(SignUpController.class));
    }

    static /* synthetic */ void lambda$onCreateView$5(SignUpController this_, CompoundButton buttonView, boolean isChecked) {
        this_.mBinding.btnSignupSubmit.setEnabled(isChecked);
        this_.hideSoftKeyboard();
    }

    static /* synthetic */ boolean lambda$onCreateView$6(SignUpController this_, View v, int keyCode, KeyEvent event) {
        if (event.getAction() != 0 || keyCode != 66) {
            return false;
        }
        this_.hideSoftKeyboard();
        return true;
    }

    static /* synthetic */ void lambda$onCreateView$9(SignUpController this_, View v) {
        if (this_.mCredentialsApiRxWrapper.shouldConnect()) {
            ProgressDialog progressDialog = ProgressDialog.show(this_.getActivity(), null, this_.getApplicationContext().getString(R.string.please_wait));
            this_.mCompositeSubscription.add(this_.mCredentialsApiRxWrapper.connect().onBackpressureLatest().flatMap(new MapToMainLooperFunc()).subscribe(SignUpController$$Lambda$12.lambdaFactory$(this_, progressDialog), SignUpController$$Lambda$13.lambdaFactory$(this_, progressDialog)));
        }
    }

    static /* synthetic */ void lambda$null$7(SignUpController this_, ProgressDialog progressDialog, Credential credential) {
        Utils.dismissDialog(progressDialog);
        if (credential != null) {
            this_.onCredentialRetrieved(credential);
        }
    }

    static /* synthetic */ void lambda$null$8(SignUpController this_, ProgressDialog progressDialog, Throwable t) {
        Utils.dismissDialog(progressDialog);
        this_.mLogger.error("Couldn't get credentials", t);
    }

    protected void onShow() {
        super.onShow();
        Utils.postShowKeyboardFrom(getActivity(), this.mBinding.etSignupEmailField);
        Map<String, Object> onboardingResults = (Map) this.mOnboardingUpdatedConnector.getResults().getValue();
        if (onboardingResults != null && !onboardingResults.isEmpty()) {
            startSignup();
        }
    }

    protected void onHide() {
        super.onHide();
        this.mCompositeSubscription.clear();
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString(EMAIL, this.mEmailInput.getText().toString()).putString(PASSWORD, this.mPasswordInput.getText().toString()).putString(FIRST_NAME, this.mFirstNameInput.getText().toString()).putString(LAST_NAME, this.mLastNameInput.getText().toString()).apply();
    }

    public void onValidationSucceeded() {
        String firstName = this.mFirstNameInput.getText().toString();
        String lastName = this.mLastNameInput.getText().toString();
        String password = this.mPasswordInput.getText().toString();
        String email = this.mEmailInput.getText().toString();
        if (TextUtils.isEmpty(firstName)) {
            Utils.showMessage(getApplicationContext(), (int) R.string.signup_invalid_first_name, 1);
        } else if (TextUtils.isEmpty(lastName)) {
            Utils.showMessage(getApplicationContext(), (int) R.string.signup_invalid_last_name, 1);
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).find()) {
            Utils.showMessage(getApplicationContext(), (int) R.string.signup_invalid_email, 1);
        } else if (!Pattern.compile("[a-z]*[0-9+][a-z]*").matcher(password).find()) {
            Utils.showMessage(getApplicationContext(), (int) R.string.signup_invalid_password, 1);
        } else if (Utils.isConnectedOrConnecting(getApplicationContext())) {
            startSignup();
        } else {
            Utils.showMessage(getApplicationContext(), (int) R.string.no_internet, 1);
        }
    }

    private void onSignUpPressed() {
        this.mValidator.validate();
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_CREATE_ACCOUNT_BUTTON_CLICK, new String[0]);
    }

    private void startSignup() {
        showProgress(true);
        saveCurrentViewState(getArgs());
        String firstName = this.mFirstNameInput.getText().toString();
        String lastName = this.mLastNameInput.getText().toString();
        String password = this.mPasswordInput.getText().toString();
        String email = this.mEmailInput.getText().toString();
        String referral = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("referral", null);
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.FIRST_NAME, firstName);
        params.put(ApiConstants.LAST_NAME, lastName);
        params.put("email", email);
        params.put("password", password);
        params.put(ApiConstants.ACCEPT_USER_AGREEMENT, Boolean.valueOf(true));
        if (referral != null) {
            params.put("referral", referral);
        }
        params.put(ApiConstants.APPLICATION_CLIENT_ID, this.mLoginManager.getClientId());
        Map<String, Object> onboardingResults = (Map) this.mOnboardingUpdatedConnector.getResults().getValue();
        if (!(onboardingResults == null || onboardingResults.isEmpty())) {
            params.putAll(onboardingResults);
        }
        this.mCompositeSubscription.add(this.mLoginManager.getClient().createUserRx(params).observeOn(this.mMainScheduler).subscribe(SignUpController$$Lambda$9.lambdaFactory$(this, email), SignUpController$$Lambda$10.lambdaFactory$(this)));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void lambda$startSignup$10(SignUpController this_, String email, Pair pair) {
        Response<User> response = pair.first;
        if (response.isSuccessful()) {
            Data user = ((User) response.body()).getData();
            this_.mAuthManager.cacheEmailRestrictionFromSignup(email);
            this_.mLoginManager.signin(user.getOauth());
            this_.mAnalytics.trackAttributionEvent("signed_up");
            ActionBarController nextController = this_.mAuthRouter.getNextController(ViewType.EMAIL_VERIFICATION, false);
            nextController.getArgs().putString(EmailVerifyPresenter.EMAIL, email);
            this_.pushModalController(nextController);
            return;
        }
        android.support.v4.util.Pair<String, String> errorIdAndMessage = Utils.getErrorIdAndMessage(response);
        if (errorIdAndMessage.first != null) {
            boolean z;
            String str = (String) errorIdAndMessage.first;
            switch (str.hashCode()) {
                case 454059377:
                    if (str.equals(ApiConstants.NEEDS_PRIVACY_NOTICE)) {
                        z = false;
                        break;
                    }
                default:
                    z = true;
                    break;
            }
            switch (z) {
                case false:
                    OnboardingItem privacyNotice = new OnboardingItem();
                    privacyNotice.setStep(OnboardingStep.PRIVACY_NOTICE.getStep());
                    privacyNotice.setRequired(Boolean.valueOf(true));
                    OnboardingItem emailPreference = new OnboardingItem();
                    emailPreference.setStep(OnboardingStep.EMAIL_PREFERENCE.getStep());
                    emailPreference.setRequired(Boolean.valueOf(true));
                    this_.mOnboardingRouter.routeToNext(Arrays.asList(new OnboardingItem[]{privacyNotice, emailPreference}));
                    return;
            }
        }
        Utils.showMessage(this_.getApplicationContext(), (String) errorIdAndMessage.second, 1);
        this_.showSignUpForm();
    }

    static /* synthetic */ void lambda$startSignup$11(SignUpController this_, Throwable t) {
        Utils.showMessage(this_.getApplicationContext(), t, 1);
        this_.showSignUpForm();
    }

    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        String message = failedRule.getFailureMessage();
        if (failedView instanceof EditText) {
            failedView.requestFocus();
            ((EditText) failedView).setError(message);
            return;
        }
        Utils.showMessage(getApplicationContext(), message, 1);
    }

    private void showSignUpForm() {
        showProgress(false);
        this.mBinding.llSignupForm.setVisibility(0);
    }

    public void showProgress(boolean shouldShow) {
        int visibility = 4;
        if (shouldShow) {
            Utils.hideKeyboardFrom(getActivity(), this.mBinding.getRoot());
            this.mBinding.llSignupForm.setVisibility(4);
        }
        if (shouldShow) {
            visibility = 0;
        }
        this.mBinding.progressDeviceConfirmation.setVisibility(visibility);
        this.mBinding.tvDeviceConfirmation.setVisibility(visibility);
    }

    protected boolean onBackPressed() {
        if (this.mBinding.llSignupForm.getVisibility() == 0) {
            return super.onBackPressed();
        }
        this.mSignInRouter.cancel(SignUpController$$Lambda$11.lambdaFactory$(), R.string.abandon_signup_message);
        return true;
    }

    static /* synthetic */ void lambda$onBackPressed$12() {
    }

    private void onEmailVerifiedEvent(Type event) {
        switch (event) {
            case FAILED:
                onEmailVerifiedFailed();
                return;
            default:
                onEmailChangeRequested();
                return;
        }
    }

    private void onEmailVerifiedFailed() {
        if (!this.mLoginManager.isSignedIn()) {
            this.mLoginManager.signout();
            getActivity().finish();
        }
    }

    private void onEmailChangeRequested() {
        if (!this.mLoginManager.isSignedIn()) {
            this.mLoginManager.signout();
        }
        this.mEmailInput.setText("");
        this.mPasswordInput.setText("");
        showSignUpForm();
    }

    protected void onCredentialRetrieved(Credential credential) {
        if (credential.getAccountType() == null) {
            this.mEmailInput.setText(credential.getId());
            String name = credential.getName();
            String[] nameParts = null;
            if (name != null) {
                nameParts = name.split(" ");
            }
            if (nameParts != null) {
                this.mFirstNameInput.setText(nameParts[0]);
                if (nameParts.length > 1) {
                    this.mLastNameInput.setText(nameParts[1]);
                }
            }
        }
    }

    public void onActivityResult(int aRequestCode, int aResultCode, Intent aData) {
        super.onActivityResult(aRequestCode, aResultCode, aData);
        if (aRequestCode == 100 && aResultCode == -1) {
            onCredentialRetrieved((Credential) aData.getParcelableExtra("com.google.android.gms.credentials.Credential"));
        }
    }

    private void saveCurrentViewState(Bundle bundle) {
        bundle.putString(EMAIL, this.mEmailInput.getText().toString());
        bundle.putString(PASSWORD, this.mPasswordInput.getText().toString());
        bundle.putString(FIRST_NAME, this.mFirstNameInput.getText().toString());
        bundle.putString(LAST_NAME, this.mLastNameInput.getText().toString());
    }
}
