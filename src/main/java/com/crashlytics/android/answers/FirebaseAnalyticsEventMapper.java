package com.crashlytics.android.answers;

import android.os.Bundle;
import com.coinbase.android.utils.CryptoUri;
import com.coinbase.api.internal.ApiConstants;
import io.fabric.sdk.android.Fabric;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FirebaseAnalyticsEventMapper {
    private static final Set<String> EVENT_NAMES = new HashSet(Arrays.asList(new String[]{"app_clear_data", "app_exception", "app_remove", "app_upgrade", "app_install", "app_update", "firebase_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "ad_exposure", "adunit_exposure", "ad_query", "ad_activeview", "ad_impression", "ad_click", "screen_view", "firebase_extra_parameter"}));

    public FirebaseAnalyticsEvent mapEvent(SessionEvent event) {
        boolean isCustomEvent;
        boolean isPredefinedEvent;
        if (!Type.CUSTOM.equals(event.type) || event.customType == null) {
            isCustomEvent = false;
        } else {
            isCustomEvent = true;
        }
        if (!Type.PREDEFINED.equals(event.type) || event.predefinedType == null) {
            isPredefinedEvent = false;
        } else {
            isPredefinedEvent = true;
        }
        if (!isCustomEvent && !isPredefinedEvent) {
            return null;
        }
        Bundle bundle;
        String eventName;
        if (isPredefinedEvent) {
            bundle = mapPredefinedEvent(event);
        } else {
            bundle = new Bundle();
            if (event.customAttributes != null) {
                mapCustomEventAttributes(bundle, event.customAttributes);
            }
        }
        if (isPredefinedEvent) {
            boolean wasFailedEvent;
            String successBoolean = (String) event.predefinedAttributes.get("success");
            if (successBoolean == null || Boolean.parseBoolean(successBoolean)) {
                wasFailedEvent = false;
            } else {
                wasFailedEvent = true;
            }
            eventName = mapPredefinedEventName(event.predefinedType, wasFailedEvent);
        } else {
            eventName = mapCustomEventName(event.customType);
        }
        Fabric.getLogger().d("Answers", "Logging event into firebase...");
        return new FirebaseAnalyticsEvent(eventName, bundle);
    }

    private String mapCustomEventName(String eventName) {
        if (eventName == null || eventName.length() == 0) {
            return "fabric_unnamed_event";
        }
        if (EVENT_NAMES.contains(eventName)) {
            return "fabric_" + eventName;
        }
        eventName = eventName.replaceAll("[^\\p{Alnum}_]+", "_");
        if (eventName.startsWith("ga_") || eventName.startsWith("google_") || eventName.startsWith("firebase_") || !Character.isLetter(eventName.charAt(0))) {
            eventName = "fabric_" + eventName;
        }
        if (eventName.length() > 40) {
            eventName = eventName.substring(0, 40);
        }
        return eventName;
    }

    private String mapAttribute(String attributeName) {
        if (attributeName == null || attributeName.length() == 0) {
            return "fabric_unnamed_parameter";
        }
        attributeName = attributeName.replaceAll("[^\\p{Alnum}_]+", "_");
        if (attributeName.startsWith("ga_") || attributeName.startsWith("google_") || attributeName.startsWith("firebase_") || !Character.isLetter(attributeName.charAt(0))) {
            attributeName = "fabric_" + attributeName;
        }
        return attributeName.length() > 40 ? attributeName.substring(0, 40) : attributeName;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private String mapPredefinedEventName(String name, boolean wasFailedEvent) {
        Object obj = null;
        if (wasFailedEvent) {
            Object obj2;
            int i;
            switch (name.hashCode()) {
                case -902468296:
                    if (name.equals("signUp")) {
                        i = 1;
                        break;
                    }
                case 103149417:
                    if (name.equals("login")) {
                        i = 2;
                        break;
                    }
                case 1743324417:
                    if (name.equals("purchase")) {
                        obj2 = null;
                        break;
                    }
                default:
                    obj2 = -1;
                    break;
            }
            switch (obj2) {
                case null:
                    return "failed_ecommerce_purchase";
                case 1:
                    return "failed_sign_up";
                case 2:
                    return "failed_login";
            }
        }
        int i2;
        switch (name.hashCode()) {
            case -2131650889:
                if (name.equals("levelEnd")) {
                    obj = 11;
                    break;
                }
            case -1183699191:
                if (name.equals("invite")) {
                    obj = 9;
                    break;
                }
            case -938102371:
                if (name.equals("rating")) {
                    obj = 6;
                    break;
                }
            case -906336856:
                if (name.equals("search")) {
                    obj = 4;
                    break;
                }
            case -902468296:
                if (name.equals("signUp")) {
                    obj = 7;
                    break;
                }
            case -389087554:
                if (name.equals("contentView")) {
                    obj = 3;
                    break;
                }
            case 23457852:
                if (name.equals("addToCart")) {
                    i2 = 1;
                    break;
                }
            case 103149417:
                if (name.equals("login")) {
                    obj = 8;
                    break;
                }
            case 109400031:
                if (name.equals("share")) {
                    obj = 5;
                    break;
                }
            case 196004670:
                if (name.equals("levelStart")) {
                    obj = 10;
                    break;
                }
            case 1664021448:
                if (name.equals("startCheckout")) {
                    i2 = 2;
                    break;
                }
            case 1743324417:
                if (name.equals("purchase")) {
                    break;
                }
            default:
                obj = -1;
                break;
        }
        switch (obj) {
            case null:
                return "ecommerce_purchase";
            case 1:
                return "add_to_cart";
            case 2:
                return "begin_checkout";
            case 3:
                return "select_content";
            case 4:
                return "search";
            case 5:
                return "share";
            case 6:
                return "rate_content";
            case 7:
                return "sign_up";
            case 8:
                return "login";
            case 9:
                return "invite";
            case 10:
                return "level_start";
            case 11:
                return "level_end";
            default:
                return mapCustomEventName(name);
        }
    }

    private Bundle mapPredefinedEvent(SessionEvent event) {
        Bundle bundle = new Bundle();
        if ("purchase".equals(event.predefinedType)) {
            putString(bundle, "item_id", (String) event.predefinedAttributes.get("itemId"));
            putString(bundle, "item_name", (String) event.predefinedAttributes.get("itemName"));
            putString(bundle, "item_category", (String) event.predefinedAttributes.get("itemType"));
            putDouble(bundle, CryptoUri.VALUE, mapPriceValue(event.predefinedAttributes.get("itemPrice")));
            putString(bundle, "currency", (String) event.predefinedAttributes.get("currency"));
        } else if ("addToCart".equals(event.predefinedType)) {
            putString(bundle, "item_id", (String) event.predefinedAttributes.get("itemId"));
            putString(bundle, "item_name", (String) event.predefinedAttributes.get("itemName"));
            putString(bundle, "item_category", (String) event.predefinedAttributes.get("itemType"));
            putDouble(bundle, "price", mapPriceValue(event.predefinedAttributes.get("itemPrice")));
            putDouble(bundle, CryptoUri.VALUE, mapPriceValue(event.predefinedAttributes.get("itemPrice")));
            putString(bundle, "currency", (String) event.predefinedAttributes.get("currency"));
            bundle.putLong("quantity", 1);
        } else if ("startCheckout".equals(event.predefinedType)) {
            putLong(bundle, "quantity", Long.valueOf((long) ((Integer) event.predefinedAttributes.get("itemCount")).intValue()));
            putDouble(bundle, CryptoUri.VALUE, mapPriceValue(event.predefinedAttributes.get("totalPrice")));
            putString(bundle, "currency", (String) event.predefinedAttributes.get("currency"));
        } else if ("contentView".equals(event.predefinedType)) {
            putString(bundle, "content_type", (String) event.predefinedAttributes.get("contentType"));
            putString(bundle, "item_id", (String) event.predefinedAttributes.get("contentId"));
            putString(bundle, "item_name", (String) event.predefinedAttributes.get("contentName"));
        } else if ("search".equals(event.predefinedType)) {
            putString(bundle, "search_term", (String) event.predefinedAttributes.get(ApiConstants.QUERY));
        } else if ("share".equals(event.predefinedType)) {
            putString(bundle, "method", (String) event.predefinedAttributes.get("method"));
            putString(bundle, "content_type", (String) event.predefinedAttributes.get("contentType"));
            putString(bundle, "item_id", (String) event.predefinedAttributes.get("contentId"));
            putString(bundle, "item_name", (String) event.predefinedAttributes.get("contentName"));
        } else if ("rating".equals(event.predefinedType)) {
            putString(bundle, "rating", String.valueOf(event.predefinedAttributes.get("rating")));
            putString(bundle, "content_type", (String) event.predefinedAttributes.get("contentType"));
            putString(bundle, "item_id", (String) event.predefinedAttributes.get("contentId"));
            putString(bundle, "item_name", (String) event.predefinedAttributes.get("contentName"));
        } else if ("signUp".equals(event.predefinedType)) {
            putString(bundle, "method", (String) event.predefinedAttributes.get("method"));
        } else if ("login".equals(event.predefinedType)) {
            putString(bundle, "method", (String) event.predefinedAttributes.get("method"));
        } else if ("invite".equals(event.predefinedType)) {
            putString(bundle, "method", (String) event.predefinedAttributes.get("method"));
        } else if ("levelStart".equals(event.predefinedType)) {
            putString(bundle, "level_name", (String) event.predefinedAttributes.get("levelName"));
        } else if ("levelEnd".equals(event.predefinedType)) {
            putDouble(bundle, "score", mapDouble(event.predefinedAttributes.get("score")));
            putString(bundle, "level_name", (String) event.predefinedAttributes.get("levelName"));
            putInt(bundle, "success", mapBooleanValue((String) event.predefinedAttributes.get("success")));
        }
        mapCustomEventAttributes(bundle, event.customAttributes);
        return bundle;
    }

    private void putLong(Bundle bundle, String param, Long longValue) {
        if (longValue != null) {
            bundle.putLong(param, longValue.longValue());
        }
    }

    private void putInt(Bundle bundle, String param, Integer intValue) {
        if (intValue != null) {
            bundle.putInt(param, intValue.intValue());
        }
    }

    private void putString(Bundle bundle, String param, String stringValue) {
        if (stringValue != null) {
            bundle.putString(param, stringValue);
        }
    }

    private void putDouble(Bundle bundle, String param, Double doubleValue) {
        Double mappedDouble = mapDouble(doubleValue);
        if (mappedDouble != null) {
            bundle.putDouble(param, mappedDouble.doubleValue());
        }
    }

    private Double mapDouble(Object doubleObj) {
        String doubleString = String.valueOf(doubleObj);
        if (doubleString == null) {
            return null;
        }
        return Double.valueOf(doubleString);
    }

    private Integer mapBooleanValue(String truthyString) {
        if (truthyString == null) {
            return null;
        }
        return Integer.valueOf(truthyString.equals("true") ? 1 : 0);
    }

    private Double mapPriceValue(Object o) {
        if (((Long) o) == null) {
            return null;
        }
        return Double.valueOf(new BigDecimal(((Long) o).longValue()).divide(AddToCartEvent.MICRO_CONSTANT).doubleValue());
    }

    private void mapCustomEventAttributes(Bundle mutatedBundle, Map<String, Object> customAttributes) {
        for (Entry<String, Object> o : customAttributes.entrySet()) {
            Object value = o.getValue();
            String attributeKey = mapAttribute((String) o.getKey());
            if (value instanceof String) {
                mutatedBundle.putString(attributeKey, o.getValue().toString());
            } else if (value instanceof Double) {
                mutatedBundle.putDouble(attributeKey, ((Double) o.getValue()).doubleValue());
            } else if (value instanceof Long) {
                mutatedBundle.putLong(attributeKey, ((Long) o.getValue()).longValue());
            } else if (value instanceof Integer) {
                mutatedBundle.putInt(attributeKey, ((Integer) o.getValue()).intValue());
            }
        }
    }
}
