package com.example.Productos.controller;

import com.example.Productos.model.Products;
import com.example.Productos.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private ProductService service;
    public ProductsController(ProductService service){
        this.service = service;
    }
    @GetMapping()
    public ResponseEntity<List<Products>> findAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Products> getById(@PathVariable long id){
        Optional<Products> product = service.findById(id);
        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<Products> postMethodName(@RequestBody Products product){
        Products savedProduct = service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Products> delete(@PathVariable Long id){
        boolean deleted = service.delete(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Products> update(@PathVariable Long id,@RequestBody Products product){
        Optional<Products> optional = service.update(id,product);
        if(optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
