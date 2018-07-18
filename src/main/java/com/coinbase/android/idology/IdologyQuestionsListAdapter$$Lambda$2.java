package com.coinbase.android.idology;

import android.util.Pair;
import com.coinbase.api.internal.models.idology.Question;
import rx.functions.Func1;

final /* synthetic */ class IdologyQuestionsListAdapter$$Lambda$2 implements Func1 {
    private final Question arg$1;

    private IdologyQuestionsListAdapter$$Lambda$2(Question question) {
        this.arg$1 = question;
    }

    public static Func1 lambdaFactory$(Question question) {
        return new IdologyQuestionsListAdapter$$Lambda$2(question);
    }

    public Object call(Object obj) {
        return IdologyQuestionsListAdapter.lambda$onBindViewHolder$1(this.arg$1, (Pair) obj);
    }
}
