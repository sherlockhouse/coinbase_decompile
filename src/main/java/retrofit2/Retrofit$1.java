package retrofit2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class Retrofit$1 implements InvocationHandler {
    private final Platform platform = Platform.get();
    final /* synthetic */ Retrofit this$0;
    final /* synthetic */ Class val$service;

    Retrofit$1(Retrofit this$0, Class cls) {
        this.this$0 = this$0;
        this.val$service = cls;
    }

    public Object invoke(Object proxy, Method method, Object... args) throws Throwable {
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this, args);
        }
        if (this.platform.isDefaultMethod(method)) {
            return this.platform.invokeDefaultMethod(method, this.val$service, proxy, args);
        }
        ServiceMethod serviceMethod = this.this$0.loadServiceMethod(method);
        return serviceMethod.callAdapter.adapt(new OkHttpCall(serviceMethod, args));
    }
}
