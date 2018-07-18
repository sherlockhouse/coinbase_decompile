package com.coinbase.android.settings.gdpr;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerPrivacyRightsBinding;
import com.coinbase.android.settings.SettingsPreferenceListAdapter;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class PrivacyRightsController extends ActionBarController implements PrivacyRightsScreen {
    private SettingsPreferenceListAdapter mAdapter;
    private ControllerPrivacyRightsBinding mBinding;
    @Inject
    PrivacyRightsPresenter mPresenter;

    public PrivacyRightsController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerPrivacyRightsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_privacy_rights, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addPrivacyRightsControllerSubcomponent(new PrivacyRightsPresenterModule(this, this)).inject(this);
        initList();
        return this.mBinding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getResources().getString(R.string.gdpr_privacy));
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    private void initList() {
        this.mAdapter = new SettingsPreferenceListAdapter(getApplicationContext(), this.mPresenter.getPreferenceList());
        this.mBinding.rvPrivacyRightsOptions.setAdapter(this.mAdapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), 1);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.account_settings_divider));
        this.mBinding.rvPrivacyRightsOptions.addItemDecoration(itemDecoration);
        this.mBinding.rvPrivacyRightsOptions.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 1, false));
        this.mBinding.rvPrivacyRightsOptions.setNestedScrollingEnabled(false);
    }
}
