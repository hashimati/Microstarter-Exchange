package io.hashimati.repositories;

import io.hashimati.domains.ScheduledTask;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import java.util.*;
import org.bson.types.ObjectId;





@MongoRepository
public interface ScheduledTaskRepository extends CrudRepository<ScheduledTask, String> {

    public Optional<ScheduledTask> findByName(String name);

    public Iterable<ScheduledTask> findAllByService(String service);

    public Iterable<ScheduledTask> findAllByEndpoint(String endpoint);

}

