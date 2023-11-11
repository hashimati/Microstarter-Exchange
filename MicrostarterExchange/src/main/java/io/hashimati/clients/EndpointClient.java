package io.hashimati.clients;


import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import io.hashimati.domains.Endpoint;

import io.micronaut.http.MediaType;
import io.micronaut.http.multipart.CompletedFileUpload;





@Client("/api/v1/endpoint")
public interface EndpointClient {

    @Post("/save")
    public Endpoint save(Endpoint endpoint, @Header("Authorization") String authorization);

    @Get("/get")
    public Endpoint findById(@Parameter("id") String id, @Header("Authorization") String authorization);

    @Delete("/delete/{id}")
    public boolean deleteById(@PathVariable("id") String id, @Header("Authorization") String authorization);

    @Get("/findAll")
    public Iterable<Endpoint> findAll(  @Header("Authorization") String authorization);

    @Put("/update")
    public Endpoint update(@Body Endpoint endpoint, @Header("Authorization") String authorization);


}


