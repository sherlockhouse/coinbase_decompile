package com.coinbase.android.wbl;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.databinding.LayoutAvailableBalanceAppBarBinding;

public class AvailableBalanceAppBarLayout extends LinearLayout implements AvailableBalanceAppBarScreen {
    LayoutAvailableBalanceAppBarBinding mBinding;
    Toolbar mToolbar;

    public AvailableBalanceAppBarLayout(Context context) {
        this(context, null);
    }

    public AvailableBalanceAppBarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AvailableBalanceAppBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mBinding = LayoutAvailableBalanceAppBarBinding.inflate(LayoutInflater.from(context), this, true);
        this.mToolbar = this.mBinding.cvCoinbaseToolbar;
    }

    public Toolbar getToolbar() {
        return this.mBinding.cvCoinbaseToolbar;
    }

    public LayoutAvailableBalanceAppBarBinding getBinding() {
        return this.mBinding;
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            centerTitle();
        }
    }

    public void registerOnClickListener(AvailableBalanceAppBarPresenter presenter) {
        this.mBinding.ivAvailableBalance.setOnClickListener(AvailableBalanceAppBarLayout$$Lambda$1.lambdaFactory$(presenter));
    }

    private void centerTitle() {
        if (this.mToolbar != null) {
            if (this.mToolbar.getChildCount() > 1) {
                if (this.mToolbar.getChildAt(0) instanceof AppCompatTextView) {
                    centerTitleWithImage((AppCompatTextView) this.mToolbar.getChildAt(0));
                }
            } else if (this.mToolbar.getChildAt(0) instanceof AppCompatTextView) {
                centerTitleWithoutImage((AppCompatTextView) this.mToolbar.getChildAt(0));
            }
        }
    }

    private void centerTitleWithImage(AppCompatTextView textView) {
        centerTitleWithoutImage(textView);
        textView.setPadding(-textView.getLeft(), 0, 0, 0);
    }

    private void centerTitleWithoutImage(AppCompatTextView textView) {
        textView.getLayoutParams().width = -1;
        textView.setGravity(17);
    }

    public void setAvailableBalance(String text) {
        this.mBinding.tvAvailableBalance.setText(text);
    }

    public void showAvailableBalanceClickable() {
        this.mBinding.ivAvailableBalance.setVisibility(0);
        this.mBinding.ivAvailableBalanceInvisible.setVisibility(4);
    }

    public void hideAvailableBalanceClickable() {
        this.mBinding.ivAvailableBalance.setVisibility(8);
        this.mBinding.ivAvailableBalanceInvisible.setVisibility(8);
    }
}
