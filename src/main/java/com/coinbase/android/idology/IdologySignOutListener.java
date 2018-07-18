package com.coinbase.android.idology;

import com.coinbase.android.ApplicationScope;
import com.coinbase.android.ApplicationSignOutListener;
import javax.inject.Inject;

@ApplicationScope
public class IdologySignOutListener implements ApplicationSignOutListener {
    private final IdologyUtils mIdologyUtils;

    @Inject
    public IdologySignOutListener(IdologyUtils idologyUtils) {
        this.mIdologyUtils = idologyUtils;
    }

    public void onApplicationSignOut() {
        this.mIdologyUtils.clear();
    }
}
