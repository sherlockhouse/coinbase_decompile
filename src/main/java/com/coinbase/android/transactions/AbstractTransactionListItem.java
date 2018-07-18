package com.coinbase.android.transactions;

import android.content.Context;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Data;

public abstract class AbstractTransactionListItem {
    private boolean isSelected = false;
    protected boolean isVault;
    protected Context mContext;
    protected LoginManager mLoginManager;
    protected Data mSelectedAccount;

    public AbstractTransactionListItem(Context context, LoginManager loginManager, Data selectedAccount, boolean vault) {
        this.mContext = context;
        this.mLoginManager = loginManager;
        this.mSelectedAccount = selectedAccount;
        this.isVault = vault;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
