package com.coinbase.android.phone;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.databinding.FragmentTextDialogBinding;
import com.coinbase.android.utils.Utils;

public class DeletePhoneNumberFragment extends DialogFragment {
    public static final String PHONE_ID_KEY = "DeletePhoneNumber_Phone";
    public static final String PHONE_IS_VERIFIED_KEY = "DeletePhoneNumber_IsVerified";
    private boolean isVerified;
    private FragmentTextDialogBinding mBinding;
    private String mId;

    public static DeletePhoneNumberFragment newInstance(String id, boolean isVerified) {
        DeletePhoneNumberFragment f = new DeletePhoneNumberFragment();
        Bundle args = new Bundle();
        args.putString(PHONE_ID_KEY, id);
        args.putBoolean(PHONE_IS_VERIFIED_KEY, isVerified);
        f.setArguments(args);
        return f;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mId = getArguments().getString(PHONE_ID_KEY);
        this.isVerified = getArguments().getBoolean(PHONE_IS_VERIFIED_KEY);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int i;
        int i2 = 0;
        this.mBinding = (FragmentTextDialogBinding) DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_text_dialog, null, false);
        if (TextUtils.isEmpty(getTitle())) {
            this.mBinding.tvTitle.setVisibility(8);
        } else {
            this.mBinding.tvTitle.setVisibility(0);
            this.mBinding.tvTitle.setText(getTitle());
        }
        this.mBinding.etTextDialogInput.setInputType(getInputType());
        EditText editText = this.mBinding.etTextDialogInput;
        if (this.isVerified) {
            i = 0;
        } else {
            i = 8;
        }
        editText.setVisibility(i);
        this.mBinding.tvLabel.setText(getLabel());
        TextView textView = this.mBinding.tvLabel;
        if (!this.isVerified) {
            i2 = 8;
        }
        textView.setVisibility(i2);
        Builder alertBuilder = new Builder(getActivity());
        alertBuilder.setView(this.mBinding.getRoot());
        alertBuilder.setTitle(null);
        alertBuilder.setPositiveButton(R.string.ok, null);
        alertBuilder.setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return alertBuilder.create();
    }

    public void onStart() {
        super.onStart();
        ((AlertDialog) getDialog()).getButton(-1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DeletePhoneNumberFragment.this.onSubmit(DeletePhoneNumberFragment.this.mBinding.etTextDialogInput.getText().toString());
            }
        });
    }

    public int getInputType() {
        return 2;
    }

    public String getLabel() {
        return getString(R.string.tfa_token);
    }

    public String getTitle() {
        return getString(R.string.delete_phone_number);
    }

    public void onSubmit(String enteredValue) {
        if (enteredValue.trim().isEmpty()) {
            Utils.showMessage(getContext(), (int) R.string.token_empty_message, 1);
            return;
        }
        new DeletePhoneTask((AppCompatActivity) getActivity(), getContext(), this.mId, enteredValue, this.isVerified, false).deletePhoneNumber();
        dismiss();
    }
}
