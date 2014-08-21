package com.example.dawn.helloandroid;

/**
 * Created by Dawn on 2014/8/20.
 */
public class Util {
    public static boolean isUrlContainsImagePostfix(String url){
        return url.endsWith(".jpg") || url.endsWith(".jpeg")
                || url.endsWith(".gif") || url.endsWith(".png");
    }

    public static boolean isUrlContainsCSS(String url){
        return url.endsWith(".css");
    }

    public static boolean isUrlContainsJs(String url){
        return url.endsWith(".js");
    }

    public static boolean isPlainTextUrl(String url){
        return !isUrlContainsImagePostfix(url) && !isUrlContainsCSS(url) && !isUrlContainsJs(url);
    }
}
