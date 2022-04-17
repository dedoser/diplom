package ru.cs.msu.diplom.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "solution")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "sol_file_loc")
    String solFileLoc;

    @Column(name = "image_plot")
    String imagePlot;

    @Column(name = "image_system")
    String imageSystem;

    @Column(name = "log_file")
    String logFile;

    @OneToOne
    @JoinColumn(name = "quality_id", referencedColumnName = "id")
    Quality quality;

    @ManyToOne
    @JoinColumn(name = "task_id")
    Task task;
}
