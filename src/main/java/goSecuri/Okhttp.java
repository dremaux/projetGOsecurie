package com.goSecuri.Test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Okhttp {
	final OkHttpClient client = new OkHttpClient();
	String run(String url) throws IOException {
	    Request request = new Request.Builder()
	        .url(url)
	        .build();

Response response = null;
try {
    response = client.newCall(request).execute();
    return response.body().string();
} finally {
    if (response != null) {
        response.close();
    }
}
	  }
}
