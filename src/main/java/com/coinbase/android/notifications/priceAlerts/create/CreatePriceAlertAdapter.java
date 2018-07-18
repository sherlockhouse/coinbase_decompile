package com.coinbase.android.notifications.priceAlerts.create;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemCreatePriceAlertBinding;
import com.coinbase.android.utils.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreatePriceAlertAdapter extends Adapter<ViewHolder> {
    private Context mContext;
    private double mCurrentPrice;
    private double mIncrement;
    List<String> mItems;
    private int mViewWidth;

    public class ViewHolderItem extends ViewHolder {
        private final ListItemCreatePriceAlertBinding mBinding;
        Context mContext;
        View markerView;

        public ViewHolderItem(Context context, View view) {
            super(view);
            this.mContext = context;
            this.mBinding = (ListItemCreatePriceAlertBinding) DataBindingUtil.bind(view);
        }

        ListItemCreatePriceAlertBinding getBinding() {
            return this.mBinding;
        }
    }

    public class ViewHolderLeft extends ViewHolder {
        Context mContext;

        public ViewHolderLeft(Context context, View view) {
            super(view);
            this.mContext = context;
        }
    }

    private enum ViewType {
        PLACEHOLDER(0),
        ITEM(1);
        
        private static Map<Integer, ViewType> map;
        private int id;

        static {
            map = new HashMap();
            ViewType[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                ViewType viewType = values[i];
                map.put(Integer.valueOf(viewType.id), viewType);
                i++;
            }
        }

        private ViewType(int id) {
            this.id = id;
        }

        public static ViewType valueOf(int id) {
            return (ViewType) map.get(Integer.valueOf(id));
        }
    }

    public CreatePriceAlertAdapter(Context context, List<String> items, int viewWidth, double currentPrice, double increment) {
        this.mContext = context;
        this.mItems = items;
        this.mViewWidth = viewWidth;
        this.mCurrentPrice = currentPrice;
        this.mIncrement = increment;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (ViewType.valueOf(viewType)) {
            case PLACEHOLDER:
                View v = new View(this.mContext);
                v.setLayoutParams(new LayoutParams((this.mViewWidth / 2) - (this.mContext.getResources().getDimensionPixelSize(R.dimen.create_price_alert_item_width) / 2), -1));
                return new ViewHolderLeft(this.mContext, v);
            case ITEM:
                return new ViewHolderItem(this.mContext, LayoutInflater.from(this.mContext).inflate(R.layout.list_item_create_price_alert, parent, false));
            default:
                return null;
        }
    }

    public int getItemViewType(int position) {
        if (position == 0 || position == getItemCount() - 1) {
            return ViewType.PLACEHOLDER.ordinal();
        }
        return ViewType.ITEM.ordinal();
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolderItem) {
            String label = (String) this.mItems.get(position);
            ViewHolderItem viewHolderItem = (ViewHolderItem) viewHolder;
            ListItemCreatePriceAlertBinding binding = viewHolderItem.getBinding();
            binding.tvPriceLabel.setText(label);
            if (position == 1) {
                binding.flTopFirst.setVisibility(4);
                binding.flTopSecond.setVisibility(4);
                binding.flBottomFirst.setVisibility(4);
                binding.flBottomSecond.setVisibility(4);
            } else if (position == getItemCount() - 2) {
                binding.flTopThird.setVisibility(4);
                binding.flTopFourth.setVisibility(4);
                binding.flBottomThird.setVisibility(4);
                binding.flBottomFourth.setVisibility(4);
            } else {
                binding.flTopFirst.setVisibility(0);
                binding.flTopSecond.setVisibility(0);
                binding.flTopThird.setVisibility(0);
                binding.flTopFourth.setVisibility(0);
                binding.flBottomFirst.setVisibility(0);
                binding.flBottomSecond.setVisibility(0);
                binding.flBottomThird.setVisibility(0);
                binding.flBottomFourth.setVisibility(0);
            }
            int itemValue = Integer.valueOf(label).intValue();
            if (Math.abs(this.mCurrentPrice - ((double) itemValue)) < this.mIncrement / 2.0d) {
                if (viewHolderItem.markerView == null) {
                    viewHolderItem.markerView = new View(this.mContext);
                    viewHolderItem.markerView.setVisibility(0);
                } else {
                    binding.rlParent.removeView(viewHolderItem.markerView);
                }
                viewHolderItem.markerView.setBackgroundColor(-1);
                int markerPixelWidth = Utils.getPixels(this.mContext, 3);
                RelativeLayout.LayoutParams markerParams = new RelativeLayout.LayoutParams(markerPixelWidth, this.mContext.getResources().getDimensionPixelSize(R.dimen.create_price_alert_item_height));
                markerParams.leftMargin = (int) ((((double) this.mContext.getResources().getDimensionPixelSize(R.dimen.create_price_alert_item_width)) * (((this.mCurrentPrice - ((double) itemValue)) + (this.mIncrement / 2.0d)) / this.mIncrement)) - ((double) (markerPixelWidth / 2)));
                markerParams.topMargin = 0;
                binding.rlParent.addView(viewHolderItem.markerView, markerParams);
            } else if (viewHolderItem.markerView != null) {
                binding.rlParent.removeView(viewHolderItem.markerView);
            }
        }
    }

    public int getItemCount() {
        return this.mItems.size();
    }
}
