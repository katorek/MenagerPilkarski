package com.meneger.controllers;

import com.meneger.model.mecz.Bilet;
import com.meneger.repositories.BiletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BiletRestController.CONTROLLER_BASE)
public class BiletRestController extends AbstractRestController<Bilet, BiletRepository>{
    static final String CONTROLLER_BASE = "/bilety";

    private BiletRepository biletRepository;

    @Autowired
    public BiletRestController(BiletRepository biletRepository) {
        super(biletRepository);
    }
}
