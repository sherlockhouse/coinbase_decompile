package com.coinbase.android.signin.state;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerStateIdologyQuestionsBinding;
import com.coinbase.android.settings.idology.IdologySettingsQuestionsPresenter;
import com.coinbase.android.settings.idology.IdologySettingsQuestionsScreen;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.internal.models.idology.Question;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class StateIdologyQuestionsController extends ActionBarController implements IdologySettingsQuestionsScreen {
    private ControllerStateIdologyQuestionsBinding mBinding;
    private MenuItem mContinueMenuItem;
    @Inject
    IdologySettingsQuestionsPresenter mPresenter;

    public StateIdologyQuestionsController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerStateIdologyQuestionsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_state_idology_questions, container, false);
        onAttachToolbar(null);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addStateIdologyQuestionsControllerSubcomponent(new StateIdologySettingsQuestionsPresenterModule(this)).inject(this);
        this.mPresenter.onViewCreated(getArgs());
        this.mBinding.btnIdologyContinue.setOnClickListener(StateIdologyQuestionsController$$Lambda$1.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getResources().getString(R.string.verify_identity));
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
        this.mBinding.idologyQuestionsLayout.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
        this.mBinding.idologyQuestionsLayout.onHide();
    }

    public void onShowOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_idology_settings, menu);
        this.mContinueMenuItem = menu.findItem(R.id.menu_continue);
    }

    public void setContinueMenuEnabled(boolean isEnabled) {
        this.mBinding.btnIdologyContinue.setEnabled(isEnabled);
    }

    public void setQuestions(List<Question> questions) {
        this.mBinding.idologyQuestionsLayout.setQuestionList(questions);
    }

    public void submitAnswers(String idologyId) {
        this.mBinding.idologyQuestionsLayout.submitAnswers(idologyId);
    }

    public void showProgress() {
        this.mBinding.rlQuestionsContainer.setVisibility(8);
        this.mBinding.progress.setVisibility(0);
    }

    public void hideProgress() {
        this.mBinding.rlQuestionsContainer.setVisibility(0);
        this.mBinding.progress.setVisibility(8);
    }
}
