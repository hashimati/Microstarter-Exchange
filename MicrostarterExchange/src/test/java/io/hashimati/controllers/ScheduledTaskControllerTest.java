package io.hashimati.controllers;

import io.hashimati.domains.ScheduledTask;
import io.hashimati.utils.Randomizer;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import io.hashimati.security.SecurityClient;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;


import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class ScheduledTaskControllerTest {

    @Inject
    @Client("/api/v1/scheduledTask")
    HttpClient client;

    HashMap<String, Object> randomObject;
    @Inject SecurityClient securityClient;

    @Test
    void save() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Randomizer randomizer= new Randomizer<ScheduledTask>(ScheduledTask.class);
        randomObject = randomizer.getRandomInstance();
        BearerAccessRefreshToken token = securityClient.login(new UsernamePasswordCredentials("admin", "admin"));
        HttpRequest<HashMap<String, Object>> request = HttpRequest.POST("/save", randomObject).bearerAuth(token.getAccessToken());
        this.randomObject = client.toBlocking().retrieve(request, HashMap.class);
        assertNotNull(randomObject);
    }

    @Test
    void findById() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        save();
         BearerAccessRefreshToken token = securityClient.login(new UsernamePasswordCredentials("admin", "admin"));
        MutableHttpRequest<Object> request = HttpRequest.GET("/get?id="+this.randomObject.get("id")).bearerAuth(token.getAccessToken());
        HashMap<String, Object> randomObject = client.toBlocking().retrieve(request, HashMap.class);
        assertNotNull(randomObject);
    }

    @Test
    void deleteById() throws InstantiationException, IllegalAccessException, NoSuchFieldException{
        save();
         BearerAccessRefreshToken token = securityClient.login(new UsernamePasswordCredentials("admin", "admin"));
        MutableHttpRequest<Object> request = HttpRequest.DELETE("/delete/"+this.randomObject.get("id")).bearerAuth(token.getAccessToken());
        Boolean body= client.toBlocking().retrieve(request, Boolean.class);
        assertTrue(body.booleanValue());
    }

    @Test
    void findAll() throws InstantiationException, IllegalAccessException, NoSuchFieldException{
        save();
         BearerAccessRefreshToken token = securityClient.login(new UsernamePasswordCredentials("admin", "admin"));
        MutableHttpRequest<Object> request = HttpRequest.GET("/findAll").bearerAuth(token.getAccessToken());
        Iterable<ScheduledTask> list = client.toBlocking().retrieve(request, Iterable.class);
        assertNotNull(list);

    }

    @Test
    void update() throws InstantiationException, IllegalAccessException, NoSuchFieldException{
        save();
         BearerAccessRefreshToken token = securityClient.login(new UsernamePasswordCredentials("admin", "admin"));
        HttpRequest<HashMap<String, Object>> request = HttpRequest.PUT("/update", this.randomObject).bearerAuth(token.getAccessToken());
        HashMap<String, Object> randomObject = client.toBlocking().retrieve(request, HashMap.class);
        assertNotNull(randomObject);
    }
}


