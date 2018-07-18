package com.coinbase.android;

import android.content.Intent;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.coinbase.android.CoinbaseActivity.RequiresAuthentication;
import com.coinbase.android.CoinbaseActivity.RequiresPIN;
import com.coinbase.android.databinding.ActivityMainContentBinding;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.signin.LoginController;
import com.coinbase.android.signin.StateDisclosureFinishedConnector;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.task.SyncAccountsTask;
import com.coinbase.android.task.SyncAccountsTask.SyncAccountsListener;
import com.coinbase.android.transfers.DelayedTxSenderService;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.BaseViewModule;
import com.coinbase.android.ui.BaseViewProvider;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.BottomNavigationItem;
import com.coinbase.android.ui.BottomNavigationViewProvider;
import com.coinbase.android.ui.MainController;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.ModalBottomNavigationItem.Type;
import com.coinbase.android.ui.ModalControllerLifeCycle.ModalDestination;
import com.coinbase.android.ui.ModalViewProvider;
import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import java.util.HashMap;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@RequiresPIN
@ActivityScope
@RequiresAuthentication
public class MainActivity extends CoinbaseActivity implements ActivityProvider, MainActivitySubcomponentProvider, BaseViewProvider, BottomNavigationViewProvider, ModalViewProvider {
    public static final String ACTION_BUY = "com.coinbase.MainActivity.ACTION_BUY";
    public static final String ACTION_SCAN = "com.siriusapplications.coinbase.MainActivity.ACTION_SCAN";
    public static final String ACTION_TRANSACTIONS = "com.siriusapplications.coinbase.MainActivity.ACTION_TRANSACTIONS";
    public static final String ACTION_TRANSFER = "com.siriusapplications.coinbase.MainActivity.ACTION_TRANSFER";
    public static final String ACTION_URI_TRANSFER = "com.coinbase.MainActivity.ACTION_URI_TRANSFER";
    public static final int REQUEST_CODE_PIN = 2;
    @Inject
    BackNavigationConnector mBackNavigationConnector;
    @Inject
    @BackgroundScheduler
    Scheduler mBackgroundScheduler;
    @Inject
    BottomNavigationConnector mBottomNavigationConnector;
    private ActivityMainContentBinding mContentBinding;
    @Inject
    DatabaseManager mDbManager;
    private final Logger mLogger = LoggerFactory.getLogger(MainActivity.class);
    private MainActivitySubcomponent mMainActivitySubcomponent;
    private MainController mMainController;
    @MainScheduler
    @Inject
    Scheduler mMainScheduler;
    boolean mPendingPinReturn = false;
    @Inject
    MainPresenter mPresenter;
    @Inject
    RefreshRequestedConnector mRefreshRequestedConnector;
    private Router mRouter;
    OnSharedPreferenceChangeListener mSharedPreferenceChangeListener;
    @Inject
    SplitTesting mSplitTesting;
    @Inject
    StateDisclosureFinishedConnector mStateDisclosureFinishedConnector;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private SyncAccountsTask mSyncAccountsTask;

    public void onCreate(Bundle savedInstanceState) {
        supportLandscapeOnTablet();
        super.onCreate(savedInstanceState);
        this.mContentBinding = (ActivityMainContentBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_content);
        this.mMainActivitySubcomponent = ((ComponentProvider) getApplicationContext()).applicationComponent().mainActivitySubcomponent(new BaseActivityModule(this), new BaseViewModule(this), new BottomNavActivityModule(this, this));
        ((ComponentProvider) getApplicationContext()).applicationComponent().mainActivityPresenterSubcomponent(new MainPresenterModule(this), new BaseActivityModule(this)).inject(this);
        this.mSubscription.add(this.mRefreshRequestedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(MainActivity$$Lambda$1.lambdaFactory$(this)));
        this.mSubscription.add(this.mStateDisclosureFinishedConnector.get().observeOn(this.mMainScheduler).filter(MainActivity$$Lambda$2.lambdaFactory$()).map(MainActivity$$Lambda$3.lambdaFactory$()).subscribe(MainActivity$$Lambda$4.lambdaFactory$(this)));
        if (!isFinishing()) {
            this.mRouter = Conductor.attachRouter(this, this.mContentBinding.cvControllerContainer, savedInstanceState);
            if (!this.mRouter.hasRootController()) {
                this.mMainController = new MainController();
                this.mRouter.setRoot(RouterTransaction.with(this.mMainController));
            } else if (this.mRouter.getBackstackSize() <= 0) {
                throw new IllegalStateException("Conductor has a controller but back stack empty, should never happen.");
            } else {
                Controller controller = ((RouterTransaction) this.mRouter.getBackstack().get(0)).controller();
                if (controller instanceof MainController) {
                    this.mMainController = (MainController) controller;
                } else {
                    this.mRouter.popToRoot();
                    this.mRouter = Conductor.attachRouter(this, this.mContentBinding.cvControllerContainer, null);
                    this.mMainController = new MainController();
                    this.mRouter.setRoot(RouterTransaction.with(this.mMainController));
                }
            }
            onNewIntent(getIntent());
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_APP_OPEN, new String[0]);
            syncAccounts();
        }
    }

    static /* synthetic */ Boolean lambda$onCreate$1(ClassConsumableEvent event) {
        return Boolean.valueOf(!event.consumeIfNotConsumed(LoginController.class));
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(ACTION_URI_TRANSFER, false).apply();
        if (ACTION_BUY.equals(intent.getAction())) {
            this.mBottomNavigationConnector.getModal().onNext(ModalDestination.builder().setType(Type.NEW_BUY).build());
        }
    }

    public void onResume() {
        super.onResume();
        startService(new Intent(this, DelayedTxSenderService.class));
        String action = getIntent().getAction();
        if (action != null) {
            boolean z = true;
            switch (action.hashCode()) {
                case -1395973047:
                    if (action.equals(Constants.NOTIFICATION_SELL_FRAGMENT)) {
                        z = true;
                        break;
                    }
                    break;
                case -10067235:
                    if (action.equals(Constants.NOTIFICATION_BUY_FRAGMENT)) {
                        z = true;
                        break;
                    }
                    break;
                case 1488233349:
                    if (action.equals(Constants.NOTIFICATION_CHART_FRAGMENT)) {
                        z = false;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setBottomNavigationItem(BottomNavigationItem.Type.DASHBOARD).build());
                    break;
                case true:
                    this.mBottomNavigationConnector.getModal().onNext(ModalDestination.builder().setType(Type.NEW_BUY).build());
                    break;
                case true:
                    this.mBottomNavigationConnector.getModal().onNext(ModalDestination.builder().setType(Type.NEW_BUY).setPushModalControllerFunc(MainActivity$$Lambda$5.lambdaFactory$()).build());
                    break;
            }
        }
        Utils.showJumioFlow(this, false);
        this.mPresenter.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this.mSharedPreferenceChangeListener);
        this.mSubscription.clear();
    }

    public void onPause() {
        super.onPause();
        this.mPendingPinReturn = false;
        ((CoinbaseApplication) getApplication()).removeMainActivity(this);
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.mContentBinding.getRoot().getWindowToken(), 0);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 2 && resultCode == -1) {
            this.mPendingPinReturn = true;
        } else {
            super.onActivityResult(requestCode, resultCode, intent);
        }
    }

    public void onPostResume() {
        super.onPostResume();
    }

    public View getBaseView() {
        return this.mContentBinding.cvControllerContainer;
    }

    public RelativeLayout getBlockingOverlayView() {
        return this.mContentBinding.flOverlay;
    }

    public AppCompatActivity getActivity() {
        return this;
    }

    public ViewGroup getModalView() {
        if (this.mMainController == null) {
            return null;
        }
        return this.mMainController.getModalView();
    }

    public MainActivitySubcomponent mainActivitySubcomponent() {
        return this.mMainActivitySubcomponent;
    }

    public View getBottomNavigationView() {
        if (this.mMainController == null) {
            return null;
        }
        return this.mMainController.getBottomNavigationView();
    }

    private void syncAccounts() {
        if (this.mSyncAccountsTask == null) {
            this.mSyncAccountsTask = new SyncAccountsTask(this, new SyncAccountsListener() {
                public void onPreExecute() {
                }

                public void onException() {
                }

                public void onFinally() {
                    MainActivity.this.mSyncAccountsTask = null;
                }
            });
        }
        this.mSyncAccountsTask.syncAccounts();
    }

    private void onRefreshRequested() {
        syncAccounts();
    }

    private void stateDisclosureFinished(HashMap<String, String> chosenState) {
        if (chosenState == null || ((String) chosenState.get(Constants.ABBREVIATION)).equalsIgnoreCase("WY")) {
            signOut();
        }
    }

    public void onBackPressed() {
        this.mBackNavigationConnector.get().onNext(null);
    }
}
