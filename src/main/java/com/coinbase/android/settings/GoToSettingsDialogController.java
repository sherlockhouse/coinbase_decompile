package com.coinbase.android.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.databinding.DialogGotoSettingsBinding;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.DialogController;
import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import javax.inject.Inject;

@ControllerScope
public class GoToSettingsDialogController extends DialogController {
    DialogGotoSettingsBinding mBinding;
    @Inject
    BottomNavigationConnector mBottomNavigationConnector;
    @Inject
    GoToSettingsConnector mGoToSettingsConnector;
    @Inject
    MixpanelTracking mMixpanelTracking;

    public GoToSettingsDialogController(Bundle args) {
        super(args);
    }

    protected View inflateContent(LayoutInflater inflater, ViewGroup container) {
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().inject(this);
        this.mBinding = DialogGotoSettingsBinding.inflate(inflater, container, true);
        this.mBinding.btnGoToSettings.setOnClickListener(GoToSettingsDialogController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btnGoToSettingsClose.setOnClickListener(GoToSettingsDialogController$$Lambda$2.lambdaFactory$(this));
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_LINKED_ACCOUNTS_NO_ACCOUNT_VIEWED, new String[0]);
        return this.mBinding.getRoot();
    }

    private void onGoToSettingsClicked() {
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(Type.MORE).setBottomNavigationItem(Type.MORE).build());
        this.mGoToSettingsConnector.get().onNext(new ClassConsumableEvent());
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_LINKED_ACCOUNTS_NO_ACCOUNT_TAPPED_GO_TO_SETTINGS, new String[0]);
        dismiss(true);
    }

    private void onCloseButtonClicked() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_LINKED_ACCOUNTS_NO_ACCOUNT_TAPPED_CLOSE, new String[0]);
        dismiss();
    }
}
