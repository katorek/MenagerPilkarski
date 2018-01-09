package com.meneger.controllers.Boiska;

import com.meneger.controllers.AbstractRestController;
import com.meneger.model.boisko.Orlik;
import com.meneger.repositories.OrlikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = OrlikRestController.CONTROLLER_BASE)
public class OrlikRestController extends AbstractRestController<Orlik, OrlikRepository> {
    static final String CONTROLLER_BASE = "/orliki";

//    private final OrlikRepository orlikRepository;

    @Autowired
    public OrlikRestController(OrlikRepository orlikRepository) {
        super(orlikRepository);
//        this.orlikRepository = orlikRepository;
    }

    @GetMapping
    public ResponseEntity<List<Orlik>> getList(){
        List<Orlik> orliki = repository.findAll();
        orliki.forEach(e -> e.getBoisko().clear());
        return ResponseEntity.ok(orliki);
    }

//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity delete(@PathVariable Integer id) {
//        try {
//            repository.delete(id);
//        } catch (Exception e) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok().build();
//    }
//
    @PostMapping
    public ResponseEntity add(@RequestBody Orlik orlik) {
        try {
            orlik.setId(0);
            repository.save(orlik);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok().build();
    }
//
//    @PutMapping(path = "/{id}")
//    public ResponseEntity update(@PathVariable Integer id,
//                                 @RequestBody Orlik orlik) {
//        if (!repository.exists(id))
//            return ResponseEntity.notFound().build();
//        try {
//            repository.save(orlik);
//        } catch (Exception e) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok().build();
//    }

}
