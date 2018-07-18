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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.paymentmethods.IAVLoginPresenter;

public class BankCdvAmountsFormBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnCdvVerificationAmountsSubmit;
    public final EditText etAmount1;
    private InverseBindingListener etAmount1androidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(BankCdvAmountsFormBinding.this.etAmount1);
            IAVLoginPresenter presenter = BankCdvAmountsFormBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mBankAmount1 = callbackArg_0;
            }
        }
    };
    public final EditText etAmount2;
    private InverseBindingListener etAmount2androidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(BankCdvAmountsFormBinding.this.etAmount2);
            IAVLoginPresenter presenter = BankCdvAmountsFormBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mBankAmount2 = callbackArg_0;
            }
        }
    };
    public final ImageView imageView2;
    public final LinearLayout llCdvVerificationAmountsForm;
    private long mDirtyFlags = -1;
    private IAVLoginPresenter mPresenter;
    public final TextView textView;
    public final TextView textView11;
    public final TextView textView12;
    public final TextView textView5;
    public final TextView tvTransactionSupport;

    static {
        sViewsWithIds.put(R.id.imageView2, 3);
        sViewsWithIds.put(R.id.textView, 4);
        sViewsWithIds.put(R.id.textView5, 5);
        sViewsWithIds.put(R.id.textView11, 6);
        sViewsWithIds.put(R.id.textView12, 7);
        sViewsWithIds.put(R.id.btnCdvVerificationAmountsSubmit, 8);
        sViewsWithIds.put(R.id.tvTransactionSupport, 9);
    }

    public BankCdvAmountsFormBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.btnCdvVerificationAmountsSubmit = (Button) bindings[8];
        this.etAmount1 = (EditText) bindings[1];
        this.etAmount1.setTag(null);
        this.etAmount2 = (EditText) bindings[2];
        this.etAmount2.setTag(null);
        this.imageView2 = (ImageView) bindings[3];
        this.llCdvVerificationAmountsForm = (LinearLayout) bindings[0];
        this.llCdvVerificationAmountsForm.setTag(null);
        this.textView = (TextView) bindings[4];
        this.textView11 = (TextView) bindings[6];
        this.textView12 = (TextView) bindings[7];
        this.textView5 = (TextView) bindings[5];
        this.tvTransactionSupport = (TextView) bindings[9];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
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
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    public IAVLoginPresenter getPresenter() {
        return this.mPresenter;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        IAVLoginPresenter presenter = this.mPresenter;
        String presenterMBankAmount2 = null;
        String presenterMBankAmount1 = null;
        if (!((dirtyFlags & 3) == 0 || presenter == null)) {
            presenterMBankAmount2 = presenter.mBankAmount2;
            presenterMBankAmount1 = presenter.mBankAmount1;
        }
        if ((dirtyFlags & 3) != 0) {
            TextViewBindingAdapter.setText(this.etAmount1, presenterMBankAmount1);
            TextViewBindingAdapter.setText(this.etAmount2, presenterMBankAmount2);
        }
        if ((2 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.etAmount1, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etAmount1androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.etAmount2, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etAmount2androidTextAttrChanged);
        }
    }

    public static BankCdvAmountsFormBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static BankCdvAmountsFormBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (BankCdvAmountsFormBinding) DataBindingUtil.inflate(inflater, R.layout.bank_cdv_amounts_form, root, attachToRoot, bindingComponent);
    }

    public static BankCdvAmountsFormBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static BankCdvAmountsFormBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.bank_cdv_amounts_form, null, false), bindingComponent);
    }

    public static BankCdvAmountsFormBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static BankCdvAmountsFormBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/bank_cdv_amounts_form_0".equals(view.getTag())) {
            return new BankCdvAmountsFormBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
