package com.coinbase.android.pin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.Constants;
import com.coinbase.android.R;
import com.coinbase.android.databinding.FragmentPinSettingsDialogBinding;
import com.coinbase.android.settings.LocalUserDataUpdatedConnector;
import com.coinbase.api.LoginManager;
import javax.inject.Inject;

@ActivityScope
public class PINSettingDialogFragment extends DialogFragment {
    public static final String IS_SET_MODE = "PinSettingDialogFragment_Is_Set_Mode";
    private boolean isSetMode;
    @Inject
    LocalUserDataUpdatedConnector mLocalUserDataUpdatedConnector;
    @Inject
    protected LoginManager mLoginManager;
    @Inject
    protected PINManager mPINManager;

    public interface PINSettingListener {
        void onDismiss();
    }

    public static PINSettingDialogFragment newInstance(boolean isSetMode) {
        Bundle args = new Bundle();
        PINSettingDialogFragment fragment = new PINSettingDialogFragment();
        args.putBoolean(IS_SET_MODE, isSetMode);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ComponentProvider) getActivity().getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int i;
        Builder builder = new Builder(getActivity());
        if (getActivity() != null) {
            this.isSetMode = getArguments().getBoolean(IS_SET_MODE);
        }
        final FragmentPinSettingsDialogBinding binding = (FragmentPinSettingsDialogBinding) DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_pin_settings_dialog, null, false);
        Integer[] items = new Integer[]{Integer.valueOf(R.string.pin_app_open), Integer.valueOf(R.string.pin_send_money)};
        String[] itemsText = new String[items.length];
        for (i = 0; i < items.length; i++) {
            itemsText[i] = getString(items[i].intValue());
        }
        binding.tvPinSettingsTitle.setText(R.string.enable_pin_code);
        binding.lvPinSettingsOptions.setAdapter(new ArrayAdapter(getActivity(), 17367056, itemsText));
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        for (i = 0; i < items.length; i++) {
            int i2;
            ListView listView = binding.lvPinSettingsOptions;
            if (this.mPINManager.isPinEnabled(getContext())) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            listView.setItemChecked(i, i2 | prefs.getBoolean(PINManager.KEYS[i], false));
        }
        builder.setView(binding.getRoot());
        builder.setTitle("");
        builder.setPositiveButton(R.string.ok, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int i;
                boolean pinEnabled = false;
                SparseBooleanArray options = binding.lvPinSettingsOptions.getCheckedItemPositions();
                for (i = 0; i < PINManager.KEYS.length; i++) {
                    pinEnabled |= options.get(i);
                }
                Editor e = prefs.edit();
                if (!(pinEnabled || TextUtils.isEmpty(prefs.getString(Constants.KEY_ACCOUNT_PIN, null)))) {
                    e.putString(Constants.KEY_ACCOUNT_PIN, null);
                }
                for (i = 0; i < PINManager.KEYS.length; i++) {
                    e.putBoolean(PINManager.KEYS[i], options.get(i));
                }
                e.apply();
                PINSettingDialogFragment.this.mLocalUserDataUpdatedConnector.get().onNext(null);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Editor e = prefs.edit();
                if (PINSettingDialogFragment.this.isSetMode) {
                    e.remove(Constants.KEY_ACCOUNT_PIN);
                    e.apply();
                }
                PINSettingDialogFragment.this.mLocalUserDataUpdatedConnector.get().onNext(null);
                dialog.dismiss();
            }
        });
        return builder.create();
    }

    public void onStart() {
        super.onStart();
        ((AlertDialog) getDialog()).getButton(-1).setTextColor(getResources().getColor(R.color.coinbase_blue));
        ((AlertDialog) getDialog()).getButton(-2).setTextColor(getResources().getColor(R.color.extra_dark_grey_text));
    }

    public void onDismiss(DialogInterface dialog) {
        if (getActivity() != null) {
            ((PINSettingListener) getActivity()).onDismiss();
        }
        super.onDismiss(dialog);
    }
}
