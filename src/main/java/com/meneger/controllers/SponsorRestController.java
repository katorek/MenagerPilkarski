package com.meneger.controllers;

import com.meneger.model.druzyna.Sponsor;
import com.meneger.repositories.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = SponsorRestController.CONTROLLER_BASE)
public class SponsorRestController extends AbstractRestController{
    static final String CONTROLLER_BASE = "/sponsorzy";

    private final SponsorRepository sponsorRepository;

    @Autowired
    public SponsorRestController(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    @GetMapping(produces = HAL_JSON)
    private ResponseEntity<List<Sponsor>> getSponsorow(){
        List<Sponsor> sponsorzy = sponsorRepository.findAll();
        sponsorzy.forEach(e -> e.getDruzyna().clear());
        return ResponseEntity.ok(sponsorzy);
    }

    @DeleteMapping(path = "/{sponsorId}")
    public ResponseEntity deletePlayer(@PathVariable Integer sponsorId) {
        try{
            sponsorRepository.delete(sponsorId);
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity addSponsor(@RequestBody Sponsor sponsor){
        sponsor.setId(0);
        try{
            sponsorRepository.save(sponsor);
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{sponsorId}")
    public ResponseEntity updateSponsor(@PathVariable Integer sponsorId,
                                        @RequestBody Sponsor sponsor){
        sponsor.setId(sponsorId);
        try{
            sponsorRepository.save(sponsor);
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok().build();
    }

}
