package org.lukawska.course.hibernate.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    @RequestMapping("/courses")
    public List<Course> getCourses(){
        return Arrays.asList(
                new Course(1, "Hibernate", "XYZ"),
                new Course(2, "SpringBoot", "XYZ")
        );
    }
}
