package io.hashimati.services;


import jakarta.inject.Inject;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;
import java.security.Principal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.annotation.Timed;

import io.hashimati.domains.Endpoint;
import io.hashimati.repositories.EndpointRepository;









@Singleton
public class EndpointService {

    private static final Logger log = LoggerFactory.getLogger(EndpointService.class);
    @Inject private EndpointRepository endpointRepository;
    

    @Timed(value = "io.hashimati.services.endpointService.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving endpoint object")
    public Endpoint save(Endpoint endpoint, Principal principle ,  String authorization){
        log.info("Saving  Endpoint : {}", endpoint);
        //TODO insert your logic here!
        //saving Object
        if(!endpoint.getUrl().startsWith("/"))
        {
            endpoint.setUrl("/"+endpoint.getUrl());
        }
        return endpointRepository.save(endpoint);

    }

    
    @Timed(value = "io.hashimati.services.endpointService.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a endpoint object by id")
    public Endpoint findById(String id, Principal principle ,  String authorization){
        log.info("Finding Endpoint By Id: {}", id);
        return endpointRepository.findById(id).orElse(null);
    }

    @Timed(value = "io.hashimati.services.endpointService.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a endpoint object by id")
    public boolean deleteById(String id, Principal principle ,  String authorization){
        log.info("Deleting Endpoint by Id: {}", id);
        try{
            endpointRepository.deleteById(id);
            log.info("Deleted Endpoint by Id: {}", id);
            return true;
        }
        catch(Exception ex)
        {
            log.info("Failed to delete Endpoint by Id: {}", id);
            ex.printStackTrace();
            return false;
        }
    }

    @Timed(value = "io.hashimati.services.endpointService.deleteByService", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a endpoint object by id")
    public boolean deleteByService(String id, Principal principle ,  String authorization){
        log.info("Deleting Endpoint by Id: {}", id);
        try{
            endpointRepository.deleteById(id);
            log.info("Deleted Endpoint by Id: {}", id);
            return true;
        }
        catch(Exception ex)
        {
            log.info("Failed to delete Endpoint by Id: {}", id);
            ex.printStackTrace();
            return false;
        }
    }

    @Timed(value = "io.hashimati.services.endpointService.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all endpoint objects")
    public Iterable<Endpoint> findAll(Principal principle ,  String authorization) {
        log.info("Find All");
      return  endpointRepository.findAll();
    }

    public boolean existsById(String id, Principal principle ,  String authorization)
    {
        log.info("Check if id exists: {}", id);
        return  endpointRepository.existsById(id);

    }

    @Timed(value = "io.hashimati.services.endpointService.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a endpoint object")
    public Endpoint update(Endpoint endpoint, Principal principle ,  String authorization)
    {
        log.info("update {}", endpoint);
        return endpointRepository.update(endpoint);


    }

    @Timed(value = "io.hashimati.services.endpointService.findByServiceAndUrl", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a endpoint object by service and url")
    public Optional<Endpoint> findByWebServiceAndUrl(String service, String url, Principal principle ,  String authorization)
    {
        log.info("Find by service and url: {} {}", service, url);
        return endpointRepository.findByServiceAndUrl(service, url);
    }

    @Timed(value = "io.hashimati.services.endpointService.findByServiceAndUrl", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a endpoint object by service and url")
    public Iterable<Endpoint> findByWebService(String service, Principal principle ,  String authorization)
    {
        log.info("Find by service and url: {}", service);
        return endpointRepository.findByService(service);
    }
//
//    public boolean deleteByServiceAndURL(String service, String url,  Principal principle, String authorization) {
//        log.info("Deleting Endpoint by Id: {}, {}", service, url);
//        try{
//            endpointRepository.deleteByServiceAndUrl(service, url);
//            log.info("Deleted Endpoint by Id: {}, url: {}", service, url);
//            return true;
//        }
//        catch(Exception ex)
//        {
//            log.info("Failed to delete Endpoint by Id: {}, url: {}", service, url);
//            ex.printStackTrace();
//            return false;
//        }
//    }
}

