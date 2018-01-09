//package com.meneger.controllers.Boiska;
//
//import com.meneger.controllers.AbstractRestController;
//import com.meneger.model.boisko.Boisko;
//import com.meneger.model.boisko.Stadion;
//import com.meneger.repositories.OrlikRepository;
//import com.meneger.repositories.StadionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = StadionRestController.CONTROLLER_BASE)
//public  class StadionRestController extends AbstractRestController<Stadion>{
//    static final String CONTROLLER_BASE = "/stadiony";
//
//    private final StadionRepository stadionRepository;
//
//    @Autowired
//    public StadionRestController(StadionRepository stadionRepository) {
//        this.stadionRepository = stadionRepository;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Stadion>> getList() {
//        List<Stadion> stadiony;
//        try {
//            stadiony = stadionRepository.findAll();
//            stadiony.forEach(e -> e.getBoisko().clear());
//
//        } catch (Exception e) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok(stadiony);
//    }
//
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity delete(@PathVariable Integer id) {
//        try {
//            stadionRepository.delete(id);
//        } catch (Exception e) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping
//    public ResponseEntity add(@RequestBody Stadion stadion) {
//        try {
//            stadion.setId(0);
//            stadionRepository.save(stadion);
//        } catch (Exception e) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping(path = "/{id}")
//    public ResponseEntity update(@PathVariable Integer id,
//                                 @RequestBody Stadion stadion) {
//        if (!stadionRepository.exists(id))
//            return ResponseEntity.notFound().build();
//        try {
//            stadionRepository.save(stadion);
//        } catch (Exception e) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok().build();
//    }
//
//
//}
