package org.lukawska.course.hibernate.jdbc;

import org.lukawska.course.hibernate.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setLocation(rs.getString("location"));
            return person;
        }
    }

    public List<Person> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonRowMapper());
    }

    public Person getById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM person p WHERE p.id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class));
    }

    public List<Person> getByName(String name) {
        return jdbcTemplate.query("SELECT * FROM person p WHERE p.name = ?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class));
    }

    public int deleteByIdOrName(int id, String name) {
        return jdbcTemplate.update(
                "DELETE FROM person p WHERE p.id = ? OR p.name = ?",
                id, name);
    }

    public int insert(Person person) {
        return jdbcTemplate.update(
                "INSERT INTO person (id, name, location) VALUES (?, ?, ?)",
                person.getId(), person.getName(), person.getLocation());
    }

    public int update(Person person) {
        return jdbcTemplate.update(
                " UPDATE person "
                        + " SET name = ?, location = ? "
                        + " WHERE person.id = ? ",
                person.getName(), person.getLocation(), person.getId());
    }
}

