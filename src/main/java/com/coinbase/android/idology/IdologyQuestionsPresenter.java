package com.coinbase.android.idology;

import android.app.Application;
import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyTrackingContextProvider.IdologyTrackingContext;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.idology.Answer;
import com.coinbase.api.internal.models.idology.Idology;
import com.coinbase.api.internal.models.idology.Question;
import com.coinbase.api.internal.models.idology.UserAnswers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class IdologyQuestionsPresenter {
    private static final int IDOLOGY_QUESTION_TIMER_INTERVAL = 1000;
    private static final int IDOLOGY_QUESTION_TIMER_MINUTES = 4;
    private static final String QUESTION_TIMER_FORMAT = "%d:%02d";
    private Map<String, String> mAnswerMap = new HashMap();
    private final Context mContext;
    private final IdologyAnswerListValidConnector mIdologyAnswerListValidConnector;
    private final IdologyRetryConnector mIdologyRetryConnector;
    private final String mIdologyTrackingContext;
    private final IdologyVerificationConnector mIdologyVerificationConnector;
    private final Logger mLogger = LoggerFactory.getLogger(IdologyQuestionsPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private List<Question> mQuestionList = new ArrayList();
    private final IdologyQuestionsScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private CountDownTimer mTimer;

    @Inject
    public IdologyQuestionsPresenter(Application application, LoginManager loginManager, IdologyQuestionsScreen screen, IdologyAnswerListValidConnector idologyAnswerListValidConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyRetryConnector idologyRetryConnector, SnackBarWrapper snackBarWrapper, MixpanelTracking mixpanelTracking, @IdologyTrackingContext String idologyTrackingContext, @MainScheduler Scheduler mainScheduler) {
        this.mContext = application;
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mIdologyAnswerListValidConnector = idologyAnswerListValidConnector;
        this.mIdologyVerificationConnector = idologyVerificationConnector;
        this.mIdologyRetryConnector = idologyRetryConnector;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMixpanelTracking = mixpanelTracking;
        this.mIdologyTrackingContext = idologyTrackingContext;
        this.mMainScheduler = mainScheduler;
    }

    void onShow() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_QUESTIONS_VIEWED, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
    }

    void onHide() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
        }
        this.mSubscription.clear();
    }

    void setQuestionList(List<Question> questions) {
        this.mQuestionList.clear();
        this.mQuestionList.addAll(questions);
        refreshQuestionList();
    }

    void refreshQuestionList() {
        for (Question question : this.mQuestionList) {
            this.mAnswerMap.put(question.getType(), null);
        }
        this.mIdologyAnswerListValidConnector.get().onNext(Boolean.valueOf(false));
        this.mScreen.updateQuestionList();
        startTimer();
    }

    List<Question> getQuestionList() {
        return this.mQuestionList;
    }

    void updateAnswerMap(String type, String answer) {
        this.mAnswerMap.put(type, answer);
        this.mIdologyAnswerListValidConnector.get().onNext(Boolean.valueOf(isFormValid()));
    }

    void submitAnswers(String idologyId) {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_QUESTIONS_TAPPED_SUBMIT, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
        if (TextUtils.isEmpty(idologyId)) {
            this.mSnackBarWrapper.showGenericError();
            this.mLogger.error("Couldn't submit answers -- idologyId is null");
            return;
        }
        UserAnswers userAnswers = getUserAnswers();
        if (userAnswers == null) {
            this.mSnackBarWrapper.showGenericError();
            this.mLogger.error("Couldn't submit answers -- UserAnswers is null");
            return;
        }
        Observable<Pair<Response<Idology>, Retrofit>> submitAnswersObservable = this.mLoginManager.getClient().submitAnswersRx(idologyId, userAnswers);
        this.mSubscription.clear();
        this.mSubscription.add(submitAnswersObservable.first().observeOn(this.mMainScheduler).subscribe(IdologyQuestionsPresenter$$Lambda$1.lambdaFactory$(this), IdologyQuestionsPresenter$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$submitAnswers$0(IdologyQuestionsPresenter this_, Pair pair) {
        Response<Idology> response = pair.first;
        if (response.isSuccessful()) {
            this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_QUESTIONS_VIEWED_SUCCESS, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this_.mIdologyTrackingContext);
            this_.mIdologyVerificationConnector.get().onNext(((Idology) response.body()).getData());
            return;
        }
        this_.mSnackBarWrapper.show(Utils.getErrorMessage(response, (Retrofit) pair.second));
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_QUESTIONS_VIEWED_ERROR, "error", errorMessage, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this_.mIdologyTrackingContext);
        this_.mIdologyRetryConnector.get().onNext(Boolean.valueOf(this_.isFormValid()));
    }

    static /* synthetic */ void lambda$submitAnswers$1(IdologyQuestionsPresenter this_, Throwable t) {
        this_.mSnackBarWrapper.show(Utils.getMessage(this_.mContext, t));
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_QUESTIONS_VIEWED_ERROR, "error", errorMessage, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this_.mIdologyTrackingContext);
        this_.mIdologyRetryConnector.get().onNext(Boolean.valueOf(this_.isFormValid()));
    }

    int getTimerInMin() {
        return 4;
    }

    void onTimedOut() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_QUESTIONS_VIEWED_TIMEOUT_ERROR, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
        this.mScreen.showTimeOutDialog();
    }

    private void startTimer() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
            this.mScreen.updateTimerText("");
        }
        this.mTimer = new CountDownTimer((long) getTimerInMs(), 1000) {
            public void onTick(long millisUntilFinished) {
                IdologyQuestionsPresenter.this.mScreen.updateTimerText(String.format(IdologyQuestionsPresenter.QUESTION_TIMER_FORMAT, new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))}));
            }

            public void onFinish() {
                IdologyQuestionsPresenter.this.onTimedOut();
                IdologyQuestionsPresenter.this.mTimer.cancel();
            }
        };
        this.mTimer.start();
    }

    private boolean isFormValid() {
        for (Entry<String, String> entry : this.mAnswerMap.entrySet()) {
            if (entry.getValue() == null) {
                return false;
            }
        }
        return true;
    }

    private int getTimerInMs() {
        return 240000;
    }

    private UserAnswers getUserAnswers() {
        List<Answer> answerList = new ArrayList();
        for (Entry<String, String> entry : this.mAnswerMap.entrySet()) {
            String type = (String) entry.getKey();
            String answer = (String) entry.getValue();
            if (answer == null) {
                return null;
            }
            answerList.add(Answer.builder().setType(type).setAnswer(answer).build());
        }
        return UserAnswers.builder().setAnswers(answerList).build();
    }
}
