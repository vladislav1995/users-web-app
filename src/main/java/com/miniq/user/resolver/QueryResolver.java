package com.miniq.user.resolver;

import com.miniq.example.graphql.dto.User;
import com.miniq.user.service.UserService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
