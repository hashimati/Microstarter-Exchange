package io.hashimati.controllers;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import okhttp3.ResponseBody;

import java.util.HashMap;

@Controller("/test")
public class MyTrials {


    @Get("hello")
    public String hello(@QueryValue("n") String name)
    {
        return "Hello " + name;

    }
    @Get("ahmed")
    public String helloAhmed()
    {
        return "Hello Ahmed";

    }
    @Post("/hello/ahmed")
    public HttpResponse helloAhmed(@Body HashMap<String, String> test)
    {
        return  HttpResponse.status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON_TYPE)
                .body(test);

    }
}
