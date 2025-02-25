package org.lukawska.course.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.lukawska.course.hibernate.entity.Course;
import org.lukawska.course.hibernate.entity.Review;
import org.lukawska.course.hibernate.entity.ReviewRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public Course saveCourse(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }

        return course;
    }

    public void playWithEntityManager(){
        Course course = new Course("Maven");
        em.persist(course);
        Course course2 = findById(10001L);
        course2.setName("Spring Update");
    }

    public void addReviewForCourse() {
        Course course = findById(10003L);
        logger.info("My current reviews are {}", course.getReviews());

        Review review1 = new Review(ReviewRating.FOUR, "Very good course");
        Review review2 = new Review(ReviewRating.FIVE, "Amazing course");

        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        em.persist(review1);
        em.persist(review2);
    }

    public void addReviewForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        logger.info("My reviews are {}", course.getReviews());

        for(Review review:reviews){
            course.addReview(review);
            review.setCourse(course);

            em.persist(review);
        }
    }
}
