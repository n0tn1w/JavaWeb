package com.ex.FitApp.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "exercises")
public class ExerciseEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int sets;
    @Column(name = "duration_time",nullable = false)
    private double durationTime;
    @Column(nullable = false)
    private double weights;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

}
