package com.coinbase.android.notifications.priceAlerts;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.GlideApp;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.FragmentPriceAlertsBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.LeftOffsetItemDecorator;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.models.currency.Data;
import javax.inject.Inject;

@ActivityScope
public class PriceAlertsController extends ActionBarController implements PriceAlertsScreen {
    private FragmentPriceAlertsBinding mBinding;
    @Inject
    PriceAlertsPresenter mPresenter;
    @Inject
    SnackBarWrapper mSnackBarWrapper;
    PriceAlertsAdapter priceAlertsAdapter;

    public PriceAlertsController(Bundle bundle) {
        super(bundle);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (FragmentPriceAlertsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_price_alerts, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().priceAlertsControllerSubcomponent(new PriceAlertsPresenterModule(this)).inject(this);
        this.priceAlertsAdapter = new PriceAlertsAdapter(getApplicationContext(), this.mPresenter);
        this.mBinding.rvNotifications.setAdapter(this.priceAlertsAdapter);
        this.mBinding.rvNotifications.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 1, false));
        this.mBinding.rvNotifications.setEmptyView(this.mBinding.rlEmptyState);
        this.mBinding.rvNotifications.addItemDecoration(new LeftOffsetItemDecorator(getActivity(), (int) getApplicationContext().getResources().getDimension(R.dimen.margin_default)));
        new ItemTouchHelper(this.priceAlertsAdapter.getItemTouchCallback()).attachToRecyclerView(this.mBinding.rvNotifications);
        this.mPresenter.onCreateView();
        this.mBinding.btCreatePriceAlert.setOnClickListener(PriceAlertsController$$Lambda$1.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    public void onShowOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_price_alerts, menu);
    }

    public boolean onShownOptionsItemSelected(MenuItem item) {
        if (this.mPresenter == null) {
            return super.onShownOptionsItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.menu_add:
                this.mPresenter.onCreatePriceAlertClicked();
                return true;
            default:
                return super.onShownOptionsItemSelected(item);
        }
    }

    public void onHide() {
        super.onHide();
        this.mBinding.layoutTabCurrency.onHide();
        this.mPresenter.onDestroy();
    }

    public void onShow() {
        super.onShow();
        this.mBinding.layoutTabCurrency.initTabLayout(new PriceAlertsCurrencyTabFilter());
        this.mBinding.layoutTabCurrency.onShow();
        this.mPresenter.onResume();
    }

    public void showCurrentPriceNotAvailable() {
        this.mBinding.tvCurrentPrice.setText(getApplicationContext().getString(R.string.notification_current_price_not_available));
    }

    public void showCurrentPrice(String amount) {
        this.mBinding.tvCurrentPrice.setText(amount);
    }

    public void notifyDataSetChanged() {
        this.priceAlertsAdapter.notifyDataSetChanged();
    }

    public void updateCurrencyView(Data selectedCurrency) {
        if (selectedCurrency != null) {
            this.mBinding.tvEmptyStateDescription.setText(getApplicationContext().getString(R.string.notification_model_description));
            this.mBinding.tvCurrencyCode.setText(selectedCurrency.getName());
            setCurrencyImage(getActivity(), selectedCurrency);
            return;
        }
        this.mBinding.tvEmptyStateDescription.setText("");
        this.mBinding.tvCurrencyCode.setText("");
        this.mBinding.ivCurrencyIcon.setBackground(null);
    }

    public void setCurrencyImage(Context context, final Data currency) {
        if (context != null && currency != null) {
            showDefaultCurrencyImage(currency);
            if (currency.getImage() != null) {
                GlideApp.with(context).load(currency.getImage().getUrl()).placeholder(AccountUtils.getDefaultCurrencyDrawable(context, currency.getCode())).listener(new RequestListener<Drawable>() {
                    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        PriceAlertsController.this.showDefaultCurrencyImage(currency);
                        return true;
                    }

                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                }).into(this.mBinding.ivCurrencyIcon);
            }
        }
    }

    private void showDefaultCurrencyImage(Data currency) {
        int color = Utils.getColorInt(currency.getColor());
        if (color != -1) {
            Utils.updateBackgroundColorWithAlpha(this.mBinding.ivCurrencyIcon, color);
        } else {
            this.mBinding.ivCurrencyIcon.setBackground(null);
        }
    }

    public SpannableStringBuilder getTitle() {
        if (getApplicationContext() == null) {
            return null;
        }
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.price_alerts));
    }
}
