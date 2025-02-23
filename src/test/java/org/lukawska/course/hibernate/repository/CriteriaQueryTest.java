package org.lukawska.course.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.lukawska.course.hibernate.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    void getAllCoursesUsingCriteriaQuery() {
        //1. Use CriteriaBuilder to init CriteriaQuery (defines what type is returned)
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define predicate etc. using CriteriaBuilder
        //4. Add predicate to Criteria Query

        //5. Build TypedQuery using Entity Manager and Criteria Query
        TypedQuery<Course> selectCFromCourseC = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = selectCFromCourseC.getResultList();
        logger.info("My result {}", resultList);
    }

    @Test
    void shouldReturnAllSpringCourses() {
        //1. Use CriteriaBuilder to init CriteriaQuery (defines what type is returned)
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define predicate etc. using CriteriaBuilder
        Predicate isSpringCourse = cb.like(courseRoot.get("name"), "%Spring%");

        //4. Add predicate to Criteria Query
        cq.where(isSpringCourse);

        //5.  Build TypedQuery using Entity Manager and Criteria Query
        TypedQuery<Course> selectCFromCourseC = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = selectCFromCourseC.getResultList();
        logger.info("My Spring courses{}", resultList);
    }

    @Test
    void shouldReturnCoursesWithoutStudents() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        Predicate noStudents = cb.isEmpty(courseRoot.get("students"));
        cq.where(noStudents);

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();

        logger.info("My Empty courses{}", resultList);
    }

    @Test
    void joinCourse() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        courseRoot.join("students");

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();

        logger.info("My join is {}", resultList);
    }

    @Test
    void leftJoinCourse() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        courseRoot.join("students", JoinType.LEFT);

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();

        logger.info("My left join is {}", resultList);
    }
}
