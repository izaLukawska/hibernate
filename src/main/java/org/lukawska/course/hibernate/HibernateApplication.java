package org.lukawska.course.hibernate;

import org.lukawska.course.hibernate.entity.Review;
import org.lukawska.course.hibernate.repository.CourseRepository;
import org.lukawska.course.hibernate.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Review> reviews = List.of(new Review("2", "Not good"), new Review("3", "Nothing special"));
        courseRepository.addReviewForCourse(10004L, reviews);
    }
}
