package com.coinbase.android.signin;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.ActivityProvider;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.BaseActivityModule;
import com.coinbase.android.BottomNavActivityModule;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.Constants;
import com.coinbase.android.MainActivity;
import com.coinbase.android.MainActivitySubcomponent;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.ScreenProtector;
import com.coinbase.android.databinding.ActivityIntroBinding;
import com.coinbase.android.googleappinvite.GoogleAppInviteUtils;
import com.coinbase.android.signin.AuthManager.ViewType;
import com.coinbase.android.ui.BaseViewModule;
import com.coinbase.android.ui.BaseViewProvider;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.MaterialProgressBar;
import com.coinbase.android.utils.ActivityPermissionCheckUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.LoginManager.TokenAndUserResponse;
import com.coinbase.api.internal.CoinbaseInternal;
import com.coinbase.v2.models.user.User;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;

@ActivityScope
public class IntroActivity extends AppCompatActivity implements ActivityProvider, MainActivitySubcomponentProvider, BaseViewProvider {
    @Inject
    AuthIntentManager authIntentManager;
    @Inject
    AuthManager authManager;
    @Inject
    AuthManager mAuthManager;
    ActivityIntroBinding mBinding;
    @Inject
    LaunchMessageModalRouter mLaunchMessageModalRouter;
    @Inject
    LoginManager mLoginManager;
    private MainActivitySubcomponent mMainActivitySubcomponent;
    @MainScheduler
    @Inject
    Scheduler mMainScheduler;
    @Inject
    ScreenProtector mScreenProtector;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mMainActivitySubcomponent = ((ComponentProvider) getApplicationContext()).applicationComponent().mainActivitySubcomponent(new BaseActivityModule(this), new BaseViewModule(this), new BottomNavActivityModule(IntroActivity$$Lambda$1.lambdaFactory$(), IntroActivity$$Lambda$2.lambdaFactory$()));
        this.mMainActivitySubcomponent.inject(this);
        this.mScreenProtector.protect();
        this.mBinding = (ActivityIntroBinding) DataBindingUtil.setContentView(this, R.layout.activity_intro);
        CoinbaseInternal.getInstance().init(this, this.mLoginManager.getAccessToken());
        this.mBinding.tvLoginIntroSignin.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent signinIntent = new Intent(IntroActivity.this, RegistrationControllerActivity.class);
                IntroActivity.this.mAuthManager.setAuthScreen(ViewType.LOGIN.ordinal());
                IntroActivity.this.startActivityForResult(signinIntent, ActivityPermissionCheckUtils.ACTIVITY_PERMISSION_REQUEST_CODE);
                MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_SIGN_IN_BUTTON_CLICK, new String[0]);
            }
        });
        this.mBinding.btnLoginIntroSignupText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent signupIntent = new Intent(IntroActivity.this, RegistrationControllerActivity.class);
                IntroActivity.this.mAuthManager.setAuthScreen(ViewType.SIGN_UP.ordinal());
                IntroActivity.this.startActivityForResult(signupIntent, ActivityPermissionCheckUtils.ACTIVITY_PERMISSION_REQUEST_CODE);
                MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_SIGN_UP_BUTTON_CLICK, new String[0]);
            }
        });
        showProgress(false);
    }

    public View getBaseView() {
        return this.mBinding.rlChartContainer;
    }

    public RelativeLayout getBlockingOverlayView() {
        return null;
    }

    public AppCompatActivity getActivity() {
        return this;
    }

    private void showProgress(boolean show) {
        int i;
        int i2 = 4;
        MaterialProgressBar materialProgressBar = this.mBinding.progress;
        if (show) {
            i = 0;
        } else {
            i = 4;
        }
        materialProgressBar.setVisibility(i);
        TextView textView = this.mBinding.tvPleaseWait;
        if (show) {
            i = 0;
        } else {
            i = 4;
        }
        textView.setVisibility(i);
        RelativeLayout relativeLayout = this.mBinding.rlContainer;
        if (!show) {
            i2 = 0;
        }
        relativeLayout.setVisibility(i2);
    }

    protected void onResume() {
        super.onResume();
        final boolean showJumioFromWeb = showJumioFromWeb(getIntent().getData());
        if ((getIntent().getFlags() & 4194304) != 0 && !showJumioFromWeb) {
            finish();
        } else if (this.mLoginManager.isSignedIn()) {
            showProgress(true);
            this.mLoginManager.checkTokenValidity(new TokenAndUserResponse() {
                public void onResponseNeededRefresh(boolean shouldLogOut) {
                    if (shouldLogOut) {
                        IntroActivity.this.showProgress(false);
                        Utils.showMessage(IntroActivity.this, (int) R.string.session_expired, 1);
                        IntroActivity.this.userNotSignedIn(showJumioFromWeb);
                        return;
                    }
                    if (showJumioFromWeb) {
                        PreferenceManager.getDefaultSharedPreferences(IntroActivity.this).edit().putBoolean(Constants.KEY_SHOW_JUMIO_FLOW, true).apply();
                    }
                    IntroActivity.this.proceed();
                }

                public void onResponse(Response<User> user) {
                    if (showJumioFromWeb) {
                        PreferenceManager.getDefaultSharedPreferences(IntroActivity.this).edit().putBoolean(Constants.KEY_SHOW_JUMIO_FLOW, true).apply();
                    }
                    IntroActivity.this.proceedWithUser(user);
                }
            });
        } else {
            userNotSignedIn(showJumioFromWeb);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public MainActivitySubcomponent mainActivitySubcomponent() {
        return this.mMainActivitySubcomponent;
    }

    private void userNotSignedIn(boolean showJumioFromWeb) {
        checkConfigForIdentityVerficiation(showJumioFromWeb);
    }

    private boolean showJumioFromWeb(Uri uri) {
        return (uri == null || uri.getScheme() == null || !uri.getScheme().equalsIgnoreCase("coinbase-identity-verification")) ? false : true;
    }

    private void checkConfigForIdentityVerficiation(boolean showJumioFromWeb) {
        this.mLaunchMessageModalRouter.route().onBackpressureLatest().subscribe(IntroActivity$$Lambda$3.lambdaFactory$(this, showJumioFromWeb), IntroActivity$$Lambda$4.lambdaFactory$(this, showJumioFromWeb));
    }

    static /* synthetic */ void lambda$checkConfigForIdentityVerficiation$2(IntroActivity this_, boolean showJumioFromWeb, Action0 action) {
        if (action != null) {
            action.call();
        } else {
            this_.showJumioFromWebMessage(showJumioFromWeb);
        }
    }

    private void showJumioFromWebMessage(boolean showJumioFromWeb) {
        if (showJumioFromWeb) {
            Utils.showMessage((Context) this, (int) R.string.login_to_verify_identity, 1);
        }
    }

    private void proceed() {
        showProgress(true);
        GoogleAppInviteUtils.setReferrerId(getApplicationContext(), getIntent());
        proceedWithUser(null);
    }

    private void proceedWithUser(Response<User> user) {
        Observable<Pair<ViewType, String>> observable;
        showProgress(true);
        GoogleAppInviteUtils.setReferrerId(getApplicationContext(), getIntent());
        if (user == null) {
            observable = this.authManager.getAuthTypeForUser();
        } else {
            observable = this.authManager.getAuthTypeForUserResponse(user);
        }
        this.mSubscription.add(observable.map(IntroActivity$$Lambda$5.lambdaFactory$(this)).observeOn(this.mMainScheduler).onBackpressureLatest().subscribe(IntroActivity$$Lambda$6.lambdaFactory$(this), IntroActivity$$Lambda$7.lambdaFactory$(this)));
    }

    private void onAuthComplete(Intent intent) {
        if (intent == null) {
            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.setFlags(67108864);
            startActivity(mainIntent);
            overridePendingTransition(0, 0);
            finish();
            return;
        }
        startActivityForResult(intent, ActivityPermissionCheckUtils.ACTIVITY_PERMISSION_REQUEST_CODE);
    }
}
