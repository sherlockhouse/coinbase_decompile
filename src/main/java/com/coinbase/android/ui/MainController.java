package com.coinbase.android.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.support.RouterPagerAdapter;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.MystiqueBottomNavigationControllerBinding;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.ModalControllerLifeCycle.ModalDestination;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class MainController extends Controller implements MainScreen {
    @Inject
    AppCompatActivity mActivity;
    private MystiqueBottomNavigationControllerBinding mBinding;
    @Inject
    List<Func0<ActionBarController>> mControllerFuncs;
    @Inject
    Map<Type, Integer> mControllerMap;
    List<ActionBarController> mControllers;
    private final Logger mLogger = LoggerFactory.getLogger(MainController.class);
    @Inject
    Map<ModalBottomNavigationItem.Type, Func1<Bundle, ActionBarController>> mModalControllerMap;
    private RouterPagerAdapter mPagerAdapter;
    @Inject
    MainControllerPresenter mPresenter;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (MystiqueBottomNavigationControllerBinding) DataBindingUtil.inflate(inflater, R.layout.mystique_bottom_navigation_controller, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().mainControllerSubcomponent(new MainControllerPresenterModule(this)).inject(this);
        this.mPagerAdapter = new RouterPagerAdapter(this) {
            public void configureRouter(Router router, int position) {
                if (!router.hasRootController()) {
                    ActionBarController controller = (ActionBarController) ((Func0) MainController.this.mControllerFuncs.get(position)).call();
                    MainController.this.initializeControllersAndAdd(controller, position);
                    router.setRoot(RouterTransaction.with(controller).tag(controller.getTag()));
                }
            }

            public Object instantiateItem(ViewGroup container, int position) {
                Object o = super.instantiateItem(container, position);
                controllersReplaceInstanceof(o, position);
                return o;
            }

            public int getCount() {
                return MainController.this.mControllerFuncs.size();
            }

            private void controllersReplaceInstanceof(Object o, int position) {
                if (o instanceof Router) {
                    Router router = (Router) o;
                    if (router.getBackstackSize() > 0) {
                        Controller controllerFromSavedInstanceState = ((RouterTransaction) router.getBackstack().get(0)).controller();
                        if (controllerFromSavedInstanceState instanceof ActionBarController) {
                            ActionBarController actionBarController = (ActionBarController) controllerFromSavedInstanceState;
                            MainController.this.initializeControllersAndAdd(actionBarController, position);
                            actionBarController.injectComponentIfNeeded();
                        }
                    }
                }
            }
        };
        this.mPresenter.onCreateView();
        this.mBinding.vpControllerPager.setAdapter(this.mPagerAdapter);
        return this.mBinding.getRoot();
    }

    protected void onDestroyView(View view) {
        super.onDestroyView(view);
        this.mBinding.vpControllerPager.setAdapter(null);
        this.mSubscription.clear();
    }

    protected void onAttach(View view) {
        super.onAttach(view);
        this.mBinding.cvBottom.onShow(this.mActivity);
        if (this.mPresenter != null) {
            this.mPresenter.onAttach();
            this.mBinding.vpControllerPager.setOffscreenPageLimit(this.mControllerFuncs.size() + 1);
        }
    }

    protected void onDetach(View view) {
        super.onDetach(view);
        this.mBinding.cvBottom.onHide();
        if (this.mPresenter != null) {
            this.mPresenter.onDetach();
        }
    }

    protected void onActivityPaused(Activity activity) {
        super.onActivityPaused(activity);
        if (this.mPresenter != null) {
            this.mPresenter.onActivityPaused();
        }
    }

    protected void onActivityResumed(Activity activity) {
        super.onActivityResumed(activity);
        if (this.mPresenter != null) {
            this.mPresenter.onActivityResumed();
        }
    }

    public void switchTo(Type type) {
        this.mActivity.supportInvalidateOptionsMenu();
        if (this.mControllerMap.get(type) != null) {
            this.mBinding.vpControllerPager.setCurrentItem(((Integer) this.mControllerMap.get(type)).intValue(), false);
        }
    }

    public void switchToModal(Type parentType, ModalDestination modalDestination) {
        if (this.mControllerMap.containsKey(parentType)) {
            Controller parentControllerTop;
            ActionBarController parentController = (ActionBarController) this.mControllers.get(((Integer) this.mControllerMap.get(parentType)).intValue());
            if (parentController.getRouter().getBackstackSize() <= 0) {
                parentControllerTop = null;
            } else {
                parentControllerTop = ((RouterTransaction) parentController.getRouter().getBackstack().get(parentController.getRouter().getBackstackSize() - 1)).controller();
            }
            if (parentControllerTop instanceof ActionBarController) {
                Func1<Bundle, ActionBarController> modalCreatorFunc = modalDestination.getPushModalControllerFunc();
                if (modalCreatorFunc == null) {
                    ((ActionBarController) parentControllerTop).pushModalController((ActionBarController) ((Func1) this.mModalControllerMap.get(modalDestination.getType())).call(parentController.appendModalArgs(new Bundle())));
                    return;
                } else {
                    ((ActionBarController) parentControllerTop).pushModalController((ActionBarController) modalCreatorFunc.call(parentController.appendModalArgs(new Bundle())));
                    return;
                }
            }
            this.mLogger.error("Parent controller top isn't an ActionBarController.  Should never happen.");
        }
    }

    public View getBottomNavigationView() {
        if (this.mBinding == null) {
            return null;
        }
        return this.mBinding.clControllerContainer;
    }

    public void showBottomNavigation() {
        this.mBinding.cvBottom.setVisibility(0);
    }

    public void hideBottomNavigation() {
        this.mBinding.cvBottom.setVisibility(8);
    }

    public ViewGroup getModalView() {
        return this.mBinding.flModalContainer;
    }

    private void initializeControllersAndAdd(ActionBarController controller, int position) {
        if (this.mControllers == null || this.mControllers.isEmpty()) {
            this.mControllers = new ArrayList(this.mControllerFuncs.size());
        }
        if (position < this.mControllers.size()) {
            this.mControllers.remove(position);
        } else {
            for (int i = this.mControllers.size(); i < position; i++) {
                this.mControllers.add(i, null);
            }
        }
        this.mControllers.add(position, controller);
    }
}
