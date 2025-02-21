package org.lukawska.course.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.lukawska.course.hibernate.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class JpqlTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    public void jpql_basic() {
        List courses = em.createQuery("SELECT c FROM Course c").getResultList();
        logger.info("All my courses are {}", courses);
    }

    @Test
    public void jpql_typed() {
        TypedQuery<Course> typedQuery = em.createQuery("SELECT c FROM Course c", Course.class);
        List<Course> courses = typedQuery.getResultList();
        logger.info("All my courses are {}", courses);
    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> typedQuery = em.createQuery("SELECT c FROM Course c WHERE c.name LIKE 'Spr%'", Course.class);
        List<Course> courses = typedQuery.getResultList();
        logger.info("Matched Courses {}", courses);
    }
}