package com.coinbase.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import dagger.MembersInjector;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
import rx.functions.Func0;
import rx.functions.Func1;

public final class MainController_MembersInjector implements MembersInjector<MainController> {
    private final Provider<AppCompatActivity> mActivityProvider;
    private final Provider<List<Func0<ActionBarController>>> mControllerFuncsProvider;
    private final Provider<Map<Type, Integer>> mControllerMapProvider;
    private final Provider<Map<ModalBottomNavigationItem.Type, Func1<Bundle, ActionBarController>>> mModalControllerMapProvider;
    private final Provider<MainControllerPresenter> mPresenterProvider;

    public MainController_MembersInjector(Provider<AppCompatActivity> mActivityProvider, Provider<MainControllerPresenter> mPresenterProvider, Provider<List<Func0<ActionBarController>>> mControllerFuncsProvider, Provider<Map<ModalBottomNavigationItem.Type, Func1<Bundle, ActionBarController>>> mModalControllerMapProvider, Provider<Map<Type, Integer>> mControllerMapProvider) {
        this.mActivityProvider = mActivityProvider;
        this.mPresenterProvider = mPresenterProvider;
        this.mControllerFuncsProvider = mControllerFuncsProvider;
        this.mModalControllerMapProvider = mModalControllerMapProvider;
        this.mControllerMapProvider = mControllerMapProvider;
    }

    public static MembersInjector<MainController> create(Provider<AppCompatActivity> mActivityProvider, Provider<MainControllerPresenter> mPresenterProvider, Provider<List<Func0<ActionBarController>>> mControllerFuncsProvider, Provider<Map<ModalBottomNavigationItem.Type, Func1<Bundle, ActionBarController>>> mModalControllerMapProvider, Provider<Map<Type, Integer>> mControllerMapProvider) {
        return new MainController_MembersInjector(mActivityProvider, mPresenterProvider, mControllerFuncsProvider, mModalControllerMapProvider, mControllerMapProvider);
    }

    public void injectMembers(MainController instance) {
        injectMActivity(instance, (AppCompatActivity) this.mActivityProvider.get());
        injectMPresenter(instance, (MainControllerPresenter) this.mPresenterProvider.get());
        injectMControllerFuncs(instance, (List) this.mControllerFuncsProvider.get());
        injectMModalControllerMap(instance, (Map) this.mModalControllerMapProvider.get());
        injectMControllerMap(instance, (Map) this.mControllerMapProvider.get());
    }

    public static void injectMActivity(MainController instance, AppCompatActivity mActivity) {
        instance.mActivity = mActivity;
    }

    public static void injectMPresenter(MainController instance, MainControllerPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMControllerFuncs(MainController instance, List<Func0<ActionBarController>> mControllerFuncs) {
        instance.mControllerFuncs = mControllerFuncs;
    }

    public static void injectMModalControllerMap(MainController instance, Map<ModalBottomNavigationItem.Type, Func1<Bundle, ActionBarController>> mModalControllerMap) {
        instance.mModalControllerMap = mModalControllerMap;
    }

    public static void injectMControllerMap(MainController instance, Map<Type, Integer> mControllerMap) {
        instance.mControllerMap = mControllerMap;
    }
}
