package io.fabric.sdk.android.services.common;

import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class AbstractSpiCall {
    private static final Pattern PROTOCOL_AND_HOST_PATTERN = Pattern.compile("http(s?)://[^\\/]+", 2);
    protected final Kit kit;
    private final HttpMethod method;
    private final String protocolAndHostOverride;
    private final HttpRequestFactory requestFactory;
    private final String url;

    public AbstractSpiCall(Kit kit, String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, HttpMethod method) {
        if (url == null) {
            throw new IllegalArgumentException("url must not be null.");
        } else if (requestFactory == null) {
            throw new IllegalArgumentException("requestFactory must not be null.");
        } else {
            this.kit = kit;
            this.protocolAndHostOverride = protocolAndHostOverride;
            this.url = overrideProtocolAndHost(url);
            this.requestFactory = requestFactory;
            this.method = method;
        }
    }

    protected String getUrl() {
        return this.url;
    }

    protected HttpRequest getHttpRequest() {
        return getHttpRequest(Collections.emptyMap());
    }

    protected HttpRequest getHttpRequest(Map<String, String> queryParams) {
        return this.requestFactory.buildHttpRequest(this.method, getUrl(), queryParams).useCaches(false).connectTimeout(10000).header("User-Agent", "Crashlytics Android SDK/" + this.kit.getVersion()).header("X-CRASHLYTICS-DEVELOPER-TOKEN", "470fa2b4ae81cd56ecbcda9735803434cec591fa");
    }

    private String overrideProtocolAndHost(String url) {
        String toReturn = url;
        if (CommonUtils.isNullOrEmpty(this.protocolAndHostOverride)) {
            return toReturn;
        }
        return PROTOCOL_AND_HOST_PATTERN.matcher(url).replaceFirst(this.protocolAndHostOverride);
    }
}
