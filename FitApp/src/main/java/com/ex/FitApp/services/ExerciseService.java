package com.ex.FitApp.services;

import com.ex.FitApp.models.bindings.ExerciseAddBinding;
import com.ex.FitApp.models.entities.ExerciseEntity;

public interface ExerciseService {
    ExerciseEntity findByExName(String name);

    void addExercise(ExerciseAddBinding exerciseAddBinding);
}
