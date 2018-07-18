package com.bumptech.glide.module;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class ManifestParser {
    private final Context context;

    public ManifestParser(Context context) {
        this.context = context;
    }

    public List<GlideModule> parse() {
        if (Log.isLoggable("ManifestParser", 3)) {
            Log.d("ManifestParser", "Loading Glide modules");
        }
        List<GlideModule> modules = new ArrayList();
        try {
            ApplicationInfo appInfo = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
            if (appInfo.metaData != null) {
                if (Log.isLoggable("ManifestParser", 2)) {
                    Log.v("ManifestParser", "Got app info metadata: " + appInfo.metaData);
                }
                for (String key : appInfo.metaData.keySet()) {
                    if ("GlideModule".equals(appInfo.metaData.get(key))) {
                        modules.add(parseModule(key));
                        if (Log.isLoggable("ManifestParser", 3)) {
                            Log.d("ManifestParser", "Loaded Glide module: " + key);
                        }
                    }
                }
                if (Log.isLoggable("ManifestParser", 3)) {
                    Log.d("ManifestParser", "Finished loading Glide modules");
                }
            } else if (Log.isLoggable("ManifestParser", 3)) {
                Log.d("ManifestParser", "Got null app info metadata");
            }
            return modules;
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e);
        }
    }

    private static GlideModule parseModule(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            try {
                Object module = clazz.newInstance();
                if (module instanceof GlideModule) {
                    return (GlideModule) module;
                }
                throw new RuntimeException("Expected instanceof GlideModule, but found: " + module);
            } catch (InstantiationException e) {
                throw new RuntimeException("Unable to instantiate GlideModule implementation for " + clazz, e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Unable to instantiate GlideModule implementation for " + clazz, e2);
            }
        } catch (ClassNotFoundException e3) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e3);
        }
    }
}
