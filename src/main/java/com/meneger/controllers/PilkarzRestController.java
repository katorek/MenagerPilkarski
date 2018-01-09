//package com.meneger.controllers;
//
//import com.meneger.model.druzyna.Druzyna;
//import com.meneger.model.osoba.Pilkarz;
//import com.meneger.repositories.PilkarzRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = PilkarzRestController.CONTROLLER_BASE)
//public class PilkarzRestController extends AbstractRestController<Pilkarz> {
//    static final String CONTROLLER_BASE = "/pilkarze";
//
//    private final PilkarzRepository pilkarzRepository;
//
//    @Autowired
//    public PilkarzRestController(PilkarzRepository pilkarzRepository) {
//        this.pilkarzRepository = pilkarzRepository;
//    }
//
//    @GetMapping(produces = HAL_JSON)
//    public ResponseEntity<List<Pilkarz>> getList() {
//        List<Pilkarz> pilkarze = pilkarzRepository.findAll();
//        pilkarze.forEach(e -> e.getDruzyna().clear());
//
//        return ResponseEntity.ok(pilkarze);
//    }
//
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity delete(@PathVariable Integer id) {
//        if (!pilkarzRepository.exists(id)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        pilkarzRepository.delete(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping
//    public ResponseEntity add(@RequestBody Pilkarz pilkarz) {
//        if (pilkarzRepository.exists(pilkarz.getId()))
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
//        pilkarzRepository.save(pilkarz);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping(path = "/{id}")
//    public ResponseEntity update(@PathVariable Integer id,
//                                       @RequestBody Pilkarz pilkarz) {
//        if (!pilkarzRepository.exists(id))
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        pilkarz.setId(id);
//        try{
//            pilkarzRepository.save(pilkarz);
//        }catch (Exception e){
//            return ResponseEntity.unprocessableEntity().body(e.getCause());
//        }
//        return ResponseEntity.ok().build();
//    }
//
//}
