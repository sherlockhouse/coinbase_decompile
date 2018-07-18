package android.support.v7.widget;

import android.content.res.Resources.Theme;
import android.widget.SpinnerAdapter;

public interface ThemedSpinnerAdapter extends SpinnerAdapter {
    Theme getDropDownViewTheme();

    void setDropDownViewTheme(Theme theme);
}
