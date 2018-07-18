package com.coinbase.android.wbl;

public interface ExistingUserDialogScreen {
    void dismiss(boolean z);

    void showCompleteAccountLevels();

    void showIncompleteAccountLevels();

    void showTier0AccountLevels();
}
