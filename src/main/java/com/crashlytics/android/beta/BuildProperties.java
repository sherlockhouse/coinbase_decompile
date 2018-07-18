package com.crashlytics.android.beta;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class BuildProperties {
    public final String buildId;
    public final String packageName;
    public final String versionCode;
    public final String versionName;

    BuildProperties(String versionCode, String versionName, String buildId, String packageName) {
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.buildId = buildId;
        this.packageName = packageName;
    }

    public static BuildProperties fromProperties(Properties props) {
        return new BuildProperties(props.getProperty("version_code"), props.getProperty("version_name"), props.getProperty("build_id"), props.getProperty("package_name"));
    }

    public static BuildProperties fromPropertiesStream(InputStream is) throws IOException {
        Properties props = new Properties();
        props.load(is);
        return fromProperties(props);
    }
}
