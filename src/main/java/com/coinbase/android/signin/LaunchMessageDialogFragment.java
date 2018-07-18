package com.coinbase.android.signin;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.android.GlideApp;
import com.coinbase.android.R;
import com.coinbase.android.databinding.DialogLaunchMessageBinding;

public class LaunchMessageDialogFragment extends DialogFragment {
    public static LaunchMessageDialogFragment newInstance(String title, String description, String url, String action, String image, boolean isLocked) {
        Bundle args = new Bundle();
        args.putString(LaunchMessageActivity.TITLE, title);
        args.putString(LaunchMessageActivity.DESCRIPTION, description);
        args.putString(LaunchMessageActivity.URL, url);
        args.putString(LaunchMessageActivity.ACTION, action);
        args.putString(LaunchMessageActivity.IMAGE, image);
        args.putBoolean(LaunchMessageActivity.DISMISSIBLE, isLocked);
        LaunchMessageDialogFragment fragment = new LaunchMessageDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DialogLaunchMessageBinding binding = (DialogLaunchMessageBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_launch_message, null, false);
        final Bundle args = getArguments();
        if (args != null) {
            binding.tvTitle.setText(args.getString(LaunchMessageActivity.TITLE));
            binding.tvDescription.setText(Html.fromHtml(args.getString(LaunchMessageActivity.DESCRIPTION)));
            binding.tvDescription.setMovementMethod(LinkMovementMethod.getInstance());
            binding.btnUpdate.setText(args.getString(LaunchMessageActivity.ACTION));
            binding.btnUpdate.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    String url = args.getString(LaunchMessageActivity.URL);
                    if (url != null) {
                        Intent i = new Intent("android.intent.action.VIEW");
                        i.setData(Uri.parse(url));
                        LaunchMessageDialogFragment.this.startActivity(i);
                    }
                    LaunchMessageDialogFragment.this.getActivity().finish();
                }
            });
            Object image = args.getString(LaunchMessageActivity.IMAGE);
            if (!(image == null || getContext() == null)) {
                GlideApp.with(getContext()).load(image).placeholder((int) R.drawable.warning).into(binding.ivImage);
            }
        }
        return new Builder(getActivity()).setView(binding.getRoot()).create();
    }
}
