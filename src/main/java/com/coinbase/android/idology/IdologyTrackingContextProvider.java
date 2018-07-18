package com.coinbase.android.idology;

import android.app.Application;
import android.content.Context;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.settings.idology.IdologySettingsController;
import com.coinbase.android.settings.idology.IdologySettingsQuestionsController;
import com.coinbase.android.signin.state.StateIdologyAddressFormController;
import com.coinbase.android.signin.state.StateIdologyNameFormController;
import com.coinbase.android.signin.state.StateIdologyQuestionsController;
import com.coinbase.android.signin.state.StateIdologyRetryFormController;
import com.coinbase.android.signin.state.StateIdologySourceOfFundsFormController;
import com.coinbase.android.ui.ActionBarController;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Inject;
import javax.inject.Qualifier;

@ControllerScope
public class IdologyTrackingContextProvider {
    private static final int ONBOARDING = 2131297227;
    private static final int SETTINGS = 2131297228;
    private static final int UNKNOWN = 2131297229;
    private final Context mContext;
    private final ActionBarController mController;

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface IdologyTrackingContext {
    }

    @Inject
    public IdologyTrackingContextProvider(ActionBarController controller, Application app) {
        this.mController = controller;
        this.mContext = app;
    }

    public String getContext() {
        if ((this.mController instanceof StateIdologyNameFormController) || (this.mController instanceof StateIdologyAddressFormController) || (this.mController instanceof StateIdologySourceOfFundsFormController) || (this.mController instanceof StateIdologyQuestionsController) || (this.mController instanceof StateIdologyRetryFormController)) {
            return this.mContext.getString(R.string.idology_tracking_context_onboarding);
        }
        if ((this.mController instanceof IdologySettingsController) || (this.mController instanceof IdologySettingsQuestionsController)) {
            return this.mContext.getString(R.string.idology_tracking_context_settings);
        }
        return this.mContext.getString(R.string.idology_tracking_context_unknown);
    }
}
