package com.intcomex.store.repositories.services;

import com.intcomex.store.models.entity.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {

    List<Supplier> getAll();
    Optional<Supplier> getSupplierById(Long id);
    Supplier save(Supplier supplier);
    void delete(Long id);

}
