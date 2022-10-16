package com.intcomex.store.repositories.services;

import com.intcomex.store.models.entity.Supplier;
import com.intcomex.store.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService{

    @Autowired
    private SupplierRepository repository;

    @Override
    public List<Supplier> getAll() {
        return (List<Supplier>) repository.findAll();
    }

    @Override
    public Optional<Supplier> getSupplierById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Supplier save(Supplier supplier) {
        return repository.save(supplier);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
