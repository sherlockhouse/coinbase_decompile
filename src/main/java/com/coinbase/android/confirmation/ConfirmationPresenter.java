package com.coinbase.android.confirmation;

import java.util.List;

public interface ConfirmationPresenter {
    List getDetailList();

    String getTitle();

    void onConfirmClicked();

    void onFeeHelpClicked();

    void onPaymentMethodClicked();
}
