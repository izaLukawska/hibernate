package org.lukawska.course.hibernate;

import org.lukawska.course.hibernate.entity.Course;
import org.lukawska.course.hibernate.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Course course = repository.findById(10001L);
        logger.info("My course is {}", course);
        repository.deleteById(10004L);
        logger.info("New course is {}", repository.saveCourse(new Course("Java")));
    }
}
