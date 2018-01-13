package com.meneger.controllers;

import com.meneger.model.druzyna.Liga;
import com.meneger.repositories.LigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = LigiRestController.CONTROLLER_BASE)
public class LigiRestController extends AbstractRestController<Liga, LigaRepository> {
    static final String CONTROLLER_BASE = "/ligi";

    @Autowired
    public LigiRestController(LigaRepository ligaRepository) {
        super(ligaRepository);
    }
}
