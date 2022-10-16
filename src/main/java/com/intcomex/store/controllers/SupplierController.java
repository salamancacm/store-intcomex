package com.intcomex.store.controllers;

import com.intcomex.store.models.entity.Supplier;
import com.intcomex.store.repositories.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService service;

    @GetMapping("/Suppliers/")
    public ResponseEntity<List<Supplier>> getAllSuppliers(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/Suppliers/{id}")
    public ResponseEntity<?> getSingleSupplier(@PathVariable Long id){
        Optional<Supplier> o = service.getSupplierById(id);
        if (o.isPresent()){
            return ResponseEntity.ok(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/Suppliers/")
    public ResponseEntity<?> createSupplier(@Valid @RequestBody Supplier supplier, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return validateSupplier(bindingResult);
        }

        Supplier supplierDB = service.save(supplier);

        return ResponseEntity.status(HttpStatus.CREATED).body(supplierDB);
    }

    @DeleteMapping("/Suppliers/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id){
        Optional<Supplier> o = service.getSupplierById(id);

        if (o.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validateSupplier(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

}
