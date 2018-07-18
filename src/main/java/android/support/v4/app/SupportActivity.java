package android.support.v4.app;

import android.app.Activity;
import android.support.v4.util.SimpleArrayMap;

public class SupportActivity extends Activity {
    private SimpleArrayMap<Class<? extends ExtraData>, ExtraData> mExtraDataMap = new SimpleArrayMap();

    public static class ExtraData {
    }

    public void putExtraData(ExtraData extraData) {
        this.mExtraDataMap.put(extraData.getClass(), extraData);
    }

    public <T extends ExtraData> T getExtraData(Class<T> extraDataClass) {
        return (ExtraData) this.mExtraDataMap.get(extraDataClass);
    }
}
