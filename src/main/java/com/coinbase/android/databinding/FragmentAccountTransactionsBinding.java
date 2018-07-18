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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.accounts.AccountCryptoAddressLayout;
import com.coinbase.android.accounts.EnableSendReceiveLayout;
import com.coinbase.android.transactions.TransactionListLayout;

public class FragmentAccountTransactionsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final AccountCryptoAddressLayout accountCryptoAddressLayout;
    public final Button btLeft;
    public final Button btRight;
    public final Toolbar cvCoinbaseToolbar;
    public final EnableSendReceiveLayout enableSendReceiveLayout;
    public final LinearLayout llAmount;
    public final LinearLayout llButton;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TransactionListLayout transactionsListLayout;
    public final TextView tvAccountBalance;
    public final TextView tvAccountNativeBalance;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.llAmount, 2);
        sViewsWithIds.put(R.id.tvAccountBalance, 3);
        sViewsWithIds.put(R.id.tvAccountNativeBalance, 4);
        sViewsWithIds.put(R.id.llButton, 5);
        sViewsWithIds.put(R.id.btLeft, 6);
        sViewsWithIds.put(R.id.btRight, 7);
        sViewsWithIds.put(R.id.transactionsListLayout, 8);
        sViewsWithIds.put(R.id.accountCryptoAddressLayout, 9);
        sViewsWithIds.put(R.id.enableSendReceiveLayout, 10);
    }

    public FragmentAccountTransactionsBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.accountCryptoAddressLayout = (AccountCryptoAddressLayout) bindings[9];
        this.btLeft = (Button) bindings[6];
        this.btRight = (Button) bindings[7];
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.enableSendReceiveLayout = (EnableSendReceiveLayout) bindings[10];
        this.llAmount = (LinearLayout) bindings[2];
        this.llButton = (LinearLayout) bindings[5];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.transactionsListLayout = (TransactionListLayout) bindings[8];
        this.tvAccountBalance = (TextView) bindings[3];
        this.tvAccountNativeBalance = (TextView) bindings[4];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
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
        return false;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    public static FragmentAccountTransactionsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentAccountTransactionsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentAccountTransactionsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_account_transactions, root, attachToRoot, bindingComponent);
    }

    public static FragmentAccountTransactionsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentAccountTransactionsBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.fragment_account_transactions, null, false), bindingComponent);
    }

    public static FragmentAccountTransactionsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentAccountTransactionsBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/fragment_account_transactions_0".equals(view.getTag())) {
            return new FragmentAccountTransactionsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
