package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StateSelectorPresenterModule.kt */
public final class StateSelectorPresenterModule {
    private final ActionBarController mActionBarController;
    private final StateSelectorScreen mScreen;

    public StateSelectorPresenterModule(StateSelectorScreen mScreen, ActionBarController mActionBarController) {
        Intrinsics.checkParameterIsNotNull(mScreen, "mScreen");
        Intrinsics.checkParameterIsNotNull(mActionBarController, "mActionBarController");
        this.mScreen = mScreen;
        this.mActionBarController = mActionBarController;
    }

    @ControllerScope
    public final StateSelectorScreen providesStateSelectorScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public final ActionBarController providesActionBarController() {
        return this.mActionBarController;
    }
}
