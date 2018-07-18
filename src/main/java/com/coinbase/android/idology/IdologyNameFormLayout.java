package com.coinbase.android.idology;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutIdologyNameFormBinding;
import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.models.idology.Data;
import javax.inject.Inject;

@ControllerScope
public class IdologyNameFormLayout extends RelativeLayout implements IdologyNameFormScreen {
    private Activity mActivity;
    @Inject
    BackgroundDimmer mBackgroundDimmer;
    private LayoutIdologyNameFormBinding mBinding;
    private Context mContext;
    private OnFocusChangeListener mEditTextFocusListener;
    private TextWatcher mEditTextWatcher;
    private String mIdologyTrackingContext;
    @Inject
    IdologyNameFormPresenter mPresenter;

    public IdologyNameFormLayout(Context context) {
        this(context, null);
    }

    public IdologyNameFormLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IdologyNameFormLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mEditTextWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                IdologyNameFormLayout.this.mPresenter.onFormUpdated();
            }
        };
        this.mEditTextFocusListener = new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    IdologyNameFormLayout.this.mPresenter.onFocusRequested(view.getId());
                }
            }
        };
        init(context, attrs);
    }

    private void setAttributes(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.IdologyNameFormLayout, 0, 0);
            try {
                this.mIdologyTrackingContext = a.getString(0);
            } finally {
                a.recycle();
            }
        }
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        this.mActivity = (Activity) context;
        this.mBinding = LayoutIdologyNameFormBinding.inflate(LayoutInflater.from(this.mContext), this, true);
        setAttributes(context, attrs);
        ((MainActivitySubcomponentProvider) this.mContext).mainActivitySubcomponent().addIdologyNameFormLayoutSubcomponent(new IdologyNameFormPresenterModule(this, this.mIdologyTrackingContext)).inject(this);
        setListeners();
        this.mPresenter.init();
        this.mBinding.etFirstName.requestFocus();
        Utils.postShowKeyboardFrom(this.mActivity, this.mBinding.etFirstName);
    }

    public void onShow() {
        this.mPresenter.onShow();
    }

    public void onHide() {
        this.mPresenter.onHide();
    }

    public void onContinueClicked() {
        this.mPresenter.onContinueClicked();
    }

    public void onFormUpdated() {
        this.mPresenter.onFormUpdated();
    }

    public void setData(Data idology) {
        this.mPresenter.setData(idology);
    }

    public boolean hideVisibleLayout() {
        return this.mPresenter.hideVisibleLayout();
    }

    private void setListeners() {
        this.mBinding.etFirstName.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etLastName.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etSSN.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etFirstName.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etLastName.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etSSN.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etDob.setOnClickListener(IdologyNameFormLayout$$Lambda$1.lambdaFactory$(this));
        this.mBinding.etLastName.setOnEditorActionListener(IdologyNameFormLayout$$Lambda$2.lambdaFactory$(this));
        this.mBinding.etSSN.setOnEditorActionListener(IdologyNameFormLayout$$Lambda$3.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setListeners$0(IdologyNameFormLayout this_, View event) {
        this_.hideKeyboard();
        this_.mPresenter.onFocusRequested(this_.mBinding.etDob.getId());
        this_.mPresenter.onDobClicked();
    }

    static /* synthetic */ boolean lambda$setListeners$1(IdologyNameFormLayout this_, TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId != 5) {
            return false;
        }
        this_.mBinding.etSSN.requestFocus();
        return true;
    }

    static /* synthetic */ boolean lambda$setListeners$2(IdologyNameFormLayout this_, TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId != 6) {
            return false;
        }
        this_.mPresenter.onContinueClicked();
        return true;
    }

    public void setDobText(String dobText) {
        this.mBinding.etDob.setText(dobText);
        this.mPresenter.onFormUpdated();
        this.mBinding.etSSN.requestFocus();
        Utils.postShowKeyboardFrom(this.mActivity, this.mBinding.etSSN);
    }

    public void showDatePickerLayout(int year, int month, int dateOfMonth) {
        this.mBinding.dobDatePicker.initDate(year, month, dateOfMonth);
        this.mBinding.dobDatePicker.setVisibility(0);
        this.mBackgroundDimmer.dim(this.mBinding.dobDatePicker, null, true, 17);
    }

    public void hideDatePickerLayout() {
        this.mBinding.dobDatePicker.setVisibility(8);
        this.mBackgroundDimmer.undim(null);
    }

    public boolean isDatePickerLayoutVisible() {
        return this.mBinding.dobDatePicker.getVisibility() == 0;
    }

    public String getFirstNameText() {
        return this.mBinding.etFirstName.getText().toString();
    }

    public String getLastNameText() {
        return this.mBinding.etLastName.getText().toString();
    }

    public String getDobText() {
        return this.mBinding.etDob.getText().toString();
    }

    public String getSsnText() {
        return this.mBinding.etSSN.getText().toString();
    }

    public void hideSsnText() {
        this.mBinding.tlSSN.setVisibility(8);
    }

    public void prefillName(String firstName, String lastName) {
        if (!TextUtils.isEmpty(firstName)) {
            this.mBinding.etFirstName.setText(firstName);
        }
        if (!TextUtils.isEmpty(lastName)) {
            this.mBinding.etLastName.setText(lastName);
        }
    }

    public void prefillSsnLast4(String ssnLast4) {
        if (!TextUtils.isEmpty(ssnLast4)) {
            this.mBinding.etSSN.setText(ssnLast4);
        }
    }

    public void showFirstNameFieldError(String message) {
        showFieldError(this.mBinding.tlFirstName, message);
    }

    public void showLastNameFieldError(String message) {
        showFieldError(this.mBinding.tlLastName, message);
    }

    public void showDobFieldError(String message) {
        showFieldError(this.mBinding.tlDob, message);
    }

    public void showSsnLast4FieldError(String message) {
        showFieldError(this.mBinding.tlSSN, message);
    }

    private void showFieldError(TextInputLayout tl, String message) {
        tl.setErrorEnabled(true);
        tl.setError(message);
    }

    private void hideKeyboard() {
        Utils.hideKeyboard(this.mActivity);
    }
}
