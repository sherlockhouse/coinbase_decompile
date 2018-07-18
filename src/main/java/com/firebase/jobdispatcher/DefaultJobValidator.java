package com.firebase.jobdispatcher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import com.firebase.jobdispatcher.JobTrigger.ContentUriTrigger;
import com.firebase.jobdispatcher.JobTrigger.ExecutionWindowTrigger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DefaultJobValidator implements JobValidator {
    private final Context context;

    public DefaultJobValidator(Context context) {
        this.context = context;
    }

    private static int measureBundleSize(Bundle extras) {
        Parcel p = Parcel.obtain();
        extras.writeToParcel(p, 0);
        int sizeInBytes = p.dataSize();
        p.recycle();
        return sizeInBytes;
    }

    private static List<String> mergeErrorLists(List<String> errors, List<String> newErrors) {
        if (errors == null) {
            return newErrors;
        }
        if (newErrors == null) {
            return errors;
        }
        errors.addAll(newErrors);
        return errors;
    }

    private static List<String> addError(List<String> errors, String newError) {
        if (newError == null) {
            return errors;
        }
        if (errors == null) {
            return getMutableSingletonList(newError);
        }
        Collections.addAll(errors, new String[]{newError});
        return errors;
    }

    private static List<String> addErrorsIf(boolean condition, List<String> errors, String newErr) {
        if (condition) {
            return addError(errors, newErr);
        }
        return errors;
    }

    public List<String> validate(JobParameters job) {
        List<String> errors = mergeErrorLists(mergeErrorLists(null, validate(job.getTrigger())), validate(job.getRetryStrategy()));
        if (job.isRecurring() && job.getTrigger() == Trigger.NOW) {
            errors = addError(errors, "ImmediateTriggers can't be used with recurring jobs");
        }
        errors = mergeErrorLists(errors, validateForTransport(job.getExtras()));
        if (job.getLifetime() > 1) {
            errors = mergeErrorLists(errors, validateForPersistence(job.getExtras()));
        }
        return mergeErrorLists(mergeErrorLists(errors, validateTag(job.getTag())), validateService(job.getService()));
    }

    public List<String> validate(JobTrigger trigger) {
        if (trigger == Trigger.NOW || (trigger instanceof ExecutionWindowTrigger) || (trigger instanceof ContentUriTrigger)) {
            return null;
        }
        return getMutableSingletonList("Unknown trigger provided");
    }

    public List<String> validate(RetryStrategy retryStrategy) {
        boolean z = true;
        int policy = retryStrategy.getPolicy();
        int initial = retryStrategy.getInitialBackoff();
        int maximum = retryStrategy.getMaximumBackoff();
        boolean z2 = (policy == 1 || policy == 2) ? false : true;
        List<String> errors = addErrorsIf(z2, null, "Unknown retry policy provided");
        if (maximum < initial) {
            z2 = true;
        } else {
            z2 = false;
        }
        errors = addErrorsIf(z2, errors, "Maximum backoff must be greater than or equal to initial backoff");
        if (300 > maximum) {
            z2 = true;
        } else {
            z2 = false;
        }
        errors = addErrorsIf(z2, errors, "Maximum backoff must be greater than 300s (5 minutes)");
        if (initial >= 30) {
            z = false;
        }
        return addErrorsIf(z, errors, "Initial backoff must be at least 30s");
    }

    private static List<String> validateForPersistence(Bundle extras) {
        List<String> errors = null;
        if (extras != null) {
            for (String k : extras.keySet()) {
                errors = addError(errors, validateExtrasType(extras, k));
            }
        }
        return errors;
    }

    private static List<String> validateForTransport(Bundle extras) {
        if (extras == null || measureBundleSize(extras) <= 10240) {
            return null;
        }
        return getMutableSingletonList(String.format(Locale.US, "Extras too large: %d bytes is > the max (%d bytes)", new Object[]{Integer.valueOf(measureBundleSize(extras)), Integer.valueOf(10240)}));
    }

    private static String validateExtrasType(Bundle extras, String key) {
        String str = null;
        Object o = extras.get(key);
        if (o == null || (o instanceof Integer) || (o instanceof Long) || (o instanceof Double) || (o instanceof String) || (o instanceof Boolean)) {
            return null;
        }
        Locale locale = Locale.US;
        String str2 = "Received value of type '%s' for key '%s', but only the following extra parameter types are supported: Integer, Long, Double, String, and Boolean";
        Object[] objArr = new Object[2];
        if (o != null) {
            str = o.getClass();
        }
        objArr[0] = str;
        objArr[1] = key;
        return String.format(locale, str2, objArr);
    }

    List<String> validateService(String service) {
        if (service == null || service.isEmpty()) {
            return getMutableSingletonList("Service can't be empty");
        }
        if (this.context == null) {
            return getMutableSingletonList("Context is null, can't query PackageManager");
        }
        PackageManager pm = this.context.getPackageManager();
        if (pm == null) {
            return getMutableSingletonList("PackageManager is null, can't validate service");
        }
        Intent executeIntent = new Intent("com.firebase.jobdispatcher.ACTION_EXECUTE");
        executeIntent.setClassName(this.context, service);
        List<ResolveInfo> intentServices = pm.queryIntentServices(executeIntent, 0);
        if (intentServices == null || intentServices.isEmpty()) {
            Log.e("FJD.GooglePlayReceiver", "Couldn't find a registered service with the name " + service + ". Is it declared in the manifest with the right intent-filter? If not, the job won't be started.");
            return null;
        }
        for (ResolveInfo info : intentServices) {
            if (info.serviceInfo != null && info.serviceInfo.enabled) {
                return null;
            }
        }
        return getMutableSingletonList(service + " is disabled.");
    }

    private static List<String> validateTag(String tag) {
        if (tag == null) {
            return getMutableSingletonList("Tag can't be null");
        }
        if (tag.length() > 100) {
            return getMutableSingletonList("Tag must be shorter than 100");
        }
        return null;
    }

    private static List<String> getMutableSingletonList(String msg) {
        ArrayList<String> strings = new ArrayList();
        strings.add(msg);
        return strings;
    }
}
