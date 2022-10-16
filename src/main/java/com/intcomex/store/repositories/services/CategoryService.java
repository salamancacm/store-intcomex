package com.intcomex.store.repositories.services;

import com.intcomex.store.models.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAll();
    Optional<Category> getById(Long id);
    Category save(Category category);
    void delete(Long id);

}
