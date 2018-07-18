package com.coinbase.android.idology;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.coinbase.android.databinding.LayoutIdologyAddressFormBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.ClearableTextInputLayoutWatcher;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.idology.Data;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter.Builder;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocomplete.IntentBuilder;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.functions.Func1;

@ControllerScope
public class IdologyAddressFormLayout extends RelativeLayout implements IdologyAddressFormScreen {
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private AppCompatActivity mActivity;
    private LayoutIdologyAddressFormBinding mBinding;
    private Context mContext;
    private Func1<String, Void> mContinueButtonTextCallback;
    private ActionBarController mController;
    private OnFocusChangeListener mEditTextFocusListener;
    private String mIdologyTrackingContext;
    private final Logger mLogger;
    @Inject
    LoginManager mLoginManager;
    @Inject
    IdologyAddressFormPresenter mPresenter;

    public IdologyAddressFormLayout(Context context) {
        this(context, null);
    }

    public IdologyAddressFormLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IdologyAddressFormLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mLogger = LoggerFactory.getLogger(IdologyAddressFormLayout.class);
        this.mEditTextFocusListener = new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    IdologyAddressFormLayout.this.mPresenter.onFocusRequested(view.getId());
                }
            }
        };
        init(context, attrs);
    }

    private void setAttributes(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.IdologyAddressFormLayout, 0, 0);
            try {
                this.mIdologyTrackingContext = a.getString(0);
            } finally {
                a.recycle();
            }
        }
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        this.mActivity = (AppCompatActivity) context;
        this.mBinding = LayoutIdologyAddressFormBinding.inflate(LayoutInflater.from(this.mContext), this, true);
        setAttributes(context, attrs);
        ((MainActivitySubcomponentProvider) this.mContext).mainActivitySubcomponent().addIdologyAddressFormLayoutSubcomponent(new IdologyAddressFormPresenterModule(this, this.mIdologyTrackingContext)).inject(this);
        setListeners();
        this.mBinding.etAutoComplete.setOnFocusChangeListener(IdologyAddressFormLayout$$Lambda$1.lambdaFactory$(this));
        this.mBinding.etAutoComplete.setOnClickListener(IdologyAddressFormLayout$$Lambda$2.lambdaFactory$(this));
        this.mBinding.tvEnterManually.setOnClickListener(IdologyAddressFormLayout$$Lambda$3.lambdaFactory$(this));
        this.mBinding.etZip.setOnEditorActionListener(IdologyAddressFormLayout$$Lambda$4.lambdaFactory$(this));
        this.mBinding.etAddressLine1.requestFocus();
        Utils.postShowKeyboardFrom(this.mActivity, this.mBinding.etAddressLine1);
    }

    static /* synthetic */ void lambda$init$1(IdologyAddressFormLayout this_, View v, boolean focused) {
        if (focused) {
            this_.mBinding.etAutoComplete.setOnFocusChangeListener(IdologyAddressFormLayout$$Lambda$11.lambdaFactory$());
            this_.mPresenter.onAutoCompleteStarted();
        }
    }

    static /* synthetic */ void lambda$null$0(View v2, boolean focused2) {
    }

    static /* synthetic */ boolean lambda$init$4(IdologyAddressFormLayout this_, TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId != 6) {
            return false;
        }
        this_.mPresenter.onContinueClicked();
        return true;
    }

    public void setController(ActionBarController callingController) {
        this.mController = callingController;
    }

    public void setContinueButtonTextCallback(Func1<String, Void> func1) {
        this.mContinueButtonTextCallback = func1;
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 1) {
            return false;
        }
        switch (resultCode) {
            case -1:
                this.mPresenter.onAutoCompleteSelected(PlaceAutocomplete.getPlace(this.mContext, data));
                return true;
            case 2:
                Status status = PlaceAutocomplete.getStatus(this.mContext, data);
                this.mPresenter.onAutoCompleteError();
                return true;
            default:
                return true;
        }
    }

    public void showAddressAutoComplete() {
        Exception e;
        try {
            Builder autoCompleteFilter = new Builder().setTypeFilter(2);
            autoCompleteFilter.setCountry(this.mLoginManager.getActiveUserCountryCode());
            this.mController.startActivityForResult(new IntentBuilder(2).setFilter(autoCompleteFilter.build()).build(this.mActivity), 1);
            return;
        } catch (GooglePlayServicesRepairableException e2) {
            e = e2;
        } catch (GooglePlayServicesNotAvailableException e3) {
            e = e3;
        }
        this.mLogger.error("Error getting places", e);
        this.mPresenter.onAutoCompleteError();
    }

    public void onShow(Bundle controllerArgs) {
        this.mPresenter.onShow(controllerArgs);
    }

    public void onHide() {
        this.mPresenter.onHide();
    }

    public boolean onBackPressed() {
        return this.mPresenter.onBackPressed();
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

    private void setListeners() {
        this.mBinding.etAddressLine1.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etAddressLine2.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etCity.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etState.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etZip.setOnFocusChangeListener(this.mEditTextFocusListener);
        this.mBinding.etAddressLine1.addTextChangedListener(new ClearableTextInputLayoutWatcher(this.mBinding.tlAddressLine1, IdologyAddressFormLayout$$Lambda$5.lambdaFactory$(this)));
        this.mBinding.etAddressLine2.addTextChangedListener(new ClearableTextInputLayoutWatcher(this.mBinding.tlAddressLine2, IdologyAddressFormLayout$$Lambda$6.lambdaFactory$()));
        this.mBinding.etCity.addTextChangedListener(new ClearableTextInputLayoutWatcher(this.mBinding.tlCity, IdologyAddressFormLayout$$Lambda$7.lambdaFactory$(this)));
        this.mBinding.etState.addTextChangedListener(new ClearableTextInputLayoutWatcher(this.mBinding.tlState, IdologyAddressFormLayout$$Lambda$8.lambdaFactory$(this)));
        this.mBinding.etZip.addTextChangedListener(new ClearableTextInputLayoutWatcher(this.mBinding.tlZip, IdologyAddressFormLayout$$Lambda$9.lambdaFactory$(this)));
        this.mBinding.etZip.setOnEditorActionListener(IdologyAddressFormLayout$$Lambda$10.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setListeners$6() {
    }

    static /* synthetic */ boolean lambda$setListeners$10(IdologyAddressFormLayout this_, TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == 6) {
            this_.hideKeyboard();
        }
        return false;
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

    public void showPostalCode() {
        this.mBinding.etZip.setHint(R.string.postal_code);
        this.mBinding.etZip.setInputType(112);
    }

    public String getZipText() {
        return this.mBinding.etZip.getText().toString();
    }

    public void prefillAddress(String line1, String line2, String city, String state, String postalCode) {
        this.mBinding.etAddressLine1.setText(line1);
        this.mBinding.etAddressLine2.setText(line2);
        this.mBinding.etCity.setText(city);
        this.mBinding.etState.setText(state);
        this.mBinding.etZip.setText(postalCode);
    }

    public void showAutoCompleteView() {
        this.mBinding.flAutoCompleteContainer.setVisibility(0);
        this.mBinding.progress.setVisibility(8);
        this.mBinding.svManualEntry.setVisibility(8);
    }

    public void showManualEntryView() {
        this.mBinding.flAutoCompleteContainer.setVisibility(8);
        this.mBinding.progress.setVisibility(8);
        this.mBinding.svManualEntry.setVisibility(0);
    }

    public void showProgressView() {
        this.mBinding.flAutoCompleteContainer.setVisibility(8);
        this.mBinding.progress.setVisibility(0);
        this.mBinding.svManualEntry.setVisibility(8);
    }

    public void setContinueButtonText(String text) {
        if (this.mContinueButtonTextCallback != null) {
            this.mContinueButtonTextCallback.call(text);
        }
    }

    public void showAddress1FieldError(String message) {
        this.mBinding.tlAddressLine1.setErrorEnabled(true);
        this.mBinding.tlAddressLine1.setError(message);
    }

    public void showAddress2FieldError(String message) {
        this.mBinding.tlAddressLine2.setErrorEnabled(true);
        this.mBinding.tlAddressLine2.setError(message);
    }

    public void showCityFieldError(String message) {
        this.mBinding.tlCity.setErrorEnabled(true);
        this.mBinding.tlCity.setError(message);
    }

    public void showStateFieldError(String message) {
        this.mBinding.tlState.setErrorEnabled(true);
        this.mBinding.tlState.setError(message);
    }

    public void showZipFieldError(String message) {
        this.mBinding.tlZip.setErrorEnabled(true);
        this.mBinding.tlZip.setError(message);
    }

    private void hideKeyboard() {
        Utils.hideKeyboard(this.mActivity);
    }
}
