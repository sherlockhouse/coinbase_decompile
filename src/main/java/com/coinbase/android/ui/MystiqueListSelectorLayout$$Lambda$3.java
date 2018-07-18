package com.coinbase.android.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final /* synthetic */ class MystiqueListSelectorLayout$$Lambda$3 implements OnItemClickListener {
    private final MystiqueListSelectorLayout arg$1;

    private MystiqueListSelectorLayout$$Lambda$3(MystiqueListSelectorLayout mystiqueListSelectorLayout) {
        this.arg$1 = mystiqueListSelectorLayout;
    }

    public static OnItemClickListener lambdaFactory$(MystiqueListSelectorLayout mystiqueListSelectorLayout) {
        return new MystiqueListSelectorLayout$$Lambda$3(mystiqueListSelectorLayout);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.arg$1.mListSelectorConnector.get().onNext(Integer.valueOf(i));
    }
}
