package com.coinbase.android.phone;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.FragmentPhoneNumberBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import javax.inject.Inject;

@ControllerScope
public class PhoneNumberController extends ActionBarController implements PhoneNumberScreen {
    private PhoneNumberAdapter mAdapter;
    private FragmentPhoneNumberBinding mBinding;
    @Inject
    PhoneNumberPresenter mPresenter;

    public PhoneNumberController(Bundle bundle) {
        super(bundle);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (FragmentPhoneNumberBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_phone_number, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addPhoneNumberControllerSubcomponent(new PhoneNumberPresenterModule(this)).inject(this);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        initView();
        return this.mBinding.getRoot();
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onResume(getArgs());
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onDestroy();
    }

    public SpannableStringBuilder getTitle() {
        if (getApplicationContext() == null) {
            return null;
        }
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.manage_phone));
    }

    public void onShowOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_phone_number, menu);
    }

    public boolean onShownOptionsItemSelected(MenuItem item) {
        if (this.mPresenter == null) {
            return super.onShownOptionsItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.menu_phone_number_add:
                onAddPhoneNumberMenuClicked();
                return true;
            default:
                return super.onShownOptionsItemSelected(item);
        }
    }

    private void onAddPhoneNumberMenuClicked() {
        this.mPresenter.addNewPhoneNumber();
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_FROM_SETTINGS, new String[0]);
    }

    private void initView() {
        this.mAdapter = new PhoneNumberAdapter(this.mPresenter);
        this.mBinding.rvPhoneNumbers.setAdapter(this.mAdapter);
        this.mBinding.rvPhoneNumbers.setEmptyView(this.mBinding.rlEmptyView);
        this.mBinding.rvPhoneNumbers.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        this.mBinding.rvPhoneNumbers.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 1));
    }

    public void hideProgressBar() {
        this.mBinding.progress.setVisibility(8);
    }

    public void showProgressBar() {
        this.mBinding.progress.setVisibility(0);
    }

    public void notifyDataSetChanged() {
        this.mAdapter.notifyDataSetChanged();
    }

    public void showVerifyPhoneNumberDialog(String id) {
        VerifyPhoneDialogFragment.newInstance(id, null, null, null).show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), "Verify");
    }

    public void deletePhoneNumber(String id, boolean isVerified) {
        new DeletePhoneTask((AppCompatActivity) getActivity(), getActivity(), id, "", isVerified, true).deletePhoneNumber();
    }
}
