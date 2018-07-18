package com.coinbase.android.paymentmethods.linkedaccounts;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.GlideApp;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutLinkedAccountItemBinding;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.v2.models.paymentMethods.Data;
import javax.inject.Inject;

@ControllerScope
public class LinkedAccountItemLayout extends LinearLayout {
    private LayoutLinkedAccountItemBinding mBinding;
    @Inject
    LinkedAccountConnector mConnector;
    private Context mContext;
    @Inject
    PaymentMethodUtils mPaymentMethodUtils;

    public LinkedAccountItemLayout(Context context) {
        this(context, null);
    }

    public LinkedAccountItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinkedAccountItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mBinding = LayoutLinkedAccountItemBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().inject(this);
        this.mContext = context;
    }

    public void showLinkedAccountMethod(String name, String number, String limit, boolean limitReached, String imageUrl, int defaultDrawable) {
        if (name == null && number == null) {
            showError();
            return;
        }
        this.mBinding.rlLoading.setVisibility(8);
        this.mBinding.rlMissingLinkedAccount.setVisibility(8);
        this.mBinding.rlFiatAccount.setVisibility(8);
        this.mBinding.tvLinkedAccountName.setText(name);
        this.mBinding.tvLinkedAccountNumber.setText(number);
        if (TextUtils.isEmpty(limit)) {
            this.mBinding.tvLinkedAccountLimit.setVisibility(8);
        } else {
            int color;
            this.mBinding.tvLinkedAccountLimit.setVisibility(0);
            this.mBinding.tvLinkedAccountLimit.setText(this.mContext.getString(R.string.buy_max_amount, new Object[]{limit}));
            TextView textView = this.mBinding.tvLinkedAccountLimit;
            if (limitReached) {
                color = ContextCompat.getColor(this.mContext, R.color.max_reached_text_color);
            } else {
                color = ContextCompat.getColor(this.mContext, R.color.primary_mystique_light_text_color);
            }
            textView.setTextColor(color);
        }
        if (imageUrl != null) {
            GlideApp.with(this.mContext).load((Object) imageUrl).into(this.mBinding.ivLinkedAccountIcon);
        } else {
            this.mBinding.ivLinkedAccountIcon.setImageResource(defaultDrawable);
        }
        this.mBinding.rlLinkedAccount.setVisibility(0);
        this.mBinding.rlLinkedAccount.setOnClickListener(LinkedAccountItemLayout$$Lambda$1.lambdaFactory$(this));
    }

    public void showFiatAccountMethod(Data paymentMethod, String name, String balance) {
        if (paymentMethod == null || (name == null && balance == null)) {
            showError();
            return;
        }
        this.mBinding.rlLoading.setVisibility(8);
        this.mBinding.rlMissingLinkedAccount.setVisibility(8);
        this.mBinding.rlLinkedAccount.setVisibility(8);
        this.mPaymentMethodUtils.setFiatImageBackground(this.mContext, this.mBinding.ivFiatBackground, paymentMethod);
        this.mBinding.tvFiatSymbol.setText(this.mPaymentMethodUtils.getFiatAccountCurrencySymbol(paymentMethod));
        this.mBinding.tvFiatName.setText(name);
        if (TextUtils.isEmpty(balance)) {
            this.mBinding.tvFiatBalance.setVisibility(8);
        } else {
            this.mBinding.tvFiatBalance.setVisibility(0);
            this.mBinding.tvFiatBalance.setText(balance);
        }
        this.mBinding.rlFiatAccount.setVisibility(0);
        this.mBinding.rlFiatAccount.setOnClickListener(LinkedAccountItemLayout$$Lambda$2.lambdaFactory$(this));
    }

    public void showMissingLinkedAccount(String addPrompt) {
        this.mBinding.rlLoading.setVisibility(8);
        this.mBinding.rlFiatAccount.setVisibility(8);
        this.mBinding.rlLinkedAccount.setVisibility(8);
        this.mBinding.tvAddAccount.setText(addPrompt);
        this.mBinding.rlMissingLinkedAccount.setVisibility(0);
        this.mBinding.rlMissingLinkedAccount.setOnClickListener(LinkedAccountItemLayout$$Lambda$3.lambdaFactory$(this));
    }

    public void showError() {
        this.mBinding.rlFiatAccount.setVisibility(8);
        this.mBinding.rlLinkedAccount.setVisibility(8);
        this.mBinding.rlMissingLinkedAccount.setVisibility(8);
        this.mBinding.rlLoading.setVisibility(0);
    }
}
