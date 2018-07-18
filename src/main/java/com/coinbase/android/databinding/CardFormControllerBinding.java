package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingListener;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.OnTextChanged;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.paymentmethods.card.CardFormPresenter;
import com.coinbase.android.ui.MaterialProgressBar;
import com.coinbase.android.ui.MystiqueListSelectorLayout;
import com.coinbase.android.utils.BindableString;

public class CardFormControllerBinding extends ViewDataBinding implements Listener {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(36);
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final MystiqueListSelectorLayout billingAddressListSelectorLayout;
    public final Button btnContinue;
    public final MaterialProgressBar btnProgress;
    public final PlaidLoginAllDoneBinding cardFormAllDone;
    public final TextInputLayout cvBillingContainer;
    public final Toolbar cvCoinbaseToolbar;
    public final TextInputLayout cvZipContainer;
    public final EditText etBilling;
    private InverseBindingListener etBillingandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(CardFormControllerBinding.this.etBilling);
            CardFormPresenter presenter = CardFormControllerBinding.this.mPresenter;
            if (presenter != null) {
                boolean presenterMBillingJavaLangObjectNull;
                BindableString presenterMBilling = presenter.mBilling;
                if (presenterMBilling != null) {
                    presenterMBillingJavaLangObjectNull = true;
                } else {
                    presenterMBillingJavaLangObjectNull = false;
                }
                if (presenterMBillingJavaLangObjectNull) {
                    presenterMBilling.setValue(callbackArg_0);
                }
            }
        }
    };
    public final EditText etCardNumber;
    private InverseBindingListener etCardNumberandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(CardFormControllerBinding.this.etCardNumber);
            CardFormPresenter presenter = CardFormControllerBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mCardNumber = callbackArg_0;
            }
        }
    };
    public final EditText etCvv;
    private InverseBindingListener etCvvandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(CardFormControllerBinding.this.etCvv);
            CardFormPresenter presenter = CardFormControllerBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mCvv = callbackArg_0;
            }
        }
    };
    public final EditText etExpiry;
    private InverseBindingListener etExpiryandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(CardFormControllerBinding.this.etExpiry);
            CardFormPresenter presenter = CardFormControllerBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mExpiry = callbackArg_0;
            }
        }
    };
    public final EditText etFullName;
    private InverseBindingListener etFullNameandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(CardFormControllerBinding.this.etFullName);
            CardFormPresenter presenter = CardFormControllerBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mFullName = callbackArg_0;
            }
        }
    };
    public final EditText etZip;
    private InverseBindingListener etZipandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(CardFormControllerBinding.this.etZip);
            CardFormPresenter presenter = CardFormControllerBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mZip = callbackArg_0;
            }
        }
    };
    public final FrameLayout flContainer;
    public final TextInputLayout fullNameTextInputLayout;
    public final ImageView ivCardNumberRight;
    public final ImageView ivCvvRight;
    public final ImageView ivExpiryRight;
    public final ImageView ivFullNameRight;
    public final ImageView ivMastercardProcessingIcon;
    public final LinearLayout llBottomFields;
    public final LinearLayout llButtonContainer;
    public final LinearLayout llCardCvvHelp;
    public final LinearLayout llCardExpiryHelp;
    public final LinearLayout llCardNumberHelp;
    private final OnClickListener mCallback3;
    private final OnClickListener mCallback4;
    private final OnClickListener mCallback5;
    private final OnClickListener mCallback6;
    private final OnClickListener mCallback7;
    private long mDirtyFlags = -1;
    private CardFormPresenter mPresenter;
    private final LinearLayout mboundView0;
    private final ImageView mboundView10;
    private final ImageView mboundView11;
    private final ImageView mboundView9;
    public final RelativeLayout relativeLayout;
    public final RelativeLayout rlForm;
    public final RelativeLayout rlWorldPay;
    public final ScrollView svBillingContainer;
    public final TextInputLayout tilExpiry;
    public final TextView tvWorldPayProcessingHeader;
    public final TextView tvWorldPayProcessingText;

    static {
        sIncludes.setIncludes(1, new String[]{"plaid_login_all_done"}, new int[]{12}, new int[]{R.layout.plaid_login_all_done});
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 13);
        sViewsWithIds.put(R.id.rlForm, 14);
        sViewsWithIds.put(R.id.svBillingContainer, 15);
        sViewsWithIds.put(R.id.relativeLayout, 16);
        sViewsWithIds.put(R.id.ivCardNumberRight, 17);
        sViewsWithIds.put(R.id.llBottomFields, 18);
        sViewsWithIds.put(R.id.tilExpiry, 19);
        sViewsWithIds.put(R.id.ivExpiryRight, 20);
        sViewsWithIds.put(R.id.ivCvvRight, 21);
        sViewsWithIds.put(R.id.cvZipContainer, 22);
        sViewsWithIds.put(R.id.full_name_text_input_layout, 23);
        sViewsWithIds.put(R.id.ivFullNameRight, 24);
        sViewsWithIds.put(R.id.cvBillingContainer, 25);
        sViewsWithIds.put(R.id.llButtonContainer, 26);
        sViewsWithIds.put(R.id.rlWorldPay, 27);
        sViewsWithIds.put(R.id.ivMastercardProcessingIcon, 28);
        sViewsWithIds.put(R.id.tvWorldPayProcessingHeader, 29);
        sViewsWithIds.put(R.id.tvWorldPayProcessingText, 30);
        sViewsWithIds.put(R.id.btnProgress, 31);
        sViewsWithIds.put(R.id.llCardExpiryHelp, 32);
        sViewsWithIds.put(R.id.llCardCvvHelp, 33);
        sViewsWithIds.put(R.id.llCardNumberHelp, 34);
        sViewsWithIds.put(R.id.billingAddressListSelectorLayout, 35);
    }

    public CardFormControllerBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 2);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 36, sIncludes, sViewsWithIds);
        this.billingAddressListSelectorLayout = (MystiqueListSelectorLayout) bindings[35];
        this.btnContinue = (Button) bindings[8];
        this.btnContinue.setTag(null);
        this.btnProgress = (MaterialProgressBar) bindings[31];
        this.cardFormAllDone = (PlaidLoginAllDoneBinding) bindings[12];
        setContainedBinding(this.cardFormAllDone);
        this.cvBillingContainer = (TextInputLayout) bindings[25];
        this.cvCoinbaseToolbar = (Toolbar) bindings[13];
        this.cvZipContainer = (TextInputLayout) bindings[22];
        this.etBilling = (EditText) bindings[7];
        this.etBilling.setTag(null);
        this.etCardNumber = (EditText) bindings[2];
        this.etCardNumber.setTag(null);
        this.etCvv = (EditText) bindings[4];
        this.etCvv.setTag(null);
        this.etExpiry = (EditText) bindings[3];
        this.etExpiry.setTag(null);
        this.etFullName = (EditText) bindings[6];
        this.etFullName.setTag(null);
        this.etZip = (EditText) bindings[5];
        this.etZip.setTag(null);
        this.flContainer = (FrameLayout) bindings[1];
        this.flContainer.setTag(null);
        this.fullNameTextInputLayout = (TextInputLayout) bindings[23];
        this.ivCardNumberRight = (ImageView) bindings[17];
        this.ivCvvRight = (ImageView) bindings[21];
        this.ivExpiryRight = (ImageView) bindings[20];
        this.ivFullNameRight = (ImageView) bindings[24];
        this.ivMastercardProcessingIcon = (ImageView) bindings[28];
        this.llBottomFields = (LinearLayout) bindings[18];
        this.llButtonContainer = (LinearLayout) bindings[26];
        this.llCardCvvHelp = (LinearLayout) bindings[33];
        this.llCardExpiryHelp = (LinearLayout) bindings[32];
        this.llCardNumberHelp = (LinearLayout) bindings[34];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView10 = (ImageView) bindings[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (ImageView) bindings[11];
        this.mboundView11.setTag(null);
        this.mboundView9 = (ImageView) bindings[9];
        this.mboundView9.setTag(null);
        this.relativeLayout = (RelativeLayout) bindings[16];
        this.rlForm = (RelativeLayout) bindings[14];
        this.rlWorldPay = (RelativeLayout) bindings[27];
        this.svBillingContainer = (ScrollView) bindings[15];
        this.tilExpiry = (TextInputLayout) bindings[19];
        this.tvWorldPayProcessingHeader = (TextView) bindings[29];
        this.tvWorldPayProcessingText = (TextView) bindings[30];
        setRootTag(root);
        this.mCallback7 = new android.databinding.generated.callback.OnClickListener(this, 5);
        this.mCallback6 = new android.databinding.generated.callback.OnClickListener(this, 4);
        this.mCallback5 = new android.databinding.generated.callback.OnClickListener(this, 3);
        this.mCallback4 = new android.databinding.generated.callback.OnClickListener(this, 2);
        this.mCallback3 = new android.databinding.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
        }
        this.cardFormAllDone.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
        }
    }

    public boolean setVariable(int variableId, Object variable) {
        switch (variableId) {
            case 1:
                setPresenter((CardFormPresenter) variable);
                return true;
            default:
                return false;
        }
    }

    public void setPresenter(CardFormPresenter Presenter) {
        this.mPresenter = Presenter;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    public CardFormPresenter getPresenter() {
        return this.mPresenter;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeCardFormAllDone((PlaidLoginAllDoneBinding) object, fieldId);
            case 1:
                return onChangePresenterMBilling((BindableString) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeCardFormAllDone(PlaidLoginAllDoneBinding CardFormAllDone, int fieldId) {
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            default:
                return false;
        }
    }

    private boolean onChangePresenterMBilling(BindableString PresenterMBilling, int fieldId) {
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 2;
                }
                return true;
            case 2:
                synchronized (this) {
                    this.mDirtyFlags |= 8;
                }
                return true;
            default:
                return false;
        }
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String presenterMCvv = null;
        String presenterMZip = null;
        CardFormPresenter presenter = this.mPresenter;
        String presenterMCardNumber = null;
        BindableString presenterMBilling = null;
        String presenterMFullName = null;
        String presenterMExpiry = null;
        String presenterMBillingValue = null;
        if ((30 & dirtyFlags) != 0) {
            if (!((20 & dirtyFlags) == 0 || presenter == null)) {
                presenterMCvv = presenter.mCvv;
                presenterMZip = presenter.mZip;
                presenterMCardNumber = presenter.mCardNumber;
                presenterMFullName = presenter.mFullName;
                presenterMExpiry = presenter.mExpiry;
            }
            if (presenter != null) {
                presenterMBilling = presenter.mBilling;
            }
            updateRegistration(1, (Observable) presenterMBilling);
            if (presenterMBilling != null) {
                presenterMBillingValue = presenterMBilling.getValue();
            }
        }
        if ((16 & dirtyFlags) != 0) {
            this.btnContinue.setOnClickListener(this.mCallback4);
            this.etBilling.setOnClickListener(this.mCallback3);
            TextViewBindingAdapter.setTextWatcher(this.etBilling, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etBillingandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.etCardNumber, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etCardNumberandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.etCvv, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etCvvandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.etExpiry, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etExpiryandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.etFullName, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etFullNameandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.etZip, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etZipandroidTextAttrChanged);
            this.mboundView10.setOnClickListener(this.mCallback6);
            this.mboundView11.setOnClickListener(this.mCallback7);
            this.mboundView9.setOnClickListener(this.mCallback5);
        }
        if ((30 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.etBilling, presenterMBillingValue);
        }
        if ((20 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.etCardNumber, presenterMCardNumber);
            TextViewBindingAdapter.setText(this.etCvv, presenterMCvv);
            TextViewBindingAdapter.setText(this.etExpiry, presenterMExpiry);
            TextViewBindingAdapter.setText(this.etFullName, presenterMFullName);
            TextViewBindingAdapter.setText(this.etZip, presenterMZip);
        }
        ViewDataBinding.executeBindingsOn(this.cardFormAllDone);
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        CardFormPresenter presenter;
        boolean presenterJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                presenter = this.mPresenter;
                if (presenter != null) {
                    presenterJavaLangObjectNull = true;
                } else {
                    presenterJavaLangObjectNull = false;
                }
                if (presenterJavaLangObjectNull) {
                    presenter.onBillingAddressClicked();
                    return;
                }
                return;
            case 2:
                presenter = this.mPresenter;
                if (presenter != null) {
                    presenterJavaLangObjectNull = true;
                } else {
                    presenterJavaLangObjectNull = false;
                }
                if (presenterJavaLangObjectNull) {
                    presenter.submitForm();
                    return;
                }
                return;
            case 3:
                presenter = this.mPresenter;
                if (presenter != null) {
                    presenterJavaLangObjectNull = true;
                } else {
                    presenterJavaLangObjectNull = false;
                }
                if (presenterJavaLangObjectNull) {
                    presenter.onBottomSheetCloseClicked();
                    return;
                }
                return;
            case 4:
                presenter = this.mPresenter;
                if (presenter != null) {
                    presenterJavaLangObjectNull = true;
                } else {
                    presenterJavaLangObjectNull = false;
                }
                if (presenterJavaLangObjectNull) {
                    presenter.onBottomSheetCloseClicked();
                    return;
                }
                return;
            case 5:
                presenter = this.mPresenter;
                if (presenter != null) {
                    presenterJavaLangObjectNull = true;
                } else {
                    presenterJavaLangObjectNull = false;
                }
                if (presenterJavaLangObjectNull) {
                    presenter.onBottomSheetCloseClicked();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static CardFormControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static CardFormControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (CardFormControllerBinding) DataBindingUtil.inflate(inflater, R.layout.card_form_controller, root, attachToRoot, bindingComponent);
    }

    public static CardFormControllerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static CardFormControllerBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.card_form_controller, null, false), bindingComponent);
    }

    public static CardFormControllerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static CardFormControllerBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/card_form_controller_0".equals(view.getTag())) {
            return new CardFormControllerBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
