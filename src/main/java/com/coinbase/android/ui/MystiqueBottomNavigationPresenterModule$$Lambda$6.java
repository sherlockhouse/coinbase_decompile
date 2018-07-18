package com.coinbase.android.ui;

import android.app.Application;
import com.coinbase.android.utils.PriceAlertUtils;

final /* synthetic */ class MystiqueBottomNavigationPresenterModule$$Lambda$6 implements Runnable {
    private final Application arg$1;

    private MystiqueBottomNavigationPresenterModule$$Lambda$6(Application application) {
        this.arg$1 = application;
    }

    public static Runnable lambdaFactory$(Application application) {
        return new MystiqueBottomNavigationPresenterModule$$Lambda$6(application);
    }

    public void run() {
        PriceAlertUtils.setHadPriceAlert(this.arg$1, false);
    }
}
