package com.coinbase.android.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

public interface ActionBarProvider {
    ActionBar getSupportActionBar();

    Toolbar getToolbar();
}
