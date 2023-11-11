package io.hashimati.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.micronaut.http.HttpResponse;
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



import io.hashimati.domains.WebService;
import io.hashimati.services.WebServiceService;


@Tag(name = "WebService")
@Controller("/api/v1/webService")
@CircuitBreaker(attempts = "5", maxDelay = "3s", reset = "30")
public class WebServiceController {

    private static final Logger log = LoggerFactory.getLogger(WebServiceController.class);

    @Inject private WebServiceService webServiceService;


    @NewSpan("WebService-service")
    @Timed(value = "io.hashimati.controllers.webServiceController.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving webService object")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Post("/save")
    @Version("1")
    @Operation(summary = "Creating a webService and Storing in the database",
            description = "A REST service, which saves WebService objects to the database.",
            operationId = "SaveWebService"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Object Supplied")
    @ApiResponse(responseCode = "404", description = "WebService not stored")
    public HttpResponse<WebService> save(@SpanTag("save.webService") @NonNull @Body WebService webService
          //  , Principal principle , @Header("Authorization") String authorization
    ){
        log.info("Saving  WebService : {}", webService);
        //TODO insert your logic here!

        //saving Object
        return webServiceService.save(webService, null , null);

//        return webServiceService.save(webService, principle , authorization);
    }


    @NewSpan("WebService-service")
    @Timed(value = "io.hashimati.controllers.webServiceController.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a webService object by id")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/get")
    @Version("1")
    @Operation(summary = "Getting a webService by Id",
        description = "A REST service, which retrieves a WebService object by Id.",
        operationId = "FindByIdWebService"
    )
    @ApiResponse(
        content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "WebService not found")
    public WebService findByAlias(@SpanTag("findByAlias.alias") @Parameter("id") String id
                    //, Principal principle ,  @Header("Authorization") String authorization
                               ){
        return webServiceService.findByAlias(id, null ,null).orElse(null);
    }

    @NewSpan("WebService-service")
    @Timed(value = "io.hashimati.controllers.webServiceController.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a webService object by id")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Delete("/delete/{id}")
    @Version("1")
    @Operation(summary = "Deleting a webService by ID",
            description = "A REST service, which deletes WebService object from the database.",
            operationId = "DeleteByIdWebService"
    )
    @ApiResponse(
            content = @Content(mediaType = "boolean")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "WebService not found")
    public boolean deleteById(@SpanTag("deleteById.id") @PathVariable("id") String id, Principal principle ,  @Header("Authorization") String authorization){
        log.info("Deleting WebService by Id: {}", id);
        return  webServiceService.deleteById(id, principle , authorization);
    }

    @NewSpan("WebService-service")
    @Timed(value = "io.hashimati.controllers.webServiceController.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all webService objects")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/findAll")
    @Version("1")
    @Operation(summary = "Retrieving all webService objects as Json",
            description = "A REST service, which returns all WebService objects from the database.",
            operationId = "FindAllWebService"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    public Iterable<WebService> findAll(
    //        Principal principle ,  @Header("Authorization") String authorization
    ){
        log.info("find All");
        return webServiceService.findAll(null , null);

//        return webServiceService.findAll(principle , authorization);
    }

    @NewSpan("WebService-service")
    @Timed(value = "io.hashimati.controllers.webServiceController.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a webService object")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Put("/update")
    @Version("1")
    @Operation(summary = "Updating a webService.",
            description = "A REST service, which update a WebService objects to the database.",
            operationId = "UpdateWebService"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "404", description = "WebService not found")
    public WebService update(@SpanTag("update.webService") @NonNull @Body WebService webService
          //  , Principal principle ,  @Header("Authorization") String authorization
    )
    {
        log.info("update {}", webService);
        return webServiceService.update(webService, null , null);

    }

    @NewSpan("WebService-service")
    @Timed(value = "io.hashimati.controllers.webServiceController.findAllByProvider", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a webService object by Provider")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/findAllByProvider")
    @Operation(summary = "Getting all entity by Provider",
       description = "A REST service, which retrieves a WebService objects by Provider."
    )
    @ApiResponse(
       content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "WebService not found")
    public Iterable<WebService> findAllByProvider(String query, Principal principle ,  @Header("Authorization") String authorization){
          log.info("Finding all WebService By Provider: {}", query);
          return webServiceService.findAllByProvider(query, principle , authorization);
    }



}


