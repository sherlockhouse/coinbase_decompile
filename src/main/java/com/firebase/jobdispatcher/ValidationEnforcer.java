package com.firebase.jobdispatcher;

import android.text.TextUtils;
import java.util.List;

public class ValidationEnforcer implements JobValidator {
    private final JobValidator validator;

    public static final class ValidationException extends RuntimeException {
        private final List<String> errors;

        public ValidationException(String msg, List<String> errors) {
            super(msg + ": " + TextUtils.join("\n  - ", errors));
            this.errors = errors;
        }
    }

    public ValidationEnforcer(JobValidator validator) {
        this.validator = validator;
    }

    public List<String> validate(JobParameters job) {
        return this.validator.validate(job);
    }

    public final void ensureValid(JobParameters job) {
        ensureNoErrors(validate(job));
    }

    private static void ensureNoErrors(List<String> errors) {
        if (errors != null) {
            throw new ValidationException("JobParameters is invalid", errors);
        }
    }
}
