package com.meneger.controllers;

import com.meneger.model.Template;
import com.meneger.repositories.base.BaseRepository;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractRestController<T extends Template, S extends BaseRepository<T, Integer>> {

    public static final String HAL_JSON = "application/hal+json;charset=UTF-8";
    public static final String JSON = "application/json;charset=UTF-8";

    protected S repository;

    public AbstractRestController(S repository) {
        this.repository = repository;
    }

    protected AbstractRestController() {
    }


//    public abstract ResponseEntity<List<T>> getList();

    @GetMapping
    public ResponseEntity getList(){
        try{
            List<T> all = repository.findAll();
            all.forEach(Template::prepare);
            return ResponseEntity.ok(all);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable Integer id){
        T t;
        try{
            t = repository.getOne(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(t);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            repository.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PostMapping
    public ResponseEntity add(@RequestBody T t) {
        System.err.println("T: "+ t.getClass().getCanonicalName()+", "+t.toString());
        try {
            repository.save(t);
            return ResponseEntity.ok().build();
        }catch (DataIntegrityViolationException e){
            System.err.println("DataIntegrityViolationException");
            if(e.getRootCause().getMessage().contains("Duplicate")){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return new ResponseEntity<>(e.getRootCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ConstraintViolationException e){
            System.err.println("ConstraintViolationException");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.err.println("Exception");
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestBody T t) {
        System.err.println("Update id:"+id+", T: "+ t.getClass().getCanonicalName()+", "+t.toString());
        if (!repository.exists(id))
            return ResponseEntity.notFound().build();
        try {
            repository.save(t);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
