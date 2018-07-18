package com.coinbase.android;

import com.coinbase.android.accounts.AccountMainController;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.ControllerLifeCycleFactory.LifeCycleType;
import rx.functions.Func0;

final /* synthetic */ class BottomNavActivityModule$$Lambda$3 implements Func0 {
    private final BottomNavActivityModule arg$1;

    private BottomNavActivityModule$$Lambda$3(BottomNavActivityModule bottomNavActivityModule) {
        this.arg$1 = bottomNavActivityModule;
    }

    public static Func0 lambdaFactory$(BottomNavActivityModule bottomNavActivityModule) {
        return new BottomNavActivityModule$$Lambda$3(bottomNavActivityModule);
    }

    public Object call() {
        return new AccountMainController(this.arg$1.createBundle(Type.ACCOUNTS, LifeCycleType.PAGE));
    }
}
