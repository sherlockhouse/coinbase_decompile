package android.databinding;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DataBindingUtil {
    private static DataBindingComponent sDefaultComponent = null;
    private static DataBinderMapper sMapper = new DataBinderMapper();

    public static DataBindingComponent getDefaultComponent() {
        return sDefaultComponent;
    }

    public static <T extends ViewDataBinding> T inflate(LayoutInflater inflater, int layoutId, ViewGroup parent, boolean attachToParent) {
        return inflate(inflater, layoutId, parent, attachToParent, sDefaultComponent);
    }

    public static <T extends ViewDataBinding> T inflate(LayoutInflater inflater, int layoutId, ViewGroup parent, boolean attachToParent, DataBindingComponent bindingComponent) {
        boolean useChildren;
        int startChildren = 0;
        if (parent == null || !attachToParent) {
            useChildren = false;
        } else {
            useChildren = true;
        }
        if (useChildren) {
            startChildren = parent.getChildCount();
        }
        View view = inflater.inflate(layoutId, parent, attachToParent);
        if (useChildren) {
            return bindToAddedViews(bindingComponent, parent, startChildren, layoutId);
        }
        return bind(bindingComponent, view, layoutId);
    }

    public static <T extends ViewDataBinding> T bind(View root) {
        return bind(root, sDefaultComponent);
    }

    public static <T extends ViewDataBinding> T bind(View root, DataBindingComponent bindingComponent) {
        T binding = getBinding(root);
        if (binding != null) {
            return binding;
        }
        String tagObj = root.getTag();
        if (tagObj instanceof String) {
            int layoutId = sMapper.getLayoutId(tagObj);
            if (layoutId != 0) {
                return sMapper.getDataBinder(bindingComponent, root, layoutId);
            }
            throw new IllegalArgumentException("View is not a binding layout");
        }
        throw new IllegalArgumentException("View is not a binding layout");
    }

    static <T extends ViewDataBinding> T bind(DataBindingComponent bindingComponent, View[] roots, int layoutId) {
        return sMapper.getDataBinder(bindingComponent, roots, layoutId);
    }

    static <T extends ViewDataBinding> T bind(DataBindingComponent bindingComponent, View root, int layoutId) {
        return sMapper.getDataBinder(bindingComponent, root, layoutId);
    }

    public static <T extends ViewDataBinding> T getBinding(View view) {
        return ViewDataBinding.getBinding(view);
    }

    public static <T extends ViewDataBinding> T setContentView(Activity activity, int layoutId) {
        return setContentView(activity, layoutId, sDefaultComponent);
    }

    public static <T extends ViewDataBinding> T setContentView(Activity activity, int layoutId, DataBindingComponent bindingComponent) {
        activity.setContentView(layoutId);
        return bindToAddedViews(bindingComponent, (ViewGroup) activity.getWindow().getDecorView().findViewById(16908290), 0, layoutId);
    }

    private static <T extends ViewDataBinding> T bindToAddedViews(DataBindingComponent component, ViewGroup parent, int startChildren, int layoutId) {
        int endChildren = parent.getChildCount();
        int childrenAdded = endChildren - startChildren;
        if (childrenAdded == 1) {
            return bind(component, parent.getChildAt(endChildren - 1), layoutId);
        }
        View[] children = new View[childrenAdded];
        for (int i = 0; i < childrenAdded; i++) {
            children[i] = parent.getChildAt(i + startChildren);
        }
        return bind(component, children, layoutId);
    }
}
