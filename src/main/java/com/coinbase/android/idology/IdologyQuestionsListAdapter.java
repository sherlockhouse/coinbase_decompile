package com.coinbase.android.idology;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemIdologyQuestionBinding;
import com.coinbase.api.internal.models.idology.Question;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.subscriptions.CompositeSubscription;

public class IdologyQuestionsListAdapter extends Adapter<ViewHolder> {
    private AppCompatActivity mAppCompatActivity;
    private Context mContext;
    @Inject
    IdologyAnswerSelectedConnector mIdologyAnswerSelectedConnector;
    private final Logger mLogger = LoggerFactory.getLogger(IdologyQuestionsListAdapter.class);
    private IdologyQuestionsPresenter mPresenter;
    private List<Question> mQuestionList;

    static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private ListItemIdologyQuestionBinding mBinding;
        final CompositeSubscription mSubscription = new CompositeSubscription();

        public ViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemIdologyQuestionBinding) DataBindingUtil.bind(itemView);
        }

        ListItemIdologyQuestionBinding getBinding() {
            return this.mBinding;
        }
    }

    public IdologyQuestionsListAdapter(Context context, AppCompatActivity appCompatActivity, IdologyQuestionsPresenter presenter) {
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().adapterSubcomponent().inject(this);
        this.mContext = context;
        this.mAppCompatActivity = appCompatActivity;
        this.mPresenter = presenter;
        this.mQuestionList = this.mPresenter.getQuestionList();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_idology_question, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItemIdologyQuestionBinding binding = holder.getBinding();
        Question question = (Question) this.mQuestionList.get(position);
        binding.tvQuestion.setText(question.getPrompt());
        binding.tvAnswer.setText(this.mContext.getString(R.string.idology_answer_header));
        binding.tvAnswerHint.setVisibility(0);
        binding.rlAnswerPrompt.setOnClickListener(IdologyQuestionsListAdapter$$Lambda$1.lambdaFactory$(this, new ArrayList(question.getAnswers()), question));
        holder.mSubscription.clear();
        holder.mSubscription.add(this.mIdologyAnswerSelectedConnector.get().onBackpressureLatest().filter(IdologyQuestionsListAdapter$$Lambda$2.lambdaFactory$(question)).subscribe(IdologyQuestionsListAdapter$$Lambda$3.lambdaFactory$(this, binding), IdologyQuestionsListAdapter$$Lambda$4.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onBindViewHolder$1(Question question, Pair pair) {
        boolean z = pair != null && ((String) pair.first).equals(question.getType());
        return Boolean.valueOf(z);
    }

    static /* synthetic */ void lambda$onBindViewHolder$2(IdologyQuestionsListAdapter this_, ListItemIdologyQuestionBinding binding, Pair pair) {
        binding.tvAnswer.setText((CharSequence) pair.second);
        binding.tvAnswerHint.setVisibility(8);
        this_.mPresenter.updateAnswerMap((String) pair.first, (String) pair.second);
    }

    public int getItemCount() {
        return this.mQuestionList.size();
    }

    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.mSubscription.clear();
    }

    private void showAnswerListDialog(ArrayList<String> answerList, String type) {
        if (this.mAppCompatActivity != null) {
            DialogFragment dialogFragment = new IdologyQuestionDialogFragment();
            Bundle args = new Bundle();
            args.putStringArrayList(IdologyQuestionDialogFragment.IDOLOGY_ANSWERS, answerList);
            args.putString(IdologyQuestionDialogFragment.IDOLOGY_TYPE, type);
            dialogFragment.setArguments(args);
            dialogFragment.show(this.mAppCompatActivity.getSupportFragmentManager(), "idology_questions");
        }
    }
}
