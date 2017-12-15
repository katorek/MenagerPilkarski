package com.meneger.controllers;

import com.meneger.model.druzyna.Druzyna;
import com.meneger.repositories.DruzynaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = DruzynaRestController.CONTROLLER_BASE)
public class DruzynaRestController extends AbstractRestController{
    public static final String CONTROLLER_BASE = "/druzyny";

    @Autowired
    private DruzynaRepository teamRepository;

    @GetMapping(produces = HAL_JSON)
    public List<Druzyna> druzynaList(){
        return teamRepository.findAll();
    }

    @GetMapping(path="/{druzynaId}", produces = HAL_JSON)
    public Druzyna getDruzyna(@PathVariable Integer druzynaId){
        return teamRepository.findOne(druzynaId);
    }

    @DeleteMapping(path="/{teamId}")
    public ResponseEntity deleteTeam(@PathVariable Integer teamId){
        teamRepository.delete(teamId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity addTeam(@RequestBody Druzyna team){
        System.err.println("New team: "+team.toString());

        teamRepository.save(team);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{teamId}")
    public ResponseEntity updateTeam(@PathVariable Integer teamId, @RequestBody Druzyna team){
        System.err.println("Updating team: "+team);
        if(teamRepository.getOne(teamId) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        team.setId(teamId);
        teamRepository.save(team);
        return ResponseEntity.ok().build();
    }

}
