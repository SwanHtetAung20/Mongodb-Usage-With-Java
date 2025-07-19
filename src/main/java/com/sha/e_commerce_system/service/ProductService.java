package com.sha.e_commerce_system.service;


import com.sha.e_commerce_system.domain.Product;
import com.sha.e_commerce_system.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void save(Product product);

    Optional<Product> findById(String id);

    List<Product> findAll();

    String update(String id, Product product);

    String delete(String id);

    List<Product> findByCategoryOrderByPriceDesc(String category);

    List<Product> findByNameAndTagsIn(String name, List<String> tags);

    List<Product> findByCategory(String category);

    List<Product> findByNameCaseInsensitive(String name);

    List<ProductRepository.ProductWithOrders> findProductWithOrdersByProductId(String productId);
}
