package com.coinbase.android.quickstart;

import android.content.Context;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.paymentmethods.GetPaymentMethodsTaskRx;
import com.coinbase.android.phone.PhoneNumbersUpdatedConnector;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.api.LoginManager;
import rx.Scheduler;

public class QuickstartModule {
    private final Context mContext;
    private final ActionBarController mController;

    public QuickstartModule(Context context, ActionBarController controller) {
        this.mContext = context;
        this.mController = controller;
    }

    @ControllerScope
    public QuickstartManager providesQuickStartManager(LoginManager loginManager, PhoneNumbersUpdatedConnector phoneNumbersUpdatedConnector, GetPaymentMethodsTaskRx task, @MainScheduler Scheduler mainScheduler, SplitTesting splitTesting) {
        return new QuickstartManager(loginManager, this.mContext, phoneNumbersUpdatedConnector, task, mainScheduler, this.mController, splitTesting);
    }
}
