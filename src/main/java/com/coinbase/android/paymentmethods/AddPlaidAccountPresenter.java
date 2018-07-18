package com.coinbase.android.paymentmethods;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ModelGsonAdapterFactory;
import com.coinbase.api.internal.models.institutions.Data;
import com.coinbase.api.internal.models.institutions.Institution;
import com.google.gson.GsonBuilder;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;

@ControllerScope
class AddPlaidAccountPresenter {
    static final int INTENT_ADD = 10001;
    private boolean mAtoZ = true;
    private final AdapterDelegatesManager<List<Data>> mBanksAdapterDelegate = new AdapterDelegatesManager();
    final List<Data> mInstitutions = new LinkedList();
    final List<Data> mInstitutionsFiltered = new LinkedList();
    private final LoginManager mLoginManager;
    private final Scheduler mScheduler;
    private final AddPlaidAccountScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final SuccessRouter mSuccessRouter;

    @Inject
    AddPlaidAccountPresenter(AddPlaidAccountScreen screen, LoginManager loginManager, @MainScheduler Scheduler mainScheduler, SnackBarWrapper snackBarWrapper, Application context, LayoutInflater layoutInflater, SuccessRouter successRouter) {
        this.mScreen = screen;
        this.mLoginManager = loginManager;
        this.mScheduler = mainScheduler;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mBanksAdapterDelegate.addDelegate(new BanksAdapterDelegate(this, context, layoutInflater));
        this.mSuccessRouter = successRouter;
    }

    void onShow() {
        getAchInstitutions();
    }

    void onSearchTextChange(String newText) {
        filterInstitutions(newText);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_sort) {
            return onSwitchAlphabeticalOrderClicked();
        }
        return true;
    }

    boolean onSwitchAlphabeticalOrderClicked() {
        this.mScreen.clearSearchText();
        this.mInstitutionsFiltered.clear();
        this.mInstitutionsFiltered.addAll(this.mInstitutions);
        this.mAtoZ = !this.mAtoZ;
        Collections.sort(this.mInstitutionsFiltered, AddPlaidAccountPresenter$$Lambda$1.lambdaFactory$(this));
        this.mScreen.notifyBanksDataSetChanged();
        return true;
    }

    static /* synthetic */ int lambda$onSwitchAlphabeticalOrderClicked$0(AddPlaidAccountPresenter this_, Data lhs, Data rhs) {
        if (lhs == null || rhs == null || lhs.getName() == null || rhs.getName() == null) {
            return 0;
        }
        int result = lhs.getName().compareToIgnoreCase(rhs.getName());
        return !this_.mAtoZ ? -result : result;
    }

    void onBankClicked(Data institution) {
        Intent intent = new Intent(this.mScreen.getActivity(), PlaidAccountLoginActivity.class);
        intent.putExtra(PlaidAccountLoginActivity.INSTITUTION, new GsonBuilder().registerTypeAdapterFactory(ModelGsonAdapterFactory.create()).create().toJson((Object) institution));
        intent.putExtra(Constants.PARENT_SUCCESS_ROUTER, this.mSuccessRouter.shouldRouteSuccess());
        this.mScreen.startActivityForResult(intent, 10001);
        String property = MixpanelTracking.PROPERTY_BANK_NAME + institution.getName();
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_BANK_ACCOUNT_SELECTED, property);
    }

    int getBanksCount() {
        return this.mInstitutionsFiltered.size();
    }

    int getBanksViewType(int position) {
        return this.mBanksAdapterDelegate.getItemViewType(this.mInstitutionsFiltered, position);
    }

    ViewHolder onCreateBanksViewHolder(ViewGroup parent, int viewType) {
        return this.mBanksAdapterDelegate.onCreateViewHolder(parent, viewType);
    }

    void onBindBanksViewHolder(ViewHolder holder, int position) {
        this.mBanksAdapterDelegate.onBindViewHolder(this.mInstitutionsFiltered, position, holder);
    }

    boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Constants.ACTIVITY_RESULT_PARENT_SUCCESS_ROUTER) {
            if (!this.mSuccessRouter.shouldRouteSuccess()) {
                return true;
            }
            new Handler(Looper.getMainLooper()).postDelayed(AddPlaidAccountPresenter$$Lambda$2.lambdaFactory$(this), 100);
            return true;
        } else if (requestCode != 10001) {
            return false;
        } else {
            this.mScreen.popCurrentController();
            return true;
        }
    }

    static /* synthetic */ void lambda$onActivityResult$1(AddPlaidAccountPresenter this_) {
        if (this_.mScreen.isShown() && this_.mSuccessRouter.shouldRouteSuccess()) {
            this_.mSuccessRouter.routeSuccess();
        }
    }

    private void filterInstitutions(String text) {
        this.mInstitutionsFiltered.clear();
        for (Data institution : this.mInstitutions) {
            if (institution.getName().toLowerCase().contains(text.toLowerCase())) {
                this.mInstitutionsFiltered.add(institution);
            }
        }
        Collections.sort(this.mInstitutionsFiltered, AddPlaidAccountPresenter$$Lambda$3.lambdaFactory$());
        this.mScreen.notifyBanksDataSetChanged();
    }

    private void getAchInstitutions() {
        Observable observeOn = this.mLoginManager.getClient().getAchInstitutionsRx().first().observeOn(this.mScheduler);
        Action1 lambdaFactory$ = AddPlaidAccountPresenter$$Lambda$4.lambdaFactory$(this);
        SnackBarWrapper snackBarWrapper = this.mSnackBarWrapper;
        snackBarWrapper.getClass();
        observeOn.subscribe(lambdaFactory$, AddPlaidAccountPresenter$$Lambda$5.lambdaFactory$(snackBarWrapper));
    }

    static /* synthetic */ void lambda$getAchInstitutions$4(AddPlaidAccountPresenter this_, Pair pair) {
        Response<Institution> response = pair.first;
        Retrofit retrofit = pair.second;
        if (!response.isSuccessful()) {
            this_.mSnackBarWrapper.showError(response, retrofit);
        } else if (this_.mScreen.isShown()) {
            this_.mInstitutions.clear();
            this_.mInstitutionsFiltered.clear();
            this_.mInstitutions.addAll(((Institution) response.body()).getData());
            Collections.sort(this_.mInstitutions, AddPlaidAccountPresenter$$Lambda$6.lambdaFactory$());
            this_.mInstitutionsFiltered.addAll(this_.mInstitutions);
            this_.mScreen.notifyBanksDataSetChanged();
        }
    }
}
