package com.example.dawn.helloandroid;

/**
 * Created by Dawn on 2014/8/18.
 */

import java.net.InetAddress;
import java.net.UnknownHostException;
import com.squareup.okhttp.HostResolver;

public class MyHostResolver implements HostResolver {
    @Override public InetAddress[] getAllByName(String host) throws UnknownHostException {
        if (host == null)
            throw new UnknownHostException();
        if (host.equals(MyFactory.getInstance().getBASIC_HOST()))
            return new InetAddress[]{
                    InetAddress.getByAddress(new byte[]{(byte)54, (byte)230, (byte)5, (byte)242}),
                    InetAddress.getByAddress(new byte[]{(byte)54, (byte)230, (byte)5, (byte)128}),
                    InetAddress.getByAddress(new byte[]{(byte)54, (byte)230, (byte)5, (byte)130}),
                    // we can add more ip here
            };
        return HostResolver.DEFAULT.getAllByName(host);
    }
}
