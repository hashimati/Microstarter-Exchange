package io.hashimati.security.repository;


import io.hashimati.security.domains.LoginStatus;
import io.hashimati.security.domains.User;
import io.micronaut.data.mongodb.annotation.MongoRepository;

import io.micronaut.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

@MongoRepository
public interface UserRepository extends CrudRepository<User, String> {
    public User findByUsername(String username);
    public List<User> findAll();
    public boolean existsByUsername(String username);
    public Long updateByUsername(String username, LoginStatus lastLoginStatus, Instant lastTimeTryToLogin);
}

