package com.intcomex.store.services;

import com.intcomex.store.models.entity.Product;
import com.intcomex.store.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getAll(Integer pageNumber, Integer pageSize) {

        Pageable p = PageRequest.of(pageNumber, pageSize);

        Page<Product> pageProducts = repository.findAll(p);

        return pageProducts.getContent();

    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
