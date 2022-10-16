package com.intcomex.store.repositories.services;

import com.intcomex.store.models.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll(Integer pageNumber, Integer pageSize);
    Optional<Product> getProductById(Long id);
    Product save(Product product);
    void delete(Long id);

}
