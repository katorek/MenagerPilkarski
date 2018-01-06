package com.meneger.controllers.Boiska;

import com.meneger.controllers.AbstractRestController;
import com.meneger.model.boisko.Orlik;
import com.meneger.repositories.OrlikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = OrlikRestController.CONTROLLER_BASE)
public class OrlikRestController extends AbstractRestController {
    static final String CONTROLLER_BASE = "/orliki";

    private final OrlikRepository orlikRepository;

    @Autowired
    public OrlikRestController(OrlikRepository orlikRepository) {
        this.orlikRepository = orlikRepository;
    }

    @GetMapping
    public ResponseEntity<List<Orlik>> getOrliki(){
        List<Orlik> orliki = orlikRepository.findAll();
        orliki.forEach(e -> {
            e.getBoisko().setOrlik(null);
            e.getBoisko().setStadion(null);
        });
        return ResponseEntity.ok(orliki);
    }
}
