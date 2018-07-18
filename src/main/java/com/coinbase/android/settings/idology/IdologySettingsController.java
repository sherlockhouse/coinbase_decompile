package com.coinbase.android.settings.idology;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerIdologySettingsBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.models.idology.Data;
import javax.inject.Inject;

@ControllerScope
public class IdologySettingsController extends ActionBarController implements IdologySettingsScreen {
    ControllerIdologySettingsBinding mBinding;
    private MenuItem mContinueMenuItem;
    @Inject
    IdologySettingsPresenter mPresenter;

    public IdologySettingsController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerIdologySettingsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_idology_settings, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addIdologySettingsControllerSubcomponent(new IdologySettingsPresenterModule(this)).inject(this);
        this.mPresenter.onViewCreated(getArgs());
        return this.mBinding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getResources().getString(R.string.verify_identity));
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
        this.mBinding.idologyFormLayout.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
        this.mBinding.idologyFormLayout.onHide();
    }

    protected boolean onBackPressed() {
        return this.mBinding.idologyFormLayout.hideVisibleLayout();
    }

    public void onShowOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_idology_settings, menu);
        this.mContinueMenuItem = menu.findItem(R.id.menu_continue);
        this.mBinding.idologyFormLayout.onFormUpdated();
    }

    public boolean onShownOptionsItemSelected(MenuItem item) {
        if (this.mPresenter == null) {
            return super.onShownOptionsItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.menu_continue:
                Utils.hideKeyboard(getActivity());
                this.mPresenter.onContinueClicked();
                return true;
            default:
                return super.onShownOptionsItemSelected(item);
        }
    }

    public void setContinueMenuEnabled(boolean isEnabled) {
        if (this.mContinueMenuItem != null) {
            this.mContinueMenuItem.setEnabled(isEnabled);
        }
    }

    public void submitForm() {
        this.mBinding.idologyFormLayout.submitForm();
    }

    public void setIdologyData(Data idology) {
        this.mBinding.idologyFormLayout.setData(idology);
    }
}
