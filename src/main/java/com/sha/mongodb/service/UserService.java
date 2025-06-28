package com.sha.mongodb.service;

import com.sha.mongodb.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> findById(String id);

    List<User> findAll();

    String deleteById(String id);

    void update(String id, User user);

    List<User> findByName(String name);
}
