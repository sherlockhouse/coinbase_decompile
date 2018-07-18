package com.coinbase.android.signin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.Log;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ActivityLoginBinding;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.signin.AuthManager.ViewType;
import com.coinbase.android.signin.EmailVerifiedConnector.Type;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.MapToMainLooperFunc;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.google.android.gms.auth.api.credentials.Credential;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class LoginController extends ActionBarController implements ValidationListener {
    public static final String AUTH_CODE = "AUTH_CODE";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String REFERRER_ID = "referrerId";
    @Inject
    AuthManager mAuthManager;
    @Inject
    AuthRouter mAuthRouter;
    private ActivityLoginBinding mBinding;
    @Inject
    CredentialsApiRxWrapper mCredentialsApiRxWrapper;
    @Inject
    DeviceVerifyConnector mDeviceVerifyConnector;
    @Inject
    EmailVerifiedConnector mEmailVerifiedConnector;
    @Email(message = "Please enter a valid email", order = 2)
    @Required(message = "Email is a required field", order = 1)
    EditText mEmailView;
    private final Logger mLogger = LoggerFactory.getLogger(LoginController.class);
    @Inject
    LoginAuthManager mLoginAuthManager;
    @Inject
    LoginManager mLoginManager;
    @MainScheduler
    @Inject
    Scheduler mMainScheduler;
    @Password(message = "Password is a required field", order = 3)
    EditText mPasswordView;
    private String mReferrerId;
    @Inject
    SignInRouter mSignInRouter;
    protected final CompositeSubscription mSubscription = new CompositeSubscription();
    @Inject
    TwoFactorRouter mTwoFactorRouter;
    private Validator mValidator;

    private void initializeSaripaarBindings() {
        this.mEmailView = this.mBinding.etLoginEmailField;
        this.mPasswordView = this.mBinding.etLoginPasswordField;
    }

    public LoginController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ActivityLoginBinding) DataBindingUtil.inflate(inflater, R.layout.activity_login, container, false);
        onAttachToolbar(null);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addLoginControllerSubcomponent(new LoginControllerSubcomponentModule(this)).inject(this);
        this.mSubscription.add(this.mEmailVerifiedConnector.get().observeOn(this.mMainScheduler).filter(LoginController$$Lambda$1.lambdaFactory$()).map(LoginController$$Lambda$2.lambdaFactory$()).subscribe(LoginController$$Lambda$3.lambdaFactory$(this)));
        this.mSubscription.add(this.mDeviceVerifyConnector.getFailed().observeOn(this.mMainScheduler).filter(LoginController$$Lambda$4.lambdaFactory$()).map(LoginController$$Lambda$5.lambdaFactory$()).subscribe(LoginController$$Lambda$6.lambdaFactory$(this)));
        initializeSaripaarBindings();
        ((AppCompatActivity) getActivity()).setSupportProgressBarIndeterminateVisibility(false);
        this.mValidator = new Validator(this);
        this.mValidator.setValidationListener(this);
        this.mEmailView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (LoginController.this.mCredentialsApiRxWrapper.shouldConnect()) {
                    ProgressDialog progressDialog = ProgressDialog.show(LoginController.this.getActivity(), null, LoginController.this.getApplicationContext().getString(R.string.please_wait));
                    LoginController.this.mSubscription.add(LoginController.this.mCredentialsApiRxWrapper.connect().onBackpressureLatest().flatMap(new MapToMainLooperFunc()).subscribe(LoginController$1$$Lambda$1.lambdaFactory$(this, progressDialog), LoginController$1$$Lambda$2.lambdaFactory$(progressDialog)));
                }
            }

            static /* synthetic */ void lambda$onClick$0(AnonymousClass1 this_, ProgressDialog progressDialog, Credential credential) {
                Utils.dismissDialog(progressDialog);
                if (credential != null) {
                    LoginController.this.onCredentialRetrieved(credential);
                }
            }
        });
        this.mBinding.btnLoginSubmit.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LoginController.this.onLoginPressed();
            }
        });
        this.mPasswordView.setTypeface(Typeface.DEFAULT);
        this.mPasswordView.setTransformationMethod(new PasswordTransformationMethod());
        this.mPasswordView.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() != 0 || keyCode != 66) {
                    return false;
                }
                LoginController.this.onLoginPressed();
                return true;
            }
        });
        this.mBinding.tvLoginForgotPassword.setOnClickListener(LoginController$$Lambda$7.lambdaFactory$(this));
        this.mBinding.tvLoginPrivacyPolicy.setOnClickListener(LoginController$$Lambda$8.lambdaFactory$(this));
        this.mAuthManager.setAuthScreen(ViewType.LOGIN.ordinal());
        this.mAuthRouter.loadAuthFromBundle(getArgs());
        showLoginForm();
        return this.mBinding.getRoot();
    }

    static /* synthetic */ Boolean lambda$onCreateView$0(ClassConsumableEvent event) {
        return Boolean.valueOf(!event.consumeIfNotConsumed(LoginController.class));
    }

    static /* synthetic */ void lambda$onCreateView$2(LoginController this_, Type event) {
        if (event == Type.FAILED) {
            this_.onEmailVerifiedFailed();
        }
    }

    static /* synthetic */ Boolean lambda$onCreateView$3(ClassConsumableEvent event) {
        return Boolean.valueOf(!event.consumeIfNotConsumed(LoginController.class));
    }

    private void goToWebsite(String url) {
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    protected void onShow() {
        super.onShow();
        Utils.postShowKeyboardFrom(getActivity(), this.mBinding.etLoginEmailField);
    }

    protected void onHide() {
        super.onHide();
        this.mSubscription.clear();
    }

    protected void onRestoreViewState(View view, Bundle savedViewState) {
        super.onRestoreViewState(view, savedViewState);
        if (savedViewState.getString("email") != null) {
            this.mEmailView.setText(savedViewState.getString("email"));
            this.mPasswordView.setText(savedViewState.getString("password"));
            this.mReferrerId = savedViewState.getString(REFERRER_ID);
        }
    }

    protected void onSaveViewState(View view, Bundle outState) {
        super.onSaveViewState(view, outState);
        saveCurrentViewState(outState);
    }

    private void saveCurrentViewState(Bundle bundle) {
        bundle.putString("email", this.mEmailView.getText().toString());
        bundle.putString("password", this.mPasswordView.getText().toString());
        bundle.putString(REFERRER_ID, this.mReferrerId);
    }

    public void onValidationSucceeded() {
        login();
    }

    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        String message = failedRule.getFailureMessage();
        if (failedView instanceof EditText) {
            failedView.requestFocus();
            ((EditText) failedView).setError(message);
            return;
        }
        Utils.showMessage(getApplicationContext(), message, 0);
    }

    private void onLoginPressed() {
        this.mValidator.validate();
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_LOG_IN_BUTTON_CLICK, new String[0]);
    }

    public void onCredentialsIncorrect() {
        showLoginForm();
        this.mPasswordView.requestFocus();
        this.mPasswordView.setError(getApplicationContext().getString(R.string.login_incorrect_password));
    }

    public void onRateLimited() {
        showLoginForm();
        this.mPasswordView.setError(getApplicationContext().getString(R.string.login_rate_limited));
    }

    private void showLoginForm() {
        showProgress(false);
        this.mBinding.llLoginForm.setVisibility(0);
    }

    public void showProgress(boolean shouldShow) {
        int visibility = 0;
        if (shouldShow) {
            Utils.hideKeyboardFrom(getActivity(), this.mBinding.getRoot());
            this.mBinding.llLoginForm.setVisibility(4);
        } else {
            this.mBinding.llLoginForm.setVisibility(0);
        }
        if (!shouldShow) {
            visibility = 4;
        }
        this.mBinding.progressDeviceConfirmation.setVisibility(visibility);
        this.mBinding.tvLoggingIn.setVisibility(visibility);
    }

    protected void onCredentialRetrieved(Credential credential) {
        if (credential.getAccountType() == null) {
            this.mEmailView.setText(credential.getId());
            this.mPasswordView.setText(credential.getPassword());
        }
    }

    public boolean onBackPressed() {
        if (this.mBinding.llLoginForm.getVisibility() == 0) {
            return super.onBackPressed();
        }
        this.mSignInRouter.cancel(LoginController$$Lambda$9.lambdaFactory$(), R.string.abandon_login_message);
        return true;
    }

    static /* synthetic */ void lambda$onBackPressed$8() {
    }

    private void login() {
        if (Utils.isConnectedOrConnecting(getApplicationContext())) {
            saveCurrentViewState(getArgs());
            showProgress(true);
            this.mReferrerId = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("referral", null);
            this.mLoginAuthManager.getAuthTypeForLogin(this.mEmailView.getText().toString(), this.mPasswordView.getText().toString(), "", this.mReferrerId).observeOn(this.mMainScheduler).onBackpressureLatest().flatMap(LoginController$$Lambda$10.lambdaFactory$(this)).subscribe(LoginController$$Lambda$11.lambdaFactory$(this), LoginController$$Lambda$12.lambdaFactory$(this));
            return;
        }
        Utils.showMessage(getApplicationContext(), (int) R.string.no_internet, 1);
    }

    static /* synthetic */ Observable lambda$login$9(LoginController this_, LoginAuthResult loginAuthResult) {
        if (this_.mTwoFactorRouter.shouldRouteToNext(loginAuthResult.getViewType())) {
            return this_.mTwoFactorRouter.routeToNext(loginAuthResult);
        }
        this_.onAuthErrorTypeReceived(loginAuthResult.getViewType());
        return Observable.never();
    }

    static /* synthetic */ void lambda$login$11(LoginController this_, Throwable t) {
        Utils.showMessage(this_.getApplicationContext(), t, 1);
        this_.onAuthErrorTypeReceived(ViewType.NONE);
    }

    private void onEmailVerifiedFailed() {
        Utils.showMessage(getApplicationContext(), getApplicationContext().getString(R.string.please_verify_email_address_and_login), 1);
        getActivity().finish();
    }

    private void onDeviceVerifyFailed(Throwable t) {
        Utils.showMessage(getApplicationContext(), t, 1);
        showLoginForm();
    }

    public void onActivityResult(int aRequestCode, int aResultCode, Intent aData) {
        super.onActivityResult(aRequestCode, aResultCode, aData);
        if (aRequestCode != 100) {
            showLoginForm();
        } else if (aResultCode == -1) {
            onCredentialRetrieved((Credential) aData.getParcelableExtra("com.google.android.gms.credentials.Credential"));
        } else {
            Log.e(getActivity().getLocalClassName(), "Credential Read: NOT OK");
        }
    }

    private void onAuthErrorTypeReceived(ViewType viewType) {
        showProgress(false);
        switch (viewType) {
            case LOGIN:
                this.mLogger.error("Got login auth type in login controller, shouldn't happen");
                return;
            case INCORRECT_CREDENTIALS:
                onCredentialsIncorrect();
                return;
            case RATE_LIMITED:
                onRateLimited();
                return;
            case TWO_FACTOR:
                this.mLogger.error("Got two factor auth type in login controller, shouldn't happen");
                return;
            case DEVICE_VERIFICATION:
                this.mLogger.error("Got device verification auth type in login controller, shouldn't happen");
                return;
            case EMAIL_VERIFICATION:
                Utils.showMessage(getApplicationContext(), (int) R.string.please_verify_email_address_and_login, 1);
                showLoginForm();
                return;
            default:
                showLoginForm();
                return;
        }
    }
}
