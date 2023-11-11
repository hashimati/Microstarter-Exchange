package io.hashimati.repositories;

import io.hashimati.domains.WebService;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import java.util.*;
import org.bson.types.ObjectId;





@MongoRepository
public interface WebServiceRepository extends CrudRepository<WebService, String> {

    public Iterable<WebService> findAllByProvider(String provider);
    public Optional<WebService> findByName(String name);
    public Optional<WebService> findByAlias(String alias);
    public boolean existsByName(String name);
    public boolean existsByAlias(String alias);


}

