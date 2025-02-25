package org.lukawska.course.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.lukawska.course.hibernate.entity.Address;
import org.lukawska.course.hibernate.entity.Course;
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
    private EntityManager em;

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
        logger.info("My student {}", student);
        logger.info("His passport {}", student.getPassport());
    }

    @Test
    @Transactional
    void setAddressDetailsToStudent() {
        Student student = em.find(Student.class, 20002L);
        Address address = new Address("Warsaw", "Dummy Street 5");
        student.setAddress(address);
        em.flush();
        logger.info("My curr student{}", student);
        logger.info("His address is {}", student.getAddress());
    }

    @Test
    @Transactional //lazy fetch is default
    void getStudentWithCourses(){
        Student student = em.find(Student.class, 20001L);
        logger.info("My current student is {}", student);
        logger.info("All student 20001L courses are {}", student.getCourses());
    }

    @Test
    @Transactional
    void getCourseWithStudent(){
        Course course = em.find(Course.class, 10001L);
        logger.info("My course is{}", course);
        logger.info("Course 10001 students are {}", course.getStudents());
    }
}