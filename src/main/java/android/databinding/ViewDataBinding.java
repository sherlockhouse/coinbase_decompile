package android.databinding;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.databinding.CallbackRegistry.NotifierCallback;
import android.databinding.Observable.OnPropertyChangedCallback;
import android.databinding.ObservableList.OnListChangedCallback;
import android.databinding.ObservableMap.OnMapChangedCallback;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import com.android.databinding.library.R;
import com.coinbase.android.ui.NumericKeypadConnector;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public abstract class ViewDataBinding extends BaseObservable {
    private static final int BINDING_NUMBER_START = BINDING_TAG_PREFIX.length();
    public static final String BINDING_TAG_PREFIX = "binding_";
    private static final CreateWeakListener CREATE_LIST_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int localFieldId) {
            return new WeakListListener(viewDataBinding, localFieldId).getListener();
        }
    };
    private static final CreateWeakListener CREATE_MAP_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int localFieldId) {
            return new WeakMapListener(viewDataBinding, localFieldId).getListener();
        }
    };
    private static final CreateWeakListener CREATE_PROPERTY_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int localFieldId) {
            return new WeakPropertyListener(viewDataBinding, localFieldId).getListener();
        }
    };
    private static final int HALTED = 2;
    private static final int REBIND = 1;
    private static final NotifierCallback<OnRebindCallback, ViewDataBinding, Void> REBIND_NOTIFIER = new NotifierCallback<OnRebindCallback, ViewDataBinding, Void>() {
        public void onNotifyCallback(OnRebindCallback callback, ViewDataBinding sender, int mode, Void arg2) {
            switch (mode) {
                case 1:
                    if (!callback.onPreBind(sender)) {
                        sender.mRebindHalted = true;
                        return;
                    }
                    return;
                case 2:
                    callback.onCanceled(sender);
                    return;
                case 3:
                    callback.onBound(sender);
                    return;
                default:
                    return;
            }
        }
    };
    private static final int REBOUND = 3;
    private static final OnAttachStateChangeListener ROOT_REATTACHED_LISTENER;
    static int SDK_INT = VERSION.SDK_INT;
    private static final boolean USE_CHOREOGRAPHER;
    private static final boolean USE_TAG_ID = (DataBinderMapper.TARGET_MIN_SDK >= 14);
    private static final ReferenceQueue<ViewDataBinding> sReferenceQueue = new ReferenceQueue();
    protected final DataBindingComponent mBindingComponent;
    private Choreographer mChoreographer;
    private ViewDataBinding mContainingBinding;
    private final FrameCallback mFrameCallback;
    private boolean mIsExecutingPendingBindings;
    private WeakListener[] mLocalFieldObservers;
    private boolean mPendingRebind = false;
    private CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> mRebindCallbacks;
    private boolean mRebindHalted = false;
    private final Runnable mRebindRunnable = new Runnable() {
        public void run() {
            synchronized (this) {
                ViewDataBinding.this.mPendingRebind = false;
            }
            ViewDataBinding.processReferenceQueue();
            if (VERSION.SDK_INT < 19 || ViewDataBinding.this.mRoot.isAttachedToWindow()) {
                ViewDataBinding.this.executePendingBindings();
                return;
            }
            ViewDataBinding.this.mRoot.removeOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
            ViewDataBinding.this.mRoot.addOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
        }
    };
    private final View mRoot;
    private Handler mUIThreadHandler;

    private interface CreateWeakListener {
        WeakListener create(ViewDataBinding viewDataBinding, int i);
    }

    protected static class IncludedLayouts {
        public final int[][] indexes;
        public final int[][] layoutIds;
        public final String[][] layouts;

        public IncludedLayouts(int bindingCount) {
            this.layouts = new String[bindingCount][];
            this.indexes = new int[bindingCount][];
            this.layoutIds = new int[bindingCount][];
        }

        public void setIncludes(int index, String[] layouts, int[] indexes, int[] layoutIds) {
            this.layouts[index] = layouts;
            this.indexes[index] = indexes;
            this.layoutIds[index] = layoutIds;
        }
    }

    private interface ObservableReference<T> {
        void addListener(T t);

        void removeListener(T t);
    }

    protected static abstract class PropertyChangedInverseListener extends OnPropertyChangedCallback implements InverseBindingListener {
    }

    private static class WeakListListener extends OnListChangedCallback implements ObservableReference<ObservableList> {
        final WeakListener<ObservableList> mListener;

        public WeakListListener(ViewDataBinding binder, int localFieldId) {
            this.mListener = new WeakListener(binder, localFieldId, this);
        }

        public WeakListener<ObservableList> getListener() {
            return this.mListener;
        }

        public void addListener(ObservableList target) {
            target.addOnListChangedCallback(this);
        }

        public void removeListener(ObservableList target) {
            target.removeOnListChangedCallback(this);
        }
    }

    private static class WeakListener<T> extends WeakReference<ViewDataBinding> {
        protected final int mLocalFieldId;
        private final ObservableReference<T> mObservable;
        private T mTarget;

        public WeakListener(ViewDataBinding binder, int localFieldId, ObservableReference<T> observable) {
            super(binder, ViewDataBinding.sReferenceQueue);
            this.mLocalFieldId = localFieldId;
            this.mObservable = observable;
        }

        public void setTarget(T object) {
            unregister();
            this.mTarget = object;
            if (this.mTarget != null) {
                this.mObservable.addListener(this.mTarget);
            }
        }

        public boolean unregister() {
            boolean unregistered = false;
            if (this.mTarget != null) {
                this.mObservable.removeListener(this.mTarget);
                unregistered = true;
            }
            this.mTarget = null;
            return unregistered;
        }

        public T getTarget() {
            return this.mTarget;
        }

        protected ViewDataBinding getBinder() {
            ViewDataBinding binder = (ViewDataBinding) get();
            if (binder == null) {
                unregister();
            }
            return binder;
        }
    }

    private static class WeakMapListener extends OnMapChangedCallback implements ObservableReference<ObservableMap> {
        final WeakListener<ObservableMap> mListener;

        public WeakMapListener(ViewDataBinding binder, int localFieldId) {
            this.mListener = new WeakListener(binder, localFieldId, this);
        }

        public WeakListener<ObservableMap> getListener() {
            return this.mListener;
        }

        public void addListener(ObservableMap target) {
            target.addOnMapChangedCallback(this);
        }

        public void removeListener(ObservableMap target) {
            target.removeOnMapChangedCallback(this);
        }
    }

    private static class WeakPropertyListener extends OnPropertyChangedCallback implements ObservableReference<Observable> {
        final WeakListener<Observable> mListener;

        public WeakPropertyListener(ViewDataBinding binder, int localFieldId) {
            this.mListener = new WeakListener(binder, localFieldId, this);
        }

        public WeakListener<Observable> getListener() {
            return this.mListener;
        }

        public void addListener(Observable target) {
            target.addOnPropertyChangedCallback(this);
        }

        public void removeListener(Observable target) {
            target.removeOnPropertyChangedCallback(this);
        }

        public void onPropertyChanged(Observable sender, int propertyId) {
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null && ((Observable) this.mListener.getTarget()) == sender) {
                binder.handleFieldChange(this.mListener.mLocalFieldId, sender, propertyId);
            }
        }
    }

    protected abstract void executeBindings();

    public abstract boolean hasPendingBindings();

    public abstract void invalidateAll();

    protected abstract boolean onFieldChange(int i, Object obj, int i2);

    public abstract boolean setVariable(int i, Object obj);

    static {
        boolean z = true;
        if (SDK_INT < 16) {
            z = false;
        }
        USE_CHOREOGRAPHER = z;
        if (VERSION.SDK_INT < 19) {
            ROOT_REATTACHED_LISTENER = null;
        } else {
            ROOT_REATTACHED_LISTENER = new OnAttachStateChangeListener() {
                @TargetApi(19)
                public void onViewAttachedToWindow(View v) {
                    ViewDataBinding.getBinding(v).mRebindRunnable.run();
                    v.removeOnAttachStateChangeListener(this);
                }

                public void onViewDetachedFromWindow(View v) {
                }
            };
        }
    }

    protected ViewDataBinding(DataBindingComponent bindingComponent, View root, int localFieldCount) {
        this.mBindingComponent = bindingComponent;
        this.mLocalFieldObservers = new WeakListener[localFieldCount];
        this.mRoot = root;
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("DataBinding must be created in view's UI Thread");
        } else if (USE_CHOREOGRAPHER) {
            this.mChoreographer = Choreographer.getInstance();
            this.mFrameCallback = new FrameCallback() {
                public void doFrame(long frameTimeNanos) {
                    ViewDataBinding.this.mRebindRunnable.run();
                }
            };
        } else {
            this.mFrameCallback = null;
            this.mUIThreadHandler = new Handler(Looper.myLooper());
        }
    }

    protected void setRootTag(View view) {
        if (USE_TAG_ID) {
            view.setTag(R.id.dataBinding, this);
        } else {
            view.setTag(this);
        }
    }

    protected void setRootTag(View[] views) {
        int i = 0;
        int length;
        if (USE_TAG_ID) {
            length = views.length;
            while (i < length) {
                views[i].setTag(R.id.dataBinding, this);
                i++;
            }
            return;
        }
        length = views.length;
        while (i < length) {
            views[i].setTag(this);
            i++;
        }
    }

    public static int getBuildSdkInt() {
        return SDK_INT;
    }

    public void addOnRebindCallback(OnRebindCallback listener) {
        if (this.mRebindCallbacks == null) {
            this.mRebindCallbacks = new CallbackRegistry(REBIND_NOTIFIER);
        }
        this.mRebindCallbacks.add(listener);
    }

    public void removeOnRebindCallback(OnRebindCallback listener) {
        if (this.mRebindCallbacks != null) {
            this.mRebindCallbacks.remove(listener);
        }
    }

    public void executePendingBindings() {
        if (this.mContainingBinding == null) {
            executeBindingsInternal();
        } else {
            this.mContainingBinding.executePendingBindings();
        }
    }

    private void executeBindingsInternal() {
        if (this.mIsExecutingPendingBindings) {
            requestRebind();
        } else if (hasPendingBindings()) {
            this.mIsExecutingPendingBindings = true;
            this.mRebindHalted = false;
            if (this.mRebindCallbacks != null) {
                this.mRebindCallbacks.notifyCallbacks(this, 1, null);
                if (this.mRebindHalted) {
                    this.mRebindCallbacks.notifyCallbacks(this, 2, null);
                }
            }
            if (!this.mRebindHalted) {
                executeBindings();
                if (this.mRebindCallbacks != null) {
                    this.mRebindCallbacks.notifyCallbacks(this, 3, null);
                }
            }
            this.mIsExecutingPendingBindings = false;
        }
    }

    protected static void executeBindingsOn(ViewDataBinding other) {
        other.executeBindingsInternal();
    }

    void forceExecuteBindings() {
        executeBindings();
    }

    public void unbind() {
        for (WeakListener weakListener : this.mLocalFieldObservers) {
            if (weakListener != null) {
                weakListener.unregister();
            }
        }
    }

    static ViewDataBinding getBinding(View v) {
        if (v != null) {
            if (USE_TAG_ID) {
                return (ViewDataBinding) v.getTag(R.id.dataBinding);
            }
            Object tag = v.getTag();
            if (tag instanceof ViewDataBinding) {
                return (ViewDataBinding) tag;
            }
        }
        return null;
    }

    public View getRoot() {
        return this.mRoot;
    }

    private void handleFieldChange(int mLocalFieldId, Object object, int fieldId) {
        if (onFieldChange(mLocalFieldId, object, fieldId)) {
            requestRebind();
        }
    }

    protected boolean unregisterFrom(int localFieldId) {
        WeakListener listener = this.mLocalFieldObservers[localFieldId];
        if (listener != null) {
            return listener.unregister();
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void requestRebind() {
        if (this.mContainingBinding != null) {
            this.mContainingBinding.requestRebind();
            return;
        }
        synchronized (this) {
            if (this.mPendingRebind) {
                return;
            }
            this.mPendingRebind = true;
        }
    }

    protected Object getObservedField(int localFieldId) {
        WeakListener listener = this.mLocalFieldObservers[localFieldId];
        if (listener == null) {
            return null;
        }
        return listener.getTarget();
    }

    private boolean updateRegistration(int localFieldId, Object observable, CreateWeakListener listenerCreator) {
        if (observable == null) {
            return unregisterFrom(localFieldId);
        }
        WeakListener listener = this.mLocalFieldObservers[localFieldId];
        if (listener == null) {
            registerTo(localFieldId, observable, listenerCreator);
            return true;
        } else if (listener.getTarget() == observable) {
            return false;
        } else {
            unregisterFrom(localFieldId);
            registerTo(localFieldId, observable, listenerCreator);
            return true;
        }
    }

    protected boolean updateRegistration(int localFieldId, Observable observable) {
        return updateRegistration(localFieldId, observable, CREATE_PROPERTY_LISTENER);
    }

    protected boolean updateRegistration(int localFieldId, ObservableList observable) {
        return updateRegistration(localFieldId, observable, CREATE_LIST_LISTENER);
    }

    protected boolean updateRegistration(int localFieldId, ObservableMap observable) {
        return updateRegistration(localFieldId, observable, CREATE_MAP_LISTENER);
    }

    protected void ensureBindingComponentIsNotNull(Class<?> oneExample) {
        if (this.mBindingComponent == null) {
            throw new IllegalStateException("Required DataBindingComponent is null in class " + getClass().getSimpleName() + ". A BindingAdapter in " + oneExample.getCanonicalName() + " is not static and requires an object to use, retrieved from the " + "DataBindingComponent. If you don't use an inflation method taking a " + "DataBindingComponent, use DataBindingUtil.setDefaultComponent or " + "make all BindingAdapter methods static.");
        }
    }

    protected void registerTo(int localFieldId, Object observable, CreateWeakListener listenerCreator) {
        if (observable != null) {
            WeakListener listener = this.mLocalFieldObservers[localFieldId];
            if (listener == null) {
                listener = listenerCreator.create(this, localFieldId);
                this.mLocalFieldObservers[localFieldId] = listener;
            }
            listener.setTarget(observable);
        }
    }

    protected static ViewDataBinding bind(DataBindingComponent bindingComponent, View view, int layoutId) {
        return DataBindingUtil.bind(bindingComponent, view, layoutId);
    }

    protected static Object[] mapBindings(DataBindingComponent bindingComponent, View root, int numBindings, IncludedLayouts includes, SparseIntArray viewsWithIds) {
        Object[] bindings = new Object[numBindings];
        mapBindings(bindingComponent, root, bindings, includes, viewsWithIds, true);
        return bindings;
    }

    protected static boolean parse(String str, boolean fallback) {
        return str == null ? fallback : Boolean.parseBoolean(str);
    }

    protected static byte parse(String str, byte fallback) {
        try {
            fallback = Byte.parseByte(str);
        } catch (NumberFormatException e) {
        }
        return fallback;
    }

    protected static short parse(String str, short fallback) {
        try {
            fallback = Short.parseShort(str);
        } catch (NumberFormatException e) {
        }
        return fallback;
    }

    protected static int parse(String str, int fallback) {
        try {
            fallback = Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }
        return fallback;
    }

    protected static long parse(String str, long fallback) {
        try {
            fallback = Long.parseLong(str);
        } catch (NumberFormatException e) {
        }
        return fallback;
    }

    protected static float parse(String str, float fallback) {
        try {
            fallback = Float.parseFloat(str);
        } catch (NumberFormatException e) {
        }
        return fallback;
    }

    protected static double parse(String str, double fallback) {
        try {
            fallback = Double.parseDouble(str);
        } catch (NumberFormatException e) {
        }
        return fallback;
    }

    protected static char parse(String str, char fallback) {
        return (str == null || str.isEmpty()) ? fallback : str.charAt(0);
    }

    protected static int getColorFromResource(View view, int resourceId) {
        if (VERSION.SDK_INT >= 23) {
            return view.getContext().getColor(resourceId);
        }
        return view.getResources().getColor(resourceId);
    }

    protected static ColorStateList getColorStateListFromResource(View view, int resourceId) {
        if (VERSION.SDK_INT >= 23) {
            return view.getContext().getColorStateList(resourceId);
        }
        return view.getResources().getColorStateList(resourceId);
    }

    protected static Drawable getDrawableFromResource(View view, int resourceId) {
        if (VERSION.SDK_INT >= 21) {
            return view.getContext().getDrawable(resourceId);
        }
        return view.getResources().getDrawable(resourceId);
    }

    protected static <T> T getFromArray(T[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return null;
        }
        return arr[index];
    }

    protected static <T> void setTo(T[] arr, int index, T value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static boolean getFromArray(boolean[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return false;
        }
        return arr[index];
    }

    protected static void setTo(boolean[] arr, int index, boolean value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static byte getFromArray(byte[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return (byte) 0;
        }
        return arr[index];
    }

    protected static void setTo(byte[] arr, int index, byte value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static short getFromArray(short[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return (short) 0;
        }
        return arr[index];
    }

    protected static void setTo(short[] arr, int index, short value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static char getFromArray(char[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return '\u0000';
        }
        return arr[index];
    }

    protected static void setTo(char[] arr, int index, char value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static int getFromArray(int[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return 0;
        }
        return arr[index];
    }

    protected static void setTo(int[] arr, int index, int value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static long getFromArray(long[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return 0;
        }
        return arr[index];
    }

    protected static void setTo(long[] arr, int index, long value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static float getFromArray(float[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return 0.0f;
        }
        return arr[index];
    }

    protected static void setTo(float[] arr, int index, float value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static double getFromArray(double[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return 0.0d;
        }
        return arr[index];
    }

    protected static void setTo(double[] arr, int index, double value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static <T> T getFromList(List<T> list, int index) {
        if (list == null || index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    protected static <T> void setTo(List<T> list, int index, T value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.set(index, value);
        }
    }

    protected static <T> T getFromList(SparseArray<T> list, int index) {
        if (list == null || index < 0) {
            return null;
        }
        return list.get(index);
    }

    protected static <T> void setTo(SparseArray<T> list, int index, T value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.put(index, value);
        }
    }

    @TargetApi(16)
    protected static <T> T getFromList(LongSparseArray<T> list, int index) {
        if (list == null || index < 0) {
            return null;
        }
        return list.get((long) index);
    }

    @TargetApi(16)
    protected static <T> void setTo(LongSparseArray<T> list, int index, T value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.put((long) index, value);
        }
    }

    protected static <T> T getFromList(android.support.v4.util.LongSparseArray<T> list, int index) {
        if (list == null || index < 0) {
            return null;
        }
        return list.get((long) index);
    }

    protected static <T> void setTo(android.support.v4.util.LongSparseArray<T> list, int index, T value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.put((long) index, value);
        }
    }

    protected static boolean getFromList(SparseBooleanArray list, int index) {
        if (list == null || index < 0) {
            return false;
        }
        return list.get(index);
    }

    protected static void setTo(SparseBooleanArray list, int index, boolean value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.put(index, value);
        }
    }

    protected static int getFromList(SparseIntArray list, int index) {
        if (list == null || index < 0) {
            return 0;
        }
        return list.get(index);
    }

    protected static void setTo(SparseIntArray list, int index, int value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.put(index, value);
        }
    }

    @TargetApi(18)
    protected static long getFromList(SparseLongArray list, int index) {
        if (list == null || index < 0) {
            return 0;
        }
        return list.get(index);
    }

    @TargetApi(18)
    protected static void setTo(SparseLongArray list, int index, long value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.put(index, value);
        }
    }

    protected static <K, T> T getFrom(Map<K, T> map, K key) {
        if (map == null) {
            return null;
        }
        return map.get(key);
    }

    protected static <K, T> void setTo(Map<K, T> map, K key, T value) {
        if (map != null) {
            map.put(key, value);
        }
    }

    protected static void setBindingInverseListener(ViewDataBinding binder, InverseBindingListener oldListener, PropertyChangedInverseListener listener) {
        if (oldListener != listener) {
            if (oldListener != null) {
                binder.removeOnPropertyChangedCallback((PropertyChangedInverseListener) oldListener);
            }
            if (listener != null) {
                binder.addOnPropertyChangedCallback(listener);
            }
        }
    }

    protected static int safeUnbox(Integer boxed) {
        return boxed == null ? 0 : boxed.intValue();
    }

    protected static long safeUnbox(Long boxed) {
        return boxed == null ? 0 : boxed.longValue();
    }

    protected static short safeUnbox(Short boxed) {
        return boxed == null ? (short) 0 : boxed.shortValue();
    }

    protected static byte safeUnbox(Byte boxed) {
        return boxed == null ? (byte) 0 : boxed.byteValue();
    }

    protected static char safeUnbox(Character boxed) {
        return boxed == null ? '\u0000' : boxed.charValue();
    }

    protected static double safeUnbox(Double boxed) {
        return boxed == null ? 0.0d : boxed.doubleValue();
    }

    protected static float safeUnbox(Float boxed) {
        return boxed == null ? 0.0f : boxed.floatValue();
    }

    protected static boolean safeUnbox(Boolean boxed) {
        return boxed == null ? false : boxed.booleanValue();
    }

    protected void setContainedBinding(ViewDataBinding included) {
        if (included != null) {
            included.mContainingBinding = this;
        }
    }

    protected static Object[] mapBindings(DataBindingComponent bindingComponent, View[] roots, int numBindings, IncludedLayouts includes, SparseIntArray viewsWithIds) {
        Object[] bindings = new Object[numBindings];
        for (View mapBindings : roots) {
            mapBindings(bindingComponent, mapBindings, bindings, includes, viewsWithIds, true);
        }
        return bindings;
    }

    private static void mapBindings(DataBindingComponent bindingComponent, View view, Object[] bindings, IncludedLayouts includes, SparseIntArray viewsWithIds, boolean isRoot) {
        if (getBinding(view) == null) {
            int index;
            int indexInIncludes;
            int id;
            ViewGroup viewGroup;
            int count;
            int minInclude;
            int i;
            View child;
            boolean isInclude;
            String childTag;
            int includeIndex;
            int layoutId;
            int lastMatchingIndex;
            int includeCount;
            View[] included;
            int j;
            Object objTag = view.getTag();
            String tag = objTag instanceof String ? (String) objTag : null;
            boolean isBound = false;
            if (isRoot && tag != null) {
                if (tag.startsWith("layout")) {
                    int underscoreIndex = tag.lastIndexOf(95);
                    if (underscoreIndex > 0) {
                        if (isNumeric(tag, underscoreIndex + 1)) {
                            index = parseTagInt(tag, underscoreIndex + 1);
                            if (bindings[index] == null) {
                                bindings[index] = view;
                            }
                            if (includes == null) {
                                indexInIncludes = -1;
                            } else {
                                indexInIncludes = index;
                            }
                            isBound = true;
                            if (!isBound) {
                                id = view.getId();
                                if (id > 0 && viewsWithIds != null) {
                                    index = viewsWithIds.get(id, -1);
                                    if (index >= 0 && bindings[index] == null) {
                                        bindings[index] = view;
                                    }
                                }
                            }
                            if (!(view instanceof ViewGroup)) {
                                viewGroup = (ViewGroup) view;
                                count = viewGroup.getChildCount();
                                minInclude = 0;
                                i = 0;
                                while (i < count) {
                                    child = viewGroup.getChildAt(i);
                                    isInclude = false;
                                    if (indexInIncludes >= 0 && (child.getTag() instanceof String)) {
                                        childTag = (String) child.getTag();
                                        if (childTag.endsWith("_0") && childTag.startsWith("layout") && childTag.indexOf(47) > 0) {
                                            includeIndex = findIncludeIndex(childTag, minInclude, includes, indexInIncludes);
                                            if (includeIndex >= 0) {
                                                isInclude = true;
                                                minInclude = includeIndex + 1;
                                                index = includes.indexes[indexInIncludes][includeIndex];
                                                layoutId = includes.layoutIds[indexInIncludes][includeIndex];
                                                lastMatchingIndex = findLastMatching(viewGroup, i);
                                                if (lastMatchingIndex != i) {
                                                    bindings[index] = DataBindingUtil.bind(bindingComponent, child, layoutId);
                                                } else {
                                                    includeCount = (lastMatchingIndex - i) + 1;
                                                    included = new View[includeCount];
                                                    for (j = 0; j < includeCount; j++) {
                                                        included[j] = viewGroup.getChildAt(i + j);
                                                    }
                                                    bindings[index] = DataBindingUtil.bind(bindingComponent, included, layoutId);
                                                    i += includeCount - 1;
                                                }
                                            }
                                        }
                                    }
                                    if (isInclude) {
                                        mapBindings(bindingComponent, child, bindings, includes, viewsWithIds, false);
                                    }
                                    i++;
                                }
                            }
                        }
                    }
                    indexInIncludes = -1;
                    if (isBound) {
                        id = view.getId();
                        index = viewsWithIds.get(id, -1);
                        bindings[index] = view;
                    }
                    if (!(view instanceof ViewGroup)) {
                        viewGroup = (ViewGroup) view;
                        count = viewGroup.getChildCount();
                        minInclude = 0;
                        i = 0;
                        while (i < count) {
                            child = viewGroup.getChildAt(i);
                            isInclude = false;
                            childTag = (String) child.getTag();
                            includeIndex = findIncludeIndex(childTag, minInclude, includes, indexInIncludes);
                            if (includeIndex >= 0) {
                                isInclude = true;
                                minInclude = includeIndex + 1;
                                index = includes.indexes[indexInIncludes][includeIndex];
                                layoutId = includes.layoutIds[indexInIncludes][includeIndex];
                                lastMatchingIndex = findLastMatching(viewGroup, i);
                                if (lastMatchingIndex != i) {
                                    includeCount = (lastMatchingIndex - i) + 1;
                                    included = new View[includeCount];
                                    for (j = 0; j < includeCount; j++) {
                                        included[j] = viewGroup.getChildAt(i + j);
                                    }
                                    bindings[index] = DataBindingUtil.bind(bindingComponent, included, layoutId);
                                    i += includeCount - 1;
                                } else {
                                    bindings[index] = DataBindingUtil.bind(bindingComponent, child, layoutId);
                                }
                            }
                            if (isInclude) {
                                mapBindings(bindingComponent, child, bindings, includes, viewsWithIds, false);
                            }
                            i++;
                        }
                    }
                }
            }
            if (tag != null) {
                if (tag.startsWith(BINDING_TAG_PREFIX)) {
                    int tagIndex = parseTagInt(tag, BINDING_NUMBER_START);
                    if (bindings[tagIndex] == null) {
                        bindings[tagIndex] = view;
                    }
                    isBound = true;
                    if (includes == null) {
                        indexInIncludes = -1;
                    } else {
                        indexInIncludes = tagIndex;
                    }
                    if (isBound) {
                        id = view.getId();
                        index = viewsWithIds.get(id, -1);
                        bindings[index] = view;
                    }
                    if (!(view instanceof ViewGroup)) {
                        viewGroup = (ViewGroup) view;
                        count = viewGroup.getChildCount();
                        minInclude = 0;
                        i = 0;
                        while (i < count) {
                            child = viewGroup.getChildAt(i);
                            isInclude = false;
                            childTag = (String) child.getTag();
                            includeIndex = findIncludeIndex(childTag, minInclude, includes, indexInIncludes);
                            if (includeIndex >= 0) {
                                isInclude = true;
                                minInclude = includeIndex + 1;
                                index = includes.indexes[indexInIncludes][includeIndex];
                                layoutId = includes.layoutIds[indexInIncludes][includeIndex];
                                lastMatchingIndex = findLastMatching(viewGroup, i);
                                if (lastMatchingIndex != i) {
                                    bindings[index] = DataBindingUtil.bind(bindingComponent, child, layoutId);
                                } else {
                                    includeCount = (lastMatchingIndex - i) + 1;
                                    included = new View[includeCount];
                                    for (j = 0; j < includeCount; j++) {
                                        included[j] = viewGroup.getChildAt(i + j);
                                    }
                                    bindings[index] = DataBindingUtil.bind(bindingComponent, included, layoutId);
                                    i += includeCount - 1;
                                }
                            }
                            if (isInclude) {
                                mapBindings(bindingComponent, child, bindings, includes, viewsWithIds, false);
                            }
                            i++;
                        }
                    }
                }
            }
            indexInIncludes = -1;
            if (isBound) {
                id = view.getId();
                index = viewsWithIds.get(id, -1);
                bindings[index] = view;
            }
            if (!(view instanceof ViewGroup)) {
                viewGroup = (ViewGroup) view;
                count = viewGroup.getChildCount();
                minInclude = 0;
                i = 0;
                while (i < count) {
                    child = viewGroup.getChildAt(i);
                    isInclude = false;
                    childTag = (String) child.getTag();
                    includeIndex = findIncludeIndex(childTag, minInclude, includes, indexInIncludes);
                    if (includeIndex >= 0) {
                        isInclude = true;
                        minInclude = includeIndex + 1;
                        index = includes.indexes[indexInIncludes][includeIndex];
                        layoutId = includes.layoutIds[indexInIncludes][includeIndex];
                        lastMatchingIndex = findLastMatching(viewGroup, i);
                        if (lastMatchingIndex != i) {
                            includeCount = (lastMatchingIndex - i) + 1;
                            included = new View[includeCount];
                            for (j = 0; j < includeCount; j++) {
                                included[j] = viewGroup.getChildAt(i + j);
                            }
                            bindings[index] = DataBindingUtil.bind(bindingComponent, included, layoutId);
                            i += includeCount - 1;
                        } else {
                            bindings[index] = DataBindingUtil.bind(bindingComponent, child, layoutId);
                        }
                    }
                    if (isInclude) {
                        mapBindings(bindingComponent, child, bindings, includes, viewsWithIds, false);
                    }
                    i++;
                }
            }
        }
    }

    private static int findIncludeIndex(String tag, int minInclude, IncludedLayouts included, int includedIndex) {
        CharSequence layoutName = tag.subSequence(tag.indexOf(47) + 1, tag.length() - 2);
        String[] layouts = included.layouts[includedIndex];
        int length = layouts.length;
        for (int i = minInclude; i < length; i++) {
            if (TextUtils.equals(layoutName, layouts[i])) {
                return i;
            }
        }
        return -1;
    }

    private static int findLastMatching(ViewGroup viewGroup, int firstIncludedIndex) {
        String firstViewTag = (String) viewGroup.getChildAt(firstIncludedIndex).getTag();
        String tagBase = firstViewTag.substring(0, firstViewTag.length() - 1);
        int tagSequenceIndex = tagBase.length();
        int count = viewGroup.getChildCount();
        int max = firstIncludedIndex;
        for (int i = firstIncludedIndex + 1; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            String tag = view.getTag() instanceof String ? (String) view.getTag() : null;
            if (tag != null && tag.startsWith(tagBase)) {
                if (tag.length() == firstViewTag.length() && tag.charAt(tag.length() - 1) == NumericKeypadConnector.ZERO) {
                    break;
                } else if (isNumeric(tag, tagSequenceIndex)) {
                    max = i;
                }
            }
        }
        return max;
    }

    private static boolean isNumeric(String tag, int startIndex) {
        int length = tag.length();
        if (length == startIndex) {
            return false;
        }
        for (int i = startIndex; i < length; i++) {
            if (!Character.isDigit(tag.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int parseTagInt(String str, int startIndex) {
        int val = 0;
        for (int i = startIndex; i < str.length(); i++) {
            val = (val * 10) + (str.charAt(i) - 48);
        }
        return val;
    }

    private static void processReferenceQueue() {
        while (true) {
            Reference<? extends ViewDataBinding> ref = sReferenceQueue.poll();
            if (ref == null) {
                return;
            }
            if (ref instanceof WeakListener) {
                ((WeakListener) ref).unregister();
            }
        }
    }
}
