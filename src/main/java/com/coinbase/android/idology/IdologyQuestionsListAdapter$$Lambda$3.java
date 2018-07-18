package com.coinbase.android.idology;

import android.util.Pair;
import com.coinbase.android.databinding.ListItemIdologyQuestionBinding;
import rx.functions.Action1;

final /* synthetic */ class IdologyQuestionsListAdapter$$Lambda$3 implements Action1 {
    private final IdologyQuestionsListAdapter arg$1;
    private final ListItemIdologyQuestionBinding arg$2;

    private IdologyQuestionsListAdapter$$Lambda$3(IdologyQuestionsListAdapter idologyQuestionsListAdapter, ListItemIdologyQuestionBinding listItemIdologyQuestionBinding) {
        this.arg$1 = idologyQuestionsListAdapter;
        this.arg$2 = listItemIdologyQuestionBinding;
    }

    public static Action1 lambdaFactory$(IdologyQuestionsListAdapter idologyQuestionsListAdapter, ListItemIdologyQuestionBinding listItemIdologyQuestionBinding) {
        return new IdologyQuestionsListAdapter$$Lambda$3(idologyQuestionsListAdapter, listItemIdologyQuestionBinding);
    }

    public void call(Object obj) {
        IdologyQuestionsListAdapter.lambda$onBindViewHolder$2(this.arg$1, this.arg$2, (Pair) obj);
    }
}
