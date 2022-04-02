package io.github.learnjakartaee.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="role")
    private String role;
}
