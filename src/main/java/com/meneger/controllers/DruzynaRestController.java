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
public class DruzynaRestController extends AbstractRestController {
    static final String CONTROLLER_BASE = "/druzyny";

    private final DruzynaRepository teamRepository;

    @Autowired
    public DruzynaRestController(DruzynaRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping(produces = HAL_JSON)
    public ResponseEntity<List<Druzyna>> druzynaList() {
        List<Druzyna> druzyny = teamRepository.findAll();
        druzyny.forEach(Druzyna::clear);
        return ResponseEntity.ok(druzyny);
    }

    @GetMapping(path = "/{druzynaId}", produces = HAL_JSON)
    public Druzyna getDruzyna(@PathVariable Integer druzynaId) {
        return teamRepository.findOne(druzynaId);
    }

    @DeleteMapping(path = "/{teamId}")
    public ResponseEntity deleteTeam(@PathVariable Integer teamId) {
        if (!teamRepository.exists(teamId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        teamRepository.delete(teamId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity addTeam(@RequestBody Druzyna team) {
        if (teamRepository.exists(team.getId()))
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        teamRepository.save(team);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{teamId}")
    public ResponseEntity updateTeam(@PathVariable Integer teamId,
                                     @RequestBody Druzyna team) {
        if (!teamRepository.exists(teamId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        team.setId(teamId);
        teamRepository.save(team);
        return ResponseEntity.ok().build();
    }

}
