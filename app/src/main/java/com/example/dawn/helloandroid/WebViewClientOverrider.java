package com.example.dawn.helloandroid;

import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Dawn on 2014/8/20.
 */
public class WebViewClientOverrider extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        Log.d("info-Incercept", url);

        Repository repository = Repository.getInstance();
        OkHttpClient client = repository.getHttpClient();

        // currently we only intercept request from our site
        if (url.contains(repository.getBASIC_HOST())){
            //if (Util.isPlainTextUrl(url)) {
                Log.d("info-handled", url);
                try {
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = client.newCall(request).execute();

                    String mimeType = "text/html";
                    String encoding = "utf-8";

                    // check response header to find correct mimeType
                    String headerContentType = response.header("Content-Type");
                    if (headerContentType.contains(";")) {
                        String[] typeArray = headerContentType.split(";");
                        mimeType = typeArray[0];

                    }
                    else {
                        mimeType = headerContentType;
                    }

                    // if server doesn't send mimeType correctly, we do some check on url
                    // but there is a lot of mimeTypes and we are not write a browser!
                    if (Util.isUrlContainsImagePostfix(url))
                        mimeType = "image/" + url.substring(url.lastIndexOf(".") + 1);

                    if (Util.isUrlContainsJs(url))
                        mimeType = "text/css";

                    if (Util.isUrlContainsJs(url))
                        mimeType = "text/javascript";

                    Log.d("info-mimeType", mimeType);
                    Log.d("info-encoding", encoding);

                    return new WebResourceResponse(mimeType,
                            encoding, response.body().byteStream());

                } catch (IOException e) {
                    String msg = e.getMessage();
                    if (msg == null)
                        msg = "Unknown Error!";
                    Log.e("Error", msg);
                }
            //}
        }
        return super.shouldInterceptRequest(view, url);
    }
}
