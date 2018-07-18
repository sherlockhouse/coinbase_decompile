package com.coinbase.android.ui;

import com.coinbase.android.ui.BottomNavigationItem.Type;
import rx.functions.Func0;

final class AutoValue_BottomNavigationItem extends BottomNavigationItem {
    private final Runnable clearHasNotificationFunc;
    private final Func0<Boolean> hasNotificationFunc;
    private final int iconDisabled;
    private final int iconEnabled;
    private final int iconEnabledNotification;
    private final int iconNotification;
    private final int title;
    private final Type type;

    static final class Builder extends com.coinbase.android.ui.BottomNavigationItem.Builder {
        private Runnable clearHasNotificationFunc;
        private Func0<Boolean> hasNotificationFunc;
        private Integer iconDisabled;
        private Integer iconEnabled;
        private Integer iconEnabledNotification;
        private Integer iconNotification;
        private Integer title;
        private Type type;

        Builder() {
        }

        Builder(BottomNavigationItem source) {
            this.title = Integer.valueOf(source.getTitle());
            this.iconNotification = Integer.valueOf(source.getIconNotification());
            this.hasNotificationFunc = source.getHasNotificationFunc();
            this.clearHasNotificationFunc = source.getClearHasNotificationFunc();
            this.iconDisabled = Integer.valueOf(source.getIconDisabled());
            this.iconEnabled = Integer.valueOf(source.getIconEnabled());
            this.iconEnabledNotification = Integer.valueOf(source.getIconEnabledNotification());
            this.type = source.getType();
        }

        public com.coinbase.android.ui.BottomNavigationItem.Builder setTitle(int title) {
            this.title = Integer.valueOf(title);
            return this;
        }

        public com.coinbase.android.ui.BottomNavigationItem.Builder setIconNotification(int iconNotification) {
            this.iconNotification = Integer.valueOf(iconNotification);
            return this;
        }

        public com.coinbase.android.ui.BottomNavigationItem.Builder setHasNotificationFunc(Func0<Boolean> hasNotificationFunc) {
            this.hasNotificationFunc = hasNotificationFunc;
            return this;
        }

        public com.coinbase.android.ui.BottomNavigationItem.Builder setClearHasNotificationFunc(Runnable clearHasNotificationFunc) {
            this.clearHasNotificationFunc = clearHasNotificationFunc;
            return this;
        }

        public com.coinbase.android.ui.BottomNavigationItem.Builder setIconDisabled(int iconDisabled) {
            this.iconDisabled = Integer.valueOf(iconDisabled);
            return this;
        }

        public com.coinbase.android.ui.BottomNavigationItem.Builder setIconEnabled(int iconEnabled) {
            this.iconEnabled = Integer.valueOf(iconEnabled);
            return this;
        }

        public com.coinbase.android.ui.BottomNavigationItem.Builder setIconEnabledNotification(int iconEnabledNotification) {
            this.iconEnabledNotification = Integer.valueOf(iconEnabledNotification);
            return this;
        }

        public com.coinbase.android.ui.BottomNavigationItem.Builder setType(Type type) {
            this.type = type;
            return this;
        }

        public BottomNavigationItem build() {
            String missing = "";
            if (this.title == null) {
                missing = missing + " title";
            }
            if (this.iconNotification == null) {
                missing = missing + " iconNotification";
            }
            if (this.hasNotificationFunc == null) {
                missing = missing + " hasNotificationFunc";
            }
            if (this.clearHasNotificationFunc == null) {
                missing = missing + " clearHasNotificationFunc";
            }
            if (this.iconDisabled == null) {
                missing = missing + " iconDisabled";
            }
            if (this.iconEnabled == null) {
                missing = missing + " iconEnabled";
            }
            if (this.iconEnabledNotification == null) {
                missing = missing + " iconEnabledNotification";
            }
            if (this.type == null) {
                missing = missing + " type";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BottomNavigationItem(this.title.intValue(), this.iconNotification.intValue(), this.hasNotificationFunc, this.clearHasNotificationFunc, this.iconDisabled.intValue(), this.iconEnabled.intValue(), this.iconEnabledNotification.intValue(), this.type);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    private AutoValue_BottomNavigationItem(int title, int iconNotification, Func0<Boolean> hasNotificationFunc, Runnable clearHasNotificationFunc, int iconDisabled, int iconEnabled, int iconEnabledNotification, Type type) {
        this.title = title;
        this.iconNotification = iconNotification;
        this.hasNotificationFunc = hasNotificationFunc;
        this.clearHasNotificationFunc = clearHasNotificationFunc;
        this.iconDisabled = iconDisabled;
        this.iconEnabled = iconEnabled;
        this.iconEnabledNotification = iconEnabledNotification;
        this.type = type;
    }

    public int getTitle() {
        return this.title;
    }

    public int getIconNotification() {
        return this.iconNotification;
    }

    Func0<Boolean> getHasNotificationFunc() {
        return this.hasNotificationFunc;
    }

    Runnable getClearHasNotificationFunc() {
        return this.clearHasNotificationFunc;
    }

    public int getIconDisabled() {
        return this.iconDisabled;
    }

    public int getIconEnabled() {
        return this.iconEnabled;
    }

    public int getIconEnabledNotification() {
        return this.iconEnabledNotification;
    }

    public Type getType() {
        return this.type;
    }

    public String toString() {
        return "BottomNavigationItem{title=" + this.title + ", iconNotification=" + this.iconNotification + ", hasNotificationFunc=" + this.hasNotificationFunc + ", clearHasNotificationFunc=" + this.clearHasNotificationFunc + ", iconDisabled=" + this.iconDisabled + ", iconEnabled=" + this.iconEnabled + ", iconEnabledNotification=" + this.iconEnabledNotification + ", type=" + this.type + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BottomNavigationItem)) {
            return false;
        }
        BottomNavigationItem that = (BottomNavigationItem) o;
        if (this.title == that.getTitle() && this.iconNotification == that.getIconNotification() && this.hasNotificationFunc.equals(that.getHasNotificationFunc()) && this.clearHasNotificationFunc.equals(that.getClearHasNotificationFunc()) && this.iconDisabled == that.getIconDisabled() && this.iconEnabled == that.getIconEnabled() && this.iconEnabledNotification == that.getIconEnabledNotification() && this.type.equals(that.getType())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((1 * 1000003) ^ this.title) * 1000003) ^ this.iconNotification) * 1000003) ^ this.hasNotificationFunc.hashCode()) * 1000003) ^ this.clearHasNotificationFunc.hashCode()) * 1000003) ^ this.iconDisabled) * 1000003) ^ this.iconEnabled) * 1000003) ^ this.iconEnabledNotification) * 1000003) ^ this.type.hashCode();
    }
}
