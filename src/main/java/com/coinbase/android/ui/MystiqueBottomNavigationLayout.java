package com.coinbase.android.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.databinding.MystiqueBottomNavigationLayoutBinding;
import javax.inject.Inject;

@ControllerScope
public class MystiqueBottomNavigationLayout extends LinearLayout implements MystiqueBottomNavigationScreen {
    MystiqueBottomNavigationLayoutBinding mBinding;
    BottomNavigationItemAdapter mBottomNavigationItemAdapter;
    @Inject
    MystiqueBottomNavigationPresenter mPresenter;

    private class BottomNavigationItemAdapter extends Adapter {
        private BottomNavigationItemAdapter() {
        }

        public int getItemCount() {
            return MystiqueBottomNavigationLayout.this.mPresenter.getBottomNavigationItemCount();
        }

        public int getItemViewType(int position) {
            return MystiqueBottomNavigationLayout.this.mPresenter.getBottomNavigationItemViewType(position);
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return MystiqueBottomNavigationLayout.this.mPresenter.onCreateBottomNavigationItemViewHolder(parent, viewType);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            MystiqueBottomNavigationLayout.this.mPresenter.onBindBottomNavigationItemViewHolder(holder, position);
        }
    }

    public MystiqueBottomNavigationLayout(Context context) {
        this(context, null);
    }

    public MystiqueBottomNavigationLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MystiqueBottomNavigationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mBinding = MystiqueBottomNavigationLayoutBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().mystiqueBottomNavigationLayoutSubcomponent(new MystiqueBottomNavigationPresenterModule(this)).inject(this);
    }

    public void onShow(AppCompatActivity activity) {
        this.mBottomNavigationItemAdapter = new BottomNavigationItemAdapter();
        this.mBinding.rvItems.setHasFixedSize(true);
        this.mBinding.rvItems.setAdapter(this.mBottomNavigationItemAdapter);
        this.mBinding.rvItems.setLayoutManager(new LinearLayoutManager(activity, 0, false));
        this.mPresenter.onShow();
    }

    public void onHide() {
        this.mPresenter.onHide();
    }

    public void notifyAdapterDataSetChanged() {
        this.mBottomNavigationItemAdapter.notifyDataSetChanged();
    }
}
