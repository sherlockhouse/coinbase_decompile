package com.coinbase.android.alerts;

import android.view.View.OnClickListener;

public interface AlertsListItemScreen {
    void hideDismissButton();

    void hideLearnMoreButton();

    void hideSelf();

    void setOnDismissClickListener(OnClickListener onClickListener);

    void setOnLearnMoreClickListener(OnClickListener onClickListener);

    void setText(String str);

    void showDismissButton();

    void showLearnMoreButton();
}
