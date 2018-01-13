package com.meneger.controllers;

import com.meneger.model.boisko.Boisko;
import com.meneger.model.mecz.Bilet;
import com.meneger.model.mecz.Mecz;
import com.meneger.repositories.BoiskoRepository;
import com.meneger.repositories.MeczRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = MeczRestController.CONTROLLER_BASE)
public class MeczRestController extends AbstractRestController<Mecz, MeczRepository>{
    static final String CONTROLLER_BASE = "/mecze";

    private BoiskoRepository boiskoRepository;

    @Autowired
    public MeczRestController(MeczRepository meczRepository, BoiskoRepository boiskoRepository) {
        super(meczRepository);
        this.boiskoRepository = boiskoRepository;
    }

    private Boisko findByName(List<Boisko> boiska, String nazwa){
        return (Boisko) boiska.stream().filter(e -> e.getNazwa().equals(nazwa)).toArray()[0];
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Mecz mecz) {



        System.err.println("Add T: "+ mecz.getClass().getCanonicalName()+", "+mecz.toString());
        try {
            Boisko b = findByName(boiskoRepository.findAll(), mecz.getBoisko().getNazwa());

            mecz.setBoisko(b);

            repository.save(mecz);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
