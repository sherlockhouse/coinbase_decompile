package com.coinbase.android.buysell;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface SellControllerSubcomponent {
    void inject(SellController sellController);
}
