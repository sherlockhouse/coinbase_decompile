package com.coinbase.android.gdpr;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.ControllerLifeCycle;
import com.coinbase.android.ui.ControllerLifeCycleFactory.LifeCycleType;
import com.coinbase.android.ui.ModalControllerLifeCycle;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.v2.models.user.OnboardingItem;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerScope
public class OnboardingRouter {
    public static String DISABLE_BACK = "disableBackButton";
    private final ActionBarController mController;
    private final Logger mLogger = LoggerFactory.getLogger(OnboardingRouter.class);
    private final LoginManager mLoginManager;
    private final OnboardingUpdatedConnector mOnboardingUpdatedConnector;

    public enum OnboardingStep {
        PRIVACY_NOTICE("privacy_notice"),
        EMAIL_PREFERENCE(ApiConstants.EMAIL_PREFERENCE_PARAM);
        
        private final String mStep;

        private OnboardingStep(String step) {
            this.mStep = step;
        }

        public String getStep() {
            return this.mStep;
        }

        public static OnboardingStep from(String step) {
            for (OnboardingStep type : values()) {
                if (type.getStep().equals(step)) {
                    return type;
                }
            }
            return null;
        }
    }

    @Inject
    public OnboardingRouter(LoginManager loginManager, ActionBarController controller, OnboardingUpdatedConnector onboardingUpdatedConnector) {
        this.mLoginManager = loginManager;
        this.mController = controller;
        this.mOnboardingUpdatedConnector = onboardingUpdatedConnector;
    }

    public void routeToNext(List<OnboardingItem> onboardingItems) {
        if (onboardingItems == null || onboardingItems.isEmpty()) {
            finish();
            return;
        }
        OnboardingItem item = (OnboardingItem) onboardingItems.get(0);
        if (item != null && item.getStep() != null) {
            ActionBarController nextController = getNextController(item);
            if (nextController != null) {
                pushOrReplaceModalController(nextController);
                this.mOnboardingUpdatedConnector.getOnboardingItems().onNext(onboardingItems);
            }
        }
    }

    private void finish() {
        if (this.mController.getControllerLifeCycle() instanceof ModalControllerLifeCycle) {
            ((ModalControllerLifeCycle) this.mController.getControllerLifeCycle()).closeModal();
        }
        this.mOnboardingUpdatedConnector.getOnboardingItems().onNext(null);
    }

    private ActionBarController getNextController(OnboardingItem nextItem) {
        Bundle args = new Bundle();
        args.putBoolean(OnboardingScreen.CENTER_MODAL_WITH_OVERLAY, this.mLoginManager.isSignedIn());
        args.putInt(ControllerLifeCycle.LIFE_CYCLE_TYPE, LifeCycleType.MODAL.getValue());
        if (nextItem.getRequired().booleanValue()) {
            args.putBoolean(DISABLE_BACK, true);
        }
        OnboardingStep nextStep = OnboardingStep.from(nextItem.getStep());
        if (nextStep == null) {
            this.mLogger.error("Unknown onboarding step: " + nextItem.getStep());
            return null;
        }
        switch (nextStep) {
            case PRIVACY_NOTICE:
                return new GdprIntroController(args);
            case EMAIL_PREFERENCE:
                return new GdprMarketingEmailController(args);
            default:
                return null;
        }
    }

    private void pushOrReplaceModalController(ActionBarController actionBarController) {
        if (this.mController.getRouter().getBackstackSize() > 1) {
            this.mController.replaceModalController(actionBarController);
        } else {
            this.mController.pushModalController(actionBarController);
        }
    }
}
