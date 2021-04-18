package com.ex.FitApp.services;

import com.ex.FitApp.models.bindings.ExerciseAddBinding;
import com.ex.FitApp.models.bindings.WorkoutAddBinding;
import com.ex.FitApp.models.entities.WorkoutEntity;

public interface WorkoutService {
    WorkoutEntity findByWorkoutName(String workoutName);

    void addWorkout(WorkoutAddBinding workoutModel);

    WorkoutEntity bindingToEntity(WorkoutAddBinding workoutModel);
}
