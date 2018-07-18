package com.coinbase.android.ui;

import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;

final class AutoValue_PageControllerLifeCycle_PageDestination extends PageDestination {
    private final Type bottomNavigationItem;
    private final ActionBarController pushPageController;
    private final boolean shouldReplaceController;
    private final Type sourceBottomNavigationItem;

    static final class Builder extends com.coinbase.android.ui.PageControllerLifeCycle.PageDestination.Builder {
        private Type bottomNavigationItem;
        private ActionBarController pushPageController;
        private Boolean shouldReplaceController;
        private Type sourceBottomNavigationItem;

        Builder() {
        }

        Builder(PageDestination source) {
            this.sourceBottomNavigationItem = source.getSourceBottomNavigationItem();
            this.bottomNavigationItem = source.getBottomNavigationItem();
            this.pushPageController = source.getPushPageController();
            this.shouldReplaceController = Boolean.valueOf(source.getShouldReplaceController());
        }

        public com.coinbase.android.ui.PageControllerLifeCycle.PageDestination.Builder setSourceBottomNavigationItem(Type sourceBottomNavigationItem) {
            this.sourceBottomNavigationItem = sourceBottomNavigationItem;
            return this;
        }

        public com.coinbase.android.ui.PageControllerLifeCycle.PageDestination.Builder setBottomNavigationItem(Type bottomNavigationItem) {
            this.bottomNavigationItem = bottomNavigationItem;
            return this;
        }

        public com.coinbase.android.ui.PageControllerLifeCycle.PageDestination.Builder setPushPageController(ActionBarController pushPageController) {
            this.pushPageController = pushPageController;
            return this;
        }

        public com.coinbase.android.ui.PageControllerLifeCycle.PageDestination.Builder setShouldReplaceController(boolean shouldReplaceController) {
            this.shouldReplaceController = Boolean.valueOf(shouldReplaceController);
            return this;
        }

        public PageDestination build() {
            String missing = "";
            if (this.bottomNavigationItem == null) {
                missing = missing + " bottomNavigationItem";
            }
            if (this.shouldReplaceController == null) {
                missing = missing + " shouldReplaceController";
            }
            if (missing.isEmpty()) {
                return new AutoValue_PageControllerLifeCycle_PageDestination(this.sourceBottomNavigationItem, this.bottomNavigationItem, this.pushPageController, this.shouldReplaceController.booleanValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    private AutoValue_PageControllerLifeCycle_PageDestination(Type sourceBottomNavigationItem, Type bottomNavigationItem, ActionBarController pushPageController, boolean shouldReplaceController) {
        this.sourceBottomNavigationItem = sourceBottomNavigationItem;
        this.bottomNavigationItem = bottomNavigationItem;
        this.pushPageController = pushPageController;
        this.shouldReplaceController = shouldReplaceController;
    }

    public Type getSourceBottomNavigationItem() {
        return this.sourceBottomNavigationItem;
    }

    public Type getBottomNavigationItem() {
        return this.bottomNavigationItem;
    }

    public ActionBarController getPushPageController() {
        return this.pushPageController;
    }

    public boolean getShouldReplaceController() {
        return this.shouldReplaceController;
    }

    public String toString() {
        return "PageDestination{sourceBottomNavigationItem=" + this.sourceBottomNavigationItem + ", bottomNavigationItem=" + this.bottomNavigationItem + ", pushPageController=" + this.pushPageController + ", shouldReplaceController=" + this.shouldReplaceController + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PageDestination)) {
            return false;
        }
        PageDestination that = (PageDestination) o;
        if (this.sourceBottomNavigationItem != null) {
            if (this.sourceBottomNavigationItem.equals(that.getSourceBottomNavigationItem())) {
            }
            return false;
        }
        if (this.bottomNavigationItem.equals(that.getBottomNavigationItem())) {
            if (this.pushPageController != null) {
                if (this.pushPageController.equals(that.getPushPageController())) {
                }
            }
            if (this.shouldReplaceController == that.getShouldReplaceController()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.sourceBottomNavigationItem == null ? 0 : this.sourceBottomNavigationItem.hashCode())) * 1000003) ^ this.bottomNavigationItem.hashCode()) * 1000003;
        if (this.pushPageController != null) {
            i = this.pushPageController.hashCode();
        }
        return ((h ^ i) * 1000003) ^ (this.shouldReplaceController ? 1231 : 1237);
    }
}
