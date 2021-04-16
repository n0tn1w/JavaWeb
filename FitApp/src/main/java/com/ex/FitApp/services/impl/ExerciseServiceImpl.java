package com.ex.FitApp.services.impl;

import com.ex.FitApp.models.bindings.ExerciseAddBinding;
import com.ex.FitApp.models.entities.ExerciseEntity;
import com.ex.FitApp.models.entities.enums.BodyGroup;
import com.ex.FitApp.repositories.ExerciseRepository;
import com.ex.FitApp.services.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ModelMapper modelMapper;
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ModelMapper modelMapper, ExerciseRepository exerciseRepository) {
        this.modelMapper = modelMapper;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public ExerciseEntity findByExName(String name) {
        return this.exerciseRepository.findByExName(name).orElse(null);
    }

    @Override
    public void addExercise(ExerciseAddBinding exerciseAddBinding) {
        ExerciseEntity exerciseEntity=this.modelMapper.map(exerciseAddBinding,ExerciseEntity.class);
        this.exerciseRepository.save(exerciseEntity);
    }
}
