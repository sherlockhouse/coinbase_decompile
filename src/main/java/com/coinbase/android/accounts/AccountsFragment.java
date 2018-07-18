package com.coinbase.android.accounts;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Data;
import java.util.List;
import javax.inject.Inject;

public class AccountsFragment extends DialogFragment {
    @Inject
    protected LoginManager mLoginManager;
    int selectedIndex = 0;
    boolean widgetMode = false;

    public interface ParentActivity {
        void onAccountChosen(Data data);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ComponentProvider) getActivity().getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        boolean z;
        Builder builder = new Builder(getActivity());
        builder.setTitle(R.string.accounts_title);
        if (getArguments() == null) {
            z = false;
        } else {
            z = getArguments().getBoolean("widgetMode");
        }
        this.widgetMode = z;
        final List<Data> accounts = this.mLoginManager.getAccounts();
        String activeAccountId = this.mLoginManager.getActiveAccountId();
        String[] accountNames = new String[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            if (((Data) accounts.get(i)).getId().equals(activeAccountId)) {
                this.selectedIndex = i;
            }
            accountNames[i] = ((Data) accounts.get(i)).getName();
        }
        builder.setSingleChoiceItems(accountNames, this.selectedIndex, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AccountsFragment.this.selectedIndex = which;
            }
        }).setPositiveButton(R.string.ok, new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ParentActivity activity = (ParentActivity) AccountsFragment.this.getActivity();
                if (accounts.size() > 0) {
                    activity.onAccountChosen((Data) accounts.get(AccountsFragment.this.selectedIndex));
                }
            }
        }).setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (AccountsFragment.this.widgetMode) {
                    AccountsFragment.this.getActivity().finish();
                }
            }
        });
        return builder.create();
    }
}
