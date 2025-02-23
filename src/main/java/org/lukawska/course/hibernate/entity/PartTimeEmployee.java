package org.lukawska.course.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

@Entity
@Transactional
public class PartTimeEmployee extends Employee {

    private BigDecimal hourlyWage;

    protected PartTimeEmployee() {
    }

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }
}
