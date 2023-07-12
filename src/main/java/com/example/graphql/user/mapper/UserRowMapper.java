package com.example.graphql.user.mapper;

import com.example.graphql.dto.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Uladik
 */
@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User result = new User();
        result.setId(resultSet.getLong("id"));
        result.setName(resultSet.getString("name"));
        result.setRating(resultSet.getDouble("rating"));
        return result;
    }

}
