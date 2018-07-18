package com.coinbase.android.task;

import android.content.Context;
import android.util.Pair;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.settings.UserUpdatedConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.user.User;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Scheduler;

@ActivityScope
public class GetUserTask {
    Context context;
    private GetUserListener listener;
    @Inject
    LoginManager loginManager;
    @MainScheduler
    @Inject
    Scheduler mMainScheduler;
    @Inject
    UserUpdatedConnector mUserUpdatedConnector;

    public interface GetUserListener {
        void onException(boolean z);

        void onFinally(Response<User> response);

        void onPreExecute();
    }

    public enum AdminFlags {
        REQUIRE_JUMIO_FACE_MATCH("require_jumio_face_match");
        
        private String _value;

        private AdminFlags(String value) {
            this._value = value;
        }

        public static AdminFlags create(String val) {
            for (AdminFlags adminFlag : values()) {
                if (adminFlag.toString().equalsIgnoreCase(val)) {
                    return adminFlag;
                }
            }
            return null;
        }

        public String toString() {
            return this._value;
        }
    }

    public enum Restriction {
        RESIDENTIAL_ADDRESS_REQUIRED("residential_address_required"),
        REGIONAL_AGREEMENT_REQUIRED("regional_agreement_required"),
        STATE_UNSUPPORTED("state_unsupported"),
        STATE_VERIFICATION_REQUIRED("state_verification_required"),
        NEEDS_TO_REACCEPT_USER_AGREEMENT("needs_to_reaccept_user_agreement"),
        EMAIL_VERIFICATION_REQUIRED("email_verification_required"),
        PHONE_VERIFICATION_REQUIRED("phone_required"),
        IDENTITY_VERIFICATION_REQUIRED("identity_verification_required"),
        IDV_REQUIRED("idv_required"),
        IDV_WITH_FACEMATCH_REQUIRED("idv_with_face_match_required");
        
        private String _value;

        private Restriction(String value) {
            this._value = value;
        }

        public static Restriction create(String val) {
            for (Restriction restriction : values()) {
                if (restriction.toString().equalsIgnoreCase(val)) {
                    return restriction;
                }
            }
            return null;
        }

        public String toString() {
            return this._value;
        }
    }

    public GetUserTask(Context context, GetUserListener listener) {
        this.context = context;
        this.listener = listener;
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().tasksSubcomponent().inject(this);
    }

    public void getUser() {
        if (this.listener != null) {
            this.listener.onPreExecute();
        }
        this.loginManager.getClient().getUserRx().observeOn(this.mMainScheduler).subscribe(GetUserTask$$Lambda$1.lambdaFactory$(this), GetUserTask$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$getUser$0(GetUserTask this_, Pair pair) {
        Response<User> response = pair.first;
        if (response.isSuccessful()) {
            if (this_.context != null) {
                Utils.setAdminFlagsToPrefs(((User) response.body()).getData(), this_.context);
            }
            this_.mUserUpdatedConnector.get().onNext(((User) response.body()).getData());
            if (this_.listener != null) {
                this_.listener.onFinally(response);
            }
        } else if (this_.listener != null) {
            boolean z;
            GetUserListener getUserListener = this_.listener;
            if (response.code() == 401) {
                z = true;
            } else {
                z = false;
            }
            getUserListener.onException(z);
        }
    }

    static /* synthetic */ void lambda$getUser$1(GetUserTask this_, Throwable t) {
        if (this_.listener != null) {
            this_.listener.onException(false);
        }
    }
}
