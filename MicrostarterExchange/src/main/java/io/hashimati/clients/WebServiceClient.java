package io.hashimati.clients;


import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import io.hashimati.domains.WebService;

import io.micronaut.http.MediaType;
import io.micronaut.http.multipart.CompletedFileUpload;





@Client("/api/v1/webService")
public interface WebServiceClient {

    @Post("/save")
    public WebService save(WebService webService, @Header("Authorization") String authorization);

    @Get("/get")
    public WebService findById(@Parameter("id") String id, @Header("Authorization") String authorization);

    @Delete("/delete/{id}")
    public boolean deleteById(@PathVariable("id") String id, @Header("Authorization") String authorization);

    @Get("/findAll")
    public Iterable<WebService> findAll(  @Header("Authorization") String authorization);

    @Put("/update")
    public WebService update(@Body WebService webService, @Header("Authorization") String authorization);


    @Get("/findAllByProvider")
    public Iterable<WebService> findAllByProvider(String query,  @Header("Authorization") String authorization);

}


