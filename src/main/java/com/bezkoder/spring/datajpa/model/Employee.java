package com.bezkoder.spring.datajpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "dep")
    private long dep;
    @Column(name = "dep_name")
    private String dep_name;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
