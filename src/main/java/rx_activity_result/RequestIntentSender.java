package rx_activity_result;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

public class RequestIntentSender extends Request {
    private final int extraFlags;
    private final Intent fillInIntent;
    private final int flagsMask;
    private final int flagsValues;
    private final IntentSender intentSender;
    private final Bundle options;

    public IntentSender getIntentSender() {
        return this.intentSender;
    }

    public Intent getFillInIntent() {
        return this.fillInIntent;
    }

    public int getFlagsMask() {
        return this.flagsMask;
    }

    public int getFlagsValues() {
        return this.flagsValues;
    }

    public int getExtraFlags() {
        return this.extraFlags;
    }

    public Bundle getOptions() {
        return this.options;
    }
}
