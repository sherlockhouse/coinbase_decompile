package com.tenjin.android;

import android.net.Uri;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.Log;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

class HttpConnection {
    HttpConnection() {
    }

    boolean connect(String url, Map<String, String> params, Map<String, String> headers) {
        Log.d("HttpConnection", "--------------------");
        boolean result = false;
        InputStream inputStream = null;
        HttpURLConnection con = null;
        try {
            con = getConnection(new URL(url), "POST", params, headers);
            if (con == null) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                return 0;
            }
            int status = con.getResponseCode();
            if (Callback.DEFAULT_DRAG_ANIMATION_DURATION > status || status > 299) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                return 0;
            }
            inputStream = con.getInputStream();
            if (inputStream == null) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                return 0;
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            if (rd == null) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                return 0;
            }
            StringBuilder sb = new StringBuilder();
            while (true) {
                String inputLine = rd.readLine();
                if (inputLine == null) {
                    break;
                }
                sb.append(inputLine + "\n");
            }
            String response = sb.toString();
            if (rd != null) {
                rd.close();
            }
            Log.d("HttpConnection", "Tenjin::connect response: " + response);
            if (status == Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
                result = true;
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2222) {
                    e2222.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
            Log.d("HttpConnection", "--------------------");
            return result;
        } catch (Exception e3) {
            e3.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e22222) {
                    e22222.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e222222) {
                    e222222.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
    }

    String getUser(String url, Map<String, String> params) {
        Log.d("HttpConnection", "--------------------");
        String result = null;
        InputStream inputStream = null;
        HttpURLConnection con = null;
        try {
            con = getConnection(new URL(url + "?" + convertMapToString(params, true)));
            if (con == null) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                return null;
            }
            int status = con.getResponseCode();
            if (Callback.DEFAULT_DRAG_ANIMATION_DURATION > status || status > 299) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                return null;
            }
            inputStream = con.getInputStream();
            if (inputStream == null) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                return null;
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            if (rd == null) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                return null;
            }
            StringBuilder sb = new StringBuilder();
            while (true) {
                String inputLine = rd.readLine();
                if (inputLine == null) {
                    break;
                }
                sb.append(inputLine + "\n");
            }
            result = sb.toString();
            if (rd != null) {
                rd.close();
            }
            Log.d("HttpConnection", "Tenjin::getUser response: " + result);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2222) {
                    e2222.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
            Log.d("HttpConnection", "--------------------");
            return result;
        } catch (Exception e3) {
            e3.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e22222) {
                    e22222.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e222222) {
                    e222222.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
    }

    private HttpURLConnection getConnection(URL entries) throws InterruptedException, IOException {
        return getConnection(entries, "GET", null, null);
    }

    private HttpURLConnection getConnection(URL entries, String requestMethod, Map<String, String> params, Map<String, String> headers) throws InterruptedException, IOException {
        HttpURLConnection connection;
        int retry_count = 0;
        boolean delay = false;
        do {
            if (delay) {
                Thread.sleep((long) (retry_count * 1000));
            }
            connection = (HttpURLConnection) entries.openConnection();
            if (headers != null && headers.size() > 0) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
            }
            if (requestMethod == "POST") {
                String urlParameters = convertMapToString(params, false);
                byte[] contentSize = urlParameters.getBytes();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setConnectTimeout(0);
                connection.setReadTimeout(0);
                connection.setChunkedStreamingMode(0);
                connection.setInstanceFollowRedirects(false);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("charset", "utf-8");
                if (contentSize.length > 0) {
                    connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
                }
                connection.setUseCaches(false);
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.write(urlParameters.getBytes("UTF-8"));
                wr.flush();
                wr.close();
            } else {
                connection.setRequestMethod("GET");
            }
            int status = connection.getResponseCode();
            if (status == Callback.DEFAULT_DRAG_ANIMATION_DURATION || status == 202 || status == 204) {
                break;
            }
            connection.disconnect();
            retry_count++;
            delay = true;
        } while (retry_count < 10);
        return connection;
    }

    static String convertMapToString(Map<String, String> source, boolean encode) {
        String result = "";
        if (source != null) {
            for (Entry<String, String> entry : source.entrySet()) {
                if (result.length() > 0) {
                    result = result + "&";
                }
                if (encode) {
                    result = result + Uri.encode((String) entry.getKey()) + "=" + Uri.encode((String) entry.getValue());
                } else {
                    result = result + ((String) entry.getKey()) + "=" + ((String) entry.getValue());
                }
            }
        }
        return result;
    }
}
