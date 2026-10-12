package com.example.Productos.controller;

import com.example.Productos.dto.ProductRequest;
import com.example.Productos.dto.ProductResponse;
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
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest product){
        Optional<Products> optional = service.update(id,toEntity(product));
        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }
    private ProductResponse toResponse (Products product){
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    private Products toEntity(ProductRequest request){
        Products product = new Products();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setStock(request.stock());

        return product;
    }
}
