package com.coinbase.android.ui;

import com.coinbase.android.ui.ModalBottomNavigationItem.Type;
import rx.functions.Func0;

final class AutoValue_ModalBottomNavigationItem extends ModalBottomNavigationItem {
    private final Runnable clearHasNotificationFunc;
    private final Func0<Boolean> hasNotificationFunc;
    private final int icon;
    private final int iconNotification;
    private final int title;
    private final Type type;

    static final class Builder extends com.coinbase.android.ui.ModalBottomNavigationItem.Builder {
        private Runnable clearHasNotificationFunc;
        private Func0<Boolean> hasNotificationFunc;
        private Integer icon;
        private Integer iconNotification;
        private Integer title;
        private Type type;

        Builder() {
        }

        Builder(ModalBottomNavigationItem source) {
            this.title = Integer.valueOf(source.getTitle());
            this.iconNotification = Integer.valueOf(source.getIconNotification());
            this.hasNotificationFunc = source.getHasNotificationFunc();
            this.clearHasNotificationFunc = source.getClearHasNotificationFunc();
            this.type = source.getType();
            this.icon = Integer.valueOf(source.getIcon());
        }

        public com.coinbase.android.ui.ModalBottomNavigationItem.Builder setTitle(int title) {
            this.title = Integer.valueOf(title);
            return this;
        }

        public com.coinbase.android.ui.ModalBottomNavigationItem.Builder setIconNotification(int iconNotification) {
            this.iconNotification = Integer.valueOf(iconNotification);
            return this;
        }

        public com.coinbase.android.ui.ModalBottomNavigationItem.Builder setHasNotificationFunc(Func0<Boolean> hasNotificationFunc) {
            this.hasNotificationFunc = hasNotificationFunc;
            return this;
        }

        public com.coinbase.android.ui.ModalBottomNavigationItem.Builder setClearHasNotificationFunc(Runnable clearHasNotificationFunc) {
            this.clearHasNotificationFunc = clearHasNotificationFunc;
            return this;
        }

        public com.coinbase.android.ui.ModalBottomNavigationItem.Builder setType(Type type) {
            this.type = type;
            return this;
        }

        public com.coinbase.android.ui.ModalBottomNavigationItem.Builder setIcon(int icon) {
            this.icon = Integer.valueOf(icon);
            return this;
        }

        public ModalBottomNavigationItem build() {
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
            if (this.type == null) {
                missing = missing + " type";
            }
            if (this.icon == null) {
                missing = missing + " icon";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ModalBottomNavigationItem(this.title.intValue(), this.iconNotification.intValue(), this.hasNotificationFunc, this.clearHasNotificationFunc, this.type, this.icon.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    private AutoValue_ModalBottomNavigationItem(int title, int iconNotification, Func0<Boolean> hasNotificationFunc, Runnable clearHasNotificationFunc, Type type, int icon) {
        this.title = title;
        this.iconNotification = iconNotification;
        this.hasNotificationFunc = hasNotificationFunc;
        this.clearHasNotificationFunc = clearHasNotificationFunc;
        this.type = type;
        this.icon = icon;
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

    public Type getType() {
        return this.type;
    }

    public int getIcon() {
        return this.icon;
    }

    public String toString() {
        return "ModalBottomNavigationItem{title=" + this.title + ", iconNotification=" + this.iconNotification + ", hasNotificationFunc=" + this.hasNotificationFunc + ", clearHasNotificationFunc=" + this.clearHasNotificationFunc + ", type=" + this.type + ", icon=" + this.icon + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ModalBottomNavigationItem)) {
            return false;
        }
        ModalBottomNavigationItem that = (ModalBottomNavigationItem) o;
        if (this.title == that.getTitle() && this.iconNotification == that.getIconNotification() && this.hasNotificationFunc.equals(that.getHasNotificationFunc()) && this.clearHasNotificationFunc.equals(that.getClearHasNotificationFunc()) && this.type.equals(that.getType()) && this.icon == that.getIcon()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((1 * 1000003) ^ this.title) * 1000003) ^ this.iconNotification) * 1000003) ^ this.hasNotificationFunc.hashCode()) * 1000003) ^ this.clearHasNotificationFunc.hashCode()) * 1000003) ^ this.type.hashCode()) * 1000003) ^ this.icon;
    }
}
