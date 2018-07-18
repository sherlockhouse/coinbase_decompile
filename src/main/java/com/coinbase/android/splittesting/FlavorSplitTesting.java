package com.coinbase.android.splittesting;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.coinbase.v2.models.user.SplitTest;

public abstract class FlavorSplitTesting {
    final SharedPreferences mSharedPrefs;

    public abstract SplitTest getValue(String str);

    FlavorSplitTesting(SharedPreferences sharedPrefs) {
        this.mSharedPrefs = sharedPrefs;
    }

    public boolean isInGroup(String splitTestName, String groupName) {
        return TextUtils.equals(getValue(splitTestName).getGroup(), groupName);
    }
}
