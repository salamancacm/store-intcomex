package com.intcomex.store.controllers;

import com.intcomex.store.models.entity.Category;
import com.intcomex.store.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/Category/")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/Category/")
    public ResponseEntity<?> createCategory(@RequestBody Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return validateCategory(bindingResult);
        }
        Category categoryDB = service.save(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDB);
    }

    @DeleteMapping("/Category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<Category> o = service.getById(id);

        if (o.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validateCategory(BindingResult bindingResult){
        Map<String, String> errors =new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

}
