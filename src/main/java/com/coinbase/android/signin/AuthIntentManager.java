package com.coinbase.android.signin;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.coinbase.android.ApplicationScope;
import com.coinbase.android.signin.AuthManager.ViewType;
import javax.inject.Inject;

@ApplicationScope
public class AuthIntentManager {
    private final Context mContext;

    @Inject
    public AuthIntentManager(Application app) {
        this.mContext = app;
    }

    public Pair<Intent, ViewType> getAuthIntentForResult(Pair<ViewType, String> resultPair) {
        Intent intent = getAuthIntent(this.mContext, (ViewType) resultPair.first);
        if (intent == null) {
            return new Pair(intent, resultPair.first);
        }
        if (resultPair.second != null) {
            if (resultPair.first == ViewType.STATE_REGISTRATION) {
                intent.putExtra("STATE_UNSUPPORTED", true);
            } else if (resultPair.first == ViewType.EMAIL_VERIFICATION) {
                intent.putExtra(EmailVerifyPresenter.EMAIL, (String) resultPair.second);
            }
        }
        return new Pair(intent, resultPair.first);
    }

    public Intent getAuthIntent(Context context, ViewType viewType) {
        switch (viewType) {
            case DEVICE_VERIFICATION:
            case TWO_FACTOR:
            case EMAIL_VERIFICATION:
            case STATE_REGISTRATION:
            case PHONE_VERIFICATION:
            case IDENTITY_VERIFICATION:
            case IDV_REQUIRED:
            case IDV_WITH_FACE_MATCH_REQUIRED:
            case TERMS_OF_SERVICE:
                Intent intent = new Intent(context, RegistrationControllerActivity.class);
                intent.putExtra(RegistrationControllerActivity.NEXT_FLOW, viewType.getType());
                return intent;
            default:
                return null;
        }
    }
}
