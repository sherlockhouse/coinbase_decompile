package com.coinbase.android.signin.state;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerStateSelectorBinding;
import com.coinbase.android.signin.StateListDialogFragment;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import javax.inject.Inject;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@ControllerScope
/* compiled from: StateSelectorController.kt */
public final class StateSelectorController extends ActionBarController implements StateSelectorScreen {
    public ControllerStateSelectorBinding mBinding;
    @Inject
    public MixpanelTracking mMixpanelTracking;
    @Inject
    public StateSelectorPresenter mPresenter;

    public StateSelectorController(Bundle args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        super(args);
    }

    public final ControllerStateSelectorBinding getMBinding() {
        ControllerStateSelectorBinding controllerStateSelectorBinding = this.mBinding;
        if (controllerStateSelectorBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        }
        return controllerStateSelectorBinding;
    }

    public final void setMBinding(ControllerStateSelectorBinding <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.mBinding = <set-?>;
    }

    public final StateSelectorPresenter getMPresenter() {
        StateSelectorPresenter stateSelectorPresenter = this.mPresenter;
        if (stateSelectorPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPresenter");
        }
        return stateSelectorPresenter;
    }

    public final void setMPresenter(StateSelectorPresenter <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.mPresenter = <set-?>;
    }

    public final MixpanelTracking getMMixpanelTracking() {
        MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
        if (mixpanelTracking == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMixpanelTracking");
        }
        return mixpanelTracking;
    }

    public final void setMMixpanelTracking(MixpanelTracking <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.mMixpanelTracking = <set-?>;
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        Intrinsics.checkParameterIsNotNull(container, "container");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.controller_state_selector, container, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "DataBindingUtil.inflate<…lector, container, false)");
        this.mBinding = (ControllerStateSelectorBinding) inflate;
        Activity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.coinbase.android.MainActivitySubcomponentProvider");
        }
        ((MainActivitySubcomponentProvider) activity).mainActivitySubcomponent().addStateSelectorControllerSubcomponent(new StateSelectorPresenterModule(this, this)).inject(this);
        MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
        if (mixpanelTracking == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMixpanelTracking");
        }
        mixpanelTracking.trackEvent(MixpanelTracking.EVENT_SELECT_STATE_VIEWED, new String[0]);
        ControllerStateSelectorBinding controllerStateSelectorBinding = this.mBinding;
        if (controllerStateSelectorBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        }
        onAttachToolbar(controllerStateSelectorBinding.cvCoinbaseToolbar);
        setForceDisplayHomeAsUp(true);
        setForceDisableToolbarThemeUpdate(true);
        controllerStateSelectorBinding = this.mBinding;
        if (controllerStateSelectorBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        }
        controllerStateSelectorBinding.rlStateSelector.setOnClickListener(new StateSelectorController$onCreateView$1(this));
        controllerStateSelectorBinding = this.mBinding;
        if (controllerStateSelectorBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        }
        controllerStateSelectorBinding.btnContinue.setOnClickListener(new StateSelectorController$onCreateView$2(this));
        controllerStateSelectorBinding = this.mBinding;
        if (controllerStateSelectorBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        }
        View root = controllerStateSelectorBinding.getRoot();
        Intrinsics.checkExpressionValueIsNotNull(root, "mBinding.root");
        return root;
    }

    protected SpannableStringBuilder getTitle() {
        Resources resources = getResources();
        if (resources == null) {
            Intrinsics.throwNpe();
        }
        return new SpannableStringBuilder(resources.getString(R.string.select_state));
    }

    protected void onShow() {
        super.onShow();
        StateSelectorPresenter stateSelectorPresenter = this.mPresenter;
        if (stateSelectorPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPresenter");
        }
        stateSelectorPresenter.onShow$coinbase_android_productionRelease();
    }

    protected void onHide() {
        super.onHide();
        StateSelectorPresenter stateSelectorPresenter = this.mPresenter;
        if (stateSelectorPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPresenter");
        }
        stateSelectorPresenter.onHide$coinbase_android_productionRelease();
    }

    protected boolean onBackPressed() {
        showCancelDialog();
        return true;
    }

    public void showStateSelector() {
        StateListDialogFragment dialogFragment = new StateListDialogFragment();
        Activity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.app.AppCompatActivity");
        }
        dialogFragment.show(((AppCompatActivity) activity).getSupportFragmentManager(), "change_state");
    }

    public void setStateChosen(String name) {
        ControllerStateSelectorBinding controllerStateSelectorBinding = this.mBinding;
        if (controllerStateSelectorBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        }
        controllerStateSelectorBinding.tvState.setText(name);
    }

    public void showProgressBar() {
        ControllerStateSelectorBinding controllerStateSelectorBinding = this.mBinding;
        if (controllerStateSelectorBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        }
        controllerStateSelectorBinding.pbLoading.setVisibility(0);
    }

    public void hideProgressBar() {
        ControllerStateSelectorBinding controllerStateSelectorBinding = this.mBinding;
        if (controllerStateSelectorBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        }
        controllerStateSelectorBinding.pbLoading.setVisibility(4);
    }

    private final void showCancelDialog() {
        Builder alertBuilder = new Builder(getActivity());
        Resources resources = getResources();
        if (resources == null) {
            Intrinsics.throwNpe();
        }
        alertBuilder.setMessage(resources.getString(R.string.abandon_state_selection));
        alertBuilder.setPositiveButton(R.string.ok, new StateSelectorController$showCancelDialog$1(this));
        alertBuilder.setNegativeButton(R.string.cancel, StateSelectorController$showCancelDialog$2.INSTANCE);
        alertBuilder.show();
    }
}
