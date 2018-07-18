package com.coinbase.android.ui;

import android.view.View;
import android.widget.RelativeLayout;

public interface BaseViewProvider {
    View getBaseView();

    RelativeLayout getBlockingOverlayView();
}
