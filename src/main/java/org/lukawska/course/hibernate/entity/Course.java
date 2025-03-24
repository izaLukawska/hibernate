package org.lukawska.course.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import org.hibernate.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "get_all_courses", query = "SELECT c FROM Course c"),
        @NamedQuery(name = "get_all_courses_join_fetch", query = "SELECT c FROM Course c JOIN FETCH c.students s"),
        @NamedQuery(name = "get_all_courses_where", query = "SELECT c FROM Course c WHERE c.name LIKE 'Spr%'")
})
@Cacheable
@SQLDelete(sql = "UPDATE course SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false") //instead of WHERE (deprecated)
public class Course {

    private static Logger logger = LoggerFactory.getLogger(Course.class);

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "course")
    private final List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    private boolean isDeleted;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime creationDate;

    protected Course() {
    }

    @PreRemove
    private void preRemove(){
        logger.info("Course is being removed");
        this.isDeleted = true;
    }

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
