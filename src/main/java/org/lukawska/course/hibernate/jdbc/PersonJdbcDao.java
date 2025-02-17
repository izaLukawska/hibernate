package org.lukawska.course.hibernate.jdbc;

import org.lukawska.course.hibernate.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Person> findAll(){
       return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<Person>(Person.class));
    }
}
