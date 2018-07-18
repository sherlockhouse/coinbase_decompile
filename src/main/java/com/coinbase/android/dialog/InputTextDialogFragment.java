package com.coinbase.android.dialog;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import com.coinbase.android.R;
import com.coinbase.android.databinding.FragmentTextDialogBinding;

public abstract class InputTextDialogFragment extends DialogFragment {
    public static final String VALUE = "InputTextDialogFragment_Input";

    public abstract void onSubmit(String str);

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final FragmentTextDialogBinding binding = (FragmentTextDialogBinding) DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_text_dialog, null, false);
        if (TextUtils.isEmpty(getTitle())) {
            binding.tvTitle.setVisibility(8);
        } else {
            binding.tvTitle.setVisibility(0);
            binding.tvTitle.setText(getTitle());
        }
        if (TextUtils.isEmpty(getLabel())) {
            binding.tvLabel.setVisibility(8);
        } else {
            binding.tvLabel.setVisibility(0);
            binding.tvLabel.setText(getLabel());
        }
        binding.etTextDialogInput.setInputType(getInputType());
        if (savedInstanceState == null && getArguments() != null) {
            binding.etTextDialogInput.setText(getArguments().getString(VALUE));
        }
        Builder alertBuilder = new Builder(getActivity());
        alertBuilder.setView(binding.getRoot());
        alertBuilder.setTitle(null);
        alertBuilder.setPositiveButton(R.string.ok, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                InputTextDialogFragment.this.onSubmit(binding.etTextDialogInput.getText().toString());
            }
        });
        alertBuilder.setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                InputTextDialogFragment.this.onCancel();
            }
        });
        return alertBuilder.create();
    }

    public int getInputType() {
        return 1;
    }

    public void onCancel() {
    }

    public String getTitle() {
        return null;
    }

    public String getLabel() {
        return null;
    }
}
