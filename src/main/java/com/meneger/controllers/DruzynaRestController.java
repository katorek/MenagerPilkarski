package com.meneger.controllers;

import com.meneger.model.druzyna.Druzyna;
import com.meneger.repositories.DruzynaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = DruzynaRestController.CONTROLLER_BASE)
public class DruzynaRestController extends AbstractRestController{
    public static final String CONTROLLER_BASE = "/druzyny";

    @Autowired
    private DruzynaRepository druzynaRepository;

    @GetMapping(produces = HAL_JSON)
    public List<Druzyna> druzynaList(){
        return druzynaRepository.findAll();
    }

    @GetMapping(path="/{druzynaId}")
    public Druzyna getDruzyna(@PathVariable Integer druzynaId){
        return druzynaRepository.findOne(druzynaId);
    }
}
