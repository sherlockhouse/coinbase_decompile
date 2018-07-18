package com.coinbase.zxing.client.android.common.executor;

import com.coinbase.zxing.client.android.common.PlatformSupportManager;

public final class AsyncTaskExecManager extends PlatformSupportManager<AsyncTaskExecInterface> {
    public AsyncTaskExecManager() {
        super(AsyncTaskExecInterface.class, new DefaultAsyncTaskExecInterface());
        addImplementationClass(11, "com.coinbase.zxing.client.android.common.executor.HoneycombAsyncTaskExecInterface");
    }
}
