package ru.study.demoapp.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.study.demoapp.model.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PersonServiceJDBCTemplate {

    private final JdbcTemplate jdbcTemplate;

    public PersonServiceJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Person> personRowMapperRowMapper = (resultSet, rowNum) -> {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("username"));
        person.setAge(resultSet.getInt("age"));
        person.setCity(resultSet.getString("city"));
        return person;
    };

    public void save(Person person) {
        String sql = "INSERT INTO persons (username, age, city) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, person.getName(), person.getAge(), person.getCity());
    }

    public List<Person> findAll18PlusNotMoscow() {
        String sql = "SELECT id, username, age, city FROM persons WHERE age > 18 AND city != 'Москва'";
        return jdbcTemplate.query(sql, personRowMapperRowMapper);
    }

    public Map<String, Integer> countPersonsByCity() {
        String sql = "SELECT city, COUNT(*) as count FROM persons GROUP BY city";
        return jdbcTemplate.query(sql, resultSet -> {
            Map<String, Integer> result = new HashMap<>();
            while (resultSet.next()) {
                result.put(resultSet.getString("city"), resultSet.getInt("count"));
            }
            return result;
        });
    }
}
