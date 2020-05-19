package com.categories.cateogoriesapi.services;

import com.categories.cateogoriesapi.models.Category;

import java.util.List;

public interface CategoriesService {
    List<Category> list();
    Category get(Long id);
    void save(Category entity);
    void update(Long id, Category entity);
    void delete(Long id);
}