package com.intcomex.store.repositories;

import com.intcomex.store.models.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier,Long> {
}
