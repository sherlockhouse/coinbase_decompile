package com.coinbase.android.ui;

import dagger.internal.Factory;

public final class NumericKeyboardPresenter_Factory implements Factory<NumericKeyboardPresenter> {
    private static final NumericKeyboardPresenter_Factory INSTANCE = new NumericKeyboardPresenter_Factory();

    public NumericKeyboardPresenter get() {
        return provideInstance();
    }

    public static NumericKeyboardPresenter provideInstance() {
        return new NumericKeyboardPresenter();
    }

    public static NumericKeyboardPresenter_Factory create() {
        return INSTANCE;
    }

    public static NumericKeyboardPresenter newNumericKeyboardPresenter() {
        return new NumericKeyboardPresenter();
    }
}
