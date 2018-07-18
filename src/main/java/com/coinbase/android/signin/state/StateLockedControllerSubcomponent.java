package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface StateLockedControllerSubcomponent {
    void inject(StateLockedController stateLockedController);
}
