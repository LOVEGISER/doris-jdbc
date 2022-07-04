package com.doris.jdbc;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpBuilder {

    public CloseableHttpClient buildClient() throws Exception {
        HttpClientBuilder builder = HttpClientBuilder.create()

                .disableRedirectHandling();
        return builder.build();
    }


}
