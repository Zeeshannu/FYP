package com.example.zeeshan.tablename;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpClientWrapper {
    private CookieManager cookieManager = new CookieManager();
    public HttpClientWrapper() {
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        _client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(cookieManager)).build();
    }
    private OkHttpClient _client;

    public String httpGet(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try(Response response = _client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
