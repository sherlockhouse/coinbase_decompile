package com.coinbase.android.accounts;

import com.coinbase.android.R;
import com.coinbase.android.dialog.InputTextDialogFragment;
import com.coinbase.android.utils.Utils;

public class NewAccountDialogFragment extends InputTextDialogFragment {
    public String getLabel() {
        return getString(R.string.label_account_name);
    }

    public String getTitle() {
        return getString(R.string.new_account);
    }

    public void onSubmit(String enteredValue) {
        if (enteredValue.trim().isEmpty()) {
            Utils.showMessage(getContext(), (int) R.string.create_account_empty_warning, 1);
        } else {
            new CreateAccountTask(getActivity(), enteredValue).createAccount();
        }
    }
}
