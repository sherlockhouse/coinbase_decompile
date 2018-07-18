package com.firebase.jobdispatcher;

import java.util.List;

public interface JobValidator {
    List<String> validate(JobParameters jobParameters);
}
