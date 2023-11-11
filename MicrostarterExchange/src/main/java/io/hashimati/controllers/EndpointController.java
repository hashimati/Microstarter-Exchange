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



import io.hashimati.domains.Endpoint;
import io.hashimati.services.EndpointService;


@Tag(name = "Endpoint")
@Controller("/api/v1/endpoint")
@CircuitBreaker(attempts = "5", maxDelay = "3s", reset = "30")
public class EndpointController {

    private static final Logger log = LoggerFactory.getLogger(EndpointController.class);

    @Inject private EndpointService endpointService;


    @NewSpan("Endpoint-service")
    @Timed(value = "io.hashimati.controllers.endpointController.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving endpoint object")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Post("/save")
    @Version("1")
    @Operation(summary = "Creating a endpoint and Storing in the database",
            description = "A REST service, which saves Endpoint objects to the database.",
            operationId = "SaveEndpoint"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Object Supplied")
    @ApiResponse(responseCode = "404", description = "Endpoint not stored")
    public Endpoint save(@SpanTag("save.endpoint") @NonNull @Body Endpoint endpoint
    //        , Principal principle ,  @Header("Authorization") String authorization
    ){

        log.info("Saving  Endpoint : {}", endpoint);
        //TODO insert your logic here!


        //saving Object
        //return endpointService.save(endpoint, principle , authorization);
        return endpointService.save(endpoint, null , null);

    }


    @NewSpan("Endpoint-service")
    @Timed(value = "io.hashimati.controllers.endpointController.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a endpoint object by id")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/get")
    @Version("1")
    @Operation(summary = "Getting a endpoint by Id",
        description = "A REST service, which retrieves a Endpoint object by Id.",
        operationId = "FindByIdEndpoint"
    )
    @ApiResponse(
        content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Endpoint not found")
    public Endpoint findById(@SpanTag("findById.id") @Parameter("id") String id, Principal principle ,  @Header("Authorization") String authorization){
        return endpointService.findById(id, principle , authorization);
    }



    @NewSpan("Endpoint-service")
    @Timed(value = "io.hashimati.controllers.endpointController.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a endpoint object by id")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/getByService")
    @Version("1")
    @Operation(summary = "Getting endpoints by service",
            description = "A REST service, which retrieves Endpoints object by service.",
            operationId = "FindByIdEndpoint"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Endpoint not found")
    public Iterable<Endpoint> findByService(@SpanTag("findByService.service") @Parameter("service") String service /**, Principal principle ,  @Header("Authorization") String authorization **/){
        return endpointService.findByWebService(service, null , null);
    }



    @NewSpan("Endpoint-service")
    @Timed(value = "io.hashimati.controllers.endpointController.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a endpoint object by id")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Delete("/delete/{id}")
    @Version("1")
    @Operation(summary = "Deleting a endpoint by ID",
            description = "A REST service, which deletes Endpoint object from the database.",
            operationId = "DeleteByIdEndpoint"
    )
    @ApiResponse(
            content = @Content(mediaType = "boolean")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Endpoint not found")
    public boolean deleteById(@SpanTag("deleteById.id") @PathVariable("id") String id
           // ,  Principal principle ,  @Header("Authorization") String authorization
    ){
        log.info("Deleting Endpoint by Id: {}", id);
        return  endpointService.deleteById(id, null ,null);
    }




//    @NewSpan("Endpoint-service")
//    @Timed(value = "io.hashimati.controllers.endpointController.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a endpoint object by id")
//    @Secured(SecurityRule.IS_AUTHENTICATED)
//    @Delete("/delete/{service}")
//    @Version("1")
//    @Operation(summary = "Deleting a endpoint by service and url",
//            description = "A REST service, which deletes Endpoint object from the database.",
//            operationId = "DeleteByIdEndpoint"
//    )
//    @ApiResponse(
//            content = @Content(mediaType = "boolean")
//    )
//    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
//    @ApiResponse(responseCode = "404", description = "Endpoint not found")
//    public boolean deleteByServiceAndURL(@SpanTag("deleteById.idandurl") @PathVariable("id") String id,@QueryValue("url") String url, Principal principle ,  @Header("Authorization") String authorization){
//        log.info("Deleting Endpoint by Id: {}, {}", id, url);
//        return  endpointService.deleteByServiceAndURL(id,url,  principle , authorization);
//    }


    @NewSpan("Endpoint-service")
    @Timed(value = "io.hashimati.controllers.endpointController.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all endpoint objects")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/findAll")
    @Version("1")
    @Operation(summary = "Retrieving all endpoint objects as Json",
            description = "A REST service, which returns all Endpoint objects from the database.",
            operationId = "FindAllEndpoint"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    public Iterable<Endpoint> findAll(Principal principle ,  @Header("Authorization") String authorization){
        log.info("find All");
        return endpointService.findAll(principle , authorization);
    }

    @NewSpan("Endpoint-service")
    @Timed(value = "io.hashimati.controllers.endpointController.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a endpoint object")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Put("/update")
    @Version("1")
    @Operation(summary = "Updating a endpoint.",
            description = "A REST service, which update a Endpoint objects to the database.",
            operationId = "UpdateEndpoint"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "404", description = "Endpoint not found")
    public Endpoint update(@SpanTag("update.endpoint") @NonNull @Body Endpoint endpoint
    //        , Principal principle ,  @Header("Authorization") String authorization
    )
    {
        log.info("update {}", endpoint);
        return endpointService.update(endpoint, null , null);

    }


}


