package com.coinbase.android.settings.idology;

import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.internal.models.idology.Question;
import java.util.List;

public interface IdologySettingsQuestionsScreen {
    ActionBarController getController();

    void hideProgress();

    void setContinueMenuEnabled(boolean z);

    void setQuestions(List<Question> list);

    void showProgress();

    void submitAnswers(String str);
}
