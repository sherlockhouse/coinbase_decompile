package com.coinbase.android.accounts;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.coinbase.android.databinding.LayoutEnableSendReceiveBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnableSendReceiveLayout extends LinearLayout {
    private LayoutEnableSendReceiveBinding mBinding;
    private final Context mContext;
    private final Logger mLogger;
    private AccountTransactionsPresenter mPresenter;

    public EnableSendReceiveLayout(Context context) {
        this(context, null);
    }

    public EnableSendReceiveLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EnableSendReceiveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mLogger = LoggerFactory.getLogger(EnableSendReceiveLayout.class);
        this.mContext = context;
        init(context);
    }

    private void init(Context context) {
        this.mBinding = LayoutEnableSendReceiveBinding.inflate(LayoutInflater.from(context), this, true);
        this.mBinding.btnEnableSendReceive.setOnClickListener(EnableSendReceiveLayout$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btnClose.setOnClickListener(EnableSendReceiveLayout$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$init$0(EnableSendReceiveLayout this_, View v) {
        if (this_.mPresenter == null) {
            this_.mLogger.error("Presenter is null in on click of enable send receive, should never happen");
        } else {
            this_.mPresenter.onEnableSendReceivedClicked();
        }
    }

    static /* synthetic */ void lambda$init$1(EnableSendReceiveLayout this_, View v) {
        if (this_.mPresenter == null) {
            this_.mLogger.error("Presenter is null in on click of close enable send receive, should never happen");
        } else {
            this_.mPresenter.onCloseEnableSendReceiveClicked();
        }
    }

    void setPresenter(AccountTransactionsPresenter presenter) {
        this.mPresenter = presenter;
    }

    void showEnableView(String title, String message, String button, int icon) {
        this.mBinding.llEnableSendReceiveContainer.setVisibility(0);
        this.mBinding.llProgressContainer.setVisibility(4);
        this.mBinding.progressTransparent.setVisibility(4);
        this.mBinding.ivEnableSendReceive.setBackground(ContextCompat.getDrawable(this.mContext, icon));
        this.mBinding.tvTitle.setText(title);
        this.mBinding.tvMessage.setText(message);
        this.mBinding.btnEnableSendReceive.setText(button);
        this.mBinding.llWhiteBoxContainer.setVisibility(0);
    }

    void showProgressView(boolean send) {
        this.mPresenter.initializeTracking(send);
        this.mBinding.llEnableSendReceiveContainer.setVisibility(4);
        if (send) {
            this.mBinding.progressTransparent.setVisibility(0);
            this.mBinding.llProgressContainer.setVisibility(4);
            this.mBinding.llWhiteBoxContainer.setVisibility(8);
            return;
        }
        this.mBinding.progressTransparent.setVisibility(4);
        this.mBinding.llProgressContainer.setVisibility(0);
        this.mBinding.llWhiteBoxContainer.setVisibility(0);
    }
}
