package ru.cs.msu.diplom.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "full_name")
    String fullName;

    @OneToMany(mappedBy = "author")
    Set<Task> tasks;

    @OneToMany(mappedBy = "user")
    Set<Solution> solutions;
}
