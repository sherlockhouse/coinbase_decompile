package com.coinbase.android.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.databinding.LayoutCenteredAppBarBinding;

@ControllerScope
public class CenteredAppBarLayout extends LinearLayout {
    private LayoutCenteredAppBarBinding mBinding;
    private Toolbar mToolbar;

    public CenteredAppBarLayout(Context context) {
        this(context, null);
    }

    public CenteredAppBarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CenteredAppBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mBinding = LayoutCenteredAppBarBinding.inflate(LayoutInflater.from(context), this, true);
        this.mToolbar = this.mBinding.cvCoinbaseToolbar;
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().inject(this);
    }

    public Toolbar getToolbar() {
        return this.mBinding.cvCoinbaseToolbar;
    }

    public LayoutCenteredAppBarBinding getBinding() {
        return this.mBinding;
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            centerTitle();
        }
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
}
