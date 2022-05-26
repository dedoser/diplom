package ru.cs.msu.diplom.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @Column(name = "description")
    String description;

    @Column(name = "input_block")
    String inputBlock;

    @Column(name = "output_block")
    String outputBlock;

    @Column(name = "rise_time")
    String riseTime;

    @Column(name = "settling_time")
    String settlingTime;

    @Column(name = "settling_min")
    String settlingMin;

    @Column(name = "settling_max")
    String settlingMax;

    @Column(name = "overshoot")
    String overshoot;

    @Column(name = "undershoot")
    String undershoot;

    @Column(name = "peak")
    String peak;

    @Column(name = "peak_time")
    String peakTime;

    @OneToMany(mappedBy = "task")
    Set<Solution> solutions = new HashSet<>();

    public void addSolution(Solution solution) {
        solution.setTask(this);
        solutions.add(solution);
    }
}
