package com.ex.FitApp.repositories;

import com.ex.FitApp.models.entities.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity,Long> {

    Optional<ExerciseEntity> findByExName(String name);
}
