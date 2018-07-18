package com.coinbase.android.phone;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface PhoneNumberControllerSubcomponent {
    void inject(PhoneNumberController phoneNumberController);
}
