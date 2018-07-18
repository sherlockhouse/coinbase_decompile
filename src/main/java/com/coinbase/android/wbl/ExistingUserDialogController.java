package com.coinbase.android.wbl;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.DialogExistingUserBinding;
import com.coinbase.android.ui.DialogController;
import javax.inject.Inject;

public class ExistingUserDialogController extends DialogController implements ExistingUserDialogScreen {
    DialogExistingUserBinding mBinding;
    @Inject
    ExistingUserDialogPresenter mPresenter;

    public ExistingUserDialogController(Bundle bundle) {
        super(bundle);
    }

    protected View inflateContent(LayoutInflater inflater, ViewGroup container) {
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addExistingUserDialogControllerSubcomponent(new ExistingUserDialogPresenterModule(this, this)).inject(this);
        this.mBinding = DialogExistingUserBinding.inflate(inflater, container, true);
        this.mPresenter.onCreate(getArgs());
        this.mBinding.btnAccountLevels.setOnClickListener(ExistingUserDialogController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btnLearnMore.setOnClickListener(ExistingUserDialogController$$Lambda$2.lambdaFactory$(this));
        this.mBinding.flDismissContainer.setOnClickListener(ExistingUserDialogController$$Lambda$3.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    public void showTier0AccountLevels() {
        this.mBinding.ivImage.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wbl_existing_user_tier0));
        this.mBinding.tvDescription.setText(getApplicationContext().getString(R.string.wbl_existing_user_tier0_account_description));
        this.mBinding.btnAccountLevels.setText(getApplicationContext().getString(R.string.complete_your_profile));
    }

    public void showIncompleteAccountLevels() {
        this.mBinding.tvDescription.setText(getApplicationContext().getString(R.string.wbl_existing_user_incomplete_account_description));
        this.mBinding.btnAccountLevels.setText(getApplicationContext().getString(R.string.complete_your_profile));
    }

    public void showCompleteAccountLevels() {
        this.mBinding.tvDescription.setText(getApplicationContext().getString(R.string.wbl_existing_user_complete_account_description));
        this.mBinding.btnAccountLevels.setText(getApplicationContext().getString(R.string.view_your_new_limits));
    }
}
