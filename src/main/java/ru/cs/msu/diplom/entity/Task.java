package ru.cs.msu.diplom.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    String fileName;

    String image;

    @ManyToOne()
    User author;
}
