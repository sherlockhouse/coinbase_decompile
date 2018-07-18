package com.coinbase.android.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerDialogBinding;
import javax.inject.Inject;

public abstract class DialogController extends ActionBarController {
    @Inject
    BackgroundDimmer mBackgroundDimmer;
    private ControllerDialogBinding mBinding;
    private View mContentView;

    protected abstract View inflateContent(LayoutInflater layoutInflater, ViewGroup viewGroup);

    public DialogController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerDialogBinding) DataBindingUtil.inflate(inflater, R.layout.controller_dialog, container, false);
        onAttachToolbar(null);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().inject(this);
        this.mContentView = inflateContent(inflater, this.mBinding.flDialogContainer);
        this.mContentView.setOnClickListener(DialogController$$Lambda$1.lambdaFactory$());
        this.mContentView.setVisibility(4);
        return this.mBinding.getRoot();
    }

    static /* synthetic */ void lambda$onCreateView$0(View v) {
    }

    public void dismiss() {
        dismiss(false);
    }

    public void dismiss(boolean transitioningToAnotherScreen) {
        this.mBackgroundDimmer.undim(DialogController$$Lambda$2.lambdaFactory$(this), transitioningToAnotherScreen);
    }

    public boolean handleBack() {
        return true;
    }

    protected String getTag() {
        return getClass().getName();
    }

    protected void onAttach(View view) {
        super.onAttach(view);
        this.mBackgroundDimmer.dim(this.mContentView, DialogController$$Lambda$3.lambdaFactory$(this), true, 17);
    }

    protected void onDetach(View view) {
        super.onDetach(view);
    }
}
