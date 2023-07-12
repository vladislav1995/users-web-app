package com.example.graphql.user.resolver;

import com.example.graphql.dto.User;
import com.example.graphql.user.service.UserService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Uladik
 */
@Component
public class QueryResolver {

    private UserService userService;

    @Autowired
    public QueryResolver(UserService userService) {
        this.userService = userService;
    }

    public DataFetcher<User> getUser() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return userService.get(Long.parseLong(id));
        };
    }

    public DataFetcher<List<User>> getUserByRating() {
        return dataFetchingEnvironment -> {
            Double rating = dataFetchingEnvironment.getArgument("rating");
            return userService.getByRating(rating);
        };
    }
}
