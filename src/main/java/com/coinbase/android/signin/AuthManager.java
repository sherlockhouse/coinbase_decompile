package com.coinbase.android.signin;

import android.content.SharedPreferences;
import android.util.Pair;
import com.coinbase.android.modalAlerts.ModalRouterAggregator;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.task.GetUserTask.Restriction;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.user.Data;
import com.coinbase.v2.models.user.User;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;

public class AuthManager {
    private static final String AUTH_RESTRICTIONS = "auth_restrictions";
    private static final String AUTH_RESTRICTIONS_EMAIL = "auth_restrictions_email";
    private static final String AUTH_SCREEN = "AUTH_SCREEN";
    static final String STATE_UNSUPPORTED = "STATE_UNSUPPORTED";
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final SharedPreferences mSharedPrefs;
    private final SplitTesting mSplitTesting;

    public enum ViewType {
        NONE(0),
        LOGIN(1),
        SIGN_UP(2),
        EMAIL_VERIFICATION(3),
        DEVICE_VERIFICATION(4),
        PHONE_VERIFICATION(5),
        IDENTITY_VERIFICATION(6),
        IDV_REQUIRED(7),
        IDV_WITH_FACE_MATCH_REQUIRED(8),
        STATE_REGISTRATION(9),
        TWO_FACTOR(10),
        TERMS_OF_SERVICE(11),
        INCORRECT_CREDENTIALS(12),
        RATE_LIMITED(13);
        
        private final int mType;

        private ViewType(int type) {
            this.mType = type;
        }

        public int getType() {
            return this.mType;
        }

        public static ViewType from(int type) {
            for (ViewType val : values()) {
                if (val.getType() == type) {
                    return val;
                }
            }
            return null;
        }
    }

    public AuthManager(LoginManager loginManager, SharedPreferences sharedPreferences, Scheduler mainScheduler, SplitTesting splitTesting) {
        this.mLoginManager = loginManager;
        this.mMainScheduler = mainScheduler;
        this.mSplitTesting = splitTesting;
        this.mSharedPrefs = sharedPreferences;
    }

    private Pair<ViewType, String> getAuthTypeFromCache() {
        return getAuthTypeForRestrictions(this.mSharedPrefs.getStringSet(AUTH_RESTRICTIONS, new HashSet()));
    }

    public Observable<Pair<ViewType, String>> getAuthTypeForUserResponse(Response<User> response) {
        if (!this.mLoginManager.isSignedIn()) {
            return Observable.just(new Pair(ViewType.NONE, null));
        }
        if (response.isSuccessful()) {
            return Observable.just(getAuthTypeForUser((User) response.body()));
        }
        return Observable.just(new Pair(ViewType.NONE, null));
    }

    Pair<ViewType, String> getAuthTypeForUser(User user) {
        if (user == null) {
            return new Pair(ViewType.NONE, null);
        }
        Data data = user.getData();
        if (data != null) {
            this.mSplitTesting.update(data);
            this.mLoginManager.saveUser(data);
        }
        cacheAuthRestrictionsForUser(data);
        return getAuthTypeFromCache();
    }

    public Observable<Pair<ViewType, String>> getAuthTypeForUser() {
        if (this.mLoginManager.isSignedIn()) {
            return this.mLoginManager.getClient().getUserRx().observeOn(this.mMainScheduler).map(AuthManager$$Lambda$1.lambdaFactory$(this));
        }
        return Observable.just(new Pair(ViewType.NONE, null));
    }

    static /* synthetic */ Pair lambda$getAuthTypeForUser$0(AuthManager this_, Pair pair) {
        Response<User> response = pair.first;
        if (response.isSuccessful()) {
            return this_.getAuthTypeForUser((User) response.body());
        }
        return new Pair(ViewType.NONE, null);
    }

    public Pair<ViewType, String> getAuthTypeForRestrictions(Set<String> restrictions) {
        ViewType result = ViewType.NONE;
        String data = null;
        String email = this.mSharedPrefs.getString(AUTH_RESTRICTIONS_EMAIL, "");
        if (!Utils.isUserEmailVerified(restrictions)) {
            result = ViewType.EMAIL_VERIFICATION;
            data = email;
        } else if (Utils.isPhoneVerificationRequired(restrictions) && isInAuthFlow()) {
            result = ViewType.PHONE_VERIFICATION;
        } else if (Utils.isIdentityVerificationRequired(restrictions) && isInAuthFlow()) {
            result = ViewType.IDENTITY_VERIFICATION;
        } else if (Utils.isIdvRequired(restrictions) && isInAuthFlow()) {
            result = ViewType.IDV_REQUIRED;
        } else if (Utils.isIdvWithFaceMatchRequired(restrictions) && isInAuthFlow()) {
            result = ViewType.IDV_WITH_FACE_MATCH_REQUIRED;
        } else if (Utils.isStateVerificationRequired(restrictions)) {
            result = ViewType.STATE_REGISTRATION;
        } else if (Utils.isStateUnsupported(restrictions)) {
            result = ViewType.STATE_REGISTRATION;
            data = STATE_UNSUPPORTED;
        } else if (Utils.isUserAgreementRequired(restrictions)) {
            result = ViewType.TERMS_OF_SERVICE;
        }
        return new Pair(result, data);
    }

    public void setAuthScreen(int screen) {
        this.mSharedPrefs.edit().putInt(AUTH_SCREEN, screen).apply();
    }

    public void clearAuthScreen() {
        this.mSharedPrefs.edit().putInt(ModalRouterAggregator.INITIATING_AUTH_SCREEN, this.mSharedPrefs.getInt(AUTH_SCREEN, ViewType.LOGIN.ordinal())).remove(AUTH_SCREEN).apply();
    }

    public boolean isSignUp() {
        return ViewType.SIGN_UP.ordinal() == this.mSharedPrefs.getInt(AUTH_SCREEN, ViewType.SIGN_UP.ordinal());
    }

    public boolean isInAuthFlow() {
        return this.mSharedPrefs.contains(AUTH_SCREEN);
    }

    private void cacheAuthRestrictionsForUser(Data user) {
        if (user.getRestrictions() == null || user.getRestrictions().isEmpty()) {
            this.mSharedPrefs.edit().remove(AUTH_RESTRICTIONS).apply();
            this.mSharedPrefs.edit().remove(AUTH_RESTRICTIONS_EMAIL).apply();
            return;
        }
        this.mSharedPrefs.edit().putStringSet(AUTH_RESTRICTIONS, new HashSet(user.getRestrictions())).apply();
        this.mSharedPrefs.edit().putString(AUTH_RESTRICTIONS_EMAIL, user.getEmail()).apply();
    }

    public void cacheEmailRestrictionFromSignup(String email) {
        this.mSharedPrefs.edit().remove(AUTH_RESTRICTIONS).remove(AUTH_RESTRICTIONS_EMAIL).putStringSet(AUTH_RESTRICTIONS, new HashSet(Arrays.asList(new String[]{Restriction.EMAIL_VERIFICATION_REQUIRED.toString()}))).putString(AUTH_RESTRICTIONS_EMAIL, email).apply();
    }
}
