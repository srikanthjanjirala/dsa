package com.dsa.practice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String department;

    @ManyToOne // is always the owning side of the relationship so this is create a role_id
    // owner side foreign key column is created automatically
//    @JsonBackReference
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    // mappedBy = "user" tells JPA: This is the inverse side of the relationship;
    private Profile profile;


    @ManyToMany
    @JoinTable(
            name = "user_course", // join table name
            joinColumns = @JoinColumn(name = "user_id"), // foreign key to User
            inverseJoinColumns = @JoinColumn(name = "course_id") // foreign key to Course
    )
    private Set<Course> courses = new HashSet<>();}
