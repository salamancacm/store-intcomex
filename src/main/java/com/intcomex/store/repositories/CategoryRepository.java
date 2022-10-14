package com.intcomex.store.repositories;

import com.intcomex.store.models.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
