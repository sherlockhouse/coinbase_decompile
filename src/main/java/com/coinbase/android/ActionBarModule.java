package com.coinbase.android;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import com.coinbase.android.ui.ActionBarProvider;

public class ActionBarModule {
    private final ActionBarProvider mActionBarProvider;

    public ActionBarModule(ActionBarProvider actionBarProvider) {
        this.mActionBarProvider = actionBarProvider;
    }

    @ActivityScope
    public ActionBar providesActionBar() {
        return this.mActionBarProvider.getSupportActionBar();
    }

    @ActivityScope
    public Toolbar providesToolbar() {
        return this.mActionBarProvider.getToolbar();
    }
}
