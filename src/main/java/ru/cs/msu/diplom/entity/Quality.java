package ru.cs.msu.diplom.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "quality")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "rise_time")
    Float riseTime;

    @Column(name = "settling_time")
    Float settlingTime;

    @Column(name = "settling_min")
    Float settlingMin;

    @Column(name = "settling_max")
    Float settlingMax;

    @Column(name = "overshoot")
    Float overshoot;

    @Column(name = "undershoot")
    Float undershoot;

    @Column(name = "peak")
    Float peak;

    @Column(name = "peak_time")
    Float peakTime;
}
