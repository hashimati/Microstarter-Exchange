package io.hashimati.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.micronaut.http.MediaType;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.core.annotation.NonNull;
import java.security.Principal;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import static io.micronaut.http.HttpHeaders.AUTHORIZATION;
import io.micronaut.retry.annotation.CircuitBreaker;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import io.micrometer.core.annotation.Timed;
import io.micronaut.tracing.annotation.NewSpan;
import io.micronaut.tracing.annotation.SpanTag;
import jakarta.inject.Inject;



import io.hashimati.domains.ScheduledTask;
import io.hashimati.services.ScheduledTaskService;


@Tag(name = "ScheduledTask")
@Controller("/api/v1/scheduledTask")
@CircuitBreaker(attempts = "5", maxDelay = "3s", reset = "30")
public class ScheduledTaskController {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTaskController.class);

    @Inject private ScheduledTaskService scheduledTaskService;


    @NewSpan("ScheduledTask-service")
    @Timed(value = "io.hashimati.controllers.scheduledTaskController.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving scheduledTask object")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Post("/save")
    @Version("1")
    @Operation(summary = "Creating a scheduledTask and Storing in the database",
            description = "A REST service, which saves ScheduledTask objects to the database.",
            operationId = "SaveScheduledTask"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Object Supplied")
    @ApiResponse(responseCode = "404", description = "ScheduledTask not stored")
    public ScheduledTask save(@SpanTag("save.scheduledTask") @NonNull @Body ScheduledTask scheduledTask , Principal principle ,  @Header("Authorization") String authorization){
        log.info("Saving  ScheduledTask : {}", scheduledTask);
        //TODO insert your logic here!

        //saving Object
        return scheduledTaskService.save(scheduledTask, principle , authorization);
    }


    @NewSpan("ScheduledTask-service")
    @Timed(value = "io.hashimati.controllers.scheduledTaskController.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a scheduledTask object by id")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/get")
    @Version("1")
    @Operation(summary = "Getting a scheduledTask by Id",
        description = "A REST service, which retrieves a ScheduledTask object by Id.",
        operationId = "FindByIdScheduledTask"
    )
    @ApiResponse(
        content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "ScheduledTask not found")
    public ScheduledTask findById(@SpanTag("findById.id") @Parameter("id") String id, Principal principle ,  @Header("Authorization") String authorization){
        return scheduledTaskService.findById(id, principle , authorization);
    }

    @NewSpan("ScheduledTask-service")
    @Timed(value = "io.hashimati.controllers.scheduledTaskController.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a scheduledTask object by id")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Delete("/delete/{id}")
    @Version("1")
    @Operation(summary = "Deleting a scheduledTask by ID",
            description = "A REST service, which deletes ScheduledTask object from the database.",
            operationId = "DeleteByIdScheduledTask"
    )
    @ApiResponse(
            content = @Content(mediaType = "boolean")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "ScheduledTask not found")
    public boolean deleteById(@SpanTag("deleteById.id") @PathVariable("id") String id, Principal principle ,  @Header("Authorization") String authorization){
        log.info("Deleting ScheduledTask by Id: {}", id);
        return  scheduledTaskService.deleteById(id, principle , authorization);
    }

    @NewSpan("ScheduledTask-service")
    @Timed(value = "io.hashimati.controllers.scheduledTaskController.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all scheduledTask objects")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/findAll")
    @Version("1")
    @Operation(summary = "Retrieving all scheduledTask objects as Json",
            description = "A REST service, which returns all ScheduledTask objects from the database.",
            operationId = "FindAllScheduledTask"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    public Iterable<ScheduledTask> findAll(Principal principle ,  @Header("Authorization") String authorization){
        log.info("find All");
        return scheduledTaskService.findAll(principle , authorization);
    }

    @NewSpan("ScheduledTask-service")
    @Timed(value = "io.hashimati.controllers.scheduledTaskController.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a scheduledTask object")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Put("/update")
    @Version("1")
    @Operation(summary = "Updating a scheduledTask.",
            description = "A REST service, which update a ScheduledTask objects to the database.",
            operationId = "UpdateScheduledTask"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "404", description = "ScheduledTask not found")
    public ScheduledTask update(@SpanTag("update.scheduledTask") @NonNull @Body ScheduledTask scheduledTask, Principal principle ,  @Header("Authorization") String authorization)
    {
        log.info("update {}", scheduledTask);
        return scheduledTaskService.update(scheduledTask, principle , authorization);

    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/findByName")
    @NewSpan("ScheduledTask-service")
    @Timed(value = "io.hashimati.controllers.scheduledTaskController.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a scheduledTask object by Name")
    @Operation(summary = "Find an entity by Name",
    description = "A REST service, which retrieves a ScheduledTask object by Name."
    )
    @ApiResponse(
    content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "ScheduledTask not found")
    public ScheduledTask findByName(String query, Principal principle ,  @Header("Authorization") String authorization){
          log.info("Finding ScheduledTask By Name: {}", query);
          return scheduledTaskService.findByName(query, principle , authorization);
    }


    @NewSpan("ScheduledTask-service")
    @Timed(value = "io.hashimati.controllers.scheduledTaskController.findAllByService", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a scheduledTask object by Service")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/findAllByService")
    @Operation(summary = "Getting all entity by Service",
       description = "A REST service, which retrieves a ScheduledTask objects by Service."
    )
    @ApiResponse(
       content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "ScheduledTask not found")
    public Iterable<ScheduledTask> findAllByService(String query, Principal principle ,  @Header("Authorization") String authorization){
          log.info("Finding all ScheduledTask By Service: {}", query);
          return scheduledTaskService.findAllByService(query, principle , authorization);
    }


    @NewSpan("ScheduledTask-service")
    @Timed(value = "io.hashimati.controllers.scheduledTaskController.findAllByEndpoint", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a scheduledTask object by Endpoint")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/findAllByEndpoint")
    @Operation(summary = "Getting all entity by Endpoint",
       description = "A REST service, which retrieves a ScheduledTask objects by Endpoint."
    )
    @ApiResponse(
       content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "ScheduledTask not found")
    public Iterable<ScheduledTask> findAllByEndpoint(String query, Principal principle ,  @Header("Authorization") String authorization){
          log.info("Finding all ScheduledTask By Endpoint: {}", query);
          return scheduledTaskService.findAllByEndpoint(query, principle , authorization);
    }



}


