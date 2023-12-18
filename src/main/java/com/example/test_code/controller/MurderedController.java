package com.example.test_code.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.test_code.dto.request.VictimsRequest;
import com.example.test_code.dto.response.BaseResponse;
import com.example.test_code.model.MurderedVictimsModel;
import com.example.test_code.repository.MurderedVictimsRepositoryJPA;
import com.example.test_code.service.PaternDeathService;
import com.google.gson.Gson;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController("case")
public class MurderedController {

    @Autowired
    MurderedVictimsRepositoryJPA murderedVictimsRepositoryJPA;

    @Autowired
    Gson gson;

    @Autowired
    PaternDeathService paternDeathService;

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public @ResponseBody BaseResponse<MurderedVictimsModel> addReport(
            @Valid @RequestBody final VictimsRequest request) {
        BaseResponse<MurderedVictimsModel> response = new BaseResponse<>();
        int birthYear = request.getMurderedYear() - request.getDeathAge();
        MurderedVictimsModel model;
        model = paternDeathService.save(new MurderedVictimsModel(request.getName(),
                request.getDeathAge(),
                birthYear,
                request.getMurderedYear()));
        if (birthYear > 0) {
            paternDeathService.generated(birthYear);
        }
        response.setMessage("ok");
        response.setStatus(true);
        response.setData(model);

        return response;
    }

    // @GetMapping("paternDeath/{year}")
    // public @ResponseBody BaseResponse<HashMap<String, Object>>
    // paternOfDeath(@PathVariable int year) {
    // BaseResponse<HashMap<String, Object>> response = new BaseResponse<>();
    // int totalDeath = 0;
    // List<Integer> listAddDeath = new ArrayList<>();
    // List<HashMap<String, Object>> list = new ArrayList<>();
    // HashMap<String, Object> collection = new HashMap<>();
    // for (int i = 0; i < year; i++) {
    // if (i == 0) {
    // listAddDeath.add(1);
    // } else if (i == 1) {
    // listAddDeath.add(i);

    // } else {
    // listAddDeath.add(listAddDeath.get(i - 1) + listAddDeath.get(i - 2));
    // }
    // }
    // System.out.println();
    // for (int i = 0; i < listAddDeath.size(); i++) {
    // HashMap<String, Object> inMap = new HashMap<>();
    // totalDeath += listAddDeath.get(i);
    // inMap.put("year", i + 1);
    // inMap.put("death", totalDeath);
    // list.add(inMap);

    // }
    // collection.put("list", list);
    // collection.put("listD", listAddDeath);

    // response.setStatus(true);
    // response.setMessage("ok");
    // response.setData(collection);

    // return response;
    // }

    // @GetMapping("createPaternDeath/{year}")
    // public @ResponseBody String generate(@PathVariable int year) {
    // this.paternDeathService.generated(year);
    // return "ok";
    // }

    @GetMapping("average")
    public @ResponseBody BaseResponse<Float> average() {
        BaseResponse<Float> response = new BaseResponse<>();
        Float average = this.paternDeathService.averageDeath();
        response.setData(average);
        response.setMessage("ok");
        response.setStatus(true);
        return response;
    }

}
