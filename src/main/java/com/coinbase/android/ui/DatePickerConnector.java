package com.coinbase.android.ui;

import android.util.Pair;
import java.util.Date;
import rx.subjects.PublishSubject;

public class DatePickerConnector {
    private final PublishSubject<Pair<DatePickerButton, Date>> mSubject;

    public enum DatePickerButton {
        OK,
        CANCEL
    }

    public DatePickerConnector() {
        this(PublishSubject.create());
    }

    public DatePickerConnector(PublishSubject<Pair<DatePickerButton, Date>> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Pair<DatePickerButton, Date>> get() {
        return this.mSubject;
    }
}
