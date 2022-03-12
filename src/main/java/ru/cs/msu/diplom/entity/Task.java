package ru.cs.msu.diplom.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "task_file_loc")
    String taskFileLoc;

    @Column(name = "images_loc")
    String imagesLoc;

    @JoinColumn(name = "user_id")
    @ManyToOne
    User author;

    @OneToMany(mappedBy = "task")
    Set<Solution> solutions;
}
