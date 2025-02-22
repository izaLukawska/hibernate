package org.lukawska.course.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.lukawska.course.hibernate.entity.Course;
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
    public void nativeQuery() {
        Query query = em.createNativeQuery("SELECT * FROM Course", Course.class);
        List courses = query.getResultList();
        logger.info("All my courses are with native query {}", courses);
    }

    @Test
    public void nativeQueryWithParameters() {
        Query query = em.createNativeQuery("SELECT * FROM Course WHERE id = ?", Course.class);
        query.setParameter(1, 1000L);
        List courses = query.getResultList();
        logger.info("All my courses are with WHERE{}", courses);
    }
    @Test
    @Transactional
    public void nativeQueryUpdate() {
        Query query = em.createNativeQuery("UPDATE Course SET last_updated_date = NOW()", Course.class);
        int rowsUpdated = query.executeUpdate();
        logger.info("All rows updated count is {}", rowsUpdated);
    }

    @Test
    public void jpql_basic() {
        Query query = em.createNamedQuery("get_all_courses");
        List courses = query.getResultList();
        logger.info("All my courses are with jpql{}", courses);
    }

    @Test
    public void jpql_typed() {
        TypedQuery<Course> typedQuery = em.createNamedQuery("get_all_courses", Course.class);
        List<Course> courses = typedQuery.getResultList();
        logger.info("All my courses are with typed {}", courses);
    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> typedQuery = em.createNamedQuery("get_all_courses_where", Course.class);
        List<Course> courses = typedQuery.getResultList();
        logger.info("Matched Courses with where {}", courses);
    }
}