package com.coinbase.android.buysell;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutQuickBuyListBinding;
import com.coinbase.android.ui.HorizontalSpaceItemDecorator;
import com.coinbase.android.utils.MoneyFormatterUtil;
import javax.inject.Inject;

@ControllerScope
public class QuickBuyListLayout extends LinearLayout {
    private LayoutQuickBuyListBinding mBinding;
    private Context mContext;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;
    @Inject
    QuickBuyConnector mQuickBuyConnector;

    public QuickBuyListLayout(Context context) {
        this(context, null);
    }

    public QuickBuyListLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickBuyListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mBinding = LayoutQuickBuyListBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().inject(this);
        this.mContext = context;
        this.mBinding.rlQuickBuys.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        this.mBinding.rlQuickBuys.addItemDecoration(new HorizontalSpaceItemDecorator((int) context.getResources().getDimension(R.dimen.custom_buy_sell_button_right_margin), true));
    }

    void initWithCurrency(String currencyCode) {
        if (!TextUtils.isEmpty(currencyCode)) {
            this.mBinding.rlQuickBuys.setAdapter(new QuickBuyListAdapter(currencyCode, this.mMoneyFormatterUtil, this.mQuickBuyConnector));
        }
    }
}
