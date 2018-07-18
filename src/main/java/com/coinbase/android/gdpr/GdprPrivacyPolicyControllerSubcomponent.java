package com.coinbase.android.gdpr;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface GdprPrivacyPolicyControllerSubcomponent {
    void inject(GdprPrivacyPolicyController gdprPrivacyPolicyController);
}
