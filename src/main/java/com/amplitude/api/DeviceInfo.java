package com.amplitude.api;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class DeviceInfo {
    private CachedInfo cachedInfo;
    private Context context;
    private boolean locationListening = true;

    private class CachedInfo {
        private String advertisingId;
        private String brand;
        private String carrier;
        private String country;
        private boolean gpsEnabled;
        private String language;
        private boolean limitAdTrackingEnabled;
        private String manufacturer;
        private String model;
        private String osName;
        private String osVersion;
        private String versionName;

        private CachedInfo() {
            this.advertisingId = getAdvertisingId();
            this.versionName = getVersionName();
            this.osName = getOsName();
            this.osVersion = getOsVersion();
            this.brand = getBrand();
            this.manufacturer = getManufacturer();
            this.model = getModel();
            this.carrier = getCarrier();
            this.country = getCountry();
            this.language = getLanguage();
            this.gpsEnabled = checkGPSEnabled();
        }

        private String getVersionName() {
            try {
                return DeviceInfo.this.context.getPackageManager().getPackageInfo(DeviceInfo.this.context.getPackageName(), 0).versionName;
            } catch (NameNotFoundException e) {
                return null;
            }
        }

        private String getOsName() {
            return "android";
        }

        private String getOsVersion() {
            return VERSION.RELEASE;
        }

        private String getBrand() {
            return Build.BRAND;
        }

        private String getManufacturer() {
            return Build.MANUFACTURER;
        }

        private String getModel() {
            return Build.MODEL;
        }

        private String getCarrier() {
            try {
                return ((TelephonyManager) DeviceInfo.this.context.getSystemService("phone")).getNetworkOperatorName();
            } catch (Exception e) {
                return null;
            }
        }

        private String getCountry() {
            String country = getCountryFromLocation();
            if (!Utils.isEmptyString(country)) {
                return country;
            }
            country = getCountryFromNetwork();
            if (Utils.isEmptyString(country)) {
                return getCountryFromLocale();
            }
            return country;
        }

        private String getCountryFromLocation() {
            if (!DeviceInfo.this.isLocationListening()) {
                return null;
            }
            Location recent = DeviceInfo.this.getMostRecentLocation();
            if (recent != null) {
                try {
                    if (Geocoder.isPresent()) {
                        List<Address> addresses = DeviceInfo.this.getGeocoder().getFromLocation(recent.getLatitude(), recent.getLongitude(), 1);
                        if (addresses != null) {
                            for (Address address : addresses) {
                                if (address != null) {
                                    return address.getCountryCode();
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                } catch (NullPointerException e2) {
                } catch (NoSuchMethodError e3) {
                } catch (IllegalArgumentException e4) {
                }
            }
            return null;
        }

        private String getCountryFromNetwork() {
            try {
                TelephonyManager manager = (TelephonyManager) DeviceInfo.this.context.getSystemService("phone");
                if (manager.getPhoneType() != 2) {
                    String country = manager.getNetworkCountryIso();
                    if (country != null) {
                        return country.toUpperCase(Locale.US);
                    }
                }
            } catch (Exception e) {
            }
            return null;
        }

        private String getCountryFromLocale() {
            return Locale.getDefault().getCountry();
        }

        private String getLanguage() {
            return Locale.getDefault().getLanguage();
        }

        private String getAdvertisingId() {
            if ("Amazon".equals(getManufacturer())) {
                return getAndCacheAmazonAdvertisingId();
            }
            return getAndCacheGoogleAdvertisingId();
        }

        private String getAndCacheAmazonAdvertisingId() {
            boolean z = true;
            ContentResolver cr = DeviceInfo.this.context.getContentResolver();
            if (Secure.getInt(cr, "limit_ad_tracking", 0) != 1) {
                z = false;
            }
            this.limitAdTrackingEnabled = z;
            this.advertisingId = Secure.getString(cr, "advertising_id");
            return this.advertisingId;
        }

        private String getAndCacheGoogleAdvertisingId() {
            boolean z = true;
            try {
                Object advertisingInfo = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{DeviceInfo.this.context});
                Boolean limitAdTrackingEnabled = (Boolean) advertisingInfo.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(advertisingInfo, new Object[0]);
                if (limitAdTrackingEnabled == null || !limitAdTrackingEnabled.booleanValue()) {
                    z = false;
                }
                this.limitAdTrackingEnabled = z;
                this.advertisingId = (String) advertisingInfo.getClass().getMethod("getId", new Class[0]).invoke(advertisingInfo, new Object[0]);
            } catch (ClassNotFoundException e) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services SDK not found!");
            } catch (InvocationTargetException e2) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services not available");
            } catch (Exception e3) {
                AmplitudeLog.getLogger().e("com.amplitude.api.DeviceInfo", "Encountered an error connecting to Google Play Services", e3);
            }
            return this.advertisingId;
        }

        private boolean checkGPSEnabled() {
            try {
                Integer status = (Integer) Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{DeviceInfo.this.context});
                if (status == null || status.intValue() != 0) {
                    return false;
                }
                return true;
            } catch (NoClassDefFoundError e) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services Util not found!");
                return false;
            } catch (ClassNotFoundException e2) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services Util not found!");
                return false;
            } catch (NoSuchMethodException e3) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services not available");
                return false;
            } catch (InvocationTargetException e4) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services not available");
                return false;
            } catch (IllegalAccessException e5) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services not available");
                return false;
            } catch (Exception e6) {
                AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Error when checking for Google Play Services: " + e6);
                return false;
            }
        }
    }

    public DeviceInfo(Context context) {
        this.context = context;
    }

    private CachedInfo getCachedInfo() {
        if (this.cachedInfo == null) {
            this.cachedInfo = new CachedInfo();
        }
        return this.cachedInfo;
    }

    public void prefetch() {
        getCachedInfo();
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public String getVersionName() {
        return getCachedInfo().versionName;
    }

    public String getOsName() {
        return getCachedInfo().osName;
    }

    public String getOsVersion() {
        return getCachedInfo().osVersion;
    }

    public String getBrand() {
        return getCachedInfo().brand;
    }

    public String getManufacturer() {
        return getCachedInfo().manufacturer;
    }

    public String getModel() {
        return getCachedInfo().model;
    }

    public String getCarrier() {
        return getCachedInfo().carrier;
    }

    public String getCountry() {
        return getCachedInfo().country;
    }

    public String getLanguage() {
        return getCachedInfo().language;
    }

    public String getAdvertisingId() {
        return getCachedInfo().advertisingId;
    }

    public boolean isLimitAdTrackingEnabled() {
        return getCachedInfo().limitAdTrackingEnabled;
    }

    public boolean isGooglePlayServicesEnabled() {
        return getCachedInfo().gpsEnabled;
    }

    public Location getMostRecentLocation() {
        Location location = null;
        if (isLocationListening()) {
            LocationManager locationManager = (LocationManager) this.context.getSystemService("location");
            if (locationManager != null) {
                List<String> providers = null;
                try {
                    providers = locationManager.getProviders(true);
                } catch (SecurityException e) {
                }
                if (providers != null) {
                    Location location2;
                    List<Location> locations = new ArrayList();
                    for (String provider : providers) {
                        location2 = null;
                        try {
                            location2 = locationManager.getLastKnownLocation(provider);
                        } catch (IllegalArgumentException e2) {
                        } catch (SecurityException e3) {
                        }
                        if (location2 != null) {
                            locations.add(location2);
                        }
                    }
                    long maximumTimestamp = -1;
                    location = null;
                    for (Location location22 : locations) {
                        if (location22.getTime() > maximumTimestamp) {
                            maximumTimestamp = location22.getTime();
                            location = location22;
                        }
                    }
                }
            }
        }
        return location;
    }

    public boolean isLocationListening() {
        return this.locationListening;
    }

    protected Geocoder getGeocoder() {
        return new Geocoder(this.context, Locale.ENGLISH);
    }
}
