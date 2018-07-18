package com.coinbase.android.identityverification;

import java.util.List;

public interface IdentityAcceptableDocumentsScreen {
    void hideProgress();

    void initializeDocsList(List<String> list, List<Integer> list2);

    boolean isShown();
}
