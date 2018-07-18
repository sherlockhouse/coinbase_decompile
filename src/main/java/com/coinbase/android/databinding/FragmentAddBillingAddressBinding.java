package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingListener;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.OnTextChanged;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.design.widget.TextInputLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.coinbase.android.R;
import com.coinbase.android.billing.AddBillingAddressPresenter;

public class FragmentAddBillingAddressBinding extends ViewDataBinding implements Listener {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnContinue;
    public final EditText etAddressLine1;
    private InverseBindingListener etAddressLine1androidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(FragmentAddBillingAddressBinding.this.etAddressLine1);
            AddBillingAddressPresenter presenter = FragmentAddBillingAddressBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mAddressLine1 = callbackArg_0;
            }
        }
    };
    public final EditText etAddressLine2;
    private InverseBindingListener etAddressLine2androidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(FragmentAddBillingAddressBinding.this.etAddressLine2);
            AddBillingAddressPresenter presenter = FragmentAddBillingAddressBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mAddressLine2 = callbackArg_0;
            }
        }
    };
    public final EditText etCity;
    private InverseBindingListener etCityandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(FragmentAddBillingAddressBinding.this.etCity);
            AddBillingAddressPresenter presenter = FragmentAddBillingAddressBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mCity = callbackArg_0;
            }
        }
    };
    public final EditText etCountry;
    private InverseBindingListener etCountryandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(FragmentAddBillingAddressBinding.this.etCountry);
            AddBillingAddressPresenter presenter = FragmentAddBillingAddressBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mCountry = callbackArg_0;
            }
        }
    };
    public final EditText etState;
    private InverseBindingListener etStateandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(FragmentAddBillingAddressBinding.this.etState);
            AddBillingAddressPresenter presenter = FragmentAddBillingAddressBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mState = callbackArg_0;
            }
        }
    };
    public final EditText etZip;
    private InverseBindingListener etZipandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(FragmentAddBillingAddressBinding.this.etZip);
            AddBillingAddressPresenter presenter = FragmentAddBillingAddressBinding.this.mPresenter;
            if (presenter != null) {
                presenter.mZip = callbackArg_0;
            }
        }
    };
    public final LinearLayout linearLayout1;
    public final LinearLayout linearLayout2;
    private final OnClickListener mCallback1;
    private final OnClickListener mCallback2;
    private long mDirtyFlags = -1;
    private AddBillingAddressPresenter mPresenter;
    public final RelativeLayout relativeLayout5;
    public final RelativeLayout rlContainer;
    public final ScrollView svBillingAddressContainer;
    public final TextInputLayout tlAddressLine1;
    public final TextInputLayout tlAddressLine2;
    public final TextInputLayout tlState;
    public final TextInputLayout tlZip;

    static {
        sViewsWithIds.put(R.id.svBillingAddressContainer, 8);
        sViewsWithIds.put(R.id.tlAddressLine1, 9);
        sViewsWithIds.put(R.id.linearLayout1, 10);
        sViewsWithIds.put(R.id.tlAddressLine2, 11);
        sViewsWithIds.put(R.id.linearLayout2, 12);
        sViewsWithIds.put(R.id.tlState, 13);
        sViewsWithIds.put(R.id.tlZip, 14);
        sViewsWithIds.put(R.id.relativeLayout5, 15);
    }

    public FragmentAddBillingAddressBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds);
        this.btnContinue = (Button) bindings[7];
        this.btnContinue.setTag(null);
        this.etAddressLine1 = (EditText) bindings[1];
        this.etAddressLine1.setTag(null);
        this.etAddressLine2 = (EditText) bindings[2];
        this.etAddressLine2.setTag(null);
        this.etCity = (EditText) bindings[3];
        this.etCity.setTag(null);
        this.etCountry = (EditText) bindings[6];
        this.etCountry.setTag(null);
        this.etState = (EditText) bindings[4];
        this.etState.setTag(null);
        this.etZip = (EditText) bindings[5];
        this.etZip.setTag(null);
        this.linearLayout1 = (LinearLayout) bindings[10];
        this.linearLayout2 = (LinearLayout) bindings[12];
        this.relativeLayout5 = (RelativeLayout) bindings[15];
        this.rlContainer = (RelativeLayout) bindings[0];
        this.rlContainer.setTag(null);
        this.svBillingAddressContainer = (ScrollView) bindings[8];
        this.tlAddressLine1 = (TextInputLayout) bindings[9];
        this.tlAddressLine2 = (TextInputLayout) bindings[11];
        this.tlState = (TextInputLayout) bindings[13];
        this.tlZip = (TextInputLayout) bindings[14];
        setRootTag(root);
        this.mCallback2 = new android.databinding.generated.callback.OnClickListener(this, 2);
        this.mCallback1 = new android.databinding.generated.callback.OnClickListener(this, 1);
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
                setPresenter((AddBillingAddressPresenter) variable);
                return true;
            default:
                return false;
        }
    }

    public void setPresenter(AddBillingAddressPresenter Presenter) {
        this.mPresenter = Presenter;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    public AddBillingAddressPresenter getPresenter() {
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
        String presenterMZip = null;
        AddBillingAddressPresenter presenter = this.mPresenter;
        String presenterMAddressLine1 = null;
        String presenterMCountry = null;
        String presenterMAddressLine2 = null;
        String presenterMState = null;
        String presenterMCity = null;
        if (!((3 & dirtyFlags) == 0 || presenter == null)) {
            presenterMZip = presenter.mZip;
            presenterMAddressLine1 = presenter.mAddressLine1;
            presenterMCountry = presenter.mCountry;
            presenterMAddressLine2 = presenter.mAddressLine2;
            presenterMState = presenter.mState;
            presenterMCity = presenter.mCity;
        }
        if ((2 & dirtyFlags) != 0) {
            this.btnContinue.setOnClickListener(this.mCallback2);
            TextViewBindingAdapter.setTextWatcher(this.etAddressLine1, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etAddressLine1androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.etAddressLine2, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etAddressLine2androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.etCity, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etCityandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.etCountry, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etCountryandroidTextAttrChanged);
            this.etState.setOnClickListener(this.mCallback1);
            TextViewBindingAdapter.setTextWatcher(this.etState, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etStateandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.etZip, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.etZipandroidTextAttrChanged);
        }
        if ((3 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.etAddressLine1, presenterMAddressLine1);
            TextViewBindingAdapter.setText(this.etAddressLine2, presenterMAddressLine2);
            TextViewBindingAdapter.setText(this.etCity, presenterMCity);
            TextViewBindingAdapter.setText(this.etCountry, presenterMCountry);
            TextViewBindingAdapter.setText(this.etState, presenterMState);
            TextViewBindingAdapter.setText(this.etZip, presenterMZip);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AddBillingAddressPresenter presenter;
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
                    presenter.onStateClicked();
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
                    presenter.onContinueClick();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static FragmentAddBillingAddressBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentAddBillingAddressBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentAddBillingAddressBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_add_billing_address, root, attachToRoot, bindingComponent);
    }

    public static FragmentAddBillingAddressBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentAddBillingAddressBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.fragment_add_billing_address, null, false), bindingComponent);
    }

    public static FragmentAddBillingAddressBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentAddBillingAddressBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/fragment_add_billing_address_0".equals(view.getTag())) {
            return new FragmentAddBillingAddressBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
