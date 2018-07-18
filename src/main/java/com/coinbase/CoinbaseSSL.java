package com.coinbase;

import javax.net.ssl.SSLContext;

public class CoinbaseSSL {
    private static SSLContext sslContext = null;

    public static synchronized javax.net.ssl.SSLContext getSSLContext() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.coinbase.CoinbaseSSL.getSSLContext():javax.net.ssl.SSLContext. bs: [B:15:0x0050, B:36:0x0079]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r6 = com.coinbase.CoinbaseSSL.class;
        monitor-enter(r6);
        r5 = sslContext;	 Catch:{ all -> 0x006c }
        if (r5 == 0) goto L_0x000b;	 Catch:{ all -> 0x006c }
    L_0x0007:
        r5 = sslContext;	 Catch:{ all -> 0x006c }
    L_0x0009:
        monitor-exit(r6);
        return r5;
    L_0x000b:
        r3 = 0;
        r4 = 0;
        r5 = "java.vm.name";	 Catch:{ Exception -> 0x006f }
        r5 = java.lang.System.getProperty(r5);	 Catch:{ Exception -> 0x006f }
        r7 = "Dalvik";	 Catch:{ Exception -> 0x006f }
        r5 = r5.equalsIgnoreCase(r7);	 Catch:{ Exception -> 0x006f }
        if (r5 == 0) goto L_0x0056;	 Catch:{ Exception -> 0x006f }
    L_0x001b:
        r5 = com.coinbase.CoinbaseSSL.class;	 Catch:{ Exception -> 0x006f }
        r7 = "/com/coinbase/api/ca-coinbase.bks";	 Catch:{ Exception -> 0x006f }
        r4 = r5.getResourceAsStream(r7);	 Catch:{ Exception -> 0x006f }
        r5 = "BKS";	 Catch:{ Exception -> 0x006f }
        r3 = java.security.KeyStore.getInstance(r5);	 Catch:{ Exception -> 0x006f }
    L_0x0029:
        r5 = "changeit";	 Catch:{ Exception -> 0x006f }
        r5 = r5.toCharArray();	 Catch:{ Exception -> 0x006f }
        r3.load(r4, r5);	 Catch:{ Exception -> 0x006f }
        r5 = javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm();	 Catch:{ Exception -> 0x006f }
        r2 = javax.net.ssl.TrustManagerFactory.getInstance(r5);	 Catch:{ Exception -> 0x006f }
        r2.init(r3);	 Catch:{ Exception -> 0x006f }
        r5 = "TLS";	 Catch:{ Exception -> 0x006f }
        r0 = javax.net.ssl.SSLContext.getInstance(r5);	 Catch:{ Exception -> 0x006f }
        r5 = 0;	 Catch:{ Exception -> 0x006f }
        r7 = r2.getTrustManagers();	 Catch:{ Exception -> 0x006f }
        r8 = 0;	 Catch:{ Exception -> 0x006f }
        r0.init(r5, r7, r8);	 Catch:{ Exception -> 0x006f }
        sslContext = r0;	 Catch:{ Exception -> 0x006f }
        if (r4 == 0) goto L_0x0053;
    L_0x0050:
        r4.close();	 Catch:{ IOException -> 0x0065 }
    L_0x0053:
        r5 = sslContext;	 Catch:{ all -> 0x006c }
        goto L_0x0009;
    L_0x0056:
        r5 = com.coinbase.CoinbaseSSL.class;	 Catch:{ Exception -> 0x006f }
        r7 = "/com/coinbase/api/ca-coinbase.jks";	 Catch:{ Exception -> 0x006f }
        r4 = r5.getResourceAsStream(r7);	 Catch:{ Exception -> 0x006f }
        r5 = "JKS";	 Catch:{ Exception -> 0x006f }
        r3 = java.security.KeyStore.getInstance(r5);	 Catch:{ Exception -> 0x006f }
        goto L_0x0029;
    L_0x0065:
        r1 = move-exception;
        r5 = new java.lang.RuntimeException;	 Catch:{ all -> 0x006c }
        r5.<init>(r1);	 Catch:{ all -> 0x006c }
        throw r5;	 Catch:{ all -> 0x006c }
    L_0x006c:
        r5 = move-exception;
        monitor-exit(r6);
        throw r5;
    L_0x006f:
        r1 = move-exception;
        r5 = new java.lang.RuntimeException;	 Catch:{ all -> 0x0076 }
        r5.<init>(r1);	 Catch:{ all -> 0x0076 }
        throw r5;	 Catch:{ all -> 0x0076 }
    L_0x0076:
        r5 = move-exception;
        if (r4 == 0) goto L_0x007c;
    L_0x0079:
        r4.close();	 Catch:{ IOException -> 0x007d }
    L_0x007c:
        throw r5;	 Catch:{ all -> 0x006c }
    L_0x007d:
        r1 = move-exception;	 Catch:{ all -> 0x006c }
        r5 = new java.lang.RuntimeException;	 Catch:{ all -> 0x006c }
        r5.<init>(r1);	 Catch:{ all -> 0x006c }
        throw r5;	 Catch:{ all -> 0x006c }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coinbase.CoinbaseSSL.getSSLContext():javax.net.ssl.SSLContext");
    }
}
