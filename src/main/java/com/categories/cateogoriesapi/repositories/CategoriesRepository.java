package com.categories.cateogoriesapi.repositories;

import com.categories.cateogoriesapi.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends CrudRepository<Category, Long> {
}
