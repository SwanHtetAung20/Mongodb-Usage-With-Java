package com.sha.mongodb.service.impl;

import com.sha.mongodb.domain.User;
import com.sha.mongodb.repository.UserRepository;
import com.sha.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
     userRepository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String deleteById(String id) {
        userRepository.deleteById(id);
        return "Deleted successfully.!";
    }

    @Override
    public void update(String id, User user) {
     userRepository.findById(id).ifPresent(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            userRepository.save(existingUser);
        });
    }

    @Override
    public List<User> findByName(String name) {
      //  List<User> users = userRepository.findByName("mgmg", Sort.by(Sort.Direction.ASC, "email"));
        return userRepository.findByName(name);
    }
}
