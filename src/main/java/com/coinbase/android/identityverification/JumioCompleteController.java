package com.coinbase.android.identityverification;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.coinbase.android.Constants;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerJumioCompleteBinding;
import com.coinbase.android.quickstart.QuickstartItem;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.PreferenceUtils;

public class JumioCompleteController extends ActionBarController {
    public JumioCompleteController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        ControllerJumioCompleteBinding binding = (ControllerJumioCompleteBinding) DataBindingUtil.inflate(inflater, R.layout.controller_jumio_complete, container, false);
        onAttachToolbar(binding.cvCoinbaseToolbar);
        binding.btnJumioContinue.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PreferenceUtils.putPrefsBool(JumioCompleteController.this.getActivity(), String.format(Constants.KEY_QUICKSTART_ITEM_SHOW, new Object[]{QuickstartItem.VERIFY_IDENTITY.name()}), false);
                JumioCompleteController.this.getRouter().popToRoot();
            }
        });
        return binding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.verify_identity_title));
    }
}
