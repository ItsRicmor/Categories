package com.categories.cateogoriesapi.controllers;

import com.categories.cateogoriesapi.models.Category;
import com.categories.cateogoriesapi.services.CategoriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/categories")
public class CategoriesController {

    public static final Logger logger = LoggerFactory.getLogger(CategoriesController.class);

    private CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> get() throws Exception {
        return ResponseEntity.ok().body(categoriesService.list());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        logger.info("Fetching Category with id {}", id);
        try {
            return ResponseEntity.ok().body(categoriesService.get(id));
        } catch (EntityNotFoundException err) {
            logger.info("Not Found Category with id {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Category> post(@RequestBody Category category) {
        logger.info("Creating Category : {}", category);
        categoriesService.save(category);
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();
        logger.info("Created Category : {}", category);
        //Send location in response
        return ResponseEntity.created(location).body(category);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @RequestBody Category categoryRequest) {
        logger.info("Updating Category with id {}", id);
        categoriesService.update(id, categoryRequest);
        logger.info("Updated Category with id {}", id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        logger.info("Deleting Category with id {}", id);
        categoriesService.delete(id);
        logger.info("Deleted Category with id {}", id);
        return ResponseEntity.ok().build();
    }

}
