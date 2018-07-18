package com.coinbase.android.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.MystiqueListSelectorLayoutBinding;
import javax.inject.Inject;

@ActivityScope
public class MystiqueListSelectorLayout extends LinearLayout {
    private ArrayAdapter mAdapter;
    private MystiqueListSelectorLayoutBinding mBinding;
    @Inject
    MystiqueListButtonConnector mListButtonConnector;
    @Inject
    MystiqueListSelectorConnector mListSelectorConnector;

    public MystiqueListSelectorLayout(Context context) {
        this(context, null);
    }

    public MystiqueListSelectorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MystiqueListSelectorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mBinding = MystiqueListSelectorLayoutBinding.inflate(LayoutInflater.from(context), this, true);
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
        this.mBinding.btnClose.setOnClickListener(MystiqueListSelectorLayout$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btnAction.setOnClickListener(MystiqueListSelectorLayout$$Lambda$2.lambdaFactory$(this));
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MystiqueListSelectorLayout, 0, 0);
            try {
                this.mBinding.btnAction.setText(a.getString(0));
            } finally {
                a.recycle();
            }
        }
    }

    public void initializeAdapter(ArrayAdapter adapter) {
        this.mAdapter = adapter;
        this.mBinding.lvItems.setAdapter(this.mAdapter);
        this.mBinding.lvItems.setOnItemClickListener(MystiqueListSelectorLayout$$Lambda$3.lambdaFactory$(this));
    }

    public void notifyAdapterDataSetChanged() {
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void showProgressBar() {
        this.mBinding.progressBar.setVisibility(0);
    }

    public void hideProgressBar() {
        this.mBinding.progressBar.setVisibility(8);
    }
}
