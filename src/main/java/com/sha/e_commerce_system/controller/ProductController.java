package com.sha.e_commerce_system.controller;

import com.sha.e_commerce_system.domain.Product;
import com.sha.e_commerce_system.repository.ProductRepository;
import com.sha.e_commerce_system.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Product product) {
        this.service.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody Product product) {
        return ResponseEntity.ok(this.service.update(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.service.delete(id));
    }

    @GetMapping("/findByCategory")
    public ResponseEntity<List<Product>> findByCategoryOrderByPriceDesc(@RequestParam("category") String category) {
        return ResponseEntity.ok(this.service.findByCategoryOrderByPriceDesc(category));
    }

    @GetMapping("/findByNameAndTags")
    public ResponseEntity<List<Product>> findByNameAndTagsIn(@RequestParam("name") String name, @RequestParam("tags") List<String> tags) {
        return ResponseEntity.ok(this.service.findByNameAndTagsIn(name, tags));
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<Product>> findByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(this.service.findByNameCaseInsensitive(name));
    }

    @GetMapping("/findProduct/{id}")
    public ResponseEntity<List<ProductRepository.ProductWithOrders>> findProductWithProductId(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.service.findProductWithOrdersByProductId(id));
    }
}

