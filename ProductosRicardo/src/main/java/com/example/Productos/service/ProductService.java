package com.example.Productos.service;

import com.example.Productos.model.Products;
import com.example.Productos.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository repo;
    public ProductService(ProductRepository repo){
        this.repo = repo;
    }
    public List<Products> getAll(){
        return repo.findAll();
    }
    public Optional<Products> findById(Long id){
        return repo.findById(id);
    }
    public Products save(Products products){
        return repo.save(products);
    }
    public Optional<Products> update(Long id, Products products){
        Optional<Products> optional = repo.findById(id);
        if(optional.isEmpty()){
            return optional.empty();
        }
        Products productDb = optional.get();
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
