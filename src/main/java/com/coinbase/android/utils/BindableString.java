package com.coinbase.android.utils;

import android.databinding.BaseObservable;

public class BindableString extends BaseObservable {
    private String value;

    public BindableString(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value != null ? this.value : "";
    }

    public void setValue(String value) {
        if (this.value != value) {
            if (this.value == null) {
                if (value != null) {
                    this.value = value;
                    notifyChange();
                }
            } else if (!this.value.equals(value)) {
                this.value = value;
                notifyChange();
            }
        }
    }

    public boolean isEmpty() {
        return this.value == null || this.value.isEmpty();
    }
}
