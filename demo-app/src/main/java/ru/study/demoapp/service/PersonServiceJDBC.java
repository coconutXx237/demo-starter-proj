package ru.study.demoapp.service;

import org.springframework.stereotype.Component;
import ru.study.demoapp.model.Person;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PersonServiceJDBC {

/*    private static final String URL = "jdbc:postgresql://localhost:5432/mydb";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

    private final DataSource dataSource;

    public PersonServiceJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Person person) {
        String sql = "INSERT INTO persons (username, age, city) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getCity());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> findAll18PlusNotMoscow() {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM persons WHERE age > 18 AND city != 'Москва'";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("username"));
                person.setAge(resultSet.getInt("age"));
                person.setCity(resultSet.getString("city"));
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    public Map<String, Integer> countPersonsByCity() {
        Map<String, Integer> result = new HashMap<>();
        String sql = "SELECT city, COUNT(*) as count FROM persons GROUP BY city";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                result.put(resultSet.getString("city"), resultSet.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
