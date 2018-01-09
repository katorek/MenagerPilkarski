//package com.meneger.controllers;
//
//import com.meneger.model.mecz.Bilet;
//import com.meneger.repositories.BiletRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(BiletRestController.CONTROLLER_BASE)
//public class BiletRestController extends AbstractRestController<Bilet>{
//    static final String CONTROLLER_BASE = "/bilety";
//
//    private BiletRepository biletRepository;
//
//    @Autowired
//    public BiletRestController(BiletRepository biletRepository) {
//        this.biletRepository = biletRepository;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Bilet>> getList() {
//        List<Bilet> bilety = biletRepository.findAll();
//        bilety.forEach(e -> {
//            e.getKibic().clear();
//            e.getMecz().clear();
//        });
//        return ResponseEntity.ok(bilety);
//    }
//
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity delete(@PathVariable Integer id) {
//        try {
//            biletRepository.delete(id);
//        } catch (Exception e) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping
//    public ResponseEntity add(@RequestBody Bilet bilet) {
//        try {
//            bilet.setId(0);
//            biletRepository.save(bilet);
//        } catch (Exception e) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping(path = "/{id}")
//    public ResponseEntity update(@PathVariable Integer id,
//                                 @RequestBody Bilet bilet) {
//        if (!biletRepository.exists(id))
//            return ResponseEntity.notFound().build();
//        try {
//            biletRepository.save(bilet);
//        } catch (Exception e) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok().build();
//    }
//}
