package io.hashimati.clients;


import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import io.hashimati.domains.ScheduledTask;

import io.micronaut.http.MediaType;
import io.micronaut.http.multipart.CompletedFileUpload;





@Client("/api/v1/scheduledTask")
public interface ScheduledTaskClient {

    @Post("/save")
    public ScheduledTask save(ScheduledTask scheduledTask, @Header("Authorization") String authorization);

    @Get("/get")
    public ScheduledTask findById(@Parameter("id") String id, @Header("Authorization") String authorization);

    @Delete("/delete/{id}")
    public boolean deleteById(@PathVariable("id") String id, @Header("Authorization") String authorization);

    @Get("/findAll")
    public Iterable<ScheduledTask> findAll(  @Header("Authorization") String authorization);

    @Put("/update")
    public ScheduledTask update(@Body ScheduledTask scheduledTask, @Header("Authorization") String authorization);


    @Get("/findAllByName")
    public ScheduledTask findByName(String query,  @Header("Authorization") String authorization);

    @Get("/findAllByService")
    public Iterable<ScheduledTask> findAllByService(String query,  @Header("Authorization") String authorization);

    @Get("/findAllByEndpoint")
    public Iterable<ScheduledTask> findAllByEndpoint(String query,  @Header("Authorization") String authorization);

}


