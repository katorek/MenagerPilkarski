package com.meneger.controllers;

import com.meneger.model.DefModel;
import com.meneger.repositories.OrlikRepository;
import com.meneger.repositories.base.BaseRepository;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractRestController<T, S extends BaseRepository<T, Integer>> {

    public static final String HAL_JSON = "application/hal+json;charset=UTF-8";
    public static final String JSON = "application/json;charset=UTF-8";

    protected S repository;

    public AbstractRestController(S repository) {
        this.repository = repository;
    }

    public abstract ResponseEntity<List<T>> getList();
//    public abstract ResponseEntity delete(Integer id);
//    public abstract ResponseEntity update(Integer id, T t);
//    public abstract ResponseEntity add(T t);

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable Integer id){
        T t = getList().getBody().get(id-1);
        if(t == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(t);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity add(@RequestBody T t) {
        try {
            repository.save(t);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestBody T t) {
        if (!repository.exists(id))
            return ResponseEntity.notFound().build();
        try {
            repository.save(t);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok().build();
    }
}
