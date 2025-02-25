package org.lukawska.course.hibernate.repository;

import org.lukawska.course.hibernate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);

    //jpql
    @Query("SELECT c FROM Course c WHERE c.name LIKE '%Spring%'")
    List<Course> coursesWithSpring();

    //nativeQuery
    @Query(nativeQuery = true, value = "SELECT * FROM Course c WHERE c.name LIKE '%Spring%'")
    List<Course> coursesWithSpringNativeQuery();

    //namedQuery
    @Query(name = "get_all_courses")
    List<Course> coursesWithUsingNamedQuery();
}
