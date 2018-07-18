package com.coinbase.android.settings.gdpr;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface PrivacyRightsControllerSubcomponent {
    void inject(PrivacyRightsController privacyRightsController);
}
