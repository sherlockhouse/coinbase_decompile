package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.BuiltInConverters.ToStringConverter;
import retrofit2.CallAdapter.Factory;
import retrofit2.ServiceMethod.Builder;

public final class Retrofit {
    private final List<Factory> adapterFactories;
    private final HttpUrl baseUrl;
    private final Call.Factory callFactory;
    private final Executor callbackExecutor;
    private final List<Converter.Factory> converterFactories;
    private final Map<Method, ServiceMethod> serviceMethodCache = new LinkedHashMap();
    private final boolean validateEagerly;

    Retrofit(Call.Factory callFactory, HttpUrl baseUrl, List<Converter.Factory> converterFactories, List<Factory> adapterFactories, Executor callbackExecutor, boolean validateEagerly) {
        this.callFactory = callFactory;
        this.baseUrl = baseUrl;
        this.converterFactories = Collections.unmodifiableList(converterFactories);
        this.adapterFactories = Collections.unmodifiableList(adapterFactories);
        this.callbackExecutor = callbackExecutor;
        this.validateEagerly = validateEagerly;
    }

    public <T> T create(Class<T> service) {
        Utils.validateServiceInterface(service);
        if (this.validateEagerly) {
            eagerlyValidateMethods(service);
        }
        return Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new 1(this, service));
    }

    private void eagerlyValidateMethods(Class<?> service) {
        Platform platform = Platform.get();
        for (Method method : service.getDeclaredMethods()) {
            if (!platform.isDefaultMethod(method)) {
                loadServiceMethod(method);
            }
        }
    }

    ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod result;
        synchronized (this.serviceMethodCache) {
            result = (ServiceMethod) this.serviceMethodCache.get(method);
            if (result == null) {
                result = new Builder(this, method).build();
                this.serviceMethodCache.put(method, result);
            }
        }
        return result;
    }

    public Call.Factory callFactory() {
        return this.callFactory;
    }

    public HttpUrl baseUrl() {
        return this.baseUrl;
    }

    public CallAdapter<?> callAdapter(Type returnType, Annotation[] annotations) {
        return nextCallAdapter(null, returnType, annotations);
    }

    public CallAdapter<?> nextCallAdapter(Factory skipPast, Type returnType, Annotation[] annotations) {
        int i;
        Utils.checkNotNull(returnType, "returnType == null");
        Utils.checkNotNull(annotations, "annotations == null");
        int start = this.adapterFactories.indexOf(skipPast) + 1;
        int count = this.adapterFactories.size();
        for (i = start; i < count; i++) {
            CallAdapter<?> adapter = ((Factory) this.adapterFactories.get(i)).get(returnType, annotations, this);
            if (adapter != null) {
                return adapter;
            }
        }
        StringBuilder builder = new StringBuilder("Could not locate call adapter for ").append(returnType).append(".\n");
        if (skipPast != null) {
            builder.append("  Skipped:");
            for (i = 0; i < start; i++) {
                builder.append("\n   * ").append(((Factory) this.adapterFactories.get(i)).getClass().getName());
            }
            builder.append('\n');
        }
        builder.append("  Tried:");
        count = this.adapterFactories.size();
        for (i = start; i < count; i++) {
            builder.append("\n   * ").append(((Factory) this.adapterFactories.get(i)).getClass().getName());
        }
        throw new IllegalArgumentException(builder.toString());
    }

    public <T> Converter<T, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations) {
        return nextRequestBodyConverter(null, type, parameterAnnotations, methodAnnotations);
    }

    public <T> Converter<T, RequestBody> nextRequestBodyConverter(Converter.Factory skipPast, Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations) {
        int i;
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(parameterAnnotations, "parameterAnnotations == null");
        Utils.checkNotNull(methodAnnotations, "methodAnnotations == null");
        int start = this.converterFactories.indexOf(skipPast) + 1;
        int count = this.converterFactories.size();
        for (i = start; i < count; i++) {
            Converter<?, RequestBody> converter = ((Converter.Factory) this.converterFactories.get(i)).requestBodyConverter(type, parameterAnnotations, methodAnnotations, this);
            if (converter != null) {
                return converter;
            }
        }
        StringBuilder builder = new StringBuilder("Could not locate RequestBody converter for ").append(type).append(".\n");
        if (skipPast != null) {
            builder.append("  Skipped:");
            for (i = 0; i < start; i++) {
                builder.append("\n   * ").append(((Converter.Factory) this.converterFactories.get(i)).getClass().getName());
            }
            builder.append('\n');
        }
        builder.append("  Tried:");
        count = this.converterFactories.size();
        for (i = start; i < count; i++) {
            builder.append("\n   * ").append(((Converter.Factory) this.converterFactories.get(i)).getClass().getName());
        }
        throw new IllegalArgumentException(builder.toString());
    }

    public <T> Converter<ResponseBody, T> responseBodyConverter(Type type, Annotation[] annotations) {
        return nextResponseBodyConverter(null, type, annotations);
    }

    public <T> Converter<ResponseBody, T> nextResponseBodyConverter(Converter.Factory skipPast, Type type, Annotation[] annotations) {
        int i;
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotations, "annotations == null");
        int start = this.converterFactories.indexOf(skipPast) + 1;
        int count = this.converterFactories.size();
        for (i = start; i < count; i++) {
            Converter<ResponseBody, ?> converter = ((Converter.Factory) this.converterFactories.get(i)).responseBodyConverter(type, annotations, this);
            if (converter != null) {
                return converter;
            }
        }
        StringBuilder builder = new StringBuilder("Could not locate ResponseBody converter for ").append(type).append(".\n");
        if (skipPast != null) {
            builder.append("  Skipped:");
            for (i = 0; i < start; i++) {
                builder.append("\n   * ").append(((Converter.Factory) this.converterFactories.get(i)).getClass().getName());
            }
            builder.append('\n');
        }
        builder.append("  Tried:");
        count = this.converterFactories.size();
        for (i = start; i < count; i++) {
            builder.append("\n   * ").append(((Converter.Factory) this.converterFactories.get(i)).getClass().getName());
        }
        throw new IllegalArgumentException(builder.toString());
    }

    public <T> Converter<T, String> stringConverter(Type type, Annotation[] annotations) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotations, "annotations == null");
        int count = this.converterFactories.size();
        for (int i = 0; i < count; i++) {
            Converter<?, String> converter = ((Converter.Factory) this.converterFactories.get(i)).stringConverter(type, annotations, this);
            if (converter != null) {
                return converter;
            }
        }
        return ToStringConverter.INSTANCE;
    }
}
