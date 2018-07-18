package com.coinbase.android.paymentmethods;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Pair;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.coinbase.ApiConstants;
import com.coinbase.android.BuildConfig;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.StatusBarUpdater;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class PlaidPresenter {
    private static final String JS_SET_STATUS_BAR_COLOR = "js_set_status_bar_color";
    private final BankAccountsUpdatedConnector mBankAccountsUpdatedConnector;
    private final Logger mLogger = LoggerFactory.getLogger(PlaidPresenter.class);
    private final LoginManager mLoginManager;
    private final PlaidOnExitConnector mPlaidOnExitConnector;
    private final Scheduler mScheduler;
    private final PlaidScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final StatusBarUpdater mStatusBarUpdater;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final SuccessRouter mSuccessRouter;
    private int mThemeColor;

    private class WebviewJSInterface {
        private WebviewJSInterface() {
        }

        @JavascriptInterface
        public void processHTML(String output) {
            Observable.create(PlaidPresenter$WebviewJSInterface$$Lambda$1.lambdaFactory$(this, output)).observeOn(PlaidPresenter.this.mScheduler).first().onBackpressureLatest().subscribe(PlaidPresenter$WebviewJSInterface$$Lambda$2.lambdaFactory$(this), PlaidPresenter$WebviewJSInterface$$Lambda$3.lambdaFactory$(this));
        }

        static /* synthetic */ void lambda$processHTML$0(WebviewJSInterface this_, String output, Subscriber subscriber) {
            if (TextUtils.isEmpty(output)) {
                subscriber.onNext(null);
                subscriber.onCompleted();
            } else if (TextUtils.equals(output, "white")) {
                subscriber.onNext(Integer.valueOf(-1));
                subscriber.onCompleted();
            } else {
                subscriber.onNext(Integer.valueOf(PlaidPresenter.this.parse(output)));
                subscriber.onCompleted();
            }
        }

        static /* synthetic */ void lambda$processHTML$1(WebviewJSInterface this_, Object color) {
            if (color != null) {
                int colorInt = ((Integer) color).intValue();
                if (colorInt != 0 && PlaidPresenter.this.mThemeColor != colorInt) {
                    PlaidPresenter.this.mThemeColor = colorInt;
                    PlaidPresenter.this.mStatusBarUpdater.setStatusBarColorFromBackgroundColor(colorInt);
                }
            }
        }
    }

    @Inject
    public PlaidPresenter(PlaidScreen screen, SuccessRouter successRouter, BankAccountsUpdatedConnector bankAccountsUpdatedConnector, @MainScheduler Scheduler scheduler, LoginManager loginManager, SnackBarWrapper snackBarWrapper, StatusBarUpdater statusBarUpdater, Application app, PlaidOnExitConnector plaidOnExitConnector) {
        this.mScreen = screen;
        this.mSuccessRouter = successRouter;
        this.mBankAccountsUpdatedConnector = bankAccountsUpdatedConnector;
        this.mScheduler = scheduler;
        this.mLoginManager = loginManager;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mStatusBarUpdater = statusBarUpdater;
        this.mPlaidOnExitConnector = plaidOnExitConnector;
        this.mThemeColor = ContextCompat.getColor(app, R.color.white);
        prepareWebView(this.mScreen.getWebView());
    }

    int getThemeColor() {
        return this.mThemeColor;
    }

    private void prepareWebView(final WebView plaidLinkWebview) {
        if (plaidLinkWebview == null) {
            this.mScreen.closeForm();
            return;
        }
        HashMap<String, String> linkInitializeOptions = new HashMap();
        linkInitializeOptions.put("key", "c52f3de17944312356997882b0c8de");
        linkInitializeOptions.put("product", "auth");
        linkInitializeOptions.put("apiVersion", ApiConstants.SERVER_VERSION);
        linkInitializeOptions.put("env", BuildConfig.FLAVOR);
        linkInitializeOptions.put("clientName", "Coinbase");
        linkInitializeOptions.put("selectAccount", "true");
        linkInitializeOptions.put("webhook", "http://requestb.in");
        linkInitializeOptions.put("baseUrl", "https://cdn.plaid.com/link/v2/stable/link.html");
        final Uri linkInitializationUrl = generateLinkInitializationUrl(linkInitializeOptions);
        if (linkInitializationUrl == null) {
            this.mScreen.closeForm();
            return;
        }
        WebSettings webSettings = plaidLinkWebview.getSettings();
        webSettings.setAllowFileAccess(false);
        webSettings.setAllowContentAccess(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(2);
        plaidLinkWebview.addJavascriptInterface(new WebviewJSInterface(), JS_SET_STATUS_BAR_COLOR);
        plaidLinkWebview.loadUrl(linkInitializationUrl.toString());
        plaidLinkWebview.setWebViewClient(new WebViewClient() {
            private void loadColorFromWebView() {
                plaidLinkWebview.loadUrl("javascript:window. js_set_status_bar_color.processHTML(document.getElementsByTagName('body')[0].style.backgroundColor);");
            }

            public void onLoadResource(WebView view, String url) {
                loadColorFromWebView();
                super.onLoadResource(view, url);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri parsedUri = Uri.parse(url);
                if (parsedUri != null && parsedUri.getScheme() != null && parsedUri.getScheme().equals("plaidlink")) {
                    String action = parsedUri.getHost();
                    Map linkData = PlaidPresenter.this.parseLinkUriData(parsedUri);
                    if (action == null || linkData == null) {
                        return true;
                    }
                    String eventName = (String) linkData.get("event_name");
                    if (eventName != null && linkData.get("public_token") == null && linkData.get("account_id") == null) {
                        MixpanelTracking.getInstance().trackEvent("plaid_link_" + eventName, linkData);
                    }
                    if (action.equals("connected")) {
                        String publicToken = (String) linkData.get("public_token");
                        String accountId = (String) linkData.get("account_id");
                        if (publicToken == null || accountId == null) {
                            PlaidPresenter.this.mSnackBarWrapper.show((int) R.string.error_occurred_try_again);
                            plaidLinkWebview.loadUrl(linkInitializationUrl.toString());
                            return true;
                        }
                        PlaidPresenter.this.createPlaidPaymentMethod(publicToken, accountId);
                        return true;
                    } else if (action == null || !action.equals("exit")) {
                        return true;
                    } else {
                        PlaidPresenter.this.mScreen.closeForm();
                        if (linkData == null || linkData.get("error_type") == null) {
                            return true;
                        }
                        PlaidPresenter.this.mPlaidOnExitConnector.get().onNext(null);
                        return true;
                    }
                } else if (parsedUri == null || parsedUri.getScheme() == null || !parsedUri.getScheme().equals("https")) {
                    return false;
                } else {
                    view.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    return true;
                }
            }
        });
    }

    private void gotoSettings() {
        if (this.mSuccessRouter.shouldRouteSuccess()) {
            this.mSuccessRouter.routeSuccess();
        }
    }

    private void createPlaidPaymentMethod(String publicToken, String accountId) {
        this.mScreen.showProgressDialog();
        this.mSubscription.add(this.mLoginManager.getClient().createPlaidPaymentMethodRx(publicToken, accountId).first().observeOn(this.mScheduler).subscribe(PlaidPresenter$$Lambda$1.lambdaFactory$(this), PlaidPresenter$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$createPlaidPaymentMethod$0(PlaidPresenter this_, Pair pair) {
        Response<Void> response = pair.first;
        Retrofit retrofit = pair.second;
        this_.mScreen.hideProgressDialog();
        if (response.isSuccessful()) {
            this_.mSnackBarWrapper.show((int) R.string.account_successfully_added);
            this_.gotoSettings();
            this_.mBankAccountsUpdatedConnector.get().onNext(new ClassConsumableEvent());
            return;
        }
        this_.mSnackBarWrapper.showError(response, retrofit);
        this_.mScreen.closeModal();
    }

    static /* synthetic */ void lambda$createPlaidPaymentMethod$1(PlaidPresenter this_, Throwable t) {
        this_.mScreen.hideProgressDialog();
        this_.mSnackBarWrapper.showFailure(t);
        this_.mScreen.closeModal();
    }

    private Uri generateLinkInitializationUrl(HashMap<String, String> linkOptions) {
        if (linkOptions == null) {
            return null;
        }
        Builder builder = Uri.parse((String) linkOptions.get("baseUrl")).buildUpon().appendQueryParameter("isWebview", "true").appendQueryParameter("isMobile", "true");
        for (String key : linkOptions.keySet()) {
            if (!key.equals("baseUrl")) {
                builder.appendQueryParameter(key, (String) linkOptions.get(key));
            }
        }
        return builder.build();
    }

    private HashMap<String, String> parseLinkUriData(Uri linkUri) {
        if (linkUri == null) {
            return null;
        }
        HashMap<String, String> linkData = new HashMap();
        for (String key : linkUri.getQueryParameterNames()) {
            linkData.put(key, linkUri.getQueryParameter(key));
        }
        return linkData;
    }

    private int parse(String input) {
        try {
            Matcher m = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)").matcher(input);
            if (m.matches()) {
                int r = Integer.valueOf(m.group(1)).intValue();
                return ((((65280 + r) << 8) + Integer.valueOf(m.group(2)).intValue()) << 8) + Integer.valueOf(m.group(3)).intValue();
            }
        } catch (PatternSyntaxException e) {
            this.mLogger.error("Couldn't parse color [" + input + "]", e);
        }
        return 0;
    }
}
