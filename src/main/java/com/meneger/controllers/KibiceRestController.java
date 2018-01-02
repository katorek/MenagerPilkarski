package com.meneger.controllers;

import com.meneger.model.druzyna.Druzyna;
import com.meneger.model.druzyna.Sponsor;
import com.meneger.model.osoba.Kibic;
import com.meneger.repositories.KibicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = KibiceRestController.CONTROLLER_BASE)
public class KibiceRestController {
    static final String CONTROLLER_BASE = "/kibice";

    private final KibicRepository kibicRepository;

    @Autowired
    public KibiceRestController(KibicRepository kibicRepository) {
        this.kibicRepository = kibicRepository;
    }

    @GetMapping
    public ResponseEntity<List<Kibic>> getKibice(){
        List<Kibic> kibice = kibicRepository.findAll();
        return ResponseEntity.ok(kibice);
    }

    @DeleteMapping(path = "/{kibicId}")
    public ResponseEntity deletePlayer(@PathVariable Integer kibicId) {
        if (!kibicRepository.exists(kibicId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        kibicRepository.delete(kibicId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity addSponsor(@RequestBody Kibic kibic) {
        if (kibicRepository.exists(kibic.getId()))
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        kibicRepository.save(kibic);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{kibicId}")
    public ResponseEntity updateSponsor(@PathVariable Integer kibicId,
                                       @RequestBody Kibic kibic) {
        if (!kibicRepository.exists(kibicId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        kibic.setId(kibicId);
        try{
            kibicRepository.save(kibic);
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().body(e.getCause());
        }
        return ResponseEntity.ok().build();
    }

}
