//package com.meneger.controllers;
//
//import com.meneger.model.druzyna.Druzyna;
//import com.meneger.model.druzyna.Sponsor;
//import com.meneger.model.mecz.Bilet;
//import com.meneger.model.osoba.Kibic;
//import com.meneger.repositories.KibicRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = KibiceRestController.CONTROLLER_BASE)
//public class KibiceRestController extends AbstractRestController<Kibic>{
//    static final String CONTROLLER_BASE = "/kibice";
//
//    private final KibicRepository kibicRepository;
//
//    @Autowired
//    public KibiceRestController(KibicRepository kibicRepository) {
//        this.kibicRepository = kibicRepository;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Kibic>> getList(){
//        List<Kibic> kibice = kibicRepository.findAll();
//        kibice.forEach(e->{
//            e.getBilety().forEach(Bilet::clear);
//        });
//        return ResponseEntity.ok(kibice);
//    }
//
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity delete(@PathVariable Integer id) {
//        if (!kibicRepository.exists(id)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        kibicRepository.delete(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping
//    public ResponseEntity add(@RequestBody Kibic kibic) {
//        if (kibicRepository.exists(kibic.getId()))
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
//        kibicRepository.save(kibic);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping(path = "/{id}")
//    public ResponseEntity update(@PathVariable Integer id,
//                                       @RequestBody Kibic kibic) {
//        if (!kibicRepository.exists(id))
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        kibic.setId(id);
//        try{
//            kibicRepository.save(kibic);
//        }catch (Exception e){
//            return ResponseEntity.unprocessableEntity().body(e.getCause());
//        }
//        return ResponseEntity.ok().build();
//    }
//
//}
