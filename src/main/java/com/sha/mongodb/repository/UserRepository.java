package com.sha.mongodb.repository;

import com.sha.mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {

    @Query(value = "{ 'name' : ?0 }", collation = "{ 'locale' : 'en', 'strength' : 1 }")
    List<User> findByName(String name);

}
