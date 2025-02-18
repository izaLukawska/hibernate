package org.lukawska.course.hibernate.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.lukawska.course.hibernate.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PersonJpaRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Person getById(int id) {
        return entityManager.find(Person.class, id);
    }

    public Person updatePerson(Person person) {
        return entityManager.merge(person);
    }

    public Person insertPerson(Person person) {
        return entityManager.merge(person);
    }

    public void deleteById(int id) {
        Person person = getById(id);
        entityManager.remove(person);
    }

    public List<Person> getAllPeople() {
        return entityManager.createNamedQuery("getAll", Person.class).getResultList();
    }
}
