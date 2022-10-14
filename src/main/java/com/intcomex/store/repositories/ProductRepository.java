package com.intcomex.store.repositories;

import com.intcomex.store.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
