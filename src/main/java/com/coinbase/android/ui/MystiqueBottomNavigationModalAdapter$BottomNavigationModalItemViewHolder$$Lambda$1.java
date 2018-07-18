package com.coinbase.android.ui;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MystiqueBottomNavigationModalAdapter$BottomNavigationModalItemViewHolder$$Lambda$1 implements OnClickListener {
    private final BottomNavigationModalItemViewHolder arg$1;
    private final ModalBottomNavigationItem arg$2;

    private MystiqueBottomNavigationModalAdapter$BottomNavigationModalItemViewHolder$$Lambda$1(BottomNavigationModalItemViewHolder bottomNavigationModalItemViewHolder, ModalBottomNavigationItem modalBottomNavigationItem) {
        this.arg$1 = bottomNavigationModalItemViewHolder;
        this.arg$2 = modalBottomNavigationItem;
    }

    public static OnClickListener lambdaFactory$(BottomNavigationModalItemViewHolder bottomNavigationModalItemViewHolder, ModalBottomNavigationItem modalBottomNavigationItem) {
        return new MystiqueBottomNavigationModalAdapter$BottomNavigationModalItemViewHolder$$Lambda$1(bottomNavigationModalItemViewHolder, modalBottomNavigationItem);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onModalSelected(this.arg$2);
    }
}
