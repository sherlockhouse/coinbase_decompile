package com.coinbase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import com.coinbase.v1.entity.OAuthCodeRequest;
import com.coinbase.v1.entity.OAuthCodeRequest.Meta;
import com.coinbase.v1.entity.OAuthTokensResponse;
import com.coinbase.v1.exception.CoinbaseException;
import com.coinbase.v1.exception.UnauthorizedException;
import java.io.IOException;
import java.util.Random;

public class OAuth {
    private static final String KEY_COINBASE_PREFERENCES = "com.coinbase.android.sdk";
    private static final String KEY_LOGIN_CSRF_TOKEN = "com.coinbase.android.sdk.login_csrf_token";
    private final Coinbase mCoinbase;

    public OAuth(Coinbase coinbase) {
        this.mCoinbase = coinbase;
    }

    public void beginAuthorization(Context context, String clientId, String scope, String redirectUri, Meta meta) throws CoinbaseException {
        OAuthCodeRequest request = new OAuthCodeRequest();
        request.setClientId(clientId);
        request.setScope(scope);
        request.setRedirectUri(redirectUri);
        request.setMeta(meta);
        Uri authorizationUri = this.mCoinbase.getAuthorizationUri(request);
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse(authorizationUri.toString()).buildUpon().appendQueryParameter("state", getLoginCSRFToken(context)).build());
        context.startActivity(i);
    }

    public OAuthTokensResponse completeAuthorization(Context context, String clientId, String clientSecret, Uri redirectUri) throws UnauthorizedException, IOException {
        String csrfToken = redirectUri.getQueryParameter("state");
        String authCode = redirectUri.getQueryParameter("code");
        if (csrfToken == null || !csrfToken.equals(getLoginCSRFToken(context))) {
            throw new UnauthorizedException("CSRF Detected!");
        } else if (authCode == null) {
            throw new UnauthorizedException(redirectUri.getQueryParameter("error_description"));
        } else {
            try {
                return this.mCoinbase.getTokens(clientId, clientSecret, authCode, redirectUri.buildUpon().clearQuery().build().toString());
            } catch (CoinbaseException ex) {
                throw new UnauthorizedException(ex.getMessage());
            }
        }
    }

    public String getLoginCSRFToken(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(KEY_COINBASE_PREFERENCES, 0);
        int result = prefs.getInt(KEY_LOGIN_CSRF_TOKEN, 0);
        if (result == 0) {
            result = new Random().nextInt();
            Editor e = prefs.edit();
            e.putInt(KEY_LOGIN_CSRF_TOKEN, result);
            e.commit();
        }
        return Integer.toString(result);
    }
}
