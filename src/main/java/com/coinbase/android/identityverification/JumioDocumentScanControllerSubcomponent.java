package com.coinbase.android.identityverification;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface JumioDocumentScanControllerSubcomponent {
    void inject(InAppIdentityDocumentScanController inAppIdentityDocumentScanController);
}
