package com.coinbase.android.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GoToSettingsDialogController$$Lambda$1 implements OnClickListener {
    private final GoToSettingsDialogController arg$1;

    private GoToSettingsDialogController$$Lambda$1(GoToSettingsDialogController goToSettingsDialogController) {
        this.arg$1 = goToSettingsDialogController;
    }

    public static OnClickListener lambdaFactory$(GoToSettingsDialogController goToSettingsDialogController) {
        return new GoToSettingsDialogController$$Lambda$1(goToSettingsDialogController);
    }

    public void onClick(View view) {
        this.arg$1.onGoToSettingsClicked();
    }
}
