package com.coinbase.android.ui;

import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

final /* synthetic */ class DatePickerSpinnerLayout$$Lambda$3 implements OnDateChangedListener {
    private final DatePickerSpinnerLayout arg$1;

    private DatePickerSpinnerLayout$$Lambda$3(DatePickerSpinnerLayout datePickerSpinnerLayout) {
        this.arg$1 = datePickerSpinnerLayout;
    }

    public static OnDateChangedListener lambdaFactory$(DatePickerSpinnerLayout datePickerSpinnerLayout) {
        return new DatePickerSpinnerLayout$$Lambda$3(datePickerSpinnerLayout);
    }

    public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
        this.arg$1.mSelectedDate = this.arg$1.getDate(i, i2, i3);
    }
}
