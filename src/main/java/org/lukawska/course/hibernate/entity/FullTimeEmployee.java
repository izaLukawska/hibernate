package org.lukawska.course.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

@Entity
@Transactional
public class FullTimeEmployee extends Employee {

    private BigDecimal salary;

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }
}
