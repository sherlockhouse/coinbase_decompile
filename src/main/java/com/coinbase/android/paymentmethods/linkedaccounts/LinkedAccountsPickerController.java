package com.coinbase.android.paymentmethods.linkedaccounts;

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
import com.coinbase.android.databinding.ControllerLinkedAccountsPickerBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class LinkedAccountsPickerController extends ActionBarController implements LinkedAccountsPickerScreen {
    private PaymentMethodListAdapter mAdapter;
    private ControllerLinkedAccountsPickerBinding mBinding;
    @Inject
    LinkedAccountsPickerPresenter mPresenter;

    public LinkedAccountsPickerController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerLinkedAccountsPickerBinding) DataBindingUtil.inflate(inflater, R.layout.controller_linked_accounts_picker, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addLinkedAccountsPickerControllerSubcomponent(new LinkedAccountsPickerPresenterModule(this, this)).inject(this);
        onAttachToolbar(this.mBinding.apbLayout.getToolbar());
        this.mPresenter.onCreateView(getArgs());
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), 1);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.linked_accounts_divider));
        this.mBinding.rlPaymentMethods.addItemDecoration(itemDecoration);
        this.mBinding.rlPaymentMethods.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        this.mAdapter = new PaymentMethodListAdapter(getActivity(), this.mPresenter);
        this.mBinding.rlPaymentMethods.setAdapter(this.mAdapter);
        return this.mBinding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.linked_accounts));
    }

    protected int getThemeColor() {
        return ContextCompat.getColor(getActivity(), R.color.white);
    }

    protected int getUpIndicatorColor() {
        return R.color.new_up_icon_color;
    }

    protected int getToolbarTextColor() {
        return R.color.primary_mystique_text_color;
    }
}
