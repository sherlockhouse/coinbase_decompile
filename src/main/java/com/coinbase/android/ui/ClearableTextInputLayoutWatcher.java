package com.coinbase.android.ui;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

public class ClearableTextInputLayoutWatcher implements TextWatcher {
    private final Runnable mRunOnTextChanged;
    private final TextInputLayout mTextInputLayout;

    public ClearableTextInputLayoutWatcher(TextInputLayout textInputLayout, Runnable runOnTextChanged) {
        this.mTextInputLayout = textInputLayout;
        this.mRunOnTextChanged = runOnTextChanged;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void afterTextChanged(Editable editable) {
        this.mTextInputLayout.setErrorEnabled(false);
        this.mRunOnTextChanged.run();
    }
}
