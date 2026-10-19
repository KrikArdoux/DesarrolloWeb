package com.example.Productos.service;

import com.example.Productos.exception.ResourceNotFoundException;
import com.example.Productos.model.Category;
import com.example.Productos.model.Products;
import com.example.Productos.repository.CategoryRepository;
import com.example.Productos.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;
    private final CategoryRepository repoCategory;

    public ProductService(ProductRepository repo,CategoryRepository repoCategory){
        this.repo = repo;
        this.repoCategory = repoCategory;
    }
    public List<Products> getAll(){
        return repo.findAll();
    }

    public Optional<Products> findById(Long id){
        return repo.findById(id);
    }

    public Products save(Products products, Long categoryId){
        Category category = repoCategory.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category Not Found with ID: " + categoryId)
        );
        products.setCategory(category);
        return repo.save(products);
    }
    public Optional<Products> update(Long id, Products products, Long categoryId){Products productDb= repo.findById(id).orElseThrow(
                ()->
                        new ResourceNotFoundException("Product Not Found with ID: " + id)
        );
        Category category = repoCategory.findById(categoryId).orElseThrow(
                ()->
                        new ResourceNotFoundException("Category Not Found with ID: " + categoryId)
        );
        //Products productDb = optional.get();
        productDb.setCategory(category);
        productDb.setName(products.getName());
        productDb.setPrice(products.getPrice());
        productDb.setStock(products.getStock());
        return Optional.of(repo.save(productDb));
    }
    public boolean delete(Long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
