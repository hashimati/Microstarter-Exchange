package io.hashimati.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;

import java.net.http.HttpClient;

@Factory
public class SysFactory {

    @Singleton
    public OkHttpClient okHttpClient()
    {
        return new OkHttpClient();
    }
    @Singleton
    public ObjectMapper objectMapper()
    {
        return new ObjectMapper();
    }

    @Singleton
    public HttpClient httpClient()
    {
        return HttpClient.newHttpClient();
    }

}
