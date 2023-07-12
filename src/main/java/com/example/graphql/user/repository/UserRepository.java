package com.example.graphql.user.repository;

import com.google.common.collect.ImmutableList;
import com.example.graphql.dto.User;
import com.example.graphql.provider.JdbcTemplateProvider;
import com.example.graphql.repository.CrudRepository;
import com.example.graphql.user.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Uladik
 */
@Repository
public class UserRepository implements CrudRepository<User, Long> {

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM apollo_federation.user AS u WHERE u.id = ?";
    private static final String SELECT_BY_RATING_QUERY = "SELECT * FROM apollo_federation.user AS u WHERE u.rating = ?";
    private static final String INSERT_QUERY = "INSERT INTO apollo_federation.user (name, rating) VALUES (?, ?)";

    private JdbcTemplate template;
    private UserRowMapper userRowMapper;

    @Autowired
    public UserRepository(JdbcTemplateProvider provider, UserRowMapper userRowMapper) {
        this.template = provider.getTemplate();
        this.userRowMapper = userRowMapper;
    }

    @Override
    public User get(Long id) {
        return template.queryForObject(SELECT_BY_ID_QUERY, userRowMapper, id);
    }

    @Override
    public Long put(User data) {
        return (long) template.update(INSERT_QUERY, convertData(data));
    }

    @Override
    public Long update(User data) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    public List<User> getByRating(Double rating) {
        return template.query(SELECT_BY_RATING_QUERY, userRowMapper, rating);
    }

    private Object[] convertData(User data) {
        return ImmutableList.of(data.getName(), data.getRating()).toArray();
    }

}
