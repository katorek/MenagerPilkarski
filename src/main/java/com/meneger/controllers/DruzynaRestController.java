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
public class DruzynaRestController extends AbstractRestController<Druzyna, DruzynaRepository> {
    static final String CONTROLLER_BASE = "/druzyny";

    @Autowired
    public DruzynaRestController(DruzynaRepository teamRepository) {
        super(teamRepository);
    }


}
