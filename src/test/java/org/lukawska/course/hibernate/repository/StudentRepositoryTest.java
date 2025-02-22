package org.lukawska.course.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.lukawska.course.hibernate.entity.Passport;
import org.lukawska.course.hibernate.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentRepositoryTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;
    String associated;

    @Test
    @Transactional
    void findPassportWithAssociatedStudent() {
        Passport passport = em.find(Passport.class, 40001L);
        logger.info("My passport is {}", passport);
        logger.info("Associated student is {}", passport.getStudent());
    }


    @Test
    @Transactional //we have lazy fetching so without it getPassport won't work
    void findByIdWithPassportDetails() {
        Student student = em.find(Student.class, 20001L);
        logger.info("My student is {}", student);
        logger.info("His passport is {}", student.getPassport());
    }
}