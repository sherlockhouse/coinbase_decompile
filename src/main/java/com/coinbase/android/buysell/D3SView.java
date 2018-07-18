package com.coinbase.android.buysell;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.featureflag.FeatureFlags;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D3SView extends WebView {
    private static String JavaScriptNS = "D3SJS";
    private D3SViewListener authorizationListener = null;
    private boolean debugMode = false;
    private FeatureFlags mFeatureFlags;
    private boolean postbackHandled = false;
    private String postbackUrl = "https://www.google.com";
    private boolean urlReturned = false;

    class D3SJSInterface {
        D3SJSInterface() {
        }

        @JavascriptInterface
        public void processHTML(String paramString) {
            D3SView.this.completeAuthorization(paramString);
        }
    }

    static class PaResParser {
        private static Pattern pareqFinder = Pattern.compile(".*?(<input[^<>]* name=\\\"parequest\\\"[^<>]*>).*?", 32);
        private static Pattern paresFinder = Pattern.compile(".*?(<input[^<>]* name=\\\"PaRes\\\"[^<>]*>).*?", 32);
        private static Pattern valuePattern = Pattern.compile(".*? value=\\\"(.*?)\\\"", 32);

        PaResParser() {
        }

        public String parsePaRes(String html) {
            return parse(paresFinder, html);
        }

        public boolean containsPaReq(String html) {
            return !TextUtils.isEmpty(parse(pareqFinder, html));
        }

        private String parse(Pattern pattern, String html) {
            String pares = "";
            Matcher localMatcher2 = pattern.matcher(html);
            if (localMatcher2.find()) {
                pares = localMatcher2.group(1);
            }
            if (TextUtils.isEmpty(pares)) {
                return pares;
            }
            Matcher valueMatcher = valuePattern.matcher(pares);
            if (valueMatcher.find()) {
                return valueMatcher.group(1);
            }
            return pares;
        }
    }

    public D3SView(Context context) {
        super(context);
        initUI();
    }

    private void initUI() {
        getSettings().setJavaScriptEnabled(true);
        getSettings().setBuiltInZoomControls(true);
        addJavascriptInterface(new D3SJSInterface(), JavaScriptNS);
        this.mFeatureFlags = ((ComponentProvider) getContext().getApplicationContext()).applicationComponent().features();
        getSettings().setAllowFileAccess(false);
        getSettings().setAllowContentAccess(false);
        setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (D3SView.this.postbackHandled || !url.toLowerCase().contains(D3SView.this.postbackUrl.toLowerCase())) {
                    return super.shouldOverrideUrlLoading(view, url);
                }
                D3SView.this.postbackHandled = true;
                view.loadUrl(String.format("javascript:window.%s.processHTML(document.getElementsByTagName('html')[0].innerHTML);", new Object[]{D3SView.JavaScriptNS}));
                return true;
            }

            public void onPageStarted(WebView view, String url, Bitmap icon) {
                if (!D3SView.this.urlReturned && !D3SView.this.postbackHandled) {
                    if (url.toLowerCase().contains(D3SView.this.postbackUrl.toLowerCase())) {
                        D3SView.this.postbackHandled = true;
                        view.loadUrl(String.format("javascript:window.%s.processHTML(document.getElementsByTagName('html')[0].innerHTML);", new Object[]{D3SView.JavaScriptNS}));
                        D3SView.this.urlReturned = true;
                        return;
                    }
                    super.onPageStarted(view, url, icon);
                }
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                D3SView.this.authorizationListener.onAuthorizationWebPageLoadingComplete();
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                if (!failingUrl.startsWith(D3SView.this.postbackUrl) && D3SView.this.authorizationListener != null) {
                    D3SView.this.authorizationListener.onAuthorizationWebPageLoadingError(errorCode, description, failingUrl);
                }
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (D3SView.this.debugMode) {
                    handler.proceed();
                }
            }
        });
        setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {
                if (D3SView.this.authorizationListener != null) {
                    D3SView.this.authorizationListener.onAuthorizationWebPageLoadingProgressChanged(newProgress);
                }
            }
        });
    }

    public D3SView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public D3SView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initUI();
    }

    public D3SView(Context context, AttributeSet attrs, int defStyle, boolean privateBrowsing) {
        super(context, attrs, defStyle);
        initUI();
    }

    private void completeAuthorization(String html) {
        PaResParser paResParser = new PaResParser();
        if (!this.mFeatureFlags.hasFeature("tEsTdIsAbLeD") || !paResParser.containsPaReq(html)) {
            String pares = paResParser.parsePaRes(html);
            if (this.authorizationListener != null) {
                this.urlReturned = false;
                this.postbackHandled = false;
                this.authorizationListener.onAuthorizationCompleted(pares);
            }
        } else if (this.authorizationListener != null) {
            this.urlReturned = false;
            this.postbackHandled = false;
        }
    }

    public boolean isDebugMode() {
        return this.debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void setAuthorizationListener(D3SViewListener authorizationListener) {
        this.authorizationListener = authorizationListener;
    }

    public void authorize(String acsUrl, String paReq) {
        authorize(acsUrl, paReq, null);
    }

    public void authorize(String acsUrl, String paReq, String postbackUrl) {
        this.urlReturned = false;
        this.postbackHandled = false;
        if (this.authorizationListener != null) {
            this.authorizationListener.onAuthorizationStarted(this);
        }
        if (!TextUtils.isEmpty(postbackUrl)) {
            this.postbackUrl = postbackUrl;
        }
        try {
            postUrl(acsUrl, String.format(Locale.US, "TermUrl=%1$s&PaReq=%2$s", new Object[]{URLEncoder.encode(this.postbackUrl, "UTF-8"), URLEncoder.encode(paReq, "UTF-8")}).getBytes());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
