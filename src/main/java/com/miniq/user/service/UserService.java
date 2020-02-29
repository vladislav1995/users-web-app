package com.miniq.user.service;

import com.miniq.example.graphql.dto.User;
import com.miniq.example.graphql.repository.CrudRepository;
import com.miniq.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Uladik
 */
@Service
public class UserService implements CrudRepository<User, Long> {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(Long id) {
        return userRepository.get(id);
    }

    @Override
    public Long put(User data) {
        return userRepository.put(data);
    }

    @Override
    public Long update(User data) {
        return userRepository.update(data);
    }

    @Override
    public boolean delete(Long id) {
        return userRepository.delete(id);
    }
}
