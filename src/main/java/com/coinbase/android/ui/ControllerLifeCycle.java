package com.coinbase.android.ui;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public abstract class ControllerLifeCycle {
    public static final String LIFE_CYCLE_TYPE = "life_cycle_type";
    protected Menu mMenu;

    abstract int getUpIndicator();

    abstract void onActivityPaused(Activity activity);

    abstract void onActivityResumed(Activity activity);

    abstract void onAttach(View view);

    abstract void onChangeEndShow();

    abstract void onChangeStartHide();

    abstract void onCreate();

    abstract void onDestroy();

    abstract void onDetach(View view);

    abstract boolean onOptionsItemSelected(MenuItem menuItem);

    abstract void replayOnHide();

    abstract void replayOnShow();

    public void onPrepareOptionsMenu(Menu menu) {
        this.mMenu = menu;
    }
}
