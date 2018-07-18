package com.coinbase.android.buysell;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface BuyControllerSubcomponent {
    void inject(BuyController buyController);
}
