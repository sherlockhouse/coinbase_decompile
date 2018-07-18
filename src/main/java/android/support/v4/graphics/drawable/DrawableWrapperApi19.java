package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class DrawableWrapperApi19 extends DrawableWrapperApi14 {

    private static class DrawableWrapperStateKitKat extends DrawableWrapperState {
        DrawableWrapperStateKitKat(DrawableWrapperState orig, Resources res) {
            super(orig, res);
        }

        public Drawable newDrawable(Resources res) {
            return new DrawableWrapperApi19(this, res);
        }
    }

    DrawableWrapperApi19(Drawable drawable) {
        super(drawable);
    }

    DrawableWrapperApi19(DrawableWrapperState state, Resources resources) {
        super(state, resources);
    }

    public void setAutoMirrored(boolean mirrored) {
        this.mDrawable.setAutoMirrored(mirrored);
    }

    public boolean isAutoMirrored() {
        return this.mDrawable.isAutoMirrored();
    }

    DrawableWrapperState mutateConstantState() {
        return new DrawableWrapperStateKitKat(this.mState, null);
    }
}
