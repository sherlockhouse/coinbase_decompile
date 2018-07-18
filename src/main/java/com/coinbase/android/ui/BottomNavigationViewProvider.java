package com.coinbase.android.ui;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

public interface BottomNavigationViewProvider {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface BottomNavigationView {
    }

    View getBottomNavigationView();
}
