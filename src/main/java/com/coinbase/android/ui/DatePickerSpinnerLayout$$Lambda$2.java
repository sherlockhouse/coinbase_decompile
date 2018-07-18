package com.coinbase.android.ui;

import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.android.ui.DatePickerConnector.DatePickerButton;

final /* synthetic */ class DatePickerSpinnerLayout$$Lambda$2 implements OnClickListener {
    private final DatePickerSpinnerLayout arg$1;

    private DatePickerSpinnerLayout$$Lambda$2(DatePickerSpinnerLayout datePickerSpinnerLayout) {
        this.arg$1 = datePickerSpinnerLayout;
    }

    public static OnClickListener lambdaFactory$(DatePickerSpinnerLayout datePickerSpinnerLayout) {
        return new DatePickerSpinnerLayout$$Lambda$2(datePickerSpinnerLayout);
    }

    public void onClick(View view) {
        this.arg$1.mDatePickerConnector.get().onNext(new Pair(DatePickerButton.CANCEL, null));
    }
}
