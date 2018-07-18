package retrofit2;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import retrofit2.CallAdapter.Factory;

class Platform {
    private static final Platform PLATFORM = findPlatform();

    static class Android extends Platform {

        static class MainThreadExecutor implements Executor {
            private final Handler handler = new Handler(Looper.getMainLooper());

            MainThreadExecutor() {
            }

            public void execute(Runnable r) {
                this.handler.post(r);
            }
        }

        Android() {
        }

        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        Factory defaultCallAdapterFactory(Executor callbackExecutor) {
            return new ExecutorCallAdapterFactory(callbackExecutor);
        }
    }

    static class IOS extends Platform {

        static class MainThreadExecutor implements Executor {
            private static Method addOperation;
            private static Object queue;

            MainThreadExecutor() {
            }

            static {
                try {
                    Class<?> operationQueue = Class.forName("org.robovm.apple.foundation.NSOperationQueue");
                    queue = operationQueue.getDeclaredMethod("getMainQueue", new Class[0]).invoke(null, new Object[0]);
                    addOperation = operationQueue.getDeclaredMethod("addOperation", new Class[]{Runnable.class});
                } catch (Exception e) {
                    throw new AssertionError(e);
                }
            }

            public void execute(Runnable r) {
                Exception e;
                try {
                    addOperation.invoke(queue, new Object[]{r});
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    throw new AssertionError(e);
                } catch (IllegalAccessException e3) {
                    e = e3;
                    throw new AssertionError(e);
                } catch (InvocationTargetException e4) {
                    Throwable cause = e4.getCause();
                    if (cause instanceof RuntimeException) {
                        throw ((RuntimeException) cause);
                    } else if (cause instanceof Error) {
                        throw ((Error) cause);
                    } else {
                        throw new RuntimeException(cause);
                    }
                }
            }
        }

        IOS() {
        }

        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        Factory defaultCallAdapterFactory(Executor callbackExecutor) {
            return new ExecutorCallAdapterFactory(callbackExecutor);
        }
    }

    @IgnoreJRERequirement
    static class Java8 extends Platform {
        Java8() {
        }

        boolean isDefaultMethod(Method method) {
            return method.isDefault();
        }

        Object invokeDefaultMethod(Method method, Class<?> declaringClass, Object object, Object... args) throws Throwable {
            Constructor<Lookup> constructor = Lookup.class.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
            constructor.setAccessible(true);
            return ((Lookup) constructor.newInstance(new Object[]{declaringClass, Integer.valueOf(-1)})).unreflectSpecial(method, declaringClass).bindTo(object).invokeWithArguments(args);
        }
    }

    Platform() {
    }

    static Platform get() {
        return PLATFORM;
    }

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (VERSION.SDK_INT != 0) {
                return new Android();
            }
        } catch (ClassNotFoundException e) {
        }
        try {
            Class.forName("java.util.Optional");
            return new Java8();
        } catch (ClassNotFoundException e2) {
            try {
                Class.forName("org.robovm.apple.foundation.NSObject");
                return new IOS();
            } catch (ClassNotFoundException e3) {
                return new Platform();
            }
        }
    }

    Executor defaultCallbackExecutor() {
        return null;
    }

    Factory defaultCallAdapterFactory(Executor callbackExecutor) {
        if (callbackExecutor != null) {
            return new ExecutorCallAdapterFactory(callbackExecutor);
        }
        return DefaultCallAdapterFactory.INSTANCE;
    }

    boolean isDefaultMethod(Method method) {
        return false;
    }

    Object invokeDefaultMethod(Method method, Class<?> cls, Object object, Object... args) throws Throwable {
        throw new UnsupportedOperationException();
    }
}
