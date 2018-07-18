package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface StateSuspendedControllerSubcomponent {
    void inject(StateSuspendedController stateSuspendedController);
}
