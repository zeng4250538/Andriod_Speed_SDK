package com.example.zengzhaolin.myapplication;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zengzhaolin on 2016/12/18.
 */

public class RequestHelper {

    public static String GET(String path) {
        HttpURLConnection urlConn = null;
        try {
            URL url = new URL(path);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(5 * 1000);
            urlConn.connect();
            int code = urlConn.getResponseCode();
            if (code == 200 || code == 201 || code ==204) {
                return streamToString(urlConn.getInputStream())+"code为:"+code;
            } else {
                return "网络访问失败";
            }
        } catch (IOException e) {
            return e.getMessage();
        } finally {
            urlConn.disconnect();
        }
    }

    public static String POST(String path, String param , String privateip) {
        HttpURLConnection urlConn = null;

        try {
            URL url = new URL(path);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(5 * 1000);
            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("Accept","application/json");
            urlConn.setRequestProperty("Content-Type","application/json");
            urlConn.setRequestProperty("X-Wsse","UsernameToken Username=\"user1\",PasswordDigest=\"7C4A8D09CA3762AF61E59520943DC26494F8941B\",Nonce=\"eUZZZXpSczFycXJCNVhCWU1mS3ZScldOYg==\", Timestamp=\"2013-09-05T02:12:21Z\"");
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);
            urlConn.setUseCaches(false);

            OutputStream os = urlConn.getOutputStream();

            if (param.length() > 0) {
                os.write((param.getBytes()));
                os.flush();
            }
            int code = urlConn.getResponseCode();
            if (code == 200 || code == 201 || code ==204) {
                InputStream is = urlConn.getInputStream();

                return streamToString(is);
            } else {
                String stringCode = Integer.toString(code);
                String po = urlConn.getResponseMessage();
                Log.i("Info","请求异常"+po+stringCode);
                return "请求异常";
            }
        } catch (Exception e) {
            Log.i("Info","访问网络失败");
            return "访问网络失败";
        } finally {
            urlConn.disconnect();
        }
    }

    public static String DELETE(String path) {

        URL url = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException exception) {
            exception.printStackTrace();
        }
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Accept","application/json");
            httpURLConnection.setRequestProperty("Content-Type","application/json");
            httpURLConnection.setRequestProperty("User-Agent","Go-http-client/1.1");
            httpURLConnection.setRequestProperty("Authorization",
                    "WSSE realm=\"QoS\", profile=\"UsernameToken\"");
            httpURLConnection.setRequestProperty("X-Wsse","UsernameToken Username=\"user1\",PasswordDigest=\"7C4A8D09CA3762AF61E59520943DC26494F8941B\",Nonce=\"eUZZZXpSczFycXJCNVhCWU1mS3ZScldOYg==\", Timestamp=\"2013-09-05T02:12:21Z\"");
            httpURLConnection.setRequestMethod("DELETE");
            int code = httpURLConnection.getResponseCode();
            if (code == 200 || code == 201 || code ==204) {
                InputStream is = httpURLConnection.getInputStream();
                return streamToString(is)+code;
            } else {
                return "网络访问失败"+code;
            }
        } catch (IOException exception) {
             exception.printStackTrace();
            return exception.getMessage();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

    }

    private static String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            return null;
        }
    }
}
