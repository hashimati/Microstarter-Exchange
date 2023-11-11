package io.hashimati.repositories;

import io.hashimati.domains.Endpoint;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import java.util.*;
import org.bson.types.ObjectId;





@MongoRepository
public interface EndpointRepository extends CrudRepository<Endpoint, String> {

    public Optional<Endpoint> findByServiceAndUrl(String webService, String url) ;
    public Iterable<Endpoint> findByService(String service);
//    public boolean deleteByServiceAndUrl(String service, String url);
}

