package com.firebase.jobdispatcher;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import com.coinbase.android.utils.CryptoUri;
import java.util.ArrayList;

final class GooglePlayCallbackExtractor {
    private static Boolean shouldReadKeysAsStringsCached = null;

    GooglePlayCallbackExtractor() {
    }

    public Pair<JobCallback, Bundle> extractCallback(Bundle data) {
        if (data != null) {
            return extractWrappedBinderFromParcel(data);
        }
        Log.e("FJD.GooglePlayReceiver", "No callback received, terminating");
        return null;
    }

    @SuppressLint({"ParcelClassLoader"})
    private static Pair<JobCallback, Bundle> extractWrappedBinderFromParcel(Bundle data) {
        Throwable th;
        Bundle cleanBundle = new Bundle();
        Parcel serialized = toParcel(data);
        try {
            if (serialized.readInt() <= 0) {
                Log.w("FJD.GooglePlayReceiver", "No callback received, terminating");
                serialized.recycle();
                return null;
            } else if (serialized.readInt() != 1279544898) {
                Log.w("FJD.GooglePlayReceiver", "No callback received, terminating");
                serialized.recycle();
                return null;
            } else {
                JobCallback callback;
                int numEntries = serialized.readInt();
                int i = 0;
                JobCallback callback2 = null;
                while (i < numEntries) {
                    String entryKey = readKey(serialized);
                    if (entryKey == null) {
                        callback = callback2;
                    } else if (callback2 != null || !"callback".equals(entryKey)) {
                        Object value = serialized.readValue(null);
                        if (value instanceof String) {
                            cleanBundle.putString(entryKey, (String) value);
                            callback = callback2;
                        } else if (value instanceof Boolean) {
                            cleanBundle.putBoolean(entryKey, ((Boolean) value).booleanValue());
                            callback = callback2;
                        } else if (value instanceof Integer) {
                            cleanBundle.putInt(entryKey, ((Integer) value).intValue());
                            callback = callback2;
                        } else if (value instanceof ArrayList) {
                            cleanBundle.putParcelableArrayList(entryKey, (ArrayList) value);
                            callback = callback2;
                        } else if (value instanceof Bundle) {
                            cleanBundle.putBundle(entryKey, (Bundle) value);
                            callback = callback2;
                        } else if (value instanceof Parcelable) {
                            cleanBundle.putParcelable(entryKey, (Parcelable) value);
                            callback = callback2;
                        } else {
                            callback = callback2;
                        }
                    } else if (serialized.readInt() != 4) {
                        Log.w("FJD.GooglePlayReceiver", "Bad callback received, terminating");
                        serialized.recycle();
                        callback = callback2;
                        return null;
                    } else {
                        try {
                            if ("com.google.android.gms.gcm.PendingCallback".equals(serialized.readString())) {
                                callback = new GooglePlayJobCallback(serialized.readStrongBinder());
                            } else {
                                Log.w("FJD.GooglePlayReceiver", "Bad callback received, terminating");
                                serialized.recycle();
                                callback = callback2;
                                return null;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            callback = callback2;
                        }
                    }
                    i++;
                    callback2 = callback;
                }
                if (callback2 == null) {
                    Log.w("FJD.GooglePlayReceiver", "No callback received, terminating");
                    serialized.recycle();
                    callback = callback2;
                    return null;
                }
                Pair<JobCallback, Bundle> create = Pair.create(callback2, cleanBundle);
                serialized.recycle();
                callback = callback2;
                return create;
            }
        } catch (Throwable th3) {
            th = th3;
            serialized.recycle();
            throw th;
        }
    }

    private static Parcel toParcel(Bundle data) {
        Parcel serialized = Parcel.obtain();
        data.writeToParcel(serialized, 0);
        serialized.setDataPosition(0);
        return serialized;
    }

    private static String readKey(Parcel serialized) {
        if (shouldReadKeysAsStrings()) {
            return serialized.readString();
        }
        Object entryKeyObj = serialized.readValue(null);
        if (entryKeyObj instanceof String) {
            return (String) entryKeyObj;
        }
        Log.w("FJD.GooglePlayReceiver", "Bad callback received, terminating");
        return null;
    }

    private static synchronized boolean shouldReadKeysAsStrings() {
        boolean z = true;
        synchronized (GooglePlayCallbackExtractor.class) {
            if (shouldReadKeysAsStringsCached == null) {
                String expectedKey = "key";
                Bundle testBundle = new Bundle();
                testBundle.putString("key", CryptoUri.VALUE);
                Parcel testParcel = toParcel(testBundle);
                try {
                    boolean z2;
                    checkCondition(testParcel.readInt() > 0);
                    if (testParcel.readInt() == 1279544898) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    checkCondition(z2);
                    if (testParcel.readInt() != 1) {
                        z = false;
                    }
                    checkCondition(z);
                    shouldReadKeysAsStringsCached = Boolean.valueOf("key".equals(testParcel.readString()));
                    testParcel.recycle();
                } catch (RuntimeException e) {
                    shouldReadKeysAsStringsCached = Boolean.FALSE;
                    testParcel.recycle();
                } catch (Throwable th) {
                    testParcel.recycle();
                }
            }
            z = shouldReadKeysAsStringsCached.booleanValue();
        }
        return z;
    }

    private static void checkCondition(boolean condition) {
        if (!condition) {
            throw new IllegalStateException();
        }
    }
}
