package com.example.test_code.service;

import org.springframework.stereotype.Component;

import com.example.test_code.model.MurderedVictimsModel;

@Component
public interface PaternDeathService {
    public MurderedVictimsModel save( MurderedVictimsModel model);
    public void generated(int year);
    public Float averageDeath();
}
