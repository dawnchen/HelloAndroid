package com.example.dawn.helloandroid;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Dawn on 2014/8/20.
 */
public class Repository {
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
    private Repository(){
    }

    private static final Repository instance = new Repository();

    public static final Repository getInstance(){
        return instance;
    }
}
