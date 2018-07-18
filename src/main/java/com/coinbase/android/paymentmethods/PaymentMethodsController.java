package com.coinbase.android.paymentmethods;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.PaymentMethodControllerBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.android.ui.LeftOffsetItemDecorator;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.v2.models.paymentMethods.Data;
import com.coinbase.v2.models.paymentMethods.Data.Type;
import javax.inject.Inject;

@ControllerScope
public class PaymentMethodsController extends ActionBarController implements PaymentMethodsScreen {
    public static final String INTENDING_TO_ADD_PAYMENT_METHOD = "INTENDING_TO_ADD_PAYMENT_METHOD ";
    PaymentMethodAdapter mAdapter;
    AvailablePaymentMethodAdapter mAvailablePaymentMethodAdapter;
    @Inject
    BackgroundDimmer mBackgroundDimmer;
    PaymentMethodControllerBinding mBinding;
    @Inject
    PaymentMethodsPresenter mPresenter;
    private boolean mShowAddButton = false;
    private boolean mShown = false;
    private String mTitle;

    private class AvailablePaymentMethodAdapter extends Adapter {
        private AvailablePaymentMethodAdapter() {
        }

        public int getItemCount() {
            return PaymentMethodsController.this.mPresenter.getAvailablePaymentMethodCount();
        }

        public int getItemViewType(int position) {
            return PaymentMethodsController.this.mPresenter.getAvailablePaymentMethodItemViewType(position);
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return PaymentMethodsController.this.mPresenter.onCreateAvailablePaymentMethodViewHolder(parent, viewType);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            PaymentMethodsController.this.mPresenter.onBindAvailablePaymentMethodViewHolder(holder, position);
        }
    }

    private class PaymentMethodAdapter extends Adapter {
        private PaymentMethodAdapter() {
        }

        public int getItemCount() {
            return PaymentMethodsController.this.mPresenter.getPaymentMethodCount();
        }

        public int getItemViewType(int position) {
            return PaymentMethodsController.this.mPresenter.getPaymentMethodItemViewType(position);
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return PaymentMethodsController.this.mPresenter.onCreatePaymentMethodViewHolder(parent, viewType);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            PaymentMethodsController.this.mPresenter.onBindPaymentMethodViewHolder(holder, position);
        }
    }

    public PaymentMethodsController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (PaymentMethodControllerBinding) DataBindingUtil.inflate(inflater, R.layout.payment_method_controller, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().paymentMethodsControllerSubcomponent(new PaymentMethodsPresenterModule(this, this)).inject(this);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        this.mAdapter = new PaymentMethodAdapter();
        this.mBinding.lvList.setAdapter(this.mAdapter);
        this.mBinding.lvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mAvailablePaymentMethodAdapter = new AvailablePaymentMethodAdapter();
        this.mBinding.rvAvailablePaymentMethods.setAdapter(this.mAvailablePaymentMethodAdapter);
        this.mBinding.rvAvailablePaymentMethods.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        this.mBinding.rvAvailablePaymentMethods.addItemDecoration(new LeftOffsetItemDecorator(getActivity(), (int) getApplicationContext().getResources().getDimension(R.dimen.margin_default)));
        this.mTitle = getApplicationContext().getString(R.string.linked_accounts);
        return this.mBinding.getRoot();
    }

    public boolean onBackPressed() {
        if (this.mPresenter == null) {
            return super.onBackPressed();
        }
        this.mBackgroundDimmer.undim(null);
        return this.mPresenter.onBackPressed();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mShown = false;
    }

    protected void onDetach(View view) {
        super.onDetach(view);
        this.mShown = false;
    }

    protected void onShow() {
        super.onShow();
        this.mShown = true;
        this.mPresenter.onResume(getArgs());
    }

    protected void onHide() {
        super.onHide();
        this.mShown = false;
        this.mPresenter.onHide();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mPresenter == null) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (!this.mPresenter.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    protected void onShowOptionsMenu(Menu menu) {
        if (this.mShowAddButton) {
            getActivity().getMenuInflater().inflate(R.menu.menu_add_linked_account, menu);
        }
        MenuItem item = menu.findItem(R.id.menu_add);
        if (item != null) {
            item.setVisible(this.mShowAddButton);
        }
        super.onShowOptionsMenu(menu);
    }

    public boolean onShownOptionsItemSelected(MenuItem item) {
        if (this.mPresenter == null) {
            return super.onShownOptionsItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.menu_add_account:
                this.mPresenter.onAddPaymentMethodsButtonClicked();
                return true;
            default:
                return super.onShownOptionsItemSelected(item);
        }
    }

    public void popToRoot() {
        getRouter().popToRoot();
    }

    public void hideProgress() {
        this.mBinding.progress.setVisibility(8);
    }

    public void notifyPaymentMethodsDataSetChanged() {
        this.mAdapter.notifyDataSetChanged();
    }

    public void showPaymentMethodsAdapter() {
        this.mBinding.lvList.setVisibility(0);
        this.mBinding.lvAvailablePaymentMethods.setVisibility(8);
        this.mShowAddButton = true;
        getActivity().invalidateOptionsMenu();
        setTitle(R.string.linked_accounts);
    }

    public void notifyAvailablePaymentMethodsDataSetChanged() {
        this.mAvailablePaymentMethodAdapter.notifyDataSetChanged();
    }

    public void showAvailablePaymentMethodsAdapter() {
        this.mBinding.flCoinbaseOverlay.setVisibility(8);
        this.mBinding.lvAvailablePaymentMethods.setVisibility(0);
        this.mBinding.lvList.setVisibility(8);
        this.mShowAddButton = false;
        getActivity().invalidateOptionsMenu();
        setTitle(R.string.add_account);
    }

    public void showNoAvailablePaymentMethods() {
        this.mBinding.flCoinbaseOverlay.setVisibility(0);
        this.mShowAddButton = false;
        getActivity().invalidateOptionsMenu();
    }

    public void showRemovePaymentMethodConfirmed(final Data method) {
        this.mBinding.llRemovePaymentMethodConfirmation.setVisibility(0);
        Pair<String, String> nameAndSuffix = PaymentMethodUtils.applyPaymentMethodNameNewlineTransformation(method.getName());
        if (nameAndSuffix != null) {
            this.mBinding.tvRemovePaymentMethodNameConfirmation.setText((CharSequence) nameAndSuffix.first);
            this.mBinding.tvRemovePaymentMethodNameConfirmationSuffix.setText((CharSequence) nameAndSuffix.second);
            this.mBinding.tvRemovePaymentMethodNameConfirmationSuffix.setVisibility(0);
        } else {
            this.mBinding.tvRemovePaymentMethodNameConfirmation.setText(method.getName());
            this.mBinding.tvRemovePaymentMethodNameConfirmationSuffix.setVisibility(4);
        }
        this.mBinding.tvRemovePaymentMethodConfirmation.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PaymentMethodsController.this.mPresenter.onRemovePaymentMethodConfirmed(method);
            }
        });
        this.mBinding.tvRemovePaymentMethodDescriptionConfirmation.setText(getApplicationContext().getString(R.string.confirm_remove_payment_method, new Object[]{getApplicationContext().getString(getRemoveTextNameForType(method.getType()))}));
        this.mBinding.tvRemovePaymentMethodConfirmationCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PaymentMethodsController.this.mPresenter.onRemovePaymentMethodCancel();
            }
        });
        this.mBackgroundDimmer.dim(this.mBinding.llRemovePaymentMethodConfirmation, null, true, 80);
        setTitle(R.string.linked_accounts);
    }

    public void hideRemovePaymentMethodView() {
        this.mBinding.llRemovePaymentMethodConfirmation.setVisibility(8);
        this.mBackgroundDimmer.undim(null);
        setTitle(R.string.linked_accounts);
    }

    public boolean isShown() {
        return this.mShown;
    }

    public Context getContext() {
        return getActivity();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(this.mTitle);
    }

    private int getRemoveTextForType(Type type) {
        switch (type) {
            case ACH_BANK_ACCOUNT:
            case SEPA_BANK_ACCOUNT:
            case BANK_WIRE:
            case FIAT_ACCOUNT:
            case XFERS:
                return R.string.remove_bank;
            case CREDIT_CARD:
            case DEBIT_CARD:
            case SECURE_3DS:
            case WORLDPAY_CARD:
                return R.string.remove_card;
            default:
                return R.string.remove;
        }
    }

    private int getRemoveTextNameForType(Type type) {
        switch (type) {
            case ACH_BANK_ACCOUNT:
            case SEPA_BANK_ACCOUNT:
            case BANK_WIRE:
            case FIAT_ACCOUNT:
            case XFERS:
                return R.string.remove_bank_name;
            case CREDIT_CARD:
            case DEBIT_CARD:
            case SECURE_3DS:
            case WORLDPAY_CARD:
                return R.string.remove_card_name;
            default:
                return R.string.remove_default_name;
        }
    }

    private void setTitle(int id) {
        this.mTitle = getApplicationContext().getString(id);
        setTitle(new SpannableStringBuilder(this.mTitle));
    }
}
