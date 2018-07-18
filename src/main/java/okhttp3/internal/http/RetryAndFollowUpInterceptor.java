package okhttp3.internal.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.StreamAllocation;

public final class RetryAndFollowUpInterceptor implements Interceptor {
    private static final int MAX_FOLLOW_UPS = 20;
    private volatile boolean canceled;
    private final OkHttpClient client;
    private boolean forWebSocket;
    private StreamAllocation streamAllocation;

    public RetryAndFollowUpInterceptor(OkHttpClient client) {
        this.client = client;
    }

    public void cancel() {
        this.canceled = true;
        StreamAllocation streamAllocation = this.streamAllocation;
        if (streamAllocation != null) {
            streamAllocation.cancel();
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public OkHttpClient client() {
        return this.client;
    }

    public void setForWebSocket(boolean forWebSocket) {
        this.forWebSocket = forWebSocket;
    }

    public boolean isForWebSocket() {
        return this.forWebSocket;
    }

    public StreamAllocation streamAllocation() {
        return this.streamAllocation;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        this.streamAllocation = new StreamAllocation(this.client.connectionPool(), createAddress(request.url()));
        int followUpCount = 0;
        Response priorResponse = null;
        while (!this.canceled) {
            try {
                Response response = ((RealInterceptorChain) chain).proceed(request, this.streamAllocation, null, null);
                if (false) {
                    this.streamAllocation.streamFailed(null);
                    this.streamAllocation.release();
                }
                if (priorResponse != null) {
                    response = response.newBuilder().priorResponse(priorResponse.newBuilder().body(null).build()).build();
                }
                Request followUp = followUpRequest(response);
                if (followUp == null) {
                    if (!this.forWebSocket) {
                        this.streamAllocation.release();
                    }
                    return response;
                }
                Util.closeQuietly(response.body());
                followUpCount++;
                if (followUpCount > 20) {
                    this.streamAllocation.release();
                    throw new ProtocolException("Too many follow-up requests: " + followUpCount);
                } else if (followUp.body() instanceof UnrepeatableRequestBody) {
                    throw new HttpRetryException("Cannot retry streamed HTTP body", response.code());
                } else {
                    if (!sameConnection(response, followUp.url())) {
                        this.streamAllocation.release();
                        this.streamAllocation = new StreamAllocation(this.client.connectionPool(), createAddress(followUp.url()));
                    } else if (this.streamAllocation.stream() != null) {
                        throw new IllegalStateException("Closing the body of " + response + " didn't close its backing stream. Bad interceptor?");
                    }
                    request = followUp;
                    priorResponse = response;
                }
            } catch (RouteException e) {
                if (!recover(e.getLastConnectException(), true, request)) {
                    throw e.getLastConnectException();
                } else if (false) {
                    this.streamAllocation.streamFailed(null);
                    this.streamAllocation.release();
                }
            } catch (IOException e2) {
                if (!recover(e2, false, request)) {
                    throw e2;
                } else if (false) {
                    this.streamAllocation.streamFailed(null);
                    this.streamAllocation.release();
                }
            } catch (Throwable th) {
                if (true) {
                    this.streamAllocation.streamFailed(null);
                    this.streamAllocation.release();
                }
            }
        }
        this.streamAllocation.release();
        throw new IOException("Canceled");
    }

    private Address createAddress(HttpUrl url) {
        SSLSocketFactory sslSocketFactory = null;
        HostnameVerifier hostnameVerifier = null;
        CertificatePinner certificatePinner = null;
        if (url.isHttps()) {
            sslSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            certificatePinner = this.client.certificatePinner();
        }
        return new Address(url.host(), url.port(), this.client.dns(), this.client.socketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }

    private boolean recover(IOException e, boolean routeException, Request userRequest) {
        this.streamAllocation.streamFailed(e);
        if (!this.client.retryOnConnectionFailure()) {
            return false;
        }
        if ((routeException || !(userRequest.body() instanceof UnrepeatableRequestBody)) && isRecoverable(e, routeException) && this.streamAllocation.hasMoreRoutes()) {
            return true;
        }
        return false;
    }

    private boolean isRecoverable(IOException e, boolean routeException) {
        boolean z = true;
        if (e instanceof ProtocolException) {
            return false;
        }
        if (e instanceof InterruptedIOException) {
            if (!((e instanceof SocketTimeoutException) && routeException)) {
                z = false;
            }
            return z;
        } else if (((e instanceof SSLHandshakeException) && (e.getCause() instanceof CertificateException)) || (e instanceof SSLPeerUnverifiedException)) {
            return false;
        } else {
            return true;
        }
    }

    private Request followUpRequest(Response userResponse) throws IOException {
        if (userResponse == null) {
            throw new IllegalStateException();
        }
        Route route;
        Connection connection = this.streamAllocation.connection();
        if (connection != null) {
            route = connection.route();
        } else {
            route = null;
        }
        int responseCode = userResponse.code();
        String method = userResponse.request().method();
        switch (responseCode) {
            case 300:
            case 301:
            case 302:
            case 303:
                break;
            case StatusLine.HTTP_TEMP_REDIRECT /*307*/:
            case StatusLine.HTTP_PERM_REDIRECT /*308*/:
                if (!(method.equals("GET") || method.equals("HEAD"))) {
                    return null;
                }
            case 401:
                return this.client.authenticator().authenticate(route, userResponse);
            case 407:
                Proxy selectedProxy;
                if (route != null) {
                    selectedProxy = route.proxy();
                } else {
                    selectedProxy = this.client.proxy();
                }
                if (selectedProxy.type() == Type.HTTP) {
                    return this.client.proxyAuthenticator().authenticate(route, userResponse);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            case 408:
                if (userResponse.request().body() instanceof UnrepeatableRequestBody) {
                    return null;
                }
                return userResponse.request();
            default:
                return null;
        }
        if (!this.client.followRedirects()) {
            return null;
        }
        String location = userResponse.header("Location");
        if (location == null) {
            return null;
        }
        HttpUrl url = userResponse.request().url().resolve(location);
        if (url == null) {
            return null;
        }
        if (!url.scheme().equals(userResponse.request().url().scheme()) && !this.client.followSslRedirects()) {
            return null;
        }
        Builder requestBuilder = userResponse.request().newBuilder();
        if (HttpMethod.permitsRequestBody(method)) {
            if (HttpMethod.redirectsToGet(method)) {
                requestBuilder.method("GET", null);
            } else {
                requestBuilder.method(method, null);
            }
            requestBuilder.removeHeader("Transfer-Encoding");
            requestBuilder.removeHeader("Content-Length");
            requestBuilder.removeHeader("Content-Type");
        }
        if (!sameConnection(userResponse, url)) {
            requestBuilder.removeHeader("Authorization");
        }
        return requestBuilder.url(url).build();
    }

    private boolean sameConnection(Response response, HttpUrl followUp) {
        HttpUrl url = response.request().url();
        return url.host().equals(followUp.host()) && url.port() == followUp.port() && url.scheme().equals(followUp.scheme());
    }
}
