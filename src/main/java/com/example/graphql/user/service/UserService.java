package com.example.graphql.user.service;

import com.example.graphql.dto.User;
import com.example.graphql.service.CrudService;
import com.example.graphql.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Uladik
 */
@Service
public class UserService implements CrudService<User, Long> {

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

    public List<User> getByRating(Double rating) {
        return userRepository.getByRating(rating);
    }
}
