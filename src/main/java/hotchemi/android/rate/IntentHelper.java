package hotchemi.android.rate;

import android.content.Context;
import android.content.Intent;

final class IntentHelper {
    static Intent createIntentForGooglePlay(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", UriHelper.getGooglePlay(context.getPackageName()));
        if (UriHelper.isPackageExists(context, "com.android.vending")) {
            intent.setPackage("com.android.vending");
        }
        return intent;
    }

    static Intent createIntentForAmazonAppstore(Context context) {
        return new Intent("android.intent.action.VIEW", UriHelper.getAmazonAppstore(context.getPackageName()));
    }
}
