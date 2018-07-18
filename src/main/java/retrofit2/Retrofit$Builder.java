package retrofit2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter.Factory;

public final class Retrofit$Builder {
    private List<Factory> adapterFactories;
    private HttpUrl baseUrl;
    private Call.Factory callFactory;
    private Executor callbackExecutor;
    private List<Converter.Factory> converterFactories;
    private Platform platform;
    private boolean validateEagerly;

    Retrofit$Builder(Platform platform) {
        this.converterFactories = new ArrayList();
        this.adapterFactories = new ArrayList();
        this.platform = platform;
        this.converterFactories.add(new BuiltInConverters());
    }

    public Retrofit$Builder() {
        this(Platform.get());
    }

    public Retrofit$Builder client(OkHttpClient client) {
        return callFactory((Call.Factory) Utils.checkNotNull(client, "client == null"));
    }

    public Retrofit$Builder callFactory(Call.Factory factory) {
        this.callFactory = (Call.Factory) Utils.checkNotNull(factory, "factory == null");
        return this;
    }

    public Retrofit$Builder baseUrl(String baseUrl) {
        Utils.checkNotNull(baseUrl, "baseUrl == null");
        HttpUrl httpUrl = HttpUrl.parse(baseUrl);
        if (httpUrl != null) {
            return baseUrl(httpUrl);
        }
        throw new IllegalArgumentException("Illegal URL: " + baseUrl);
    }

    public Retrofit$Builder baseUrl(HttpUrl baseUrl) {
        Utils.checkNotNull(baseUrl, "baseUrl == null");
        List<String> pathSegments = baseUrl.pathSegments();
        if ("".equals(pathSegments.get(pathSegments.size() - 1))) {
            this.baseUrl = baseUrl;
            return this;
        }
        throw new IllegalArgumentException("baseUrl must end in /: " + baseUrl);
    }

    public Retrofit$Builder addConverterFactory(Converter.Factory factory) {
        this.converterFactories.add(Utils.checkNotNull(factory, "factory == null"));
        return this;
    }

    public Retrofit$Builder addCallAdapterFactory(Factory factory) {
        this.adapterFactories.add(Utils.checkNotNull(factory, "factory == null"));
        return this;
    }

    public Retrofit build() {
        if (this.baseUrl == null) {
            throw new IllegalStateException("Base URL required.");
        }
        Call.Factory callFactory = this.callFactory;
        if (callFactory == null) {
            callFactory = new OkHttpClient();
        }
        Executor callbackExecutor = this.callbackExecutor;
        if (callbackExecutor == null) {
            callbackExecutor = this.platform.defaultCallbackExecutor();
        }
        List<Factory> adapterFactories = new ArrayList(this.adapterFactories);
        adapterFactories.add(this.platform.defaultCallAdapterFactory(callbackExecutor));
        return new Retrofit(callFactory, this.baseUrl, new ArrayList(this.converterFactories), adapterFactories, callbackExecutor, this.validateEagerly);
    }
}
