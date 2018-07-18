package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.coinbase.android.R;

public class FragmentDepositBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(9);
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnSubmit;
    public final Toolbar cvCoinbaseToolbar;
    public final EditText etAmount;
    public final LinearLayout llHeader;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    private final RelativeLayout mboundView1;
    public final OverlayBuysellDepositBinding quickstartContent;
    public final Spinner spinnerBank;
    public final TextView tvBankLabel;

    static {
        sIncludes.setIncludes(1, new String[]{"overlay_buysell_deposit"}, new int[]{2}, new int[]{R.layout.overlay_buysell_deposit});
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 3);
        sViewsWithIds.put(R.id.llHeader, 4);
        sViewsWithIds.put(R.id.tvBankLabel, 5);
        sViewsWithIds.put(R.id.spinnerBank, 6);
        sViewsWithIds.put(R.id.etAmount, 7);
        sViewsWithIds.put(R.id.btnSubmit, 8);
    }

    public FragmentDepositBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.btnSubmit = (Button) bindings[8];
        this.cvCoinbaseToolbar = (Toolbar) bindings[3];
        this.etAmount = (EditText) bindings[7];
        this.llHeader = (LinearLayout) bindings[4];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (RelativeLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.quickstartContent = (OverlayBuysellDepositBinding) bindings[2];
        setContainedBinding(this.quickstartContent);
        this.spinnerBank = (Spinner) bindings[6];
        this.tvBankLabel = (TextView) bindings[5];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.quickstartContent.invalidateAll();
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
                return onChangeQuickstartContent((OverlayBuysellDepositBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeQuickstartContent(OverlayBuysellDepositBinding QuickstartContent, int fieldId) {
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
        ViewDataBinding.executeBindingsOn(this.quickstartContent);
    }

    public static FragmentDepositBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentDepositBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentDepositBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_deposit, root, attachToRoot, bindingComponent);
    }

    public static FragmentDepositBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentDepositBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.fragment_deposit, null, false), bindingComponent);
    }

    public static FragmentDepositBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentDepositBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/fragment_deposit_0".equals(view.getTag())) {
            return new FragmentDepositBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
