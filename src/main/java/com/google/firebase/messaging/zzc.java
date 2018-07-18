package com.google.firebase.messaging;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzeym;
import com.google.android.gms.internal.zzezo;
import com.google.android.gms.internal.zzezp;
import com.google.android.gms.measurement.AppMeasurement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class zzc {
    private static Bundle zza(zzezp com_google_android_gms_internal_zzezp) {
        return zzay(com_google_android_gms_internal_zzezp.zzoxs, com_google_android_gms_internal_zzezp.zzoxt);
    }

    private static Object zza(zzezp com_google_android_gms_internal_zzezp, String str, zzb com_google_firebase_messaging_zzb) {
        Object newInstance;
        Throwable e;
        Object obj = null;
        try {
            Class cls = Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            Bundle zza = zza(com_google_android_gms_internal_zzezp);
            newInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            try {
                cls.getField("mOrigin").set(newInstance, str);
                cls.getField("mCreationTimestamp").set(newInstance, Long.valueOf(com_google_android_gms_internal_zzezp.zzoxu));
                cls.getField("mName").set(newInstance, com_google_android_gms_internal_zzezp.zzoxs);
                cls.getField("mValue").set(newInstance, com_google_android_gms_internal_zzezp.zzoxt);
                if (!TextUtils.isEmpty(com_google_android_gms_internal_zzezp.zzoxv)) {
                    obj = com_google_android_gms_internal_zzezp.zzoxv;
                }
                cls.getField("mTriggerEventName").set(newInstance, obj);
                cls.getField("mTimedOutEventName").set(newInstance, !TextUtils.isEmpty(com_google_android_gms_internal_zzezp.zzoya) ? com_google_android_gms_internal_zzezp.zzoya : com_google_firebase_messaging_zzb.zzboc());
                cls.getField("mTimedOutEventParams").set(newInstance, zza);
                cls.getField("mTriggerTimeout").set(newInstance, Long.valueOf(com_google_android_gms_internal_zzezp.zzoxw));
                cls.getField("mTriggeredEventName").set(newInstance, !TextUtils.isEmpty(com_google_android_gms_internal_zzezp.zzoxy) ? com_google_android_gms_internal_zzezp.zzoxy : com_google_firebase_messaging_zzb.zzbob());
                cls.getField("mTriggeredEventParams").set(newInstance, zza);
                cls.getField("mTimeToLive").set(newInstance, Long.valueOf(com_google_android_gms_internal_zzezp.zzgcc));
                cls.getField("mExpiredEventName").set(newInstance, !TextUtils.isEmpty(com_google_android_gms_internal_zzezp.zzoyb) ? com_google_android_gms_internal_zzezp.zzoyb : com_google_firebase_messaging_zzb.zzbod());
                cls.getField("mExpiredEventParams").set(newInstance, zza);
            } catch (ClassNotFoundException e2) {
                e = e2;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return newInstance;
            } catch (NoSuchMethodException e3) {
                e = e3;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return newInstance;
            } catch (IllegalAccessException e4) {
                e = e4;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return newInstance;
            } catch (InvocationTargetException e5) {
                e = e5;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return newInstance;
            } catch (NoSuchFieldException e6) {
                e = e6;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return newInstance;
            } catch (InstantiationException e7) {
                e = e7;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return newInstance;
            }
        } catch (ClassNotFoundException e8) {
            e = e8;
            newInstance = null;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return newInstance;
        } catch (NoSuchMethodException e9) {
            e = e9;
            newInstance = null;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return newInstance;
        } catch (IllegalAccessException e10) {
            e = e10;
            newInstance = null;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return newInstance;
        } catch (InvocationTargetException e11) {
            e = e11;
            newInstance = null;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return newInstance;
        } catch (NoSuchFieldException e12) {
            e = e12;
            newInstance = null;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return newInstance;
        } catch (InstantiationException e13) {
            e = e13;
            newInstance = null;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return newInstance;
        }
        return newInstance;
    }

    private static String zza(zzezp com_google_android_gms_internal_zzezp, zzb com_google_firebase_messaging_zzb) {
        return (com_google_android_gms_internal_zzezp == null || TextUtils.isEmpty(com_google_android_gms_internal_zzezp.zzoxz)) ? com_google_firebase_messaging_zzb.zzboe() : com_google_android_gms_internal_zzezp.zzoxz;
    }

    private static List<Object> zza(AppMeasurement appMeasurement, String str) {
        List<Object> list;
        Throwable e;
        Object obj;
        ArrayList arrayList = new ArrayList();
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getConditionalUserProperties", new Class[]{String.class, String.class});
            declaredMethod.setAccessible(true);
            list = (List) declaredMethod.invoke(appMeasurement, new Object[]{str, ""});
        } catch (NoSuchMethodException e2) {
            e = e2;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            obj = arrayList;
            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str).length() + 55).append("Number of currently set _Es for origin: ").append(str).append(" is ").append(list.size()).toString());
            }
            return list;
        } catch (IllegalAccessException e3) {
            e = e3;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            obj = arrayList;
            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str).length() + 55).append("Number of currently set _Es for origin: ").append(str).append(" is ").append(list.size()).toString());
            }
            return list;
        } catch (InvocationTargetException e4) {
            e = e4;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            obj = arrayList;
            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str).length() + 55).append("Number of currently set _Es for origin: ").append(str).append(" is ").append(list.size()).toString());
            }
            return list;
        }
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str).length() + 55).append("Number of currently set _Es for origin: ").append(str).append(" is ").append(list.size()).toString());
        }
        return list;
    }

    private static void zza(Context context, String str, String str2, String str3, String str4) {
        Throwable e;
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String str5 = "FirebaseAbtUtil";
            String str6 = "_CE(experimentId) called by ";
            String valueOf = String.valueOf(str);
            Log.v(str5, valueOf.length() != 0 ? str6.concat(valueOf) : new String(str6));
        }
        if (zzeh(context)) {
            AppMeasurement zzcs = zzcs(context);
            try {
                Method declaredMethod = AppMeasurement.class.getDeclaredMethod("clearConditionalUserProperty", new Class[]{String.class, String.class, Bundle.class});
                declaredMethod.setAccessible(true);
                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(str2).length() + 17) + String.valueOf(str3).length()).append("Clearing _E: [").append(str2).append(", ").append(str3).append("]").toString());
                }
                declaredMethod.invoke(zzcs, new Object[]{str2, str4, zzay(str2, str3)});
            } catch (NoSuchMethodException e2) {
                e = e2;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            } catch (IllegalAccessException e3) {
                e = e3;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            } catch (InvocationTargetException e4) {
                e = e4;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            }
        }
    }

    public static void zza(Context context, String str, byte[] bArr, zzb com_google_firebase_messaging_zzb, int i) {
        Throwable e;
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String str2 = "FirebaseAbtUtil";
            String str3 = "_SE called by ";
            String valueOf = String.valueOf(str);
            Log.v(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        if (zzeh(context)) {
            AppMeasurement zzcs = zzcs(context);
            zzezp zzal = zzal(bArr);
            if (zzal != null) {
                try {
                    Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                    Object obj = null;
                    for (Object next : zza(zzcs, str)) {
                        Object next2;
                        String zzat = zzat(next2);
                        String zzau = zzau(next2);
                        long longValue = ((Long) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mCreationTimestamp").get(next2)).longValue();
                        if (zzal.zzoxs.equals(zzat) && zzal.zzoxt.equals(zzau)) {
                            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzat).length() + 23) + String.valueOf(zzau).length()).append("_E is already set. [").append(zzat).append(", ").append(zzau).append("]").toString());
                            }
                            obj = 1;
                        } else {
                            next2 = null;
                            zzezo[] com_google_android_gms_internal_zzezoArr = zzal.zzoyd;
                            int length = com_google_android_gms_internal_zzezoArr.length;
                            int i2 = 0;
                            while (i2 < length) {
                                if (com_google_android_gms_internal_zzezoArr[i2].zzoxs.equals(zzat)) {
                                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                        Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzat).length() + 33) + String.valueOf(zzau).length()).append("_E is found in the _OE list. [").append(zzat).append(", ").append(zzau).append("]").toString());
                                    }
                                    next2 = 1;
                                    if (next2 != null) {
                                        continue;
                                    } else if (zzal.zzoxu > longValue) {
                                        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                            Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzat).length() + 115) + String.valueOf(zzau).length()).append("Clearing _E as it was not in the _OE list, andits start time is older than the start time of the _E to be set. [").append(zzat).append(", ").append(zzau).append("]").toString());
                                        }
                                        zza(context, str, zzat, zzau, zza(zzal, com_google_firebase_messaging_zzb));
                                    } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                        Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzat).length() + 109) + String.valueOf(zzau).length()).append("_E was not found in the _OE list, but not clearing it as it has a new start time than the _E to be set.  [").append(zzat).append(", ").append(zzau).append("]").toString());
                                    }
                                } else {
                                    i2++;
                                }
                            }
                            if (next2 != null) {
                                continue;
                            } else if (zzal.zzoxu > longValue) {
                                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                    Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzat).length() + 115) + String.valueOf(zzau).length()).append("Clearing _E as it was not in the _OE list, andits start time is older than the start time of the _E to be set. [").append(zzat).append(", ").append(zzau).append("]").toString());
                                }
                                zza(context, str, zzat, zzau, zza(zzal, com_google_firebase_messaging_zzb));
                            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzat).length() + 109) + String.valueOf(zzau).length()).append("_E was not found in the _OE list, but not clearing it as it has a new start time than the _E to be set.  [").append(zzat).append(", ").append(zzau).append("]").toString());
                            }
                        }
                    }
                    if (obj == null) {
                        zza(zzcs, context, str, zzal, com_google_firebase_messaging_zzb, 1);
                        return;
                    } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                        str2 = zzal.zzoxs;
                        str3 = zzal.zzoxt;
                        Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(str2).length() + 44) + String.valueOf(str3).length()).append("_E is already set. Not setting it again [").append(str2).append(", ").append(str3).append("]").toString());
                        return;
                    } else {
                        return;
                    }
                } catch (ClassNotFoundException e2) {
                    e = e2;
                } catch (IllegalAccessException e3) {
                    e = e3;
                } catch (NoSuchFieldException e4) {
                    e = e4;
                }
            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                Log.v("FirebaseAbtUtil", "_SE failed; either _P was not set, or we couldn't deserialize the _P.");
                return;
            } else {
                return;
            }
        }
        return;
        Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(AppMeasurement appMeasurement, Context context, String str, zzezp com_google_android_gms_internal_zzezp, zzb com_google_firebase_messaging_zzb, int i) {
        Throwable e;
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String str2 = com_google_android_gms_internal_zzezp.zzoxs;
            String str3 = com_google_android_gms_internal_zzezp.zzoxt;
            Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(str2).length() + 7) + String.valueOf(str3).length()).append("_SEI: ").append(str2).append(" ").append(str3).toString());
        }
        try {
            String zzat;
            Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            List zza = zza(appMeasurement, str);
            if (zza(appMeasurement, str).size() >= zzb(appMeasurement, str)) {
                if ((com_google_android_gms_internal_zzezp.zzoyc != 0 ? com_google_android_gms_internal_zzezp.zzoyc : 1) == 1) {
                    Object obj = zza.get(0);
                    zzat = zzat(obj);
                    String zzau = zzau(obj);
                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                        Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(zzat).length() + 38).append("Clearing _E due to overflow policy: [").append(zzat).append("]").toString());
                    }
                    zza(context, str, zzat, zzau, zza(com_google_android_gms_internal_zzezp, com_google_firebase_messaging_zzb));
                } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    zzat = com_google_android_gms_internal_zzezp.zzoxs;
                    str2 = com_google_android_gms_internal_zzezp.zzoxt;
                    Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzat).length() + 44) + String.valueOf(str2).length()).append("_E won't be set due to overflow policy. [").append(zzat).append(", ").append(str2).append("]").toString());
                    return;
                } else {
                    return;
                }
            }
            for (Object next : zza) {
                str2 = zzat(next);
                zzat = zzau(next);
                if (str2.equals(com_google_android_gms_internal_zzezp.zzoxs) && !zzat.equals(com_google_android_gms_internal_zzezp.zzoxt) && Log.isLoggable("FirebaseAbtUtil", 2)) {
                    Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(str2).length() + 77) + String.valueOf(zzat).length()).append("Clearing _E, as only one _V of the same _E can be set atany given time: [").append(str2).append(", ").append(zzat).append("].").toString());
                    zza(context, str, str2, zzat, zza(com_google_android_gms_internal_zzezp, com_google_firebase_messaging_zzb));
                }
            }
            if (zza(com_google_android_gms_internal_zzezp, str, com_google_firebase_messaging_zzb) != null) {
                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    str2 = com_google_android_gms_internal_zzezp.zzoxs;
                    str3 = com_google_android_gms_internal_zzezp.zzoxt;
                    String str4 = com_google_android_gms_internal_zzezp.zzoxv;
                    Log.v("FirebaseAbtUtil", new StringBuilder(((String.valueOf(str2).length() + 27) + String.valueOf(str3).length()) + String.valueOf(str4).length()).append("Setting _CUP for _E: [").append(str2).append(", ").append(str3).append(", ").append(str4).append("]").toString());
                }
                Method declaredMethod = AppMeasurement.class.getDeclaredMethod("setConditionalUserProperty", new Class[]{Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty")});
                declaredMethod.setAccessible(true);
                appMeasurement.logEventInternal(str, !TextUtils.isEmpty(com_google_android_gms_internal_zzezp.zzoxx) ? com_google_android_gms_internal_zzezp.zzoxx : com_google_firebase_messaging_zzb.zzboa(), zza(com_google_android_gms_internal_zzezp));
                declaredMethod.invoke(appMeasurement, new Object[]{next});
            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                zzat = com_google_android_gms_internal_zzezp.zzoxs;
                str2 = com_google_android_gms_internal_zzezp.zzoxt;
                Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzat).length() + 42) + String.valueOf(str2).length()).append("Could not create _CUP for: [").append(zzat).append(", ").append(str2).append("]. Skipping.").toString());
            }
        } catch (ClassNotFoundException e2) {
            e = e2;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        } catch (NoSuchMethodException e3) {
            e = e3;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        } catch (IllegalAccessException e4) {
            e = e4;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        } catch (InvocationTargetException e5) {
            e = e5;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        } catch (NoSuchFieldException e6) {
            e = e6;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        }
    }

    private static zzezp zzal(byte[] bArr) {
        try {
            return zzezp.zzbi(bArr);
        } catch (zzeym e) {
            return null;
        }
    }

    private static String zzat(Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mName").get(obj);
    }

    private static String zzau(Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mValue").get(obj);
    }

    private static Bundle zzay(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        return bundle;
    }

    private static int zzb(AppMeasurement appMeasurement, String str) {
        Throwable e;
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getMaxUserProperties", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(appMeasurement, new Object[]{str})).intValue();
        } catch (NoSuchMethodException e2) {
            e = e2;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return 20;
        } catch (IllegalAccessException e3) {
            e = e3;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return 20;
        } catch (InvocationTargetException e4) {
            e = e4;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return 20;
        }
    }

    private static AppMeasurement zzcs(Context context) {
        try {
            return AppMeasurement.getInstance(context);
        } catch (NoClassDefFoundError e) {
            return null;
        }
    }

    private static boolean zzeh(Context context) {
        if (zzcs(context) != null) {
            try {
                Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                return true;
            } catch (ClassNotFoundException e) {
                if (!Log.isLoggable("FirebaseAbtUtil", 2)) {
                    return false;
                }
                Log.v("FirebaseAbtUtil", "Firebase Analytics library is missing support for abt. Please update to a more recent version.");
                return false;
            }
        } else if (!Log.isLoggable("FirebaseAbtUtil", 2)) {
            return false;
        } else {
            Log.v("FirebaseAbtUtil", "Firebase Analytics not available");
            return false;
        }
    }
}
