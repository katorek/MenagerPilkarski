package com.meneger.controllers;

import com.meneger.model.druzyna.Druzyna;
import com.meneger.model.druzyna.Sponsor;
import com.meneger.model.mecz.Bilet;
import com.meneger.model.osoba.Kibic;
import com.meneger.repositories.KibicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = KibiceRestController.CONTROLLER_BASE)
public class KibiceRestController extends AbstractRestController<Kibic, KibicRepository>{
    static final String CONTROLLER_BASE = "/kibice";

    @Autowired
    public KibiceRestController(KibicRepository kibicRepository) {
        super(kibicRepository);
    }


}
