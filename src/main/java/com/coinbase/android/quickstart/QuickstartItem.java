package com.coinbase.android.quickstart;

import com.coinbase.android.R;

public enum QuickstartItem {
    ADD_PAYMENT_METHOD(R.string.quickstart_add_payment_method, R.drawable.add_bank_small),
    COMPLETE_CDV(R.string.quickstart_complete_cdv, R.drawable.add_bank_small),
    DEPOSIT_FUNDS(R.string.quickstart_deposit_funds, R.drawable.add_bank_small),
    VERIFY_IDENTITY(R.string.quickstart_verify_identity, R.drawable.identity_transactions),
    VERIFY_IDENTITY_PENDING(R.string.quickstart_jumio_pending, R.drawable.identity_transactions),
    VERIFY_IDENTITY_FACEMATCH(R.string.quickstart_verify_identity, R.drawable.identity_transactions),
    VERIFY_PHONE_NUMBER(R.string.quickstart_verify_phone_number, R.drawable.phone_small),
    VERIFY_EMAIL(R.string.quickstart_verify_email, R.drawable.email_verification),
    ADD_PAYMENT_VIA_WEBSITE(R.string.quickstart_add_payment_via_website, R.drawable.ic_globe),
    REGION_UNSUPPORTED(R.string.quickstart_region_unsupported, R.drawable.ic_globe);
    
    private int mIconResource;
    private int mTitleResource;

    private QuickstartItem(int titleResource, int iconResource) {
        this.mTitleResource = titleResource;
        this.mIconResource = iconResource;
    }

    public int getTitleResource() {
        return this.mTitleResource;
    }

    public int getIconResource() {
        return this.mIconResource;
    }
}
