package com.meneger.controllers.Boiska;

import com.meneger.controllers.AbstractRestController;
import com.meneger.model.boisko.Boisko;
import com.meneger.model.boisko.Stadion;
import com.meneger.repositories.OrlikRepository;
import com.meneger.repositories.StadionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = StadionRestController.CONTROLLER_BASE)
public class StadionRestController extends AbstractRestController<Stadion, StadionRepository> {
    static final String CONTROLLER_BASE = "/stadiony";

    @Autowired
    public StadionRestController(StadionRepository stadionRepository) {
        super(stadionRepository);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestBody Stadion t) {
        if (!repository.exists(id))
            return ResponseEntity.notFound().build();
        try {
            Integer boiskoId = repository.findOne(id).getBoisko().getId();
            Boisko b = t.getBoisko();
            b.setId(boiskoId);
            t.setBoisko(b);

            t.setId(id);
            repository.save(t);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok().build();
    }

}
