package com.coinbase.android.settings.gdpr;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerRequestDataBinding;
import com.coinbase.android.settings.SettingsPreferenceListAdapter;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

public class GdprPrivacyRequestController extends ActionBarController implements GdprPrivacyRequestScreen {
    private SettingsPreferenceListAdapter mAdapter;
    private ControllerRequestDataBinding mBinding;
    @Inject
    GdprPrivacyRequestPresenter mPresenter;

    public GdprPrivacyRequestController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerRequestDataBinding) DataBindingUtil.inflate(inflater, R.layout.controller_request_data, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addRequestDataControllerSubcomponent(new GdprPrivacyRequestPresenterModule(this, this)).inject(this);
        this.mBinding.btSendRequest.setOnClickListener(GdprPrivacyRequestController$$Lambda$1.lambdaFactory$(this));
        initList();
        this.mPresenter.onCreateView(getArgs());
        return this.mBinding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.gdpr_request_data));
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    public void updateListItems() {
        this.mAdapter.notifyDataSetChanged();
    }

    public void updateToolbarTitle(String title) {
        super.setTitle(new SpannableStringBuilder(title));
    }

    public void updateLegalHeader(String legalHeader) {
        if (TextUtils.isEmpty(legalHeader)) {
            this.mBinding.tvLegalHeader.setText("");
            return;
        }
        this.mBinding.tvLegalHeader.setText(Html.fromHtml(legalHeader));
        this.mBinding.tvLegalHeader.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void showProgressBar() {
        this.mBinding.progressBar.setVisibility(0);
    }

    public void hideProgressBar() {
        this.mBinding.progressBar.setVisibility(8);
    }

    public void showAddlMessageAndButton() {
        this.mBinding.tvAddlMessage.setVisibility(0);
        this.mBinding.etAddlMessage.setVisibility(0);
        this.mBinding.btSendRequest.setVisibility(0);
    }

    public void hideAddlMessageAndButton() {
        this.mBinding.tvAddlMessage.setVisibility(4);
        this.mBinding.etAddlMessage.setVisibility(4);
        this.mBinding.btSendRequest.setVisibility(4);
    }

    private void initList() {
        this.mAdapter = new SettingsPreferenceListAdapter(getApplicationContext(), this.mPresenter.getOptionsList());
        this.mBinding.rvRequestDataOptions.setAdapter(this.mAdapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), 1);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.account_settings_divider));
        this.mBinding.rvRequestDataOptions.addItemDecoration(itemDecoration);
        this.mBinding.rvRequestDataOptions.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 1, false));
        this.mBinding.rvRequestDataOptions.setNestedScrollingEnabled(false);
    }
}
