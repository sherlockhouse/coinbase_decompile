package com.coinbase.android.buysell;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerBuyBinding;
import com.coinbase.android.databinding.ListItemQuickstartBinding;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountItemLayout;
import com.coinbase.android.phone.VerifyPhoneHandler.VerifyPhoneCaller;
import com.coinbase.android.quickstart.QuickstartItem;
import com.coinbase.android.quickstart.QuickstartManager;
import com.coinbase.android.quickstart.QuickstartModule;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.v2.models.paymentMethods.Data;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class BuyController extends ActionBarController implements BuyScreen {
    private boolean isInForeground;
    @Inject
    AppCompatActivity mActivity;
    private ControllerBuyBinding mBinding;
    @Inject
    BuyPresenter mPresenter;
    private VerifyPhoneCaller mVerifyPhoneCaller;

    public BuyController(Bundle args) {
        super(args);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerBuyBinding) DataBindingUtil.inflate(inflater, R.layout.controller_buy, container, false);
        onAttachToolbar(this.mBinding.apbLayout.getToolbar());
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addBuyControllerSubcomponent(new QuickstartModule(getActivity(), this), new BuyPresenterModule(this, this)).inject(this);
        this.mBinding.llSwap.setOnClickListener(BuyController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btPreview.setText(getApplicationContext().getString(R.string.preview_cta, new Object[]{getApplicationContext().getString(R.string.buy_action)}));
        this.mBinding.btPreview.setOnClickListener(BuyController$$Lambda$2.lambdaFactory$(this));
        initQuickstartView();
        return this.mBinding.getRoot();
    }

    static /* synthetic */ void lambda$onCreateView$1(BuyController this_, View view) {
        view.performHapticFeedback(1);
        this_.mPresenter.onPreviewButtonClicked();
    }

    protected int getThemeColor() {
        return ContextCompat.getColor(getActivity(), R.color.white);
    }

    protected int getToolbarTextColor() {
        return R.color.primary_mystique_text_color;
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    protected void onAttach(View view) {
        super.onAttach(view);
        this.isInForeground = true;
    }

    protected void onDetach(View view) {
        super.onDetach(view);
        this.isInForeground = false;
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.buy));
    }

    public void updateTitle(String currencyName) {
        if (!TextUtils.isEmpty(currencyName)) {
            super.setTitle(new SpannableStringBuilder(String.format(getApplicationContext().getString(R.string.buy_currency_name_title), new Object[]{currencyName})));
        }
    }

    public void showMissingPaymentMethod() {
        this.mBinding.buyLinkedAccountItemLayout.showMissingLinkedAccount(getApplicationContext().getString(R.string.buy_add_payment_method));
    }

    public void showFiatPaymentMethod(Data paymentMethod, String name, String balance) {
        this.mBinding.buyLinkedAccountItemLayout.showFiatAccountMethod(paymentMethod, name, balance);
    }

    public void showPaymentMethod(Data paymentMethod, String limit, boolean limitReached) {
        Pair<String, String> nameAndNumber = this.mPresenter.getFormattedPaymentMethodNameAndNumberDisplay(paymentMethod);
        if (nameAndNumber == null) {
            this.mBinding.buyLinkedAccountItemLayout.showError();
            return;
        }
        this.mBinding.buyLinkedAccountItemLayout.showLinkedAccountMethod((String) nameAndNumber.first, (String) nameAndNumber.second, limit, limitReached, paymentMethod.getImage(), this.mPresenter.getResourceForType(paymentMethod.getType()));
    }

    public void updatePrimaryAmountText(SpannableStringBuilder stringBuilder) {
        this.mBinding.tvAmount.setTextSize(0, getResources().getDimension(R.dimen.currency_amount));
        this.mBinding.tvAmount.setText(stringBuilder);
    }

    public void updateSecondaryCurrencyCode(String currencyCode) {
        this.mBinding.tvSecondaryCurrencyCode.setText(currencyCode);
    }

    public void showInvalidKeystroke() {
        this.mBinding.tvAmount.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.shake));
    }

    public void showProgressOverlay() {
        this.mBinding.btPreview.setEnabled(false);
        this.mBinding.rlProgressBarOverlay.setVisibility(0);
    }

    public void hideProgressOverlay() {
        this.mBinding.btPreview.setEnabled(true);
        this.mBinding.rlProgressBarOverlay.setVisibility(8);
    }

    public void showRegionUnsupported(String country) {
        this.mBinding.rlBuyContent.setVisibility(8);
        this.mBinding.quickstartOverlay.llOverlay.setVisibility(8);
        this.mBinding.quickstartOverlay.llUnavailable.setVisibility(0);
        this.mBinding.quickstartOverlay.rlQuickStart.setVisibility(0);
        if (country == null) {
            country = getApplicationContext().getString(R.string.your_country);
        }
        this.mBinding.quickstartOverlay.tvUnavailableNotice.setText(String.format(getApplicationContext().getString(R.string.feature_not_available_in_country_details), new Object[]{country}));
        this.mBinding.quickstartOverlay.btnSubscribe.setOnClickListener(BuyController$$Lambda$3.lambdaFactory$(this));
    }

    public void showQuickstartItems(List<QuickstartItem> quickstartItems) {
        this.mBinding.rlBuyContent.setVisibility(8);
        this.mBinding.quickstartOverlay.llUnavailable.setVisibility(8);
        this.mBinding.quickstartOverlay.llOverlay.setVisibility(0);
        this.mBinding.quickstartOverlay.rlQuickStart.setVisibility(0);
        if (getActivity() != null) {
            this.mBinding.quickstartOverlay.lvOverlayQuickstart.setAdapter(new ArrayAdapter<QuickstartItem>(getActivity(), R.layout.list_item_quickstart, quickstartItems) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    ListItemQuickstartBinding binding = QuickstartManager.prepareItemView(BuyController.this.getActivity(), (QuickstartItem) getItem(position));
                    binding.ibtnQuickstartItemDismiss.setVisibility(4);
                    return binding.getRoot();
                }
            });
            if (quickstartItems.size() == 1) {
                this.mBinding.quickstartOverlay.lvOverlayQuickstart.setDivider(null);
                this.mBinding.quickstartOverlay.lvOverlayQuickstart.setDividerHeight(0);
            }
        }
    }

    public void showBuyContent(boolean hidePaymentMethodView) {
        int i = 8;
        this.mBinding.quickstartOverlay.rlQuickStart.setVisibility(8);
        this.mBinding.rlBuyContent.setVisibility(0);
        LinkedAccountItemLayout linkedAccountItemLayout = this.mBinding.buyLinkedAccountItemLayout;
        if (!hidePaymentMethodView) {
            i = 0;
        }
        linkedAccountItemLayout.setVisibility(i);
    }

    public void showQuickBuys(String currencyCode) {
        this.mBinding.llQuickBuy.setVisibility(0);
        this.mBinding.layoutQuickBuy.setVisibility(0);
        this.mBinding.layoutQuickBuy.initWithCurrency(currencyCode);
    }

    public void hideQuickBuys() {
        this.mBinding.llQuickBuy.setVisibility(8);
        this.mBinding.layoutQuickBuy.setVisibility(8);
    }

    private void initQuickstartView() {
        this.mVerifyPhoneCaller = new VerifyPhoneCaller() {
            public FragmentManager getCallerFragmentManager() {
                return BuyController.this.mActivity.getSupportFragmentManager();
            }

            public ActionBarController getCallingController() {
                return BuyController.this;
            }

            public Context getContext() {
                return getActivity();
            }

            public boolean isForeground() {
                return BuyController.this.isInForeground;
            }

            public Activity getActivity() {
                return BuyController.this.getActivity();
            }
        };
        this.mBinding.quickstartOverlay.tvOverlayDetails.setText(R.string.buy_verification_details);
        this.mBinding.quickstartOverlay.ivOverlayIcon.setImageResource(R.drawable.bank_large);
        this.mBinding.quickstartOverlay.lvOverlayQuickstart.setOnItemClickListener(BuyController$$Lambda$4.lambdaFactory$(this));
    }
}
