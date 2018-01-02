package com.meneger.controllers;

import com.meneger.model.druzyna.Druzyna;
import com.meneger.model.osoba.Pilkarz;
import com.meneger.repositories.PilkarzRepository;
import com.meneger.resourceProcessors.PilkarzResourceProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = PilkarzRestController.CONTROLLER_BASE)
public class PilkarzRestController extends AbstractRestController {
    static final String CONTROLLER_BASE = "/pilkarze";

    private final PilkarzRepository pilkarzRepository;

    @Autowired
    public PilkarzRestController(PilkarzRepository pilkarzRepository) {
        this.pilkarzRepository = pilkarzRepository;
    }

    @GetMapping(produces = HAL_JSON)
    public List<Pilkarz> pilkarzeList() {
        List<Pilkarz> pilkarze = pilkarzRepository.findAll();
        pilkarze.forEach(e -> e.getDruzyna().clear());

        return pilkarze;
    }

    @GetMapping(path = "/{pilkarzId}")
    public Pilkarz readPilkarz(@PathVariable Integer pilkarzId) {
        Pilkarz p = pilkarzRepository.findOne(pilkarzId);
        return p;
    }

    @DeleteMapping(path = "/{playerId}")
    public ResponseEntity deletePlayer(@PathVariable Integer playerId) {
        if (!pilkarzRepository.exists(playerId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        pilkarzRepository.delete(playerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity addPlayer(@RequestBody Pilkarz pilkarz) {
//        System.err.println(obj);
//        Druzyna druzyna = json
//        Pilkarz pilkarz = (Pilkarz) obj;
        System.err.println(pilkarz.toString());
        Druzyna d = pilkarz.getDruzyna();

        if (pilkarzRepository.exists(pilkarz.getId()))
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        pilkarzRepository.save(pilkarz);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{pilkarzId}")
    public ResponseEntity updatePlayer(@PathVariable Integer pilkarzId,
                                       @RequestBody Pilkarz pilkarz) {
        System.err.println(pilkarz.toString());

        if (!pilkarzRepository.exists(pilkarzId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        pilkarz.setId(pilkarzId);
        try{
            pilkarzRepository.save(pilkarz);
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().body(e.getCause());
        }
        return ResponseEntity.ok().build();
    }

}
