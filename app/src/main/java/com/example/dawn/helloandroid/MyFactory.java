package com.example.dawn.helloandroid;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by Dawn on 2014/8/20.
 */
public class MyFactory {
    // This host for all
    private final String BASIC_HOST = "d2518dpi0ehrmy.cloudfront.net";

    public final String getBASIC_HOST(){
        return BASIC_HOST;
    }

    public final String getBASIC_URL(){
        return "https://" + BASIC_HOST;
    }

    private OkHttpClient httpClient = null;

    public final OkHttpClient getHttpClient(){
        // We just need one client through all application.
        if (httpClient == null){
            httpClient = new OkHttpClient();
            httpClient.setHostResolver(new MyHostResolver());
        }
        return httpClient;
    }

    // only all one instance
    private MyFactory(){
    }

    private static final MyFactory instance = new MyFactory();

    public static final MyFactory getInstance(){
        return instance;
    }
}
