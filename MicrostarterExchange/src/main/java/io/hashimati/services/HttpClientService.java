package io.hashimati.services;


import io.hashimati.domains.Header;
import io.hashimati.domains.Parameter;
import io.micronaut.http.BasicAuth;
import io.micronaut.security.authentication.Authentication;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.net.Authenticator;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Singleton
public class HttpClientService {



    @Inject
    private HttpClient httpClient;;


    public String getString(String url, List<Parameter> parameter, List<Header> headers, BasicAuth auth) {
        var requestBuild = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url));
        if(headers != null)
        {
            headers.forEach(header -> {
                requestBuild.header(header.getName(), header.getValue().toString());
            });

        }

        var request = requestBuild.build();
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    }
