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
public class SponsorRestController extends AbstractRestController<Sponsor, SponsorRepository>{
    static final String CONTROLLER_BASE = "/sponsorzy";

    @Autowired
    public SponsorRestController(SponsorRepository sponsorRepository) {
        super(sponsorRepository);
    }


}
