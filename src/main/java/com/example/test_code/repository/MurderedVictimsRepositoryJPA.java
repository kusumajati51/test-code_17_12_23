package com.example.test_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.example.test_code.model.MurderedVictimsModel;
import java.util.List;

@Component
public interface MurderedVictimsRepositoryJPA extends JpaRepository<MurderedVictimsModel, Long> {

    List<MurderedVictimsModel> findByName(String name);

    List<MurderedVictimsModel> findByDeathAge(Integer deathAge);

    List<MurderedVictimsModel> findByMurderedYear(Integer murderedYear);

    @Query("SELECT e FROM MurderedVictimsModel e WHERE e.birthYear = (?1 - ?2)")
    List<MurderedVictimsModel> findByBirthAge(Integer murderedYear, Integer deathAge);
    
}
