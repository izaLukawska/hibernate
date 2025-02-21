package org.lukawska.course.hibernate.repository;

import org.junit.jupiter.api.Test;
import org.lukawska.course.hibernate.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository testRepository;

    @Test
    public void findById() {
        //given
        Long id = 10001L;
        String expectedName = "Spring";

        //when
        Course course = testRepository.findById(id);
        String actualName = course.getName();

        //then
        assertEquals(expectedName, actualName);
    }

    @Test
    @DirtiesContext //reset data after test
    public void deleteById(){
        //given
        Long id = 10002L;

        //when
        testRepository.deleteById(id);

        //then
        assertNull(testRepository.findById(id));
    }

    @Test
    @DirtiesContext
    public void saveCourse(){
        //given
        Long id = 10001L;
        Course course = testRepository.findById(id);
        String expectedName = "Spring Boot";

        //when
        course.setName("Spring Boot");
        Course actualCourse = testRepository.saveCourse(course);

        //then
        assertEquals(expectedName, actualCourse.getName());
        assertEquals(course.getId(), actualCourse.getId());
    }
}