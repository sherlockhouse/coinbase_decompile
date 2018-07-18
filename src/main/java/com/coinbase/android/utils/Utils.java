package com.coinbase.android.utils;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.preference.PreferenceManager;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.util.Pair;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.coinbase.ObjectMapperProvider;
import com.coinbase.android.Constants;
import com.coinbase.android.Log;
import com.coinbase.android.R;
import com.coinbase.android.identityverification.IdentityVerificationActivity;
import com.coinbase.android.task.GetUserTask.AdminFlags;
import com.coinbase.android.task.GetUserTask.Restriction;
import com.coinbase.android.ui.AvatarDrawable;
import com.coinbase.android.utils.MoneyUtils.Currency;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.auth.Error;
import com.coinbase.api.internal.models.auth.Error.Builder;
import com.coinbase.api.internal.models.phoneNumber.Data;
import com.coinbase.v2.models.errors.ErrorBody;
import com.coinbase.v2.models.errors.Errors;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.squareup.picasso.Picasso;
import hotchemi.android.rate.AppRate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import okhttp3.ResponseBody;
import org.joda.money.CurrencyUnit;
import org.joda.money.IllegalCurrencyException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.functions.Func1;

public class Utils {
    private static final int BACKGROUND_VIEW_COLOR_ALPHA = 190;
    public static final int GRAVATAR_RADIUS = 60;
    private static final Logger sLogger = LoggerFactory.getLogger(Utils.class);

    public static class URLSpanNoUnderline extends URLSpan {
        public static Creator<URLSpanNoUnderline> CREATOR = new Creator<URLSpanNoUnderline>() {
            public URLSpanNoUnderline createFromParcel(Parcel parcel) {
                return new URLSpanNoUnderline(parcel);
            }

            public URLSpanNoUnderline[] newArray(int i) {
                return new URLSpanNoUnderline[0];
            }
        };

        private URLSpanNoUnderline(Parcel in) {
            super(in);
        }

        public URLSpanNoUnderline(String url) {
            super(url);
        }

        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }

    public static class URLSpanUnderlineWithColor extends URLSpan {
        public static Creator<URLSpanUnderlineWithColor> CREATOR = new Creator<URLSpanUnderlineWithColor>() {
            public URLSpanUnderlineWithColor createFromParcel(Parcel parcel) {
                return new URLSpanUnderlineWithColor(parcel);
            }

            public URLSpanUnderlineWithColor[] newArray(int i) {
                return new URLSpanUnderlineWithColor[0];
            }
        };
        private final int mColor;

        private URLSpanUnderlineWithColor(Parcel in) {
            super(in);
            this.mColor = in.readInt();
        }

        public URLSpanUnderlineWithColor(String url, int color) {
            super(url);
            this.mColor = color;
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.mColor);
        }

        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(this.mColor);
        }
    }

    public static Bitmap createBarcode(String contents, BarcodeFormat format, int desiredWidth, int desiredHeight) throws WriterException {
        BitMatrix result = new MultiFormatWriter().encode(contents, format, desiredWidth, desiredHeight, new Hashtable(2));
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[(width * height)];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                int i;
                int i2 = offset + x;
                if (result.get(x, y)) {
                    i = -16777216;
                } else {
                    i = 16777215;
                }
                pixels[i2] = i;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public static String getGravatarUrl(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }
        return String.format("https://secure.gravatar.com/avatar/%1$s?s=100&d=https://coinbase.com/assets/avatar.png", new Object[]{md5(email.toLowerCase(Locale.US).trim())});
    }

    public static String md5(String original) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(original.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                int unsigned = b & 255;
                if (unsigned < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(unsigned));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 does not exist", e);
        }
    }

    public static boolean isConnectedOrConnecting(Context c) {
        if (c == null) {
            return false;
        }
        NetworkInfo activeNetwork = ((ConnectivityManager) c.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }

    public static int getPixels(Context context, int dimensionDp) {
        if (context == null) {
            Log.e("Utils.getPixels", "context is null, can't calculate pixels");
            return dimensionDp;
        }
        return (int) ((((float) dimensionDp) * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static int getHeight(Context context) {
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public static int getWidth(Context context) {
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static String curry(List<String> list) {
        try {
            return ObjectMapperProvider.createDefaultMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static List<String> uncurry(String string) {
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        ObjectMapper mapper = ObjectMapperProvider.createDefaultMapper();
        try {
            return (List) mapper.readValue(string, mapper.getTypeFactory().constructType((Type) List.class, String.class));
        } catch (IOException e) {
            return null;
        }
    }

    public static void stripUnderlines(TextView textView) {
        replaceURLSpan(textView, Utils$$Lambda$1.lambdaFactory$());
    }

    public static void replaceURLColor(TextView textView, int color) {
        replaceURLSpan(textView, Utils$$Lambda$2.lambdaFactory$(color));
    }

    private static void replaceURLSpan(TextView textView, Func1<String, URLSpan> func) {
        Spannable s = (Spannable) textView.getText();
        for (URLSpan span : (URLSpan[]) s.getSpans(0, s.length(), URLSpan.class)) {
            int start = s.getSpanStart(span);
            int end = s.getSpanEnd(span);
            s.removeSpan(span);
            s.setSpan((URLSpan) func.call(span.getURL()), start, end, 0);
        }
        textView.setText(s);
    }

    public static double roundToSignificantFigures(double num, int n, boolean isFloor) {
        if (num == 0.0d) {
            return 0.0d;
        }
        if (n == 0) {
            return num;
        }
        double d;
        long shifted;
        if (num < 0.0d) {
            d = -num;
        } else {
            d = num;
        }
        double magnitude = Math.pow(10.0d, (double) (n - ((int) Math.ceil(Math.log10(d)))));
        if (isFloor) {
            shifted = (long) Math.floor(num * magnitude);
        } else {
            shifted = Math.round(num * magnitude);
        }
        return ((double) shifted) / magnitude;
    }

    public static void setAlpha(View view, float alpha, boolean animated, final Runnable completion) {
        if (animated) {
            ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", new float[]{alpha});
            anim.setDuration(100);
            anim.setInterpolator(new AccelerateInterpolator());
            anim.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator animation) {
                }

                public void onAnimationEnd(Animator animation) {
                    if (completion != null) {
                        completion.run();
                    }
                }

                public void onAnimationCancel(Animator animation) {
                }

                public void onAnimationRepeat(Animator animation) {
                }
            });
            anim.start();
            return;
        }
        view.setAlpha(alpha);
        if (completion != null) {
            completion.run();
        }
    }

    @Deprecated
    public static String getErrorMessage(Response<?> response, Retrofit retrofit) {
        return getErrorMessage((Response) response);
    }

    public static Pair<String, String> getErrorIdAndMessage(Response<?> response) {
        ErrorBody errorBody = (ErrorBody) getErrors((Response) response).getErrors().get(0);
        return new Pair(errorBody.getId(), errorBody.getMessage());
    }

    public static String getErrorMessage(Response<?> response) {
        return getErrorMessage(getErrors((Response) response));
    }

    public static String getErrorMessage(String errorBody) {
        return getErrorMessage(getErrors(errorBody));
    }

    public static String getErrorMessage(Errors errors) {
        String message = null;
        if (errors != null) {
            try {
                if (!(errors.getErrors() == null || errors.getErrors().isEmpty())) {
                    message = ((ErrorBody) errors.getErrors().get(0)).getMessage();
                }
            } catch (Exception e) {
            }
        }
        return message;
    }

    @Deprecated
    public static Errors getErrors(Response<?> response, Retrofit retrofit) {
        return getErrors((Response) response);
    }

    public static Errors getErrors(Response<?> response) {
        if (response == null || response.errorBody() == null) {
            return null;
        }
        return getErrors(getErrorBody(response.errorBody()));
    }

    public static Errors getErrors(String errorBody) {
        if (TextUtils.isEmpty(errorBody)) {
            return null;
        }
        try {
            return (Errors) new Gson().fromJson(errorBody, Errors.class);
        } catch (JsonSyntaxException e) {
            Errors errors = new Errors();
            errors.setErrors(Collections.singletonList(new ErrorBody()));
            ((ErrorBody) errors.getErrors().get(0)).setMessage(errorBody);
            return errors;
        }
    }

    public static String getErrorBody(ResponseBody errorBody) {
        if (errorBody == null || errorBody.byteStream() == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(errorBody.byteStream()));
        while (true) {
            try {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            } catch (IOException e) {
                sLogger.error("Couldn't read error", e);
            }
        }
        return sb.toString();
    }

    public static void showRateAppDialog(Activity activity, String callingView) {
        int count = PreferenceUtils.getPrefsInt(activity, Constants.KEY_RATE_APP_THRESHOLD, 0);
        if (count >= 3 && AppRate.showRateDialogIfMeetsConditions(activity)) {
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_APP_RATE_VIEWED, MixpanelTracking.PROPERTY_APP_RATE_CALLING_VIEW, callingView);
        }
        PreferenceUtils.putPrefsInt(activity, Constants.KEY_RATE_APP_THRESHOLD, count + 1);
    }

    public static String getMessage(Context context, Throwable t) {
        if (context != null && !isConnectedOrConnecting(context)) {
            return context.getString(R.string.no_internet);
        }
        if (t == null) {
            return "";
        }
        return t.getLocalizedMessage();
    }

    public static void showMessage(Context context, Throwable t, int length) {
        if (context != null) {
            String message = getMessage(context, t);
            if (!TextUtils.isEmpty(message)) {
                Toast.makeText(context, message, length).show();
            }
        }
    }

    public static void showMessage(Context context, String message, int length) {
        if (context != null && message != null) {
            Toast.makeText(context, message, length).show();
        }
    }

    public static void showMessage(Context context, int message, int length) {
        showMessage(context, context == null ? null : context.getString(message), length);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null) {
            View view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
            }
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
            activity.getWindow().setSoftInputMode(3);
        }
    }

    public static void hideKeyboardFrom(Activity activity, View view) {
        if (activity != null) {
            View focus = activity.getCurrentFocus();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService("input_method");
            if (imm != null) {
                if (focus != null) {
                    view = focus;
                }
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                activity.getWindow().setSoftInputMode(3);
            }
        }
    }

    public static void postShowKeyboardFrom(Activity activity, View view) {
        if (activity != null) {
            view.postDelayed(Utils$$Lambda$3.lambdaFactory$(activity, view), 200);
        }
    }

    static /* synthetic */ void lambda$postShowKeyboardFrom$2(Activity activity, View view) {
        InputMethodManager keyboard = (InputMethodManager) activity.getSystemService("input_method");
        if (keyboard != null) {
            keyboard.showSoftInput(view, 0);
        }
    }

    public static void showKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.toggleSoftInput(2, 1);
            }
        }
    }

    public static Intent supportEmailIntent(String senderEmail, String subject) {
        String uriText = "mailto:support@coinbase.com";
        if (subject != null) {
            uriText = uriText + "?subject=" + Uri.encode(subject);
        }
        if (senderEmail != null) {
            uriText = uriText + "&body=" + Uri.encode("Coinbase email: " + senderEmail);
        }
        Uri uri = Uri.parse(uriText);
        Intent contactSupportIntent = new Intent("android.intent.action.SENDTO");
        contactSupportIntent.setData(uri);
        return contactSupportIntent;
    }

    public static boolean isPhoneVerificationRequired(Collection<String> restrictions) {
        return hasRestriction(restrictions, Restriction.PHONE_VERIFICATION_REQUIRED.toString());
    }

    public static boolean isIdentityVerificationRequired(Collection<String> restrictions) {
        return hasRestriction(restrictions, Restriction.IDENTITY_VERIFICATION_REQUIRED.toString());
    }

    public static boolean isIdvRequired(Collection<String> restrictions) {
        return hasRestriction(restrictions, Restriction.IDV_REQUIRED.toString());
    }

    public static boolean isIdvWithFaceMatchRequired(Collection<String> restrictions) {
        return hasRestriction(restrictions, Restriction.IDV_WITH_FACEMATCH_REQUIRED.toString());
    }

    public static boolean isStateVerificationRequired(Collection<String> restrictions) {
        return hasRestriction(restrictions, Restriction.STATE_VERIFICATION_REQUIRED.toString());
    }

    public static boolean isStateUnsupported(Collection<String> restrictions) {
        return hasRestriction(restrictions, Restriction.STATE_UNSUPPORTED.toString());
    }

    public static boolean isUserEmailVerified(Collection<String> restrictions) {
        return !hasRestriction(restrictions, Restriction.EMAIL_VERIFICATION_REQUIRED.toString());
    }

    public static boolean isUserAgreementRequired(Collection<String> restrictions) {
        return hasRestriction(restrictions, Restriction.NEEDS_TO_REACCEPT_USER_AGREEMENT.toString());
    }

    private static boolean hasRestriction(Collection<String> restrictions, String checkRestriction) {
        for (String restriction : restrictions) {
            if (restriction.equalsIgnoreCase(checkRestriction)) {
                return true;
            }
        }
        return false;
    }

    private static DateTime getLocalDateTime() {
        return new LocalDateTime(DateTimeZone.getDefault()).toDateTime();
    }

    public static DateTime getDateTimeFrom(String string) {
        if (string == null || string.isEmpty()) {
            return getLocalDateTime();
        }
        try {
            return new DateTime(string, DateTimeZone.UTC).withZone(DateTimeZone.getDefault());
        } catch (Exception e) {
            return getLocalDateTime();
        }
    }

    public static void processPhoneNumbersUpdatedEvent(Object event, Context context) {
        if (event != null && context != null) {
            if (event instanceof String) {
                showMessage(context, (String) event, 1);
            } else if (event instanceof Integer) {
                showMessage(context, ((Integer) event).intValue(), 1);
            }
        }
    }

    public static void showRetrofitErrorMessage(Response<?> response, Retrofit retrofit, Context context) {
        if (context != null) {
            showMessage(context, getErrorMessage(response, retrofit), 1);
        }
    }

    public static Error getError(Response<?> response, Retrofit retrofit) {
        try {
            return (Error) retrofit.responseBodyConverter(Error.class, new Annotation[0]).convert(response.errorBody());
        } catch (Exception e) {
            Builder errorBuilder = Error.builder();
            errorBuilder.setError(ApiConstants.NONE);
            return errorBuilder.build();
        }
    }

    public static boolean isPhoneVerified(List<Data> phoneNumbers) {
        boolean isVerified = false;
        for (Data number : phoneNumbers) {
            if (number.getVerified().booleanValue()) {
                isVerified = true;
            }
        }
        return isVerified;
    }

    public static void setSelectedCurrencyType(Context context, Currency currency) {
        if (context != null && currency != null && !currency.toString().isEmpty()) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(Constants.KEY_LAST_CHOSEN_CURRENCY, currency.toString()).apply();
        }
    }

    public static Currency lastSelectedCurrencyType(Context context) {
        if (context == null) {
            return Currency.BTC;
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!AccountUtils.hasEthAccount(context)) {
            return Currency.BTC;
        }
        String currencyPrefs = prefs.getString(Constants.KEY_LAST_CHOSEN_CURRENCY, null);
        if (currencyPrefs == null) {
            return Currency.BTC;
        }
        return Currency.fromString(currencyPrefs);
    }

    public static void setAdminFlagsToPrefs(com.coinbase.v2.models.user.Data user, Context context) {
        Editor e = PreferenceManager.getDefaultSharedPreferences(context).edit();
        for (AdminFlags adminFlags : AdminFlags.values()) {
            e.putBoolean(adminFlags.toString(), false);
        }
        if (user.getAdminFlags() == null || user.getAdminFlags().isEmpty()) {
            e.apply();
            return;
        }
        for (String adminFlag : user.getAdminFlags()) {
            switch (AdminFlags.create(adminFlag)) {
                case REQUIRE_JUMIO_FACE_MATCH:
                    e.putBoolean(AdminFlags.REQUIRE_JUMIO_FACE_MATCH.toString(), true);
                    break;
                default:
                    break;
            }
        }
        e.apply();
    }

    public static boolean hasAdminFlag(AdminFlags adminFlag, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(adminFlag.toString(), false);
    }

    public static void loadAvatarImage(Context context, ImageView imageView, String url, int radius) {
        String avatarUrl = url;
        if (url == null || url.isEmpty()) {
            avatarUrl = null;
        }
        Picasso.with(context).load(avatarUrl).placeholder(new AvatarDrawable(BitmapFactory.decodeResource(context.getResources(), R.drawable.no_avatar))).transform(new RoundedTransformation(radius, 0)).into(imageView);
    }

    public static void dismissDialog(ProgressDialog dialog) {
        if (dialog != null) {
            try {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static CurrencyUnit getDeviceCurrency(Locale locale) {
        CurrencyUnit currencyUnit = CurrencyUnit.USD;
        if (locale == null) {
            return currencyUnit;
        }
        try {
            currencyUnit = getCurrencyUnitByCode(java.util.Currency.getInstance(locale).getCurrencyCode());
        } catch (Exception e) {
        }
        return currencyUnit;
    }

    public static CurrencyUnit getCurrencyUnitByCode(String code) {
        CurrencyUnit currencyUnit = CurrencyUnit.USD;
        if (TextUtils.isEmpty(code)) {
            return currencyUnit;
        }
        try {
            currencyUnit = CurrencyUnit.of(code);
        } catch (IllegalCurrencyException e) {
        }
        return currencyUnit;
    }

    public static int compareVersions(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        String[] vals1 = str1.split("\\.");
        String[] vals2 = str2.split("\\.");
        int i = 0;
        while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
            i++;
        }
        if (i >= vals1.length || i >= vals2.length) {
            return Integer.signum(vals1.length - vals2.length);
        }
        return Integer.signum(Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i])));
    }

    public static void showJumioFlow(Activity activity, boolean hide) {
        if (activity != null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
            if (prefs.getBoolean(Constants.KEY_SHOW_JUMIO_FLOW, false)) {
                Intent intent = new Intent(activity, IdentityVerificationActivity.class);
                if (hide) {
                    intent.setFlags(65536);
                }
                activity.startActivity(intent);
                MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_START_FROM_WEB, new String[0]);
                prefs.edit().putBoolean(Constants.KEY_SHOW_JUMIO_FLOW, false).apply();
            }
        }
    }

    public static double floorOneDecimal(double d) {
        DecimalFormat oneDForm;
        double value = Math.floor(d * 10.0d) / 10.0d;
        NumberFormat format = NumberFormat.getNumberInstance(Locale.ENGLISH);
        format.setMaximumFractionDigits(1);
        if (format instanceof DecimalFormat) {
            oneDForm = (DecimalFormat) format;
        } else {
            oneDForm = new DecimalFormat("#.#");
        }
        try {
            value = Double.valueOf(oneDForm.format(value)).doubleValue();
        } catch (NumberFormatException e) {
        }
        return value;
    }

    public static List<String> zeroDecimalCurrencyCodes(List<com.coinbase.v2.models.supportedCurrencies.Data> currencies) {
        List<String> zeroDecimalCurrencies = new ArrayList();
        for (com.coinbase.v2.models.supportedCurrencies.Data currency : currencies) {
            if (Double.parseDouble(currency.getMinSize()) >= 1.0d) {
                zeroDecimalCurrencies.add(currency.getId());
            }
        }
        return zeroDecimalCurrencies;
    }

    public static int getPixelValue(Context context, float dimenId) {
        return (int) TypedValue.applyDimension(1, dimenId, context.getResources().getDisplayMetrics());
    }

    public static int updateColor(int color, float factor) {
        return Color.argb(Color.alpha(color), Math.min(Math.round(((float) Color.red(color)) * factor), 255), Math.min(Math.round(((float) Color.green(color)) * factor), 255), Math.min(Math.round(((float) Color.blue(color)) * factor), 255));
    }

    public static boolean updateBackgroundColorWithAlpha(View view, int color) {
        return updateBackgroundColor(view, ColorUtils.setAlphaComponent(color, BACKGROUND_VIEW_COLOR_ALPHA));
    }

    public static boolean updateBackgroundColor(View view, int color) {
        if (view == null || view.getBackground() == null) {
            return false;
        }
        Drawable imageBackgroundView = view.getBackground().mutate();
        if (!(imageBackgroundView instanceof GradientDrawable)) {
            return false;
        }
        ((GradientDrawable) imageBackgroundView).setColor(color);
        return true;
    }

    public static int getColorInt(String color) {
        int colorInt = -1;
        if (TextUtils.isEmpty(color)) {
            return colorInt;
        }
        try {
            colorInt = Color.parseColor(color);
        } catch (IllegalArgumentException e) {
        }
        return colorInt;
    }

    public static boolean isBetweenVersions(String version, String min, String max) {
        if (version == null || min == null || max == null || compareVersions(version, min) <= 0 || compareVersions(version, max) >= 0) {
            return false;
        }
        return true;
    }
}
