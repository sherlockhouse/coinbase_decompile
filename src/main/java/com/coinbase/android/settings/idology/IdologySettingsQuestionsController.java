package com.coinbase.android.settings.idology;

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
import com.coinbase.android.databinding.ControllerIdologySettingsQuestionsBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.internal.models.idology.Question;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class IdologySettingsQuestionsController extends ActionBarController implements IdologySettingsQuestionsScreen {
    private ControllerIdologySettingsQuestionsBinding mBinding;
    private MenuItem mContinueMenuItem;
    @Inject
    IdologySettingsQuestionsPresenter mPresenter;

    public IdologySettingsQuestionsController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerIdologySettingsQuestionsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_idology_settings_questions, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addIdologyQuestionsControllerSubcomponent(new IdologySettingsQuestionsPresenterModule(this)).inject(this);
        this.mPresenter.onViewCreated(getArgs());
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

    public boolean onShownOptionsItemSelected(MenuItem item) {
        if (this.mPresenter == null) {
            return super.onShownOptionsItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.menu_continue:
                this.mPresenter.onContinueClicked(getArgs());
                return true;
            default:
                return super.onShownOptionsItemSelected(item);
        }
    }

    public void setContinueMenuEnabled(boolean isEnabled) {
        if (this.mContinueMenuItem != null) {
            this.mContinueMenuItem.setEnabled(isEnabled);
        }
    }

    public void setQuestions(List<Question> questions) {
        this.mBinding.idologyQuestionsLayout.setQuestionList(questions);
    }

    public void submitAnswers(String idologyId) {
        this.mBinding.idologyQuestionsLayout.submitAnswers(idologyId);
    }

    public void showProgress() {
    }

    public void hideProgress() {
    }
}
