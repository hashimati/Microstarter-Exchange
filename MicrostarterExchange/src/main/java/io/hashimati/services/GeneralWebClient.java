package io.hashimati.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import io.hashimati.domains.Endpoint;
import io.hashimati.domains.Header;
import io.hashimati.domains.Parameter;
import io.hashimati.domains.WebService;
import io.hashimati.domains.enums.SecurityType;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.BasicAuth;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.List;

@Singleton
public class GeneralWebClient {
    @Inject
    private OkHttpClient okHttpClient;

    @Inject
    private ObjectMapper objectMapper;

    public String getTextBased(WebService webService, Endpoint endpoint, List<Parameter> parameters){
        return getTextBased(webService.getRootUrl() + endpoint.getUrl(),parameters, endpoint.getHeaders(), webService.getSecurityType() == SecurityType.BASIC?new BasicAuth(webService.getUsername(), webService.getPassword()): null);
    }

    public String getTextBased(String url, List<Parameter> parameter, List<Header> headers, BasicAuth basicAuth) {
        Request.Builder builder = new Request.Builder();
        System.out.println(parameter);
        if(parameter != null && !parameter.isEmpty())
        {
            StringBuilder urlBuilder = new StringBuilder(url + "?");
            for (Parameter param : parameter) {
                urlBuilder.append(param.getName()).append("=").append(param.getValue()).append("&");
            }
            //remove the last &
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
            url = urlBuilder.toString();
        }

        System.out.println(url);
        if(basicAuth != null)
        {
            builder.addHeader("Authorization", Credentials.basic(basicAuth.getUsername(), basicAuth.getPassword()));
        }
        builder.url(url)
                .get();
        if (headers != null) {

            headers.forEach(header -> {
                builder.addHeader(header.getName(), header.getValue().toString());
            });
        }
        Request request = builder.build();
        try {
            return okHttpClient.newCall(request).execute().body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public byte[] getBinaryBased(String url, List<Parameter> parameter, List<Header> headers, BasicAuth basicAuth) {
        Request.Builder builder = new Request.Builder();
        if(parameter != null && !parameter.isEmpty())
        {
            StringBuilder urlBuilder = new StringBuilder(url + "?");
            for (Parameter param : parameter) {
                urlBuilder.append(param.getName()).append("=").append(param.getValue()).append("&");
            }
            //remove the last &
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
            url = urlBuilder.toString();
        }

        if(basicAuth != null)
        {
            builder.addHeader("Authorization", Credentials.basic(basicAuth.getUsername(), basicAuth.getPassword()));
        }
        builder.url(url)
                .get();
        if (headers != null) {

            headers.forEach(header -> {
                builder.addHeader(header.getName(), header.getValue().toString());
            });
        }
        Request request = builder.build();
        try {


            return okHttpClient.newCall(request).execute().body().bytes();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public String postTextBased(@NonNull String url, @NonNull HashMap<String, Object> requestBody, List<Header> headers, BasicAuth basicAuth) throws JsonProcessingException {
        // convert requestBody to string
        String body = objectMapper.writeValueAsString(requestBody);

        // call a web service using url, parameter & hewaders

        Request.Builder builder = new Request.Builder();
        builder.url(url)
                .post(RequestBody.create(body, okhttp3.MediaType.parse("application/json")));


        if(basicAuth != null)
        {
            builder.addHeader("Authorization", Credentials.basic(basicAuth.getUsername(), basicAuth.getPassword()));
        }
        if (headers != null) {

            headers.forEach(header -> {
                builder.addHeader(header.getName(), header.getValue().toString());
            });
        }
        Request request = builder.build();
        try {

            return okHttpClient.newCall(request).execute().body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public byte[] postBinaryBased(@NonNull String url, @NonNull HashMap<String, Object> requestBody, List<Header> headers, BasicAuth basicAuth) throws JsonProcessingException {
        // convert requestBody to string
        String body = objectMapper.writeValueAsString(requestBody);

        // call a web service using url, parameter & hewaders

        Request.Builder builder = new Request.Builder();
        builder.url(url)
                .post(RequestBody.create(body, okhttp3.MediaType.parse("application/json")));


        if(basicAuth != null)
        {
            builder.addHeader("Authorization", Credentials.basic(basicAuth.getUsername(), basicAuth.getPassword()));
        }
        if (headers != null) {

            headers.forEach(header -> {
                builder.addHeader(header.getName(), header.getValue().toString());
            });
        }
        Request request = builder.build();
        try {

            return okHttpClient.newCall(request).execute().body().bytes();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String putTextBased(@NonNull String url, @NonNull HashMap<String, Object> requestBody, List<Header> headers, BasicAuth basicAuth) throws JsonProcessingException {

        String body = objectMapper.writeValueAsString(requestBody);

        // call a web service using url, parameter & hewaders

        Request.Builder builder = new Request.Builder();
        builder.url(url)
                .put(RequestBody.create(body, okhttp3.MediaType.parse("application/json")));


        if(basicAuth != null)
        {
            builder.addHeader("Authorization", Credentials.basic(basicAuth.getUsername(), basicAuth.getPassword()));
        }
        if (headers != null) {

            headers.forEach(header -> {
                builder.addHeader(header.getName(), header.getValue().toString());
            });
        }
        Request request = builder.build();
        try {

            return okHttpClient.newCall(request).execute().body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public byte[] putBinaryBased(@NonNull String url, @NonNull HashMap<String, Object> requestBody, List<Header> headers, BasicAuth basicAuth) throws JsonProcessingException {
        // convert requestBody to string
        String body = objectMapper.writeValueAsString(requestBody);

        // call a web service using url, parameter & hewaders

        Request.Builder builder = new Request.Builder();
        builder.url(url)
                .put(RequestBody.create(body, okhttp3.MediaType.parse("application/json")));


        if(basicAuth != null)
        {
            builder.addHeader("Authorization", Credentials.basic(basicAuth.getUsername(), basicAuth.getPassword()));
        }
        if (headers != null) {

            headers.forEach(header -> {
                builder.addHeader(header.getName(), header.getValue().toString());
            });
        }
        Request request = builder.build();
        try {

            return okHttpClient.newCall(request).execute().body().bytes();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String deleteTextBased(String url, List<Parameter> parameter, HashMap<String, Object> requestBody,  List<Header> headers, BasicAuth basicAuth) throws JsonProcessingException {
        Request.Builder builder = new Request.Builder();

        String body = objectMapper.writeValueAsString(requestBody);
        if(parameter != null && !parameter.isEmpty())
        {
            StringBuilder urlBuilder = new StringBuilder(url + "?");
            for (Parameter param : parameter) {
                urlBuilder.append(param.getName()).append("=").append(param.getValue()).append("&");
            }
            //remove the last &
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
            url = urlBuilder.toString();
        }

        if(basicAuth != null)
        {
            builder.addHeader("Authorization", Credentials.basic(basicAuth.getUsername(), basicAuth.getPassword()));
        }
        if(requestBody != null && !requestBody.isEmpty())
            builder.url(url)
                .delete(RequestBody.create(body, okhttp3.MediaType.parse("application/json")));
        else
            builder.url(url)
                .delete();
        if (headers != null) {

            headers.forEach(header -> {
                builder.addHeader(header.getName(), header.getValue().toString());
            });
        }
        Request request = builder.build();
        try {
            return okHttpClient.newCall(request).execute().body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public byte[] deleteBinaryBased(String url, List<Parameter> parameter,HashMap<String, Object> requestBody, List<Header> headers, BasicAuth basicAuth) throws JsonProcessingException {
        Request.Builder builder = new Request.Builder();
        String body = objectMapper.writeValueAsString(requestBody);

        if(parameter != null && !parameter.isEmpty())
        {
            StringBuilder urlBuilder = new StringBuilder(url + "?");
            for (Parameter param : parameter) {
                urlBuilder.append(param.getName()).append("=").append(param.getValue()).append("&");
            }
            //remove the last &
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
            url = urlBuilder.toString();
        }


        if(basicAuth != null)
        {
            builder.addHeader("Authorization", Credentials.basic(basicAuth.getUsername(), basicAuth.getPassword()));
        }
        if(requestBody != null && !requestBody.isEmpty())
            builder.url(url)
                    .delete(RequestBody.create(body, okhttp3.MediaType.parse("application/json")));
        else
            builder.url(url)
                    .delete();
        if (headers != null) {

            headers.forEach(header -> {
                builder.addHeader(header.getName(), header.getValue().toString());
            });
        }
        Request request = builder.build();
        try {


            return okHttpClient.newCall(request).execute().body().bytes();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
