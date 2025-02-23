package org.lukawska.course.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.lukawska.course.hibernate.entity.Course;
import org.lukawska.course.hibernate.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JpqlTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    void nativeQuery() {
        Query query = em.createNativeQuery("SELECT * FROM Course", Course.class);
        List courses = query.getResultList();
        logger.info("All my courses are with native query {}", courses);
    }

    @Test
    void nativeQueryWithParameters() {
        Query query = em.createNativeQuery("SELECT * FROM Course WHERE id = ?", Course.class);
        query.setParameter(1, 1000L);
        List courses = query.getResultList();
        logger.info("All my courses are with WHERE{}", courses);
    }
    @Test
    @Transactional
    void nativeQueryUpdate() {
        Query query = em.createNativeQuery("UPDATE Course SET last_updated_date = NOW()", Course.class);
        int rowsUpdated = query.executeUpdate();
        logger.info("All rows updated count is {}", rowsUpdated);
    }

    @Test
    void jpql_basic() {
        Query query = em.createNamedQuery("get_all_courses");
        List courses = query.getResultList();
        logger.info("All my courses are with jpql{}", courses);
    }

    @Test
    void jpql_typed() {
        TypedQuery<Course> typedQuery = em.createNamedQuery("get_all_courses", Course.class);
        List<Course> courses = typedQuery.getResultList();
        logger.info("All my courses are with typed {}", courses);
    }

    @Test
    void jpql_where() {
        TypedQuery<Course> typedQuery = em.createNamedQuery("get_all_courses_where", Course.class);
        List<Course> courses = typedQuery.getResultList();
        logger.info("Matched Courses with where {}", courses);
    }

    @Test
    void jpql_getCoursesWithoutStudent(){
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.students IS EMPTY", Course.class);
        List<Course> resultList = query.getResultList();

        logger.info("Courses without students are {}", resultList);
    }

    @Test
    void jpql_getCoursesWithAtLeast2Students(){
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE size(c.students) > 1", Course.class);
        List<Course> resultList = query.getResultList();

        logger.info("Courses with 2 students are {}", resultList);
    }

    @Test
    void jpql_getCoursesOrderByStudentCount(){
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c ORDER BY size(c.students) DESC", Course.class);
        List<Course> resultList = query.getResultList();

        logger.info("Courses ordered are {}", resultList);
    }

    @Test
    void jpql_getStudentsWithPassportPattern(){
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.passport.number LIKE '%123%'", Student.class);
        List<Student> resultList = query.getResultList();

        logger.info("My students matched are {}", resultList);
    }
}