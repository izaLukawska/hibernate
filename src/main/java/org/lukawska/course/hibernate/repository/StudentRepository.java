package org.lukawska.course.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.lukawska.course.hibernate.entity.Course;
import org.lukawska.course.hibernate.entity.Passport;
import org.lukawska.course.hibernate.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class StudentRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }

    public Student saveStudent(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }

        return student;
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("K1234");
        em.persist(passport);
        Student student = new Student("Wiktor");
        student.setPassport(passport);
        em.persist(student);
    }

    public void insertStudentWithCourse() {
        Student student = new Student("Jacob");
        Course course = new Course("Java");

        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
    }

    public void insertStudentWithCourse(Student student, Course course) {
        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
        em.persist(course);
    }

    public void enrollStudentForCourse(Long courseId, Student student){
        Course course = em.find(Course.class, courseId);

        course.addStudent(student);
        student.addCourse(course);

        em.persist(student);
        em.persist(course);
    }

    public void assignCourseToStudent(Long studentId, Course course){
        Student student = em.find(Student.class, studentId);

        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
        em.persist(course);
    }
}
