package jp.wasabeef.blurry;

import android.graphics.drawable.BitmapDrawable;
import android.view.ViewGroup;
import jp.wasabeef.blurry.Blurry.Composer;
import jp.wasabeef.blurry.internal.BlurTask.Callback;

class Blurry$Composer$1 implements Callback {
    final /* synthetic */ Composer this$0;
    final /* synthetic */ ViewGroup val$target;

    Blurry$Composer$1(Composer this$0, ViewGroup viewGroup) {
        this.this$0 = this$0;
        this.val$target = viewGroup;
    }

    public void done(BitmapDrawable drawable) {
        Composer.access$100(this.this$0, this.val$target, drawable);
    }
}
