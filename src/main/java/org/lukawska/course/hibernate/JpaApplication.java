package org.lukawska.course.hibernate;

import org.lukawska.course.hibernate.entity.Person;
import org.lukawska.course.hibernate.jpa.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJpaRepository jpaRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("My user with id 10001 is {}", jpaRepository.getById(10001));
        logger.info("My insert method {}",
                jpaRepository.insertPerson(new Person("Wojtek", "Rome")));
        logger.info("My update method {}",
                jpaRepository.updatePerson(new Person( "Jakub", "Kyiv")));
        logger.info("All users are {}", jpaRepository.getAllPeople());

    }
}
