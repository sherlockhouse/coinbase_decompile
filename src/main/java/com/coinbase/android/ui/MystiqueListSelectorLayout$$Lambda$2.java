package com.coinbase.android.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.android.ui.MystiqueListButtonConnector.MystiqueListButtonEvent;

final /* synthetic */ class MystiqueListSelectorLayout$$Lambda$2 implements OnClickListener {
    private final MystiqueListSelectorLayout arg$1;

    private MystiqueListSelectorLayout$$Lambda$2(MystiqueListSelectorLayout mystiqueListSelectorLayout) {
        this.arg$1 = mystiqueListSelectorLayout;
    }

    public static OnClickListener lambdaFactory$(MystiqueListSelectorLayout mystiqueListSelectorLayout) {
        return new MystiqueListSelectorLayout$$Lambda$2(mystiqueListSelectorLayout);
    }

    public void onClick(View view) {
        this.arg$1.mListButtonConnector.get().onNext(MystiqueListButtonEvent.ACTION);
    }
}
