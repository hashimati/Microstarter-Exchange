package io.hashimati.services;


import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.security.Principal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.annotation.Timed;

import io.hashimati.domains.WebService;
import io.hashimati.repositories.WebServiceRepository;









@Singleton
public class WebServiceService {

    private static final Logger log = LoggerFactory.getLogger(WebServiceService.class);
    @Inject private WebServiceRepository webServiceRepository;
    

    @Timed(value = "io.hashimati.services.webServiceService.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving webService object")
    public HttpResponse<WebService> save(WebService webService, Principal principle , String authorization){
        log.info("Saving  WebService : {}", webService);
        //TODO insert your logic here!
        //saving Object

        webService.setId(webService.getAlias());


            webService.setRootUrl(validateUrl(webService.getRootUrl()));

        if(webServiceRepository.existsByName(webService.getName()))
           return HttpResponse.notFound();
        if(webServiceRepository.existsByAlias(webService.getAlias()))
            return HttpResponse.notFound();
        return HttpResponse.ok(webServiceRepository.save(webService));

    }

    private String validateUrl(String rootUrl) {
        if(rootUrl.endsWith("/"))
         return rootUrl.substring(0, rootUrl.length()-1);
        return rootUrl;
    }


    @Timed(value = "io.hashimati.services.webServiceService.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a webService object by id")
    public WebService findById(String id, Principal principle ,  String authorization){
        log.info("Finding WebService By Id: {}", id);
        return webServiceRepository.findById(id).orElse(null);
    }

    @Timed(value = "io.hashimati.services.webServiceService.findByAlias", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a webService object by id")
    public Optional<WebService> findByAlias(String alias, Principal principle , String authorization){
        log.info("Finding WebService By Alias: {}", alias);
        return webServiceRepository.findByAlias(alias);
    }

    @Timed(value = "io.hashimati.services.webServiceService.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a webService object by id")
    public boolean deleteById(String id, Principal principle ,  String authorization){
        log.info("Deleting WebService by Id: {}", id);
        try{
            webServiceRepository.deleteById(id);
            log.info("Deleted WebService by Id: {}", id);
            return true;
        }
        catch(Exception ex)
        {
            log.info("Failed to delete WebService by Id: {}", id);
            ex.printStackTrace();
            return false;
        }
    }

    @Timed(value = "io.hashimati.services.webServiceService.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all webService objects")
    public Iterable<WebService> findAll(Principal principle ,  String authorization) {
        log.info("Find All");
      return  webServiceRepository.findAll();
    }

    public boolean existsById(String id, Principal principle ,  String authorization)
    {
        log.info("Check if id exists: {}", id);
        return  webServiceRepository.existsById(id);

    }

    @Timed(value = "io.hashimati.services.webServiceService.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a webService object")
    public WebService update(WebService webService, Principal principle ,  String authorization)
    {
        log.info("update {}", webService);

        webService.setRootUrl(validateUrl(webService.getRootUrl()));

        return webServiceRepository.update(webService);

    }
    
    @Timed(value = "io.hashimati.services.webServiceService.findAllByProvider", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a webService object by Provider")
    public Iterable<WebService> findAllByProvider(String query, Principal principle , String authorization){
          log.info("Finding WebService By Provider: {}", query);
          return webServiceRepository.findAllByProvider(query);
    }


    public Optional<WebService> findByName(String name, Principal principle , String authorization){
        log.info("Finding WebService By Name: {}", name);
        return webServiceRepository.findByName(name);
    }
}

