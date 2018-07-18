package com.coinbase.android.ui;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MystiqueBottomNavigationAdapter$BottomNavigationItemViewHolder$$Lambda$1 implements OnClickListener {
    private final BottomNavigationItemViewHolder arg$1;
    private final BottomNavigationItem arg$2;

    private MystiqueBottomNavigationAdapter$BottomNavigationItemViewHolder$$Lambda$1(BottomNavigationItemViewHolder bottomNavigationItemViewHolder, BottomNavigationItem bottomNavigationItem) {
        this.arg$1 = bottomNavigationItemViewHolder;
        this.arg$2 = bottomNavigationItem;
    }

    public static OnClickListener lambdaFactory$(BottomNavigationItemViewHolder bottomNavigationItemViewHolder, BottomNavigationItem bottomNavigationItem) {
        return new MystiqueBottomNavigationAdapter$BottomNavigationItemViewHolder$$Lambda$1(bottomNavigationItemViewHolder, bottomNavigationItem);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onSelected(this.arg$2);
    }
}
