package com.ex.FitApp.services.impl;

import com.ex.FitApp.models.bindings.WorkoutAddBinding;
import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.models.entities.WorkoutEntity;
import com.ex.FitApp.repositories.UserRepository;
import com.ex.FitApp.repositories.WorkoutRepository;
import com.ex.FitApp.services.ExerciseService;
import com.ex.FitApp.services.WorkoutService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private final ModelMapper modelMapper;
    private final WorkoutRepository workoutRepository;
    private final ExerciseService exerciseService;
    private final UserRepository userRepository;

    public WorkoutServiceImpl(ModelMapper modelMapper, WorkoutRepository workoutRepository, ExerciseService exerciseService, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.workoutRepository = workoutRepository;
        this.exerciseService = exerciseService;
        this.userRepository = userRepository;
        ;
    }

    @Override
    public WorkoutEntity findByWorkoutName(String workoutName) {
        return this.workoutRepository.findByWorkoutName(workoutName).orElse(null);
    }

    @Override
    public void addWorkout(WorkoutAddBinding workoutModel) {
        WorkoutEntity workoutEntity=this.modelMapper.map(workoutModel,WorkoutEntity.class);

        if(workoutEntity.getExercises().size() !=0){
            workoutEntity.setExercises(new ArrayList<>());
        }

        for (String exercise : workoutModel.getExercisesNames()) {
            workoutEntity.getExercises().add(this.exerciseService.findByExName(exercise));
        }


        this.workoutRepository.save(workoutEntity);
    }

    @Override
    public WorkoutEntity bindingToEntity(WorkoutAddBinding workoutModel) {
        WorkoutEntity workoutEntity=this.modelMapper.map(workoutModel,WorkoutEntity.class);

        if(workoutEntity.getExercises().size() !=0){
            workoutEntity.setExercises(new ArrayList<>());
        }

        for (String exercise : workoutModel.getExercisesNames()) {
            workoutEntity.getExercises().add(this.exerciseService.findByExName(exercise));
        }
        return workoutEntity;
    }


}
