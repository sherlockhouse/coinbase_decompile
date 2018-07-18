package com.coinbase.android.signin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.Constants;
import com.coinbase.android.modalAlerts.ModalRouter;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.config.Config;
import com.coinbase.api.internal.models.config.Data;
import com.coinbase.api.internal.models.config.Message;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Observable;
import rx.functions.Action0;

@ActivityScope
public class LaunchMessageModalRouter implements ModalRouter {
    private final AppCompatActivity mActivity;
    private final LoginManager mLoginManager;

    @Inject
    public LaunchMessageModalRouter(AppCompatActivity activity, LoginManager loginManager) {
        this.mActivity = activity;
        this.mLoginManager = loginManager;
    }

    public Observable<Action0> route() {
        return this.mLoginManager.getClient().getConfigRx().first().map(LaunchMessageModalRouter$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ Action0 lambda$route$1(LaunchMessageModalRouter this_, Pair pair) {
        Response<Config> response = pair.first;
        if (!response.isSuccessful()) {
            return null;
        }
        Data config = ((Config) response.body()).getData();
        if (config == null) {
            return null;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this_.mActivity);
        boolean isDismissible = true;
        String currentVersion = null;
        try {
            currentVersion = this_.mActivity.getPackageManager().getPackageInfo(this_.mActivity.getPackageName(), 0).versionName;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        if (currentVersion == null) {
            return null;
        }
        Message version = null;
        Message message = null;
        Message result = null;
        if (config.getAndroid() == null) {
            return null;
        }
        if (config.getAndroid().getVersion() != null) {
            version = config.getAndroid().getVersion();
        }
        if (config.getAndroid().getMessage() != null) {
            message = config.getAndroid().getMessage();
        }
        if (version != null && version.getMinVersion() != null && Utils.compareVersions(currentVersion, version.getMinVersion()) < 0) {
            result = version;
            isDismissible = false;
        } else if (message != null && message.getVersionRange() != null && message.getVersionRange().getMin() != null && message.getVersionRange().getMax() != null && Utils.isBetweenVersions(currentVersion, message.getVersionRange().getMin(), message.getVersionRange().getMax()) && !this_.isConfigMessageCached(this_.mActivity, message.getDescription())) {
            result = message;
            preferences.edit().putInt(Constants.LAUNCH_MESSAGE_HASHCODE, message.getDescription().hashCode()).apply();
        } else if (!(message == null || Utils.compareVersions(currentVersion, message.getVersion()) != 0 || this_.isConfigMessageCached(this_.mActivity, message.getDescription()))) {
            result = message;
            preferences.edit().putInt(Constants.LAUNCH_MESSAGE_HASHCODE, message.getDescription().hashCode()).apply();
        }
        if (result != null) {
            return LaunchMessageModalRouter$$Lambda$2.lambdaFactory$(this_, result, isDismissible);
        }
        return null;
    }

    static /* synthetic */ void lambda$null$0(LaunchMessageModalRouter this_, Message finalResult, boolean finalDismissible) {
        Intent intent = new Intent(this_.mActivity, LaunchMessageActivity.class);
        intent.putExtra(LaunchMessageActivity.TITLE, finalResult.getTitle());
        intent.putExtra(LaunchMessageActivity.DESCRIPTION, finalResult.getDescription());
        intent.putExtra(LaunchMessageActivity.URL, finalResult.getLink());
        intent.putExtra(LaunchMessageActivity.DISMISSIBLE, finalDismissible);
        intent.putExtra(LaunchMessageActivity.ACTION, finalResult.getAction());
        intent.putExtra(LaunchMessageActivity.IMAGE, finalResult.getImage());
        this_.mActivity.startActivity(intent);
    }

    private boolean isConfigMessageCached(Context context, String description) {
        if (PreferenceManager.getDefaultSharedPreferences(context).getInt(Constants.LAUNCH_MESSAGE_HASHCODE, 0) == description.hashCode()) {
            return true;
        }
        return false;
    }
}
