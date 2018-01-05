package com.meneger.controllers.Boiska;

import com.meneger.model.boisko.Boisko;
import com.meneger.model.boisko.Stadion;
import com.meneger.repositories.OrlikRepository;
import com.meneger.repositories.StadionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = StadionRestController.CONTROLLER_BASE)
public class StadionRestController {
    static final String CONTROLLER_BASE = "/stadiony";

    private final StadionRepository stadionRepository;

    @Autowired
    public StadionRestController(StadionRepository stadionRepository) {
        this.stadionRepository = stadionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Stadion>> getStadiony() {
        List<Stadion> stadiony;
        try {
            stadiony = stadionRepository.findAll();
//            stadiony.forEach(System.out::println);

        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(stadiony);
    }


}
