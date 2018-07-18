package com.coinbase.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.android.pin.PINManager;
import com.coinbase.android.signin.IntroActivity;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.LoginManager.TokenAndUserResponse;
import com.coinbase.v2.models.user.User;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

@ActivityScope
public class CoinbaseActivity extends AppCompatActivity implements ActivityProvider {
    private final Logger mLogger = LoggerFactory.getLogger(CoinbaseActivity.class);
    @Inject
    protected LoginManager mLoginManager;
    @Inject
    protected PINManager mPinManager;
    @Inject
    protected ScreenProtector mScreenProtector;

    @Retention(RetentionPolicy.RUNTIME)
    public @interface RequiresAuthentication {
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface RequiresPIN {
    }

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        ((ComponentProvider) getApplicationContext()).applicationComponent().coinbaseActivitySubcomponent(new BaseActivityModule(this)).inject(this);
    }

    public void signOut() {
        this.mLoginManager.signout();
        redirectToIntro();
    }

    protected void supportLandscapeOnTablet() {
        if (Utils.isTablet(this)) {
            setRequestedOrientation(4);
        } else {
            setRequestedOrientation(1);
        }
    }

    public void onResume() {
        super.onResume();
        if (getClass().isAnnotationPresent(RequiresAuthentication.class)) {
            this.mLoginManager.checkTokenValidityAndRefreshUser(new TokenAndUserResponse() {
                public void onResponseNeededRefresh(boolean shouldLogOut) {
                    if (shouldLogOut) {
                        Utils.showMessage(CoinbaseActivity.this, (int) R.string.session_expired, 1);
                        CoinbaseActivity.this.signOut();
                    }
                }

                public void onResponse(Response<User> response) {
                }
            });
        }
    }

    public void redirectToIntro() {
        Intent intent = new Intent(this, IntroActivity.class);
        intent.addFlags(32768);
        startActivity(intent);
        finish();
    }

    protected void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                CoinbaseActivity.this.finish();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public AppCompatActivity getActivity() {
        return this;
    }
}
