package com.coinbase.android.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.databinding.LayoutIdologyDatePickerBinding;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;

@ActivityScope
public class DatePickerSpinnerLayout extends LinearLayout {
    private LayoutIdologyDatePickerBinding mBinding;
    @Inject
    DatePickerConnector mDatePickerConnector;
    private Date mSelectedDate;

    public DatePickerSpinnerLayout(Context context) {
        this(context, null);
    }

    public DatePickerSpinnerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DatePickerSpinnerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mBinding = LayoutIdologyDatePickerBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().inject(this);
        this.mBinding.btnAction.setOnClickListener(DatePickerSpinnerLayout$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btnClose.setOnClickListener(DatePickerSpinnerLayout$$Lambda$2.lambdaFactory$(this));
    }

    public void initDate(int defaultYear, int defaultMonth, int defaultDateOfMonth) {
        this.mSelectedDate = getDate(defaultYear, defaultMonth, defaultDateOfMonth);
        this.mBinding.datePicker.init(defaultYear, defaultMonth, defaultDateOfMonth, DatePickerSpinnerLayout$$Lambda$3.lambdaFactory$(this));
    }

    private Date getDate(int year, int month, int dateOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, year);
        calendar.set(2, month);
        calendar.set(5, dateOfMonth);
        return calendar.getTime();
    }
}
