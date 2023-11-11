package io.hashimati.services;


import jakarta.inject.Inject;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;
import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.annotation.Timed;

import io.hashimati.domains.ScheduledTask;
import io.hashimati.repositories.ScheduledTaskRepository;









@Singleton
public class ScheduledTaskService {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTaskService.class);
    @Inject private ScheduledTaskRepository scheduledTaskRepository;
    

    @Timed(value = "io.hashimati.services.scheduledTaskService.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving scheduledTask object")
    public ScheduledTask save(ScheduledTask scheduledTask, Principal principle ,  String authorization){
        log.info("Saving  ScheduledTask : {}", scheduledTask);
        //TODO insert your logic here!
        //saving Object
        return scheduledTaskRepository.save(scheduledTask);

    }

    
    @Timed(value = "io.hashimati.services.scheduledTaskService.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a scheduledTask object by id")
    public ScheduledTask findById(String id, Principal principle ,  String authorization){
        log.info("Finding ScheduledTask By Id: {}", id);
        return scheduledTaskRepository.findById(id).orElse(null);
    }

    @Timed(value = "io.hashimati.services.scheduledTaskService.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a scheduledTask object by id")
    public boolean deleteById(String id, Principal principle ,  String authorization){
        log.info("Deleting ScheduledTask by Id: {}", id);
        try{
            scheduledTaskRepository.deleteById(id);
            log.info("Deleted ScheduledTask by Id: {}", id);
            return true;
        }
        catch(Exception ex)
        {
            log.info("Failed to delete ScheduledTask by Id: {}", id);
            ex.printStackTrace();
            return false;
        }
    }

    @Timed(value = "io.hashimati.services.scheduledTaskService.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all scheduledTask objects")
    public Iterable<ScheduledTask> findAll(Principal principle ,  String authorization) {
        log.info("Find All");
      return  scheduledTaskRepository.findAll();
    }

    public boolean existsById(String id, Principal principle ,  String authorization)
    {
        log.info("Check if id exists: {}", id);
        return  scheduledTaskRepository.existsById(id);

    }

    @Timed(value = "io.hashimati.services.scheduledTaskService.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a scheduledTask object")
    public ScheduledTask update(ScheduledTask scheduledTask, Principal principle ,  String authorization)
    {
        log.info("update {}", scheduledTask);
        return scheduledTaskRepository.update(scheduledTask);

    }
    
    @Timed(value = "io.hashimati.services.scheduledTaskService.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a scheduledTask object by id")
    public ScheduledTask findByName(String query, Principal principle ,  String authorization){
          log.info("Finding ScheduledTask By Name: {}", query);
          return scheduledTaskRepository.findByName(query).orElse(null);
    }


    
    @Timed(value = "io.hashimati.services.scheduledTaskService.findAllByService", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a scheduledTask object by Service")
    public Iterable<ScheduledTask> findAllByService(String query, Principal principle , String authorization){
          log.info("Finding ScheduledTask By Service: {}", query);
          return scheduledTaskRepository.findAllByService(query);
    }

    
    @Timed(value = "io.hashimati.services.scheduledTaskService.findAllByEndpoint", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a scheduledTask object by Endpoint")
    public Iterable<ScheduledTask> findAllByEndpoint(String query, Principal principle , String authorization){
          log.info("Finding ScheduledTask By Endpoint: {}", query);
          return scheduledTaskRepository.findAllByEndpoint(query);
    }


}

