/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.provider.common.http;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URI;

public class OkHttpFactory implements ClientHttpRequestFactory {

    private final OkHttpClient client = new OkHttpClient();
    private final OkUrlFactory urlFactory = new OkUrlFactory(client);

    private int connectTimeout = 10000;
    private int readTimeout = 10000;

    @Override
    public ClientHttpRequest createRequest(final URI uri, final HttpMethod httpMethod) throws IOException {
        final HttpURLConnection connection = this.urlFactory.open(uri.toURL());

        prepareConnection(connection, httpMethod.name());

        return new OkHttpClientRequest(connection);
    }

    public void setProxy(Proxy proxy) {
        this.client.setProxy(proxy);
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
        if (this.connectTimeout >= 0) {
            connection.setConnectTimeout(this.connectTimeout);
        }
        if (this.readTimeout >= 0) {
            connection.setReadTimeout(this.readTimeout);
        }
        connection.setDoInput(true);
        if ("GET".equals(httpMethod)) {
            connection.setInstanceFollowRedirects(true);
        } else {
            connection.setInstanceFollowRedirects(false);
        }
        if ("PUT".equals(httpMethod) || "POST".equals(httpMethod)) {
            connection.setDoOutput(true);
        } else {
            connection.setDoOutput(false);
        }
        connection.setRequestMethod(httpMethod);
    }

}
