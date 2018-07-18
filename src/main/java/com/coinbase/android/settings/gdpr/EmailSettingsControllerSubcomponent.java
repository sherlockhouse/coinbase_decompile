package com.coinbase.android.settings.gdpr;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface EmailSettingsControllerSubcomponent {
    void inject(EmailSettingsController emailSettingsController);
}
