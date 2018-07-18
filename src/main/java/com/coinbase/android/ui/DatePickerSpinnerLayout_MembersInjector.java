package com.coinbase.android.ui;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class DatePickerSpinnerLayout_MembersInjector implements MembersInjector<DatePickerSpinnerLayout> {
    private final Provider<DatePickerConnector> mDatePickerConnectorProvider;

    public DatePickerSpinnerLayout_MembersInjector(Provider<DatePickerConnector> mDatePickerConnectorProvider) {
        this.mDatePickerConnectorProvider = mDatePickerConnectorProvider;
    }

    public static MembersInjector<DatePickerSpinnerLayout> create(Provider<DatePickerConnector> mDatePickerConnectorProvider) {
        return new DatePickerSpinnerLayout_MembersInjector(mDatePickerConnectorProvider);
    }

    public void injectMembers(DatePickerSpinnerLayout instance) {
        injectMDatePickerConnector(instance, (DatePickerConnector) this.mDatePickerConnectorProvider.get());
    }

    public static void injectMDatePickerConnector(DatePickerSpinnerLayout instance, DatePickerConnector mDatePickerConnector) {
        instance.mDatePickerConnector = mDatePickerConnector;
    }
}
