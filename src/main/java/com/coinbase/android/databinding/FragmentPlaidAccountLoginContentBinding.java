package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.coinbase.android.R;

public class FragmentPlaidAccountLoginContentBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(23);
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnPlaidAccountChooserSubmit;
    public final Button btnPlaidLoginSubmit;
    public final Button btnPlaidPinSubmit;
    public final Button btnPlaidSendMfaSubmit;
    public final Button btnPlaidSubmitMfaSubmit;
    public final EditText etPlaidLoginPassword;
    public final EditText etPlaidLoginPin;
    public final EditText etPlaidLoginUserName;
    public final ImageView ivPlaidLoginLogo;
    public final LinearLayout llPLaidAccountLoginForm;
    public final LinearLayout llPlaidAccountPinForm;
    public final LinearLayout llPlaidChooseAccountForm;
    public final LinearLayout llPlaidMfaQuestionsContainer;
    public final LinearLayout llPlaidSendMfa;
    public final LinearLayout llPlaidSubmitMfa;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final PlaidLoginAllDoneBinding plaidLoginAllDone;
    public final Spinner spinnerPlaidChooseAccount;
    public final Spinner spinnerPlaidMfa;
    public final TextView tvPlaidLoginManual;
    public final TextView tvPlaidLoginProtectionDetails;
    public final TextView tvPlaidSubmitMfaTitle;
    public final TextView tvPlainLoginBankName;

    static {
        sIncludes.setIncludes(0, new String[]{"plaid_login_all_done"}, new int[]{1}, new int[]{R.layout.plaid_login_all_done});
        sViewsWithIds.put(R.id.ivPlaidLoginLogo, 2);
        sViewsWithIds.put(R.id.tvPlainLoginBankName, 3);
        sViewsWithIds.put(R.id.llPLaidAccountLoginForm, 4);
        sViewsWithIds.put(R.id.etPlaidLoginUserName, 5);
        sViewsWithIds.put(R.id.etPlaidLoginPassword, 6);
        sViewsWithIds.put(R.id.btnPlaidLoginSubmit, 7);
        sViewsWithIds.put(R.id.tvPlaidLoginManual, 8);
        sViewsWithIds.put(R.id.tvPlaidLoginProtectionDetails, 9);
        sViewsWithIds.put(R.id.llPlaidAccountPinForm, 10);
        sViewsWithIds.put(R.id.etPlaidLoginPin, 11);
        sViewsWithIds.put(R.id.btnPlaidPinSubmit, 12);
        sViewsWithIds.put(R.id.llPlaidSendMfa, 13);
        sViewsWithIds.put(R.id.spinnerPlaidMfa, 14);
        sViewsWithIds.put(R.id.btnPlaidSendMfaSubmit, 15);
        sViewsWithIds.put(R.id.llPlaidSubmitMfa, 16);
        sViewsWithIds.put(R.id.tvPlaidSubmitMfaTitle, 17);
        sViewsWithIds.put(R.id.llPlaidMfaQuestionsContainer, 18);
        sViewsWithIds.put(R.id.btnPlaidSubmitMfaSubmit, 19);
        sViewsWithIds.put(R.id.llPlaidChooseAccountForm, 20);
        sViewsWithIds.put(R.id.spinnerPlaidChooseAccount, 21);
        sViewsWithIds.put(R.id.btnPlaidAccountChooserSubmit, 22);
    }

    public FragmentPlaidAccountLoginContentBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds);
        this.btnPlaidAccountChooserSubmit = (Button) bindings[22];
        this.btnPlaidLoginSubmit = (Button) bindings[7];
        this.btnPlaidPinSubmit = (Button) bindings[12];
        this.btnPlaidSendMfaSubmit = (Button) bindings[15];
        this.btnPlaidSubmitMfaSubmit = (Button) bindings[19];
        this.etPlaidLoginPassword = (EditText) bindings[6];
        this.etPlaidLoginPin = (EditText) bindings[11];
        this.etPlaidLoginUserName = (EditText) bindings[5];
        this.ivPlaidLoginLogo = (ImageView) bindings[2];
        this.llPLaidAccountLoginForm = (LinearLayout) bindings[4];
        this.llPlaidAccountPinForm = (LinearLayout) bindings[10];
        this.llPlaidChooseAccountForm = (LinearLayout) bindings[20];
        this.llPlaidMfaQuestionsContainer = (LinearLayout) bindings[18];
        this.llPlaidSendMfa = (LinearLayout) bindings[13];
        this.llPlaidSubmitMfa = (LinearLayout) bindings[16];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.plaidLoginAllDone = (PlaidLoginAllDoneBinding) bindings[1];
        setContainedBinding(this.plaidLoginAllDone);
        this.spinnerPlaidChooseAccount = (Spinner) bindings[21];
        this.spinnerPlaidMfa = (Spinner) bindings[14];
        this.tvPlaidLoginManual = (TextView) bindings[8];
        this.tvPlaidLoginProtectionDetails = (TextView) bindings[9];
        this.tvPlaidSubmitMfaTitle = (TextView) bindings[17];
        this.tvPlainLoginBankName = (TextView) bindings[3];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.plaidLoginAllDone.invalidateAll();
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
        return false;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangePlaidLoginAllDone((PlaidLoginAllDoneBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangePlaidLoginAllDone(PlaidLoginAllDoneBinding PlaidLoginAllDone, int fieldId) {
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

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ViewDataBinding.executeBindingsOn(this.plaidLoginAllDone);
    }

    public static FragmentPlaidAccountLoginContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPlaidAccountLoginContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentPlaidAccountLoginContentBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_plaid_account_login_content, root, attachToRoot, bindingComponent);
    }

    public static FragmentPlaidAccountLoginContentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPlaidAccountLoginContentBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.fragment_plaid_account_login_content, null, false), bindingComponent);
    }

    public static FragmentPlaidAccountLoginContentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPlaidAccountLoginContentBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/fragment_plaid_account_login_content_0".equals(view.getTag())) {
            return new FragmentPlaidAccountLoginContentBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
