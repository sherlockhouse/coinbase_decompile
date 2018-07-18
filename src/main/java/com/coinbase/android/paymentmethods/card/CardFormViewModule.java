package com.coinbase.android.paymentmethods.card;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.paymentmethods.card.CardFormPresenter.CheckFieldsErrorMessage;
import com.coinbase.android.paymentmethods.card.CardFormPresenter.PaymentProcessingErrorMessage;

public class CardFormViewModule {
    @CheckFieldsErrorMessage
    @ControllerScope
    int providesCheckFieldsErrorRes() {
        return R.string.please_check_fields;
    }

    @PaymentProcessingErrorMessage
    @ControllerScope
    int providesPaymentProcessingErrorRes() {
        return R.string.a_payment_processing_error_occurred;
    }
}
