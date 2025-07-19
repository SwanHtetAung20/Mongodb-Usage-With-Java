package com.sha.e_commerce_system.service.impl;

import com.sha.e_commerce_system.domain.Product;
import com.sha.e_commerce_system.repository.ProductRepository;
import com.sha.e_commerce_system.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public void save(Product product) {
        String formattedDate = new java.text.SimpleDateFormat("yyyyMMdd").format(new Date());
        product.setCreatedAt(formattedDate);
        repository.save(product);
    }

    @Override
    public Optional<Product> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public String update(String id, Product product) {
        repository.findById(id).ifPresent(p -> {
            p.setName(product.getName());
            p.setCategory(product.getCategory());
            p.setAvailable(product.isAvailable());
            p.setPrice(product.getPrice());
            p.setTags(product.getTags());
            repository.save(p);
        });
        return "Successfully Updated";
    }

    @Override
    public String delete(String id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            repository.deleteById(id);
        }
        return "Successfully Deleted";
    }

    @Override
    public List<Product> findByCategoryOrderByPriceDesc(String category) {
        return repository.findByCategoryOrderByPriceDesc(category);
    }

    @Override
    public List<Product> findByNameAndTagsIn(String name, List<String> tags) {
        return repository.findByNameAndTagsIn(name, tags);
    }

    @Override
    public List<Product> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    @Override
    public List<Product> findByNameCaseInsensitive(String name) {
        return repository.findByNameCaseInsensitive(name);
    }

    @Override
    public List<ProductRepository.ProductWithOrders> findProductWithOrdersByProductId(String productId) {
        return repository.findProductWithOrdersByProductId(productId);
    }
}
