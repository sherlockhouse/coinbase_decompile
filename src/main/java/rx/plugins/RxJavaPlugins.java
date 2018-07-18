package rx.plugins;

import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class RxJavaPlugins {
    static final RxJavaErrorHandler DEFAULT_ERROR_HANDLER = new 1();
    private static final RxJavaPlugins INSTANCE = new RxJavaPlugins();
    private final AtomicReference<RxJavaCompletableExecutionHook> completableExecutionHook = new AtomicReference();
    private final AtomicReference<RxJavaErrorHandler> errorHandler = new AtomicReference();
    private final AtomicReference<RxJavaObservableExecutionHook> observableExecutionHook = new AtomicReference();
    private final AtomicReference<RxJavaSchedulersHook> schedulersHook = new AtomicReference();
    private final AtomicReference<RxJavaSingleExecutionHook> singleExecutionHook = new AtomicReference();

    @Deprecated
    public static RxJavaPlugins getInstance() {
        return INSTANCE;
    }

    RxJavaPlugins() {
    }

    public RxJavaErrorHandler getErrorHandler() {
        if (this.errorHandler.get() == null) {
            Object impl = getPluginImplementationViaProperty(RxJavaErrorHandler.class, System.getProperties());
            if (impl == null) {
                this.errorHandler.compareAndSet(null, DEFAULT_ERROR_HANDLER);
            } else {
                this.errorHandler.compareAndSet(null, (RxJavaErrorHandler) impl);
            }
        }
        return (RxJavaErrorHandler) this.errorHandler.get();
    }

    public RxJavaObservableExecutionHook getObservableExecutionHook() {
        if (this.observableExecutionHook.get() == null) {
            Object impl = getPluginImplementationViaProperty(RxJavaObservableExecutionHook.class, System.getProperties());
            if (impl == null) {
                this.observableExecutionHook.compareAndSet(null, RxJavaObservableExecutionHookDefault.getInstance());
            } else {
                this.observableExecutionHook.compareAndSet(null, (RxJavaObservableExecutionHook) impl);
            }
        }
        return (RxJavaObservableExecutionHook) this.observableExecutionHook.get();
    }

    public RxJavaSingleExecutionHook getSingleExecutionHook() {
        if (this.singleExecutionHook.get() == null) {
            Object impl = getPluginImplementationViaProperty(RxJavaSingleExecutionHook.class, System.getProperties());
            if (impl == null) {
                this.singleExecutionHook.compareAndSet(null, RxJavaSingleExecutionHookDefault.getInstance());
            } else {
                this.singleExecutionHook.compareAndSet(null, (RxJavaSingleExecutionHook) impl);
            }
        }
        return (RxJavaSingleExecutionHook) this.singleExecutionHook.get();
    }

    public RxJavaCompletableExecutionHook getCompletableExecutionHook() {
        if (this.completableExecutionHook.get() == null) {
            Object impl = getPluginImplementationViaProperty(RxJavaCompletableExecutionHook.class, System.getProperties());
            if (impl == null) {
                this.completableExecutionHook.compareAndSet(null, new 2(this));
            } else {
                this.completableExecutionHook.compareAndSet(null, (RxJavaCompletableExecutionHook) impl);
            }
        }
        return (RxJavaCompletableExecutionHook) this.completableExecutionHook.get();
    }

    static Object getPluginImplementationViaProperty(Class<?> pluginClass, Properties propsIn) {
        Properties props = (Properties) propsIn.clone();
        String classSimpleName = pluginClass.getSimpleName();
        String pluginPrefix = "rxjava.plugin.";
        String implementingClass = props.getProperty(pluginPrefix + classSimpleName + ".implementation");
        if (implementingClass == null) {
            String classSuffix = ".class";
            String implSuffix = ".impl";
            for (Entry<Object, Object> e : props.entrySet()) {
                String key = e.getKey().toString();
                if (key.startsWith(pluginPrefix) && key.endsWith(classSuffix) && classSimpleName.equals(e.getValue().toString())) {
                    String implKey = pluginPrefix + key.substring(0, key.length() - classSuffix.length()).substring(pluginPrefix.length()) + implSuffix;
                    implementingClass = props.getProperty(implKey);
                    if (implementingClass == null) {
                        throw new IllegalStateException("Implementing class declaration for " + classSimpleName + " missing: " + implKey);
                    }
                }
            }
        }
        if (implementingClass == null) {
            return null;
        }
        try {
            return Class.forName(implementingClass).asSubclass(pluginClass).newInstance();
        } catch (ClassCastException e2) {
            throw new IllegalStateException(classSimpleName + " implementation is not an instance of " + classSimpleName + ": " + implementingClass, e2);
        } catch (ClassNotFoundException e3) {
            throw new IllegalStateException(classSimpleName + " implementation class not found: " + implementingClass, e3);
        } catch (InstantiationException e4) {
            throw new IllegalStateException(classSimpleName + " implementation not able to be instantiated: " + implementingClass, e4);
        } catch (IllegalAccessException e5) {
            throw new IllegalStateException(classSimpleName + " implementation not able to be accessed: " + implementingClass, e5);
        }
    }

    public RxJavaSchedulersHook getSchedulersHook() {
        if (this.schedulersHook.get() == null) {
            Object impl = getPluginImplementationViaProperty(RxJavaSchedulersHook.class, System.getProperties());
            if (impl == null) {
                this.schedulersHook.compareAndSet(null, RxJavaSchedulersHook.getDefaultInstance());
            } else {
                this.schedulersHook.compareAndSet(null, (RxJavaSchedulersHook) impl);
            }
        }
        return (RxJavaSchedulersHook) this.schedulersHook.get();
    }
}
