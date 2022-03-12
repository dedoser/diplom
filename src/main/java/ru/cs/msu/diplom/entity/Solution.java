package ru.cs.msu.diplom.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "solution")
@Data
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "sol_file_loc")
    String solFieLoc;

    @Column(name = "sol_images_loc")
    String solImagesLoc;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    Task task;
}
