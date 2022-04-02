package io.github.learnjakartaee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Department {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="department_name")
    private String departmentName;


}
