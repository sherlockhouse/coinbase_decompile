package com.coinbase.android.deposits.fiat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerWithdrawFiatBinding;
import com.coinbase.android.databinding.ListItemQuickstartBinding;
import com.coinbase.android.phone.VerifyPhoneHandler.VerifyPhoneCaller;
import com.coinbase.android.quickstart.QuickstartItem;
import com.coinbase.android.quickstart.QuickstartManager;
import com.coinbase.android.quickstart.QuickstartModule;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.v2.models.paymentMethods.Data;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class WithdrawFiatController extends ActionBarController implements WithdrawFiatScreen {
    private boolean isInForeground;
    private ControllerWithdrawFiatBinding mBinding;
    @Inject
    AppCompatActivity mCallingActivity;
    @Inject
    WithdrawFiatPresenter mWithdrawFiatPresenter;

    public WithdrawFiatController(Bundle args) {
        super(args);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerWithdrawFiatBinding) DataBindingUtil.inflate(inflater, R.layout.controller_withdraw_fiat, container, false);
        onAttachToolbar(this.mBinding.apbLayout.getToolbar());
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().withdrawFiatControllerSubcomponent(new QuickstartModule(getActivity(), this), new WithdrawFiatPresenterModule(this, this, this.mBinding.apbLayout)).inject(this);
        this.mBinding.btPreview.setOnClickListener(WithdrawFiatController$$Lambda$1.lambdaFactory$(this));
        initQuickstartView();
        return this.mBinding.getRoot();
    }

    static /* synthetic */ void lambda$onCreateView$0(WithdrawFiatController this_, View v) {
        v.performHapticFeedback(1);
        this_.mWithdrawFiatPresenter.onPreviewButtonClicked();
    }

    protected void onShow() {
        super.onShow();
        this.mWithdrawFiatPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mWithdrawFiatPresenter.onHide();
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
        if (getApplicationContext() == null) {
            return null;
        }
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.withdraw));
    }

    protected int getThemeColor() {
        return ContextCompat.getColor(getActivity(), R.color.white);
    }

    protected int getToolbarTextColor() {
        return R.color.primary_mystique_text_color;
    }

    public void showProgressDialog() {
        this.mBinding.progressBar.setVisibility(0);
    }

    public void hideProgressDialog() {
        this.mBinding.progressBar.setVisibility(8);
    }

    public void showWithdrawContent() {
        this.mBinding.quickstartContent.rlQuickStart.setVisibility(8);
        this.mBinding.rlWithdrawContent.setVisibility(0);
    }

    public void showQuickstartContent(List<QuickstartItem> quickstartItems) {
        this.mBinding.rlWithdrawContent.setVisibility(8);
        this.mBinding.quickstartContent.llUnavailable.setVisibility(8);
        this.mBinding.quickstartContent.llOverlay.setVisibility(0);
        this.mBinding.quickstartContent.rlQuickStart.setVisibility(0);
        if (getActivity() != null) {
            this.mBinding.quickstartContent.lvOverlayQuickstart.setAdapter(new ArrayAdapter<QuickstartItem>(getActivity(), R.layout.list_item_quickstart, quickstartItems) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    ListItemQuickstartBinding binding = QuickstartManager.prepareItemView(WithdrawFiatController.this.getActivity(), (QuickstartItem) getItem(position));
                    binding.ibtnQuickstartItemDismiss.setVisibility(4);
                    return binding.getRoot();
                }
            });
            if (quickstartItems.size() == 1) {
                this.mBinding.quickstartContent.lvOverlayQuickstart.setDivider(null);
                this.mBinding.quickstartContent.lvOverlayQuickstart.setDividerHeight(0);
            }
        }
    }

    public void showInvalidKeystroke() {
        this.mBinding.tvAmount.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.shake));
    }

    public void setAmountText(SpannableStringBuilder text) {
        this.mBinding.tvAmount.setText(text);
    }

    public void showMissingPaymentMethod() {
        this.mBinding.linkedAccountItemLayout.showMissingLinkedAccount(getApplicationContext().getString(R.string.generic_add_payment_method));
    }

    public void showPaymentMethod(Data paymentMethod) {
        Pair<String, String> nameAndNumber = this.mWithdrawFiatPresenter.getFormattedPaymentMethodNameAndNumberDisplay(paymentMethod);
        if (nameAndNumber == null) {
            this.mBinding.linkedAccountItemLayout.showError();
        } else {
            this.mBinding.linkedAccountItemLayout.showLinkedAccountMethod((String) nameAndNumber.first, (String) nameAndNumber.second, null, false, paymentMethod.getImage(), this.mWithdrawFiatPresenter.getResourceForType(paymentMethod.getType()));
        }
    }

    @SuppressLint({"StringFormatInvalid"})
    public void showRegionUnsupported(String country) {
        this.mBinding.rlWithdrawContent.setVisibility(8);
        this.mBinding.quickstartContent.llOverlay.setVisibility(8);
        this.mBinding.quickstartContent.llUnavailable.setVisibility(0);
        this.mBinding.quickstartContent.rlQuickStart.setVisibility(0);
        if (country == null) {
            country = getApplicationContext().getString(R.string.your_country);
        }
        this.mBinding.quickstartContent.tvUnavailableNotice.setText(String.format(getApplicationContext().getString(R.string.feature_not_available_in_country_details), new Object[]{country}));
        this.mBinding.quickstartContent.btnSubscribe.setOnClickListener(WithdrawFiatController$$Lambda$2.lambdaFactory$(this));
    }

    private void initQuickstartView() {
        VerifyPhoneCaller verifyPhoneCaller = new VerifyPhoneCaller() {
            public FragmentManager getCallerFragmentManager() {
                return WithdrawFiatController.this.mCallingActivity.getSupportFragmentManager();
            }

            public ActionBarController getCallingController() {
                return WithdrawFiatController.this;
            }

            public Context getContext() {
                return getActivity();
            }

            public boolean isForeground() {
                return WithdrawFiatController.this.isInForeground;
            }

            public Activity getActivity() {
                return WithdrawFiatController.this.getActivity();
            }
        };
        this.mBinding.quickstartContent.tvOverlayDetails.setText(R.string.quickstart_withdraw);
        this.mBinding.quickstartContent.ivOverlayIcon.setImageResource(R.drawable.bank_large);
        this.mBinding.quickstartContent.lvOverlayQuickstart.setOnItemClickListener(WithdrawFiatController$$Lambda$3.lambdaFactory$(this, verifyPhoneCaller));
    }
}
