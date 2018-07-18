package com.coinbase.android.settings.gdpr;

import android.app.Application;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import com.coinbase.android.ui.ActionBarController;
import rx.functions.Func0;

public class GdprPrivacyRequestPresenterModule {
    private final ActionBarController mController;
    private final GdprPrivacyRequestScreen mScreen;

    public GdprPrivacyRequestPresenterModule(ActionBarController controller, GdprPrivacyRequestScreen screen) {
        this.mController = controller;
        this.mScreen = screen;
    }

    @ControllerScope
    public GdprPrivacyRequestScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }

    @ControllerScope
    public Func0<PrivacyRequestViewModel> providesRequestDataViewModel(Application application, SettingsPreferenceItemClickedConnector itemClickedConnector) {
        return GdprPrivacyRequestPresenterModule$$Lambda$1.lambdaFactory$(application, itemClickedConnector);
    }

    @ControllerScope
    public Func0<PrivacyRequestViewModel> providesRequestDeletionViewModel(Application application, SettingsPreferenceItemClickedConnector itemClickedConnector) {
        return GdprPrivacyRequestPresenterModule$$Lambda$2.lambdaFactory$(application, itemClickedConnector);
    }

    @ControllerScope
    public Func0<PrivacyRequestViewModel> providesRequestExportViewModel(Application application, SettingsPreferenceItemClickedConnector itemClickedConnector) {
        return GdprPrivacyRequestPresenterModule$$Lambda$3.lambdaFactory$(application, itemClickedConnector);
    }

    @ControllerScope
    public Func0<PrivacyRequestViewModel> providesRequestRestrictionProcessingViewModel(Application application, SettingsPreferenceItemClickedConnector itemClickedConnector) {
        return GdprPrivacyRequestPresenterModule$$Lambda$4.lambdaFactory$(application, itemClickedConnector);
    }

    @ControllerScope
    public Func0<PrivacyRequestViewModel> providesRequestCorrectionViewModel(Application application, SettingsPreferenceItemClickedConnector itemClickedConnector) {
        return GdprPrivacyRequestPresenterModule$$Lambda$5.lambdaFactory$(application, itemClickedConnector);
    }
}
