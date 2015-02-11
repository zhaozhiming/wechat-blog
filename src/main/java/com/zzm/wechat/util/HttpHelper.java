package com.zzm.wechat.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;

@Component
public class HttpHelper {

    public String baseHttpRequest(HttpUriRequest request) throws Exception {
        DefaultHttpClient client = null;
        try {
            client = new DefaultHttpClient();
            HttpResponse response = client.execute(request);
            InputStream content = response.getEntity().getContent();
            return convertStreamToString(content);
        } finally {
            if (client != null) {
                client.getConnectionManager().shutdown();
            }
        }
    }

    private String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}