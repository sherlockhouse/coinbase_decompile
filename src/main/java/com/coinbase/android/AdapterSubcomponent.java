package com.coinbase.android;

import com.coinbase.android.billing.BillingAddressListAdapter;
import com.coinbase.android.idology.IdologyQuestionsListAdapter;
import com.coinbase.android.transfers.ContactsAutoCompleteAdapter;
import com.coinbase.android.ui.MystiqueBottomNavigationAdapter;

@ActivityScope
public interface AdapterSubcomponent {
    void inject(BillingAddressListAdapter billingAddressListAdapter);

    void inject(IdologyQuestionsListAdapter idologyQuestionsListAdapter);

    void inject(ContactsAutoCompleteAdapter contactsAutoCompleteAdapter);

    void inject(MystiqueBottomNavigationAdapter mystiqueBottomNavigationAdapter);
}
