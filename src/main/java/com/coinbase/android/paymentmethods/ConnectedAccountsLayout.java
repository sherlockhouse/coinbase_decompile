package com.coinbase.android.paymentmethods;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.GlideApp;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutConnectedAccountListBinding;
import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.android.ui.LastItemHiddenItemDecorator;
import com.coinbase.v2.models.paymentMethods.Data;
import javax.inject.Inject;

public class ConnectedAccountsLayout extends LinearLayout implements ConnectedAccountsScreen {
    @Inject
    BackgroundDimmer mBackgroundDimmer;
    private LayoutConnectedAccountListBinding mBinding;
    private ConnectedAccountsListAdapter mConnectedAccountListAdapter;
    private Context mContext;
    @Inject
    ConnectedAccountsPresenter mPresenter;

    public ConnectedAccountsLayout(Context context) {
        this(context, null);
    }

    public ConnectedAccountsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConnectedAccountsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        this.mBinding = LayoutConnectedAccountListBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().addConnectedAccountsLayoutSubcomponent(new ConnectedAccountsPresenterModule(this)).inject(this);
        LastItemHiddenItemDecorator itemDecoration = new LastItemHiddenItemDecorator(context, (int) context.getResources().getDimension(R.dimen.margin_default));
        itemDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.account_settings_divider));
        this.mBinding.rlConnectedAccounts.addItemDecoration(itemDecoration);
        this.mBinding.rlConnectedAccounts.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
    }

    public void onShow() {
        this.mPresenter.onShow();
    }

    public void onHide() {
        this.mPresenter.onHide();
    }

    public boolean isRemovePaymentViewVisible() {
        return this.mBinding.rlRemovePaymentView.getVisibility() == 0;
    }

    public void hideRemovePaymentView() {
        this.mBinding.rlRemovePaymentView.setVisibility(8);
        this.mBackgroundDimmer.undim(null);
    }

    public void notifyAccountListAdapterChanged() {
        if (this.mConnectedAccountListAdapter == null) {
            this.mConnectedAccountListAdapter = new ConnectedAccountsListAdapter(this.mContext, this.mPresenter);
            this.mBinding.rlConnectedAccounts.setAdapter(this.mConnectedAccountListAdapter);
            this.mBinding.rlLoading.setVisibility(8);
        }
        this.mConnectedAccountListAdapter.notifyDataSetChanged();
    }

    public void showRemovePaymentFooterView(Data method) {
        this.mBinding.rlRemovePaymentView.setVisibility(0);
        Pair<String, String> nameAndNumber = this.mPresenter.getFormattedPaymentMethodNameAndNumberDisplay(method);
        this.mBinding.tvPaymentMethodName.setText(nameAndNumber == null ? "" : (CharSequence) nameAndNumber.first);
        this.mBinding.tvPaymetMethodNumber.setText(nameAndNumber == null ? "" : (CharSequence) nameAndNumber.second);
        if (method.getImage() != null) {
            GlideApp.with(this.mContext).load(method.getImage()).into(this.mBinding.ivPaymentMethodIcon);
        } else {
            this.mBinding.ivPaymentMethodIcon.setImageResource(this.mPresenter.getResourceForType(method.getType()));
        }
        int textResourceForType = this.mPresenter.getTextNameForType(method.getType());
        String textForType = textResourceForType == -1 ? "" : this.mContext.getString(textResourceForType);
        this.mBinding.tvRemove.setText(this.mContext.getString(R.string.remove_action_text, new Object[]{textForType}));
        this.mBinding.tvRemove.setOnClickListener(ConnectedAccountsLayout$$Lambda$1.lambdaFactory$(this, method));
        if (method.getVerified().booleanValue()) {
            this.mBinding.tvVerify.setVisibility(8);
            this.mBinding.btDivider.setVisibility(8);
        } else {
            this.mBinding.tvVerify.setVisibility(0);
            this.mBinding.btDivider.setVisibility(0);
            this.mBinding.tvVerify.setText(this.mContext.getString(R.string.verify_action_text, new Object[]{textForType}));
            this.mBinding.tvVerify.setOnClickListener(ConnectedAccountsLayout$$Lambda$2.lambdaFactory$(this, method));
        }
        this.mBackgroundDimmer.dim(this.mBinding.rlRemovePaymentView, null, true, 80);
    }

    public void hideRemovePaymentFooterView() {
        this.mBinding.rlRemovePaymentView.setVisibility(8);
        this.mBackgroundDimmer.undim(null);
    }

    public void showRemoveConfirmationDialog(Data connectedAccount) {
        this.mBinding.rlRemovePaymentView.setVisibility(8);
        this.mBinding.tvConfirmRemove.setOnClickListener(ConnectedAccountsLayout$$Lambda$3.lambdaFactory$(this, connectedAccount));
        this.mBinding.tvCancel.setOnClickListener(ConnectedAccountsLayout$$Lambda$4.lambdaFactory$(this, connectedAccount));
        this.mBinding.tvRemoveWarning.setText(this.mContext.getString(R.string.remove_connected_account_confirmation, new Object[]{this.mContext.getString(this.mPresenter.getTextNameForType(connectedAccount.getType()))}));
        this.mBackgroundDimmer.dim(this.mBinding.llRemoveConfirmation, null, false, 17);
    }

    public void hideRemoveConfirmationDialog() {
        this.mBinding.llRemoveConfirmation.setVisibility(8);
        this.mBackgroundDimmer.undim(null);
    }
}
