package com.coinbase.android.ui;

import rx.functions.Func0;

public abstract class BottomNavigationItem extends NavigationItem {
    private boolean mIsDisabled = true;

    public static abstract class Builder {
        public abstract BottomNavigationItem build();

        public abstract Builder setClearHasNotificationFunc(Runnable runnable);

        public abstract Builder setHasNotificationFunc(Func0<Boolean> func0);

        public abstract Builder setIconDisabled(int i);

        public abstract Builder setIconEnabled(int i);

        public abstract Builder setIconEnabledNotification(int i);

        public abstract Builder setIconNotification(int i);

        public abstract Builder setTitle(int i);

        public abstract Builder setType(Type type);
    }

    public enum Type {
        DASHBOARD(0),
        ACCOUNTS(1),
        ALERTS(3),
        MORE(4);
        
        private final int mValue;

        private Type(int value) {
            this.mValue = value;
        }

        public int getValue() {
            return this.mValue;
        }

        public static Type fromValue(int value) {
            for (Type type : values()) {
                if (type.getValue() == value) {
                    return type;
                }
            }
            return DASHBOARD;
        }
    }

    public abstract int getIconDisabled();

    public abstract int getIconEnabled();

    public abstract int getIconEnabledNotification();

    public abstract Type getType();

    public boolean isDisabled() {
        return this.mIsDisabled;
    }

    public void setDisabled(boolean isDisabled) {
        this.mIsDisabled = isDisabled;
    }

    public static Builder builder() {
        return new Builder();
    }
}
