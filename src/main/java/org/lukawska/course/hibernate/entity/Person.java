package org.lukawska.course.hibernate.entity;

import jakarta.persistence.*;

@Entity
@NamedQuery(name = "getAll", query = "SELECT p FROM Person p")
public class Person {

    @Id
    @GeneratedValue()
    private int id;

    private String name;

    private String location;

    public Person() {
    }

    public Person(String name, String location) {
        this.name = name;
        this.location = location;

    }

    public Person(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
