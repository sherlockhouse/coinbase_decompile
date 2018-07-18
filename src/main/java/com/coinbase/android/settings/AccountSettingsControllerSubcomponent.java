package com.coinbase.android.settings;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface AccountSettingsControllerSubcomponent {
    void inject(AccountSettingsController accountSettingsController);
}
