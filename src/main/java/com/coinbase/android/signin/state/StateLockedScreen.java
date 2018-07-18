package com.coinbase.android.signin.state;

import android.content.Intent;

public interface StateLockedScreen {
    void showCancelDialog();

    void showSupportActivity(Intent intent);
}
