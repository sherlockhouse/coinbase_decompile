package com.coinbase.android.idology;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutIdologyQuestionsBinding;
import com.coinbase.api.internal.models.idology.Question;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class IdologyQuestionsLayout extends RelativeLayout implements IdologyQuestionsScreen {
    @Inject
    AppCompatActivity mAppCompatActivity;
    private LayoutIdologyQuestionsBinding mBinding;
    private Context mContext;
    private String mIdologyTrackingContext;
    @Inject
    IdologyQuestionsPresenter mPresenter;
    private IdologyQuestionsListAdapter mQuestionListAdapter;

    public IdologyQuestionsLayout(Context context) {
        this(context, null);
    }

    public IdologyQuestionsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IdologyQuestionsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void setAttributes(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.IdologyQuestionsLayout, 0, 0);
            try {
                this.mIdologyTrackingContext = a.getString(0);
            } finally {
                a.recycle();
            }
        }
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        this.mBinding = LayoutIdologyQuestionsBinding.inflate(LayoutInflater.from(context), this, true);
        setAttributes(context, attrs);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().addIdologyQuestionsLayoutSubcomponent(new IdologyQuestionsPresenterModule(this, this.mIdologyTrackingContext)).inject(this);
        this.mQuestionListAdapter = new IdologyQuestionsListAdapter(context, this.mAppCompatActivity, this.mPresenter);
        this.mBinding.rvQuestions.setLayoutManager(new LinearLayoutManager(context, 1, false));
    }

    public void onShow() {
        this.mPresenter.onShow();
        this.mBinding.rvQuestions.setAdapter(this.mQuestionListAdapter);
    }

    public void onHide() {
        this.mPresenter.onHide();
        this.mBinding.rvQuestions.setAdapter(null);
    }

    public void setQuestionList(List<Question> questions) {
        this.mPresenter.setQuestionList(questions);
    }

    public void submitAnswers(String idologyId) {
        this.mPresenter.submitAnswers(idologyId);
    }

    public void updateQuestionList() {
        this.mQuestionListAdapter.notifyDataSetChanged();
    }

    public void showTimeOutDialog() {
        new Builder(this.mContext).setPositiveButton(R.string.idology_timeout_cta, IdologyQuestionsLayout$$Lambda$1.lambdaFactory$(this)).setCancelable(false).setTitle((int) R.string.idology_timeout_title).setMessage(getResources().getString(R.string.idology_timeout_message, new Object[]{Integer.valueOf(this.mPresenter.getTimerInMin())})).show();
    }

    public void updateTimerText(String formattedTime) {
        this.mBinding.tvTimerValue.setText(formattedTime);
    }
}
