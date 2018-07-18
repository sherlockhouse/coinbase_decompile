package com.coinbase.android.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.coinbase.android.R;
import com.coinbase.android.databinding.BottomItemModalBinding;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import java.util.List;

public class MystiqueBottomNavigationModalAdapter extends AdapterDelegate<List<NavigationItem>> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int mMaxItems;
    private MystiqueBottomNavigationPresenter mPresenter;
    private WindowManager mWindowManager = ((WindowManager) this.mContext.getSystemService("window"));

    static class BottomNavigationModalItemViewHolder extends ViewHolder {
        private final BottomItemModalBinding mBinding;
        Context mContext;
        private MystiqueBottomNavigationPresenter mPresenter;

        public BottomNavigationModalItemViewHolder(Context context, View view, MystiqueBottomNavigationPresenter presenter) {
            super(view);
            this.mContext = context;
            this.mBinding = (BottomItemModalBinding) DataBindingUtil.bind(view);
            this.mPresenter = presenter;
        }

        void bind(ModalBottomNavigationItem item) {
            int icon;
            if (item.hasNotification()) {
                icon = item.getIconNotification();
            } else {
                icon = item.getIcon();
            }
            this.mBinding.llItemContainer.setOnClickListener(MystiqueBottomNavigationModalAdapter$BottomNavigationModalItemViewHolder$$Lambda$1.lambdaFactory$(this, item));
            this.mBinding.tvText.setText(this.mContext.getString(item.getTitle()));
            this.mBinding.ivIcon.setImageDrawable(ContextCompat.getDrawable(this.mContext, icon));
        }
    }

    public MystiqueBottomNavigationModalAdapter(Context context, LayoutInflater layoutInflater, MystiqueBottomNavigationPresenter presenter, int maxItems) {
        this.mContext = context;
        this.mLayoutInflater = layoutInflater;
        this.mPresenter = presenter;
        this.mMaxItems = maxItems;
    }

    protected boolean isForViewType(List<NavigationItem> items, int position) {
        return items.get(position) instanceof ModalBottomNavigationItem;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = this.mLayoutInflater.inflate(R.layout.bottom_item_modal, parent, false);
        setWidth(v, this.mMaxItems);
        return new BottomNavigationModalItemViewHolder(this.mContext, v, this.mPresenter);
    }

    private void setWidth(View v, int divisor) {
        if (this.mWindowManager != null) {
            LayoutParams params = (LayoutParams) v.getLayoutParams();
            params.width = this.mWindowManager.getDefaultDisplay().getWidth() / divisor;
            v.setLayoutParams(params);
        }
    }

    protected void onBindViewHolder(List<NavigationItem> items, int position, ViewHolder holder, List<Object> list) {
        NavigationItem item = (NavigationItem) items.get(position);
        if (item instanceof ModalBottomNavigationItem) {
            ((BottomNavigationModalItemViewHolder) holder).bind((ModalBottomNavigationItem) item);
        }
    }
}
