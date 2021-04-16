package com.ex.FitApp.services.impl;

import com.ex.FitApp.repositories.WorkoutRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WorkoutServiceImpl {
    private final ModelMapper modelMapper;
    private final WorkoutRepository workoutRepository;

    public WorkoutServiceImpl(ModelMapper modelMapper, WorkoutRepository workoutRepository) {
        this.modelMapper = modelMapper;
        this.workoutRepository = workoutRepository;
    }
}
