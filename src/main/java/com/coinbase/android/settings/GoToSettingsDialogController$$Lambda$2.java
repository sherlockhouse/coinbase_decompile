package com.coinbase.android.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GoToSettingsDialogController$$Lambda$2 implements OnClickListener {
    private final GoToSettingsDialogController arg$1;

    private GoToSettingsDialogController$$Lambda$2(GoToSettingsDialogController goToSettingsDialogController) {
        this.arg$1 = goToSettingsDialogController;
    }

    public static OnClickListener lambdaFactory$(GoToSettingsDialogController goToSettingsDialogController) {
        return new GoToSettingsDialogController$$Lambda$2(goToSettingsDialogController);
    }

    public void onClick(View view) {
        this.arg$1.onCloseButtonClicked();
    }
}
