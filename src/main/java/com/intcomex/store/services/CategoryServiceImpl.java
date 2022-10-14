package com.intcomex.store.services;

import com.intcomex.store.models.entity.Category;
import com.intcomex.store.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return (List<Category>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
