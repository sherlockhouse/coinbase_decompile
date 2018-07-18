package com.coinbase.android.ui;

import android.app.Application;
import com.coinbase.android.utils.PriceAlertUtils;
import rx.functions.Func0;

final /* synthetic */ class MystiqueBottomNavigationPresenterModule$$Lambda$5 implements Func0 {
    private final Application arg$1;

    private MystiqueBottomNavigationPresenterModule$$Lambda$5(Application application) {
        this.arg$1 = application;
    }

    public static Func0 lambdaFactory$(Application application) {
        return new MystiqueBottomNavigationPresenterModule$$Lambda$5(application);
    }

    public Object call() {
        return Boolean.valueOf(PriceAlertUtils.hadPriceAlert(this.arg$1));
    }
}
