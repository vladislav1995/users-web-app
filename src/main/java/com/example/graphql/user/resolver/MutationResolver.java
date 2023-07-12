package com.example.graphql.user.resolver;

import com.example.graphql.dto.User;
import com.example.graphql.user.service.UserService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Uladik
 */
@Component
public class MutationResolver {

    private UserService userService;

    @Autowired
    public MutationResolver(UserService userService) {
        this.userService = userService;
    }

    public DataFetcher<Long> putUser() {
        return dataFetchingEnvironment -> {
            User data = convertInputData(dataFetchingEnvironment.getArgument("user"));
            return userService.put(data);
        };
    }

    private User convertInputData(Map<String, Object> inputData) {
        User dto = new User();
        dto.setRating((Double) inputData.get("rating"));
        dto.setName((String) inputData.get("name"));
        return dto;
    }
}
