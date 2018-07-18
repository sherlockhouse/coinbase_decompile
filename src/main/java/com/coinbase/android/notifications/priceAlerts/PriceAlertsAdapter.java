package com.coinbase.android.notifications.priceAlerts;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.helper.ItemTouchHelper.SimpleCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.coinbase.android.GlideApp;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemPriceAlertBinding;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.models.currency.Data;
import java.util.List;

public class PriceAlertsAdapter extends Adapter<ViewHolder> {
    private Context mContext;
    private PriceAlertsPresenter mPresenter;
    private List<LocalPriceAlert> mPriceAlertsList = this.mPresenter.getFilteredPriceAlerts();

    static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private ListItemPriceAlertBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemPriceAlertBinding) DataBindingUtil.bind(itemView);
        }

        ListItemPriceAlertBinding getBinding() {
            return this.mBinding;
        }
    }

    PriceAlertsAdapter(Context context, PriceAlertsPresenter presenter) {
        this.mContext = context;
        this.mPresenter = presenter;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_price_alert, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListItemPriceAlertBinding binding = holder.getBinding();
        final LocalPriceAlert localPriceAlert = (LocalPriceAlert) this.mPriceAlertsList.get(position);
        binding.tvNotification.setText(localPriceAlert.getDisplayText());
        binding.tvNotificationTime.setText(this.mPresenter.getNotificationTimeDisplayText(localPriceAlert));
        boolean isEnabled = localPriceAlert.getEnabled();
        enableNotification(binding, isEnabled);
        binding.switchNotification.setChecked(isEnabled);
        binding.switchNotification.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                boolean isNotificationEnabled = binding.switchNotification.isChecked();
                PriceAlertsAdapter.this.enableNotification(binding, isNotificationEnabled);
                PriceAlertsAdapter.this.mPresenter.setNotificationEnabled(localPriceAlert, isNotificationEnabled);
            }
        });
        GlideApp.with(this.mContext).load(Integer.valueOf(this.mPresenter.getNotificationResourceId(localPriceAlert))).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(ContextCompat.getDrawable(this.mContext, R.drawable.default_circle)).listener(new RequestListener<Drawable>() {
            public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Data currency = localPriceAlert.getCurrency();
                if (currency == null || currency.getColor() == null) {
                    binding.ivNotificationIcon.setBackground(null);
                } else {
                    int color = Utils.getColorInt(currency.getColor());
                    if (color != -1) {
                        Utils.updateBackgroundColorWithAlpha(binding.ivNotificationIcon, color);
                    } else {
                        binding.ivNotificationIcon.setBackground(null);
                    }
                }
                return false;
            }
        }).into(binding.ivNotificationIcon);
    }

    private void enableNotification(ListItemPriceAlertBinding binding, boolean shouldEnable) {
        binding.rlPriceAlert.setAlpha(shouldEnable ? 1.0f : 0.5f);
    }

    public int getItemCount() {
        return this.mPriceAlertsList.size();
    }

    SimpleCallback getItemTouchCallback() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        final Drawable deleteIconDrawable = AppCompatResources.getDrawable(this.mContext, R.drawable.ic_delete);
        final int intrinsictWidth = deleteIconDrawable.getIntrinsicWidth();
        final int intrinsictHeight = deleteIconDrawable.getIntrinsicHeight();
        return new SimpleCallback(0, 4) {
            public boolean onMove(RecyclerView recyclerView, android.support.v7.widget.RecyclerView.ViewHolder viewHolder, android.support.v7.widget.RecyclerView.ViewHolder target) {
                return false;
            }

            public void onSwiped(android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                PriceAlertsAdapter.this.mPresenter.deletePriceAlert((LocalPriceAlert) PriceAlertsAdapter.this.mPriceAlertsList.get(position));
                PriceAlertsAdapter.this.mPriceAlertsList.remove(position);
                PriceAlertsAdapter.this.notifyDataSetChanged();
            }

            public void onChildDraw(Canvas c, RecyclerView recyclerView, android.support.v7.widget.RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if (actionState == 1) {
                    View itemView = viewHolder.itemView;
                    int itemViewHeight = itemView.getBottom() - itemView.getTop();
                    ColorDrawable backgroundColor = new ColorDrawable(ContextCompat.getColor(PriceAlertsAdapter.this.mContext, R.color.price_alert_delete_button_background));
                    backgroundColor.setBounds(itemView.getRight() + ((int) dX), itemView.getTop(), itemView.getRight(), itemView.getBottom());
                    backgroundColor.draw(c);
                    int deleteIconTop = itemView.getTop() + ((itemViewHeight - intrinsictHeight) / 2);
                    int deleteIconMargin = (itemViewHeight - intrinsictHeight) / 2;
                    deleteIconDrawable.setBounds((itemView.getRight() - deleteIconMargin) - intrinsictWidth, deleteIconTop, itemView.getRight() - deleteIconMargin, deleteIconTop + intrinsictHeight);
                    deleteIconDrawable.draw(c);
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
    }
}
