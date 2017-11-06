package com.meneger.controllers;

import com.meneger.model.osoba.Pilkarz;
import com.meneger.repositories.PilkarzRepository;
import com.meneger.resourceProcessors.PilkarzResourceProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = PilkarzRestController.CONTROLLER_BASE)
public class PilkarzRestController extends AbstractRestController {
    public static final String CONTROLLER_BASE = "/pilkarze";

    @Autowired
    private PilkarzResourceProcessor pilkarzResourceProcessor;

    @Autowired
    private PilkarzRepository pilkarzRepository;

    @GetMapping(produces = HAL_JSON)
    public List<Pilkarz> pilkarzeList(){
        return pilkarzRepository.findAll();
    }

    @GetMapping(path="/{pilkarzId}")
    public Pilkarz readPilkarz(@PathVariable Integer pilkarzId){
        Pilkarz p = pilkarzRepository.findOne(pilkarzId);
        return p;
    }

}
