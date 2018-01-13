package com.meneger.controllers;

import com.meneger.model.druzyna.Druzyna;
import com.meneger.model.osoba.Pilkarz;
import com.meneger.repositories.PilkarzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = PilkarzRestController.CONTROLLER_BASE)
public class PilkarzRestController extends AbstractRestController<Pilkarz, PilkarzRepository> {
    static final String CONTROLLER_BASE = "/pilkarze";

    private final PilkarzRepository pilkarzRepository;

    @Autowired
    public PilkarzRestController(PilkarzRepository pilkarzRepository) {
        super(pilkarzRepository);
        this.pilkarzRepository = pilkarzRepository;
    }


}
