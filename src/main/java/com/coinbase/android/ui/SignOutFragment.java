package com.coinbase.android.ui;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import javax.inject.Inject;

@ActivityScope
public class SignOutFragment extends DialogFragment {
    @Inject
    SignOutConnector mSignOutConnector;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ComponentProvider) getContext().getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder builder = new Builder(getActivity());
        builder.setMessage(R.string.sign_out_confirm);
        builder.setPositiveButton(R.string.ok, SignOutFragment$$Lambda$1.lambdaFactory$(this));
        builder.setNegativeButton(R.string.cancel, SignOutFragment$$Lambda$2.lambdaFactory$());
        return builder.create();
    }

    static /* synthetic */ void lambda$onCreateDialog$1(DialogInterface dialog, int id) {
    }
}
