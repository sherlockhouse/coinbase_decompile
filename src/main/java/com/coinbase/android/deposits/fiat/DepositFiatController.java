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
import com.coinbase.android.databinding.ControllerDepositFiatBinding;
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
public class DepositFiatController extends ActionBarController implements DepositFiatScreen {
    private boolean isInForeground;
    private ControllerDepositFiatBinding mBinding;
    @Inject
    AppCompatActivity mCallingActivity;
    @Inject
    DepositFiatPresenter mDepositFiatPresenter;

    public DepositFiatController(Bundle args) {
        super(args);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerDepositFiatBinding) DataBindingUtil.inflate(inflater, R.layout.controller_deposit_fiat, container, false);
        onAttachToolbar(this.mBinding.apbLayout.getToolbar());
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().depositFiatControllerSubcomponent(new QuickstartModule(getActivity(), this), new DepositFiatPresenterModule(this, this)).inject(this);
        this.mBinding.btPreview.setOnClickListener(DepositFiatController$$Lambda$1.lambdaFactory$(this));
        initQuickstartView();
        return this.mBinding.getRoot();
    }

    static /* synthetic */ void lambda$onCreateView$0(DepositFiatController this_, View v) {
        v.performHapticFeedback(1);
        this_.mDepositFiatPresenter.onPreviewButtonClicked();
    }

    protected void onShow() {
        super.onShow();
        this.mDepositFiatPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mDepositFiatPresenter.onHide();
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
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.deposit));
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

    public void showDepositContent() {
        this.mBinding.quickstartContent.rlQuickStart.setVisibility(8);
        this.mBinding.rlDepositContent.setVisibility(0);
    }

    public void showQuickstartContent(List<QuickstartItem> quickstartItems) {
        this.mBinding.rlDepositContent.setVisibility(8);
        this.mBinding.quickstartContent.llUnavailable.setVisibility(8);
        this.mBinding.quickstartContent.llOverlay.setVisibility(0);
        this.mBinding.quickstartContent.rlQuickStart.setVisibility(0);
        if (getActivity() != null) {
            this.mBinding.quickstartContent.lvOverlayQuickstart.setAdapter(new ArrayAdapter<QuickstartItem>(getActivity(), R.layout.list_item_quickstart, quickstartItems) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    ListItemQuickstartBinding binding = QuickstartManager.prepareItemView(DepositFiatController.this.getActivity(), (QuickstartItem) getItem(position));
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

    public void showPaymentMethod(Data paymentMethod, String limit, boolean limitReached) {
        Pair<String, String> nameAndNumber = this.mDepositFiatPresenter.getFormattedPaymentMethodNameAndNumberDisplay(paymentMethod);
        if (nameAndNumber == null) {
            this.mBinding.linkedAccountItemLayout.showError();
            return;
        }
        this.mBinding.linkedAccountItemLayout.showLinkedAccountMethod((String) nameAndNumber.first, (String) nameAndNumber.second, limit, limitReached, paymentMethod.getImage(), this.mDepositFiatPresenter.getResourceForType(paymentMethod.getType()));
    }

    @SuppressLint({"StringFormatInvalid"})
    public void showRegionUnsupported(String country) {
        this.mBinding.rlDepositContent.setVisibility(8);
        this.mBinding.quickstartContent.llOverlay.setVisibility(8);
        this.mBinding.quickstartContent.llUnavailable.setVisibility(0);
        this.mBinding.quickstartContent.rlQuickStart.setVisibility(0);
        if (country == null) {
            country = getApplicationContext().getString(R.string.your_country);
        }
        this.mBinding.quickstartContent.tvUnavailableNotice.setText(String.format(getApplicationContext().getString(R.string.feature_not_available_in_country_details), new Object[]{country}));
        this.mBinding.quickstartContent.btnSubscribe.setOnClickListener(DepositFiatController$$Lambda$2.lambdaFactory$(this));
    }

    private void initQuickstartView() {
        VerifyPhoneCaller verifyPhoneCaller = new VerifyPhoneCaller() {
            public FragmentManager getCallerFragmentManager() {
                return DepositFiatController.this.mCallingActivity.getSupportFragmentManager();
            }

            public ActionBarController getCallingController() {
                return DepositFiatController.this;
            }

            public Context getContext() {
                return getActivity();
            }

            public boolean isForeground() {
                return DepositFiatController.this.isInForeground;
            }

            public Activity getActivity() {
                return DepositFiatController.this.getActivity();
            }
        };
        this.mBinding.quickstartContent.tvOverlayDetails.setText(R.string.quickstart_deposit);
        this.mBinding.quickstartContent.ivOverlayIcon.setImageResource(R.drawable.bank_large);
        this.mBinding.quickstartContent.lvOverlayQuickstart.setOnItemClickListener(DepositFiatController$$Lambda$3.lambdaFactory$(this, verifyPhoneCaller));
    }
}
