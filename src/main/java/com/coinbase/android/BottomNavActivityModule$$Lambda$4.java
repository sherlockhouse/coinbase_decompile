package com.coinbase.android;

import com.coinbase.android.notifications.priceAlerts.PriceAlertsController;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.ControllerLifeCycleFactory.LifeCycleType;
import rx.functions.Func0;

final /* synthetic */ class BottomNavActivityModule$$Lambda$4 implements Func0 {
    private final BottomNavActivityModule arg$1;

    private BottomNavActivityModule$$Lambda$4(BottomNavActivityModule bottomNavActivityModule) {
        this.arg$1 = bottomNavActivityModule;
    }

    public static Func0 lambdaFactory$(BottomNavActivityModule bottomNavActivityModule) {
        return new BottomNavActivityModule$$Lambda$4(bottomNavActivityModule);
    }

    public Object call() {
        return new PriceAlertsController(this.arg$1.createBundle(Type.ALERTS, LifeCycleType.PAGE));
    }
}
