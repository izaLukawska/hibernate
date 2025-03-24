package org.lukawska.course.hibernate.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.lukawska.course.hibernate.entity.Course;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PerformanceTuningTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    public void creatingNPlusOneProblem() {
        List<Course> courses = em
                .createNamedQuery("get_all_courses", Course.class)
                .getResultList();
        for(Course course:courses){
            logger.info("Courses -> {} Students -> {}",course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_JoinFetch() {
        List<Course> courses = em
                .createNamedQuery("get_all_courses_join_fetch", Course.class)
                .getResultList();

        for(Course course:courses){
            logger.info("JOIN FETCH Courses -> {} Students -> {}",course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_EntityGraph() {
        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        Subgraph<Object> subGraph = entityGraph.addSubgraph("students");

        List<Course> courses = em
                .createNamedQuery("get_all_courses", Course.class)
                .setHint("jakarta.persistence.loadgraph", entityGraph)
                .getResultList();
        for(Course course:courses){
            logger.info("ENTITY GRAPH Course -> {} Students -> {}",course, course.getStudents());
        }
    }

}
