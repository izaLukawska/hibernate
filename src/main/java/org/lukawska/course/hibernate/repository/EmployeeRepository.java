package org.lukawska.course.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.lukawska.course.hibernate.entity.Employee;
import org.lukawska.course.hibernate.entity.FullTimeEmployee;
import org.lukawska.course.hibernate.entity.PartTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public void insert(Employee employee){
        em.persist(employee);
    }

    public List<FullTimeEmployee> getAllFullTimeEmployees(){
        return em.createQuery("SELECT e FROM FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }

    public List<PartTimeEmployee> getAllPartTimeEmployees(){
        return em.createQuery("SELECT e FROM PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }

    //since Employee class is no longer an entity we can't do query using Employee
//    public List<Employee> getAllEmployees(){
//        TypedQuery<Employee> getAllEmployees = em.createQuery("SELECT e FROM Employee e", Employee.class);
//        return getAllEmployees.getResultList();
//    }
}
