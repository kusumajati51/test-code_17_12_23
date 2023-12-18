package com.example.test_code.service.impl;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test_code.model.MurderedVictimsModel;
import com.example.test_code.model.PaternDeathModel;
import com.example.test_code.repository.MurderedVictimsRepositoryJPA;
import com.example.test_code.repository.PaternDeathRepositoryJPA;
import com.example.test_code.service.PaternDeathService;

import jakarta.transaction.Transactional;
import java.util.*;

@Service("paternDeathServiceImpl")
@Transactional
public class PaternDeathServiceImpl implements PaternDeathService {
    @Autowired
    PaternDeathRepositoryJPA paternDeathRepositoryJPA;

    @Autowired
    MurderedVictimsRepositoryJPA mRepositoryJPA;

    @Override
    public MurderedVictimsModel save(MurderedVictimsModel model) {
        List<MurderedVictimsModel> murderedVictims = mRepositoryJPA.findByBirthAge(model.getMurderedYear(),
                model.getDeathAge());
        PaternDeathModel pDeathModel = paternDeathRepositoryJPA.findByYear(model.getBirthYear());
        if(!murderedVictims.isEmpty() && pDeathModel != null) {
            if(murderedVictims.size() == pDeathModel.getDeathVictims()) {
                throw new ValidationException("You has reached the death limit, please wait until next year");
            } else {
                return mRepositoryJPA.save(model);
            }
        } else {
            return mRepositoryJPA.save(model);
        }
    }

    @Override
    public void generated(int year) {

        int totalDeath = 0;
        List<Integer> listAddDeath = new ArrayList<>();
        List<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> collection = new HashMap<>();
        List<PaternDeathModel> models = new ArrayList<>();
        for (int i = 0; i < year; i++) {
            if (i == 0) {
                listAddDeath.add(1);
            } else if (i == 1) {
                listAddDeath.add(i);

            } else {
                listAddDeath.add(listAddDeath.get(i - 1) + listAddDeath.get(i - 2));
            }
        }
        for (int i = 0; i < listAddDeath.size(); i++) {
            HashMap<String, Object> inMap = new HashMap<>();
            totalDeath += listAddDeath.get(i);
            PaternDeathModel findModel = paternDeathRepositoryJPA.findByYear(i + 1);
            if (findModel == null) {
                inMap.put("year", i + 1);
                inMap.put("death", totalDeath);
                list.add(inMap);
                PaternDeathModel model = new PaternDeathModel();
                model.setYear(i + 1);
                model.setDeathVictims(totalDeath);
                models.add(model);
            }
        }
        for (PaternDeathModel model : models) {
            paternDeathRepositoryJPA.save(model);
        }
    }

    @Override
    public Float averageDeath() {
        List<Integer> deathCount = new ArrayList<>();
        List<MurderedVictimsModel> murderedVictimsModels = mRepositoryJPA.findAll();
        for (MurderedVictimsModel murderedVictimsModel : murderedVictimsModels) {
            PaternDeathModel paternDeathModel = paternDeathRepositoryJPA
                    .findByYear(murderedVictimsModel.getBirthYear());
            deathCount.add(paternDeathModel.getDeathVictims());
        }
        Float sum = (float) 0;
        Float average;
        for (Integer death : deathCount) {
            sum += death;
        }

        average = sum / deathCount.size();

        return average;
    }

}
