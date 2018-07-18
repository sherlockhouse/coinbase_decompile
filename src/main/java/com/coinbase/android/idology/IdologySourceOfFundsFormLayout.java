package com.coinbase.android.idology;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutIdologySourceOfFundsFormBinding;
import com.coinbase.android.signin.IdologyOptionDialogFragment;
import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.android.ui.ClearableTextInputLayoutWatcher;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.models.idology.Data;
import com.coinbase.api.internal.models.idology.options.IdologyOptions;
import com.coinbase.api.internal.models.idology.options.IdologyOptions.OptionType;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import javax.inject.Inject;

@ControllerScope
public class IdologySourceOfFundsFormLayout extends RelativeLayout implements IdologySourceOfFundsFormScreen {
    private Activity mActivity;
    @Inject
    BackgroundDimmer mBackgroundDimmer;
    private LayoutIdologySourceOfFundsFormBinding mBinding;
    private Context mContext;
    private OnFocusChangeListener mEditTextFocusListener;
    private TextWatcher mEditTextWatcher;
    private String mIdologyTrackingContext;
    @Inject
    IdologySourceOfFundsFormPresenter mPresenter;

    public IdologySourceOfFundsFormLayout(Context context) {
        this(context, null);
    }

    public IdologySourceOfFundsFormLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IdologySourceOfFundsFormLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mEditTextWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                IdologySourceOfFundsFormLayout.this.mPresenter.onFormUpdated();
            }
        };
        this.mEditTextFocusListener = new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    IdologySourceOfFundsFormLayout.this.mPresenter.onFocusRequested(view.getId());
                }
            }
        };
        init(context, attrs);
    }

    private void setAttributes(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.IdologySourceOfFundsFormLayout, 0, 0);
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
        this.mBinding = LayoutIdologySourceOfFundsFormBinding.inflate(LayoutInflater.from(this.mContext), this, true);
        setAttributes(context, attrs);
        ((MainActivitySubcomponentProvider) this.mContext).mainActivitySubcomponent().addIdologySourceOfFundsFormLayoutSubcomponent(new IdologySourceOfFundsFormPresenterModule(this, this.mIdologyTrackingContext)).inject(this);
        setListeners();
        this.mPresenter.init();
        Utils.postShowKeyboardFrom(this.mActivity, this.mBinding.etEmployer);
    }

    public void onShow() {
        this.mPresenter.onShow();
    }

    public void onHide() {
        this.mPresenter.onHide();
    }

    public void submitForm(Data buildingData) {
        this.mPresenter.submitForm(buildingData);
    }

    public void onFormUpdated() {
        this.mPresenter.onFormUpdated();
    }

    public void setData(Data idology) {
        this.mPresenter.setData(idology);
    }

    private void setListeners() {
        this.mBinding.etEmployer.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etCoinbaseUses.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etSourceOfFunds.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etJobTitle.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etEmployer.addTextChangedListener(new ClearableTextInputLayoutWatcher(this.mBinding.tlEmployer, IdologySourceOfFundsFormLayout$$Lambda$1.lambdaFactory$(this)));
        this.mBinding.etCoinbaseUses.setOnClickListener(IdologySourceOfFundsFormLayout$$Lambda$2.lambdaFactory$(this));
        this.mBinding.etSourceOfFunds.setOnClickListener(IdologySourceOfFundsFormLayout$$Lambda$3.lambdaFactory$(this));
        this.mBinding.etJobTitle.setOnClickListener(IdologySourceOfFundsFormLayout$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setListeners$1(IdologySourceOfFundsFormLayout this_, View event) {
        this_.hideKeyboard();
        this_.mPresenter.onFocusRequested(this_.mBinding.etCoinbaseUses.getId());
        this_.mPresenter.onCoinbaseUsesClicked();
    }

    static /* synthetic */ void lambda$setListeners$2(IdologySourceOfFundsFormLayout this_, View event) {
        this_.hideKeyboard();
        this_.mPresenter.onFocusRequested(this_.mBinding.etSourceOfFunds.getId());
        this_.mPresenter.onSourceOfFundsClicked();
    }

    static /* synthetic */ void lambda$setListeners$3(IdologySourceOfFundsFormLayout this_, View event) {
        this_.hideKeyboard();
        this_.mPresenter.onFocusRequested(this_.mBinding.etJobTitle.getId());
        this_.mPresenter.onJobTitleClicked();
    }

    public void setCoinbaseUsesText(String text) {
        this.mBinding.etCoinbaseUses.setText(text);
    }

    public void setSourceOfFundsText(String text) {
        this.mBinding.etSourceOfFunds.setText(text);
    }

    public void setJobTitleText(String text) {
        this.mBinding.etJobTitle.setText(text);
        this.mBinding.etEmployer.requestFocus();
        Utils.postShowKeyboardFrom(this.mActivity, this.mBinding.etEmployer);
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

    public void prefillCurrentEmployer(String employer) {
        this.mBinding.etEmployer.setText(employer);
    }

    public void showEmployerFieldError(String error) {
        this.mBinding.tlEmployer.setError(error);
        this.mBinding.tlEmployer.setErrorEnabled(true);
    }

    public EditText getLastEditText() {
        return this.mBinding.etEmployer;
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
