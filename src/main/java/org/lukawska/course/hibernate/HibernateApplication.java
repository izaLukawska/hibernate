package org.lukawska.course.hibernate;

import org.lukawska.course.hibernate.entity.Person;
import org.lukawska.course.hibernate.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJdbcDao personJdbcDao;

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("MY USERS {}", personJdbcDao.getAllUsers());
		logger.info("User with id 10001 is {}", personJdbcDao.getById(10001));
		logger.info("ALL USERS WITH MATCHING NAMES ARE {}", personJdbcDao.getByName("Jan"));
		logger.info("NUMBER OF ROWS DELETED {}", personJdbcDao.deleteByIdOrName(
				10006, "Jan"));
		logger.info("INSERTING PERSON {}", personJdbcDao.insert(
				new Person(10005, "Kacper", "Cracow", LocalDateTime.now())));
		logger.info("INSERTING PERSON {}", personJdbcDao.update(
				new Person(10001, "Iza", "Tokio", LocalDateTime.now())));
    }
}
