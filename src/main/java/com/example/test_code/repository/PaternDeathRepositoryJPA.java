package com.example.test_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.test_code.model.PaternDeathModel;
import java.util.List;


@Component
public interface PaternDeathRepositoryJPA extends JpaRepository<PaternDeathModel, Long> {
     PaternDeathModel findByYear(int year);
}
