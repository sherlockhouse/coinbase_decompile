package com.coinbase.android.dashboard;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.accounts.AccountMainController;
import com.coinbase.android.databinding.FragmentDashboardBinding;
import com.coinbase.android.quickstart.QuickstartModule;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import com.coinbase.android.ui.ScreenshotAllowed;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.currency.Data;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
@ScreenshotAllowed
public class DashboardMainController extends ActionBarController implements DashboardMainScreen {
    private static final float PERCENTAGE_TO_SHOW_NEWUSER_HEADER_AT_TOOLBAR = 0.98f;
    private static final float PERCENTAGE_TO_SHOW_PORTFOLIO_HEADER_AT_TOOLBAR = 0.8f;
    FragmentDashboardBinding mBinding;
    @Inject
    BottomNavigationConnector mBottomNavigationConnector;
    private float mFinalRightPortfolioTitlePadding = 0.0f;
    @Inject
    MixpanelTracking mMixpanelTracking;
    private OnOffsetChangedListener mNewUserOffsetChangedListener;
    private OnOffsetChangedListener mPortfolioBalanceOffsetChangedListener;
    @Inject
    DashboardMainPresenter mPresenter;

    public DashboardMainController(Bundle bundle) {
        super(bundle);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (FragmentDashboardBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addDashboardMainControllerSubcomponent(new QuickstartModule(getActivity(), this), new DashboardMainPresenterModule(this, this)).inject(this);
        this.mFinalRightPortfolioTitlePadding = (getResources().getDimension(R.dimen.dashboard_toolbar_padding) * PERCENTAGE_TO_SHOW_PORTFOLIO_HEADER_AT_TOOLBAR) / 0.19999999f;
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        setForceHideHomeDisplay(true);
        setForceDisableToolbarThemeUpdate(true);
        setOffsetListeners();
        return this.mBinding.getRoot();
    }

    protected void onShow() {
        super.onShow();
        this.mBinding.layoutPriceChartList.onResume();
        this.mPresenter.onResume();
    }

    protected void onHide() {
        super.onHide();
        this.mBinding.layoutPriceChartList.onDestroy();
        this.mPresenter.onDestroy();
    }

    public SpannableStringBuilder getTitle() {
        return null;
    }

    public void gotoDashboardCurrency(Data currency) {
        Bundle args = new Bundle();
        if (currency != null) {
            args.putString("cryptocurrency_data", new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().toJson((Object) currency));
        }
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(Type.DASHBOARD).setBottomNavigationItem(Type.DASHBOARD).setPushPageController(new DashboardCurrencyController(appendArgs(args))).build());
    }

    public void updatePortfolioBalanceHeader(String balance) {
        this.mBinding.ctLayout.setTitle(balance);
    }

    public void showPortfolioBalanceHeader() {
        initHeaderView();
        this.mBinding.appBar.removeOnOffsetChangedListener(this.mNewUserOffsetChangedListener);
        this.mBinding.appBar.addOnOffsetChangedListener(this.mPortfolioBalanceOffsetChangedListener);
        this.mBinding.ivLogo.setVisibility(8);
        this.mBinding.llNewUserHeader.setVisibility(8);
        this.mBinding.llAlertsNewUser.setVisibility(8);
        this.mBinding.llPortfolioHeader.setVisibility(0);
        this.mBinding.rlPortfolio.setVisibility(0);
        this.mBinding.flAlertsPortfolio.setVisibility(0);
        this.mBinding.llPortfolioHeader.setOnClickListener(DashboardMainController$$Lambda$1.lambdaFactory$(this));
    }

    public void showFirstBuyHeader() {
        initHeaderView();
        this.mBinding.appBar.removeOnOffsetChangedListener(this.mPortfolioBalanceOffsetChangedListener);
        this.mBinding.appBar.addOnOffsetChangedListener(this.mNewUserOffsetChangedListener);
        this.mBinding.btFirstBuy.setOnClickListener(DashboardMainController$$Lambda$2.lambdaFactory$(this));
        this.mBinding.llPortfolioHeader.setVisibility(8);
        this.mBinding.flAlertsPortfolio.setVisibility(8);
        this.mBinding.ivLogo.setVisibility(0);
        this.mBinding.llNewUserHeader.setVisibility(0);
        this.mBinding.llFirstBuyContent.setVisibility(0);
        this.mBinding.llAlertsNewUser.setVisibility(0);
    }

    public void refreshPriceCharts() {
        this.mBinding.layoutPriceChartList.notifyDataSetChanged();
    }

    public void showAlertsNewUser(List<com.coinbase.api.internal.models.alerts.Data> alertsData) {
        this.mBinding.cvAlertsPortfolio.setVisibility(8);
        this.mBinding.cvAlertsNewUser.setVisibility(0);
        this.mBinding.vAlertsNewUserPadding.setVisibility(0);
        this.mBinding.cvAlertsNewUser.show(getActivity(), alertsData, this);
    }

    public void showAlertsPortfolio(List<com.coinbase.api.internal.models.alerts.Data> alertsData) {
        this.mBinding.cvAlertsNewUser.setVisibility(8);
        this.mBinding.vAlertsNewUserPadding.setVisibility(8);
        this.mBinding.cvAlertsPortfolio.setVisibility(0);
        this.mBinding.cvAlertsPortfolio.show(getActivity(), alertsData, this);
    }

    public void hideAlerts() {
        this.mBinding.cvAlertsNewUser.setVisibility(8);
        this.mBinding.vAlertsNewUserPadding.setVisibility(8);
        this.mBinding.cvAlertsPortfolio.setVisibility(8);
    }

    public boolean isShowingNewUser() {
        return this.mBinding.llNewUserHeader.getVisibility() == 0;
    }

    public int getThemeColor() {
        return super.getThemeColor();
    }

    private void initHeaderView() {
        this.mBinding.cvCoinbaseToolbar.setTitle((CharSequence) "");
        this.mBinding.ctLayout.setTitle("");
        this.mBinding.ctLayout.getLayoutParams().height = -2;
    }

    private void gotoPortfolio() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_DASHBOARD_TAPPED_PORTFOLIO, new String[0]);
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(Type.DASHBOARD).setBottomNavigationItem(Type.ACCOUNTS).setPushPageController(new AccountMainController(appendArgs(new Bundle()))).setShouldReplaceController(true).build());
    }

    private void setOffsetListeners() {
        this.mNewUserOffsetChangedListener = DashboardMainController$$Lambda$3.lambdaFactory$(this);
        this.mPortfolioBalanceOffsetChangedListener = DashboardMainController$$Lambda$4.lambdaFactory$(this);
    }

    private void handlePortfolioTitleHeaderVisibility(AppBarLayout appBarLayout, int verticalOffset) {
        if (getApplicationContext() != null) {
            float percentage = ((float) Math.abs(verticalOffset)) / ((float) appBarLayout.getTotalScrollRange());
            if (percentage > PERCENTAGE_TO_SHOW_PORTFOLIO_HEADER_AT_TOOLBAR) {
                this.mBinding.ctLayout.setPadding(0, 0, (int) (this.mFinalRightPortfolioTitlePadding * (percentage - PERCENTAGE_TO_SHOW_PORTFOLIO_HEADER_AT_TOOLBAR)), 0);
                if (this.mBinding.llPortfolioHeader.getVisibility() == 0) {
                    this.mBinding.llPortfolioHeader.setVisibility(4);
                    this.mBinding.flAlertsPortfolio.setVisibility(4);
                    this.mBinding.ctLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.coinbase_blue));
                }
            } else if (this.mBinding.llPortfolioHeader.getVisibility() == 4) {
                this.mBinding.llPortfolioHeader.setVisibility(0);
                this.mBinding.flAlertsPortfolio.setVisibility(0);
                this.mBinding.ctLayout.setPadding(0, 0, 0, 0);
                this.mBinding.ctLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.header_layout_background));
            }
            this.mBinding.llPortfolioHeader.setAlpha(1.0f - percentage);
            this.mBinding.flAlertsPortfolio.setAlpha(1.0f - percentage);
        }
    }

    private void handleNewUserHeaderVisibility(AppBarLayout appBarLayout, int verticalOffset) {
        if (getApplicationContext() != null) {
            float percentage = ((float) Math.abs(verticalOffset)) / ((float) appBarLayout.getTotalScrollRange());
            if (percentage > PERCENTAGE_TO_SHOW_NEWUSER_HEADER_AT_TOOLBAR) {
                this.mBinding.ctLayout.setPadding(0, 0, 0, 0);
                this.mBinding.ctLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.coinbase_blue));
                if (this.mBinding.llNewUserHeader.getVisibility() == 0) {
                    this.mBinding.llNewUserHeader.setVisibility(4);
                    this.mBinding.llAlertsNewUser.setVisibility(4);
                }
            } else if (this.mBinding.llNewUserHeader.getVisibility() == 4) {
                this.mBinding.llNewUserHeader.setVisibility(0);
                this.mBinding.llAlertsNewUser.setVisibility(0);
                this.mBinding.ctLayout.setTitle("");
                this.mBinding.ctLayout.setPadding(0, 0, 0, 0);
                this.mBinding.ctLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.header_layout_background));
            }
            this.mBinding.llNewUserHeader.setAlpha(1.0f - percentage);
            this.mBinding.llAlertsNewUser.setAlpha(1.0f - percentage);
        }
    }
}
