package com.coinbase.android;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Window;

public class BaseActivityModule {
    private final ActivityProvider mActivityProvider;

    public BaseActivityModule(ActivityProvider activityProvider) {
        this.mActivityProvider = activityProvider;
    }

    @ActivityScope
    public Window providesWindow() {
        return this.mActivityProvider.getWindow();
    }

    @ActivityScope
    public LayoutInflater providesLayoutInflater() {
        return this.mActivityProvider.getLayoutInflater();
    }

    @ActivityScope
    public AppCompatActivity providesActivity() {
        return this.mActivityProvider.getActivity();
    }
}
