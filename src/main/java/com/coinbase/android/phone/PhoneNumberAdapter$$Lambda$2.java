package com.coinbase.android.phone;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.api.internal.models.phoneNumber.Data;

final /* synthetic */ class PhoneNumberAdapter$$Lambda$2 implements OnClickListener {
    private final PhoneNumberAdapter arg$1;
    private final Data arg$2;

    private PhoneNumberAdapter$$Lambda$2(PhoneNumberAdapter phoneNumberAdapter, Data data) {
        this.arg$1 = phoneNumberAdapter;
        this.arg$2 = data;
    }

    public static OnClickListener lambdaFactory$(PhoneNumberAdapter phoneNumberAdapter, Data data) {
        return new PhoneNumberAdapter$$Lambda$2(phoneNumberAdapter, data);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onRemovePhoneNumberClicked(this.arg$2);
    }
}
