package com.coinbase.android.ui;

import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.ModalControllerLifeCycle.ModalDestination;

public interface MainScreen {
    void hideBottomNavigation();

    void showBottomNavigation();

    void switchTo(Type type);

    void switchToModal(Type type, ModalDestination modalDestination);
}
