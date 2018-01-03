package com.meneger.controllers.Bosika;

import com.meneger.model.boisko.Boisko;
import com.meneger.repositories.BoiskoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = BoiskoRestController.CONTROLLER_BASE)
public class BoiskoRestController {
    static final String CONTROLLER_BASE = "/bosika";

    final
    BoiskoRepository boiskoRepository;

    @Autowired
    public BoiskoRestController(BoiskoRepository boiskoRepository) {
        this.boiskoRepository = boiskoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Boisko>> getBoiska(){
        List<Boisko> boiska;
        try{
            boiska = boiskoRepository.findAll();
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(boiska);
    }


}
