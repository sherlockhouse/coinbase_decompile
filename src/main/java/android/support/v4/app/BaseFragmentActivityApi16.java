package android.support.v4.app;

import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;

abstract class BaseFragmentActivityApi16 extends BaseFragmentActivityApi14 {
    boolean mStartedActivityFromFragment;

    BaseFragmentActivityApi16() {
    }

    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        if (!(this.mStartedActivityFromFragment || requestCode == -1)) {
            BaseFragmentActivityApi14.checkForValidRequestCode(requestCode);
        }
        super.startActivityForResult(intent, requestCode, options);
    }

    public void startIntentSenderForResult(IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws SendIntentException {
        if (!(this.mStartedIntentSenderFromFragment || requestCode == -1)) {
            BaseFragmentActivityApi14.checkForValidRequestCode(requestCode);
        }
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }
}
