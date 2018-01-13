package com.meneger.controllers.Boiska;

import com.meneger.controllers.AbstractRestController;
import com.meneger.model.boisko.Boisko;
import com.meneger.model.boisko.Stadion;
import com.meneger.repositories.BoiskoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = BoiskoRestController.CONTROLLER_BASE)
public class BoiskoRestController extends AbstractRestController<Boisko, BoiskoRepository> {
    static final String CONTROLLER_BASE = "/boiska";

    @Autowired
    public BoiskoRestController(BoiskoRepository repository) {
        super(repository);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestBody Boisko boisko) {
        if (boisko.getStadion() != null) {
            Integer idStadionu = ((Boisko) repository.findAll().stream().filter(e -> e.getId().equals(id)).toArray()[0]).getStadion().getId();
            System.err.println("UPDATING STADION o id: " + idStadionu);

            Stadion s = boisko.getStadion();
            s.setId(idStadionu);
            boisko.setStadion(s);

//            System.err.println("id before: " + boisko.getStadion().getId());
//            boisko.getStadion().setId(idStadionu);
            System.err.println("id after: " + boisko.getStadion().getId());

            boisko.setId(id);

            try {
                boisko.setId(id);
                repository.save(boisko);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.unprocessableEntity().build();
            }

        } else {

            System.err.println("UPDATING ORLIK");

            if (!repository.exists(id))
                return ResponseEntity.notFound().build();
            try {
                boisko.setId(id);
                repository.save(boisko);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.unprocessableEntity().build();
            }
        }
//        return ResponseEntity.unprocessableEntity().build();

//        System.err.println("Update id:"+id+", T: "+ boisko.getClass().getCanonicalName()+", "+boisko.toString());
    }
}
