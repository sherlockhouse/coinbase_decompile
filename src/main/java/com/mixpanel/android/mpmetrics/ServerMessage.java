package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

class ServerMessage {
    ServerMessage() {
    }

    public boolean isOnline(Context context) {
        boolean isOnline;
        try {
            NetworkInfo netInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            isOnline = netInfo != null && netInfo.isConnectedOrConnecting();
            if (MPConfig.DEBUG) {
                Log.d("MixpanelAPI", "ConnectivityManager says we " + (isOnline ? "are" : "are not") + " online");
            }
        } catch (SecurityException e) {
            isOnline = true;
            if (MPConfig.DEBUG) {
                Log.d("MixpanelAPI", "Don't have permission to check connectivity, assuming online");
            }
        }
        return isOnline;
    }

    public byte[] getUrls(Context context, String[] urls) {
        int i$;
        byte[] bArr = null;
        if (isOnline(context)) {
            bArr = null;
            String[] arr$ = urls;
            int len$ = arr$.length;
            i$ = 0;
            while (i$ < len$) {
                String url = arr$[i$];
                try {
                    bArr = performRequest(url, null);
                    break;
                } catch (MalformedURLException e) {
                    Log.e("MixpanelAPI", "Cannot interpret " + url + " as a URL.", e);
                } catch (IOException e2) {
                    if (MPConfig.DEBUG) {
                        Log.d("MixpanelAPI", "Cannot get " + url + ".", e2);
                    }
                } catch (OutOfMemoryError e3) {
                    Log.e("MixpanelAPI", "Out of memory when getting to " + url + ".", e3);
                }
            }
        }
        return bArr;
        i$++;
    }

    public byte[] performRequest(String endpointUrl, List<NameValuePair> params) throws IOException {
        Throwable th;
        if (MPConfig.DEBUG) {
            Log.d("MixpanelAPI", "Attempting request to " + endpointUrl);
        }
        byte[] response = null;
        int retries = 0;
        boolean succeeded = false;
        while (retries < 3 && !succeeded) {
            InputStream in = null;
            OutputStream out = null;
            BufferedOutputStream bout = null;
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) new URL(endpointUrl).openConnection();
                connection.setConnectTimeout(2000);
                connection.setReadTimeout(10000);
                if (params != null) {
                    connection.setDoOutput(true);
                    UrlEncodedFormEntity form = new UrlEncodedFormEntity(params, "UTF-8");
                    connection.setRequestMethod("POST");
                    connection.setFixedLengthStreamingMode((int) form.getContentLength());
                    out = connection.getOutputStream();
                    BufferedOutputStream bout2 = new BufferedOutputStream(out);
                    try {
                        form.writeTo(bout2);
                        bout2.close();
                        bout = null;
                        out.close();
                        out = null;
                    } catch (EOFException e) {
                        bout = bout2;
                        try {
                            if (MPConfig.DEBUG) {
                                Log.d("MixpanelAPI", "Failure to connect, likely caused by a known issue with Android lib. Retrying.");
                            }
                            retries++;
                            if (bout != null) {
                                try {
                                    bout.close();
                                } catch (IOException e2) {
                                }
                            }
                            if (out != null) {
                                try {
                                    out.close();
                                } catch (IOException e3) {
                                }
                            }
                            if (in != null) {
                                try {
                                    in.close();
                                } catch (IOException e4) {
                                }
                            }
                            if (connection != null) {
                                connection.disconnect();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bout = bout2;
                    }
                }
                in = connection.getInputStream();
                response = slurp(in);
                in.close();
                in = null;
                succeeded = true;
                if (bout != null) {
                    try {
                        bout.close();
                    } catch (IOException e5) {
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e6) {
                    }
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e7) {
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (EOFException e8) {
                if (MPConfig.DEBUG) {
                    Log.d("MixpanelAPI", "Failure to connect, likely caused by a known issue with Android lib. Retrying.");
                }
                retries++;
                if (bout != null) {
                    bout.close();
                }
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
        return response;
        if (out != null) {
            try {
                out.close();
            } catch (IOException e9) {
            }
        }
        if (in != null) {
            try {
                in.close();
            } catch (IOException e10) {
            }
        }
        if (connection != null) {
            connection.disconnect();
        }
        throw th;
        if (in != null) {
            in.close();
        }
        if (connection != null) {
            connection.disconnect();
        }
        throw th;
        if (connection != null) {
            connection.disconnect();
        }
        throw th;
        if (bout != null) {
            try {
                bout.close();
            } catch (IOException e11) {
            }
        }
        if (out != null) {
            out.close();
        }
        if (in != null) {
            in.close();
        }
        if (connection != null) {
            connection.disconnect();
        }
        throw th;
    }

    private byte[] slurp(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[8192];
        while (true) {
            int nRead = inputStream.read(data, 0, data.length);
            if (nRead != -1) {
                buffer.write(data, 0, nRead);
            } else {
                buffer.flush();
                return buffer.toByteArray();
            }
        }
    }
}
