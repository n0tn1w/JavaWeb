package com.ex.FitApp.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "workouts")
public class WorkoutEntity extends BaseEntity{

    @Column(nullable = false,name = "workout_name")
    private String workoutName;

    @Column(nullable = false)
    private int duration;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;
    @ManyToMany
    private List<ExerciseEntity> exercises;
    

    public WorkoutEntity() {
    }


    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ExerciseEntity> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseEntity> exercises) {
        this.exercises = exercises;
    }
}
