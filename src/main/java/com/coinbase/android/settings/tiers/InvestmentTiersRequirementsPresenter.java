package com.coinbase.android.settings.tiers;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Pair;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.tiers.Data;
import com.coinbase.api.internal.models.tiers.Level;
import com.coinbase.api.internal.models.tiers.Requirement;
import com.coinbase.api.internal.models.tiers.Requirement.Status;
import com.coinbase.api.internal.models.tiers.Tiers;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class InvestmentTiersRequirementsPresenter {
    private static final String AUTO_ROUTED_REQUIREMENT = "auto_routed_requirement";
    static final String FIRST_SHOW = "first_show";
    public static final String NEXT_TIER = "next_tier";
    static final String REQUIREMENTS = "requirements";
    private final AdapterDelegatesManager<List<Requirement>> mInvestmentTiersRequirementsAdapterDelegate = new AdapterDelegatesManager();
    private final Logger mLogger = LoggerFactory.getLogger(InvestmentTiersRequirementsPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private List<Requirement> mRequirements = new LinkedList();
    private final InvestmentTiersRequirementsRouter mRouter;
    private final InvestmentTiersRequirementsScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private int mTargetTier = -1;

    @Inject
    public InvestmentTiersRequirementsPresenter(InvestmentTiersRequirementsScreen screen, InvestmentTiersRequirementsRouter router, LoginManager loginManager, SnackBarWrapper snackBarWrapper, Application application, @MainScheduler Scheduler mainScheduler) {
        this.mScreen = screen;
        this.mRouter = router;
        this.mLoginManager = loginManager;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMainScheduler = mainScheduler;
        this.mInvestmentTiersRequirementsAdapterDelegate.addDelegate(new InvestmentTiersRequirementsAdapterDelegate(application, this));
    }

    void onShow(Bundle args) {
        this.mTargetTier = args.getInt(NEXT_TIER, -1);
        if (this.mTargetTier < 0) {
            this.mSnackBarWrapper.showGenericError();
            return;
        }
        loadRequirementsFromArgs(args);
        boolean firstShow = args.getBoolean(FIRST_SHOW, true);
        if (!(this.mRequirements == null || this.mRequirements.isEmpty())) {
            this.mScreen.notifyInvestmentTiersRequirementsAdapter();
            autoRouteSingleIncompleteRequirement(args);
        }
        args.putBoolean(FIRST_SHOW, false);
        if (this.mRequirements == null || this.mRequirements.isEmpty() || !firstShow) {
            this.mSubscription.add(this.mLoginManager.getClient().getTiersRx().first().observeOn(this.mMainScheduler).subscribe(InvestmentTiersRequirementsPresenter$$Lambda$1.lambdaFactory$(this, args), InvestmentTiersRequirementsPresenter$$Lambda$2.lambdaFactory$(this)));
        }
    }

    static /* synthetic */ void lambda$onShow$0(InvestmentTiersRequirementsPresenter this_, Bundle args, Pair pair) {
        Response<Tiers> response = pair.first;
        Retrofit retrofit = pair.second;
        if (!this_.mScreen.isShown()) {
            return;
        }
        if (response.isSuccessful()) {
            Data data = ((Tiers) response.body()).getData();
            if (data == null || data.getVerificationLevels() == null) {
                this_.mSnackBarWrapper.showGenericError();
                return;
            }
            List<Level> tiers = data.getVerificationLevels().getLevels();
            if (tiers == null || tiers.isEmpty()) {
                this_.mSnackBarWrapper.showGenericError();
                return;
            } else if (tiers.size() - 1 < this_.mTargetTier) {
                this_.mLogger.error("Fewer tiers than the tier we're targeting, should never happen");
                this_.mSnackBarWrapper.showGenericError();
                return;
            } else {
                Level targetTier = (Level) tiers.get(this_.mTargetTier);
                if (targetTier == null || targetTier.getStatus() == null || targetTier.getStatus().getComplete() == null) {
                    this_.mLogger.error("Returned tier is null, should never happen");
                    this_.mSnackBarWrapper.showGenericError();
                    return;
                } else if (targetTier.getStatus().getComplete().booleanValue()) {
                    this_.mRouter.showTierSuccess();
                    return;
                } else if (targetTier.getRequirements() == null || targetTier.getRequirements().isEmpty()) {
                    this_.mSnackBarWrapper.showGenericError();
                    this_.mLogger.error("Empty requirements returned, should never happen");
                    return;
                } else {
                    this_.mRequirements.clear();
                    this_.mRequirements.addAll(targetTier.getRequirements());
                    this_.saveRequirementsInArgs(args);
                    this_.mScreen.notifyInvestmentTiersRequirementsAdapter();
                    this_.autoRouteSingleIncompleteRequirement(args);
                    return;
                }
            }
        }
        this_.mSnackBarWrapper.showError(response, retrofit);
    }

    static /* synthetic */ void lambda$onShow$1(InvestmentTiersRequirementsPresenter this_, Throwable t) {
        if (this_.mScreen.isShown()) {
            this_.mSnackBarWrapper.showFailure(t);
        }
        this_.mLogger.error("Failure getting investment tiers", t);
    }

    void onRequirementClicked(Requirement requirement) {
        this.mRouter.routeRequirement(requirement);
    }

    void loadRequirementsFromArgs(Bundle args) {
        String requirementsStr = args.getString(REQUIREMENTS);
        if (!TextUtils.isEmpty(requirementsStr)) {
            Collection<Requirement> requirements = (Collection) new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().fromJson(requirementsStr, new TypeToken<Collection<Requirement>>() {
            }.getType());
            if (requirements != null && !requirements.isEmpty()) {
                this.mRequirements.addAll(requirements);
            }
        }
    }

    void saveRequirementsInArgs(Bundle args) {
        args.putString(REQUIREMENTS, new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().toJson(this.mRequirements));
    }

    void onHide() {
        this.mSubscription.clear();
    }

    private void autoRouteSingleIncompleteRequirement(Bundle args) {
        if (this.mRequirements.size() == 1) {
            if (!args.getBoolean(AUTO_ROUTED_REQUIREMENT, false)) {
                routeFirstIncompleteRequirement();
            }
            args.putBoolean(AUTO_ROUTED_REQUIREMENT, true);
        }
    }

    private void routeFirstIncompleteRequirement() {
        if (this.mRequirements != null && !this.mRequirements.isEmpty()) {
            Requirement firstPendingRequirement = null;
            for (Requirement requirement : this.mRequirements) {
                if (!TextUtils.isEmpty(requirement.getStatus())) {
                    Status status = Status.fromString(requirement.getStatus());
                    if (Status.INCOMPLETE == status) {
                        this.mRouter.routeRequirement(requirement);
                        return;
                    } else if (Status.PENDING == status && firstPendingRequirement == null) {
                        firstPendingRequirement = requirement;
                    }
                }
            }
            if (firstPendingRequirement != null) {
                this.mRouter.routeRequirement(firstPendingRequirement);
            }
        }
    }

    int getInvestmentTiersRequirementsItemCount() {
        return this.mRequirements.size();
    }

    int getInvestmentTiersRequirementsItemViewType(int position) {
        return this.mInvestmentTiersRequirementsAdapterDelegate.getItemViewType(this.mRequirements, position);
    }

    ViewHolder onCreateInvestmentTiersRequirementsViewHolder(ViewGroup parent, int viewType) {
        return this.mInvestmentTiersRequirementsAdapterDelegate.onCreateViewHolder(parent, viewType);
    }

    void onBindInvestmentTiersRequirementsViewHolder(ViewHolder holder, int position) {
        this.mInvestmentTiersRequirementsAdapterDelegate.onBindViewHolder(this.mRequirements, position, holder);
    }

    void onContinueClicked() {
        routeFirstIncompleteRequirement();
    }
}
