package com.crashlytics.android;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.beta.Beta;
import com.crashlytics.android.core.CrashlyticsCore;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.KitGroup;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Crashlytics extends Kit<Void> implements KitGroup {
    public final Answers answers;
    public final Beta beta;
    public final CrashlyticsCore core;
    public final Collection<? extends Kit> kits;

    public Crashlytics() {
        this(new Answers(), new Beta(), new CrashlyticsCore());
    }

    Crashlytics(Answers answers, Beta beta, CrashlyticsCore core) {
        this.answers = answers;
        this.beta = beta;
        this.core = core;
        this.kits = Collections.unmodifiableCollection(Arrays.asList(new Kit[]{answers, beta, core}));
    }

    public String getVersion() {
        return "2.7.0.18";
    }

    public String getIdentifier() {
        return "com.crashlytics.sdk.android:crashlytics";
    }

    public Collection<? extends Kit> getKits() {
        return this.kits;
    }

    protected Void doInBackground() {
        return null;
    }

    public static Crashlytics getInstance() {
        return (Crashlytics) Fabric.getKit(Crashlytics.class);
    }

    public static void logException(Throwable throwable) {
        checkInitialized();
        getInstance().core.logException(throwable);
    }

    public static void setUserIdentifier(String identifier) {
        checkInitialized();
        getInstance().core.setUserIdentifier(identifier);
    }

    public static void setString(String key, String value) {
        checkInitialized();
        getInstance().core.setString(key, value);
    }

    private static void checkInitialized() {
        if (getInstance() == null) {
            throw new IllegalStateException("Crashlytics must be initialized by calling Fabric.with(Context) prior to calling Crashlytics.getInstance()");
        }
    }
}
