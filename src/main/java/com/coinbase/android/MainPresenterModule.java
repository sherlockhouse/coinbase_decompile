package com.coinbase.android;

public class MainPresenterModule {
    private final MainActivity mActivity;

    public MainPresenterModule(MainActivity activity) {
        this.mActivity = activity;
    }

    @ActivityScope
    public MainActivity providesMainActivity() {
        return this.mActivity;
    }
}
