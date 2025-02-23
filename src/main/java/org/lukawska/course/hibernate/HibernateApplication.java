package org.lukawska.course.hibernate;

import org.lukawska.course.hibernate.entity.*;
import org.lukawska.course.hibernate.repository.CourseRepository;
import org.lukawska.course.hibernate.repository.EmployeeRepository;
import org.lukawska.course.hibernate.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.insert(new PartTimeEmployee("Karol", BigDecimal.valueOf(25)));
        employeeRepository.insert(new FullTimeEmployee("Izabela", BigDecimal.valueOf(10000L)));

//        logger.info("My employees {}", employeeRepository.getAllEmployees());
        logger.info("My parttime employees {}", employeeRepository.getAllPartTimeEmployees());
        logger.info("My fulltime employees {}", employeeRepository.getAllFullTimeEmployees());

    }
}
