package com.coinbase.android;

import com.coinbase.android.dashboard.DashboardMainController;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.ControllerLifeCycleFactory.LifeCycleType;
import rx.functions.Func0;

final /* synthetic */ class BottomNavActivityModule$$Lambda$2 implements Func0 {
    private final BottomNavActivityModule arg$1;

    private BottomNavActivityModule$$Lambda$2(BottomNavActivityModule bottomNavActivityModule) {
        this.arg$1 = bottomNavActivityModule;
    }

    public static Func0 lambdaFactory$(BottomNavActivityModule bottomNavActivityModule) {
        return new BottomNavActivityModule$$Lambda$2(bottomNavActivityModule);
    }

    public Object call() {
        return new DashboardMainController(this.arg$1.createBundle(Type.DASHBOARD, LifeCycleType.PAGE));
    }
}
