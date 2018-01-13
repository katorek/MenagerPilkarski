package com.meneger.controllers.Boiska;

import com.meneger.controllers.AbstractRestController;
import com.meneger.model.boisko.Boisko;
import com.meneger.model.boisko.Orlik;
import com.meneger.repositories.BoiskoRepository;
import com.meneger.repositories.OrlikRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = OrlikRestController.CONTROLLER_BASE)
public class OrlikRestController extends AbstractRestController<Orlik, OrlikRepository> {
    static final String CONTROLLER_BASE = "/orliki";



//    @Autowired
//    public OrlikRestController(OrlikRepository orlikRepository, BoiskoRepository boiskoRepository) {
//        super(orlikRepository,boiskoRepository);
//    }

//    @GetMapping
//    public ResponseEntity<List<Orlik>> getList(){
//        List<Orlik> orliki = repository.findAll();
//        orliki.forEach(e -> e.getBoisko().clear());
//        return ResponseEntity.ok(orliki);
//    }
    private final BoiskoRepository boiskoRepository;

//    private final OrlikRepository orlikRepository;

    @Autowired
    public OrlikRestController(OrlikRepository orlikRepository, BoiskoRepository boiskoRepository) {
        super(orlikRepository);
        this.boiskoRepository = boiskoRepository;
    }

//    @Override
//    @PostMapping
//    public ResponseEntity add(@RequestBody Orlik orlik) {
//        System.err.println("Orlik POST: "+orlik.toString());
//        orlik.setId(0);
//        Boisko b = orlik.getBoisko();
//        System.err.println("B: "+b.toString());
//        //todo wyszykac
//        try{
////            boiskoRepository.save(b);
//            repository.save(orlik);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok().build();
//    }

    private Boisko resolveNullId(Boisko temp){
        List<Boisko> boiska = boiskoRepository.findAll();
        for (Boisko b :
                boiska) {
            if(temp.getNazwa().equals(b.getNazwa())) return b;
        }
        return temp;
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Orlik orlik) {
//        Boisko b = (Boisko) boiska.stream().filter(e -> e.getNazwa().equals(orlik.getBoisko().getNazwa())).toArray()[0];
//        System.err.println("b:"+b.toString());
//        orlik.setBoisko(resolveNullId(orlik.getBoisko()));
//        orlik.getBoisko();
        orlik.setId(id);
//        orlik.setBoisko(b);
//        System.err.println("id: "+id+ "orlik: "+orlik.toString());
        try{
            repository.save(orlik);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().build();
        }

    }
}
