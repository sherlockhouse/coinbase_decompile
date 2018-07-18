package com.coinbase.android.idology;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutIdologyFormBinding;
import com.coinbase.android.signin.IdologyOptionDialogFragment;
import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.models.idology.Data;
import com.coinbase.api.internal.models.idology.options.IdologyOptions;
import com.coinbase.api.internal.models.idology.options.IdologyOptions.OptionType;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import javax.inject.Inject;

@ControllerScope
public class IdologyFormLayout extends RelativeLayout implements IdologyFormScreen {
    private Activity mActivity;
    @Inject
    BackgroundDimmer mBackgroundDimmer;
    private LayoutIdologyFormBinding mBinding;
    private Context mContext;
    private OnFocusChangeListener mEditTextFocusListener;
    private TextWatcher mEditTextWatcher;
    private String mIdologyTrackingContext;
    @Inject
    IdologyFormPresenter mPresenter;

    public IdologyFormLayout(Context context) {
        this(context, null);
    }

    public IdologyFormLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IdologyFormLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mEditTextWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                IdologyFormLayout.this.mPresenter.onFormUpdated();
            }
        };
        this.mEditTextFocusListener = new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    IdologyFormLayout.this.mPresenter.onFocusRequested(view.getId());
                }
            }
        };
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        this.mActivity = (Activity) context;
        this.mBinding = LayoutIdologyFormBinding.inflate(LayoutInflater.from(this.mContext), this, true);
        setAttributes(context, attrs);
        ((MainActivitySubcomponentProvider) this.mContext).mainActivitySubcomponent().addIdologyFormLayoutSubcomponent(new IdologyFormPresenterModule(this, this.mIdologyTrackingContext)).inject(this);
        setHeaderText();
        setListeners();
        this.mPresenter.init();
        Utils.postShowKeyboardFrom(this.mActivity, this.mBinding.etFirstName);
    }

    private void setAttributes(Context context, AttributeSet attrs) {
        int i = 0;
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.IdologyFormLayout, 0, 0);
            try {
                boolean showHeaderText = a.getBoolean(1, false);
                TextView textView = this.mBinding.tvHeader;
                if (!showHeaderText) {
                    i = 8;
                }
                textView.setVisibility(i);
                this.mIdologyTrackingContext = a.getString(0);
            } finally {
                a.recycle();
            }
        }
    }

    public void onShow() {
        this.mPresenter.onShow();
    }

    public void onHide() {
        this.mPresenter.onHide();
    }

    public void submitForm() {
        this.mPresenter.submitForm();
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
        this.mBinding.etAddressLine1.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etAddressLine2.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etCity.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etState.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etZip.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etSSN.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etEmployer.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etFirstName.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etLastName.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etAddressLine1.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etCity.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etState.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etZip.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etSSN.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etCoinbaseUses.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etSourceOfFunds.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etJobTitle.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etEmployer.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etDob.setOnClickListener(IdologyFormLayout$$Lambda$1.lambdaFactory$(this));
        this.mBinding.etCoinbaseUses.setOnClickListener(IdologyFormLayout$$Lambda$2.lambdaFactory$(this));
        this.mBinding.etSourceOfFunds.setOnClickListener(IdologyFormLayout$$Lambda$3.lambdaFactory$(this));
        this.mBinding.etJobTitle.setOnClickListener(IdologyFormLayout$$Lambda$4.lambdaFactory$(this));
        this.mBinding.etLastName.setOnEditorActionListener(IdologyFormLayout$$Lambda$5.lambdaFactory$(this));
        this.mBinding.etSSN.setOnEditorActionListener(IdologyFormLayout$$Lambda$6.lambdaFactory$(this));
        this.mBinding.etEmployer.setOnEditorActionListener(IdologyFormLayout$$Lambda$7.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setListeners$0(IdologyFormLayout this_, View event) {
        this_.hideKeyboard();
        this_.mPresenter.onFocusRequested(this_.mBinding.etDob.getId());
        this_.mPresenter.onDobClicked();
    }

    static /* synthetic */ void lambda$setListeners$1(IdologyFormLayout this_, View event) {
        this_.hideKeyboard();
        this_.mPresenter.onFocusRequested(this_.mBinding.etCoinbaseUses.getId());
        this_.mPresenter.onCoinbaseUsesClicked();
    }

    static /* synthetic */ void lambda$setListeners$2(IdologyFormLayout this_, View event) {
        this_.hideKeyboard();
        this_.mPresenter.onFocusRequested(this_.mBinding.etSourceOfFunds.getId());
        this_.mPresenter.onSourceOfFundsClicked();
    }

    static /* synthetic */ void lambda$setListeners$3(IdologyFormLayout this_, View event) {
        this_.hideKeyboard();
        this_.mPresenter.onFocusRequested(this_.mBinding.etJobTitle.getId());
        this_.mPresenter.onJobTitleClicked();
    }

    static /* synthetic */ boolean lambda$setListeners$4(IdologyFormLayout this_, TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId != 5) {
            return false;
        }
        this_.mBinding.etAddressLine1.requestFocus();
        this_.hideKeyboard();
        return true;
    }

    static /* synthetic */ boolean lambda$setListeners$5(IdologyFormLayout this_, TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId != 5) {
            return false;
        }
        this_.mBinding.etEmployer.requestFocus();
        this_.hideKeyboard();
        return true;
    }

    static /* synthetic */ boolean lambda$setListeners$6(IdologyFormLayout this_, TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == 6) {
            this_.hideKeyboard();
        }
        return false;
    }

    public void setDobText(String dobText, boolean preFill) {
        this.mBinding.etDob.setText(dobText);
        this.mPresenter.onFormUpdated();
        if (!preFill) {
            this.mBinding.etAddressLine1.requestFocus();
        }
        Utils.postShowKeyboardFrom(this.mActivity, this.mBinding.etAddressLine1);
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

    public void setCoinbaseUsesText(String text) {
        this.mBinding.etCoinbaseUses.setText(text);
    }

    public void setSourceOfFundsText(String text) {
        this.mBinding.etSourceOfFunds.setText(text);
    }

    public void setJobTitleText(String text, boolean prefill) {
        this.mBinding.etJobTitle.setText(text);
        if (!prefill) {
            this.mBinding.etEmployer.requestFocus();
        }
        Utils.postShowKeyboardFrom(this.mActivity, this.mBinding.etEmployer);
    }

    public String getFirstNameText() {
        return this.mBinding.etFirstName.getText().toString();
    }

    public String getLastNameText() {
        return this.mBinding.etLastName.getText().toString();
    }

    public String getAddressLine1Text() {
        return this.mBinding.etAddressLine1.getText().toString();
    }

    public String getAddressLine2Text() {
        return this.mBinding.etAddressLine2.getText().toString();
    }

    public String getCityText() {
        return this.mBinding.etCity.getText().toString();
    }

    public String getStateText() {
        return this.mBinding.etState.getText().toString();
    }

    public void hideState() {
        this.mBinding.tlState.setVisibility(8);
    }

    public String getZipText() {
        return this.mBinding.etZip.getText().toString();
    }

    public void showPostalCode() {
        this.mBinding.etZip.setHint(R.string.postal_code);
    }

    public String getDobText() {
        return this.mBinding.etDob.getText().toString();
    }

    public String getSsnText() {
        return this.mBinding.etSSN.getText().toString();
    }

    public void hideSsn() {
        this.mBinding.tlSSN.setVisibility(8);
    }

    public String getCurrentEmployerText() {
        return this.mBinding.etEmployer.getText().toString();
    }

    public void setOptionsDialog(IdologyOptions idologyOptions, OptionType optionType) {
        if (idologyOptions != null) {
            FragmentManager fragmentManager = getCallerFragmentManager();
            if (fragmentManager == null) {
                this.mPresenter.showGenericError();
                return;
            }
            String json = new Gson().toJson((Object) idologyOptions, (Type) IdologyOptions.class);
            DialogFragment dialogFragment = new IdologyOptionDialogFragment();
            Bundle args = new Bundle();
            args.putString(IdologyOptionDialogFragment.IDOLOGY_DATA, json);
            args.putSerializable(IdologyOptionDialogFragment.IDOLOGY_OPTION_TYPE, optionType);
            dialogFragment.setArguments(args);
            dialogFragment.show(fragmentManager, "idology_options");
        }
    }

    public void prefillName(String firstName, String lastName) {
        if (!TextUtils.isEmpty(firstName)) {
            this.mBinding.etFirstName.setText(firstName);
        }
        if (!TextUtils.isEmpty(lastName)) {
            this.mBinding.etLastName.setText(lastName);
        }
    }

    public void prefillAddress(String line1, String line2, String city, String state, String postalCode) {
        this.mBinding.etAddressLine1.setText(line1);
        this.mBinding.etAddressLine2.setText(line2);
        this.mBinding.etCity.setText(city);
        this.mBinding.etState.setText(state);
        this.mBinding.etZip.setText(postalCode);
    }

    public void prefillSsnLast4(String ssnLast4) {
        this.mBinding.etSSN.setText(ssnLast4);
    }

    public void prefillCurrentEmployer(String employer) {
        this.mBinding.etEmployer.setText(employer);
    }

    public void displaySupportUrl(Uri supportUri) {
        if (supportUri != null) {
            this.mContext.startActivity(new Intent("android.intent.action.VIEW", supportUri));
        }
    }

    public EditText getLastEditText() {
        return this.mBinding.etEmployer;
    }

    private void setHeaderText() {
        String supportUrlLink = this.mContext.getString(R.string.idology_linked_accounts_header_support_text);
        String supportText = this.mContext.getString(R.string.idology_linked_accounts_header, new Object[]{supportUrlLink});
        ClickableSpan clickableSpan = new ClickableSpan() {
            public void onClick(View view) {
                IdologyFormLayout.this.mPresenter.onSupportHeaderClicked();
            }

            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        int supportTextLinkIndex = supportText.indexOf(supportUrlLink);
        SpannableString ss = new SpannableString(supportText);
        ss.setSpan(clickableSpan, supportTextLinkIndex, supportUrlLink.length() + supportTextLinkIndex, 18);
        ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.primary_mystique_text_color)), supportTextLinkIndex, supportUrlLink.length() + supportTextLinkIndex, 18);
        this.mBinding.tvHeader.setText(ss);
        this.mBinding.tvHeader.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private FragmentManager getCallerFragmentManager() {
        if (this.mContext instanceof AppCompatActivity) {
            return ((AppCompatActivity) this.mContext).getSupportFragmentManager();
        }
        return null;
    }

    private void hideKeyboard() {
        Utils.hideKeyboard(this.mActivity);
    }
}
