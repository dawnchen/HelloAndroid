package com.example.dawn.helloandroid;

import android.test.InstrumentationTestCase;
import android.util.Log;

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
*/

import com.squareup.okhttp.HostResolver;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import junit.framework.Assert;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Created by Dawn on 2014/8/16.
 */
public class MiscTest extends InstrumentationTestCase {

    /*public void test() throws Exception{
        final int expected = 1;
        final int reality = 5;
        assertEquals(expected, reality);
    }*/

    /*
    public void testHttpsConnection() throws Exception{
        String https_url = "https://www.google.com";
        URL url;
        try {

            url = new URL(https_url);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            print_content(con);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            assertFalse(true);
        } catch (IOException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    private void print_content(HttpsURLConnection con){
        if(con != null){
            try {
                Log.d("Info","****** Content of the URL ********");
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(con.getInputStream()));

                String input;

                while ((input = br.readLine()) != null){
                    Log.d("Info", input);
                }
                br.close();

            } catch (IOException e) {
                Log.d("Error",e.getMessage());
                assertFalse(true);
            }
        }
    }
    */

    /*
    public void testEasyHttpClient() throws Exception{
        try {
            EasyHttpClient client = new EasyHttpClient();
            Log.d("info", client.get("https://74.125.232.16/"));
        }
        catch (Exception e){
            Log.d("Error", e.getMessage());
            assertFalse(true);
        }
    }
    */

    /*
       public void testOkHttpClint() throws Exception{
        try{
            OkHttpClient client = new OkHttpClient();
            MyHostResolver dns = new MyHostResolver();
            client.setHostResolver(dns);

            Request request = new Request.Builder()
                    .url("https://d2518dpi0ehrmy.cloudfront.net")
                    .build();

            Response response = client.newCall(request).execute();
            String body = response.body().string();
            Log.d("info", body);

            request = new Request.Builder()
                    .url("https://d2518dpi0ehrmy.cloudfront.net/weibo/%E5%8D%8E%E5%A4%8F%E6%AD%A3%E9%81%93")
                    .build();
            response = client.newCall(request).execute();
            String body1 = response.body().string();
            Log.d("info", body1);
            Assert.assertNotSame(body, body1);
        }
        catch (Exception e){
            e.printStackTrace();
            assertFalse(true);
        }
    }
    */

    public void testResolveAWorkIP(){
        try{
            OkHttpClient client = new OkHttpClient();
            client.setHostResolver(new HostResolver() {
                @Override
                public InetAddress[] getAllByName(String host) throws UnknownHostException {
                    Log.d("info", host);
                    if (host == null)
                        throw new UnknownHostException();
                    if (host.equals(Repository.getInstance().getBASIC_HOST()))
                        return new InetAddress[]{
                                InetAddress.getByAddress(new byte[]{(byte)192, (byte)168, (byte)3, (byte)1}),
                                InetAddress.getByAddress(new byte[]{(byte)192, (byte)168, (byte)5, (byte)1}),
                                InetAddress.getByAddress(new byte[]{(byte)54, (byte)230, (byte)5, (byte)130}),
                                // we can add more ip here
                        };
                    return HostResolver.DEFAULT.getAllByName(host);
                }
            });

            Request request = new Request.Builder()
                    .url("https://d2518dpi0ehrmy.cloudfront.net")
                    .build();
            Response response = client.newCall(request).execute();
            assertTrue(response.header("Content-Type").contains("text/html"));

        }catch (Exception e){
            e.printStackTrace();
            assertFalse(true);
        }
    }
}
