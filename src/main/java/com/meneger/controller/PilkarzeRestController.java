package com.meneger.controller;

import com.meneger.model.osoba.Pilkarz;
import com.meneger.repositories.PilkarzeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = PilkarzeRestController.CONTROLLER_BASE)
public class PilkarzeRestController extends AbstractRestController {
    public static final String CONTROLLER_BASE = "/pilkarze";

    @Autowired
    private PilkarzeRepository pilkarzeRepository;

    @GetMapping(produces = HAL_JSON)
    public List<Pilkarz> pilkarzeList(){
        return pilkarzeRepository.findAll();
    }

    @GetMapping(produces = HAL_JSON, path="/{pilkarzId}")
    public Pilkarz readPilkarz(@PathVariable Integer pilkarzId){
        return pilkarzeRepository.findOne(pilkarzId);
    }

}
