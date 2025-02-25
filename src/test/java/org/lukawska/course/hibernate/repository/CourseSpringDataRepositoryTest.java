package org.lukawska.course.hibernate.repository;

import net.bytebuddy.dynamic.scaffold.TypeWriter;
import org.junit.jupiter.api.Test;
import org.lukawska.course.hibernate.HibernateApplication;
import org.lukawska.course.hibernate.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = HibernateApplication.class)
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseSpringDataRepository repository;

    @Test
    void getById(){
        Optional<Course> optionalCourse = repository.findById(10001L);
        assertTrue(optionalCourse.isPresent());
    }

    @Test
    void getByIdNotPresent(){
        Optional<Course> optionalCourse = repository.findById(20001L);
        assertFalse(optionalCourse.isPresent());
    }

    @Test
    void saveCourse(){
        Course course = new Course("Java Script");
        repository.save(course);
        course.setName("Java Script Updated");
        repository.save(course);
    }

    @Test
    void getAllCourses(){
        logger.info("My courses are {}", repository.findAll());
    }

    @Test
    void getAllCoursesCount(){
        logger.info("My count is {}", repository.count());
    }

    @Test
    void getAllCoursesSortedDescending(){
        Sort desc = Sort.by(Sort.Direction.ASC, "name");
        logger.info("My sorted desc courses are {}", repository.findAll(desc));
    }

    @Test
    void getAllCoursesSortedAscending(){
        Sort asc = Sort.by(Sort.Direction.DESC, "name");
        logger.info("My sorted asc courses are {}", repository.findAll(asc));
    }

    @Test
    void pagination(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> page = repository.findAll(pageRequest);
        logger.info("My curr page is {}", page.getContent());
        PageRequest pageRequest2 = PageRequest.of(3, 3);
        Page<Course> page2 = repository.findAll(pageRequest2);
        logger.info("My curr page is {}", page2.getContent());
    }

    @Test
    void findByName(){
        logger.info("My course with this name is {}", repository.findByName("Spring"));
    }

    @Test
    void getSpringCourses(){
        logger.info("My Spring courses with jpql are {}", repository.coursesWithSpring());
        logger.info("My Spring courses with native query are {}", repository.coursesWithSpringNativeQuery());
    }

    @Test
    void getAllCoursesUsingNamedQuery(){
        logger.info("All courses with named query are {}", repository.coursesWithUsingNamedQuery());
    }
}
