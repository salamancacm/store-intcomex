package com.intcomex.store.controllers;

import com.intcomex.store.models.entity.Category;
import com.intcomex.store.models.entity.Product;
import com.intcomex.store.models.entity.Supplier;
import com.intcomex.store.services.CategoryService;
import com.intcomex.store.services.ProductService;
import com.intcomex.store.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/Products/")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(value= "pageNumber", defaultValue = "10",
                                                        required = false) Integer pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = "1",
                                                        required = false) Integer pageSize ){

        int initialPageNumber = 0;
        int initialSizeNumber = 0;

        if (pageNumber == 1){
            pageNumber = initialPageNumber;
        }else if(pageSize == 1){
            pageSize = initialSizeNumber;
        }

        List<Product> allProducts = service.getAll(pageNumber, pageSize);

        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/Products/{id}")
    public ResponseEntity<?> getSingleProduct(@PathVariable Long id){
        Optional<Product> o = service.getProductById(id);
        if (o.isPresent()){
            return ResponseEntity.ok(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/Product/")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return validateProduct(bindingResult);
        }

        Optional<Supplier> supplierDB = supplierService.getSupplierById(product.getSupplierId());
        Optional<Category> categoryDB = categoryService.getById(product.getCategoryId());

        if (!supplierDB.isPresent() || !categoryDB.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error",
                    "supplierId or categoryId does not exists in database"));
        }

        Product productDB = service.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(productDB);
    }

    @DeleteMapping("/Products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        Optional<Product> o = service.getProductById(id);

        if (o.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validateProduct(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

}
