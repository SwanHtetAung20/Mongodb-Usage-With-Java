package com.sha.e_commerce_system.repository;

import com.sha.e_commerce_system.domain.Order;
import com.sha.e_commerce_system.domain.Product;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCategoryOrderByPriceDesc(String category);

    List<Product> findByNameAndTagsIn(String name, List<String> tags);

    Optional<Product> findByName(String name);

    List<Product> findByCategory(String category);

    @Query(value = "{ 'name': ?0 }", collation = "{ 'locale': 'en', 'strength': 1 }")
    List<Product> findByNameCaseInsensitive(String name);

    @Aggregation(pipeline = {
            // this will join orders for a given product id and return enriched product info
            "{ $match: { _id: ?0 } }",
            "{ $lookup: { from: 'order', localField: '_id', foreignField: 'items.productId', as: 'orders' } }"
    })
    List<ProductWithOrders> findProductWithOrdersByProductId(String productId);

    interface ProductWithOrders {
        String getId();

        String getName();

        String getCategory();

        List<Order> getOrders();
    }
}
