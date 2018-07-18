package com.coinbase.android.transfers;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface TransferSendControllerSubcomponent {
    void inject(TransferSendController transferSendController);
}
