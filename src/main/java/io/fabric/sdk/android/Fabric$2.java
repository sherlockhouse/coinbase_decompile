package io.fabric.sdk.android;

import java.util.concurrent.CountDownLatch;

class Fabric$2 implements InitializationCallback {
    final CountDownLatch kitInitializedLatch = new CountDownLatch(this.val$size);
    final /* synthetic */ Fabric this$0;
    final /* synthetic */ int val$size;

    Fabric$2(Fabric this$0, int i) {
        this.this$0 = this$0;
        this.val$size = i;
    }

    public void success(Object o) {
        this.kitInitializedLatch.countDown();
        if (this.kitInitializedLatch.getCount() == 0) {
            Fabric.access$200(this.this$0).set(true);
            Fabric.access$300(this.this$0).success(this.this$0);
        }
    }

    public void failure(Exception exception) {
        Fabric.access$300(this.this$0).failure(exception);
    }
}
