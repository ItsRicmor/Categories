package com.categories.cateogoriesapi.services;

import com.categories.cateogoriesapi.models.Category;
import com.categories.cateogoriesapi.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesServiceImp implements CategoriesService {

    private CategoriesRepository categoryRepository;

    @Autowired
    public CategoriesServiceImp(CategoriesRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> list() {
        Iterable<Category> iterable = categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();
        iterable.forEach(categories::add);
        return categories;
    }

    @Override
    public Category get(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new EntityNotFoundException("Categoria no encontrada");
        }
        return  category;
    }

    @Override
    public void save(Category entity) {
        categoryRepository.save(entity);
    }

    @Override
    public void update(Long id, Category entity) {
        Category category = this.get(id);
        category.setDescription(entity.getDescription());
        category.setName(entity.getName());
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        Category category =  this.get(id);
        if(category != null) {
            categoryRepository.delete(category);
        }
    }
}
