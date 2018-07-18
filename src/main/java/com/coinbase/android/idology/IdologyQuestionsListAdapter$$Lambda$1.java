package com.coinbase.android.idology;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.api.internal.models.idology.Question;
import java.util.ArrayList;

final /* synthetic */ class IdologyQuestionsListAdapter$$Lambda$1 implements OnClickListener {
    private final IdologyQuestionsListAdapter arg$1;
    private final ArrayList arg$2;
    private final Question arg$3;

    private IdologyQuestionsListAdapter$$Lambda$1(IdologyQuestionsListAdapter idologyQuestionsListAdapter, ArrayList arrayList, Question question) {
        this.arg$1 = idologyQuestionsListAdapter;
        this.arg$2 = arrayList;
        this.arg$3 = question;
    }

    public static OnClickListener lambdaFactory$(IdologyQuestionsListAdapter idologyQuestionsListAdapter, ArrayList arrayList, Question question) {
        return new IdologyQuestionsListAdapter$$Lambda$1(idologyQuestionsListAdapter, arrayList, question);
    }

    public void onClick(View view) {
        this.arg$1.showAnswerListDialog(this.arg$2, this.arg$3.getType());
    }
}
