package com.coinbase.android;

import android.app.Application;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler;
import com.coinbase.android.buysell.BuyController;
import com.coinbase.android.buysell.BuyRouter.BuySource;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BackgroundDimmer.BlockingOverlay;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.BottomNavigationViewProvider;
import com.coinbase.android.ui.BottomNavigationViewProvider.BottomNavigationView;
import com.coinbase.android.ui.CoinbaseResources.GenericErrorMessage;
import com.coinbase.android.ui.CoinbaseResources.GenericErrorTryAgainMessage;
import com.coinbase.android.ui.ControllerLifeCycle;
import com.coinbase.android.ui.ControllerLifeCycleFactory.LifeCycleType;
import com.coinbase.android.ui.ControllerTransitionContainer;
import com.coinbase.android.ui.ModalBottomNavigationItem;
import com.coinbase.android.ui.ModalNavItem;
import com.coinbase.android.ui.ModalViewProvider;
import com.coinbase.android.ui.PageControllerLifeCycle;
import com.coinbase.android.ui.RootViewWrapper;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.SnackBarWrapper.SnackBarRoot;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import rx.functions.Func0;
import rx.functions.Func1;

public class BottomNavActivityModule {
    private static final int TRANSITION_ANIMATION_DURATION = 250;
    private final BottomNavigationViewProvider mBottomNavigationViewProvider;
    private final ModalViewProvider mModalViewProvider;

    public BottomNavActivityModule(BottomNavigationViewProvider bottomNavViewProvider, ModalViewProvider modalViewProvider) {
        this.mBottomNavigationViewProvider = bottomNavViewProvider;
        this.mModalViewProvider = modalViewProvider;
    }

    @BottomNavigationView
    @ActivityScope
    public Func0<View> providesBottomNavigationViewFunc() {
        return BottomNavActivityModule$$Lambda$1.lambdaFactory$(this);
    }

    @BottomNavigationView
    @ActivityScope
    public View providesBottomNavigationView(@BottomNavigationView Func0<View> viewFunc) {
        return (View) viewFunc.call();
    }

    @ActivityScope
    RootViewWrapper providesRootViewWrapper(@SnackBarRoot Func0<View> rootView, @BottomNavigationView Func0<View> bottomNavigationView, @BlockingOverlay Func0<RelativeLayout> blockingOverlayView, @ModalNavItem Func0<ViewGroup> modalView) {
        return new RootViewWrapper(rootView, bottomNavigationView, blockingOverlayView, modalView);
    }

    @ActivityScope
    public SnackBarWrapper providesSnackBarWrapper(Application app, @GenericErrorMessage int genericeErrorRes, @GenericErrorTryAgainMessage int genericErrorTryAgainRes, RootViewWrapper rootViewWrapper) {
        return new SnackBarWrapper(app, genericeErrorRes, genericErrorTryAgainRes, rootViewWrapper);
    }

    @ActivityScope
    List<Pair<Type, Func0<ActionBarController>>> providesPageControllerPairs() {
        Func0<ActionBarController> mDashboardController = BottomNavActivityModule$$Lambda$2.lambdaFactory$(this);
        Func0<ActionBarController> mAccountMainController = BottomNavActivityModule$$Lambda$3.lambdaFactory$(this);
        Func0<ActionBarController> mPriceAlertsController = BottomNavActivityModule$$Lambda$4.lambdaFactory$(this);
        Func0<ActionBarController> mAccountSettingsController = BottomNavActivityModule$$Lambda$5.lambdaFactory$(this);
        return Arrays.asList(new Pair[]{new Pair(Type.DASHBOARD, mDashboardController), new Pair(Type.ACCOUNTS, mAccountMainController), new Pair(Type.ALERTS, mPriceAlertsController), new Pair(Type.MORE, mAccountSettingsController)});
    }

    @ActivityScope
    List<Func0<ActionBarController>> providesPageControllers(List<Pair<Type, Func0<ActionBarController>>> controllerPairs) {
        LinkedList<Func0<ActionBarController>> list = new LinkedList();
        for (Pair<Type, Func0<ActionBarController>> pair : controllerPairs) {
            list.add(pair.second);
        }
        return list;
    }

    @ActivityScope
    public Map<Type, Integer> providesPageControllerMap(List<Pair<Type, Func0<ActionBarController>>> controllers) {
        return createControllerMap(controllers);
    }

    @ModalNavItem
    @ActivityScope
    public List<Pair<ModalBottomNavigationItem.Type, Func1<Bundle, ActionBarController>>> providesModalControllerFuncs() {
        return Arrays.asList(new Pair[]{new Pair(ModalBottomNavigationItem.Type.NEW_BUY, BottomNavActivityModule$$Lambda$6.lambdaFactory$())});
    }

    static /* synthetic */ ActionBarController lambda$providesModalControllerFuncs$5(Bundle args) {
        args.putString("PAGE_SOURCE", BuySource.OTHER.toString());
        args.putInt(ActionBarController.OVERIDDEN_UP_INDICATOR, R.drawable.modal_close_gray);
        return new BuyController(args);
    }

    @ActivityScope
    public Map<ModalBottomNavigationItem.Type, Func1<Bundle, ActionBarController>> providesModalControllerMap(@ModalNavItem List<Pair<ModalBottomNavigationItem.Type, Func1<Bundle, ActionBarController>>> list) {
        return createModalControllerMap(list);
    }

    @ActivityScope
    public ControllerTransitionContainer providesDefaultControllerTransitionContainer() {
        return new ControllerTransitionContainer() {
            public ControllerChangeHandler pushChangeHandler() {
                return new HorizontalChangeHandler(250);
            }

            public ControllerChangeHandler popChangeHandler() {
                return new HorizontalChangeHandler(250);
            }

            public ControllerChangeHandler modalPushChangeHandler() {
                return new VerticalChangeHandler(250);
            }

            public ControllerChangeHandler modalPopChangeHandler() {
                return new VerticalChangeHandler(250);
            }

            public ControllerChangeHandler dialogPushChangeHandler() {
                return new FadeChangeHandler(false);
            }

            public ControllerChangeHandler dialogPopChangeHandler() {
                return new FadeChangeHandler();
            }

            public int getTransitionDuration() {
                return 250;
            }
        };
    }

    @ModalNavItem
    @ActivityScope
    Func0<ViewGroup> providesModalView() {
        return BottomNavActivityModule$$Lambda$7.lambdaFactory$(this);
    }

    public Bundle createBundle(Type type, LifeCycleType lifeCycleType) {
        Bundle bundle = new Bundle();
        bundle.putInt(PageControllerLifeCycle.TYPE, type.getValue());
        bundle.putInt(ControllerLifeCycle.LIFE_CYCLE_TYPE, lifeCycleType.getValue());
        return bundle;
    }

    public Map<Type, Integer> createControllerMap(List<Pair<Type, Func0<ActionBarController>>> controllers) {
        Map<Type, Integer> controllerMap = new HashMap();
        for (int i = 0; i < controllers.size(); i++) {
            controllerMap.put(((Pair) controllers.get(i)).first, Integer.valueOf(i));
        }
        return controllerMap;
    }

    Map<ModalBottomNavigationItem.Type, Func1<Bundle, ActionBarController>> createModalControllerMap(List<Pair<ModalBottomNavigationItem.Type, Func1<Bundle, ActionBarController>>> list) {
        Map<ModalBottomNavigationItem.Type, Func1<Bundle, ActionBarController>> modalControllerMap = new HashMap();
        for (Pair<ModalBottomNavigationItem.Type, Func1<Bundle, ActionBarController>> pair : list) {
            modalControllerMap.put(pair.first, pair.second);
        }
        return modalControllerMap;
    }
}
