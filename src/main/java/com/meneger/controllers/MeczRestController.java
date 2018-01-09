//package com.meneger.controllers;
//
//import com.meneger.model.mecz.Bilet;
//import com.meneger.model.mecz.Mecz;
//import com.meneger.repositories.MeczRepository;
//import com.sun.org.apache.regexp.internal.RE;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = MeczRestController.CONTROLLER_BASE)
//public class MeczRestController extends AbstractRestController<Mecz>{
//    static final String CONTROLLER_BASE = "/mecze";
//
//    private final MeczRepository meczRepository;
//
//    @Autowired
//    public MeczRestController(MeczRepository meczRepository) {
//        this.meczRepository = meczRepository;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Mecz>> getList(){
//        List<Mecz> mecze = meczRepository.findAll();
//        mecze.forEach(e->{
//            e.getBilety().forEach(Bilet::clear);
//            e.getBoisko().clear();
//            e.getGospodarz().clear();
//            e.getPrzyjezdni().clear();
//        });
//        return ResponseEntity.ok(mecze);
//    }
//
//    @DeleteMapping(path="/{id}")
//    public ResponseEntity delete(@PathVariable Integer id){
//        try{
//            meczRepository.delete(id);
//        }catch(Exception e){
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping
//    public ResponseEntity add(@RequestBody Mecz mecz){
//        try{
//            mecz.setId(0);
//            meczRepository.save(mecz);
//        }catch(Exception e){
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping(path = "/{id}")
//    public ResponseEntity update(@PathVariable Integer id,
//                                 @RequestBody Mecz mecz){
//        if(!meczRepository.exists(id))
//            return ResponseEntity.notFound().build();
//        try{
//            meczRepository.save(mecz);
//        }catch(Exception e){
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok().build();
//    }
//}
