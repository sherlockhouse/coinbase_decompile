package com.coinbase.android.settings;

import android.app.ProgressDialog;
import com.coinbase.android.R;
import com.coinbase.android.dialog.InputTextDialogFragment;
import com.coinbase.android.settings.UpdateUserTask.UpdateUserListener;
import com.coinbase.android.utils.Utils;

public class ChangeNameDialogFragment extends InputTextDialogFragment {
    public int getInputType() {
        return 97;
    }

    public void onSubmit(String newName) {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getString(R.string.account_save_progress));
        new UpdateUserTask(getActivity(), new UpdateUserListener() {
            public void onPreExecute() {
            }

            public void onException() {
                Utils.dismissDialog(dialog);
            }

            public void onFinally() {
                Utils.dismissDialog(dialog);
            }
        }).updateUser(newName, null, null);
    }

    public String getTitle() {
        return getString(R.string.settings_account_change_name);
    }
}
