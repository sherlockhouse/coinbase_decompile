package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingListener;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.OnTextChanged;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.paymentmethods.IAVLoginPresenter;

public class FragmentIavLoginContentBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(26);
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final BankCdvAmountsFormBinding bankCdvAmountsForm;
    public final Button btnCdvAmountSentContinue;
    public final Button btnChooseCdv;
    public final Button btnIavDetailsSubmit;
    public final Button btnIavNameFormSubmit;
    public final Button btnIavTypeSubmit;
    public final CardCdvAmountsFormBinding cardCdvAmountsForm;
    public final EditText etAchAccountNumber;
    private InverseBindingListener etAchAccountNumberandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(FragmentIavLoginContentBinding.this.etAchAccountNumber);
            IAVLoginPresenter presenter = FragmentIavLoginContentBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mAccountNumber = callbackArg_0;
            }
        }
    };
    public final EditText etAchRoutingNumber;
    private InverseBindingListener etAchRoutingNumberandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(FragmentIavLoginContentBinding.this.etAchRoutingNumber);
            IAVLoginPresenter presenter = FragmentIavLoginContentBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mRoutingNumber = callbackArg_0;
            }
        }
    };
    public final EditText etFullName;
    private InverseBindingListener etFullNameandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(FragmentIavLoginContentBinding.this.etFullName);
            IAVLoginPresenter presenter = FragmentIavLoginContentBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mCustomerName = callbackArg_0;
            }
        }
    };
    public final ImageView ivCheque;
    public final ImageView ivIavLogo;
    public final LinearLayout llAccountHint;
    public final LinearLayout llCdvAmountSentForm;
    public final LinearLayout llChooseCdvIavForm;
    public final LinearLayout llIavAccountDetailsForm;
    public final LinearLayout llIavNameForm;
    public final LinearLayout llIavTypeForm;
    public final LinearLayout llIavVerificationInProgressForm;
    public final LinearLayout llRoutingHint;
    private long mDirtyFlags = -1;
    private IAVLoginPresenter mPresenter;
    private final FrameLayout mboundView0;
    private final LinearLayout mboundView1;
    public final PlaidLoginAllDoneBinding plaidLoginAllDone;
    public final RelativeLayout rlHintContainer;
    public final Spinner spinnerIavType;
    public final TextView tvIavShowRoutingDetails;

    static {
        sIncludes.setIncludes(0, new String[]{"bank_cdv_amounts_form", "card_cdv_amounts_form"}, new int[]{6, 7}, new int[]{R.layout.bank_cdv_amounts_form, R.layout.card_cdv_amounts_form});
        sIncludes.setIncludes(1, new String[]{"plaid_login_all_done"}, new int[]{5}, new int[]{R.layout.plaid_login_all_done});
        sViewsWithIds.put(R.id.ivIavLogo, 8);
        sViewsWithIds.put(R.id.llIavNameForm, 9);
        sViewsWithIds.put(R.id.btnIavNameFormSubmit, 10);
        sViewsWithIds.put(R.id.llIavTypeForm, 11);
        sViewsWithIds.put(R.id.spinnerIavType, 12);
        sViewsWithIds.put(R.id.btnIavTypeSubmit, 13);
        sViewsWithIds.put(R.id.llIavAccountDetailsForm, 14);
        sViewsWithIds.put(R.id.btnIavDetailsSubmit, 15);
        sViewsWithIds.put(R.id.tvIavShowRoutingDetails, 16);
        sViewsWithIds.put(R.id.llChooseCdvIavForm, 17);
        sViewsWithIds.put(R.id.btnChooseCdv, 18);
        sViewsWithIds.put(R.id.llCdvAmountSentForm, 19);
        sViewsWithIds.put(R.id.btnCdvAmountSentContinue, 20);
        sViewsWithIds.put(R.id.llIavVerificationInProgressForm, 21);
        sViewsWithIds.put(R.id.rlHintContainer, 22);
        sViewsWithIds.put(R.id.ivCheque, 23);
        sViewsWithIds.put(R.id.llRoutingHint, 24);
        sViewsWithIds.put(R.id.llAccountHint, 25);
    }

    public FragmentIavLoginContentBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 3);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 26, sIncludes, sViewsWithIds);
        this.bankCdvAmountsForm = (BankCdvAmountsFormBinding) bindings[6];
        setContainedBinding(this.bankCdvAmountsForm);
        this.btnCdvAmountSentContinue = (Button) bindings[20];
        this.btnChooseCdv = (Button) bindings[18];
        this.btnIavDetailsSubmit = (Button) bindings[15];
        this.btnIavNameFormSubmit = (Button) bindings[10];
        this.btnIavTypeSubmit = (Button) bindings[13];
        this.cardCdvAmountsForm = (CardCdvAmountsFormBinding) bindings[7];
        setContainedBinding(this.cardCdvAmountsForm);
        this.etAchAccountNumber = (EditText) bindings[4];
        this.etAchAccountNumber.setTag(null);
        this.etAchRoutingNumber = (EditText) bindings[3];
        this.etAchRoutingNumber.setTag(null);
        this.etFullName = (EditText) bindings[2];
        this.etFullName.setTag(null);
        this.ivCheque = (ImageView) bindings[23];
        this.ivIavLogo = (ImageView) bindings[8];
        this.llAccountHint = (LinearLayout) bindings[25];
        this.llCdvAmountSentForm = (LinearLayout) bindings[19];
        this.llChooseCdvIavForm = (LinearLayout) bindings[17];
        this.llIavAccountDetailsForm = (LinearLayout) bindings[14];
        this.llIavNameForm = (LinearLayout) bindings[9];
        this.llIavTypeForm = (LinearLayout) bindings[11];
        this.llIavVerificationInProgressForm = (LinearLayout) bindings[21];
        this.llRoutingHint = (LinearLayout) bindings[24];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.plaidLoginAllDone = (PlaidLoginAllDoneBinding) bindings[5];
        setContainedBinding(this.plaidLoginAllDone);
        this.rlHintContainer = (RelativeLayout) bindings[22];
        this.spinnerIavType = (Spinner) bindings[12];
        this.tvIavShowRoutingDetails = (TextView) bindings[16];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
        }
        this.plaidLoginAllDone.invalidateAll();
        this.bankCdvAmountsForm.invalidateAll();
        this.cardCdvAmountsForm.invalidateAll();
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
                setPresenter((IAVLoginPresenter) variable);
                return true;
            default:
                return false;
        }
    }

    public void setPresenter(IAVLoginPresenter Presenter) {
        this.mPresenter = Presenter;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    public IAVLoginPresenter getPresenter() {
        return this.mPresenter;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeCardCdvAmountsForm((CardCdvAmountsFormBinding) object, fieldId);
            case 1:
                return onChangePlaidLoginAllDone((PlaidLoginAllDoneBinding) object, fieldId);
            case 2:
                return onChangeBankCdvAmountsForm((BankCdvAmountsFormBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeCardCdvAmountsForm(CardCdvAmountsFormBinding CardCdvAmountsForm, int fieldId) {
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

    private boolean onChangePlaidLoginAllDone(PlaidLoginAllDoneBinding PlaidLoginAllDone, int fieldId) {
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 2;
                }
                return true;
            default:
                return false;
        }
    }

    private boolean onChangeBankCdvAmountsForm(BankCdvAmountsFormBinding BankCdvAmountsForm, int fieldId) {
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 4;
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
        IAVLoginPresenter presenter = this.mPresenter;
        String presenterMCustomerName = null;
        String presenterMRoutingNumber = null;
        String presenterMAccountNumber = null;
        if (!((dirtyFlags & 24) == 0 || presenter == null)) {
            presenterMCustomerName = presenter.mCustomerName;
            presenterMRoutingNumber = presenter.mRoutingNumber;
            presenterMAccountNumber = presenter.mAccountNumber;
        }
        if ((dirtyFlags & 24) != 0) {
            TextViewBindingAdapter.setText(this.etAchAccountNumber, presenterMAccountNumber);
            TextViewBindingAdapter.setText(this.etAchRoutingNumber, presenterMRoutingNumber);
            TextViewBindingAdapter.setText(this.etFullName, presenterMCustomerName);
        }
        if ((16 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.etAchAccountNumber, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etAchAccountNumberandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.etAchRoutingNumber, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etAchRoutingNumberandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.etFullName, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etFullNameandroidTextAttrChanged);
        }
        ViewDataBinding.executeBindingsOn(this.plaidLoginAllDone);
        ViewDataBinding.executeBindingsOn(this.bankCdvAmountsForm);
        ViewDataBinding.executeBindingsOn(this.cardCdvAmountsForm);
    }

    public static FragmentIavLoginContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentIavLoginContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentIavLoginContentBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_iav_login_content, root, attachToRoot, bindingComponent);
    }

    public static FragmentIavLoginContentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentIavLoginContentBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.fragment_iav_login_content, null, false), bindingComponent);
    }

    public static FragmentIavLoginContentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentIavLoginContentBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/fragment_iav_login_content_0".equals(view.getTag())) {
            return new FragmentIavLoginContentBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
