package com.coinbase.android.billing;

import com.coinbase.android.FragmentScope;
import com.coinbase.android.R;
import com.coinbase.android.billing.AddBillingAddressPresenter.CheckFieldsErrorMessage;

public class AddBillingAddressViewModule {
    @CheckFieldsErrorMessage
    @FragmentScope
    int providesCheckFieldsErrorRes() {
        return R.string.please_check_fields;
    }
}
