package com.coinbase.android;

import com.coinbase.android.settings.AccountSettingsController;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.ControllerLifeCycleFactory.LifeCycleType;
import rx.functions.Func0;

final /* synthetic */ class BottomNavActivityModule$$Lambda$5 implements Func0 {
    private final BottomNavActivityModule arg$1;

    private BottomNavActivityModule$$Lambda$5(BottomNavActivityModule bottomNavActivityModule) {
        this.arg$1 = bottomNavActivityModule;
    }

    public static Func0 lambdaFactory$(BottomNavActivityModule bottomNavActivityModule) {
        return new BottomNavActivityModule$$Lambda$5(bottomNavActivityModule);
    }

    public Object call() {
        return new AccountSettingsController(this.arg$1.createBundle(Type.MORE, LifeCycleType.PAGE));
    }
}
