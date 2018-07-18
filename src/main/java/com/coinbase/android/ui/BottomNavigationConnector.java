package com.coinbase.android.ui;

import com.coinbase.android.ApplicationSignOutListener;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.ModalControllerLifeCycle.ModalDestination;
import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import rx.subjects.BehaviorSubject;

public final class BottomNavigationConnector implements ApplicationSignOutListener {
    private BehaviorSubject<ModalDestination> mModalSubject;
    private volatile BehaviorSubject<PageDestination> mSubject;

    public BottomNavigationConnector() {
        this(BehaviorSubject.create(PageDestination.builder().setBottomNavigationItem(Type.DASHBOARD).build()), BehaviorSubject.create());
    }

    BottomNavigationConnector(BehaviorSubject<PageDestination> subject, BehaviorSubject<ModalDestination> modalSubject) {
        this.mSubject = subject;
        this.mModalSubject = modalSubject;
    }

    public BehaviorSubject<PageDestination> get() {
        return this.mSubject;
    }

    public BehaviorSubject<ModalDestination> getModal() {
        return this.mModalSubject;
    }

    public void onApplicationSignOut() {
        this.mSubject = BehaviorSubject.create(PageDestination.builder().setBottomNavigationItem(Type.DASHBOARD).build());
        this.mModalSubject = BehaviorSubject.create();
    }
}
