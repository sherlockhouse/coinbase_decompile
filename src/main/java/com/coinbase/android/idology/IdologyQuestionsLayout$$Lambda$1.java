package com.coinbase.android.idology;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class IdologyQuestionsLayout$$Lambda$1 implements OnClickListener {
    private final IdologyQuestionsLayout arg$1;

    private IdologyQuestionsLayout$$Lambda$1(IdologyQuestionsLayout idologyQuestionsLayout) {
        this.arg$1 = idologyQuestionsLayout;
    }

    public static OnClickListener lambdaFactory$(IdologyQuestionsLayout idologyQuestionsLayout) {
        return new IdologyQuestionsLayout$$Lambda$1(idologyQuestionsLayout);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.mPresenter.refreshQuestionList();
    }
}
